package dao;

import java.sql.SQLException;
import java.util.List;


import dominio.Boleto;
import excepciones.BaseDeDatosException;

public interface Boleto_DAO {

	public List<Boleto> buscarTodos();	
	public Boleto insertarBoleto(Boleto boleto) throws BaseDeDatosException, SQLException;
	public void eliminarBoleto();
	//?
	public Boleto editarBoleto(Boleto boleto) throws BaseDeDatosException, SQLException;
	public Boleto buscarPorId(Integer id); //?

	
}
