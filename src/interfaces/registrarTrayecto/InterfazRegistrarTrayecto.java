package interfaces.registrarTrayecto;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazRegistrarTrayecto {

    private static InterfazRegistrarTrayecto singleton;
    private final JPanel panelRegistroTrayecto;

    public JPanel getPanelRegistroTrayecto() {
        return panelRegistroTrayecto;
    }

    public static InterfazRegistrarTrayecto getInstance(){
        if(singleton == null){
            singleton= new InterfazRegistrarTrayecto();
        }
        return singleton;
    }

    private InterfazRegistrarTrayecto() {
        panelRegistroTrayecto = new JPanel();
        JLabel etiqueta= new JLabel("entro a la interfaz de Registro de Trayectos");

        panelRegistroTrayecto.add(etiqueta);

        JButton boton = new JButton("Atras");
        panelRegistroTrayecto.add(boton);

        boton.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));
    }
}
