package interfaces.registrarTransporte;

import interfaces.InterfazFrame;
import javax.swing.*;
import dominio.LineaTransporte;
import gestores.GestorLineaTransporte;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

public class BuscarAtributosTransporte {

    private static BuscarAtributosTransporte singleton;
    private final JPanel panelBuscarAtributosTransporte;

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
        GestorLineaTransporte gestorLinea = new GestorLineaTransporte();
        List<LineaTransporte> lineas = gestorLinea.listarTodas();

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu1 = new JLabel("BUSCAR TRANSPORTE");
        nombreMenu1.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.ipady = 100;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(20, 0, 20, 0);
        panelBuscarAtributosTransporte.add(nombreMenu1, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel nombreMenu2 = new JLabel("POR ATRIBUTOS");
        nombreMenu2.setFont(new Font("Dialog", Font.BOLD, 25));
        cons1.gridwidth = 2;
        cons1.gridx = 0;
        cons1.gridy = 0;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(75, 40, 10, 0);
        panelBuscarAtributosTransporte.add(nombreMenu2, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JLabel labelNombre = new JLabel("Nombre: ");
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(10, 5 ,5 ,5);
        panelBuscarAtributosTransporte.add(labelNombre,cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JTextField campoNombre = new JTextField();
        cons3.gridwidth = 2;
        cons3.gridx = 0;
        cons3.gridy = 2;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(5, 5 ,10 ,5);
        panelBuscarAtributosTransporte.add(campoNombre,cons3);

        campoNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(campoNombre.getText().length()>29) {
                    e.consume();
                }
            }
        });
        
        GridBagConstraints cons4 = new GridBagConstraints();
        JLabel labelColor = new JLabel("Color: ");
        cons4.gridx = 0;
        cons4.gridy = 3;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(10, 5 ,5 ,5);
        panelBuscarAtributosTransporte.add(labelColor,cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JComboBox<String> campoColor = new JComboBox<>();
        cons5.gridwidth = 2;
        cons5.gridx = 0;
        cons5.gridy = 4;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.insets = new Insets(5, 5 ,10 ,5);
        campoColor.addItem("Seleccionar color...");
        campoColor.addItem("ROJO");
        campoColor.addItem("AZUL");
        campoColor.addItem("VERDE");
        campoColor.addItem("AMARILLO");
        campoColor.addItem("NARANJA");
        campoColor.addItem("CELESTE");
        campoColor.addItem("VIOLETA");
        panelBuscarAtributosTransporte.add(campoColor,cons5);
        
        GridBagConstraints cons6 = new GridBagConstraints();
        JLabel labelEstado = new JLabel("Estado: ");
        cons6.gridx = 0;
        cons6.gridy = 7;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(10, 5 ,5 ,5);
        panelBuscarAtributosTransporte.add(labelEstado,cons6);

        GridBagConstraints cons7 = new GridBagConstraints();
        JComboBox<String> campoEstado = new JComboBox<>();
        cons7.gridwidth = 2;
        cons7.gridx = 0;
        cons7.gridy = 8;
        cons7.fill = GridBagConstraints.HORIZONTAL;
        cons7.insets = new Insets(5, 5 ,10 ,5);
        campoEstado.addItem("Seleccionar estado...");
        campoEstado.addItem("ACTIVA");
        campoEstado.addItem("INACTIVA");
        panelBuscarAtributosTransporte.add(campoEstado,cons7);

        GridBagConstraints cons8 = new GridBagConstraints();
        JButton botonBuscar = new JButton("Buscar");
        cons8.gridwidth = 2;
        cons8.gridx = 0;
        cons8.gridy = 9;
        cons8.fill = GridBagConstraints.HORIZONTAL;
        cons8.insets = new Insets(40,0,20,0);
        panelBuscarAtributosTransporte.add(botonBuscar,cons8);
        
        GridBagConstraints cons9 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons9.gridwidth = 2;
        cons9.gridx = 0;
        cons9.gridy = 10;
        cons9.fill = GridBagConstraints.BOTH;
        cons9.insets = new Insets(30,30,60,30);
        panelBuscarAtributosTransporte.add(botonAtras,cons9);

        botonAtras.addActionListener(e -> {InterfazFrame.setPanel(InterfazRegistrarTransporte.getInstance().getPanelRegistrarTransporte()); singleton = null;});

        botonBuscar.addActionListener(e -> {

            String[] listaCampos = {campoNombre.getText(), Objects.requireNonNull(campoColor.getSelectedItem()).toString(),
                                    Objects.requireNonNull(campoEstado.getSelectedItem()).toString()};


            InterfazFrame.setPanel(BuscarLineasTabla.getInstance(listaCampos).getPanelBuscarLineasTabla());}
        );
    }
    
}
