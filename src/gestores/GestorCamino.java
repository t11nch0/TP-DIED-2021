package gestores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import dominio.Camino;
import dominio.EstacionDeTransbordoMultimodal;
import dominio.EstacionDeTransbordoMultimodal.EstadoEstacion;
import dominio.Ruta;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;

public class GestorCamino {
 	
	private GestorRuta gestorRuta;
	private GestorEstacion gestorEstacion;
	public GestorCamino() 
	{
		super(); 
		this.gestorRuta = new GestorRuta();
		this.gestorEstacion = new GestorEstacion();
	}
	
	public Camino crearCamino(Integer distancia, Integer duracion, Double costo, EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino) throws CamposIncorrectosException, SQLException, BaseDeDatosException
	{ 
	
		Camino c = new Camino();
		this.actualizarModelo(c, distancia, duracion, costo, origen, destino); //?
		return c; 
	}
	
	public void actualizarModelo(Camino c, Integer distancia, Integer duracion, Double costo, EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino)
	{
		c.setDistanciaTotal(distancia);
		c.setDuracionTotal(duracion);
		c.setCostoTotal(costo); 
		c.setOrigen(origen);
		c.setDestino(destino);
	}
	
	//

	public List<Camino> todosCaminos(EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino){
	
		List<Camino> caminosProbables = new ArrayList<>(); 
		List<List<Ruta>> caminos = this.buscar(origen, destino);

		for(List<Ruta> c: caminos) {  
			Integer distancia = 0;
			Integer duracion = 0;
			Double costo = 0.0;
			Camino camino; 
			
			for(Ruta r: c) { 
				distancia += r.getDistanciaKilometros();
				duracion += r.getDuracionViajeMinutos();
				costo += r.getCosto();
			}
			
			camino = new Camino(distancia, duracion, costo, origen, destino);
			camino.agregarRutas(c);
			caminosProbables.add(camino);
		}
		return caminosProbables;		
	}
	
	public List<List<Ruta>> buscar(EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino){
		List<List<Ruta>> lista = new ArrayList<>();
		List<EstacionDeTransbordoMultimodal> estacionesMarcadas = new ArrayList<>();
		estacionesMarcadas.add(origen);
		List<Ruta> camino = new ArrayList<>();
		buscarAux(origen, destino, estacionesMarcadas, lista, camino);
		return lista;
	}
	
	public void buscarAux(EstacionDeTransbordoMultimodal estacion1, EstacionDeTransbordoMultimodal estacion2, 
			List<EstacionDeTransbordoMultimodal> estacionesMarcadas, List<List<Ruta>> lista, List<Ruta> camino) {
		if(estacion1.equals(estacion2))
			lista.add(camino);
		else {
			List<Ruta> copiaCamino = null;
			List<Ruta> rutasSalen = gestorRuta.getRutasConOrigen(estacion1); 
			List<EstacionDeTransbordoMultimodal> copiaestacionesMarcadas = null;
			estacionesMarcadas.add(estacion1);
			
			for(Ruta r: rutasSalen) { 
				// 
				if(!estacionesMarcadas.contains(r.getDestino()) && r.getDestino().getEstado().equals(EstadoEstacion.OPERATIVA)) { 
					copiaCamino = camino.stream().collect(Collectors.toList());
					copiaCamino.add(r);
					copiaestacionesMarcadas = estacionesMarcadas.stream().collect(Collectors.toList());
					buscarAux(r.getDestino(), estacion2, copiaestacionesMarcadas, lista, copiaCamino);
				}
			}
		}
	}

