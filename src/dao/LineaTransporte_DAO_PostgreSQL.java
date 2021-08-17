package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import dominio.EstacionDeTransbordoMultimodal;
import dominio.LineaTransporte;
import dominio.LineaTransporte.EstadoLinea;
import excepciones.BaseDeDatosException;
import gestores.GestorConexion;
import gestores.GestorTrayecto;

public class LineaTransporte_DAO_PostgreSQL implements LineaTransporte_DAO
{
	private final Connection conn = GestorConexion.getConnection();
	
	private static final String SELECT_ALL_LINEA =
	"SELECT * FROM died_db.linea";  
	
	private static final String INSERT_LINEA =
			"INSERT INTO died_db.linea (NOMBRE, COLOR, ESTADO_LINEA) VALUES (?,?,?) RETURNING ID";
	
	private static final String UPDATE_LINEA =
			"UPDATE died_db.linea SET NOMBRE = ?, COLOR = ?, ESTADO_LINEA = ? "+
			"WHERE ID = ?"; //?
	
	private static final String EXISTE_NOMBRE_LINEA =
			"SELECT "+
		     "CASE WHEN EXISTS "+
		     "( "+
		     "SELECT NOMBRE FROM died_db.linea WHERE nombre = ? "+
		     ") "+
		     "THEN TRUE "+
		     "ELSE FALSE "+
		     "END";
	
	
	
	@Override
	public List<LineaTransporte> buscarTodas() 
	{
		List<LineaTransporte> lista = new ArrayList<LineaTransporte>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Trayecto_DAO trayectoDAO = new Trayecto_DAO_PostgreSQL();
		
		try 
		{
			pstmt= conn.prepareStatement(SELECT_ALL_LINEA); 
			rs = pstmt.executeQuery();
			while(rs.next()) 
			{
				LineaTransporte l = new LineaTransporte();
				l.setId((rs.getInt("ID")));
				l.setNombre(rs.getString("NOMBRE"));
				l.setColor(rs.getString("COLOR"));
				switch (rs.getString("ESTADO_LINEA")) {
					case "ACTIVA":
						l.setEstado(EstadoLinea.ACTIVA);
						break;
					case "INACTIVA":
						l.setEstado(EstadoLinea.INACTIVA);
						break;
				}
				// ? Lista
				//l.setTrayectos(trayectoDAO.buscarPorIdLinea(l.getId()));
				l.setTrayectos(trayectoDAO.buscarPorIdLinea((rs.getInt("ID"))));
				//bucle
				lista.add(l);
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
		return lista;
}

	
	@Override	
	public LineaTransporte insertarLineaTransporte(LineaTransporte linea) 
			throws BaseDeDatosException, SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try 
		{			
			conn.setAutoCommit(false);  
			pstmt= conn.prepareStatement(INSERT_LINEA);
			
			pstmt.setString(1, linea.getNombre());
			pstmt.setString(2, linea.getColor());
			pstmt.setString(3, linea.getEstado().toString());
			
			rs = pstmt.executeQuery(); 
			while(rs.next()) 
			{
				linea.setId(rs.getInt("ID"));
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
		return linea; 
	}

	
	@Override
	public void eliminarLineaTransporte(LineaTransporte linea) throws BaseDeDatosException, SQLException {
		
		PreparedStatement pstmt = null;
			try 
			{				
				conn.setAutoCommit(false);  
				pstmt= conn.prepareStatement("DELETE FROM died_db.linea WHERE ID = ?");
				
				pstmt.setInt(1, linea.getId());

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
	
	}
	
	@Override
	public LineaTransporte editarLineaTransporte(LineaTransporte linea) throws BaseDeDatosException, SQLException {

		PreparedStatement pstmt = null;
			try 
			{				
				conn.setAutoCommit(false);  
				pstmt= conn.prepareStatement(UPDATE_LINEA);
				
				pstmt.setString(1, linea.getNombre()); 
				pstmt.setString(2, linea.getColor());
				pstmt.setString(3, linea.getEstado().toString());				
				pstmt.setInt(4, linea.getId());
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
			return linea; 
		}

	@Override
	public boolean existeNombreDeLinea(String nombre)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean resultado = false;
		try 
		{
			pstmt = conn.prepareStatement(EXISTE_NOMBRE_LINEA);
			pstmt.setString(1, nombre);
			rs = pstmt.executeQuery();
			while(rs.next()) 
			{
				resultado = rs.getBoolean("CASE"); 
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
	public LineaTransporte buscarPorId(Integer id) {
		{
			LineaTransporte linea = new LineaTransporte();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			Trayecto_DAO trayectoDAO = new Trayecto_DAO_PostgreSQL();

			
			try 
			{
				pstmt= conn.prepareStatement("SELECT * FROM died_db.linea WHERE ID = "+ id);
				rs = pstmt.executeQuery();

				while(rs.next()) 
				{
					linea.setId(rs.getInt("ID"));
					linea.setNombre(rs.getString("NOMBRE"));
					linea.setColor(rs.getString("COLOR"));
					switch (rs.getString("ESTADO_LINEA")) {
						case "ACTIVA":
							linea.setEstado(EstadoLinea.ACTIVA);
							break;
						case "INACTIVA":
							linea.setEstado(EstadoLinea.INACTIVA);
							break;
					}
					linea.setTrayectos(trayectoDAO.buscarPorIdLinea(linea.getId())); //??
					//AAAAAAAAAAA
					// bucle?
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
			return linea;
		}
	}

	@Override
	public List<LineaTransporte> filtrar(String[] param) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LineaTransporte> resultado = new ArrayList<>();
		String consulta = "SELECT * FROM died_db.linea l WHERE 1=1";
		int contador = 1;

		if(!param[0].isEmpty()){
			consulta += "AND l.nombre LIKE CONCAT(?, '%')";
		}
		if(!Objects.equals(param[1], "Seleccionar color...")){
			consulta += "AND l.color = ?";
		}
		if(!Objects.equals(param[2], "Seleccionar estado...")){
			consulta += "AND l.estado_linea = ?";
		}

		consulta += ";";

		try{

			pstmt = conn.prepareStatement(consulta);

			if(!param[0].isEmpty()){
				pstmt.setString(contador, param[0]);
				contador++;
			}
			if(!Objects.equals(param[1], "Seleccionar color...")){
				pstmt.setString(contador, param[1]);
				contador++;
			}
			if(!Objects.equals(param[2], "Seleccionar estado...")){
				pstmt.setString(contador, param[2]);
			}

			rs = pstmt.executeQuery();

			while(rs.next()){

				LineaTransporte lin = new LineaTransporte();
				lin.setId(rs.getInt("id"));
				lin.setNombre(rs.getString("nombre"));
				lin.setColor(rs.getString("color"));
				lin.setEstado(LineaTransporte.EstadoLinea.valueOf(rs.getString("estado_linea")));

				resultado.add(lin);
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
