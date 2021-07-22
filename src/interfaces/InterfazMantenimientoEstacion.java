package interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazMantenimientoEstacion {

    private static InterfazMantenimientoEstacion singleton;
    private JPanel panelMantenimiento;

    public JPanel getPanelMantenimiento() {
        return panelMantenimiento;
    }

    public static InterfazMantenimientoEstacion getInstance(){
        if(singleton == null){
            singleton= new InterfazMantenimientoEstacion();

        }
        return singleton;
    }

    private InterfazMantenimientoEstacion() {
        panelMantenimiento= new JPanel();
        JLabel etiqueta= new JLabel("entro a la interfaz de Mantenimiento de Estacion");

        panelMantenimiento.add(etiqueta);

        JButton boton = new JButton("Atras");
        panelMantenimiento.add(boton);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal());
            }
        });

    }
}
