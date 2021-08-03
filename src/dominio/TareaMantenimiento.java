package dominio;
import java.time.LocalDate;

public class TareaMantenimiento {

	private Integer id;
	private LocalDate fechaInicio; //?
	private LocalDate fechaFin; //?
	private String observaciones;
	//Estacion?
	
	public TareaMantenimiento(Integer id, LocalDate inicio, String observaciones) {
		this.id = id;
		this.fechaInicio = inicio;
		this.fechaFin = null;
		this.observaciones = observaciones;
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
	
	
	
	
}
