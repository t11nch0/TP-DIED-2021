import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class EstacionDeTransbordoMultimodal {
    private Integer id;
	private String nombreEstacion;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private Boolean abierto; //? op y mant
//Lisra de mantenimietns_??
    private List<TareaMantenimiento> mantenimientos; 
    
    public EstacionDeTransbordoMultimodal(Integer id, String nombre, LocalTime hApertura, LocalTime hCierre, Boolean abierto) {
    	this.id = id;
    	this.nombreEstacion = nombre;
    	this.horarioApertura = hApertura;
    	this.horarioCierre = hCierre;
    	this.abierto = abierto; // ?
    	
    	this.mantenimientos = new ArrayList<TareaMantenimiento>();
    }


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombreEstacion() {
		return nombreEstacion;
	}


	public void setNombreEstacion(String nombreEstacion) {
		this.nombreEstacion = nombreEstacion;
	}


	public LocalTime getHorarioApertura() {
		return horarioApertura;
	}


	public void setHorarioApertura(LocalTime horarioApertura) {
		this.horarioApertura = horarioApertura;
	}


	public LocalTime getHorarioCierre() {
		return horarioCierre;
	}


	public void setHorarioCierre(LocalTime horarioCierre) {
		this.horarioCierre = horarioCierre;
	}


	public Boolean getAbierto() {
		return abierto;
	}

    //??
	
	
	public void setAbierto(Boolean abierto) {
		this.abierto = abierto;
	}
    
	
	public void abrir() {
		abierto = true;
	}
	
	public void cerrar() {
		abierto = false;
	}
 
	
	public List<TareaMantenimiento> getMantenimientos() {
		return mantenimientos;
	}
	
	public void setMantenimientos(List<TareaMantenimiento> mantenimientos) {
		this.mantenimientos = mantenimientos;
	}
    
}

