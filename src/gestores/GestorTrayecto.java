package gestores;

import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

import dao.Trayecto_DAO;
import dao.Trayecto_DAO_PostgreSQL;
import dominio.Trayecto;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;

public class GestorTrayecto {


	private Trayecto_DAO TrayectoDAO;
	public GestorTrayecto() 
	{
		super();
		this.TrayectoDAO = new Trayecto_DAO_PostgreSQL();
	}
	 //??
	public Trayecto crearTrayecto() throws CamposIncorrectosException, SQLException, BaseDeDatosException
	{
		Trayecto t = new Trayecto();
		//this.actualizarModelo(t); ///??
		return TrayectoDAO.insertarTrayecto(t);
	} //?
	
	
	/*public void actualizarModelo(Trayecto t)
	{
		
	}*/
	
	public List<Trayecto> listarTodos() 
	{
		return TrayectoDAO.buscarTodos();
	}

	public Trayecto buscarPorId(Integer id) 
	{
		return TrayectoDAO.buscarPorId(id);
	}
}