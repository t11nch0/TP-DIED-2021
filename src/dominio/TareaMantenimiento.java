package dominio;
import java.time.LocalDate;

public class TareaMantenimiento {

	private Integer id;
	private LocalDate fechaInicio; 
	private LocalDate fechaFin; 
	private String observaciones;
//	private EstacionDeTransbordoMultimodal estacion; //bucle?
	private Integer idEstacion;
	
	//public TareaMantenimiento(Integer id, LocalDate inicio, String observaciones, EstacionDeTransbordoMultimodal estacion) {
	public TareaMantenimiento(Integer id, LocalDate inicio, String observaciones, Integer idEstacion) {
		this.id = id;
		this.fechaInicio = inicio;
		this.fechaFin = null;
		this.observaciones = observaciones;
		//this.estacion = estacion;
		//this.idEstacion = estacion.getId(); //?
		this.idEstacion = idEstacion;
	}
	public TareaMantenimiento() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public void finalizarTareaMantenimiento(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	/*public EstacionDeTransbordoMultimodal getEstacion() {
		return estacion;
	}
	public void setEstacion(EstacionDeTransbordoMultimodal estacion) {
		this.estacion = estacion;
	}*/
	
	public Integer getIdEstacion() {
		return idEstacion;
	}
	public void setIdEstacion(Integer idEstacion) {
		this.idEstacion = idEstacion;
	}	
	
	
}
