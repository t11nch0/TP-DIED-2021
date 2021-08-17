package base_de_datos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DATA 
{
	private static boolean _DATA_INSERTADA = false; //?
	

	private static final String DATA_INSERT_ESTACION = 
			"INSERT INTO died_db.estacion (NOMBRE, HORARIO_APERTURA, HORARIO_CIERRE, ESTADO)"+
			"VALUES ('Estacion A','08:00','20:00','OPERATIVA'),"+
			"		('Estacion B','08:15','21:30','OPERATIVA'),"+
			"		('Estacion C','08:00','22:00','OPERATIVA'),"+
			"		('Estacion D','08:30','20:30','OPERATIVA'),"+
			"		('Estacion E','09:00','22:00','OPERATIVA'),"+
			"		('Estacion F','08:00','21:00','OPERATIVA'),"+
			"		('Estacion G','08:00','20:00','OPERATIVA'),"+
			"		('Estacion H','09:30','22:00','OPERATIVA'),"+
			"		('Estacion I','09:00','21:30','OPERATIVA'),"+
			"		('Estacion J','08:30','20:45','OPERATIVA');";

	
	private static final String DATA_INSERT_TAREA_MANTENIMIENTO =
	"INSERT INTO died_db.tarea_mantenimiento (FECHA_INICIO, FECHA_FIN, OBSERVACIONES, ID_ESTACION)"+
	"VALUES ('2021-01-05','2021-01-23','Obs 1','1'),"+
	"('2021-02-15','2021-03-02','Obs 2','1'),"+
	"('2021-03-20','2021-03-23','Obs 3','2'),"+
	"('2021-01-23','2021-01-25','Obs 4','3'),"+
	"('2021-01-15','2021-01-23','Obs 5','4'),"+
	"('2021-02-02','2021-02-15','Obs 6','4'),"+
	"('2021-04-26','2021-04-29','Obs 7','4'),"+
	"('2021-01-25','2021-01-29','Obs 8','5'),"+
	"('2021-02-28','2021-03-15','Obs 9','6'),"+
	"('2021-04-15','2021-04-26','Obs 10','8'),"+
	"('2021-04-20','2021-04-26','Obs 11','9'),"+
	"('2021-02-15','2021-02-25','Obs 12','10');";
	
	private static final String DATA_INSERT_LINEA =
	"INSERT INTO died_db.linea (NOMBRE, COLOR, ESTADO_LINEA)"+
	"VALUES ('Linea 1','Roja','ACTIVA'),"+
	"('Linea 2','Azul','ACTIVA'),"+
	"('Linea 3','Verde','ACTIVA'),"+
	"('Linea 4','Violeta','NO_ACTIVA'),"+
	"('Linea 5','Rosada','ACTIVA'),"+
	"('Linea 6','Amarilla','ACTIVA'),"+
	"('Linea 7','Naranja','ACTIVA'),"+
	"('Linea 8','Marron','NO_ACTIVA');";
	
	private static final String DATA_INSERT_TRAYECTO =
	"INSERT INTO died_db.trayecto (ID_LINEA)"+
	"VALUES ('1'),"+
	"('1'),"+
	"('2'),"+
	"('3'),"+
	"('3'),"+
	"('4'),"+
	"('5'),"+
	"('6'),"+
	"('6'),"+
	"('7'),"+
	"('8');";
	
	private static final String DATA_INSERT_RUTA =
	"INSERT INTO died_db.ruta (ID_ORIGEN, ID_DESTINO, DISTANCIA_KILOMETROS, DURACION_VIAJE_MINUTOS, PASAJEROS_MAXIMO, ESTADO_RUTA, COSTO, ID_TRAYECTO)"+
	"VALUES ('1','3','50','20','20','ACTIVA','65','1'),"+
	"('1','6','55','22','30','ACTIVA','60.75','2'),"+
	"('1','6','30','23','32','ACTIVA','40','6'),"+
	"('1','2','80','45','40','ACTIVA','50.50','3'),"+
	"('3','9','65','35','50','ACTIVA','45','11'),"+
	"('3','5','56','26','48','ACTIVA','55.75','1'),"+
	"('2','3','48','25','60','ACTIVA','70','11'),"+
	"('2','4','20','5','36','ACTIVA','80','3'),"+
	"('2','4','38','15','20','NO_ACTIVA','40.50','7'),"+
	"('6','9','25','10','58','ACTIVA','45','8'),"+
	"('6','5','62','25','40','ACTIVA','75','9'),"+
	"('6','7','45','20','26','ACTIVA','60','6'),"+
	"('6','8','78','45','50','ACTIVA','25.75','2'),"+
	"('6','2','40','12','44','ACTIVA','30','7'),"+
	"('4','6','38','12','38','ACTIVA','50','4'),"+
	"('4','7','45','15','24','ACTIVA','65.75','7'),"+
	"('4','7','65','25','30','ACTIVA','40','5'),"+
	"('5','9','65','30','32','ACTIVA','44.50','9'),"+
	"('5','9','45','25','30','ACTIVA','35','5'),"+
	"('7','5','26','8','28','ACTIVA','70.75','5'),"+
	"('7','8','70','30','20','ACTIVA','75','10'),"+
	"('5','10','80','35','42','ACTIVA','60.75','1'),"+
	"('8','10','75','35','40','ACTIVA','25.50','10');";
	
	private static final String DATA_INSERT_CAMINO =
	"INSERT INTO died_db.camino (DISTANCIA_TOTAL, DURACION_TOTAL, COSTO_TOTAL, ID_ORIGEN, ID_DESTINO)"+
	"VALUES ('100','40','125.50','1','7'),"+
	"('71','33','105.75','7','9'),"+
	"('113','60','115','2','9'),"+
	"('106','46','120.75','1','5'),"+
	"('92','48','115','1','5');";
	
	
	private static final String DATA_INSERT_BOLETO =
	"INSERT INTO died_db.boleto (NRO_BOLETO, EMAIL_CLIENTE, NOMBRE_CLIENTE, FECHA_VENTA, ID_ORIGEN, ID_DESTINO, ID_CAMINO, COSTO)"+
	"VALUES ('0001', 'cliente_uno@gmail.com', 'Cliente Uno','2021-02-26','7','9','2','105.75'),"+
	"('0002', 'cliente_dos@gmail.com', 'Cliente Dos','2021-03-15','1','5','5','115');";			

	

	
	public static void verificarInsertarData(Connection conn) 
	{
		if(!_DATA_INSERTADA) 
		{
			Statement stmt = null;
			try 
			{
				stmt = conn.createStatement();
				//If no insertó las de estacion, que las otra no ejecute (?)
				//Nombre de estacion con UNIQUE + ON CONFLICT DO NOTHING
				
				//stmt.execute(DATA_INSERT_ESTACION);
				if(stmt.execute(DATA_INSERT_ESTACION)) //????
				{
				stmt.execute(DATA_INSERT_TAREA_MANTENIMIENTO);
				stmt.execute(DATA_INSERT_LINEA);
				stmt.execute(DATA_INSERT_TRAYECTO);
				stmt.execute(DATA_INSERT_RUTA);
				stmt.execute(DATA_INSERT_CAMINO);
				stmt.execute(DATA_INSERT_BOLETO);
				}
				_DATA_INSERTADA = true;
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			finally 
			{
					try 
					{
						if(stmt!=null) stmt.close();
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
			}
		}
	}

}
