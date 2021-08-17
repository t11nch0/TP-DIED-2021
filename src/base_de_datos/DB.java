package base_de_datos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	private static boolean _TABLAS_CREADAS = false;

	private static final String TABLA_CREATE_ESTACION =
			"CREATE TABLE IF NOT EXISTS died_db.estacion ( "+
					"		  ID SERIAL, "+
					"         NOMBRE VARCHAR(30) UNIQUE, "+
					"         HORARIO_APERTURA TIME, "+
					"         HORARIO_CIERRE TIME, "+
					"         ESTADO VARCHAR(18) NULL, "+
					"		  PRIMARY KEY (ID)) ";

	private static final String TABLA_CREATE_TAREA_MANTENIMIENTO = 
			"CREATE TABLE IF NOT EXISTS died_db.tarea_mantenimiento ( "+
			"		  ID SERIAL, "+
			"         FECHA_INICIO DATE, "+
			"         FECHA_FIN DATE, "+
			"		  OBSERVACIONES VARCHAR(200) NULL, "+ 
			"		  ID_ESTACION SERIAL, "+
			"		  PRIMARY KEY (ID), "+
			"         FOREIGN KEY (ID_ESTACION) REFERENCES died_db.estacion(ID))";

	
	private static final String TABLA_CREATE_LINEA = 
			"CREATE TABLE IF NOT EXISTS died_db.linea ( "+
			"		  ID SERIAL, "+
            "         NOMBRE VARCHAR(30) UNIQUE, "+ 
            "         COLOR VARCHAR(15) NULL, "+ 
			"         ESTADO_LINEA VARCHAR(12) NULL, "+ 
			"		  PRIMARY KEY (ID))";
	
	private static final String TABLA_CREATE_TRAYECTO = 
			"CREATE TABLE IF NOT EXISTS died_db.trayecto ( "+
			"		  ID SERIAL, "+
			"		  ID_LINEA INTEGER, "+ 
			"		  PRIMARY KEY (ID), "+
			"         FOREIGN KEY (ID_LINEA) REFERENCES died_db.linea(ID))";//?

	private static final String TABLA_CREATE_RUTA =
			"CREATE TABLE IF NOT EXISTS died_db.ruta ( "+
					"		  ID SERIAL, "+
					"		  ID_ORIGEN INTEGER, "+
					"		  ID_DESTINO INTEGER, "+
					"		  DISTANCIA_KILOMETROS INTEGER, "+
					"		  DURACION_VIAJE_MINUTOS INTEGER, "+
					"		  PASAJEROS_MAXIMO INTEGER, "+
					"         ESTADO_RUTA VARCHAR(12) NULL, "+
					"         COSTO DECIMAL(10,2), "+
					"		  ID_TRAYECTO INTEGER, "+ //?
					"		  PRIMARY KEY (ID), "+
					"         FOREIGN KEY (ID_TRAYECTO) REFERENCES died_db.trayecto(ID), "+
					"         FOREIGN KEY (ID_ORIGEN) REFERENCES died_db.estacion(ID), "+
					"         FOREIGN KEY (ID_DESTINO) REFERENCES died_db.estacion(ID)) ";
	
	private static final String TABLA_CREATE_CAMINO = 
			"CREATE TABLE IF NOT EXISTS died_db.camino ( "+
			"		  ID SERIAL, "+
			"		  DISTANCIA_TOTAL INTEGER, "+
			"		  DURACION_TOTAL INTEGER, "+	
			"         COSTO_TOTAL DECIMAL(14,2), "+
			"		  ID_ORIGEN INTEGER, "+
			"		  ID_DESTINO INTEGER, "+
			"		  PRIMARY KEY (ID), "+
			"         FOREIGN KEY (ID_ORIGEN) REFERENCES died_db.estacion(ID), "+
			"         FOREIGN KEY (ID_DESTINO) REFERENCES died_db.estacion(ID)) ";
	
	private static final String TABLA_CREATE_BOLETO = 
			"CREATE TABLE IF NOT EXISTS died_db.boleto ( "+
			"		  ID SERIAL, "+
			"		  NRO_BOLETO INTEGER, "+
			"         EMAIL_CLIENTE VARCHAR(30) NULL, "+ 
			"         NOMBRE_CLIENTE VARCHAR(30) NULL, "+ 
			"		  FECHA_VENTA DATE, "+
			"		  ID_ORIGEN INTEGER, "+
			"		  ID_DESTINO INTEGER, "+
			"		  ID_CAMINO INTEGER, "+
			"         COSTO DECIMAL(14,2), "+
			"		  PRIMARY KEY (ID), "+
			"         FOREIGN KEY (ID_ORIGEN) REFERENCES died_db.estacion(ID), "+
			"         FOREIGN KEY (ID_DESTINO) REFERENCES died_db.estacion(ID)) ";
	

	public static void verificarCrearTablas(Connection conn) {

		if(!_TABLAS_CREADAS) {

			Statement stmt = null;

			try {

				stmt = conn.createStatement();
				stmt.execute(TABLA_CREATE_ESTACION);
				stmt.execute(TABLA_CREATE_TAREA_MANTENIMIENTO);
				stmt.execute(TABLA_CREATE_LINEA);
				stmt.execute(TABLA_CREATE_TRAYECTO);
				stmt.execute(TABLA_CREATE_RUTA);
				stmt.execute(TABLA_CREATE_CAMINO);
				stmt.execute(TABLA_CREATE_BOLETO);
				_TABLAS_CREADAS = true;

			} catch (SQLException e) {

				e.printStackTrace();

			} finally {

					try {

						if(stmt!=null) stmt.close();

					} catch (SQLException e) {

						e.printStackTrace();
					}
			}
		}
	}
}
