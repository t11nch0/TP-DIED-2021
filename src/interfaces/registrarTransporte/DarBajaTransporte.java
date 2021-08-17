package interfaces.registrarTransporte;

import interfaces.InterfazFrame;

import javax.swing.*;

import dominio.LineaTransporte;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;
import gestores.GestorLineaTransporte;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class DarBajaTransporte {

    private static DarBajaTransporte singleton;
    private final JPanel panelDarBajaTransporte;
    private final GestorLineaTransporte gestorLinea;
    private final List<LineaTransporte> transportes;

    public JPanel getPanelDarBajaTransporte() {
        return panelDarBajaTransporte;
    }

    public static DarBajaTransporte getInstance() {
        if (singleton == null)
            singleton = new DarBajaTransporte();
        return singleton;
    }

    private DarBajaTransporte() {
        panelDarBajaTransporte = new JPanel(new GridBagLayout());
        gestorLinea = new GestorLineaTransporte();
        transportes = gestorLinea.listarTodas();

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("DAR BAJA TRANSPORTE");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55, 0, 40, 0);
        panelDarBajaTransporte.add(nombreMenu, cons0);

        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (LineaTransporte t : transportes) {
            modelo.addElement(t.getNombre());
        }
        if (modelo.isEmpty()) {
            modelo.add(0, "No hay transportes disponibles");
        }

        GridBagConstraints cons2 = new GridBagConstraints();
        JList<String> campoLista = new JList<>(modelo);
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.BOTH;
        cons2.insets = new Insets(10, 0, 40, 0);
        panelDarBajaTransporte.add(campoLista, cons2);

        GridBagConstraints cons11 = new GridBagConstraints();
        JButton botonEliminar = new JButton("Eliminar");
        cons11.gridwidth = 2;
        cons11.gridx = 0;
        cons11.gridy = 2;
        cons11.fill = GridBagConstraints.HORIZONTAL;
        cons11.insets = new Insets(10, 0, 30, 0);
        panelDarBajaTransporte.add(botonEliminar, cons11);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 3;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(30, 0, 60, 0);
        panelDarBajaTransporte.add(botonAtras, cons12);

        botonAtras.addActionListener(e -> {
            InterfazFrame.setPanel(InterfazRegistrarTransporte.getInstance().getPanelRegistrarTransporte());
            singleton = null;
        });

        botonEliminar.addActionListener(e -> {

            try {

                int index = campoLista.getSelectedIndex();
                gestorLinea.eliminarLinea(transportes.get(index));
                modelo.remove(index);
                campoLista.setModel(modelo);

                if (modelo.isEmpty()) {
                    modelo.add(0, "No hay transportes disponibles");
                }

                InterfazFrame.setPanel(DarBajaTransporte.getInstance().getPanelDarBajaTransporte());
            } catch (SQLException | BaseDeDatosException | CamposIncorrectosException e1) {

                e1.printStackTrace();
            }
        });

    }
}
