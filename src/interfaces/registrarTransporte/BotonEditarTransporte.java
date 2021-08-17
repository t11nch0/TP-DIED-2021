package interfaces.registrarTransporte;

import interfaces.InterfazFrame;

import javax.swing.*;

import dominio.LineaTransporte;
import dominio.LineaTransporte.EstadoLinea;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;
import gestores.GestorLineaTransporte;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class BotonEditarTransporte {

    private static BotonEditarTransporte singleton;
    private final JPanel panelBotonEditarTransporte;

    public JPanel getPanelBotonEditarTransporte() {
        return panelBotonEditarTransporte;
    }

    public static BotonEditarTransporte getInstance(Integer index) {
        if (singleton == null)
            singleton = new BotonEditarTransporte(index);
        return singleton;
    }

    private BotonEditarTransporte(Integer index) {
        panelBotonEditarTransporte = new JPanel(new GridBagLayout());
        GestorLineaTransporte gestorLinea = new GestorLineaTransporte();
        List<LineaTransporte> lineas = gestorLinea.listarTodas();
        LineaTransporte lin = lineas.get(index);

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("EDITAR TRANSPORTE");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55, 0, 20, 0);
        panelBotonEditarTransporte.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel labelNombre = new JLabel("Nombre: ");
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(10, 5, 5, 5);
        panelBotonEditarTransporte.add(labelNombre, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JTextField campoNombre = new JTextField();
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 2;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(5, 5, 10, 5);
        campoNombre.setText(lin.getNombre());
        panelBotonEditarTransporte.add(campoNombre, cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JLabel labelColor = new JLabel("Color: ");
        cons3.gridx = 0;
        cons3.gridy = 3;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(10, 5, 5, 5);
        panelBotonEditarTransporte.add(labelColor, cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JComboBox<String> campoColor = new JComboBox<>();
        cons4.gridwidth = 2;
        cons4.gridx = 0;
        cons4.gridy = 4;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(5, 5, 10, 5);
        campoColor.addItem("ROJO");
        campoColor.addItem("AZUL");
        campoColor.addItem("VERDE");
        campoColor.addItem("AMARILLO");
        campoColor.addItem("NARANJA");
        campoColor.addItem("CELESTE");
        campoColor.addItem("VIOLETA");

        switch (lin.getColor()) {
            case "ROJO":
                campoColor.setSelectedIndex(0);
                break;
            case "AZUL":
                campoColor.setSelectedIndex(1);
                break;
            case "VERDE":
                campoColor.setSelectedIndex(2);
                break;
            case "AMARILLO":
                campoColor.setSelectedIndex(3);
                break;
            case "NARANJA":
                campoColor.setSelectedIndex(4);
                break;
            case "CELESTE":
                campoColor.setSelectedIndex(5);
                break;
            case "VIOLETA":
                campoColor.setSelectedIndex(6);
                break;
        }
        panelBotonEditarTransporte.add(campoColor, cons4);

        GridBagConstraints cons9 = new GridBagConstraints();
        JLabel labelEstado = new JLabel("Estado: ");
        cons9.gridx = 0;
        cons9.gridy = 9;
        cons9.fill = GridBagConstraints.HORIZONTAL;
        cons9.insets = new Insets(10, 5, 5, 5);
        panelBotonEditarTransporte.add(labelEstado, cons9);

        GridBagConstraints cons10 = new GridBagConstraints();
        JComboBox<String> campoEstado = new JComboBox<>();
        cons10.gridwidth = 2;
        cons10.gridx = 0;
        cons10.gridy = 10;
        cons10.fill = GridBagConstraints.HORIZONTAL;
        cons10.insets = new Insets(5, 5, 10, 5);
        campoEstado.addItem("Activa");
        campoEstado.addItem("Inactiva");
        if ((lin.getEstado() == EstadoLinea.ACTIVA)) {
            campoEstado.setSelectedIndex(0);
        } else {
            campoEstado.setSelectedIndex(1);
        }
        panelBotonEditarTransporte.add(campoEstado, cons10);

        GridBagConstraints cons11 = new GridBagConstraints();
        JButton botonAceptar = new JButton("Aceptar");
        cons11.gridwidth = 2;
        cons11.gridx = 0;
        cons11.gridy = 11;
        cons11.fill = GridBagConstraints.HORIZONTAL;
        cons11.insets = new Insets(40, 0, 30, 0);
        panelBotonEditarTransporte.add(botonAceptar, cons11);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 12;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(30, 0, 60, 0);
        panelBotonEditarTransporte.add(botonAtras, cons12);

        botonAtras.addActionListener(e -> {
            InterfazFrame.setPanel(EditarTransporte.getInstance().getPanelEditarTransporte());
            singleton = null;
        });

        botonAceptar.addActionListener(e -> {

            try {

                String nombre = campoNombre.getText();
                String color = Objects.requireNonNull(campoColor.getSelectedItem()).toString();
                //ESTADO
                EstadoLinea estado;
                if ((Objects.equals(campoEstado.getSelectedItem(), "Activa")))
                    estado = EstadoLinea.ACTIVA;
                else
                    estado = EstadoLinea.INACTIVA;

                gestorLinea.editarLinea(lin, nombre, color, estado);


            } catch (CamposIncorrectosException | SQLException | BaseDeDatosException ex) {
                ex.printStackTrace();
            }


        });

    }
}
