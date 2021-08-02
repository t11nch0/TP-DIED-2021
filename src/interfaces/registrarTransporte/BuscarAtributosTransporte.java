package interfaces.registrarTransporte;

import interfaces.InterfazFrame;
import interfaces.registrarEstacion.InterfazRegistrarEstacion;

import javax.swing.*;
import java.awt.*;

public class BuscarAtributosTransporte {

    private static BuscarAtributosTransporte singleton;
    private final JPanel panelBuscarAtributosTransporte;

    public JPanel getPanelBuscarAtributosTransporte() {
        return panelBuscarAtributosTransporte;
    }

    public static BuscarAtributosTransporte getInstance(){
        if(singleton == null){
            singleton = new BuscarAtributosTransporte();
        }
        return singleton;
    }

    private BuscarAtributosTransporte() {
        panelBuscarAtributosTransporte = new JPanel(new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("BUSCAR ATRIBUTOS DE TRANSPORTE");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.ipady = 100;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.BOTH;
        cons0.insets = new Insets(40,0,10,0);
        panelBuscarAtributosTransporte.add(nombreMenu, cons0);

        String[] data = {"Estacion1", "Estacion2", "Estacion3", "Estacion4", "Estacion5", "Estacion6", "Estacion7", "Estacion8", "Estacion9", "Estacion10"};
        GridBagConstraints cons1 = new GridBagConstraints();
        JTabbedPane paneles = new JTabbedPane();
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.BOTH;
        cons1.insets = new Insets(10,0,30,0);
        panelBuscarAtributosTransporte.add(paneles, cons1);

        paneles.addTab("Nombre", new JList<>(data));
        paneles.addTab("Color", new JList<>(data));
        paneles.addTab("Estado", new JList<>(data));


        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridx = 0;
        cons12.gridy = 3;
        cons12.fill = GridBagConstraints.BOTH;
        cons12.insets = new Insets(30,0,60,0);
        panelBuscarAtributosTransporte.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarTransporte.getInstance().getPanelRegistrarTransporte()));

    }
}
