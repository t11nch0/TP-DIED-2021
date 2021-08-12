package interfaces.registrarTransporte;

import interfaces.InterfazFrame;
import interfaces.registrarEstacion.BuscarEstacionesTabla;
import interfaces.registrarEstacion.InterfazRegistrarEstacion;
import interfaces.registrarEstacion.ModeloTablaEstaciones;

import javax.swing.*;

import dominio.EstacionDeTransbordoMultimodal;
import dominio.LineaTransporte;
import gestores.GestorEstacion;
import gestores.GestorLineaTransporte;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class BuscarAtributosTransporte {

    private static BuscarAtributosTransporte singleton;
    private final JPanel panelBuscarAtributosTransporte;
    private ModeloTablaLineas modeloTablaLineas;
    private JTable tablaLineas;
    private GestorLineaTransporte gestorLinea;
    private List<LineaTransporte> lineas;

    public JPanel getPanelBuscarAtributosTransporte() {
        return panelBuscarAtributosTransporte;
    }

    public static BuscarAtributosTransporte getInstance(){
        if(singleton == null){
            singleton = new BuscarAtributosTransporte();
        }
        return singleton;
    }

    private BuscarAtributosTransporte() {
        panelBuscarAtributosTransporte = new JPanel(new GridBagLayout());
        this.gestorLinea = new GestorLineaTransporte();
        this.lineas = gestorLinea.listarTodas();

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("BUSCAR ATRIBUTOS DE TRANSPORTE");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.ipady = 100;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.BOTH;
        cons0.insets = new Insets(40,0,10,0);
        panelBuscarAtributosTransporte.add(nombreMenu, cons0);

     /*   String[] data = {"Estacion1", "Estacion2", "Estacion3", "Estacion4", "Estacion5", "Estacion6", "Estacion7", "Estacion8", "Estacion9", "Estacion10"};
        GridBagConstraints cons1 = new GridBagConstraints();
        JTabbedPane paneles = new JTabbedPane();
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.BOTH;
        cons1.insets = new Insets(10,0,30,0);
        panelBuscarAtributosTransporte.add(paneles, cons1);

        paneles.addTab("Nombre", new JList<>(data));
        paneles.addTab("Color", new JList<>(data));
        paneles.addTab("Estado", new JList<>(data));*/

        GridBagConstraints cons3 = new GridBagConstraints();
        JLabel labelNombre = new JLabel("Nombre: ");
        cons3.gridx = 0;
        cons3.gridy = 1;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(10, 5 ,5 ,5);
        panelBuscarAtributosTransporte.add(labelNombre,cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JTextField campoNombre = new JTextField();
        //
        campoNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(campoNombre.getText().length()>29) {
					e.consume();
				}
			}
		});
        //
        cons4.gridwidth = 2;
        cons4.gridx = 0;
        cons4.gridy = 2;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(5, 5 ,10 ,5);
        panelBuscarAtributosTransporte.add(campoNombre,cons4);
        
        GridBagConstraints cons5 = new GridBagConstraints();
        JLabel labelColor = new JLabel("Color: ");
        cons5.gridx = 0;
        cons5.gridy = 3;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.insets = new Insets(10, 5 ,5 ,5);
        panelBuscarAtributosTransporte.add(labelColor,cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        JTextField campoColor = new JTextField();
        cons6.gridwidth = 2;
        cons6.gridx = 0;
        cons6.gridy = 4;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(5, 5 ,10 ,5);
        panelBuscarAtributosTransporte.add(campoColor,cons6);
        
        GridBagConstraints cons7 = new GridBagConstraints();
        JLabel labelEstado = new JLabel("Estado: ");
        cons7.gridx = 0;
        cons7.gridy = 7;
        cons7.fill = GridBagConstraints.HORIZONTAL;
        cons7.insets = new Insets(10, 5 ,5 ,5);
        panelBuscarAtributosTransporte.add(labelEstado,cons7);

        GridBagConstraints cons8 = new GridBagConstraints();
        JComboBox<String> campoEstado = new JComboBox<>();
        cons8.gridwidth = 2;
        cons8.gridx = 0;
        cons8.gridy = 8;
        cons8.fill = GridBagConstraints.HORIZONTAL;
        cons8.insets = new Insets(5, 5 ,10 ,5);
        campoEstado.addItem("Ninguno"); //
        campoEstado.addItem("Activa");
        campoEstado.addItem("No Activa");
        panelBuscarAtributosTransporte.add(campoEstado,cons8);
        

        GridBagConstraints cons11 = new GridBagConstraints();
        JButton botonBuscar = new JButton("Buscar");
        cons11.gridwidth = 2;
        cons11.gridx = 0;
        cons11.gridy = 9;
        cons11.fill = GridBagConstraints.HORIZONTAL;
        cons11.insets = new Insets(40,0,20,0);
        panelBuscarAtributosTransporte.add(botonBuscar,cons11);  
        
        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridx = 0;
        cons12.gridy = 10;
        cons12.fill = GridBagConstraints.BOTH;
        cons12.insets = new Insets(30,0,60,0);
        panelBuscarAtributosTransporte.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarTransporte.getInstance().getPanelRegistrarTransporte()));
        botonBuscar.addActionListener(e -> {
            String[] listaCampos = {campoNombre.getText(), campoColor.getText(), campoEstado.getSelectedItem().toString()};
            InterfazFrame.setPanel(BuscarLineasTabla.getInstance(listaCampos).getPanelBuscarLineasTabla(listaCampos));});
    }
    
}
