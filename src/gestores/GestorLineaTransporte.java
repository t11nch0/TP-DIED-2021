package gestores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.LineaTransporte_DAO;
import dao.LineaTransporte_DAO_PostgreSQL;
import dominio.LineaTransporte;
import dominio.LineaTransporte.EstadoLinea;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;

public class GestorLineaTransporte {
	
	private LineaTransporte_DAO lineaDAO;
	public GestorLineaTransporte() 
	{
		super();
		this.lineaDAO = new LineaTransporte_DAO_PostgreSQL();
	}
	public LineaTransporte crearLinea(String nombre, String color, EstadoLinea estado) throws CamposIncorrectosException, SQLException, BaseDeDatosException
	{
		this.validarDatos(nombre);
		LineaTransporte l = new LineaTransporte();
		this.actualizarModelo(l, nombre, color, estado);
		return lineaDAO.insertarLineaTransporte(l);
	} 
	
	public void validarDatos(String nombre) throws CamposIncorrectosException
	{
			StringBuilder mensajeAMostrar = new StringBuilder();
			List<String> lista_de_campos_erroneos = new ArrayList<String>();
			if(nombre.isEmpty())
			{
				lista_de_campos_erroneos.add("Nombre");
				mensajeAMostrar.append("\n"+"- Nombre de la Linea de Transporte. (Campo incompleto-Dato Obligatorio)"+"\n");
			}
			else
			{
				if(this.existeNombreDeLinea(nombre))
				{
					lista_de_campos_erroneos.add("Nombre");
					mensajeAMostrar.append("\n"+"- El nombre de la linea de transporte ya existe."+"\n");
				}
			}	
			if(mensajeAMostrar.length() != 0)
			{
				throw new CamposIncorrectosException(mensajeAMostrar.toString(), lista_de_campos_erroneos);
			}
	}
	public void actualizarModelo(LineaTransporte l, String nombre, String color, EstadoLinea estado)
	{
		l.setNombre(nombre);
		l.setColor(color);
		l.setEstado(estado);
	}
	
	public boolean existeNombreDeLinea(String nombre)
	{
		return lineaDAO.existeNombreDeLinea(nombre);
	}
	public List<LineaTransporte> listarTodas() 
	{
		return lineaDAO.buscarTodas();
	}
	
	public LineaTransporte buscarPorId(Integer id) 
	{
		return lineaDAO.buscarPorId(id);
	}
	
}

