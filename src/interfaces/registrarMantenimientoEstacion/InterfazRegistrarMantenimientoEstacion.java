package interfaces.registrarMantenimientoEstacion;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

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
        panelRegistrarMantenimiento = new JPanel(new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("MANTENIMIENTO DE ESTACION");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,20,0);
        panelRegistrarMantenimiento.add(nombreMenu, cons0);



        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 7;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.anchor = GridBagConstraints.SOUTH;
        cons12.insets = new Insets(10,0,60,0);
        panelRegistrarMantenimiento.add(botonAtras,cons12);


        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));

    }
}
