package interfaces.registrarTransporte;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;
import java.awt.*;

public class InterfazRegistrarTransporte {

    private static InterfazRegistrarTransporte singleton;
    private final JPanel panelRegistrarTransporte;

    public JPanel getPanelRegistrarTransporte() {
        return panelRegistrarTransporte;
    }

    public static InterfazRegistrarTransporte getInstance(){
        if(singleton == null){
            singleton = new InterfazRegistrarTransporte();
        }
        return singleton;
    }

    private InterfazRegistrarTransporte() {
        panelRegistrarTransporte = new JPanel(new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("REGISTRO DE TRANSPORTE");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,20,0);
        panelRegistrarTransporte.add(nombreMenu, cons0);



        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 7;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(10,0,60,0);
        panelRegistrarTransporte.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));

    }
}
