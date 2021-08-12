package gestores;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dao.Mantenimiento_DAO;
import dao.Mantenimiento_DAO_PostgreSQL;
import dominio.EstacionDeTransbordoMultimodal;
import dominio.TareaMantenimiento;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;

public class GestorMantenimiento {

	private Mantenimiento_DAO mantenimientoDAO;
	private List<TareaMantenimiento> mantenimientos;
	
	public GestorMantenimiento() {
		super();
		this.mantenimientoDAO = new Mantenimiento_DAO_PostgreSQL();//?
	}
	
	public List<TareaMantenimiento> listarTodas() 
	{
		return mantenimientos;		
	}
	
	public TareaMantenimiento crearTareaMantenimiento(String observaciones, EstacionDeTransbordoMultimodal estacion) throws CamposIncorrectosException, SQLException, BaseDeDatosException
	{ 
		TareaMantenimiento m = new TareaMantenimiento();
		LocalDate fechaInicio = LocalDate.now();
		this.actualizarModelo(m, fechaInicio, null, observaciones, estacion); 
		mantenimientos.add(m); 
		return mantenimientoDAO.insertarMantenimiento(m);
	}
	public TareaMantenimiento finalizarTareaMantenimiento(String observaciones, EstacionDeTransbordoMultimodal estacion) throws BaseDeDatosException, SQLException {
		TareaMantenimiento m = mantenimientoDAO.buscarPorIdEstacion(estacion.getId()).get(estacion.getMantenimientos().size()-1);
		LocalDate fechaFin = LocalDate.now();
		this.actualizarModelo(m, m.getFechaInicio(), fechaFin, observaciones, estacion); 
		return mantenimientoDAO.finalizarMantenimiento(m);
	}
	
	public void actualizarModelo(TareaMantenimiento m, LocalDate inicio, LocalDate fin, String observaciones, EstacionDeTransbordoMultimodal estacion)
	{
	//	m.setId(id); 
		m.setFechaInicio(inicio); 
		m.setFechaFin(fin);
		m.setObservaciones(observaciones); //
		m.setEstacion(estacion);
	}
	
	public List<TareaMantenimiento> listarTodos() 
	{
		return mantenimientoDAO.buscarTodos();
	}
	
	public List<TareaMantenimiento> buscarPorIdEstacion(Integer idEstacion) 
	{
		return mantenimientoDAO.buscarPorIdEstacion(idEstacion);
	}
	
}
