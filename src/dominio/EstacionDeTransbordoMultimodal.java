package dominio;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class EstacionDeTransbordoMultimodal {
    private Integer id;
    private String nombreEstacion;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private EstadoEstacion estado;
    private List<TareaMantenimiento> mantenimientos;

    public enum EstadoEstacion {
        OPERATIVA, MANTENIMIENTO
    }

    public EstacionDeTransbordoMultimodal(Integer id, String nombre, LocalTime hApertura, LocalTime hCierre, EstadoEstacion estado) {
        this.id = id;
        this.nombreEstacion = nombre;
        this.horarioApertura = hApertura;
        this.horarioCierre = hCierre;
        this.estado = estado;

        this.mantenimientos = new ArrayList<>();
    }


    public EstacionDeTransbordoMultimodal() {
        super();
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


    public EstadoEstacion getEstado() {
        return estado;
    }


    public Boolean estadoOperativa() {
        return (estado == EstadoEstacion.OPERATIVA);
    }

    public void setEstado(EstadoEstacion estado) {
        this.estado = estado;
    }

    public void cambiarEstadoEstacion() {
        if (this.estadoOperativa()) {
            TareaMantenimiento mantenimiento = new TareaMantenimiento();
            this.agregarMantenimiento(mantenimiento);
            this.setEstado(EstadoEstacion.MANTENIMIENTO);
        } else {
            this.setEstado(EstadoEstacion.OPERATIVA);
        }
    }

    public List<TareaMantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    public void setMantenimientos(List<TareaMantenimiento> mantenimientos) {
        this.mantenimientos = mantenimientos;
    }

    public void agregarMantenimiento(TareaMantenimiento mantenimiento) {
        this.mantenimientos.add(mantenimiento);
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombreEstacion == null) ? 0 : nombreEstacion.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EstacionDeTransbordoMultimodal other = (EstacionDeTransbordoMultimodal) obj;
        if (estado != other.estado)
            return false;
        if (horarioApertura == null) {
            if (other.horarioApertura != null)
                return false;
        } else if (!horarioApertura.equals(other.horarioApertura))
            return false;
        if (horarioCierre == null) {
            if (other.horarioCierre != null)
                return false;
        } else if (!horarioCierre.equals(other.horarioCierre))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (mantenimientos == null) {
            if (other.mantenimientos != null)
                return false;
        } else if (!mantenimientos.equals(other.mantenimientos))
            return false;
        if (nombreEstacion == null) {
            return other.nombreEstacion == null;
        } else return nombreEstacion.equals(other.nombreEstacion);
    }
}

