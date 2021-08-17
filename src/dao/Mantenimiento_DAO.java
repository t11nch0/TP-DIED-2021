package dao;

import java.util.List;

import dominio.TareaMantenimiento;

import java.sql.SQLException;

import excepciones.BaseDeDatosException;

public interface Mantenimiento_DAO {

    List<TareaMantenimiento> buscarTodos();

    TareaMantenimiento insertarMantenimiento(TareaMantenimiento mantenimiento) throws BaseDeDatosException, SQLException;

    TareaMantenimiento finalizarMantenimiento(TareaMantenimiento mantenimiento) throws BaseDeDatosException, SQLException;

    List<TareaMantenimiento> buscarPorIdEstacion(Integer idEstacion);

}
