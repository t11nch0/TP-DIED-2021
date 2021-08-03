package gestores;

import dao.Estacion_DAO;
import dao.Estacion_DAO_PostgreSQL;
import dominio.EstacionDeTransbordoMultimodal;

import dominio.EstacionDeTransbordoMultimodal.EstadoEstacion;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class GestorEstacion {

		private Estacion_DAO EstacionDAO;
	public GestorEstacion() 
	{
		super();
		this.EstacionDAO = new Estacion_DAO_PostgreSQL();
	}

	public EstacionDeTransbordoMultimodal crearEstacion(String nombreEstacion, LocalTime apertura, LocalTime cierre, EstadoEstacion estado) throws CamposIncorrectosException, SQLException, BaseDeDatosException
	{
		this.validarDatos(nombreEstacion);
		EstacionDeTransbordoMultimodal e = new EstacionDeTransbordoMultimodal();
		this.actualizarModelo(e, nombreEstacion, apertura, cierre, estado);
		return EstacionDAO.insertarEstacion(e);
	}
	
	public void validarDatos(String nombreEstacion) throws CamposIncorrectosException
	{
			
			StringBuilder mensajeAMostrar = new StringBuilder();
			List<String> lista_de_campos_erroneos = new ArrayList<String>();
			if(nombreEstacion.isEmpty())
			{
				lista_de_campos_erroneos.add("Nombre");
				mensajeAMostrar.append("\n"+"- Nombre de la Estacion. (Campo incompleto-Dato Obligatorio)"+"\n");
			}
			else
			{
				if(this.existeNombreDeEstacion(nombreEstacion))
				{
					lista_de_campos_erroneos.add("Nombre");
					mensajeAMostrar.append("\n"+"- El nombre de la estacion ya existe."+"\n");
				}
			}	
			if(mensajeAMostrar.length() != 0)
			{
				throw new CamposIncorrectosException(mensajeAMostrar.toString(), lista_de_campos_erroneos);
			}
	}
	public void actualizarModelo(EstacionDeTransbordoMultimodal e, String nombreEstacion, LocalTime apertura, LocalTime cierre, EstadoEstacion estado)
	{
		e.setNombreEstacion(nombreEstacion); //?
		e.setHorarioApertura(apertura);
		e.setHorarioCierre(cierre);
		e.setEstado(estado); // EstadoEstacion.estado?
	}
	
	public boolean existeNombreDeEstacion(String nombre)
	{
		return EstacionDAO.existeNombreDeEstacion(nombre);
	}

	public List<EstacionDeTransbordoMultimodal> listarTodas() 
	{
		return EstacionDAO.buscarTodas();
	}
	
	public EstacionDeTransbordoMultimodal buscarPorId(Integer id) 
	{
		return EstacionDAO.buscarPorId(id);
	}
	
}
