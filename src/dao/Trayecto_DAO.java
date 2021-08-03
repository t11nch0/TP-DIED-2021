package dao;

import java.sql.SQLException;
import java.util.List;

//import dominio.LineaTransporte;
import dominio.Trayecto;
import excepciones.BaseDeDatosException;

public interface Trayecto_DAO 
{
	
	public List<Trayecto> buscarTodos();
	
	public Trayecto insertarTrayecto(Trayecto trayecto) throws BaseDeDatosException, SQLException;
	public void eliminarTrayecto();
	public Trayecto editarTrayecto(Trayecto trayecto) throws BaseDeDatosException, SQLException;

	public Trayecto buscarPorId(Integer id); //?
	
	public List<Trayecto> buscarPorIdLinea(Integer id); //?
}