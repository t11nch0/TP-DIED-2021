import java.time.LocalDateTime;

public class TareaMantenimiento {

	private Integer id;
	private LocalDateTime fechaInicio; //?
	private LocalDateTime fechaFin; //?
	private String observaciones;
	//Estacion?
	
	public TareaMantenimiento(Integer id, LocalDateTime inicio, LocalDateTime fin, String observaciones) {
		this.id = id;
		this.fechaInicio = inicio;
		this.fechaFin = fin;
		this.observaciones = observaciones;
	}
	
	// Setters?

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	
	
}
