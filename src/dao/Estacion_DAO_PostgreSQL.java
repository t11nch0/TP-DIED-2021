package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import dominio.EstacionDeTransbordoMultimodal;
import dominio.EstacionDeTransbordoMultimodal.EstadoEstacion;
import excepciones.BaseDeDatosException;
import gestores.GestorConexion;
import gestores.GestorMantenimiento;

public class Estacion_DAO_PostgreSQL implements Estacion_DAO{

	private final Connection conn = GestorConexion.getConnection();
	GestorMantenimiento gestorMantenimiento = new GestorMantenimiento();
	
	private static final String SELECT_ALL_ESTACION =
	"SELECT * FROM died_db.estacion"; 
	
	private static final String INSERT_ESTACION =
			"INSERT INTO died_db.estacion (NOMBRE, HORARIO_APERTURA, HORARIO_CIERRE, ESTADO) VALUES (?,?,?,?) RETURNING ID";
	
	private static final String UPDATE_ESTACION =
			"UPDATE died_db.estacion SET NOMBRE = ?, HORARIO_APERTURA = ?, HORARIO_CIERRE = ?, ESTADO = ? "+
			"WHERE ID = ?"; //?
	
	private static final String EXISTE_NOMBRE_ESTACION =
			"SELECT "+
		     "CASE WHEN EXISTS "+
		     "( "+
		     "SELECT NOMBRE FROM died_db.estacion WHERE nombre = ? "+
		     ") "+
		     "THEN TRUE "+
		     "ELSE FALSE "+
		     "END";
	
	
	@Override	
	public EstacionDeTransbordoMultimodal insertarEstacion(EstacionDeTransbordoMultimodal estacion) 
			throws BaseDeDatosException, SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			conn.setAutoCommit(false); 
			pstmt= conn.prepareStatement(INSERT_ESTACION);
			pstmt.setString(1, estacion.getNombreEstacion());

			if(estacion.getHorarioApertura() != null)
			{
				Time horario_apertura = java.sql.Time.valueOf(estacion.getHorarioApertura());
				pstmt.setTime(2, horario_apertura);
			}
			else
			{
				pstmt.setTime(2, null);
			}              
			if(estacion.getHorarioCierre() != null)
			{
				Time horario_cierre = java.sql.Time.valueOf(estacion.getHorarioCierre());
				pstmt.setTime(3, horario_cierre);
			}
			else
			{
				pstmt.setTime(3, null);
			}              //???
			pstmt.setString(4, estacion.getEstado().toString());

