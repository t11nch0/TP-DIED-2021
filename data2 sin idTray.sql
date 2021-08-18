			INSERT INTO died_db.estacion (NOMBRE, HORARIO_APERTURA, HORARIO_CIERRE, ESTADO)
			VALUES ('Estacion A','08:00','20:00','OPERATIVA'),
			('Estacion B','08:15','21:30','OPERATIVA'),
			('Estacion C','08:00','22:00','OPERATIVA'),
			('Estacion D','08:30','20:30','OPERATIVA'),
			('Estacion E','09:00','22:00','OPERATIVA'),
			('Estacion F','08:00','21:00','OPERATIVA'),
			('Estacion G','08:00','20:00','OPERATIVA'),
			('Estacion H','09:30','22:00','OPERATIVA'),
			('Estacion I','09:00','21:30','OPERATIVA'),
			('Estacion J','08:30','20:45','OPERATIVA');
			
			INSERT INTO died_db.tarea_mantenimiento (FECHA_INICIO, FECHA_FIN, OBSERVACIONES, ID_ESTACION)
			VALUES ('2021-01-05','2021-01-23','Obs 1','1'),
			('2021-02-15','2021-03-02','Obs 2','1'),
			('2021-03-20','2021-03-23','Obs 3','2'),
			('2021-01-23','2021-01-25','Obs 4','3'),
			('2021-01-15','2021-01-23','Obs 5','4'),
			('2021-02-02','2021-02-15','Obs 6','4'),
			('2021-04-26','2021-04-29','Obs 7','4'),
			('2021-01-25','2021-01-29','Obs 8','5'),
			('2021-02-28','2021-03-15','Obs 9','6'),
			('2021-04-15','2021-04-26','Obs 10','8'),
			('2021-04-20','2021-04-26','Obs 11','9'),
			('2021-02-15','2021-02-25','Obs 12','10');
			
			INSERT INTO died_db.linea (NOMBRE, COLOR, ESTADO_LINEA)
			VALUES ('Linea 1','ROJO','ACTIVA'),
			('Linea 2','AZUL','ACTIVA'),
			('Linea 3','VERDE','ACTIVA'),
			('Linea 4','VIOLETA','ACTIVA'),
			('Linea 5','CELESTE','ACTIVA'),
			('Linea 6','AMARILLO','ACTIVA'),
			('Linea 7','NARANJA','ACTIVA'),
			('Linea 8','ROJO','INACTIVA');
			
			INSERT INTO died_db.trayecto (ID_LINEA)
			VALUES ('1'),
			('1'),
			('2'),
			('3'),
			('3'),
			('4'),
			('5'),
			('6'),
			('6'),
			('7'),
			('8');
			
			INSERT INTO died_db.ruta (ID_ORIGEN, ID_DESTINO, DISTANCIA_KILOMETROS, DURACION_VIAJE_MINUTOS, PASAJEROS_MAXIMO, ESTADO_RUTA, COSTO)
			VALUES ('1','3','50','20','20','ACTIVA','65'),
			('1','6','55','22','30','INACTIVA','60.75'),
			('1','6','30','23','32','ACTIVA','40'),
			('1','2','80','45','40','ACTIVA','50.50'),
			('3','9','65','35','50','ACTIVA','45'),
			('3','5','56','26','48','ACTIVA','55.75'),
			('2','3','48','25','60','ACTIVA','70'),
			('2','4','20','5','36','ACTIVA','80'),
			('2','4','38','15','20','INACTIVA','40.50'),
			('6','9','25','10','58','ACTIVA','45'),
			('6','5','62','25','40','ACTIVA','75'),
			('6','7','45','20','26','ACTIVA','60'),
			('6','8','78','45','50','ACTIVA','25.75'),
			('6','2','40','12','44','ACTIVA','30'),
			('4','6','38','12','38','ACTIVA','50'),
			('4','7','45','15','24','INACTIVA','65.75'),
			('4','7','65','25','30','ACTIVA','40'),
			('5','9','65','30','32','ACTIVA','44.50'),
			('5','9','45','25','30','INACTIVA','35'),
			('7','5','26','8','28','ACTIVA','70.75'),
			('7','8','70','30','20','ACTIVA','75'),
			('5','10','80','35','42','ACTIVA','60.75'),
			('8','10','75','35','40','ACTIVA','25.50');
			
			INSERT INTO died_db.camino (DISTANCIA_TOTAL, DURACION_TOTAL, COSTO_TOTAL, ID_ORIGEN, ID_DESTINO)
			VALUES ('100','40','125.50','1','7'),
			('71','33','105.75','7','9'),
			('113','60','115','2','9'),
			('106','46','120.75','1','5'),
			('92','48','115','1','5');
			
			INSERT INTO died_db.boleto (NRO_BOLETO, EMAIL_CLIENTE, NOMBRE_CLIENTE, FECHA_VENTA, ID_ORIGEN, ID_DESTINO, ID_CAMINO, COSTO)
			VALUES ('0001', 'cliente_uno@gmail.com', 'Cliente Uno','2021-02-26','7','9','2','105.75'),
			('0002', 'cliente_dos@gmail.com', 'Cliente Dos','2021-03-15','1','5','5','115');			
