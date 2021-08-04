package gestores;

import dao.Estacion_DAO;
import dao.Estacion_DAO_PostgreSQL;
import dominio.EstacionDeTransbordoMultimodal;
import dominio.TareaMantenimiento;
import dominio.EstacionDeTransbordoMultimodal.EstadoEstacion;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class GestorEstacion {

	private Estacion_DAO estacionDAO;

	public GestorEstacion() 
	{
		super();
		this.estacionDAO = new Estacion_DAO_PostgreSQL();
	}

	public EstacionDeTransbordoMultimodal crearEstacion(String nombreEstacion, LocalTime apertura, LocalTime cierre, EstadoEstacion estado) throws CamposIncorrectosException, SQLException, BaseDeDatosException
	{
		this.validarDatos(nombreEstacion);
		EstacionDeTransbordoMultimodal e = new EstacionDeTransbordoMultimodal();
		this.actualizarModelo(e, nombreEstacion, apertura, cierre, estado);
		return estacionDAO.insertarEstacion(e);
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
		e.setNombreEstacion(nombreEstacion);
		e.setHorarioApertura(apertura);
		e.setHorarioCierre(cierre);
		e.setEstado(estado); 
	}
	
	public boolean existeNombreDeEstacion(String nombre)
	{
		return estacionDAO.existeNombreDeEstacion(nombre);
	}

	public List<EstacionDeTransbordoMultimodal> listarTodas() 
	{
		return estacionDAO.buscarTodas();
	}
	
	public EstacionDeTransbordoMultimodal buscarPorId(Integer id) 
	{
		return estacionDAO.buscarPorId(id);
	}
	
	//Proximo mantenimiento
	public List<EstacionDeTransbordoMultimodal> prioridadMantenimiento(List<EstacionDeTransbordoMultimodal> estaciones){

		PriorityQueue<EstacionDeTransbordoMultimodal> monticulo = new PriorityQueue<EstacionDeTransbordoMultimodal>(new MonticuloComparator());
		monticulo.addAll(estaciones);
		List<EstacionDeTransbordoMultimodal> lista = new ArrayList<EstacionDeTransbordoMultimodal>();
		
		Integer tamMonticulo = monticulo.size();
		//for(int i=0; i< monticulo.size(); i++)
		for(int i=0; i< tamMonticulo; i++)
			lista.add(monticulo.remove()); 
		return lista;
	}
	
	public class MonticuloComparator implements Comparator<EstacionDeTransbordoMultimodal>{
		public int compare(EstacionDeTransbordoMultimodal estacion1, EstacionDeTransbordoMultimodal estacion2) {
			if(estacion1.getMantenimientos().isEmpty()) {
				if(estacion2.getMantenimientos().isEmpty())
					return 0;
				return -1;
			}
			if(estacion2.getMantenimientos().isEmpty()) {
				if(estacion1.getMantenimientos().isEmpty())
					return 0;
				return 1;
			}
			
			TareaMantenimiento ultimoMantenimiento1 = estacion1.getMantenimientos().get(estacion1.getMantenimientos().size()-1);
			TareaMantenimiento ultimoMantenimiento2 = estacion2.getMantenimientos().get(estacion2.getMantenimientos().size()-1);
			LocalDate fechaFin1 = ultimoMantenimiento1.getFechaFin();
			LocalDate fechaFin2 = ultimoMantenimiento2.getFechaFin();
			if(fechaFin1 == null) fechaFin1 = LocalDate.now();
			if(fechaFin2 == null) fechaFin2 = LocalDate.now();
			if(fechaFin1.isAfter(fechaFin2))
				return 1;
			else if(fechaFin1.isBefore(fechaFin2))
				return -1;
			else
				return 0;
		}
	}
}
