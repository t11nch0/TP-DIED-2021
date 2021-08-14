package interfaces.registrarEstacion;

import interfaces.InterfazFrame;

import javax.swing.*;

import dominio.EstacionDeTransbordoMultimodal;
import dominio.TareaMantenimiento;
import dominio.EstacionDeTransbordoMultimodal.EstadoEstacion;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;
import gestores.GestorEstacion;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HistorialDeMantenimientos {

    private static HistorialDeMantenimientos singleton;
    private final JPanel panelHistorialDeMantenimientos;
    //
	private GestorEstacion gestorEstacion;
   	private List<EstacionDeTransbordoMultimodal> estaciones;

    public JPanel getPanelHistorialDeMantenimientos() {
        return panelHistorialDeMantenimientos;
    }

    public static HistorialDeMantenimientos getInstance(){
        if(singleton == null){
            singleton = new HistorialDeMantenimientos();
        }
        return singleton;
    }

    private HistorialDeMantenimientos() {
        panelHistorialDeMantenimientos = new JPanel(new GridBagLayout());
        //
        this.gestorEstacion = new GestorEstacion();
        this.estaciones = gestorEstacion.listarTodas();
        //
        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("HISTORIAL DE MANTENIMIENTOS");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,20,0);
        panelHistorialDeMantenimientos.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel labelId = new JLabel("ESTACION: ");
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(10, 5 ,5 ,5);
        panelHistorialDeMantenimientos.add(labelId,cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JComboBox<String> campoEstacion = new JComboBox<>();
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 2;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(5, 5 ,10 ,5);
        for(EstacionDeTransbordoMultimodal e: estaciones) {
        	campoEstacion.addItem(e.getNombreEstacion());
        }
        panelHistorialDeMantenimientos.add(campoEstacion,cons2);

        GridBagConstraints cons11 = new GridBagConstraints();
        JButton botonBuscar = new JButton("Buscar");
        cons11.gridwidth = 2;
        cons11.gridx = 0;
        cons11.gridy = 3;
        cons11.fill = GridBagConstraints.HORIZONTAL;
        //cons11.insets = new Insets(40,0,30,0);
        cons11.insets = new Insets(10, 5, 5, 5);
        panelHistorialDeMantenimientos.add(botonBuscar,cons11);
        
        JList<String> listaLista = new JList<String>();
        DefaultListModel<String> modelo = new DefaultListModel<String>();
         
        listaLista.setModel(modelo);
        modelo.addElement("                                               SELECCIONE UNA ESTACION");
        modelo.addElement("                                                     Y PRESIONE BUSCAR");
        modelo.addElement("                   ");
        modelo.addElement("                   ");
        modelo.addElement("                   ");
        modelo.addElement("                   ");
        modelo.addElement("                   ");
        
      
        GridBagConstraints cons3 = new GridBagConstraints();
        cons3.gridwidth = 2;
        cons3.gridx = 0;
        cons3.gridy = 4;
        cons3.fill = GridBagConstraints.BOTH;
        cons3.insets = new Insets(10,0,40,0);
        panelHistorialDeMantenimientos.add(listaLista, cons3);

        
        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 12;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(30,0,60,0);
        panelHistorialDeMantenimientos.add(botonAtras,cons12);

        
        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarEstacion.getInstance().getPanelRegistroEstacion()));
        botonBuscar.addActionListener(e->
		{
			modelo.clear();
			int i = 1;
			for(TareaMantenimiento m: gestorEstacion.mantenimientosEstacion(estaciones.get(campoEstacion.getSelectedIndex()))){
	        	modelo.addElement("                                            Tarea de mantenimiento "+i);
	        	modelo.addElement("Fecha Inicio: "+m.getFechaInicio().toString());
	    
	        	//if(m.getFechaFin().toString().isEmpty())
	        	if(m.getFechaFin() == null)
	        		modelo.addElement("Fecha Fin: -");
	        	else
	        		modelo.addElement("Fecha Fin: "+m.getFechaFin().toString());
	        	modelo.addElement("Observaciones: "+m.getObservaciones());
	        	i++;
	        }
			listaLista.setModel(modelo);
			
		});
        
    }
}
