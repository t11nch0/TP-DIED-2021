package interfaces.registrarTrayecto;

import dominio.EstacionDeTransbordoMultimodal;
import dominio.LineaTransporte;
import dominio.Ruta;
import dominio.Ruta.EstadoRuta;
import gestores.GestorEstacion;
import gestores.GestorLineaTransporte;
import gestores.GestorRuta;
import gestores.GestorTrayecto;
import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class InterfazAgregarTrayecto {

    private static InterfazAgregarTrayecto singleton;
    private final JPanel panelAgregarTrayecto;
    private final GestorRuta gestorRuta;
    private final GestorTrayecto gestorTrayecto;
    private final List<EstacionDeTransbordoMultimodal> estaciones;


    public JPanel getPanelAgregarTrayecto() {
        return panelAgregarTrayecto;
    }

    public static InterfazAgregarTrayecto getInstance() {
        if (singleton == null) {
            singleton = new InterfazAgregarTrayecto();
        }
        return singleton;
    }

    private InterfazAgregarTrayecto() {
        panelAgregarTrayecto = new JPanel(new GridBagLayout());
        GestorEstacion gestorEstacion = new GestorEstacion();
        gestorRuta = new GestorRuta();
        gestorTrayecto = new GestorTrayecto();
        GestorLineaTransporte gestorLinea = new GestorLineaTransporte();
        List<LineaTransporte> lineas = gestorLinea.getTodasLineas();
        List<Ruta> rutaNuevaLista = new ArrayList<>();
        this.estaciones = gestorEstacion.listarTodas();
        AtomicInteger indice = new AtomicInteger();

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("AGREGAR TRAYECTOS");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55, 0, 20, 0);
        panelAgregarTrayecto.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel nombreTransporte = new JLabel("Transporte: ");
        nombreTransporte.setFont(new Font("Dialog", Font.PLAIN, 12));
        cons1.gridwidth = 2;
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(10, 0, 35, 0);
        panelAgregarTrayecto.add(nombreTransporte, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JComboBox<String> campoTransporte = new JComboBox<>();
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(35, 5, 0, 5);
        campoTransporte.addItem("Seleccionar estacion...");
        for (LineaTransporte l : lineas) {
            campoTransporte.addItem(l.getNombre());
        }
        panelAgregarTrayecto.add(campoTransporte, cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JLabel nombreOrigen = new JLabel("Estacion de Origen: ");
        nombreOrigen.setFont(new Font("Dialog", Font.PLAIN, 12));
        cons3.gridwidth = 1;
        cons3.gridx = 0;
        cons3.gridy = 2;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(5, 0, 10, 0);
        panelAgregarTrayecto.add(nombreOrigen, cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JComboBox<String> campoEstacionOrigen = new JComboBox<>();
        cons4.gridwidth = 1;
        cons4.gridx = 0;
        cons4.gridy = 2;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(30, 0, 0, 5);
        campoEstacionOrigen.addItem("Seleccionar estacion...");
        for (EstacionDeTransbordoMultimodal e : estaciones) {
            campoEstacionOrigen.addItem(e.getNombreEstacion());
        }
        panelAgregarTrayecto.add(campoEstacionOrigen, cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JLabel nombreDestino = new JLabel("Estacion de Destino: ");
        nombreDestino.setFont(new Font("Dialog", Font.PLAIN, 12));
        cons5.gridwidth = 1;
        cons5.gridx = 1;
        cons5.gridy = 2;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.insets = new Insets(5, 50, 10, 0);
        panelAgregarTrayecto.add(nombreDestino, cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        JComboBox<String> campoEstacionDestino = new JComboBox<>();
        cons6.gridwidth = 1;
        cons6.gridx = 1;
        cons6.gridy = 2;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(30, 50, 0, 30);
        campoEstacionDestino.addItem("Seleccionar estacion...");
        for (EstacionDeTransbordoMultimodal e : estaciones) {
            campoEstacionDestino.addItem(e.getNombreEstacion());
        }
/*
        for (EstacionDeTransbordoMultimodal e : estaciones) {
            if (Objects.equals(campoEstacionOrigen.getSelectedItem(), e.getNombreEstacion())) {
                System.out.println(e.getNombreEstacion());
                for (Ruta r : gestorRuta.getRutasConOrigen(e)) {
                    campoEstacionDestino.addItem(r.getDestino().getNombreEstacion());
                }
            }
        }

 */
        panelAgregarTrayecto.add(campoEstacionDestino, cons6);

        GridBagConstraints cons18 = new GridBagConstraints();
        JLabel labelDistancia = new JLabel("Distancia: ");
        cons18.gridx = 0;
        cons18.gridy = 3;
        cons18.fill = GridBagConstraints.HORIZONTAL;
        cons18.insets = new Insets(10, 5, 55, 5);
        panelAgregarTrayecto.add(labelDistancia, cons18);

        GridBagConstraints cons7 = new GridBagConstraints();
        JTextField campoDistancia = new JTextField();
        cons7.gridwidth = 2;
        cons7.gridx = 0;
        cons7.gridy = 3;
        cons7.fill = GridBagConstraints.HORIZONTAL;
        cons7.insets = new Insets(5, 5, 0, 5);
        panelAgregarTrayecto.add(campoDistancia, cons7);

        GridBagConstraints cons8 = new GridBagConstraints();
        JLabel labelTiempo = new JLabel("Tiempo (minutos): ");
        cons8.gridx = 0;
        cons8.gridy = 4;
        cons8.fill = GridBagConstraints.HORIZONTAL;
        cons8.insets = new Insets(10, 5, 55, 5);
        panelAgregarTrayecto.add(labelTiempo, cons8);

        GridBagConstraints cons9 = new GridBagConstraints();
        JTextField campoDuracion = new JTextField();
        cons9.gridwidth = 2;
        cons9.gridx = 0;
        cons9.gridy = 4;
        cons9.fill = GridBagConstraints.HORIZONTAL;
        cons9.insets = new Insets(5, 5, 10, 5);
        panelAgregarTrayecto.add(campoDuracion, cons9);

        GridBagConstraints cons10 = new GridBagConstraints();
        JLabel labelPasajeros = new JLabel("Cantida pasajeros: ");
        cons10.gridx = 0;
        cons10.gridy = 5;
        cons10.fill = GridBagConstraints.HORIZONTAL;
        cons10.insets = new Insets(10, 5, 55, 5);
        panelAgregarTrayecto.add(labelPasajeros, cons10);

        GridBagConstraints cons11 = new GridBagConstraints();
        JTextField campoPasajeros = new JTextField();
        cons11.gridwidth = 2;
        cons11.gridx = 0;
        cons11.gridy = 5;
        cons11.fill = GridBagConstraints.HORIZONTAL;
        cons11.insets = new Insets(5, 5, 10, 5);
        panelAgregarTrayecto.add(campoPasajeros, cons11);

        GridBagConstraints cons12 = new GridBagConstraints();
        JLabel labelEstado = new JLabel("Estado: ");
        cons12.gridx = 0;
        cons12.gridy = 6;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(10, 5, 55, 5);
        panelAgregarTrayecto.add(labelEstado, cons12);

        GridBagConstraints cons13 = new GridBagConstraints();
        JComboBox<String> campoEstado = new JComboBox<>();
        cons13.gridwidth = 2;
        cons13.gridx = 0;
        cons13.gridy = 6;
        cons13.fill = GridBagConstraints.HORIZONTAL;
        cons13.insets = new Insets(5, 5, 10, 5);
        campoEstado.addItem("ACTIVO");
        campoEstado.addItem("INACTIVO");
        panelAgregarTrayecto.add(campoEstado, cons13);

        GridBagConstraints cons14 = new GridBagConstraints();
        JLabel labelCosto = new JLabel("Costo en $: ");
        cons14.gridx = 0;
        cons14.gridy = 7;
        cons14.fill = GridBagConstraints.HORIZONTAL;
        cons14.insets = new Insets(10, 5, 95, 5);
        panelAgregarTrayecto.add(labelCosto, cons14);

        GridBagConstraints cons15 = new GridBagConstraints();
        JTextField campoCosto = new JTextField();
        cons15.gridwidth = 2;
        cons15.gridx = 0;
        cons15.gridy = 7;
        cons15.fill = GridBagConstraints.HORIZONTAL;
        cons15.insets = new Insets(5, 5, 40, 5);
        panelAgregarTrayecto.add(campoCosto, cons15);

        GridBagConstraints cons19 = new GridBagConstraints();
        JButton botonAniadir = new JButton(" A" + '\u00f1' + "adir");
        cons19.gridwidth = 2;
        cons19.gridx = 0;
        cons19.gridy = 7;
        cons19.fill = GridBagConstraints.HORIZONTAL;
        cons19.anchor = GridBagConstraints.PAGE_END;
        cons19.insets = new Insets(35, 41, 10, 41);
        panelAgregarTrayecto.add(botonAniadir, cons19);

        DefaultListModel<String> modelo = new DefaultListModel<>();
        /*
        for (EstacionDeTransbordoMultimodal e : estaciones) {
            modelo.addElement(e.getNombreEstacion());
        }
         */
        if (modelo.isEmpty()) {
            modelo.add(0, "Lista de trayectos vacia...");
        }

        GridBagConstraints cons20 = new GridBagConstraints();
        JList<String> campoListaTrayecto = new JList<>(modelo);
        cons20.gridwidth = 2;
        cons20.gridheight = 2;
        cons20.gridx = 0;
        cons20.gridy = 8;
        cons20.fill = GridBagConstraints.BOTH;
        //cons20.anchor = GridBagConstraints.PAGE_END;
        cons20.insets = new Insets(15, 0, 10, 0);
        JScrollPane pane = new JScrollPane(campoListaTrayecto);
        panelAgregarTrayecto.add(pane, cons20);

        GridBagConstraints cons16 = new GridBagConstraints();
        JButton botonConfirmar = new JButton("Confirmar");
        cons16.gridwidth = 2;
        cons16.gridx = 0;
        cons16.gridy = 11;
        cons16.fill = GridBagConstraints.HORIZONTAL;
        cons16.insets = new Insets(10, 0, 10, 0);
        panelAgregarTrayecto.add(botonConfirmar, cons16);

        GridBagConstraints cons17 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons17.gridwidth = 2;
        cons17.gridx = 0;
        cons17.gridy = 12;
        cons17.fill = GridBagConstraints.HORIZONTAL;
        cons17.insets = new Insets(10, 0, 40, 0);
        panelAgregarTrayecto.add(botonAtras, cons17);

        botonAtras.addActionListener(e -> {
            InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal());
            singleton = null;
        });
        
        botonAniadir.addActionListener(e -> {
    		
    		
        	if(Objects.equals(modelo.get(0), "Lista de trayectos vacia..."))
                modelo.clear();

           // String lineaS = campoTransporte.getItemAt(campoTransporte.getSelectedIndex());
            modelo.add(indice.get(), campoEstacionOrigen.getSelectedItem().toString() +"->"+campoEstacionDestino.getSelectedItem().toString());
            indice.incrementAndGet();
        	
    		EstacionDeTransbordoMultimodal origen = estaciones.get(campoEstacionOrigen.getSelectedIndex()-1);
    		EstacionDeTransbordoMultimodal destino = estaciones.get(campoEstacionDestino.getSelectedIndex()-1);
        //	List<Ruta> rutaNuevaLista = new ArrayList<>(); 
        	Integer distancia = Integer.parseInt(campoDistancia.getText()); //?
        	Integer duracion =  Integer.parseInt(campoDuracion.getText());
        	Integer pasajeros =  Integer.parseInt(campoPasajeros.getText());
        	EstadoRuta estado;
        	if (Objects.equals(campoEstado.getSelectedItem(), "ACTIVA"))
				estado = EstadoRuta.ACTIVA;
			else	
				estado = EstadoRuta.INACTIVA;
        	Double costo = Double.parseDouble(campoCosto.getText());
        //	rutaNueva.setDistanciaKilometros(distancia);
        	Ruta rutaNueva = new Ruta(origen, destino, distancia, duracion, pasajeros, estado, costo);
        	rutaNuevaLista.add(rutaNueva);
        	
        	System.out.println("rutaNueva origen: "+rutaNueva.getOrigen().getNombreEstacion());
        	System.out.println("rutaNueva destino: "+rutaNueva.getDestino().getNombreEstacion());
        	System.out.println("rutaNueva distancia: "+rutaNueva.getDistanciaKilometros());
        	System.out.println("rutaNueva duracion: "+rutaNueva.getDuracionViajeMinutos());
        	System.out.println("rutaNueva pasajeros: "+rutaNueva.getPasajerosMaximos());
        	System.out.println("rutaNueva estado: "+rutaNueva.getEstadoRuta());
        	System.out.println("rutaNueva costo: "+rutaNueva.getCosto());
        	System.out.println("  ");
        	System.out.println("rutaNuevaLista size: "+rutaNuevaLista.size());
    		
        	
        	
        	/* if(!modelo.contains(campoEstOrigen.getSelectedItem().toString())){
                 modelo.addElement(campoEstOrigen.getSelectedItem().toString());
             }*/
        	
    	});
    
    botonConfirmar.addActionListener(e -> {
    	
    	//meter una lista en el for de modelo? que guarde las lineas o los id;
    	Integer idLinea = lineas.get(campoTransporte.getSelectedIndex()-1).getId();
    	System.out.println("Linea seleccionada idLinea: "+idLinea);
    	/*	try {
			gestorTrayecto.crearTrayecto(idLinea, rutaNuevaLista);
		} catch (CamposIncorrectosException | SQLException | BaseDeDatosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
    });
    }
}
