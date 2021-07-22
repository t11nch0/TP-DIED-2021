package interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazVentaBoleto{

    private static InterfazVentaBoleto singleton;
    private JPanel panelVenta;

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

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal());
            }
        });

    }
}
