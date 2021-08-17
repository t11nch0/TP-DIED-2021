package interfaces.registrarMantenimientoEstacion;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;
import java.awt.*;

public class InterfazRegistrarMantenimientoEstacion {

    private static InterfazRegistrarMantenimientoEstacion singleton;
    private final JPanel panelRegistrarMantenimiento;

    public JPanel getPanelRegistrarMantenimiento() {
        return panelRegistrarMantenimiento;
    }

    public static InterfazRegistrarMantenimientoEstacion getInstance(){
        if(singleton == null){
            singleton = new InterfazRegistrarMantenimientoEstacion();

        }
        return singleton;
    }

    private InterfazRegistrarMantenimientoEstacion() {
        panelRegistrarMantenimiento = new JPanel(new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("MANTENIMIENTO DE ESTACION");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,20,0);
        panelRegistrarMantenimiento.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        String[] data = {"Estacion1", "Estacion2", "Estacion3", "Estacion4", "Estacion5", "Estacion6", "Estacion7", "Estacion8", "Estacion9", "Estacion10"};
        JList<String> campoLista = new JList<>(data);
        cons1.gridwidth = 2;
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.BOTH;
        cons1.insets = new Insets(10,0,40,0);
        panelRegistrarMantenimiento.add(campoLista, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JLabel estadoEstacion = new JLabel("Estado de la Estacion: ");
        estadoEstacion.setFont(new Font("Dialog", Font.BOLD, 15));
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 2;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(10,0,20,0);
        panelRegistrarMantenimiento.add(estadoEstacion, cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JComboBox<String> campoEstado = new JComboBox<>();
        cons3.gridwidth = 2;
        cons3.gridx = 0;
        cons3.gridy = 3;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(5, 5 ,20 ,5);
        campoEstado.addItem("Operativa");
        campoEstado.addItem("En Mantenimiento");
        panelRegistrarMantenimiento.add(campoEstado,cons3);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 4;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.anchor = GridBagConstraints.SOUTH;
        cons12.insets = new Insets(40,0,60,0);
        panelRegistrarMantenimiento.add(botonAtras,cons12);


        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));

    }
}
