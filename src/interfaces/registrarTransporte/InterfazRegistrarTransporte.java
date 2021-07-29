package interfaces.registrarTransporte;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;

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
        panelRegistrarTransporte = new JPanel();
        JLabel etiqueta = new JLabel("entro a la interfaz de Registro de Transporte");

        panelRegistrarTransporte.add(etiqueta);

        JButton boton = new JButton("Atras");
        panelRegistrarTransporte.add(boton);

        boton.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));

    }
}
