package gestores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dao.Ruta_DAO;
import dao.Ruta_DAO_PostgreSQL;
import dominio.EstacionDeTransbordoMultimodal;
import dominio.Ruta;
import dominio.Trayecto;
import dominio.Ruta.EstadoRuta;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;

public class GestorRuta {

    private Ruta_DAO rutaDAO;
    List<Ruta> rutas;
    private GestorTrayecto gestorTrayecto;
    private GestorEstacion gestorEstacion;

    public GestorRuta() {
        super();
        this.rutaDAO = new Ruta_DAO_PostgreSQL();
        rutas = new ArrayList<>(this.listarTodas());
        this.relacionarConTrayectos(); //AAA
        //	gestorTrayecto.relacionarConLineas(); //???
    }

    public Ruta crearRuta(EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino, Integer distancia, Integer duracion, Integer pasajeros, EstadoRuta estado, Double costo, Integer idTrayecto, Trayecto trayecto) throws SQLException, BaseDeDatosException {
        Ruta r = new Ruta();
        this.actualizarModelo(r, origen, destino, distancia, duracion, pasajeros, estado, costo, idTrayecto);
        rutaDAO.insertarRuta(r);
        rutas.add(r);
        r.relacionarTrayecto(trayecto);
        return r;
    }


    public void actualizarModelo(Ruta r, EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino, Integer distancia, Integer duracion, Integer pasajeros, EstadoRuta estado, Double costo, Integer idTrayecto) {
        r.setOrigen(origen);
        r.setDestino(destino);
        r.setDistanciaKilometros(distancia);
        r.setDuracionViajeMinutos(duracion);
        r.setPasajerosMaximos(pasajeros);
        r.setEstadoRuta(estado);
        r.setCosto(costo);
        r.setIdTrayecto(idTrayecto);
    }

    public void relacionarConTrayectos() {
        gestorTrayecto = new GestorTrayecto();
        for (Ruta r : rutas) {

            Trayecto tray = (gestorTrayecto.getTodosTrayectos().stream().filter(t -> Objects.equals(t.getId(), r.getIdTrayecto())).findFirst()).get(); ///????
            r.relacionarTrayecto(tray);
        }
    }

    //
    public List<Ruta> listarTodas() {
        return rutaDAO.buscarTodas();
    }

    public List<Ruta> getTodasRutas() {
        return rutas;
    }

    public Ruta buscarPorId(Integer id) {
        //	this.relacionarConTrayectos();
        //	gestorTrayecto.relacionarConLineas(); //?aaa
        return rutaDAO.buscarPorId(id);
    }


    //
    public List<Ruta> getRutasConOrigen(EstacionDeTransbordoMultimodal e) {
        List<Ruta> listaRutas = new ArrayList<>();
        for (Ruta r : rutas) {
            if ((r.getOrigen().getNombreEstacion().equals(e.getNombreEstacion())) && r.esActiva()) {
                listaRutas.add(r);
            }
        }
        return listaRutas;
    }

    public List<Ruta> getRutasConDestino(EstacionDeTransbordoMultimodal e) {
        List<Ruta> listaRutas = new ArrayList<>();
        for (Ruta r : rutas) {
            if (r.getDestino().equals(e) && r.esActiva())
                listaRutas.add(r);
        }

        return listaRutas;
    }
    //

    public List<Ruta> buscarPorIdTrayecto(Integer idTrayecto) {
        return rutaDAO.buscarPorIdTrayecto(idTrayecto);
    }

    //
    public List<Ruta> agregarRutas(Integer idTrayecto, List<Ruta> listaTramos, Trayecto trayecto) throws SQLException, BaseDeDatosException {
        List<Ruta> listaSalida = new ArrayList<>();
        gestorEstacion = new GestorEstacion();
        for (Ruta r : listaTramos) {
            Ruta rutaAux; //?
            rutaAux = crearRuta(gestorEstacion.buscarPorId(r.getOrigen().getId()),
                    gestorEstacion.buscarPorId(r.getDestino().getId()),
                    r.getDistanciaKilometros(),
                    r.getDuracionViajeMinutos(),
                    r.getPasajerosMaximos(),
                    r.getEstadoRuta(), //?Se va a pasar un tipo EstadoEstacion?
                    r.getCosto(),
                    idTrayecto,
                    trayecto
            );
            listaSalida.add(rutaAux);

        }
        return listaSalida;
    }
    //
	
/*	public void imprimir() {
		for(Ruta r: rutas) {
			if(r.getId() == 3)
				break;
			System.out.println("getId: "+r.getId());
			System.out.println("getOrigen: "+r.getOrigen().getNombreEstacion());
			System.out.println("getDestino: "+r.getDestino().getNombreEstacion());
			System.out.println("getDistanciaKilometros: "+r.getDistanciaKilometros());
			System.out.println("getDuracionViajeMinutos: "+r.getDuracionViajeMinutos());
			System.out.println("getPasajerosMaximos: "+r.getPasajerosMaximos());
			System.out.println("getEstadoRuta: "+r.getEstadoRuta());
			System.out.println("getCosto: "+r.getCosto());
			System.out.println("getIdTrayecto: "+r.getIdTrayecto());
			System.out.println("getTrayecto: "+r.getTrayecto().getId());
		}		
	}*/

}
