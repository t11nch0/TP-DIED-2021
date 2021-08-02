package interfaces.informacionEstacion;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;
import java.awt.*;

public class InterfazInformacionEstacion {

    private static InterfazInformacionEstacion singleton;
    private final JPanel panelInformacionEstacion;

    public JPanel getPanelInformacionEstacion() {
        return panelInformacionEstacion;
    }

    public static InterfazInformacionEstacion getInstance(){
        if(singleton == null){
            singleton = new InterfazInformacionEstacion();
        }
        return singleton;
    }

    private InterfazInformacionEstacion() {
        panelInformacionEstacion = new JPanel(new GridBagLayout());

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("INFORMACION DE LAS ESTACIONES");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(35,0,20,0);
        panelInformacionEstacion.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel flujoMaximo = new JLabel("FLUJO MAXIMO: ");
        flujoMaximo.setFont(new Font("Dialog", Font.BOLD, 15));
        cons1.gridwidth = 2;
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(10,0,10,0);
        panelInformacionEstacion.add(flujoMaximo, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JLabel nombreOrigen = new JLabel("Estacion de Origen: ");
        nombreOrigen.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 2;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(20,0,35,0);
        panelInformacionEstacion.add(nombreOrigen, cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JComboBox<String> campoEstacionOrigen = new JComboBox<>();
        cons3.gridwidth = 2;
        cons3.gridx = 0;
        cons3.gridy = 2;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(35, 5 ,0 ,5);
        campoEstacionOrigen.addItem("Seleccionar estacion...");
        campoEstacionOrigen.addItem("...");
        panelInformacionEstacion.add(campoEstacionOrigen,cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JLabel nombreDestino = new JLabel("Estacion de Destino: ");
        nombreDestino.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons4.gridwidth = 2;
        cons4.gridx = 0;
        cons4.gridy = 3;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(20,0,35,0);
        panelInformacionEstacion.add(nombreDestino, cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JComboBox<String> campoEstacionDestino = new JComboBox<>();
        cons5.gridwidth = 2;
        cons5.gridx = 0;
        cons5.gridy = 3;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.insets = new Insets(35, 5 ,0 ,5);
        campoEstacionDestino.addItem("Seleccionar estacion...");
        campoEstacionDestino.addItem("...");
        panelInformacionEstacion.add(campoEstacionDestino,cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        String flujoMaximoEncontradoString = " -->>   Flujo Maximo Harcodeado   <<--";
        JLabel flujoMaximoEncontrado = new JLabel(flujoMaximoEncontradoString);
        flujoMaximoEncontrado.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons6.gridwidth = 2;
        cons6.gridx = 0;
        cons6.gridy = 4;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(20,0,20,0);
        panelInformacionEstacion.add(flujoMaximoEncontrado , cons6);


        GridBagConstraints cons7 = new GridBagConstraints();
        JLabel pageRank = new JLabel("PAGE RANK: ");
        pageRank.setFont(new Font("Dialog", Font.BOLD, 15));
        cons7.gridwidth = 2;
        cons7.gridx = 0;
        cons7.gridy = 5;
        cons7.fill = GridBagConstraints.HORIZONTAL;
        cons7.insets = new Insets(10,0,10,0);
        panelInformacionEstacion.add(pageRank, cons7);

        GridBagConstraints cons8 = new GridBagConstraints();
        String[] data = {"Estacion1", "Estacion2", "Estacion3", "Estacion4", "Estacion5", "Estacion6", "Estacion7", "Estacion8", "Estacion9", "Estacion10"};
        JList<String> campoLista = new JList<>(data);
        cons8.gridwidth = 2;
        cons8.gridheight = 1;
        cons8.gridx = 0;
        cons8.gridy = 6;
        cons8.fill = GridBagConstraints.BOTH;
        cons8.insets = new Insets(10,0,10,0);
        panelInformacionEstacion.add(campoLista, cons8);

        GridBagConstraints cons9 = new GridBagConstraints();
        JLabel proximoMantenimiento = new JLabel("PROXIMO MANTENIMIENTO: ");
        proximoMantenimiento.setFont(new Font("Dialog", Font.BOLD, 15));
        cons9.gridwidth = 2;
        cons9.gridx = 0;
        cons9.gridy = 7;
        cons9.fill = GridBagConstraints.HORIZONTAL;
        cons9.insets = new Insets(10,0,20,0);
        panelInformacionEstacion.add(proximoMantenimiento, cons9);

        GridBagConstraints cons10 = new GridBagConstraints();
        String proximoMantenimientoString = " -->>   Monticulo de Mantenimiento Harcodeado   <<--";
        JLabel proximoMantenimientoEncontrado = new JLabel(proximoMantenimientoString);
        proximoMantenimientoEncontrado.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons10.gridwidth = 2;
        cons10.gridx = 0;
        cons10.gridy = 8;
        cons10.fill = GridBagConstraints.HORIZONTAL;
        cons10.insets = new Insets(20,0,20,0);
        panelInformacionEstacion.add(proximoMantenimientoEncontrado , cons10);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 9;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(10,100,40,100);
        panelInformacionEstacion.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()));

    }
}
