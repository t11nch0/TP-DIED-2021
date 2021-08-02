package interfaces.registrarTransporte;

import interfaces.InterfazFrame;
import interfaces.registrarEstacion.InterfazRegistrarEstacion;

import javax.swing.*;
import java.awt.*;

public class DarBajaTransporte {

    private static DarBajaTransporte singleton;
    private final JPanel panelDarBajaTransporte;

    public JPanel getPanelDarBajaTransporte() {
        return panelDarBajaTransporte;
    }

    public static DarBajaTransporte getInstance(){
        if(singleton == null){
            singleton = new DarBajaTransporte();
        }
        return singleton;
    }

    private DarBajaTransporte() {
        panelDarBajaTransporte = new JPanel(new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("DAR BAJA TRANSPORTE");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,40,0);
        panelDarBajaTransporte.add(nombreMenu, cons0);

        GridBagConstraints cons2 = new GridBagConstraints();
        String[] data = {"Estacion1", "Estacion2", "Estacion3", "Estacion4", "Estacion5", "Estacion6", "Estacion7", "Estacion8", "Estacion9", "Estacion10"};
        JList<String> campoLista = new JList<>(data);
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.BOTH;
        cons2.insets = new Insets(10,0,40,0);
        panelDarBajaTransporte.add(campoLista, cons2);
/*
        GridBagConstraints cons3 = new GridBagConstraints();
        JScrollBar scrollLista = new JScrollBar(JScrollBar.VERTICAL, 1, 10, 0, 100);
        cons3.gridwidth = 0;
        cons3.gridheight = 4;
        cons3.gridx = 2;
        cons3.gridy = 3;
        cons3.fill = GridBagConstraints.BOTH;
        cons3.anchor = GridBagConstraints.WEST;
        //cons3.insets = new Insets(55,0,40,0);
        panelDarBajaEstacion.add(scrollLista,cons3);
 */
        GridBagConstraints cons11 = new GridBagConstraints();
        JButton botonEliminar = new JButton("Eliminar");
        cons11.gridwidth = 2;
        cons11.gridx = 0;
        cons11.gridy = 2;
        cons11.fill = GridBagConstraints.HORIZONTAL;
        cons11.insets = new Insets(10,0,30,0);
        panelDarBajaTransporte.add(botonEliminar,cons11);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 3;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(30,0,60,0);
        panelDarBajaTransporte.add(botonAtras,cons12);


        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarTransporte.getInstance().getPanelRegistrarTransporte()));

    }
}
