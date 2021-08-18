package gestores;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dao.Mantenimiento_DAO;
import dao.Mantenimiento_DAO_PostgreSQL;
import dominio.EstacionDeTransbordoMultimodal;
import dominio.TareaMantenimiento;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;

public class GestorMantenimiento {

    private Mantenimiento_DAO mantenimientoDAO;

    public GestorMantenimiento() {
        super();
        this.mantenimientoDAO = new Mantenimiento_DAO_PostgreSQL();
    }


    public TareaMantenimiento crearTareaMantenimiento(String observaciones, EstacionDeTransbordoMultimodal estacion) throws CamposIncorrectosException, SQLException, BaseDeDatosException {
        TareaMantenimiento m = new TareaMantenimiento();
        LocalDate fechaInicio = LocalDate.now();
        this.actualizarModelo(m, fechaInicio, null, observaciones, estacion.getId());
        return mantenimientoDAO.insertarMantenimiento(m);
    }

    public TareaMantenimiento finalizarTareaMantenimiento(String observaciones, EstacionDeTransbordoMultimodal estacion) throws BaseDeDatosException, SQLException {
        TareaMantenimiento m = this.buscarPorIdEstacion(estacion.getId()).get(estacion.getMantenimientos().size() - 1);
        LocalDate fechaFin = LocalDate.now();
        this.actualizarModelo2(m, m.getFechaInicio(), fechaFin, observaciones, estacion.getId());
        return mantenimientoDAO.finalizarMantenimiento(m);
    }

    public void actualizarModelo(TareaMantenimiento m, LocalDate inicio, LocalDate fin, String observaciones, Integer idEstacion) {
        m.setFechaInicio(inicio);
        m.setFechaFin(fin);
        m.setObservaciones(observaciones);
        m.setIdEstacion(idEstacion);
    }

    public void actualizarModelo2(TareaMantenimiento m, LocalDate inicio, LocalDate fin, String observaciones, Integer idEstacion) {
        m.setFechaInicio(inicio);
        m.setFechaFin(fin);
        m.setObservaciones(m.getObservaciones() + " // "+observaciones);
        m.setIdEstacion(idEstacion);
    }

    public List<TareaMantenimiento> listarTodos() {
        return mantenimientoDAO.buscarTodos();
    }

    public List<TareaMantenimiento> buscarPorIdEstacion(Integer idEstacion) {
        return mantenimientoDAO.buscarPorIdEstacion(idEstacion);
    }

}
