package interfaces.registrarTransporte;

import interfaces.InterfazFrame;
import javax.swing.*;
import java.util.List;
import gestores.GestorLineaTransporte;
import dominio.LineaTransporte;
import java.awt.*;

public class BuscarLineasTabla {

    private static BuscarLineasTabla singleton;
    private final JPanel panelBuscarLineasTabla;
    List<LineaTransporte> lineas;

    public JPanel getPanelBuscarLineasTabla() {
        return panelBuscarLineasTabla;
    }

    public static BuscarLineasTabla getInstance(String[] lista){
        if(singleton == null)
            singleton = new BuscarLineasTabla(lista);
        return singleton;
    }

    private BuscarLineasTabla(String[] lista) {
        panelBuscarLineasTabla = new JPanel(new GridBagLayout());
        GestorLineaTransporte gestorLinea = new GestorLineaTransporte();
        try{
            lineas = gestorLinea.filtrar(lista);
        }catch(Exception e){
            e.printStackTrace();
        }

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("TABLA LINEAS");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(40,130,40,0);
        panelBuscarLineasTabla.add(nombreMenu, cons0);

        ModeloTablaLineas modeloTablaLineas = new ModeloTablaLineas(lineas);

        GridBagConstraints cons1 = new GridBagConstraints();
        JTable tablaLineas = new JTable(modeloTablaLineas);
 		tablaLineas.setFont(new Font("Dialog", Font.PLAIN, 12));
 		cons1.gridwidth = 1;
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.insets = new Insets(15,0,40,0);
        JScrollPane pane = new JScrollPane(tablaLineas);
        panelBuscarLineasTabla.add(pane, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 3;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(30,0,60,0);
        panelBuscarLineasTabla.add(botonAtras,cons2);

        botonAtras.addActionListener(e -> {InterfazFrame.setPanel(BuscarAtributosTransporte.getInstance().getPanelBuscarAtributosTransporte()); singleton = null;});

    }
}
