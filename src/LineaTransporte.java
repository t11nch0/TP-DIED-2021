import java.util.List;

public class LineaTransporte {

	private Integer id; //id?
	private String nombre;
	private String color; //?
	private Boolean activa; //estado?
	//
	private List<Trayecto> trayectos; //arraylist

	public LineaTransporte(Integer id, String nombre, String color, Boolean activa) {
		
	this.id = id;
	this.nombre = nombre;
	this.color = color;
	this.activa = activa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Boolean getActiva() {
		return activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}
	
	public void agregarTrayecto(Trayecto trayecto) {
		this.trayectos.add(trayecto);
		}
	
	
	
}
