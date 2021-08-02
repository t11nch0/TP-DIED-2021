package interfaces.registrarTransporte;

import interfaces.InterfazFrame;
import interfaces.registrarEstacion.InterfazRegistrarEstacion;

import javax.swing.*;
import java.awt.*;

public class DarAltaTransporte {

    private static DarAltaTransporte singleton;
    private final JPanel panelDarAltaTransporte;

    public JPanel getPanelDarAltaTransporte() {
        return panelDarAltaTransporte;
    }

    public static DarAltaTransporte getInstance(){
        if(singleton == null){
            singleton = new DarAltaTransporte();
        }
        return singleton;
    }

    private DarAltaTransporte() {
        panelDarAltaTransporte = new JPanel(new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("ALTA DE TRANSPORTE");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,20,0);
        panelDarAltaTransporte.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel labelNombre = new JLabel("Nombre: ");
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(10, 5 ,5 ,5);
        panelDarAltaTransporte.add(labelNombre,cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JTextField campoId = new JTextField();
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 2;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(5, 5 ,10 ,5);
        panelDarAltaTransporte.add(campoId,cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JLabel labelColor = new JLabel("Color: ");
        cons3.gridx = 0;
        cons3.gridy = 3;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(10, 5 ,5 ,5);
        panelDarAltaTransporte.add(labelColor,cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JComboBox<String> campoColor = new JComboBox<>();
        cons4.gridwidth = 2;
        cons4.gridx = 0;
        cons4.gridy = 4;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(5, 5 ,10 ,5);
        campoColor.addItem("ROJO");
        campoColor.addItem("AZUL");
        campoColor.addItem("VERDE");
        campoColor.addItem("AMARILLO");
        campoColor.addItem("NARANJA");
        campoColor.addItem("CELESTE");
        campoColor.addItem("VIOLETA");
        panelDarAltaTransporte.add(campoColor,cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JLabel labelEstado = new JLabel("Estado: ");
        cons5.gridx = 0;
        cons5.gridy = 5;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.insets = new Insets(10, 5 ,5 ,5);
        panelDarAltaTransporte.add(labelEstado,cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        JComboBox<String> campoEstado = new JComboBox<>();
        cons6.gridwidth = 2;
        cons6.gridx = 0;
        cons6.gridy = 6;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(5, 5 ,10 ,5);
        campoEstado.addItem("Activa");
        campoEstado.addItem("No Activa");
        panelDarAltaTransporte.add(campoEstado,cons6);

        GridBagConstraints cons7 = new GridBagConstraints();
        JButton botonAceptar = new JButton("Aceptar");
        cons7.gridwidth = 2;
        cons7.gridx = 0;
        cons7.gridy = 7;
        cons7.fill = GridBagConstraints.HORIZONTAL;
        cons7.insets = new Insets(40,0,30,0);
        panelDarAltaTransporte.add(botonAceptar,cons7);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 12;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(30,0,60,0);
        panelDarAltaTransporte.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarTransporte.getInstance().getPanelRegistrarTransporte()));

    }
}
