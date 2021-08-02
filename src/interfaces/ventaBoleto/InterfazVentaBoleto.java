package interfaces.ventaBoleto;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;
import java.awt.*;

public class InterfazVentaBoleto{

    private static InterfazVentaBoleto singleton;
    private final JPanel panelVenta;

    public JPanel getPanelVenta() {
        return panelVenta;
    }

    public static InterfazVentaBoleto getInstance(){
        if(singleton == null){
            singleton= new InterfazVentaBoleto();
        }
        return singleton;
    }

    private InterfazVentaBoleto() {
        panelVenta= new JPanel(new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("VENTA DE BOLETO");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,20,0);
        panelVenta.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel nombreOrigen = new JLabel("Estacion de Origen: ");
        nombreOrigen.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons1.gridwidth = 2;
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(10,0,35,0);
        panelVenta.add(nombreOrigen, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JComboBox<String> campoEstacionOrigen = new JComboBox<>();
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(35, 5 ,0 ,5);
        campoEstacionOrigen.addItem("Seleccionar estacion...");
        campoEstacionOrigen.addItem("...");
        panelVenta.add(campoEstacionOrigen,cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JLabel nombreDestino = new JLabel("Estacion de Destino: ");
        nombreDestino.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons3.gridwidth = 2;
        cons3.gridx = 0;
        cons3.gridy = 2;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(10,0,35,0);
        panelVenta.add(nombreDestino, cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JComboBox<String> campoEstacionDestino = new JComboBox<>();
        cons4.gridwidth = 2;
        cons4.gridx = 0;
        cons4.gridy = 2;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(35, 5 ,0 ,5);
        campoEstacionDestino.addItem("Seleccionar estacion...");
        campoEstacionDestino.addItem("...");
        panelVenta.add(campoEstacionDestino,cons4);

        String[] data = {"Estacion1", "Estacion2", "Estacion3", "Estacion4", "Estacion5", "Estacion6", "Estacion7", "Estacion8", "Estacion9", "Estacion10"};
        GridBagConstraints cons5 = new GridBagConstraints();
        JTabbedPane paneles = new JTabbedPane();
        cons5.ipadx = 150;
        cons5.gridx = 0;
        cons5.gridy = 3;
        cons5.fill = GridBagConstraints.BOTH;
        cons5.insets = new Insets(10,0,20,0);
        panelVenta.add(paneles, cons5);

        paneles.addTab("Mas rapido", new JList<>(data));
        paneles.addTab("Menor Distancia", new JList<>(data));
        paneles.addTab("Mas Barato", new JList<>(data));

        GridBagConstraints cons6 = new GridBagConstraints();
        JButton botonComprar = new JButton("Comprar");
        cons6.gridwidth = 2;
        cons6.gridx = 0;
        cons6.gridy = 6;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(10,0,30,0);
        panelVenta.add(botonComprar,cons6);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 7;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(30,0,60,0);
        panelVenta.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));

    }
}
