package interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazRegistrarTrayectos {

    private static InterfazRegistrarTrayectos singleton;
    private JPanel panelRegistroTrayecto;

    public JPanel getPanelRegistroTrayecto() {
        return panelRegistroTrayecto;
    }

    public static InterfazRegistrarTrayectos getInstance(){
        if(singleton == null){
            singleton= new InterfazRegistrarTrayectos();
        }
        return singleton;
    }

    private InterfazRegistrarTrayectos() {
        panelRegistroTrayecto = new JPanel();
        JLabel etiqueta= new JLabel("entro a la interfaz de Registro de Trayectos");

        panelRegistroTrayecto.add(etiqueta);

        JButton boton = new JButton("Atras");
        panelRegistroTrayecto.add(boton);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal());
            }
        });
    }
}
