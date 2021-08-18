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
import dominio.Ruta.EstadoRuta;
import excepciones.BaseDeDatosException;
import gestores.GestorConexion;

public class Ruta_DAO_PostgreSQL implements Ruta_DAO {
    private final Connection conn = GestorConexion.getConnection();

    private static final String SELECT_ALL_RUTA =
            "SELECT * FROM died_db.ruta";

    private static final String INSERT_RUTA =
            "INSERT INTO died_db.ruta (ID_ORIGEN, ID_DESTINO, DISTANCIA_KILOMETROS, DURACION_VIAJE_MINUTOS, PASAJEROS_MAXIMO, ESTADO_RUTA, COSTO, ID_TRAYECTO) VALUES (?,?,?,?,?,?,?,?) RETURNING ID";


    @Override
    public List<Ruta> buscarTodas() {
        List<Ruta> lista = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Estacion_DAO estacionDAO = new Estacion_DAO_PostgreSQL();

        try {
            pstmt = conn.prepareStatement(SELECT_ALL_RUTA);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Ruta r = new Ruta();
                r.setId((rs.getInt("ID")));
                r.setOrigen(estacionDAO.buscarPorId(rs.getInt("ID_ORIGEN")));
                r.setDestino(estacionDAO.buscarPorId(rs.getInt("ID_DESTINO")));
                r.setDistanciaKilometros(rs.getInt("DISTANCIA_KILOMETROS"));
                r.setDuracionViajeMinutos(rs.getInt("DURACION_VIAJE_MINUTOS"));
                r.setPasajerosMaximos(rs.getInt("PASAJEROS_MAXIMO"));

                switch (rs.getString("ESTADO_RUTA")) {
                    case "ACTIVA":
                        r.setEstadoRuta(EstadoRuta.ACTIVA);
                        break;
                    case "INACTIVA":
                        r.setEstadoRuta(EstadoRuta.INACTIVA);
                        break;
                }
                r.setCosto(rs.getDouble("COSTO"));
                r.setIdTrayecto(rs.getInt("ID_TRAYECTO"));

                lista.add(r);
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
    public void insertarRuta(Ruta ruta) throws BaseDeDatosException, SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(INSERT_RUTA);
            pstmt.setInt(1, ruta.getOrigen().getId());
            pstmt.setInt(2, ruta.getDestino().getId());
            pstmt.setInt(3, ruta.getDistanciaKilometros());
            pstmt.setInt(4, ruta.getDuracionViajeMinutos());
            pstmt.setInt(5, ruta.getPasajerosMaximos());
            pstmt.setString(6, ruta.getEstadoRuta().toString());
            pstmt.setDouble(7, ruta.getCosto());
            pstmt.setInt(8, ruta.getIdTrayecto());

            rs = pstmt.executeQuery();
            while (rs.next()) {
                ruta.setId(rs.getInt("ID"));
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

    }


    @Override
    public void eliminarRuta() {
        // TODO Auto-generated method stub

    }

    @Override
    public Ruta editarRuta(Ruta ruta) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Ruta buscarPorId(Integer id) {
        {
            Ruta ruta = new Ruta();
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            Estacion_DAO estacionDAO = new Estacion_DAO_PostgreSQL();
            try {

                pstmt = conn.prepareStatement("SELECT * FROM died_db.ruta WHERE ID = " + id);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    ruta.setId((rs.getInt("ID")));
                    ruta.setOrigen(estacionDAO.buscarPorId(rs.getInt("ID_ORIGEN")));
                    ruta.setDestino(estacionDAO.buscarPorId(rs.getInt("ID_DESTINO")));
                    ruta.setDistanciaKilometros(rs.getInt("DISTANCIA_KILOMETROS"));
                    ruta.setDuracionViajeMinutos(rs.getInt("DURACION_VIAJE_MINUTOS"));
                    ruta.setPasajerosMaximos(rs.getInt("PASAJEROS_MAXIMO"));

                    switch (rs.getString("ESTADO_RUTA")) {
                        case "ACTIVA":
                            ruta.setEstadoRuta(EstadoRuta.ACTIVA);
                            break;
                        case "INACTIVA":
                            ruta.setEstadoRuta(EstadoRuta.INACTIVA);
                            break;
                    }
                    ruta.setCosto(rs.getDouble("COSTO"));


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
            return ruta;
        }
    }

    public List<Ruta> buscarPorIdTrayecto(Integer idTrayecto) {
        return this.buscarTodas()
                .stream()
                .filter(r -> Objects.equals(r.getTrayecto().getId(), idTrayecto))
                .collect(Collectors.toList());
    }

}
