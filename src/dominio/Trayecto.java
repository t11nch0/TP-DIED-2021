package dominio;
//import java.util.ArrayList;
import java.util.List;


public class Trayecto {

	private Integer id;
	
	private LineaTransporte linea; 
	private List<Ruta> tramos; 
	
	public Trayecto(Integer id, LineaTransporte linea, List<Ruta> tramos) { //?
		this.id = id;
		this.linea= linea;
		this.tramos = tramos;
	}
	
	public Trayecto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Ruta> getTramos() {
		return tramos;
	}

	public void setTramos(List<Ruta> tramos) {
		this.tramos = tramos;
	}

	public LineaTransporte getLinea() {
		return linea;
	}

	public void setLinea(LineaTransporte linea) {
		this.linea = linea;
	}
	
	public void agregarRuta(Ruta r) {
		this.tramos.add(r);
	}
	

	
	
	
}	
	
	