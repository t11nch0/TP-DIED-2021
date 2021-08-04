package dao;

import java.util.List;
import dominio.TareaMantenimiento;
import java.sql.SQLException;
import excepciones.BaseDeDatosException;

public interface Mantenimiento_DAO {

	public List<TareaMantenimiento> buscarTodos();
	public TareaMantenimiento insertarMantenimiento(TareaMantenimiento mantenimiento) throws BaseDeDatosException, SQLException;
	public TareaMantenimiento finalizarMantenimiento(TareaMantenimiento mantenimiento) throws BaseDeDatosException, SQLException;
	
	public List<TareaMantenimiento> buscarPorIdEstacion(Integer idEstacion); //?
		
}
