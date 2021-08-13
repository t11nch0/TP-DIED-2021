package interfaces.registrarEstacion;

import interfaces.InterfazFrame;
import javax.swing.*;
import dominio.EstacionDeTransbordoMultimodal.EstadoEstacion;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;
import gestores.GestorEstacion;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Objects;

public class DarAltaEstacion{

    private static DarAltaEstacion singleton;
    private final JPanel panelDarAltaEstacion;
	private final GestorEstacion gestorEstacion;

    public JPanel getPanelDarAltaEstacion() {
        return panelDarAltaEstacion;
    }

    public static DarAltaEstacion getInstance(){
        if(singleton == null){
            singleton = new DarAltaEstacion();
        }
        return singleton;
    }

    private DarAltaEstacion() {
        panelDarAltaEstacion = new JPanel(new GridBagLayout());
        gestorEstacion = new GestorEstacion();

        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("ALTA DE ESTACION");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,10,20,0);
        panelDarAltaEstacion.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel labelNombre = new JLabel("Nombre: ");
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(10, 5 ,5 ,5);
        panelDarAltaEstacion.add(labelNombre,cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JTextField campoNombre = new JTextField();
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 2;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(5, 5 ,10 ,5);
        panelDarAltaEstacion.add(campoNombre,cons2);

        campoNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(campoNombre.getText().length()>29) {
                    e.consume();
                }
            }
        });

        GridBagConstraints cons3 = new GridBagConstraints();
        JLabel labelHApertura = new JLabel("Horario Apertura: ");
        cons3.gridx = 0;
        cons3.gridy = 3;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(10, 5 ,5 ,5);
        panelDarAltaEstacion.add(labelHApertura,cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JComboBox<String> campoAHora = new JComboBox<>();
        cons4.gridwidth = 1;
        cons4.gridx = 0;
        cons4.gridy = 4;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(5, 10 ,10 ,10);
        for(String a : horas)
            campoAHora.addItem(a);
        panelDarAltaEstacion.add(campoAHora,cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JLabel labelDosPuntos1 = new JLabel(": ");
        cons5.gridx = 1;
        cons5.gridy = 4;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.insets = new Insets(5, 0 ,5 ,150);
        panelDarAltaEstacion.add(labelDosPuntos1,cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        JComboBox<String> campoAMinutos = new JComboBox<>();
        cons6.gridwidth = 1;
        cons6.gridx = 1;
        cons6.gridy = 4;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(5, 15 ,10 ,60);

        for(String b : minutos)
            campoAMinutos.addItem(b);
        panelDarAltaEstacion.add(campoAMinutos,cons6);

        GridBagConstraints cons7 = new GridBagConstraints();
        JLabel labelHCierre = new JLabel("Horario Cierre: ");
        cons7.gridx = 0;
        cons7.gridy = 5;
        cons7.fill = GridBagConstraints.HORIZONTAL;
        cons7.insets = new Insets(10, 5 ,5 ,5);
        panelDarAltaEstacion.add(labelHCierre,cons7);

        GridBagConstraints cons8 = new GridBagConstraints();
        JComboBox<String> campoCHora = new JComboBox<>();
        cons8.gridwidth = 1;
        cons8.gridx = 0;
        cons8.gridy = 6;
        cons8.fill = GridBagConstraints.HORIZONTAL;
        cons8.insets = new Insets(5, 10 ,10 ,10);
        for(String c : horas)
            campoCHora.addItem(c);
        panelDarAltaEstacion.add(campoCHora,cons8);

        GridBagConstraints cons9 = new GridBagConstraints();
        JLabel labelDosPuntos2 = new JLabel(": ");
        cons9.gridx = 1;
        cons9.gridy = 6;
        cons9.fill = GridBagConstraints.HORIZONTAL;
        cons9.insets = new Insets(5, 0 ,5 ,150);
        panelDarAltaEstacion.add(labelDosPuntos2,cons9);

        GridBagConstraints cons10 = new GridBagConstraints();
        JComboBox<String> campoCMinutos = new JComboBox<>();
        cons10.gridwidth = 1;
        cons10.gridx = 1;
        cons10.gridy = 6;
        cons10.fill = GridBagConstraints.HORIZONTAL;
        cons10.insets = new Insets(5, 15 ,10 ,60);
        for(String d : minutos)
            campoCMinutos.addItem(d);
        panelDarAltaEstacion.add(campoCMinutos,cons10);

        GridBagConstraints cons11 = new GridBagConstraints();
        JLabel labelEstado = new JLabel("Estado: ");
        cons11.gridx = 0;
        cons11.gridy = 7;
        cons11.fill = GridBagConstraints.HORIZONTAL;
        cons11.insets = new Insets(30, 5 ,5 ,5);
        panelDarAltaEstacion.add(labelEstado,cons11);

        GridBagConstraints cons12 = new GridBagConstraints();
        JComboBox<String> campoEstado = new JComboBox<>();
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 8;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(5, 5 ,10 ,5);
        campoEstado.addItem("Operativa");
        campoEstado.addItem("Mantenimiento");
        panelDarAltaEstacion.add(campoEstado,cons12);

        GridBagConstraints cons13 = new GridBagConstraints();
        JButton botonAceptar = new JButton("Aceptar");
        cons13.gridwidth = 2;
        cons13.gridx = 0;
        cons13.gridy = 9;
        cons13.fill = GridBagConstraints.HORIZONTAL;
        cons13.insets = new Insets(60,0,20,0);
        panelDarAltaEstacion.add(botonAceptar,cons13);

        GridBagConstraints cons14 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons14.gridwidth = 2;
        cons14.gridx = 0;
        cons14.gridy = 10;
        cons14.fill = GridBagConstraints.HORIZONTAL;
        cons14.insets = new Insets(20,0,60,0);
        panelDarAltaEstacion.add(botonAtras,cons14);

        botonAceptar.addActionListener(e->
		{
			try 
			{
				String nombre = campoNombre.getText();
				LocalTime apertura = LocalTime.parse(campoAHora.getSelectedItem() + ":" + campoAMinutos.getSelectedItem());
				LocalTime cierre = LocalTime.parse(campoCHora.getSelectedItem() + ":" + campoCMinutos.getSelectedItem());
				//ESTADO
				EstadoEstacion estado;
				if (Objects.equals(campoEstado.getSelectedItem(), "Operativa"))
					estado = EstadoEstacion.OPERATIVA;
				else	
					estado = EstadoEstacion.MANTENIMIENTO;
				this.gestorEstacion.crearEstacion(nombre, apertura , cierre , estado);
			
			}
			catch (SQLException | BaseDeDatosException | CamposIncorrectosException e1)
			{
				e1.printStackTrace();
			}
        });

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarEstacion.getInstance().getPanelRegistroEstacion()));

    }
}
