package dominio;

import java.util.ArrayList;
import java.util.List;

public class Camino {

    private Integer id;
    private Integer distanciaTotal;
    private Integer duracionTotal;
    private Double costoTotal;
    private List<Ruta> rutas;
    private EstacionDeTransbordoMultimodal origen;
    private EstacionDeTransbordoMultimodal destino;

    public Camino(Integer id, Integer distancia, Integer duracion, Double costo, EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino) {
        this.id = id;
        this.distanciaTotal = distancia;
        this.duracionTotal = duracion;
        this.costoTotal = costo;
        this.rutas = new ArrayList<>();
        this.origen = origen;
        this.destino = destino;
    }

    public Camino(Integer distancia, Integer duracion, Double costo, EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino) {
        this.distanciaTotal = distancia;
        this.duracionTotal = duracion;
        this.costoTotal = costo;
        this.rutas = new ArrayList<>();
        this.origen = origen;
        this.destino = destino;
    }

    public Camino() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(Integer distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public Integer getDuracionTotal() {
        return duracionTotal;
    }

    public void setDuracionTotal(Integer duracionTotal) {
        this.duracionTotal = duracionTotal;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public List<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(List<Ruta> rutas) {
        this.rutas = rutas;
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

    public void agregarRuta(Ruta ruta) {
        this.rutas.add(ruta);
    }

    public void agregarRutas(List<Ruta> lista) {
        this.rutas.addAll(lista);
    }

}
