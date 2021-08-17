package dao;

import java.sql.SQLException;
import java.util.List;

import dominio.Ruta;
import excepciones.BaseDeDatosException;

public interface Ruta_DAO 
{
	
	List<Ruta> buscarTodas();
	
	void insertarRuta(Ruta ruta) throws BaseDeDatosException, SQLException;
	void eliminarRuta();
	Ruta editarRuta(Ruta ruta) throws BaseDeDatosException, SQLException;
	Ruta buscarPorId(Integer id);
	
	List<Ruta> buscarPorIdTrayecto(Integer idTrayecto);
}
