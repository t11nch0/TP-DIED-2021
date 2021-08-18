package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dominio.Boleto;
import excepciones.BaseDeDatosException;
import gestores.GestorConexion;

public class Boleto_DAO_PostgreSQL implements Boleto_DAO {
    private final Connection conn = GestorConexion.getConnection();

    private static final String INSERT_BOLETO =
            "INSERT INTO died_db.boleto (NRO_BOLETO, EMAIL_CLIENTE, NOMBRE_CLIENTE, FECHA_VENTA, ID_ORIGEN, ID_DESTINO, CAMINO, COSTO) VALUES (?,?,?,?,?,?,?,?) RETURNING ID";


    @Override
    public List<Boleto> buscarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boleto insertarBoleto(Boleto boleto) throws BaseDeDatosException, SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(INSERT_BOLETO);

            pstmt.setInt(1, boleto.getNroBoleto());
            pstmt.setString(2, boleto.getEmailCliente());
            pstmt.setString(3, boleto.getNombreCliente());

            if (boleto.getFechaVenta() != null) {

                Date fechaVenta = java.sql.Date.valueOf(boleto.getFechaVenta());
                pstmt.setDate(4, fechaVenta);

            } else {

                pstmt.setDate(4, null);

            }
            pstmt.setInt(5, boleto.getOrigen().getId());
            pstmt.setInt(6, boleto.getDestino().getId());
           // pstmt.setInt(7, 99);
            pstmt.setString(7, boleto.getCaminoString());
            pstmt.setDouble(8, boleto.getCosto());

            rs = pstmt.executeQuery();

            while (rs.next()) {

                boleto.setId(rs.getInt("ID"));
            }

            conn.commit();

        } catch (SQLException e) {

            conn.rollback();
            e.printStackTrace();
            throw new BaseDeDatosException(e.getMessage());
        } finally {

            try {

                if (pstmt != null) pstmt.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        return boleto;
    }

    @Override
    public void eliminarBoleto() {
        // TODO Auto-generated method stub

    }

    @Override
    public Boleto editarBoleto(Boleto boleto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boleto buscarPorId(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

}
