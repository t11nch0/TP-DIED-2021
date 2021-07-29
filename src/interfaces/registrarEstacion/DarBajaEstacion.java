package interfaces.registrarEstacion;

import interfaces.InterfazFrame;

import javax.swing.*;
import java.awt.*;

public class DarBajaEstacion {

    private static DarBajaEstacion singleton;
    private final JPanel panelDarBajaEstacion;

    public JPanel getPanelDarBajaEstacion() {
        return panelDarBajaEstacion;
    }

    public static DarBajaEstacion getInstance(){
        if(singleton == null){
            singleton = new DarBajaEstacion();
        }
        return singleton;
    }

    private DarBajaEstacion() {
        panelDarBajaEstacion = new JPanel(new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("DAR BAJA ESTACION");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,40,0);
        panelDarBajaEstacion.add(nombreMenu, cons0);

        GridBagConstraints cons2 = new GridBagConstraints();
        String[] data = {"Estacion1", "Estacion2", "Estacion3", "Estacion4", "Estacion5", "Estacion6", "Estacion7", "Estacion8", "Estacion9", "Estacion10"};
        JList<String> campoLista = new JList<>(data);
        cons2.gridwidth = 2;
        cons2.gridheight = 6;
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.BOTH;
        //cons2.insets = new Insets(55,0,40,0);
        panelDarBajaEstacion.add(campoLista, cons2);
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
        JButton botonEditar = new JButton("Eliminar");
        cons11.gridwidth = 2;
        cons11.gridx = 0;
        cons11.gridy = 7;
        cons11.weighty = 0.1;
        cons11.fill = GridBagConstraints.HORIZONTAL;
        cons11.anchor = GridBagConstraints.NORTH;
        cons11.insets = new Insets(10,0,10,0);
        panelDarBajaEstacion.add(botonEditar,cons11);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 7;
        cons12.weighty = 0.1;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.anchor = GridBagConstraints.SOUTH;
        cons12.insets = new Insets(10,0,40,0);
        panelDarBajaEstacion.add(botonAtras,cons12);


        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarEstacion.getInstance().getPanelRegistroEstacion()));

    }
}
