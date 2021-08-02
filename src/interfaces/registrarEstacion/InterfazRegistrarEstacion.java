package interfaces.registrarEstacion;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;
import java.awt.*;

public class InterfazRegistrarEstacion {

    private static InterfazRegistrarEstacion singleton;
    private final JPanel panelRegistroEstacion;

    public JPanel getPanelRegistroEstacion() {
        return panelRegistroEstacion;
    }

    public static InterfazRegistrarEstacion getInstance(){
        if(singleton == null){
            singleton = new InterfazRegistrarEstacion();
        }
        return singleton;
    }

    private InterfazRegistrarEstacion() {
        panelRegistroEstacion = new JPanel(new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("REGISTRO DE ESTACION");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.BOTH;
        cons0.insets = new Insets(30,15,30,15);
        panelRegistroEstacion.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JButton botonAlta = new JButton("Dar Alta");
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.BOTH;
        cons1.insets = new Insets(40,20,10,20);
        panelRegistroEstacion.add(botonAlta,cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JButton botonEdicion = new JButton("Editar");
        cons2.gridx = 0;
        cons2.gridy = 2;
        cons2.fill = GridBagConstraints.BOTH;
        cons2.insets = new Insets(10,20,10,20);
        panelRegistroEstacion.add(botonEdicion,cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JButton botonBaja = new JButton("Dar Baja");
        cons3.gridx = 0;
        cons3.gridy = 3;
        cons3.fill = GridBagConstraints.BOTH;
        cons3.insets = new Insets(10,20,10,20);
        panelRegistroEstacion.add(botonBaja,cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JButton botonAtributos = new JButton("Buscar Atributos");
        cons4.gridx = 0;
        cons4.gridy = 4;
        cons4.fill = GridBagConstraints.BOTH;
        cons4.insets = new Insets(10,20,10,20);
        panelRegistroEstacion.add(botonAtributos,cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons5.gridx = 0;
        cons5.gridy = 6;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.anchor = GridBagConstraints.PAGE_END;
        cons5.insets = new Insets(40,41,60,41);
        panelRegistroEstacion.add(botonAtras,cons5);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));

        botonAlta.addActionListener(e -> InterfazFrame.setPanel(DarAltaEstacion.getInstance().getPanelDarAltaEstacion()));

        botonBaja.addActionListener(e -> InterfazFrame.setPanel(DarBajaEstacion.getInstance().getPanelDarBajaEstacion()));

        botonEdicion.addActionListener(e -> InterfazFrame.setPanel(EditarEstacion.getInstance().getPanelEditarEstacion()));

        botonAtributos.addActionListener(e -> InterfazFrame.setPanel(BuscarAtributos.getInstance().getPanelBuscarAtributos()));

    }
}
