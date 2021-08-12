package interfaces.registrarEstacion;

import interfaces.InterfazFrame;

import javax.swing.*;


import gestores.GestorEstacion;
import dominio.EstacionDeTransbordoMultimodal;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.List;

public class BuscarAtributosEstacion {

    private static BuscarAtributosEstacion singleton;
    private final JPanel panelBuscarAtributosEstacion;
    private ModeloTablaEstaciones modeloTablaEstaciones;
    private JTable tablaEstaciones;
    private GestorEstacion gestorEstacion;
    private List<EstacionDeTransbordoMultimodal> estaciones;

    public JPanel getPanelBuscarAtributosEstacion() {
        return panelBuscarAtributosEstacion;
    }

    public static BuscarAtributosEstacion getInstance(){
        if(singleton == null){
            singleton = new BuscarAtributosEstacion();
        }
        return singleton;
    }

    private BuscarAtributosEstacion() {
        panelBuscarAtributosEstacion = new JPanel(new GridBagLayout());
        this.gestorEstacion = new GestorEstacion();
        this.estaciones = gestorEstacion.listarTodas();

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("BUSCAR ESTACION POR ATRIBUTOS");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.ipady = 100;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
      //  cons0.insets = new Insets(55,0,20,0);
        cons0.insets = new Insets(40,0,10,0);
        panelBuscarAtributosEstacion.add(nombreMenu, cons0);
        
        GridBagConstraints cons3 = new GridBagConstraints();
        JLabel labelNombre = new JLabel("Nombre: ");
        cons3.gridx = 0;
        cons3.gridy = 1;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(10, 5 ,5 ,5);
        panelBuscarAtributosEstacion.add(labelNombre,cons3);

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
        panelBuscarAtributosEstacion.add(campoNombre,cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JLabel labelHApertura = new JLabel("Horario Apertura: ");
        cons5.gridx = 0;
        cons5.gridy = 3;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.insets = new Insets(10, 5 ,5 ,5);
        panelBuscarAtributosEstacion.add(labelHApertura,cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        JTextField campoHApertura = new JTextField();
        cons6.gridwidth = 2;
        cons6.gridx = 0;
        cons6.gridy = 4;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(5, 5 ,10 ,5);
        panelBuscarAtributosEstacion.add(campoHApertura,cons6);

        GridBagConstraints cons7 = new GridBagConstraints();
        JLabel labelHCierre = new JLabel("Horario Cierre: ");
        cons7.gridx = 0;
        cons7.gridy = 5;
        cons7.fill = GridBagConstraints.HORIZONTAL;
        cons7.insets = new Insets(10, 5 ,5 ,5);
        panelBuscarAtributosEstacion.add(labelHCierre,cons7);

        GridBagConstraints cons8 = new GridBagConstraints();
        JTextField campoHCierre = new JTextField();
        cons8.gridwidth = 2;
        cons8.gridx = 0;
        cons8.gridy = 6;
        cons8.fill = GridBagConstraints.HORIZONTAL;
        cons8.insets = new Insets(5, 5 ,10 ,5);
        panelBuscarAtributosEstacion.add(campoHCierre,cons8);

        GridBagConstraints cons9 = new GridBagConstraints();
        JLabel labelEstado = new JLabel("Estado: ");
        cons9.gridx = 0;
        cons9.gridy = 7;
        cons9.fill = GridBagConstraints.HORIZONTAL;
        cons9.insets = new Insets(10, 5 ,5 ,5);
        panelBuscarAtributosEstacion.add(labelEstado,cons9);

        GridBagConstraints cons10 = new GridBagConstraints();
        JComboBox<String> campoEstado = new JComboBox<>();
        cons10.gridwidth = 2;
        cons10.gridx = 0;
        cons10.gridy = 8;
        cons10.fill = GridBagConstraints.HORIZONTAL;
        cons10.insets = new Insets(5, 5 ,10 ,5);
        campoEstado.addItem("Ninguno"); //
        campoEstado.addItem("Operativa");
        campoEstado.addItem("Mantenimiento");
        panelBuscarAtributosEstacion.add(campoEstado,cons10);
              
        
        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonBuscar = new JButton("Buscar");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 9;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(40,0,20,0);
        panelBuscarAtributosEstacion.add(botonBuscar,cons12);       
        
        GridBagConstraints cons13 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons13.gridwidth = 2;
        cons13.gridx = 0;
        cons13.gridy = 10;
        cons13.fill = GridBagConstraints.HORIZONTAL;
        cons13.insets = new Insets(30,0,60,0);
        panelBuscarAtributosEstacion.add(botonAtras,cons13);
            

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarEstacion.getInstance().getPanelRegistroEstacion()));
        botonBuscar.addActionListener(e -> {
            String[] listaCampos = {campoNombre.getText(), campoHApertura.getText(), campoHCierre.getText(), campoEstado.getSelectedItem().toString()};
            InterfazFrame.setPanel(BuscarEstacionesTabla.getInstance(listaCampos).getPanelBuscarEstacionesTabla(listaCampos));});
    }
}