	//
	public Camino caminoMasCorto(EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino) {
		
		List<Camino> lista = this.todosCaminos(origen, destino);
		
		Integer minima = lista.get(0).getDistanciaTotal(); // distancia total del primer camino
		Camino caminoCorto = lista.get(0);
		for(Camino c: lista) { 
			if(c.getDistanciaTotal() < minima) {
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
		for(Camino c: lista) { 
			if(c.getDuracionTotal() < minimo) {
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
		for(Camino c: lista) { 
			if(c.getCostoTotal() < minimo) { 
				minimo = c.getCostoTotal();
				caminoBarato = c;
			}
		}
		return caminoBarato;
	}
	
	//
	public Integer flujoMaximo(EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino) {
		Integer flujoMax = 0;
		
		List<Ruta> rutas = gestorRuta.listarTodas();
		//String de ID de ruta
		HashMap <String, Integer> ramas = new HashMap<String, Integer>();
		for(Ruta r: rutas) {
			ramas.put(r.getId().toString(), r.getPasajerosMaximos());
		}
		List<List<Ruta>> caminos = new ArrayList<List<Ruta>>();
		List<EstacionDeTransbordoMultimodal> lista = new ArrayList<EstacionDeTransbordoMultimodal>();
		buscarAux(origen, destino, lista, caminos, new ArrayList<Ruta>());
		for(List<Ruta> c: caminos) {
			if(caminoRamas(c, ramas))
					flujoMax += minimo(c, ramas);
		}
		
		return flujoMax;	
	}

	public Boolean caminoRamas(List<Ruta> camino, HashMap<String, Integer> ramas) { 
		for(Ruta r: camino) {
			if(ramas.get(r.getId().toString()) == 0)
				return false;
		}
		return true;
	}
	
	public Integer minimo(List<Ruta> camino, HashMap<String, Integer> ramas) {
		
		Integer minimoCapacidad = ramas.get(camino.get(0).getId().toString()); //inicializo con el primero
		for(Ruta r: camino) {
			if(minimoCapacidad > ramas.get(r.getId().toString()))
				minimoCapacidad = ramas.get(r.getId().toString());
		}
		for(Ruta r: camino) {
			ramas.put(r.getId().toString(), ramas.get(r.getId().toString()) - minimoCapacidad);
		}
		return minimoCapacidad;
	}
	
	
	public List<EstacionDeTransbordoMultimodal> pageRank(){
		
		HashMap<String, List<EstacionDeTransbordoMultimodal>> estacionesEntrantes = new HashMap<String, List<EstacionDeTransbordoMultimodal>>();
		HashMap<String, Double> pageRanks = new HashMap<String, Double>();
		HashMap<String, Integer> gradoSalida = new HashMap<String, Integer>();
		List<EstacionDeTransbordoMultimodal> estaciones = gestorEstacion.listarTodas();
		Double variacion;
		Double probabilidad;
		for(EstacionDeTransbordoMultimodal e: estaciones) {
			pageRanks.put(e.getId().toString(), 1.0); //inicializo todos los pR
			gradoSalida.put(e.getId().toString(), gestorRuta.getRutasConOrigen(e).size()); 
			estacionesEntrantes.put(e.getId().toString(), gestorRuta.getRutasConDestino(e).stream()
									.map((Ruta r) -> r.getOrigen()).collect(Collectors.toList()));
		}
		probabilidad = 0.85; // default
		do {
			variacion = 0.0; //
			HashMap<String, Double> prAux = new HashMap<String, Double>();
			for(EstacionDeTransbordoMultimodal e: estaciones) {
				Double nuevoPageRank = 1-probabilidad;
				for(EstacionDeTransbordoMultimodal estacion: estacionesEntrantes.get(e.getId().toString())){
					nuevoPageRank += (pageRanks.get(estacion.getId().toString())/gradoSalida.get(estacion.getId().toString()))*probabilidad;// (/gradoSalientes)
				}
				Double auxIteracion = Math.abs(pageRanks.get(e.getId().toString()) - nuevoPageRank); // viejo-nuevo
				if(auxIteracion > variacion)
					variacion = auxIteracion;
				prAux.put(e.getId().toString(), nuevoPageRank);
			
			}
			pageRanks = prAux;
		} while(variacion > 0.01); 
		
		estaciones.sort(new GestorCamino.prEstacionComparador(pageRanks)); //ordenadas 		
		return estaciones;
	}
	

	public class prEstacionComparador implements Comparator<EstacionDeTransbordoMultimodal>{
		
		private HashMap<String, Double> pageRank;
		
		private prEstacionComparador(HashMap<String, Double> pageRank) {
			this.pageRank = pageRank;
		}
	
		public int compare(EstacionDeTransbordoMultimodal estacion1, EstacionDeTransbordoMultimodal estacion2)
		{
			return -pageRank.get(estacion1.getId().toString())
					.compareTo(pageRank.get(estacion2.getId().toString()));
		}
	}
	
}


