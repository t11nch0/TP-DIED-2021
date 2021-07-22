package interfaces;

import interfaces.informacionEstacion.InterfazInformacionEstacion;
import interfaces.informacionTransporte.InterfazInformacionTransporte;
import interfaces.mantenimientoEstacion.InterfazMantenimientoEstacion;
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

    public static InterfazPrincipal getInstance(){
        if(singleton == null){
            singleton= new InterfazPrincipal();
        }
        return singleton;
    }

    private InterfazPrincipal() {
        panelMenuPrincipal= new JPanel();
        panelMenuPrincipal.setLayout(new GridBagLayout());

        /* Configuracion y ordenacion de los 6 botones en la interfaz inicial*/

        GridBagConstraints cons0= new GridBagConstraints();
        JLabel menuPrincipal = new JLabel("MENU PRINCIPAL");
        menuPrincipal.setFont(new Font("Dialog", Font.BOLD, 30));
        cons0.gridx= 0;
        cons0.gridy= 0;
        cons0.weighty= 0;
        cons0.fill= GridBagConstraints.BOTH;
        cons0.insets= new Insets(55,15,40,15);
        panelMenuPrincipal.add(menuPrincipal, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JButton boton1= new JButton("Venta de Boleto");
        cons1.gridx= 0;
        cons1.gridy= 1;
        cons1.weighty= 0.1;
        cons1.fill= GridBagConstraints.BOTH;
        cons1.insets= new Insets(15,15,15,15);
        panelMenuPrincipal.add(boton1,cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JButton boton2= new JButton("Información de Estaciones");
        cons2.gridx= 0;
        cons2.gridy= 2;
        cons2.weighty= 0.1;
        cons2.fill= GridBagConstraints.BOTH;
        cons2.insets= new Insets(15,15,15,15);
        panelMenuPrincipal.add(boton2,cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JButton boton3= new JButton("Información de Transporte");
        cons3.gridx= 0;
        cons3.gridy= 3;
        cons3.weighty= 0.1;
        cons3.fill= GridBagConstraints.BOTH;
        cons3.insets= new Insets(15,15,15,15);
        panelMenuPrincipal.add(boton3,cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JButton boton4= new JButton("Registrar Estaciones");
        cons4.gridx= 0;
        cons4.gridy= 4;
        cons4.weighty= 0.1;
        cons4.fill= GridBagConstraints.BOTH;
        cons4.insets= new Insets(15,15,15,15);
        panelMenuPrincipal.add(boton4,cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JButton boton5= new JButton("Registrar Trayectos");
        cons5.gridx= 0;
        cons5.gridy= 5;
        cons5.weighty= 0.1;
        cons5.fill= GridBagConstraints.BOTH;
        cons5.insets= new Insets(15,15,15,15);
        panelMenuPrincipal.add(boton5,cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        JButton boton6= new JButton("Mantenimiento de Estación");
        cons6.gridx= 0;
        cons6.gridy= 6;
        cons6.weighty= 0.1;
        cons6.fill= GridBagConstraints.BOTH;
        cons6.insets= new Insets(15,15,75,15);
        panelMenuPrincipal.add(boton6,cons6);

        /* Listeners de los botones*/

        boton1.addActionListener(e -> InterfazFrame.setPanel(InterfazVentaBoleto.getInstance().getPanelVenta()));

        boton2.addActionListener(e -> InterfazFrame.setPanel(InterfazInformacionEstacion.getInstance().getPanelInformacionEstacion()));

        boton3.addActionListener(e -> InterfazFrame.setPanel(InterfazInformacionTransporte.getInstance().getPanelInformacionTransporte()));

        boton4.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarEstacion.getInstance().getPanelRegistroEstaciones()));

        boton5.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarTrayecto.getInstance().getPanelRegistroTrayecto()));

        boton6.addActionListener(e -> InterfazFrame.setPanel(InterfazMantenimientoEstacion.getInstance().getPanelMantenimiento()));
    }
}