package dao;

import java.util.List;
import dominio.EstacionDeTransbordoMultimodal;
import java.sql.SQLException;
import excepciones.BaseDeDatosException;

public interface Estacion_DAO {

	List<EstacionDeTransbordoMultimodal> buscarTodas();
	
	EstacionDeTransbordoMultimodal insertarEstacion(EstacionDeTransbordoMultimodal estacion) throws BaseDeDatosException, SQLException;
	void eliminarEstacion(EstacionDeTransbordoMultimodal estacion)throws BaseDeDatosException, SQLException;
	EstacionDeTransbordoMultimodal editarEstacion(EstacionDeTransbordoMultimodal estacion) throws BaseDeDatosException, SQLException;
	
	boolean existeNombreDeEstacion(String nombre);
	EstacionDeTransbordoMultimodal buscarPorId(Integer id);

	List<EstacionDeTransbordoMultimodal> filtrar(String[] param);
	
	//Las busquedas por atributos se resuelven al mostrar en pantalla. Se buscan todas y se omite lo innecesario.
}
