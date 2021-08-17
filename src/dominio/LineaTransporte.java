package dominio;

import java.util.ArrayList;
import java.util.List;


public class LineaTransporte {

    private Integer id;
    private String nombre;
    private String color;
    private EstadoLinea estado;
    private List<Trayecto> trayectos;

    public enum EstadoLinea {
        ACTIVA, INACTIVA
    }

    public LineaTransporte(Integer id, String nombre, String color, EstadoLinea estado) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.estado = estado;
        //this.trayectos = trayectos;
        this.trayectos = new ArrayList<>();
    }

    public LineaTransporte() {
        super();
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

    public EstadoLinea getEstado() {
        return estado;
    }

    public void setEstado(EstadoLinea estado) {
        this.estado = estado;
    }

    public List<Trayecto> getTrayectos() {
        return trayectos;
    }

    public void setTrayectos(List<Trayecto> trayectos) {
        this.trayectos = trayectos;
    }

    public void agregarTrayecto(Trayecto trayecto) {
        this.trayectos.add(trayecto);
    }

    public void relacionarTrayectos(List<Trayecto> listaT) {
        this.trayectos = listaT;
    } //?

    public boolean esActiva() {
        return (estado == EstadoLinea.ACTIVA);
    }

}
