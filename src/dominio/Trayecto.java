package dominio;

import java.util.List;


public class Trayecto {

    private Integer id;
    private LineaTransporte linea;
    private Integer idLinea;
    private List<Ruta> tramos;

    public Trayecto(Integer id, Integer idLinea, List<Ruta> tramos) {
        this.id = id;
        this.idLinea = idLinea;
        this.linea = null; //?
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

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
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

    public void relacionarLinea(LineaTransporte l) {
        this.linea = l;
    }

    public void relacionarTramos(List<Ruta> lista) {
        this.tramos = lista;
    } //Se usa?

}	
	
	
