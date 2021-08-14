package interfaces.registrarEstacion;

import interfaces.InterfazFrame;
import javax.swing.*;
import gestores.GestorEstacion;
import dominio.EstacionDeTransbordoMultimodal;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

public class BuscarAtributosEstacion {

    private static BuscarAtributosEstacion singleton;
    private final JPanel panelBuscarAtributosEstacion;
    private ModeloTablaEstaciones modeloTablaEstaciones;
    private JTable tablaEstaciones;

    public JPanel getPanelBuscarAtributosEstacion() {
        return panelBuscarAtributosEstacion;
    }

    public static BuscarAtributosEstacion getInstance(){
        if(singleton == null)
            singleton = new BuscarAtributosEstacion();
        return singleton;
    }

    private BuscarAtributosEstacion() {
        panelBuscarAtributosEstacion = new JPanel(new GridBagLayout());
        GestorEstacion gestorEstacion = new GestorEstacion();
        List<EstacionDeTransbordoMultimodal> estaciones = gestorEstacion.listarTodas();

        String[] horas = {"--", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
                          "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"--", "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu1 = new JLabel("BUSCAR ESTACION");
        nombreMenu1.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.ipady = 100;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(20, 0, 20, 0);
        panelBuscarAtributosEstacion.add(nombreMenu1, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel nombreMenu2 = new JLabel("POR ATRIBUTOS");
        nombreMenu2.setFont(new Font("Dialog", Font.BOLD, 25));
        cons1.gridwidth = 2;
        cons1.gridx = 0;
        cons1.gridy = 0;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(75, 20, 10, 0);
        panelBuscarAtributosEstacion.add(nombreMenu2, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JLabel labelNombre = new JLabel("Nombre: ");
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(0, 5, 5, 0);
        panelBuscarAtributosEstacion.add(labelNombre, cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JTextField campoNombre = new JTextField();
        cons3.gridwidth = 2;
        cons3.gridx = 0;
        cons3.gridy = 2;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(5, 10, 10, 10);
        panelBuscarAtributosEstacion.add(campoNombre, cons3);

        campoNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (campoNombre.getText().length() > 29) {
                    e.consume();
                }
            }
        });

        GridBagConstraints cons4 = new GridBagConstraints();
        JLabel labelHApertura = new JLabel("Horario Apertura: ");
        cons4.gridx = 0;
        cons4.gridy = 3;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(10, 5, 5, 5);
        panelBuscarAtributosEstacion.add(labelHApertura, cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JComboBox<String> campoAHora = new JComboBox<>();
        cons5.gridwidth = 1;
        cons5.gridx = 0;
        cons5.gridy = 4;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.insets = new Insets(5, 10, 10, 10);
        for (String a : horas)
            campoAHora.addItem(a);
        panelBuscarAtributosEstacion.add(campoAHora, cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        JLabel labelDosPuntos1 = new JLabel(": ");
        cons6.gridx = 1;
        cons6.gridy = 4;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(5, 0, 5, 150);
        panelBuscarAtributosEstacion.add(labelDosPuntos1, cons6);

        GridBagConstraints cons7 = new GridBagConstraints();
        JComboBox<String> campoAMinutos = new JComboBox<>();
        cons7.gridwidth = 1;
        cons7.gridx = 1;
        cons7.gridy = 4;
        cons7.fill = GridBagConstraints.HORIZONTAL;
        cons7.insets = new Insets(5, 15, 10, 60);

        for (String b : minutos)
            campoAMinutos.addItem(b);
        panelBuscarAtributosEstacion.add(campoAMinutos, cons7);

        GridBagConstraints cons8 = new GridBagConstraints();
        JLabel labelHCierre = new JLabel("Horario Cierre: ");
        cons8.gridx = 0;
        cons8.gridy = 5;
        cons8.fill = GridBagConstraints.HORIZONTAL;
        cons8.insets = new Insets(10, 5, 5, 5);
        panelBuscarAtributosEstacion.add(labelHCierre, cons8);

        GridBagConstraints cons9 = new GridBagConstraints();
        JComboBox<String> campoCHora = new JComboBox<>();
        cons9.gridwidth = 1;
        cons9.gridx = 0;
        cons9.gridy = 6;
        cons9.fill = GridBagConstraints.HORIZONTAL;
        cons9.insets = new Insets(5, 10, 10, 10);
        for (String c : horas)
            campoCHora.addItem(c);
        panelBuscarAtributosEstacion.add(campoCHora, cons9);

        GridBagConstraints cons10 = new GridBagConstraints();
        JLabel labelDosPuntos2 = new JLabel(": ");
        cons10.gridx = 1;
        cons10.gridy = 6;
        cons10.fill = GridBagConstraints.HORIZONTAL;
        cons10.insets = new Insets(5, 0, 5, 150);
        panelBuscarAtributosEstacion.add(labelDosPuntos2, cons10);

        GridBagConstraints cons11 = new GridBagConstraints();
        JComboBox<String> campoCMinutos = new JComboBox<>();
        cons11.gridwidth = 1;
        cons11.gridx = 1;
        cons11.gridy = 6;
        cons11.fill = GridBagConstraints.HORIZONTAL;
        cons11.insets = new Insets(5, 15, 10, 60);
        for (String d : minutos)
            campoCMinutos.addItem(d);
        panelBuscarAtributosEstacion.add(campoCMinutos, cons11);

        GridBagConstraints cons12 = new GridBagConstraints();
        JLabel labelEstado = new JLabel("Estado: ");
        cons12.gridx = 0;
        cons12.gridy = 7;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(10, 5, 5, 5);
        panelBuscarAtributosEstacion.add(labelEstado, cons12);

        GridBagConstraints cons13 = new GridBagConstraints();
        JComboBox<String> campoEstado = new JComboBox<>();
        cons13.gridwidth = 2;
        cons13.gridx = 0;
        cons13.gridy = 8;
        cons13.fill = GridBagConstraints.HORIZONTAL;
        cons13.insets = new Insets(5, 10, 10, 10);
        campoEstado.addItem("Seleccionar estado...");
        campoEstado.addItem("Operativa");
        campoEstado.addItem("Mantenimiento");
        panelBuscarAtributosEstacion.add(campoEstado, cons13);

        GridBagConstraints cons14 = new GridBagConstraints();
        JButton botonBuscar = new JButton("Buscar");
        cons14.gridwidth = 2;
        cons14.gridx = 0;
        cons14.gridy = 9;
        cons14.fill = GridBagConstraints.HORIZONTAL;
        cons14.insets = new Insets(40, 10, 20, 10);
        panelBuscarAtributosEstacion.add(botonBuscar, cons14);

        GridBagConstraints cons15 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons15.gridwidth = 2;
        cons15.gridx = 0;
        cons15.gridy = 10;
        cons15.fill = GridBagConstraints.HORIZONTAL;
        cons15.insets = new Insets(30, 30, 60, 30);
        panelBuscarAtributosEstacion.add(botonAtras, cons15);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarEstacion.getInstance().getPanelRegistroEstacion()));


        botonBuscar.addActionListener(e -> {
            String[] listaCampos = {campoNombre.getText(), campoAHora.getSelectedItem()+":"+campoAMinutos.getSelectedItem(),
                    campoCHora.getSelectedItem()+":"+campoCMinutos.getSelectedItem(), Objects.requireNonNull(campoEstado.getSelectedItem()).toString()};

            InterfazFrame.setPanel(BuscarEstacionesTabla.getInstance(listaCampos).getPanelBuscarEstacionesTabla());});
        }
    }
