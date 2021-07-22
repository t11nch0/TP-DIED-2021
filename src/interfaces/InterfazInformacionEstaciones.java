package interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazInformacionEstaciones {

    private static InterfazInformacionEstaciones singleton;
    private JPanel panelInformacionEstacion;

    public JPanel getPanelInformacionEstacion() {
        return panelInformacionEstacion;
    }

    public static InterfazInformacionEstaciones getInstance(){
        if(singleton == null){
            singleton= new InterfazInformacionEstaciones();
        }
        return singleton;
    }

    private InterfazInformacionEstaciones() {
        panelInformacionEstacion = new JPanel();
        JLabel etiqueta= new JLabel("entro a la interfaz de Informacion de estaciones");

        panelInformacionEstacion.add(etiqueta);

        JButton boton = new JButton("Atras");
        panelInformacionEstacion.add(boton);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal());
            }
        });

    }
}
