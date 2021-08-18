package gestores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dao.Trayecto_DAO;
import dao.Trayecto_DAO_PostgreSQL;
import dominio.LineaTransporte;
import dominio.Ruta;
import dominio.Trayecto;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;

public class GestorTrayecto {


    private Trayecto_DAO TrayectoDAO;
    private GestorLineaTransporte gestorLinea;
    private GestorRuta gestorRuta;
    List<Trayecto> trayectos;

    public GestorTrayecto() {
        super();
        this.TrayectoDAO = new Trayecto_DAO_PostgreSQL();
        trayectos = new ArrayList<>(this.listarTodos());
        this.relacionarConLineas();

    }

    public Trayecto crearTrayecto(Integer idLinea, List<Ruta> listaTramos) throws CamposIncorrectosException, SQLException, BaseDeDatosException {
        //Lista de Rutas??? (tramos)
        Trayecto t = new Trayecto();
        t.setIdLinea(idLinea);
        t.setTramos(listaTramos);
        TrayectoDAO.insertarTrayecto(t);
        trayectos.add(t);  //?
        //
        gestorRuta = new GestorRuta();
        List<Ruta> listaRuta = gestorRuta.agregarRutas(t.getId(), listaTramos, t); //t.getId();
        t.relacionarTramos(listaRuta);
        t.relacionarLinea(gestorLinea.buscarPorId(idLinea)); //buscarConId?

        return t;
    }

    public List<Trayecto> listarTodos() {
        return TrayectoDAO.buscarTodos();
    }

    public List<Trayecto> getTodosTrayectos() {
        return trayectos;
    }


    public Trayecto buscarPorId(Integer id) {
        //	this.relacionarConLineas(); //?
        return TrayectoDAO.buscarPorId(id);
    }

    //
    public void relacionarConLineas() {
        gestorLinea = new GestorLineaTransporte();
        for (Trayecto t : trayectos) {
            LineaTransporte lin = (gestorLinea.getTodasLineas().stream().filter(l -> Objects.equals(l.getId(), t.getIdLinea())).findFirst()).get(); ///????
            t.relacionarLinea(lin);
        }
    }
    //

    //Temporalmente
/*	public void relacionarTrayectoConLineas(List<Trayecto> listaTrayectos) {
		gestorLinea = new GestorLineaTransporte();
		for(Trayecto t: listaTrayectos) { 
			LineaTransporte lin = gestorLinea.listarTodas().stream().filter(l -> l.getId() == t.getIdLinea()).findFirst().get(); ///????
			t.relacionarLinea(lin); 
		}
	}*/
}

