package interfaces.registrarTrayecto;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;
import javax.swing.*;

import dominio.EstacionDeTransbordoMultimodal;
import dominio.LineaTransporte;
import dominio.Ruta;
import gestores.GestorEstacion;
import gestores.GestorLineaTransporte;
import gestores.GestorRuta;

import java.util.List;

import java.awt.*;

public class InterfazRegistrarTrayecto {

    private static InterfazRegistrarTrayecto singleton;
    private final JPanel panelRegistroTrayecto;
    private GestorLineaTransporte gestorLinea;
    private GestorEstacion gestorEstacion;
	private GestorRuta gestorRuta;
    private List<LineaTransporte> lineas;
	private List<EstacionDeTransbordoMultimodal> estaciones;

    

    public JPanel getPanelRegistroTrayecto() {
        return panelRegistroTrayecto;
    }

    public static InterfazRegistrarTrayecto getInstance(){
        if(singleton == null){
            singleton = new InterfazRegistrarTrayecto();
        }
        return singleton;
    }

    private InterfazRegistrarTrayecto() {
        panelRegistroTrayecto = new JPanel(new GridBagLayout());
        //this.lineas = gestorLinea.listarTodas(); //AAAA
        gestorEstacion = new GestorEstacion();
        gestorRuta = new GestorRuta();
        gestorLinea = new GestorLineaTransporte();
        this.lineas = gestorLinea.getTodasLineas();
        this.estaciones = gestorEstacion.listarTodas();
        
        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("MENU DE TRAYECTO");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,20,0);
        panelRegistroTrayecto.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel nombreTransporte = new JLabel("Transporte: ");
        nombreTransporte.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons1.gridwidth = 2;
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(10,0,35,0);
        panelRegistroTrayecto.add(nombreTransporte, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JComboBox<String> campoTransporte = new JComboBox<>();
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(35, 5 ,0 ,5);
        campoTransporte.addItem("Seleccionar transporte...");
        for(LineaTransporte l: lineas) {
        	campoTransporte.addItem(l.getNombre()); // y color?
        }
        panelRegistroTrayecto.add(campoTransporte,cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JLabel nombreEstOrigen = new JLabel("Estacion origen: ");
        nombreEstOrigen.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons3.gridwidth = 2;
        cons3.gridx = 0;
        cons3.gridy = 2;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(10,0,35,0);
        panelRegistroTrayecto.add(nombreEstOrigen, cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JComboBox<String> campoEstOrigen = new JComboBox<>();
        cons4.gridwidth = 2;
        cons4.gridx = 0;
        cons4.gridy = 2;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(35, 5 ,0 ,5);
        campoEstOrigen.addItem("Seleccionar estacion...");
        for(EstacionDeTransbordoMultimodal e: estaciones) {
            campoEstOrigen.addItem(e.getNombreEstacion());
        }      
        panelRegistroTrayecto.add(campoEstOrigen,cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JLabel nombreEstDestino = new JLabel("Estacion destino: ");
        nombreEstDestino.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons5.gridwidth = 2;
        cons5.gridx = 0;
        cons5.gridy = 3;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.insets = new Insets(10,0,35,0);
        panelRegistroTrayecto.add(nombreEstDestino, cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        JComboBox<String> campoEstDestino = new JComboBox<>();
        cons6.gridwidth = 2;
        cons6.gridx = 0;
        cons6.gridy = 3;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(35, 5 ,0 ,5);
        campoEstDestino.addItem("Seleccionar estacion...");
       /* for(EstacionDeTransbordoMultimodal e: estaciones) {
        	campoEstDestino.addItem(e.getNombreEstacion());
        }*/
        for(EstacionDeTransbordoMultimodal e: estaciones) {
        	if(campoEstOrigen.getSelectedItem().equals(e.getNombreEstacion())) {
        		System.out.println(e.getNombreEstacion());
        		for(Ruta r: gestorRuta.getRutasConOrigen(e)) {
        			campoEstDestino.addItem(r.getDestino().getNombreEstacion());
		        }
        	}
        }
        panelRegistroTrayecto.add(campoEstDestino,cons6);

        GridBagConstraints cons7 = new GridBagConstraints();
        JButton botonAniadir = new JButton(" Añadir");
        cons7.gridwidth = 1;
        cons7.gridx = 0;
        cons7.gridy = 4;
        cons7.anchor = GridBagConstraints.EAST;
        cons7.insets = new Insets(40,0,10,0);
        panelRegistroTrayecto.add(botonAniadir,cons7);

        GridBagConstraints cons8 = new GridBagConstraints();
        JButton botonEliminar = new JButton("Eliminar");
        cons8.gridwidth = 1;
        cons8.gridx = 1;
        cons8.gridy = 4;
        cons8.anchor = GridBagConstraints.EAST;
        cons8.insets = new Insets(40,0,10,0);
        panelRegistroTrayecto.add(botonEliminar,cons8);

        GridBagConstraints cons9 = new GridBagConstraints();
        JLabel labelDisenio = new JLabel("---> MAPA HARDCODEADO <---");
        labelDisenio.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons9.gridwidth = 2;
        cons9.gridx = 0;
        cons9.gridy = 5;
        cons9.fill = GridBagConstraints.HORIZONTAL;
        cons9.insets = new Insets(40,0,40,0);
        panelRegistroTrayecto.add(labelDisenio, cons9);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 7;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(10,0,60,0);
        panelRegistroTrayecto.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));
        campoEstOrigen.addActionListener(e -> 	
        				{
        					//Muestro como opcion las estaciones que tienen como origen a e1;
        					campoEstDestino.removeAllItems();
        			        campoEstDestino.addItem("Seleccionar estacion...");
        			        for(EstacionDeTransbordoMultimodal est: estaciones) {
        			        	if(campoEstOrigen.getSelectedItem().equals(est.getNombreEstacion())) {
        			        		System.out.println(est.getNombreEstacion());
        			        		for(Ruta r: gestorRuta.getRutasConOrigen(est)) {
        			        			campoEstDestino.addItem(r.getDestino().getNombreEstacion());
        					        }
        			        	}
        			        }
        					
        					
        					/*System.out.println(campoEstOrigen.getSelectedIndex());
        					System.out.println(campoEstOrigen.getSelectedItem());
        					System.out.println(estaciones.get(campoEstOrigen.getSelectedIndex()).getNombreEstacion());*/
        				});
    
    
    }
}
