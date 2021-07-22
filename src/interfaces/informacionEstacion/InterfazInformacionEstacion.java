package interfaces.informacionEstacion;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazInformacionEstacion {

    private static InterfazInformacionEstacion singleton;
    private final JPanel panelInformacionEstacion;

    public JPanel getPanelInformacionEstacion() {
        return panelInformacionEstacion;
    }

    public static InterfazInformacionEstacion getInstance(){
        if(singleton == null){
            singleton= new InterfazInformacionEstacion();
        }
        return singleton;
    }

    private InterfazInformacionEstacion() {
        panelInformacionEstacion = new JPanel();
        JLabel etiqueta= new JLabel("entro a la interfaz de Informacion de estaciones");

        panelInformacionEstacion.add(etiqueta);

        JButton boton = new JButton("Atras");
        panelInformacionEstacion.add(boton);

        boton.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));

    }
}
