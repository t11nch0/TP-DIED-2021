package interfaces.informacionTransporte;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazInformacionTransporte {

    private static InterfazInformacionTransporte singleton;
    private final JPanel panelInformacionTransporte;

    public JPanel getPanelInformacionTransporte() {
        return panelInformacionTransporte;
    }

    public static InterfazInformacionTransporte getInstance(){
        if(singleton == null){
            singleton= new InterfazInformacionTransporte();
        }
        return singleton;
    }

    private InterfazInformacionTransporte() {
        panelInformacionTransporte = new JPanel();
        JLabel etiqueta= new JLabel("entro a la interfaz de Informacion de Transporte");

        panelInformacionTransporte.add(etiqueta);

        JButton boton = new JButton("Atras");
        panelInformacionTransporte.add(boton);

        boton.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));

    }
}
