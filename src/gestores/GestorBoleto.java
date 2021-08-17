package gestores;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dao.Boleto_DAO;
import dao.Boleto_DAO_PostgreSQL;
import dominio.Boleto;
import dominio.Camino;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;

public class GestorBoleto {

	private Boleto_DAO boletoDAO;
	public GestorBoleto() 
	{
		super();
		this.boletoDAO = new Boleto_DAO_PostgreSQL();
	}
	public Boleto crearBoleto(Integer nroBoleto, String email, String nombre, LocalDate fecha, Camino camino) throws CamposIncorrectosException, SQLException, BaseDeDatosException
	{ 
		Boleto b = new Boleto();
		this.actualizarModelo(b, nroBoleto, email, nombre, fecha, camino);
		return boletoDAO.insertarBoleto(b);
	}

	public void actualizarModelo(Boleto b, Integer nroBoleto, String email, String nombre, LocalDate fecha, Camino camino) 
	{
		b.setNroBoleto(nroBoleto);
		b.setEmailCliente(email);
		b.setNombreCliente(nombre);
		b.setFechaVenta(fecha);
		b.setOrigen(camino.getOrigen());
		b.setDestino(camino.getDestino());
		b.setCamino(camino);
		b.setCosto(camino.getCostoTotal()); //?
	}
	
	public List<Boleto> listarTodos() 
	{
		return boletoDAO.buscarTodos();
	}

	public Boleto buscarPorId(Integer id) 
	{
		return boletoDAO.buscarPorId(id);
	}
	
}
