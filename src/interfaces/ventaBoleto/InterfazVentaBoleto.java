package interfaces.ventaBoleto;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;


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
        panelVenta= new JPanel();
        JLabel etiqueta= new JLabel("entro a la interfaz de venta de boleto");
        panelVenta.add(etiqueta);

        JButton boton = new JButton("Atras");
        panelVenta.add(boton);

        boton.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));

    }
}
