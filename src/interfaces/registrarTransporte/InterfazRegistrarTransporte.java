package interfaces.registrarTransporte;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;
import interfaces.registrarEstacion.BuscarAtributosEstacion;

import javax.swing.*;
import java.awt.*;

public class InterfazRegistrarTransporte {

    private static InterfazRegistrarTransporte singleton;
    private final JPanel panelRegistrarTransporte;

    public JPanel getPanelRegistrarTransporte() {
        return panelRegistrarTransporte;
    }

    public static InterfazRegistrarTransporte getInstance(){
        if(singleton == null){
            singleton = new InterfazRegistrarTransporte();
        }
        return singleton;
    }

    private InterfazRegistrarTransporte() {
        panelRegistrarTransporte = new JPanel(new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("REGISTRO DE TRANSPORTE");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        //cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,20,0);
        panelRegistrarTransporte.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JButton botonAlta = new JButton("Dar Alta");
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.BOTH;
        cons1.insets = new Insets(40,20,10,20);
        panelRegistrarTransporte.add(botonAlta,cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JButton botonEdicion = new JButton("Editar");
        cons2.gridx = 0;
        cons2.gridy = 2;
        cons2.fill = GridBagConstraints.BOTH;
        cons2.insets = new Insets(10,20,10,20);
        panelRegistrarTransporte.add(botonEdicion,cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JButton botonBaja = new JButton("Dar Baja");
        cons3.gridx = 0;
        cons3.gridy = 3;
        cons3.fill = GridBagConstraints.BOTH;
        cons3.insets = new Insets(10,20,10,20);
        panelRegistrarTransporte.add(botonBaja,cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JButton botonAtributos = new JButton("Buscar Atributos");
        cons4.gridx = 0;
        cons4.gridy = 4;
        cons4.fill = GridBagConstraints.BOTH;
        cons4.insets = new Insets(10,20,10,20);
        panelRegistrarTransporte.add(botonAtributos,cons4);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 7;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.anchor = GridBagConstraints.PAGE_END;
        cons12.insets = new Insets(40,41,60,41);
        panelRegistrarTransporte.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));

        botonAlta.addActionListener(e -> InterfazFrame.setPanel(DarAltaTransporte.getInstance().getPanelDarAltaTransporte()));

        botonBaja.addActionListener(e -> InterfazFrame.setPanel(DarBajaTransporte.getInstance().getPanelDarBajaTransporte()));

        botonEdicion.addActionListener(e -> InterfazFrame.setPanel(EditarTransporte.getInstance().getPanelEditarTransporte()));

        botonAtributos.addActionListener(e -> InterfazFrame.setPanel(BuscarAtributosTransporte.getInstance().getPanelBuscarAtributosTransporte()));

    }
}
