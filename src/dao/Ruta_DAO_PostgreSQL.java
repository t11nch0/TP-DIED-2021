package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import dominio.Ruta;
import dominio.Ruta.EstadoRuta;
import excepciones.BaseDeDatosException;
import gestores.GestorConexion;

public class Ruta_DAO_PostgreSQL implements Ruta_DAO
{
	private final Connection conn = GestorConexion.getConnection();
	
	private static final String SELECT_ALL_RUTA =
	"SELECT * FROM died_db.ruta";  
	
	@Override
	public List<Ruta> buscarTodas() 
	{
		List<Ruta> lista = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Estacion_DAO estacionDAO = new Estacion_DAO_PostgreSQL();
				
		try 
		{
			pstmt= conn.prepareStatement(SELECT_ALL_RUTA);
			rs = pstmt.executeQuery();
			while(rs.next()) 
			{
				Ruta r = new Ruta();
				r.setId((rs.getInt("ID")));
				r.setOrigen(estacionDAO.buscarPorId(rs.getInt("ID_ORIGEN"))); 
				r.setDestino(estacionDAO.buscarPorId(rs.getInt("ID_DESTINO"))); 
				r.setDistanciaKilometros(rs.getInt("DISTANCIA_KILOMETROS"));
				r.setDuracionViajeMinutos(rs.getInt("DURACION_VIAJE_MINUTOS"));
				r.setPasajerosMaximos(rs.getInt("PASAJEROS_MAXIMO"));

				switch (rs.getString("ESTADO_RUTA")) {
					case "ACTIVA" -> r.setEstadoRuta(EstadoRuta.ACTIVA);
					case "NO_ACTIVA" -> r.setEstadoRuta(EstadoRuta.NO_ACTIVA);
				}
				r.setCosto(rs.getDouble("COSTO"));

				lista.add(r);
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
	public void insertarRuta(Ruta ruta) throws BaseDeDatosException, SQLException {
		// TODO Auto-generated method stub //?
	}

	@Override
	public void eliminarRuta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ruta editarRuta(Ruta ruta) throws BaseDeDatosException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ruta buscarPorId(Integer id) {
		{
			Ruta ruta = new Ruta();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			Estacion_DAO estacionDAO = new Estacion_DAO_PostgreSQL();
			try 
			{
				
				pstmt= conn.prepareStatement("SELECT * FROM died_db.ruta WHERE ID = "+ id); 
				rs = pstmt.executeQuery();

				while(rs.next()) 
				{
					ruta.setId((rs.getInt("ID")));
					ruta.setOrigen(estacionDAO.buscarPorId(rs.getInt("ID_ORIGEN"))); 
					ruta.setDestino(estacionDAO.buscarPorId(rs.getInt("ID_DESTINO"))); 
					ruta.setDistanciaKilometros(rs.getInt("DISTANCIA_KILOMETROS"));
					ruta.setDuracionViajeMinutos(rs.getInt("DURACION_VIAJE_MINUTOS"));
					ruta.setPasajerosMaximos(rs.getInt("PASAJEROS_MAXIMO"));

					switch (rs.getString("ESTADO_RUTA")) {
						case "ACTIVA" -> ruta.setEstadoRuta(EstadoRuta.ACTIVA);
						case "NO_ACTIVA" -> ruta.setEstadoRuta(EstadoRuta.NO_ACTIVA);
					}
					ruta.setCosto(rs.getDouble("COSTO"));


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
			return ruta;
		}
	}

	public List<Ruta> buscarPorIdTrayecto(Integer idTrayecto){
		return this.buscarTodas()
				.stream()
				.filter(r -> Objects.equals(r.getTrayecto().getId(), idTrayecto))
				.collect(Collectors.toList());
	}

}
