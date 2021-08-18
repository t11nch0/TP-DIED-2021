package dao;

import java.sql.SQLException;
import java.util.List;

import dominio.LineaTransporte;
import excepciones.BaseDeDatosException;

public interface LineaTransporte_DAO {

    List<LineaTransporte> buscarTodas();

    LineaTransporte insertarLineaTransporte(LineaTransporte linea) throws BaseDeDatosException, SQLException;

    void eliminarLineaTransporte(LineaTransporte linea) throws BaseDeDatosException, SQLException;

    LineaTransporte editarLineaTransporte(LineaTransporte linea) throws BaseDeDatosException, SQLException;

    boolean existeNombreDeLinea(String nombre);

    LineaTransporte buscarPorId(Integer id);

    List<LineaTransporte> filtrar(String[] param);

}
