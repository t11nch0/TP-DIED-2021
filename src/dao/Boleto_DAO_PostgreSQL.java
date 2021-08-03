package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

//import dominio.LineaTransporte;
import dominio.Boleto;
//import dominio.Ruta;
///mport dominio.Ruta.EstadoRuta;
import excepciones.BaseDeDatosException;
import gestores.GestorConexion;

public class Boleto_DAO_PostgreSQL implements Boleto_DAO
{
	private Connection conn = GestorConexion.getConnection();

	private static final String INSERT_BOLETO =
			"INSERT INTO died_db.boleto (NRO_BOLETO, EMAIL, NOMBRE, FECHA_VENTA, ORIGEN, DESTINO, CAMINO, COSTO) VALUES (?,?,?,?,?,?,?,?) RETURNING ID";
	
	
	@Override
	public List<Boleto> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Boleto insertarBoleto(Boleto boleto) throws BaseDeDatosException, SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null; //?
			
			try 
			{			
				conn.setAutoCommit(false);  //?
				pstmt= conn.prepareStatement(INSERT_BOLETO);
				
				pstmt.setInt(1, boleto.getNroBoleto());
				pstmt.setString(2, boleto.getEmailCliente());
				pstmt.setString(3, boleto.getNombreCliente());
				if(boleto.getFechaVenta() != null)
				{
					Date fechaVenta = java.sql.Date.valueOf(boleto.getFechaVenta());
					pstmt.setDate(4, fechaVenta);
				}
				else
				{
					pstmt.setDate(4, null);
				}
				pstmt.setInt(5, boleto.getOrigen().getId()); 
				pstmt.setInt(6, boleto.getDestino().getId()); 
				pstmt.setInt(7, boleto.getCamino().getId());
				pstmt.setDouble(8, boleto.getCosto());
					
				rs = pstmt.executeQuery(); 
				while(rs.next()) 
				{
					boleto.setId(rs.getInt("ID"));
				}
		
				conn.commit();
				
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
			return boleto; //void?
}

	@Override
	public void eliminarBoleto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boleto editarBoleto(Boleto boleto) throws BaseDeDatosException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boleto buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
