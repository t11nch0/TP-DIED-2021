package interfaces.registrarEstacion;

import interfaces.InterfazFrame;

import javax.swing.*;
import java.util.List;

import gestores.GestorEstacion;
import dominio.EstacionDeTransbordoMultimodal;

import java.awt.*;

public class BuscarEstacionesTabla {

    private static BuscarEstacionesTabla singleton;
    private final JPanel panelBuscarEstacionesTabla;
    List<EstacionDeTransbordoMultimodal> estaciones;

    public JPanel getPanelBuscarEstacionesTabla() {
        return panelBuscarEstacionesTabla;
    }

    public static BuscarEstacionesTabla getInstance(String[] lista) {
        if (singleton == null)
            singleton = new BuscarEstacionesTabla(lista);
        return singleton;
    }

    private BuscarEstacionesTabla(String[] lista) {
        panelBuscarEstacionesTabla = new JPanel(new GridBagLayout());
        GestorEstacion gestorEstacion = new GestorEstacion();
        try {
            estaciones = gestorEstacion.filtrar(lista);
        } catch (Exception e) {
            e.printStackTrace();
        }

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("TABLA ESTACIONES");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 1;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.anchor = GridBagConstraints.CENTER;
        cons0.insets = new Insets(40, 105, 40, 0);
        panelBuscarEstacionesTabla.add(nombreMenu, cons0);

        ModeloTablaEstaciones modeloTablaEstaciones = new ModeloTablaEstaciones(estaciones);

        GridBagConstraints cons1 = new GridBagConstraints();
        JTable tablaEstaciones = new JTable(modeloTablaEstaciones);
        tablaEstaciones.setFont(new Font("Dialog", Font.PLAIN, 12));
        cons1.gridwidth = 1;
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.insets = new Insets(15, 0, 40, 0);
        JScrollPane pane = new JScrollPane(tablaEstaciones);
        panelBuscarEstacionesTabla.add(pane, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons2.gridwidth = 1;
        cons2.gridx = 0;
        cons2.gridy = 3;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(30, 0, 60, 0);
        panelBuscarEstacionesTabla.add(botonAtras, cons2);

        botonAtras.addActionListener(e -> {
            InterfazFrame.setPanel(BuscarAtributosEstacion.getInstance().getPanelBuscarAtributosEstacion());
            singleton = null;
        });

    }
}

