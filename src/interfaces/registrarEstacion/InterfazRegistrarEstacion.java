package interfaces.registrarEstacion;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazRegistrarEstacion {

    private static InterfazRegistrarEstacion singleton;
    private final JPanel panelRegistroEstaciones;

    public JPanel getPanelRegistroEstaciones() {
        return panelRegistroEstaciones;
    }

    public static InterfazRegistrarEstacion getInstance(){
        if(singleton == null){
            singleton= new InterfazRegistrarEstacion();
        }
        return singleton;
    }

    private InterfazRegistrarEstacion() {
        panelRegistroEstaciones = new JPanel();
        JLabel etiqueta= new JLabel("entro a la interfaz de Registro de Estaciones");

        panelRegistroEstaciones.add(etiqueta);

        JButton boton = new JButton("Atras");
        panelRegistroEstaciones.add(boton);

        boton.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));

    }

}
