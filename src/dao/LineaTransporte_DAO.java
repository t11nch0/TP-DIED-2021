package dao;

import java.sql.SQLException;
import java.util.List;

import dominio.LineaTransporte;
import excepciones.BaseDeDatosException;

public interface LineaTransporte_DAO 
{
	
	public List<LineaTransporte> buscarTodas();
	public LineaTransporte insertarLineaTransporte(LineaTransporte linea) throws BaseDeDatosException, SQLException;
	public void eliminarLineaTransporte(LineaTransporte linea) throws BaseDeDatosException, SQLException;
	public LineaTransporte editarLineaTransporte(LineaTransporte linea) throws BaseDeDatosException, SQLException;

	public boolean existeNombreDeLinea(String nombre);
	public LineaTransporte buscarPorId(Integer id);
	//Las busquedas por atributos se resuelven al mostrar en pantalla. Se buscan todas y se omite lo innecesario.
	
}
