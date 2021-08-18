package interfaces.registrarTrayecto;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;
import javax.swing.*;
import dominio.EstacionDeTransbordoMultimodal;
import dominio.LineaTransporte;
import dominio.Ruta;
import gestores.GestorEstacion;
import gestores.GestorLineaTransporte;
import gestores.GestorRuta;
import java.util.List;
import java.awt.*;
import java.util.Objects;

public class AltaRuta {

    private static AltaRuta singleton;
    private final JPanel panelRegistroTrayecto;
    private final GestorRuta gestorRuta;
    private final List<EstacionDeTransbordoMultimodal> estaciones;


    public JPanel getPanelAltaRuta() {
        return panelRegistroTrayecto;
    }

    public static AltaRuta getInstance() {
        if (singleton == null) {
            singleton = new AltaRuta();
        }
        return singleton;
    }

    private AltaRuta() {
        panelRegistroTrayecto = new JPanel(new GridBagLayout());
        //this.lineas = gestorLinea.listarTodas(); //AAAA
        GestorEstacion gestorEstacion = new GestorEstacion();
        gestorRuta = new GestorRuta();
        GestorLineaTransporte gestorLinea = new GestorLineaTransporte();
        List<LineaTransporte> lineas = gestorLinea.getTodasLineas();
        this.estaciones = gestorEstacion.listarTodas();

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("AGREGAR RUTA");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55, 0, 20, 0);
        panelRegistroTrayecto.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel nombreEstOrigen = new JLabel("Estacion origen: ");
        nombreEstOrigen.setFont(new Font("Dialog", Font.PLAIN, 12));
        cons1.gridwidth = 2;
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(10, 0, 35, 0);
        panelRegistroTrayecto.add(nombreEstOrigen, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JComboBox<String> campoEstOrigen = new JComboBox<>();
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(35, 5, 0, 5);
        campoEstOrigen.addItem("Seleccionar estacion...");
        for (EstacionDeTransbordoMultimodal e : estaciones) {
            campoEstOrigen.addItem(e.getNombreEstacion());
        }
        panelRegistroTrayecto.add(campoEstOrigen, cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JLabel nombreEstDestino = new JLabel("Estacion destino: ");
        nombreEstDestino.setFont(new Font("Dialog", Font.PLAIN, 12));
        cons3.gridwidth = 2;
        cons3.gridx = 0;
        cons3.gridy = 2;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(10, 0, 35, 0);
        panelRegistroTrayecto.add(nombreEstDestino, cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JComboBox<String> campoEstDestino = new JComboBox<>();
        cons4.gridwidth = 2;
        cons4.gridx = 0;
        cons4.gridy = 2;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(35, 5, 0, 5);
        campoEstDestino.addItem("Seleccionar estacion...");

        for (EstacionDeTransbordoMultimodal e : estaciones) {
            if (Objects.equals(campoEstOrigen.getSelectedItem(), e.getNombreEstacion())) {
                System.out.println(e.getNombreEstacion());
                for (Ruta r : gestorRuta.getRutasConOrigen(e)) {
                    campoEstDestino.addItem(r.getDestino().getNombreEstacion());
                }
            }
        }
        panelRegistroTrayecto.add(campoEstDestino, cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JLabel labelDistancia = new JLabel("Distancia: ");
        cons5.gridx = 0;
        cons5.gridy = 3;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.insets = new Insets(10, 5, 55, 5);
        panelRegistroTrayecto.add(labelDistancia, cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        JTextField campoDistancia = new JTextField();
        cons6.gridwidth = 2;
        cons6.gridx = 0;
        cons6.gridy = 3;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(5, 5, 0, 5);
        panelRegistroTrayecto.add(campoDistancia, cons6);

        GridBagConstraints cons7 = new GridBagConstraints();
        JLabel labelTiempo = new JLabel("Tiempo (minutos): ");
        cons7.gridx = 0;
        cons7.gridy = 4;
        cons7.fill = GridBagConstraints.HORIZONTAL;
        cons7.insets = new Insets(10, 5, 55, 5);
        panelRegistroTrayecto.add(labelTiempo, cons7);

        GridBagConstraints cons8 = new GridBagConstraints();
        JTextField campoTiempo = new JTextField();
        cons8.gridwidth = 2;
        cons8.gridx = 0;
        cons8.gridy = 4;
        cons8.fill = GridBagConstraints.HORIZONTAL;
        cons8.insets = new Insets(5, 5, 10, 5);
        panelRegistroTrayecto.add(campoTiempo, cons8);

        GridBagConstraints cons9 = new GridBagConstraints();
        JLabel labelPasajeros = new JLabel("Cantida pasajeros: ");
        cons9.gridx = 0;
        cons9.gridy = 5;
        cons9.fill = GridBagConstraints.HORIZONTAL;
        cons9.insets = new Insets(10, 5, 55, 5);
        panelRegistroTrayecto.add(labelPasajeros, cons9);

        GridBagConstraints cons10 = new GridBagConstraints();
        JTextField campoPasajeros = new JTextField();
        cons10.gridwidth = 2;
        cons10.gridx = 0;
        cons10.gridy = 5;
        cons10.fill = GridBagConstraints.HORIZONTAL;
        cons10.insets = new Insets(5, 5, 10, 5);
        panelRegistroTrayecto.add(campoPasajeros, cons10);

        GridBagConstraints cons11 = new GridBagConstraints();
        JLabel labelEstado = new JLabel("Estado: ");
        cons11.gridx = 0;
        cons11.gridy = 6;
        cons11.fill = GridBagConstraints.HORIZONTAL;
        cons11.insets = new Insets(10, 5, 55, 5);
        panelRegistroTrayecto.add(labelEstado, cons11);

        GridBagConstraints cons12 = new GridBagConstraints();
        JComboBox<String> campoEstado = new JComboBox<>();
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 6;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(5, 5, 10, 5);
        campoEstado.addItem("ACTIVO");
        campoEstado.addItem("INACTIVO");
        panelRegistroTrayecto.add(campoEstado, cons12);

        GridBagConstraints cons13 = new GridBagConstraints();
        JLabel labelCosto = new JLabel("Costo en $: ");
        cons13.gridx = 0;
        cons13.gridy = 7;
        cons13.fill = GridBagConstraints.HORIZONTAL;
        cons13.insets = new Insets(10, 5, 55, 5);
        panelRegistroTrayecto.add(labelCosto, cons13);

        GridBagConstraints cons14 = new GridBagConstraints();
        JTextField campoCosto = new JTextField();
        cons14.gridwidth = 2;
        cons14.gridx = 0;
        cons14.gridy = 7;
        cons14.fill = GridBagConstraints.HORIZONTAL;
        cons14.insets = new Insets(5, 5, 10, 5);
        panelRegistroTrayecto.add(campoCosto, cons14);

        GridBagConstraints cons15 = new GridBagConstraints();
        JButton botonConfirmar = new JButton("Confirmar");
        cons15.gridwidth = 2;
        cons15.gridx = 0;
        cons15.gridy = 8;
        cons15.fill = GridBagConstraints.HORIZONTAL;
        cons15.insets = new Insets(10, 0, 60, 0);
        panelRegistroTrayecto.add(botonConfirmar, cons15);

        GridBagConstraints cons16 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons16.gridwidth = 2;
        cons16.gridx = 0;
        cons16.gridy = 9;
        cons16.fill = GridBagConstraints.HORIZONTAL;
        cons16.insets = new Insets(10, 0, 60, 0);
        panelRegistroTrayecto.add(botonAtras, cons16);

        botonAtras.addActionListener(e -> {InterfazFrame.setPanel(InterfazRegistrarTrayecto.getInstance().getPanelRegistroTrayecto()); singleton = null;});


    }
}
