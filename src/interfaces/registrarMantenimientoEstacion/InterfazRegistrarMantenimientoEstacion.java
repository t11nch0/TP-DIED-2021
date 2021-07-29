package interfaces.registrarMantenimientoEstacion;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;

public class InterfazRegistrarMantenimientoEstacion {

    private static InterfazRegistrarMantenimientoEstacion singleton;
    private final JPanel panelRegistrarMantenimiento;

    public JPanel getPanelRegistrarMantenimiento() {
        return panelRegistrarMantenimiento;
    }

    public static InterfazRegistrarMantenimientoEstacion getInstance(){
        if(singleton == null){
            singleton = new InterfazRegistrarMantenimientoEstacion();

        }
        return singleton;
    }

    private InterfazRegistrarMantenimientoEstacion() {
        panelRegistrarMantenimiento = new JPanel();
        JLabel etiqueta = new JLabel("entro a la interfaz de Registro de Mantenimiento de Estacion");

        panelRegistrarMantenimiento.add(etiqueta);

        JButton boton = new JButton("Atras");
        panelRegistrarMantenimiento.add(boton);

        boton.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));

    }
}
