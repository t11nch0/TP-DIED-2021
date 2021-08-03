package dominio;

import java.time.LocalDate;
//import java.time.LocalDateTime;

public class Boleto {

	private Integer id;
	
	private Integer nroBoleto;
	private String emailCliente;
	private String nombreCliente;
	private LocalDate fechaVenta;
	private EstacionDeTransbordoMultimodal origen; //?
	private EstacionDeTransbordoMultimodal destino; //?
	private Camino camino;
	private Double costo;
	
	
	
	public Boleto(Integer id, Integer nro, String email, String nombre,
			LocalDate fecha, EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino,
			Camino camino, Double costo) {
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
	public Boleto() {
		super();
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



	public LocalDate getFechaVenta() {
		return fechaVenta;
	}



	public void setFechaVenta(LocalDate fechaVenta) {
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



	public Camino getCamino() {
		return camino;
	}



	public void setCamino(Camino camino) {
		this.camino = camino;
	}



	public Double getCosto() {
		return costo;
	}
	//? costoTotal del camino
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	
	
	
}
