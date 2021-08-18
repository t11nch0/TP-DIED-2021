package gestores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import dominio.Camino;
import dominio.EstacionDeTransbordoMultimodal;
import dominio.Ruta;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;

public class GestorCamino {

    private final GestorRuta gestorRuta;
    private final GestorEstacion gestorEstacion;
    Integer listaCaminoSize = 0;

    public GestorCamino() {
        super();
        this.gestorRuta = new GestorRuta();
        GestorTrayecto gestorTrayecto = new GestorTrayecto();
        this.gestorEstacion = new GestorEstacion();
        GestorLineaTransporte gestorLinea = new GestorLineaTransporte();
    }

    public Camino crearCamino(Integer distancia, Integer duracion, Double costo, EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino) throws CamposIncorrectosException, SQLException, BaseDeDatosException {

        Camino c = new Camino();
        this.actualizarModelo(c, distancia, duracion, costo, origen, destino);
        return c;
    }

    public void actualizarModelo(Camino c, Integer distancia, Integer duracion, Double costo, EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino) {
        c.setId(listaCaminoSize + 3);
        c.setDistanciaTotal(distancia);
        c.setDuracionTotal(duracion);
        c.setRutas(c.getRutas());
        c.setCostoTotal(costo);
        c.setOrigen(origen);
        c.setDestino(destino);
        listaCaminoSize++;
    }

    public List<Camino> todosCaminos(EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino) {

        List<Camino> caminosProbables = new ArrayList<>();
        List<List<Ruta>> caminos = buscar(origen, destino);
        for (List<Ruta> c : caminos) {
            Integer distancia = 0;
            Integer duracion = 0;
            Double costo = 0.0;
            Camino camino;

            for (Ruta r : c) {
                distancia += r.getDistanciaKilometros();
                duracion += r.getDuracionViajeMinutos();
                costo += r.getCosto();
            }

            //?
            camino = new Camino(distancia, duracion, costo, origen, destino);
            camino.agregarRutas(c);
            //
            try {
                this.crearCamino(distancia, duracion, costo, origen, destino);
            } catch (CamposIncorrectosException | SQLException | BaseDeDatosException e) {

                e.printStackTrace();
            }

            caminosProbables.add(camino);
        }
        return caminosProbables;
    }

    public List<List<Ruta>> buscar(EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino) {
        List<List<Ruta>> lista = new ArrayList<>();
        List<EstacionDeTransbordoMultimodal> estacionesMarcadas = new ArrayList<>();
        estacionesMarcadas.add(origen);

        buscarAux2(origen, destino, estacionesMarcadas, lista, new ArrayList<>());

        return lista;
    }


    public void buscarAux2(EstacionDeTransbordoMultimodal estacion1, EstacionDeTransbordoMultimodal estacion2,
                           List<EstacionDeTransbordoMultimodal> estacionesMarcadas, List<List<Ruta>> lista, List<Ruta> camino) {
        if (estacion1.getNombreEstacion().equals(estacion2.getNombreEstacion())) {
            lista.add(camino);
        } else {
            List<Ruta> copiaCamino;
            List<Ruta> rutasSalen = gestorRuta.getRutasConOrigen(estacion1);
            List<EstacionDeTransbordoMultimodal> copiaEstacionesMarcadas;

            estacionesMarcadas.add(estacion1);
            for (Ruta r : rutasSalen) {
                if (noContiene(estacionesMarcadas, r.getDestino()) && r.getDestino().estadoOperativa() && r.getTrayecto().getLinea().esActiva()) {
                    copiaEstacionesMarcadas = new ArrayList<>(estacionesMarcadas);
                    copiaCamino = new ArrayList<>(camino);
                    copiaCamino.add(r);
                    buscarAux2(r.getDestino(), estacion2, copiaEstacionesMarcadas, lista, copiaCamino);
                }
            }

        }

    }


    public Boolean noContiene(List<EstacionDeTransbordoMultimodal> lista, EstacionDeTransbordoMultimodal estacion) {

        for (EstacionDeTransbordoMultimodal e : lista) {
            if (e.getNombreEstacion().equals(estacion.getNombreEstacion())) {
                return false;
            }

        }
        return true;
    }

    public Camino caminoMasCorto(EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino) {

        List<Camino> lista = this.todosCaminos(origen, destino);

        Integer minima = lista.get(0).getDistanciaTotal();
        Camino caminoCorto = lista.get(0);
        for (Camino c : lista) {
            if (c.getDistanciaTotal() < minima) {
                minima = c.getDistanciaTotal();
                caminoCorto = c;
            }
        }
        return caminoCorto;
    }

