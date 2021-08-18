package interfaces;

import interfaces.informacionEstacion.InterfazInformacionEstacion;
import interfaces.registrarTransporte.InterfazRegistrarTransporte;
import interfaces.registrarEstacion.InterfazRegistrarEstacion;
import interfaces.registrarTrayecto.InterfazRegistrarTrayecto;
import interfaces.ventaBoleto.InterfazVentaBoleto;

import javax.swing.*;
import java.awt.*;

public class InterfazPrincipal {

    private static InterfazPrincipal singleton;
    private final JPanel panelMenuPrincipal;

    public JPanel getPanelMenuPrincipal() {
        return panelMenuPrincipal;
    }

    public static InterfazPrincipal getInstance() {
        if (singleton == null) {
            singleton = new InterfazPrincipal();
        }
        return singleton;
    }

    private InterfazPrincipal() {
        panelMenuPrincipal = new JPanel(new GridBagLayout());

        /* Configuracion y ordenacion de los 6 botones en la interfaz inicial*/

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel menuPrincipal = new JLabel("MENU PRINCIPAL");
        menuPrincipal.setFont(new Font("Dialog", Font.BOLD, 30));
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.weighty = 0;
        cons0.fill = GridBagConstraints.BOTH;
        cons0.insets = new Insets(55, 15, 40, 15);
        panelMenuPrincipal.add(menuPrincipal, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JButton boton1 = new JButton("Venta de Boleto");
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.BOTH;
        cons1.insets = new Insets(15, 15, 15, 15);
        panelMenuPrincipal.add(boton1, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JButton boton2 = new JButton("Informacion de Estaciones");
        cons2.gridx = 0;
        cons2.gridy = 2;
        cons2.fill = GridBagConstraints.BOTH;
        cons2.insets = new Insets(15, 15, 15, 15);
        panelMenuPrincipal.add(boton2, cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JButton boton3 = new JButton("Estaciones");
        cons3.gridx = 0;
        cons3.gridy = 3;
        cons3.fill = GridBagConstraints.BOTH;
        cons3.insets = new Insets(15, 15, 15, 15);
        panelMenuPrincipal.add(boton3, cons3);

        GridBagConstraints cons5 = new GridBagConstraints();
        JButton boton5 = new JButton("Transportes");
        cons5.gridx = 0;
        cons5.gridy = 5;
        cons5.fill = GridBagConstraints.BOTH;
        cons5.insets = new Insets(15, 15, 15, 15);
        panelMenuPrincipal.add(boton5, cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        JButton boton6 = new JButton("Trayectos");
        cons6.gridx = 0;
        cons6.gridy = 6;
        cons6.fill = GridBagConstraints.BOTH;
        cons6.insets = new Insets(15, 15, 15, 15);
        panelMenuPrincipal.add(boton6, cons6);

        GridBagConstraints cons7 = new GridBagConstraints();
        JButton boton7 = new JButton("Salir");
        cons7.gridx = 0;
        cons7.gridy = 7;
        cons7.fill = GridBagConstraints.BOTH;
        cons7.insets = new Insets(40, 15, 75, 15);
        panelMenuPrincipal.add(boton7, cons7);

        /* Listeners de los botones*/

        boton1.addActionListener(e -> InterfazFrame.setPanel(InterfazVentaBoleto.getInstance().getPanelVenta()));

        boton2.addActionListener(e -> InterfazFrame.setPanel(InterfazInformacionEstacion.getInstance().getPanelInformacionEstacion()));

        boton3.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarEstacion.getInstance().getPanelRegistroEstacion()));

        boton5.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarTransporte.getInstance().getPanelRegistrarTransporte()));

        boton6.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarTrayecto.getInstance().getPanelRegistroTrayecto()));

        boton7.addActionListener(e -> {
            Container frame = boton7.getParent();
            do
                frame = frame.getParent();
            while (!(frame instanceof JFrame));
            ((JFrame) frame).dispose();
        });

    }
}