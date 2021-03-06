package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import dominio.Ruta;
import dominio.Trayecto;
import excepciones.BaseDeDatosException;
import gestores.GestorConexion;
import gestores.GestorTrayecto;

public class Trayecto_DAO_PostgreSQL implements Trayecto_DAO {

    private final Connection conn = GestorConexion.getConnection();

    private static final String SELECT_ALL_TRAYECTO =
            "SELECT * FROM died_db.trayecto";

    private static final String INSERT_TRAYECTO =
            "INSERT INTO died_db.trayecto (ID_LINEA) VALUES (?) RETURNING ID";

    private static final String UPDATE_TRAYECTO =
            "UPDATE died_db.trayecto SET ID_LINEA = ? " +
                    "WHERE ID = ?";

    @Override
    public List<Trayecto> buscarTodos() {
        List<Trayecto> lista = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        LineaTransporte_DAO lineaDAO = new LineaTransporte_DAO_PostgreSQL();

        try {
            pstmt = conn.prepareStatement(SELECT_ALL_TRAYECTO);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Trayecto t = new Trayecto();
                t.setId((rs.getInt("ID")));
                t.setIdLinea(rs.getInt("ID_LINEA"));
                lista.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }


    @Override
    public Trayecto insertarTrayecto(Trayecto trayecto)
            throws BaseDeDatosException, SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs;

        Ruta_DAO rutaDAO = new Ruta_DAO_PostgreSQL();
        GestorTrayecto gestorTrayecto = new GestorTrayecto();

        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(INSERT_TRAYECTO);

            pstmt.setInt(1, trayecto.getIdLinea());

            rs = pstmt.executeQuery();
            while (rs.next()) {
                trayecto.setId(rs.getInt("ID"));
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
        return trayecto;
    }

    @Override
    public void eliminarTrayecto() {

    }

    @Override
    public Trayecto editarTrayecto(Trayecto trayecto) throws BaseDeDatosException, SQLException {

        PreparedStatement pstmt = null;
        Ruta_DAO rutaDAO = new Ruta_DAO_PostgreSQL();
        List<Ruta> tramos = trayecto.getTramos();
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(UPDATE_TRAYECTO);

            pstmt.setInt(1, trayecto.getIdLinea());
            for (Ruta unaRuta : tramos) {
                rutaDAO.insertarRuta(unaRuta);
            }

            pstmt.setInt(3, trayecto.getId());
            pstmt.executeUpdate();
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
        return trayecto;
    }


    @Override
    public Trayecto buscarPorId(Integer id) {
        return this.buscarTodos()
                .stream()
                .filter(t -> Objects.equals(t.getId(), id))
                .findFirst()
                .get();
    }


    @Override
    public List<Trayecto> buscarPorIdLinea(Integer idLinea) {
        return this.buscarTodos()
                .stream()
                .filter(t -> Objects.equals(t.getIdLinea(), idLinea))
                .collect(Collectors.toList());
    }
}


	
	
	
	

