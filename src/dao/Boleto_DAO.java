package dao;

import java.sql.SQLException;
import java.util.List;


import dominio.Boleto;
import excepciones.BaseDeDatosException;

public interface Boleto_DAO {

	List<Boleto> buscarTodos();
	Boleto insertarBoleto(Boleto boleto) throws BaseDeDatosException, SQLException;
	void eliminarBoleto();
	//?
	Boleto editarBoleto(Boleto boleto) throws BaseDeDatosException, SQLException;
	Boleto buscarPorId(Integer id); //?

	
}
