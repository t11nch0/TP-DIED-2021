package interfaces.informacionEstacion;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;
import java.util.List;

import dominio.EstacionDeTransbordoMultimodal;
import gestores.GestorCamino;
import gestores.GestorEstacion;

import java.awt.*;

public class InterfazInformacionEstacion {

    private static InterfazInformacionEstacion singleton;
    private final JPanel panelInformacionEstacion;
    private final GestorCamino gestorCamino;
    private final List<EstacionDeTransbordoMultimodal> estaciones;

    public JPanel getPanelInformacionEstacion() {
        return panelInformacionEstacion;
    }

    public static InterfazInformacionEstacion getInstance() {
        if (singleton == null) {
            singleton = new InterfazInformacionEstacion();
        }
        return singleton;
    }

    private InterfazInformacionEstacion() {
        panelInformacionEstacion = new JPanel(new GridBagLayout());
        GestorEstacion gestorEstacion = new GestorEstacion();
        gestorCamino = new GestorCamino();
        estaciones = gestorEstacion.listarTodas();

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu1 = new JLabel("           INFORMACION DE");
        nombreMenu1.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(10, 0, 45, 0);
        panelInformacionEstacion.add(nombreMenu1, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel nombreMenu2 = new JLabel("           LAS ESTACIONES");
        nombreMenu2.setFont(new Font("Dialog", Font.BOLD, 25));
        cons1.gridwidth = 2;
        cons1.gridx = 0;
        cons1.gridy = 0;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(45, 0, 10, 0);
        panelInformacionEstacion.add(nombreMenu2, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JLabel flujoMaximo = new JLabel("FLUJO MAXIMO: ");
        flujoMaximo.setFont(new Font("Dialog", Font.BOLD, 12));
        cons2.gridwidth = 1;
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(5, 0, 5, 0);
        panelInformacionEstacion.add(flujoMaximo, cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JLabel nombreOrigen = new JLabel("Estacion de Origen: ");
        nombreOrigen.setFont(new Font("Dialog", Font.PLAIN, 12));
        cons3.gridwidth = 1;
        cons3.gridx = 0;
        cons3.gridy = 2;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(5, 0, 10, 0);
        panelInformacionEstacion.add(nombreOrigen, cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JComboBox<String> campoEstacionOrigen = new JComboBox<>();
        cons4.gridwidth = 1;
        cons4.gridx = 0;
        cons4.gridy = 2;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(30, 0, 0, 5);
        campoEstacionOrigen.addItem("Seleccionar estacion...");
        for (EstacionDeTransbordoMultimodal e : estaciones) {
            campoEstacionOrigen.addItem(e.getNombreEstacion());
        }
        panelInformacionEstacion.add(campoEstacionOrigen, cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JLabel nombreDestino = new JLabel("Estacion de Destino: ");
        nombreDestino.setFont(new Font("Dialog", Font.PLAIN, 12));
        cons5.gridwidth = 1;
        cons5.gridx = 1;
        cons5.gridy = 2;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.insets = new Insets(5, 50, 10, 0);
        panelInformacionEstacion.add(nombreDestino, cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        JComboBox<String> campoEstacionDestino = new JComboBox<>();
        cons6.gridwidth = 1;
        cons6.gridx = 1;
        cons6.gridy = 2;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(30, 50, 0, 150);
        campoEstacionDestino.addItem("Seleccionar estacion...");
        for (EstacionDeTransbordoMultimodal e : estaciones) {
            campoEstacionDestino.addItem(e.getNombreEstacion());
        }
        if (campoEstacionOrigen.getSelectedItem() == "Seleccionar estacion...") {
            campoEstacionDestino.setEnabled(false);
        }
        panelInformacionEstacion.add(campoEstacionDestino, cons6);

        GridBagConstraints cons7 = new GridBagConstraints();
        JLabel flujoMaximoEncontrado = new JLabel("Flujo maximo de pasajeros: ");
        flujoMaximoEncontrado.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons7.gridwidth = 2;
        cons7.gridx = 0;
        cons7.gridy = 3;
        cons7.fill = GridBagConstraints.HORIZONTAL;
        cons7.insets = new Insets(20, 0, 20, 0);
        panelInformacionEstacion.add(flujoMaximoEncontrado, cons7);

        GridBagConstraints cons8 = new GridBagConstraints();
        JLabel pageRank = new JLabel("PAGE RANK: ");
        pageRank.setFont(new Font("Dialog", Font.BOLD, 12));
        cons8.gridwidth = 1;
        cons8.gridx = 0;
        cons8.gridy = 4;
        cons8.fill = GridBagConstraints.HORIZONTAL;
        cons8.insets = new Insets(0, 0, 5, 0);
        panelInformacionEstacion.add(pageRank, cons8);

        DefaultListModel<String> modeloPageRank = new DefaultListModel<>();
        List<EstacionDeTransbordoMultimodal> listaPR = gestorCamino.pageRank();
        for (EstacionDeTransbordoMultimodal e : listaPR) {
            modeloPageRank.addElement(e.getNombreEstacion());
        }
        if (modeloPageRank.isEmpty()) {
            modeloPageRank.add(0, "No hay estaciones disponibles");
        }

        GridBagConstraints cons9 = new GridBagConstraints();
        JList<String> campoLista = new JList<>(modeloPageRank);
        cons9.gridwidth = 1;
        cons9.gridheight = 2;
        cons9.gridx = 0;
        cons9.gridy = 5;
        cons9.fill = GridBagConstraints.HORIZONTAL;
        cons9.anchor = GridBagConstraints.WEST;
        cons9.insets = new Insets(5, 0, 30, 0);
        JScrollPane pane1 = new JScrollPane(campoLista);
        panelInformacionEstacion.add(pane1, cons9);

        GridBagConstraints cons10 = new GridBagConstraints();
        JLabel proximoMantenimiento = new JLabel("PROXIMO MANTENIMIENTO: ");
        proximoMantenimiento.setFont(new Font("Dialog", Font.BOLD, 12));
        cons10.gridwidth = 1;
        cons10.gridx = 1;
        cons10.gridy = 4;
        cons10.fill = GridBagConstraints.NONE;
        cons10.insets = new Insets(0, 0, 5, 0);
        panelInformacionEstacion.add(proximoMantenimiento, cons10);

        DefaultListModel<String> modeloMonticulo = new DefaultListModel<>();
        for (EstacionDeTransbordoMultimodal e : estaciones) {
            modeloMonticulo.addElement(e.getNombreEstacion());
        }
        if (modeloMonticulo.isEmpty()) {
            modeloMonticulo.add(0, "No hay estaciones disponibles");
        }

        GridBagConstraints cons11 = new GridBagConstraints();
        JList<String> campoMantenimiento = new JList<>(modeloMonticulo);
        cons11.gridwidth = 1;
        cons11.gridheight = 2;
        cons11.gridx = 1;
        cons11.gridy = 5;
        cons11.fill = GridBagConstraints.BOTH;
        cons11.insets = new Insets(5, 90, 30, 90);
        JScrollPane pane2 = new JScrollPane(campoMantenimiento);
        panelInformacionEstacion.add(pane2, cons11);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 7;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(10, 30, 10, 100);
        panelInformacionEstacion.add(botonAtras, cons12);

        botonAtras.addActionListener(e -> {
            InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal());
            singleton = null;
        });

        campoEstacionOrigen.addActionListener(e -> campoEstacionDestino.setEnabled(true));

        campoEstacionDestino.addActionListener(e -> {

            //Si selecciona primero la estacion destino SE ROMPE (?)
            EstacionDeTransbordoMultimodal origen = null;
            EstacionDeTransbordoMultimodal destino = null;
            for (EstacionDeTransbordoMultimodal est : estaciones) {
                if (campoEstacionOrigen.getSelectedItem() == est.getNombreEstacion())
                    origen = est;
                //else if ?
                if (campoEstacionDestino.getSelectedItem() == est.getNombreEstacion())
                    destino = est;
            }

            if (origen != destino) {
                Integer flujoMax = gestorCamino.flujoMaximo(origen, destino);
                flujoMaximoEncontrado.setText("Flujo maximo de pasajeros: " + flujoMax.toString());
                //flujoMaximoEncontrado.setText(flujoMax.toString());
            } else {
                flujoMaximoEncontrado.setText("Flujo maximo de pasajeros: " + 0);
            }


        });
    }
}