package dominio;


public class Ruta {
	
	private Integer id;
	private EstacionDeTransbordoMultimodal origen;
	private EstacionDeTransbordoMultimodal destino;
	private Integer distanciaKilometros;
	private Integer duracionViajeMinutos;
	private Integer pasajerosMaximos;
	private EstadoRuta estado;
	private Double costo;
	private Trayecto trayecto; 
	private Integer idTrayecto;

    public enum EstadoRuta 
	{
		ACTIVA, INACTIVA;
	}
    
    public Ruta(Integer id, EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino,
    		Integer distancia, Integer duracion, Integer pasajeros, EstadoRuta estado, Double costo, Integer idTrayecto) {
    	
    	this.id = id;
    	this.origen = origen;
    	this.destino = destino;
    	this.distanciaKilometros = distancia;
    	this.duracionViajeMinutos = duracion;
    	this.pasajerosMaximos = pasajeros;
    	this.estado = estado; 
    	this.costo = costo;   	
    	//this.trayecto = trayecto;  //null?
    	this.trayecto = null;
    	this.idTrayecto = idTrayecto;
    }
    
    //?
    public Ruta(EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino,
    		Integer distancia, Integer duracion, Integer pasajeros, EstadoRuta estado, Double costo) {
    	//Para creacion de trayectos (?)
    	this.origen = origen;
    	this.destino = destino;
    	this.distanciaKilometros = distancia;
    	this.duracionViajeMinutos = duracion;
    	this.pasajerosMaximos = pasajeros;
    	this.estado = estado; 
    	this.costo = costo;
    }
    //
    
	public Ruta() {
		super();
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

	public EstadoRuta getEstadoRuta() {
		return estado;
	}

	public void setEstadoRuta(EstadoRuta estado) {
		this.estado = estado;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}


	public Trayecto getTrayecto() {
		return trayecto;
	}

	public void setTrayecto(Trayecto trayecto) {
		this.trayecto = trayecto;
	}
	
	public boolean esActiva() {
		return(estado == EstadoRuta.ACTIVA);
	}

	public Integer getIdTrayecto() {
		return idTrayecto;
	}

	public void setIdTrayecto(Integer idTrayecto) {
		this.idTrayecto = idTrayecto;
	}
	
	public void relacionarTrayecto(Trayecto t) {
		this.trayecto = t;
	}
	//?
}


