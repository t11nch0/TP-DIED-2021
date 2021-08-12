package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dominio.TareaMantenimiento;
import excepciones.BaseDeDatosException;
import gestores.GestorConexion;

public class Mantenimiento_DAO_PostgreSQL implements Mantenimiento_DAO{

	private final Connection conn = GestorConexion.getConnection();
	
	private static final String SELECT_ALL_MANTENIMIENTO =
	"SELECT * FROM died_db.tarea_mantenimiento"; 
	
	private static final String INSERT_MANTENIMIENTO =
			"INSERT INTO died_db.tarea_mantenimiento (FECHA_INICIO, FECHA_FIN, OBSERVACIONES, ID_ESTACION) VALUES (?,?,?,?) RETURNING ID";
	
	private static final String UPDATE_MANTENIMIENTO =
			"UPDATE died_db.tarea_mantenimiento SET FECHA_INICIO = ?, FECHA_FIN = ?, OBSERVACIONES = ?, ID_ESTACION = ? "+
			"WHERE ID = ?"; //?
	
	
	
	
	@Override	
	public TareaMantenimiento insertarMantenimiento(TareaMantenimiento mantenimiento) 
			throws BaseDeDatosException, SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			conn.setAutoCommit(false); 
			pstmt= conn.prepareStatement(INSERT_MANTENIMIENTO);

			if(mantenimiento.getFechaInicio() != null)
			{
				Date fecha_inicio = java.sql.Date.valueOf(mantenimiento.getFechaInicio());
				pstmt.setDate(1, fecha_inicio);
			}
			else
			{
				pstmt.setDate(1, null);
			}
			if(mantenimiento.getFechaFin() != null)
			{
				Date fecha_fin = java.sql.Date.valueOf(mantenimiento.getFechaFin());
				pstmt.setDate(2, fecha_fin);
			}
			else
			{
				pstmt.setDate(2, null);
			}       
			pstmt.setString(3, mantenimiento.getObservaciones());
			pstmt.setInt(4, mantenimiento.getEstacion().getId());

			rs = pstmt.executeQuery();
			while(rs.next()) 
			{
				mantenimiento.setId(rs.getInt("ID"));
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
		return mantenimiento; 
	}

	@Override
	public TareaMantenimiento finalizarMantenimiento(TareaMantenimiento mantenimiento) throws BaseDeDatosException, SQLException {

		PreparedStatement pstmt = null;
			try 
			{
				conn.setAutoCommit(false);  
				pstmt= conn.prepareStatement(UPDATE_MANTENIMIENTO);
			
				if(mantenimiento.getFechaInicio() != null)
				{
					Date fecha_inicio = java.sql.Date.valueOf(mantenimiento.getFechaInicio());
					pstmt.setDate(1, fecha_inicio);
				}
				else
				{
					pstmt.setDate(1, null);
				}
				if(mantenimiento.getFechaFin() != null)
				{
					Date fecha_fin = java.sql.Date.valueOf(mantenimiento.getFechaFin());
					pstmt.setDate(2, fecha_fin);
				}
				else
				{
					pstmt.setDate(2, null);
				}
				
				pstmt.setString(3, mantenimiento.getObservaciones()); 
				pstmt.setInt(4, mantenimiento.getEstacion().getId());
				
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
			return mantenimiento; 
		}
	
	
	@Override
	public List<TareaMantenimiento> buscarTodos() 
	{
		List<TareaMantenimiento> lista = new ArrayList<TareaMantenimiento>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Estacion_DAO estacionDAO = new Estacion_DAO_PostgreSQL();
		try 
		{
			pstmt= conn.prepareStatement(SELECT_ALL_MANTENIMIENTO);
			rs = pstmt.executeQuery();
			while(rs.next()) 
			{
				TareaMantenimiento mantenimiento = new TareaMantenimiento();
				
				mantenimiento.setId((rs.getInt("ID")));
				if(rs.getDate("FECHA_INICIO") != null)
				{
					mantenimiento.setFechaInicio(rs.getDate("FECHA_INICIO").toLocalDate());
				}
				if(rs.getDate("FECHA_FIN") != null)
				{
					mantenimiento.setFechaFin(rs.getDate("FECHA_FIN").toLocalDate());
				}
				mantenimiento.setObservaciones(rs.getString("OBSERVACIONES"));
				mantenimiento.setEstacion(estacionDAO.buscarPorId(rs.getInt("ID_ESTACION")));
				
				
				lista.add(mantenimiento);
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
	
	
	public List<TareaMantenimiento> buscarPorIdEstacion(Integer idEstacion){
		return this.buscarTodos()
				.stream()
				.filter(m -> m.getEstacion().getId() == idEstacion)
				.collect(Collectors.toList());
	}

}
	
