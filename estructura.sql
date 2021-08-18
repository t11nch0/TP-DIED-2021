			CREATE TABLE IF NOT EXISTS died_db.estacion ( 
					  ID SERIAL, 
			         NOMBRE VARCHAR(30) UNIQUE, 
			         HORARIO_APERTURA TIME, 
			         HORARIO_CIERRE TIME, 
			         ESTADO VARCHAR(18) NULL, 
					  PRIMARY KEY (ID)); 

			CREATE TABLE IF NOT EXISTS died_db.tarea_mantenimiento ( 
					  ID SERIAL, 
			         FECHA_INICIO DATE, 
			         FECHA_FIN DATE, 
					  OBSERVACIONES VARCHAR(500) NULL, 
					  ID_ESTACION SERIAL, 
					  PRIMARY KEY (ID), 
			         FOREIGN KEY (ID_ESTACION) REFERENCES died_db.estacion(ID));

	
			CREATE TABLE IF NOT EXISTS died_db.linea ( 
					  ID SERIAL, 
                     NOMBRE VARCHAR(30) UNIQUE, 
                     COLOR VARCHAR(15) NULL, 
			         ESTADO_LINEA VARCHAR(12) NULL,  
					  PRIMARY KEY (ID));
	
			CREATE TABLE IF NOT EXISTS died_db.trayecto ( 
					  ID SERIAL, 
					  ID_LINEA INTEGER,  
					  PRIMARY KEY (ID),
			         FOREIGN KEY (ID_LINEA) REFERENCES died_db.linea(ID));

			CREATE TABLE IF NOT EXISTS died_db.ruta ( 
					  ID SERIAL, 
					  ID_ORIGEN INTEGER, 
					  ID_DESTINO INTEGER, 
					  DISTANCIA_KILOMETROS INTEGER, 
					  DURACION_VIAJE_MINUTOS INTEGER, 
					  PASAJEROS_MAXIMO INTEGER, 
			          ESTADO_RUTA VARCHAR(12) NULL, 
			          COSTO DECIMAL(10,2), 
					  ID_TRAYECTO INTEGER,
			  		  PRIMARY KEY (ID), 
			          FOREIGN KEY (ID_TRAYECTO) REFERENCES died_db.trayecto(ID),
			          FOREIGN KEY (ID_ORIGEN) REFERENCES died_db.estacion(ID), 
			          FOREIGN KEY (ID_DESTINO) REFERENCES died_db.estacion(ID));
	 
			CREATE TABLE IF NOT EXISTS died_db.boleto ( 
					  ID SERIAL, 
					  NRO_BOLETO INTEGER, 
			          EMAIL_CLIENTE VARCHAR(30) NULL,  
			          NOMBRE_CLIENTE VARCHAR(30) NULL,  
					  FECHA_VENTA DATE, 
					  ID_ORIGEN INTEGER, 
					  ID_DESTINO INTEGER, 
					  CAMINO VARCHAR(30), 
			          COSTO DECIMAL(14,2), 
					  PRIMARY KEY (ID), 
			          FOREIGN KEY (ID_ORIGEN) REFERENCES died_db.estacion(ID), 
			          FOREIGN KEY (ID_DESTINO) REFERENCES died_db.estacion(ID));
