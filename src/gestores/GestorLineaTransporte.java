package gestores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.LineaTransporte_DAO;
import dao.LineaTransporte_DAO_PostgreSQL;
import dominio.LineaTransporte;
import dominio.LineaTransporte.EstadoLinea;
import dominio.Ruta;
import dominio.Trayecto;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;

public class GestorLineaTransporte {

    private final LineaTransporte_DAO lineaDAO;
    private List<LineaTransporte> lineas;
    private GestorTrayecto gestorTrayecto;

    public GestorLineaTransporte() {
        super();
        this.lineaDAO = new LineaTransporte_DAO_PostgreSQL();
        lineas = new ArrayList<>(this.listarTodas());
    }

    public LineaTransporte crearLinea(String nombre, String color, EstadoLinea estado) throws CamposIncorrectosException, SQLException, BaseDeDatosException {
        this.validarDatos(nombre);
        LineaTransporte l = new LineaTransporte();
        this.actualizarModelo(l, nombre, color, estado);
        lineaDAO.insertarLineaTransporte(l);
        lineas.add(l);
        return l;
    }

    public void agregarLinea(String nombre, String color, EstadoLinea estado, List<Ruta> listaTrayecto) throws CamposIncorrectosException, SQLException, BaseDeDatosException {
        LineaTransporte lineaAux;
        lineaAux = this.crearLinea(nombre, color, estado);
        gestorTrayecto = new GestorTrayecto();
        Trayecto tray = gestorTrayecto.crearTrayecto(lineaAux.getId(), listaTrayecto);
        tray.relacionarLinea(lineaAux);
        lineaAux.agregarTrayecto(tray);

    }

    public void validarDatos(String nombre) throws CamposIncorrectosException {
        StringBuilder mensajeAMostrar = new StringBuilder();
        List<String> lista_de_campos_erroneos = new ArrayList<>();
        if (nombre.isEmpty()) {
            lista_de_campos_erroneos.add("Nombre");
            mensajeAMostrar.append("\n" + "- Nombre de la Linea de Transporte. (Campo incompleto-Dato Obligatorio)" + "\n");
        } else {
            if (this.existeNombreDeLinea(nombre)) {
                lista_de_campos_erroneos.add("Nombre");
                mensajeAMostrar.append("\n" + "- El nombre de la linea de transporte ya existe." + "\n");
            }
        }
        if (mensajeAMostrar.length() != 0) {
            throw new CamposIncorrectosException(mensajeAMostrar.toString(), lista_de_campos_erroneos);
        }
    }

    public void actualizarModelo(LineaTransporte l, String nombre, String color, EstadoLinea estado) {
        l.setNombre(nombre);
        l.setColor(color);
        l.setEstado(estado);
    }

    public boolean existeNombreDeLinea(String nombre) {
        return lineaDAO.existeNombreDeLinea(nombre);
    }

    public LineaTransporte editarLinea(LineaTransporte l, String nombreLinea, String color, EstadoLinea estado) throws CamposIncorrectosException, SQLException, BaseDeDatosException {

        this.actualizarModelo(l, nombreLinea, color, estado);
        return lineaDAO.editarLineaTransporte(l);
    }

    public void eliminarLinea(LineaTransporte l) throws CamposIncorrectosException, SQLException, BaseDeDatosException {
        lineaDAO.eliminarLineaTransporte(l);
    }

    public List<LineaTransporte> listarTodas() {
        return lineaDAO.buscarTodas();
    }

    public LineaTransporte buscarPorId(Integer id) {
        return lineaDAO.buscarPorId(id);
    }

    public List<LineaTransporte> getTodasLineas() {
        return lineas;
    }

    public List<LineaTransporte> filtrar(String[] param) {

        return lineaDAO.filtrar(param);
    }

}

