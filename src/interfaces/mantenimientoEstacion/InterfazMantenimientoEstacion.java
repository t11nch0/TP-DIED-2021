package interfaces.mantenimientoEstacion;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;

public class InterfazMantenimientoEstacion {

    private static InterfazMantenimientoEstacion singleton;
    private final JPanel panelMantenimiento;

    public JPanel getPanelMantenimiento() {
        return panelMantenimiento;
    }

    public static InterfazMantenimientoEstacion getInstance(){
        if(singleton == null){
            singleton = new InterfazMantenimientoEstacion();

        }
        return singleton;
    }

    private InterfazMantenimientoEstacion() {
        panelMantenimiento = new JPanel();
        JLabel etiqueta = new JLabel("entro a la interfaz de Mantenimiento de Estacion");

        panelMantenimiento.add(etiqueta);

        JButton boton = new JButton("Atras");
        panelMantenimiento.add(boton);

        boton.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));

    }
}
