package dao;

import java.util.List;
import dominio.EstacionDeTransbordoMultimodal;
import java.sql.SQLException;
import excepciones.BaseDeDatosException;

public interface Estacion_DAO {

	public List<EstacionDeTransbordoMultimodal> buscarTodas();
	
	public EstacionDeTransbordoMultimodal insertarEstacion(EstacionDeTransbordoMultimodal estacion) throws BaseDeDatosException, SQLException;
	public void eliminarEstacion(EstacionDeTransbordoMultimodal estacion)throws BaseDeDatosException, SQLException;
	public EstacionDeTransbordoMultimodal editarEstacion(EstacionDeTransbordoMultimodal estacion) throws BaseDeDatosException, SQLException;
	
	public boolean existeNombreDeEstacion(String nombre);
	public EstacionDeTransbordoMultimodal buscarPorId(Integer id);
		
}
