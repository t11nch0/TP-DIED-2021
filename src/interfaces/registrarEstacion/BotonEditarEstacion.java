package interfaces.registrarEstacion;

import interfaces.InterfazFrame;

import javax.swing.*;
import java.awt.*;

public class BotonEditarEstacion {

    private static BotonEditarEstacion singleton;
    private final JPanel panelBotonEditarEstacion;

    public JPanel getPanelBotonEditarEstacion() {
        return panelBotonEditarEstacion;
    }

    public static BotonEditarEstacion getInstance(){
        if(singleton == null){
            singleton = new BotonEditarEstacion();
        }
        return singleton;
    }

    private BotonEditarEstacion() {
        panelBotonEditarEstacion = new JPanel(new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("EDITAR ESTACION");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,20,0);
        panelBotonEditarEstacion.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel labelId = new JLabel("ID: ");
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(10, 5 ,5 ,5);
        panelBotonEditarEstacion.add(labelId,cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JTextField campoId = new JTextField();
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 2;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(5, 5 ,10 ,5);
        panelBotonEditarEstacion.add(campoId,cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JLabel labelNombre = new JLabel("Nombre: ");
        cons3.gridx = 0;
        cons3.gridy = 3;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(10, 5 ,5 ,5);
        panelBotonEditarEstacion.add(labelNombre,cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JTextField campoNombre = new JTextField();
        cons4.gridwidth = 2;
        cons4.gridx = 0;
        cons4.gridy = 4;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(5, 5 ,10 ,5);
        panelBotonEditarEstacion.add(campoNombre,cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JLabel labelHApertura = new JLabel("Horario Apertura: ");
        cons5.gridx = 0;
        cons5.gridy = 5;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.insets = new Insets(10, 5 ,5 ,5);
        panelBotonEditarEstacion.add(labelHApertura,cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        JTextField campoHApertura = new JTextField();
        cons6.gridwidth = 2;
        cons6.gridx = 0;
        cons6.gridy = 6;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(5, 5 ,10 ,5);
        panelBotonEditarEstacion.add(campoHApertura,cons6);

        GridBagConstraints cons7 = new GridBagConstraints();
        JLabel labelHCierre = new JLabel("Horario Cierre: ");
        cons7.gridx = 0;
        cons7.gridy = 7;
        cons7.fill = GridBagConstraints.HORIZONTAL;
        cons7.insets = new Insets(10, 5 ,5 ,5);
        panelBotonEditarEstacion.add(labelHCierre,cons7);

        GridBagConstraints cons8 = new GridBagConstraints();
        JTextField campoHCierre = new JTextField();
        cons8.gridwidth = 2;
        cons8.gridx = 0;
        cons8.gridy = 8;
        cons8.fill = GridBagConstraints.HORIZONTAL;
        cons8.insets = new Insets(5, 5 ,10 ,5);
        panelBotonEditarEstacion.add(campoHCierre,cons8);

        GridBagConstraints cons9 = new GridBagConstraints();
        JLabel labelEstado = new JLabel("Estado: ");
        cons9.gridx = 0;
        cons9.gridy = 9;
        cons9.fill = GridBagConstraints.HORIZONTAL;
        cons9.insets = new Insets(10, 5 ,5 ,5);
        panelBotonEditarEstacion.add(labelEstado,cons9);

        GridBagConstraints cons10 = new GridBagConstraints();
        JComboBox<String> campoEstado = new JComboBox<>();
        cons10.gridwidth = 2;
        cons10.gridx = 0;
        cons10.gridy = 10;
        cons10.fill = GridBagConstraints.HORIZONTAL;
        cons10.insets = new Insets(5, 5 ,10 ,5);
        campoEstado.addItem("Operativa");
        campoEstado.addItem("Mantenimiento");
        panelBotonEditarEstacion.add(campoEstado,cons10);

        GridBagConstraints cons11 = new GridBagConstraints();
        JButton botonAceptar = new JButton("Aceptar");
        cons11.gridwidth = 2;
        cons11.gridx = 0;
        cons11.gridy = 11;
        cons11.fill = GridBagConstraints.HORIZONTAL;
        cons11.insets = new Insets(40,0,30,0);
        panelBotonEditarEstacion.add(botonAceptar,cons11);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 12;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(30,0,60,0);
        panelBotonEditarEstacion.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(EditarEstacion.getInstance().getPanelEditarEstacion()));

    }
}
