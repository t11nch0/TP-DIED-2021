public class Ruta {
	
	private Integer id;
	
    private EstacionDeTransbordoMultimodal origen;
    private EstacionDeTransbordoMultimodal destino;
    private Integer distanciaKilometros;
    private Integer duracionViajeMinutos;
    private Integer pasajerosMaximos;
    private Boolean estadoActiva;
    private Double costo;

    public Ruta(Integer id, EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino,
    		Integer distancia, Integer duracion, Integer pasajeros, Boolean estado, Double costo) {
    	
    	this.id = id;
    	this.origen = origen;
    	this.destino = destino;
    	this.distanciaKilometros = distancia;
    	this.duracionViajeMinutos = duracion;
    	this.pasajerosMaximos = pasajeros;
    	this.estadoActiva = estado; //?
    	this.costo = costo;   	
    	
    }
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstacionDeTransbordoMultimodal getOrigen() {
		return origen;
	}

	public void setOrigen(EstacionDeTransbordoMultimodal origen) {
		this.origen = origen;
	}

	public EstacionDeTransbordoMultimodal getDestino() {
		return destino;
	}

	public void setDestino(EstacionDeTransbordoMultimodal destino) {
		this.destino = destino;
	}

	public Integer getDistanciaKilometros() {
		return distanciaKilometros;
	}

	public void setDistanciaKilometros(Integer distanciaKilometros) {
		this.distanciaKilometros = distanciaKilometros;
	}

	public Integer getDuracionViajeMinutos() {
		return duracionViajeMinutos;
	}

	public void setDuracionViajeMinutos(Integer duracionViajeMinutos) {
		this.duracionViajeMinutos = duracionViajeMinutos;
	}

	public Integer getPasajerosMaximos() {
		return pasajerosMaximos;
	}

	public void setPasajerosMaximos(Integer pasajerosMaximos) {
		this.pasajerosMaximos = pasajerosMaximos;
	}

	public Boolean getEstadoActiva() {
		return estadoActiva;
	}

	public void setEstadoActiva(Boolean estadoActiva) {
		this.estadoActiva = estadoActiva;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}
    
    
}

