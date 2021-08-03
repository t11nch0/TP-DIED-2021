package gestores;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

//import dominio.EstacionDeTransbordoMultimodal;
import dominio.TareaMantenimiento;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;

public class GestorMantenimiento {

	private List<TareaMantenimiento> mantenimientos;
	
	public GestorMantenimiento() {
		super();
	}
	
	public List<TareaMantenimiento> listarTodas() 
	{
		return mantenimientos;		
	}
	
	public TareaMantenimiento crearTareaMantenimiento(Integer id, LocalDate inicio, String observaciones) throws CamposIncorrectosException, SQLException, BaseDeDatosException
	{ //?
		TareaMantenimiento m = new TareaMantenimiento();
		this.actualizarModelo(m, id, inicio, observaciones); //?
		mantenimientos.add(m);
		return m; //? 
	}
	
	public void actualizarModelo(TareaMantenimiento m, Integer id, LocalDate inicio, String observaciones)
	{
		m.setId(id); 
		m.setFechaInicio(inicio); 
		m.setObservaciones(observaciones); 
	}
	
}