			rs = pstmt.executeQuery(); 
			while(rs.next()) 
			{
				estacion.setId(rs.getInt("ID"));
			} 
			conn.commit(); 
			
		}
		catch (SQLException e) 
		{
			conn.rollback();
			e.printStackTrace();
			throw new BaseDeDatosException(e.getMessage());
		}
		finally 
		{
			try 
			{
				if(pstmt!=null) pstmt.close();				
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return estacion; 
	}

	@Override
	public EstacionDeTransbordoMultimodal editarEstacion(EstacionDeTransbordoMultimodal estacion) throws BaseDeDatosException, SQLException {

		PreparedStatement pstmt = null;
			try 
			{
				conn.setAutoCommit(false);  //?
				pstmt= conn.prepareStatement(UPDATE_ESTACION);
			
				pstmt.setString(1, estacion.getNombreEstacion()); //etc
				
				if(estacion.getHorarioApertura() != null)
				{
					Time horario_apertura = java.sql.Time.valueOf(estacion.getHorarioApertura());
					pstmt.setTime(2, horario_apertura);
				}
				else
				{
					pstmt.setTime(2, null);
				}
				if(estacion.getHorarioCierre() != null)
				{
					Time horario_cierre = java.sql.Time.valueOf(estacion.getHorarioCierre());
					pstmt.setTime(3, horario_cierre);
				}
				else
				{
					pstmt.setTime(3, null);
				}
				pstmt.setString(4, estacion.getEstado().toString());				
				pstmt.setInt(5, estacion.getId()); //
				pstmt.executeUpdate();
				conn.commit(); 
			}
			catch (SQLException e) 
			{
				conn.rollback();
				e.printStackTrace();
				throw new BaseDeDatosException(e.getMessage());
			}
			finally 
			{
				try 
				{
					if(pstmt!=null) pstmt.close();				
				}
				catch(SQLException e) 
				{
					e.printStackTrace();
				}
			}
			return estacion; 
		}
	
	
	@Override
	public List<EstacionDeTransbordoMultimodal> buscarTodas() 
	{
		List<EstacionDeTransbordoMultimodal> lista = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try 
		{
			pstmt= conn.prepareStatement(SELECT_ALL_ESTACION);
			rs = pstmt.executeQuery();
			while(rs.next()) 
			{
				EstacionDeTransbordoMultimodal estacion = new EstacionDeTransbordoMultimodal(); //null?
				estacion.setId((rs.getInt("ID")));
				estacion.setNombreEstacion(rs.getString("NOMBRE"));
				switch(rs.getString("ESTADO"))
				{
				case "OPERATIVA":
					estacion.setEstado(EstadoEstacion.OPERATIVA);
					break;
				case "MANTENIMIENTO":
					estacion.setEstado(EstadoEstacion.MANTENIMIENTO);
					break;
				}
				if(rs.getTime("HORARIO_APERTURA") != null)
				{
					estacion.setHorarioApertura(rs.getTime("HORARIO_APERTURA").toLocalTime());
				}
				if(rs.getTime("HORARIO_CIERRE") != null)
				{
					estacion.setHorarioCierre(rs.getTime("HORARIO_CIERRE").toLocalTime());
				}

				estacion.setMantenimientos(gestorMantenimiento.buscarPorIdEstacion(rs.getInt("ID")));
				
				lista.add(estacion);
			}			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				//if(conn!=null) conn.close();				
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
		}	
		return lista;
	}
	

	@Override
	public void eliminarEstacion(EstacionDeTransbordoMultimodal estacion) throws BaseDeDatosException, SQLException
	{
		PreparedStatement pstmt = null;

			// REVISAR
			try 
			{
				
				conn.setAutoCommit(false);  //?				
				pstmt= conn.prepareStatement("DELETE FROM died_db.estacion WHERE ID = ?");
				
				pstmt.setInt(1, estacion.getId()); 
				
				pstmt.executeUpdate();
				conn.commit(); //??
				
			}
			catch (SQLException e) 
			{
				conn.rollback(); //??
				e.printStackTrace();
				throw new BaseDeDatosException(e.getMessage());
			}
			finally 
			{
				try 
				{
					if(pstmt!=null) pstmt.close();				
				}
				catch(SQLException e) 
				{
					e.printStackTrace();
				}
			}
	
	}

	@Override
	public boolean existeNombreDeEstacion(String nombre)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean resultado = false;
		try 
		{
			pstmt = conn.prepareStatement(EXISTE_NOMBRE_ESTACION);
			pstmt.setString(1, nombre);
			rs = pstmt.executeQuery();
			while(rs.next()) 
			{
				resultado = rs.getBoolean("CASE"); //?
			}			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();				
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
		}	
		return resultado;
	}
	
	@Override
	public EstacionDeTransbordoMultimodal buscarPorId(Integer id) {
		{
			EstacionDeTransbordoMultimodal estacion = new EstacionDeTransbordoMultimodal();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try 
			{
				//?
				pstmt= conn.prepareStatement("SELECT * FROM died_db.estacion WHERE ID = "+ id); // resolver arriba?
				rs = pstmt.executeQuery();

				while(rs.next()) 
				{
					estacion.setId(rs.getInt("ID"));
					estacion.setNombreEstacion(rs.getString("NOMBRE"));
					switch(rs.getString("ESTADO"))
					{
					case "OPERATIVA":
						estacion.setEstado(EstadoEstacion.OPERATIVA);
						break;
					case "MANTENIMIENTO":
						estacion.setEstado(EstadoEstacion.MANTENIMIENTO);
						break;
					}
					if(rs.getTime("HORARIO_APERTURA") != null)
					{
						estacion.setHorarioApertura(rs.getTime("HORARIO_APERTURA").toLocalTime());
					}
					if(rs.getTime("HORARIO_CIERRE") != null)
					{
						estacion.setHorarioCierre(rs.getTime("HORARIO_CIERRE").toLocalTime());
					}
					
					estacion.setMantenimientos(gestorMantenimiento.buscarPorIdEstacion(id));
					
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			finally 
			{
				try 
				{
					if(rs!=null) rs.close();
					if(pstmt!=null) pstmt.close();				
				}
				catch(SQLException e) 
				{
					e.printStackTrace();
				}
			}	
			return estacion;
		}
	}

	@Override
	public List<EstacionDeTransbordoMultimodal> filtrar(String[] param) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EstacionDeTransbordoMultimodal> resultado = new ArrayList<>();
		String consulta = "SELECT * FROM died_db.estacion e WHERE 1=1";
		int contador = 1;

		if(!param[0].isEmpty()){
			consulta += "AND e.nombre = ?";
		}
		if (!Objects.equals(param[1], "--:--")){
			consulta += "AND CAST(e.horario_apertura AS VARCHAR) = ?";
		}
		if (!Objects.equals(param[2], "--:--")){
			consulta += "AND CAST(e.horario_cierre AS VARCHAR) = ?";
		}
		if (!Objects.equals(param[3], "Seleccionar estado...")){
			consulta += "AND e.estado = ?";
		}
		consulta += ";";

		try{

			pstmt = conn.prepareStatement(consulta);
			if(!param[0].isEmpty()){
				pstmt.setString(contador, param[0]);
				contador++;
			}
			if (!Objects.equals(param[1], "--:--")){
				param[1]+= ":00";
				pstmt.setString(contador, param[1]);
				contador++;
			}
			if (!Objects.equals(param[2], "--:--")){
				param[2]+= ":00";
				pstmt.setString(contador, param[2]);
				contador++;
			}
			if (!Objects.equals(param[3], "Seleccionar estado...")){
				pstmt.setString(contador, param[3]);
			}

			rs = pstmt.executeQuery();

			while(rs.next()){

				EstacionDeTransbordoMultimodal est = new EstacionDeTransbordoMultimodal();
				est.setId(rs.getInt("id"));
				est.setNombreEstacion(rs.getString("nombre"));
				est.setHorarioApertura(rs.getTimestamp("horario_apertura").toLocalDateTime().toLocalTime());
				est.setHorarioCierre(rs.getTimestamp("horario_cierre").toLocalDateTime().toLocalTime());
				est.setEstado(EstadoEstacion.valueOf(rs.getString("estado")));

				resultado.add(est);
			}
		}
		catch (SQLException e){

			e.printStackTrace();
		}
		finally{

			try{

				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
			}
			catch(SQLException e){

				e.printStackTrace();
			}
		}
		return resultado;
	}

}
	
