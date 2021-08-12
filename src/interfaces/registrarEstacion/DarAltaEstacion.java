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

public class DarAltaEstacion {

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

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("ALTA DE ESTACION");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,20,0);
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
        JTextField campoHApertura = new JTextField();
        cons4.gridwidth = 2;
        cons4.gridx = 0;
        cons4.gridy = 4;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(5, 5 ,10 ,5);
        panelDarAltaEstacion.add(campoHApertura,cons4);

        GridBagConstraints cons5 = new GridBagConstraints();
        JLabel labelHCierre = new JLabel("Horario Cierre: ");
        cons5.gridx = 0;
        cons5.gridy = 5;
        cons5.fill = GridBagConstraints.HORIZONTAL;
        cons5.insets = new Insets(10, 5 ,5 ,5);
        panelDarAltaEstacion.add(labelHCierre,cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        JTextField campoHCierre = new JTextField();
        cons6.gridwidth = 2;
        cons6.gridx = 0;
        cons6.gridy = 6;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(5, 5 ,10 ,5);
        panelDarAltaEstacion.add(campoHCierre,cons6);

        GridBagConstraints cons7 = new GridBagConstraints();
        JLabel labelEstado = new JLabel("Estado: ");
        cons7.gridx = 0;
        cons7.gridy = 7;
        cons7.fill = GridBagConstraints.HORIZONTAL;
        cons7.insets = new Insets(10, 5 ,5 ,5);
        panelDarAltaEstacion.add(labelEstado,cons7);

        GridBagConstraints cons8 = new GridBagConstraints();
        JComboBox<String> campoEstado = new JComboBox<>();
        cons8.gridwidth = 2;
        cons8.gridx = 0;
        cons8.gridy = 8;
        cons8.fill = GridBagConstraints.HORIZONTAL;
        cons8.insets = new Insets(5, 5 ,10 ,5);
        campoEstado.addItem("Operativa");
        campoEstado.addItem("Mantenimiento");
        panelDarAltaEstacion.add(campoEstado,cons8);

        GridBagConstraints cons9 = new GridBagConstraints();
        JButton botonAceptar = new JButton("Aceptar");
        cons9.gridwidth = 2;
        cons9.gridx = 0;
        cons9.gridy = 9;
        cons9.fill = GridBagConstraints.HORIZONTAL;
        cons9.insets = new Insets(40,0,30,0);
        panelDarAltaEstacion.add(botonAceptar,cons9);

        GridBagConstraints cons10 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons10.gridwidth = 2;
        cons10.gridx = 0;
        cons10.gridy = 10;
        cons10.fill = GridBagConstraints.HORIZONTAL;
        cons10.insets = new Insets(30,0,60,0);
        panelDarAltaEstacion.add(botonAtras,cons10);

        botonAceptar.addActionListener(e->
		{
			try 
			{
				String nombre = campoNombre.getText();
				LocalTime apertura = LocalTime.parse(campoHApertura.getText());
				LocalTime cierre = LocalTime.parse(campoHCierre.getText());
				//ESTADO
				EstadoEstacion estado;
				if (Objects.equals((String) campoEstado.getSelectedItem(), "Operativa"))
					estado = EstadoEstacion.OPERATIVA;
				else	
					estado = EstadoEstacion.MANTENIMIENTO;
				this.gestorEstacion.crearEstacion(nombre, apertura, cierre, estado);
			
			}
			catch (SQLException | BaseDeDatosException | CamposIncorrectosException e1)
			{
				e1.printStackTrace();
			}
        });

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarEstacion.getInstance().getPanelRegistroEstacion()));

    }
}
