package dominio;
import java.time.LocalDate;

public class TareaMantenimiento {

	private Integer id;
	private LocalDate fechaInicio; //?
	private LocalDate fechaFin; //?
	private String observaciones;
	//Estacion?
	private EstacionDeTransbordoMultimodal estacion; //?
	
	public TareaMantenimiento(Integer id, LocalDate inicio, String observaciones, EstacionDeTransbordoMultimodal estacion) {
		this.id = id;
		this.fechaInicio = inicio;
		this.fechaFin = null;
		this.observaciones = observaciones;
		this.estacion = estacion;
	}
	public TareaMantenimiento() {
		super();//?	
	}
	
	// Setters?

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
	
	public EstacionDeTransbordoMultimodal getEstacion() {
		return estacion;
	}
	public void setEstacion(EstacionDeTransbordoMultimodal estacion) {
		this.estacion = estacion;
	}
	
	
	
	
}
