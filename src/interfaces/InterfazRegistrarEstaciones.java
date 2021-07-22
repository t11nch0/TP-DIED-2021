package interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazRegistrarEstaciones {

    private static InterfazRegistrarEstaciones singleton;
    private JPanel panelRegistroEstaciones;

    public JPanel getPanelRegistroEstaciones() {
        return panelRegistroEstaciones;
    }

    public static InterfazRegistrarEstaciones getInstance(){
        if(singleton == null){
            singleton= new InterfazRegistrarEstaciones();
        }
        return singleton;
    }

    private InterfazRegistrarEstaciones() {
        panelRegistroEstaciones = new JPanel();
        JLabel etiqueta= new JLabel("entro a la interfaz de Registro de Estaciones");

        panelRegistroEstaciones.add(etiqueta);

        JButton boton = new JButton("Atras");
        panelRegistroEstaciones.add(boton);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal());
            }
        });

    }

}
