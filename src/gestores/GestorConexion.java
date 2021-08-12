package gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import base_de_datos.DB; 

public class GestorConexion 
{
	private static Connection conn = null;
	
	private GestorConexion()
	{
		String url = "jdbc:postgresql://localhost:5432/died_db"; //localhost?
		String driver = "org.postgresql.Driver";
		String usuario = "postgres";
		String password = "1234";
		
		 try
		 {
			 Class.forName(driver);
		     conn = DriverManager.getConnection(url, usuario, password);
		 }
		 catch(ClassNotFoundException | SQLException e)
		 {
		     e.printStackTrace();
		 }
	}
	
	public static Connection getConnection()
	{
		if(conn == null)
		{
			new GestorConexion();
			DB.verificarCrearTablas(conn);
		}
		
		return conn;
	}
}
