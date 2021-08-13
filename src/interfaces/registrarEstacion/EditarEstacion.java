package interfaces.registrarEstacion;

import interfaces.InterfazFrame;
import javax.swing.*;
import dominio.EstacionDeTransbordoMultimodal;
import gestores.GestorEstacion;
import java.awt.*;
import java.util.List;

public class EditarEstacion {

    private static EditarEstacion singleton;
    private final JPanel panelEditarEstacion;

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
        GestorEstacion gestorEstacion = new GestorEstacion();
        List<EstacionDeTransbordoMultimodal> estaciones = gestorEstacion.listarTodas();
        
        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("EDITAR ESTACION");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,40,0);
        panelEditarEstacion.add(nombreMenu, cons0);

        DefaultListModel<String> modelo = new DefaultListModel<>();
        for(EstacionDeTransbordoMultimodal e: estaciones) {
            modelo.addElement(e.getNombreEstacion());
        }
        if(modelo.isEmpty()){ modelo.add(0, "No hay estaciones disponibles");}

        GridBagConstraints cons1 = new GridBagConstraints();
        JList<String> campoLista = new JList<>(modelo);
        cons1.gridwidth = 2;
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.BOTH;
        cons1.insets = new Insets(10,0,40,0);
        panelEditarEstacion.add(campoLista, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JButton botonEditar = new JButton("Editar");
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 2;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(10,0,30,0);
        panelEditarEstacion.add(botonEditar,cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons3.gridwidth = 2;
        cons3.gridx = 0;
        cons3.gridy = 3;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(30,0,60,0);
        panelEditarEstacion.add(botonAtras,cons3);


        botonAtras.addActionListener(e -> {InterfazFrame.setPanel(InterfazRegistrarEstacion.getInstance().getPanelRegistroEstacion()); singleton=null;});

        botonEditar.addActionListener(e -> {
        	Integer index = campoLista.getSelectedIndex();
            singleton=null;
			InterfazFrame.setPanel(BotonEditarEstacion.getInstance(index).getPanelBotonEditarEstacion());
			});
    }

}
