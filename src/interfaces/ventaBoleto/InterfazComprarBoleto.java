package interfaces.ventaBoleto;

import dominio.EstacionDeTransbordoMultimodal;
import interfaces.InterfazFrame;

import javax.swing.*;
import java.awt.*;

public class InterfazComprarBoleto {

    private static InterfazComprarBoleto singleton;
    private final JPanel panelCompraBoleto;

    public JPanel getPanelCompraBoleto() { return panelCompraBoleto;}

    public static InterfazComprarBoleto getInstance(){
        if(singleton == null){
            singleton= new InterfazComprarBoleto();
        }
        return singleton;
    }

    private InterfazComprarBoleto() {

        panelCompraBoleto = new JPanel( new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("COMPRAR BOLETO");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,20,0);
        panelCompraBoleto.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel labelEmailCliente = new JLabel("Email del cliente: ");
        labelEmailCliente.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons1.gridwidth = 2;
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(10,0,35,0);
        panelCompraBoleto.add(labelEmailCliente, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JTextField campoEmailCliente = new JTextField();
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(35, 5 ,0 ,5);
        panelCompraBoleto.add(campoEmailCliente,cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JLabel labelNombreCliente = new JLabel("Nombre del cliente: ");
        labelNombreCliente.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons3.gridwidth = 2;
        cons3.gridx = 0;
        cons3.gridy = 2;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(10,0,35,0);
        panelCompraBoleto.add(labelNombreCliente, cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JTextField campoNombreCliente = new JTextField();
        cons4.gridwidth = 2;
        cons4.gridx = 0;
        cons4.gridy = 2;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(35, 5 ,0 ,5);
        panelCompraBoleto.add(campoNombreCliente,cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JLabel labelCostoBoleto = new JLabel("Costo del boleto: ");
        labelCostoBoleto.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons5.gridwidth = 2;
        cons5.gridx = 0;
        cons5.gridy = 3;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.insets = new Insets(20,0,35,0);
        panelCompraBoleto.add(labelCostoBoleto, cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        JLabel campoCostoBoleto = new JLabel("200.000 PESOS");
        cons6.gridwidth = 2;
        cons6.gridx = 0;
        cons6.gridy = 3;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(20, 128 ,30 ,0);
        panelCompraBoleto.add(campoCostoBoleto,cons6);

        GridBagConstraints cons7 = new GridBagConstraints();
        JButton botonComprar = new JButton("Comprar");
        cons7.gridwidth = 2;
        cons7.gridx = 0;
        cons7.gridy = 4;
        cons7.fill = GridBagConstraints.HORIZONTAL;
        cons7.insets = new Insets(30,0,30,0);
        panelCompraBoleto.add(botonComprar,cons7);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 5;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(30,30,60,30);
        panelCompraBoleto.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> {InterfazFrame.setPanel(InterfazVentaBoleto.getInstance().getPanelVenta()); singleton = null;});

        botonComprar.addActionListener(e -> {

            //TODO

        });

    }
}
