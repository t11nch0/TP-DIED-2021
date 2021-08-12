package interfaces.registrarEstacion;

import interfaces.InterfazFrame;

import javax.swing.*;

import dominio.EstacionDeTransbordoMultimodal;
import dominio.TareaMantenimiento;
import gestores.GestorEstacion;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EditarEstacion {

    private static EditarEstacion singleton;
    private final JPanel panelEditarEstacion;
   	private GestorEstacion gestorEstacion;
   	private List<EstacionDeTransbordoMultimodal> estaciones;

    public JPanel getPanelEditarEstacion() {
        return panelEditarEstacion;
    }

    public static EditarEstacion getInstance(){
        if(singleton == null){
            singleton = new EditarEstacion();
        }
        return singleton;
    }

    private EditarEstacion() {
        panelEditarEstacion = new JPanel(new GridBagLayout());
        this.gestorEstacion = new GestorEstacion();
        this.estaciones = gestorEstacion.listarTodas();
        
        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("EDITAR ESTACION");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,40,0);
        panelEditarEstacion.add(nombreMenu, cons0);

        GridBagConstraints cons2 = new GridBagConstraints();
        List<String> lista = new ArrayList<String>();
        for(EstacionDeTransbordoMultimodal e: estaciones) {
        	lista.add(e.getNombreEstacion());
        }
        String[] data = lista.toArray(new String[0]);
              
        JList<String> campoLista = new JList<>(data);
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.BOTH;
        cons2.insets = new Insets(10,0,40,0);
        panelEditarEstacion.add(campoLista, cons2);

        GridBagConstraints cons11 = new GridBagConstraints();
        JButton botonEditar = new JButton("Editar");
        cons11.gridwidth = 2;
        cons11.gridx = 0;
        cons11.gridy = 2;
        cons11.fill = GridBagConstraints.HORIZONTAL;
        cons11.insets = new Insets(10,0,30,0);
        panelEditarEstacion.add(botonEditar,cons11);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 3;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(30,0,60,0);
        panelEditarEstacion.add(botonAtras,cons12);

        //

      /*  for(EstacionDeTransbordoMultimodal est: estaciones) {
        	System.out.println("ESTACION: "+est.getNombreEstacion());
        	for(TareaMantenimiento m: gestorEstacion.mantenimientosEstacion(est)) {
        		System.out.println("------------");
        		System.out.println("id: "+m.getId());
        		System.out.println("obs: "+m.getObservaciones());
        		System.out.println("inicio: "+m.getFechaInicio());
        		System.out.println("fin: "+m.getFechaFin());
        	}
        }*/
        //
        
        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarEstacion.getInstance().getPanelRegistroEstacion()));

        botonEditar.addActionListener(e -> {
        	Integer index = campoLista.getSelectedIndex();
			InterfazFrame.setPanel(BotonEditarEstacion.getInstance().getPanelBotonEditarEstacion(index));
			});
    }

}
