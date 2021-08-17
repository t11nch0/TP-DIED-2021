package dao;

import java.sql.SQLException;
import java.util.List;

import dominio.Trayecto;
import excepciones.BaseDeDatosException;

public interface Trayecto_DAO 
{
	
	List<Trayecto> buscarTodos();
	
	Trayecto insertarTrayecto(Trayecto trayecto) throws BaseDeDatosException, SQLException;
	void eliminarTrayecto();
	Trayecto editarTrayecto(Trayecto trayecto) throws BaseDeDatosException, SQLException;

	Trayecto buscarPorId(Integer id);
	List<Trayecto> buscarPorIdLinea(Integer id);
}
