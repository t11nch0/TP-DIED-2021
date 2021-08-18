package interfaces.registrarTrayecto;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;
import java.awt.*;

public class InterfazRegistrarTrayecto {

    private static InterfazRegistrarTrayecto singleton;
    private final JPanel panelRegistroTrayecto;

    public JPanel getPanelRegistroTrayecto() {
        return panelRegistroTrayecto;
    }

    public static InterfazRegistrarTrayecto getInstance() {
        if (singleton == null) {
            singleton = new InterfazRegistrarTrayecto();
        }
        return singleton;
    }

    private InterfazRegistrarTrayecto() {
        panelRegistroTrayecto = new JPanel(new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("MENU DE TRAYECTO");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.BOTH;
        cons0.insets = new Insets(30, 15, 30, 15);
        panelRegistroTrayecto.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JButton botonCrearTrayecto = new JButton("Crear trayecto");
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.BOTH;
        cons1.insets = new Insets(40, 20, 10, 20);
        panelRegistroTrayecto.add(botonCrearTrayecto, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JButton botonEliminarTrayecto = new JButton("Eliminar trayecto");
        cons2.gridx = 0;
        cons2.gridy = 2;
        cons2.fill = GridBagConstraints.BOTH;
        cons2.insets = new Insets(10, 20, 30, 20);
        panelRegistroTrayecto.add(botonEliminarTrayecto, cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JButton botonCrearCamino = new JButton("Crear ruta");
        cons3.gridx = 0;
        cons3.gridy = 3;
        cons3.fill = GridBagConstraints.BOTH;
        cons3.insets = new Insets(30, 20, 10, 20);
        panelRegistroTrayecto.add(botonCrearCamino, cons3);

        GridBagConstraints cons6 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons6.gridx = 0;
        cons6.gridy = 6;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.anchor = GridBagConstraints.PAGE_END;
        cons6.insets = new Insets(40, 41, 60, 41);
        panelRegistroTrayecto.add(botonAtras, cons6);

        botonAtras.addActionListener(e -> {
            InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal());
            singleton = null;
        });

        botonCrearTrayecto.addActionListener(e -> {
            InterfazFrame.setPanel(AltaTrayecto.getInstance().getPanelAltaTrayecto());
        });

        botonEliminarTrayecto.addActionListener(e -> {
            InterfazFrame.setPanel(BajaTrayecto.getInstance().getPanelBajaTrayecto());
        });

        botonCrearCamino.addActionListener(e -> {
            InterfazFrame.setPanel(AltaRuta.getInstance().getPanelAltaRuta());
        });

    }
}
