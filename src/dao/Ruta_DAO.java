package dao;

import java.sql.SQLException;
import java.util.List;

import dominio.Ruta;
import excepciones.BaseDeDatosException;

public interface Ruta_DAO 
{
	
	public List<Ruta> buscarTodas();
	
	public Ruta insertarRuta(Ruta ruta) throws BaseDeDatosException, SQLException;
	public void eliminarRuta();
	public Ruta editarRuta(Ruta ruta) throws BaseDeDatosException, SQLException;
	public Ruta buscarPorId(Integer id);
	
	public List<Ruta> buscarPorIdTrayecto(Integer idTrayecto);
}
