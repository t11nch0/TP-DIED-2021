package interfaces.registrarTrayecto;

import dominio.EstacionDeTransbordoMultimodal;
import dominio.LineaTransporte;
import dominio.Ruta;
import dominio.Trayecto;
import gestores.GestorEstacion;
import gestores.GestorLineaTransporte;
import gestores.GestorTrayecto;
import interfaces.InterfazFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AltaTrayecto {

    private static AltaTrayecto singleton;
    private final JPanel panelAltaTrayecto;

    public JPanel getPanelAltaTrayecto() {
        return panelAltaTrayecto;
    }

    public static AltaTrayecto getInstance() {
        if (singleton == null) {
            singleton = new AltaTrayecto();
        }
        return singleton;
    }

    private AltaTrayecto() {
        panelAltaTrayecto = new JPanel(new GridBagLayout());
        GestorLineaTransporte gestorLinea = new GestorLineaTransporte();
        List<LineaTransporte> lineas = gestorLinea.getTodasLineas();
        GestorEstacion gestorEstacion = new GestorEstacion();
        List<EstacionDeTransbordoMultimodal> estaciones = gestorEstacion.listarTodas();

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("CREAR TRAYECTO");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.BOTH;
        cons0.insets = new Insets(30, 15, 30, 15);
        panelAltaTrayecto.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel nombreTransporte = new JLabel("Transporte: ");
        nombreTransporte.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons1.gridwidth = 2;
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(10, 0, 35, 0);
        panelAltaTrayecto.add(nombreTransporte, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JComboBox<String> campoTransporte = new JComboBox<>();
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(35, 5, 0, 5);
        campoTransporte.addItem("Seleccionar transporte...");
        for (LineaTransporte l : lineas) {
            campoTransporte.addItem(l.getNombre());
        }
        panelAltaTrayecto.add(campoTransporte, cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JLabel nombreEstOrigen = new JLabel("Estacion a agregar: ");
        nombreEstOrigen.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons3.gridwidth = 2;
        cons3.gridx = 0;
        cons3.gridy = 2;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(10, 0, 35, 0);
        panelAltaTrayecto.add(nombreEstOrigen, cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JComboBox<String> campoEstOrigen = new JComboBox<>();
        cons4.gridwidth = 2;
        cons4.gridx = 0;
        cons4.gridy = 2;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(35, 5, 0, 5);
        campoEstOrigen.addItem("Seleccionar estacion...");
        for (EstacionDeTransbordoMultimodal e : estaciones) {
            campoEstOrigen.addItem(e.getNombreEstacion());
        }
        panelAltaTrayecto.add(campoEstOrigen, cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JButton botonAniadir = new JButton(" A" + '\u00f1' + "adir");
        cons5.gridwidth = 2;
        cons5.gridx = 0;
        cons5.gridy = 3;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.anchor = GridBagConstraints.PAGE_END;
        cons5.insets = new Insets(15, 41, 10, 41);
        panelAltaTrayecto.add(botonAniadir, cons5);

        DefaultListModel<String> modelo = new DefaultListModel<>();
        /*
        for (EstacionDeTransbordoMultimodal e : estaciones) {
            modelo.addElement(e.getNombreEstacion());
        }
         */
        if (modelo.isEmpty()) {
            modelo.add(0, "Lista de trayectos vacia...");
        }

        GridBagConstraints cons6 = new GridBagConstraints();
        JList<String> campoListaTrayecto = new JList<>(modelo);
        cons6.gridwidth = 2;
        cons6.gridheight = 2;
        cons6.gridx = 0;
        cons6.gridy = 4;
        cons6.fill = GridBagConstraints.BOTH;
        //cons6.anchor = GridBagConstraints.PAGE_END;
        cons6.insets = new Insets(15, 0, 10, 0);
        panelAltaTrayecto.add(campoListaTrayecto, cons6);

        GridBagConstraints cons7 = new GridBagConstraints();
        JButton botonConfirmar = new JButton("Confirmar");
        cons7.gridwidth = 2;
        cons7.gridx = 0;
        cons7.gridy = 7;
        cons7.fill = GridBagConstraints.HORIZONTAL;
        cons7.anchor = GridBagConstraints.PAGE_END;
        cons7.insets = new Insets(20, 41, 20, 41);
        panelAltaTrayecto.add(botonConfirmar, cons7);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 8;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.anchor = GridBagConstraints.PAGE_END;
        cons12.insets = new Insets(40, 41, 60, 41);
        panelAltaTrayecto.add(botonAtras, cons12);

        botonAtras.addActionListener(e -> {
            InterfazFrame.setPanel(InterfazRegistrarTrayecto.getInstance().getPanelRegistroTrayecto());
            singleton = null;
        });

        botonAniadir.addActionListener(e -> {
    		
    		/*
    		EstacionDeTransbordoMultimodal origen = estaciones.get(campoEstOrigen.getSelectedIndex()-1);
    		EstacionDeTransbordoMultimodal destino = estaciones.get(campoEstDestino.getSelectedIndex()-1);
        	List<Ruta> rutaNuevaLista = new ArrayList<>(); 
        	Integer distancia = Integer.parseInt(campoDistancia.getText()); //?
        	Integer duracion =  Integer.parseInt(campoDuracion.getText());
        	Integer pasajeros =  Integer.parseInt(campoPasajerosMaximo.getText());
        	EstadoRuta estado;
        	if (Objects.equals(campoEstado.getSelectedItem(), "Activa"))
				estado = EstadoRuta.ACTIVA;
			else	
				estado = EstadoRuta.INACTIVA;
        	Double costo = Double.parseDouble(campoCosto.getText());
        //	rutaNueva.setDistanciaKilometros(distancia);
        	Ruta rutaNueva = new Ruta(origen, destino, distancia, duracion, pasajeros, estado, costo);
        	rutaNuevaLista.add(rutaNueva);

    		 */
        });

        botonConfirmar.addActionListener(e -> {
    	    /*
    	    Integer idLinea = campoLineaTransporte;
    	    gestorTrayecto.crearTrayecto(Integer idLinea, List<Ruta> listaTramos);

    	     */
        });
    }
}
