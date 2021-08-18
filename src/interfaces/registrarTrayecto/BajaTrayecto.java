package interfaces.registrarTrayecto;

import interfaces.InterfazFrame;

import javax.swing.*;
import java.awt.*;

public class BajaTrayecto {

    private static BajaTrayecto singleton;
    private final JPanel panelBajaTrayecto;

    public JPanel getPanelBajaTrayecto() {
        return panelBajaTrayecto;
    }

    public static BajaTrayecto getInstance() {
        if (singleton == null) {
            singleton = new BajaTrayecto();
        }
        return singleton;
    }

    private BajaTrayecto() {

        panelBajaTrayecto = new JPanel(new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("ELIMINAR TRAYECTO");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.BOTH;
        cons0.insets = new Insets(30, 15, 30, 15);
        panelBajaTrayecto.add(nombreMenu, cons0);

        DefaultListModel<String> modelo = new DefaultListModel<>();
        if (modelo.isEmpty()) {
            modelo.add(0, "Lista de trayectos vacia...");
        }

        GridBagConstraints cons1 = new GridBagConstraints();
        JList<String> campoListaTrayecto = new JList<>(modelo);
        cons1.gridwidth = 2;
        cons1.gridheight = 2;
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.BOTH;
        cons1.insets = new Insets(15, 0, 10, 0);
        panelBajaTrayecto.add(campoListaTrayecto, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JButton botonConfirmar = new JButton("Eliminar");
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 3;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.anchor = GridBagConstraints.PAGE_END;
        cons2.insets = new Insets(20, 41, 20, 41);
        panelBajaTrayecto.add(botonConfirmar, cons2);

        GridBagConstraints cons6 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons6.gridx = 0;
        cons6.gridy = 4;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.anchor = GridBagConstraints.PAGE_END;
        cons6.insets = new Insets(40, 41, 60, 41);
        panelBajaTrayecto.add(botonAtras, cons6);

        botonAtras.addActionListener(e -> {
            InterfazFrame.setPanel(InterfazRegistrarTrayecto.getInstance().getPanelRegistroTrayecto());
            singleton = null;
        });

    }
}
