package gestores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

import dao.Ruta_DAO;
import dao.Ruta_DAO_PostgreSQL;
import dominio.EstacionDeTransbordoMultimodal;
import dominio.Ruta;
import dominio.Ruta.EstadoRuta;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;

public class GestorRuta {
	
	private Ruta_DAO rutaDAO;
	public GestorRuta() 
	{
		super();
		this.rutaDAO = new Ruta_DAO_PostgreSQL();
	}
	public Ruta crearRuta(EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino) throws CamposIncorrectosException, SQLException, BaseDeDatosException
	{
		Ruta r = new Ruta();
		this.actualizarModelo(r, origen, destino);
		return rutaDAO.insertarRuta(r);
	} //?
	
	
	public void actualizarModelo(Ruta r, EstacionDeTransbordoMultimodal origen, EstacionDeTransbordoMultimodal destino)
	{
		r.setOrigen(origen); //?
		r.setDestino(destino); // 
		r.setEstadoRuta(EstadoRuta.ACTIVA); //? 4
		//??
	}
	
	public List<Ruta> listarTodas() 
	{
		return rutaDAO.buscarTodas();
	}
	
	public Ruta buscarPorId(Integer id) 
	{
		return rutaDAO.buscarPorId(id);
	}
	//
	public List<Ruta> getRutasConOrigen(EstacionDeTransbordoMultimodal e){
		List<Ruta> listaRutas = new ArrayList<>();
		//?
		List<Ruta> rutas = this.listarTodas(); //?
		//
		for(Ruta r: rutas) {
			if(r.getOrigen().equals(e) && r.getEstadoRuta().equals(EstadoRuta.ACTIVA)) //? //?
				listaRutas.add(r);
		}
		return listaRutas;
	}
	public List<Ruta> getRutasConDestino(EstacionDeTransbordoMultimodal e){
		List<Ruta> listaRutas = new ArrayList<>();
		List<Ruta> rutas = this.listarTodas();
				
		for(Ruta r: rutas) {
			if(r.getDestino().equals(e) && r.getEstadoRuta().equals(EstadoRuta.ACTIVA)) //equals de estado?
				listaRutas.add(r);
		}
		return listaRutas;
	}
	//
	
	public List<Ruta> buscarPorIdTrayecto(Integer idTrayecto){
		return rutaDAO.buscarPorIdTrayecto(idTrayecto);
		} //??
		
}
