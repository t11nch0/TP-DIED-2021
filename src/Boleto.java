import java.time.LocalDateTime;

public class Boleto {

	private Integer id; //nroBoleto
	
	private Integer nroBoleto;
	
	private String emailCliente;
	private String nombreCliente;
	private LocalDateTime fechaVenta; //?
	private EstacionDeTransbordoMultimodal origen;
	private EstacionDeTransbordoMultimodal destino;
	private Ruta camino; // Lista? Trayecto?!!! (combinaciones)
	private Double costo;
	
	
	
	public Boleto(Integer id, Integer nro, String email, String nombre,
			LocalDateTime fecha, EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino,
			Ruta camino, Double costo) {
		this.id = id;
		this.nroBoleto = nro;
		this.emailCliente = email;
		this.nombreCliente = nombre;
		this.fechaVenta = fecha;
		this.origen = origen;
		this.destino = destino;
		this.camino = camino;
		this.costo = costo;	
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getNroBoleto() {
		return nroBoleto;
	}



	public void setNroBoleto(Integer nroBoleto) {
		this.nroBoleto = nroBoleto;
	}



	public String getEmailCliente() {
		return emailCliente;
	}



	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}



	public String getNombreCliente() {
		return nombreCliente;
	}



	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}



	public LocalDateTime getFechaVenta() {
		return fechaVenta;
	}



	public void setFechaVenta(LocalDateTime fechaVenta) {
		this.fechaVenta = fechaVenta;
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



	public Ruta getCamino() {
		return camino;
	}



	public void setCamino(Ruta camino) {
		this.camino = camino;
	}



	public Double getCosto() {
		return costo;
	}



	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	
	
	
}