    public Camino caminoMasRapido(EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino) {

        List<Camino> lista = this.todosCaminos(origen, destino);

        Integer minimo = lista.get(0).getDuracionTotal();
        Camino caminoRapido = lista.get(0);
        for (Camino c : lista) {
            if (c.getDuracionTotal() < minimo) {
                minimo = c.getDuracionTotal();
                caminoRapido = c;
            }
        }

        return caminoRapido;
    }

    public Camino caminoMasBarato(EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino) {

        List<Camino> lista = this.todosCaminos(origen, destino);

        Double minimo = lista.get(0).getCostoTotal();
        Camino caminoBarato = lista.get(0);
        for (Camino c : lista) {
            if (c.getCostoTotal() < minimo) {
                minimo = c.getCostoTotal();
                caminoBarato = c;
            }
        }
        return caminoBarato;
    }


    public Integer flujoMaximo(EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino) {
        Integer flujoMax = 0;

        List<Ruta> rutas = gestorRuta.listarTodas();
        HashMap<String, Integer> ramas = new HashMap<>();
        for (Ruta r : rutas) {
            ramas.put(r.getId().toString(), r.getPasajerosMaximos());
        }
        List<List<Ruta>> caminos = new ArrayList<>();
        List<EstacionDeTransbordoMultimodal> lista = new ArrayList<>();
        buscarAux2(origen, destino, lista, caminos, new ArrayList<>());
        for (List<Ruta> c : caminos) {
            if (caminoRamas(c, ramas))
                flujoMax += minimo(c, ramas);
        }

        return flujoMax;
    }

    public Boolean caminoRamas(List<Ruta> camino, HashMap<String, Integer> ramas) {
        for (Ruta r : camino) {
            if (ramas.get(r.getId().toString()) == 0)
                return false;
        }
        return true;
    }

    public Integer minimo(List<Ruta> camino, HashMap<String, Integer> ramas) {

        Integer minimoCapacidad = ramas.get(camino.get(0).getId().toString());
        for (Ruta r : camino) {
            if (minimoCapacidad > ramas.get(r.getId().toString()))
                minimoCapacidad = ramas.get(r.getId().toString());
        }
        for (Ruta r : camino) {
            ramas.put(r.getId().toString(), ramas.get(r.getId().toString()) - minimoCapacidad);
        }
        return minimoCapacidad;
    }


    public List<EstacionDeTransbordoMultimodal> pageRank() {

        HashMap<String, List<EstacionDeTransbordoMultimodal>> estacionesEntrantes = new HashMap<>();
        HashMap<String, Double> pageRanks = new HashMap<>();
        HashMap<String, Integer> gradoSalida = new HashMap<>();
        List<EstacionDeTransbordoMultimodal> estaciones = gestorEstacion.listarTodas();
        double variacion;
        double probabilidad;
        probabilidad = 0.85; // default
        for (EstacionDeTransbordoMultimodal e : estaciones) {
            pageRanks.put(e.getId().toString(), 1.0); //inicializo todos los pR
            gradoSalida.put(e.getId().toString(), gestorRuta.getRutasConOrigen(e).size());
            estacionesEntrantes.put(e.getId().toString(), gestorRuta.getRutasConDestino(e)
            		.stream()
                    .map(Ruta::getOrigen)
                    .collect(Collectors.toList()));
        }
        do {
            variacion = 0.0; //
            HashMap<String, Double> prAux = new HashMap<>();
            for (EstacionDeTransbordoMultimodal e : estaciones) {
                Double nuevoPageRank = 1 - probabilidad;
                for (EstacionDeTransbordoMultimodal estacion : estacionesEntrantes.get(e.getId().toString())) {
                    nuevoPageRank += (pageRanks.get(estacion.getId().toString()) / gradoSalida.get(estacion.getId().toString())) * probabilidad;// (/gradoSalientes)
                }
                double aux = Math.abs(pageRanks.get(e.getId().toString()) - nuevoPageRank); // viejo-nuevo
                if (aux > variacion)
                    variacion = aux;
                prAux.put(e.getId().toString(), nuevoPageRank);

            }
            pageRanks = prAux;
        } while (variacion > 0.01);

        estaciones.sort(new GestorCamino.prEstacionComparador(pageRanks)); //ordenadas
        return estaciones;
    }


    public class prEstacionComparador implements Comparator<EstacionDeTransbordoMultimodal> {

        private HashMap<String, Double> pageRank;

        private prEstacionComparador(HashMap<String, Double> pageRank) {
            this.pageRank = pageRank;
        }

        public int compare(EstacionDeTransbordoMultimodal estacion1, EstacionDeTransbordoMultimodal estacion2) {
            return -pageRank.get(estacion1.getId().toString())
                    .compareTo(pageRank.get(estacion2.getId().toString()));
        }
    }

}


