package interfaces.registrarTransporte;

import interfaces.InterfazFrame;

import javax.swing.*;
import java.awt.*;

public class BotonEditarTransporte {

    private static BotonEditarTransporte singleton;
    private final JPanel panelBotonEditarTransporte;

    public JPanel getPanelBotonEditarTransporte() {
        return panelBotonEditarTransporte;
    }

    public static BotonEditarTransporte getInstance(){
        if(singleton == null){
            singleton = new BotonEditarTransporte();
        }
        return singleton;
    }

    private BotonEditarTransporte() {
        panelBotonEditarTransporte = new JPanel(new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("EDITAR TRANSPORTE");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,20,0);
        panelBotonEditarTransporte.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel labelId = new JLabel("Nombre: ");
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(10, 5 ,5 ,5);
        panelBotonEditarTransporte.add(labelId,cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JTextField campoId = new JTextField();
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 2;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(5, 5 ,10 ,5);
        panelBotonEditarTransporte.add(campoId,cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JLabel labelNombre = new JLabel("Color: ");
        cons3.gridx = 0;
        cons3.gridy = 3;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(10, 5 ,5 ,5);
        panelBotonEditarTransporte.add(labelNombre,cons3);

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
        panelBotonEditarTransporte.add(campoColor,cons4);

        GridBagConstraints cons9 = new GridBagConstraints();
        JLabel labelEstado = new JLabel("Estado: ");
        cons9.gridx = 0;
        cons9.gridy = 9;
        cons9.fill = GridBagConstraints.HORIZONTAL;
        cons9.insets = new Insets(10, 5 ,5 ,5);
        panelBotonEditarTransporte.add(labelEstado,cons9);

        GridBagConstraints cons10 = new GridBagConstraints();
        JComboBox<String> campoEstado = new JComboBox<>();
        cons10.gridwidth = 2;
        cons10.gridx = 0;
        cons10.gridy = 10;
        cons10.fill = GridBagConstraints.HORIZONTAL;
        cons10.insets = new Insets(5, 5 ,10 ,5);
        campoEstado.addItem("Activa");
        campoEstado.addItem("No Activa");
        panelBotonEditarTransporte.add(campoEstado,cons10);

        GridBagConstraints cons11 = new GridBagConstraints();
        JButton botonAceptar = new JButton("Aceptar");
        cons11.gridwidth = 2;
        cons11.gridx = 0;
        cons11.gridy = 11;
        cons11.fill = GridBagConstraints.HORIZONTAL;
        cons11.insets = new Insets(40,0,30,0);
        panelBotonEditarTransporte.add(botonAceptar,cons11);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 12;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(30,0,60,0);
        panelBotonEditarTransporte.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(EditarTransporte.getInstance().getPanelEditarTransporte()));

    }
}
