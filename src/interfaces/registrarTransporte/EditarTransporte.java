package interfaces.registrarTransporte;

import interfaces.InterfazFrame;
import interfaces.registrarEstacion.BotonEditarEstacion;
import interfaces.registrarEstacion.InterfazRegistrarEstacion;

import javax.swing.*;

import dominio.EstacionDeTransbordoMultimodal;
import dominio.LineaTransporte;
import gestores.GestorLineaTransporte;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EditarTransporte {

    private static EditarTransporte singleton;
    private final JPanel panelEditarTransporte;
    private GestorLineaTransporte gestorLinea;
   	private List<LineaTransporte> lineas;

    public JPanel getPanelEditarTransporte() {
        return panelEditarTransporte;
    }

    public static EditarTransporte getInstance(){
        if(singleton == null){
            singleton = new EditarTransporte();
        }
        return singleton;
    }

    private EditarTransporte() {
        panelEditarTransporte = new JPanel(new GridBagLayout());
        this.gestorLinea = new GestorLineaTransporte();
        this.lineas = gestorLinea.listarTodas();

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("EDITAR TRANSPORTE");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,40,0);
        panelEditarTransporte.add(nombreMenu, cons0);

        GridBagConstraints cons2 = new GridBagConstraints();
        List<String> lista = new ArrayList<String>();
        for(LineaTransporte l: lineas) {
        	lista.add(l.getNombre());
        }
        String[] data = lista.toArray(new String[0]);
        
        JList<String> campoLista = new JList<>(data);
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.BOTH;
        cons2.insets = new Insets(10,0,40,0);
        panelEditarTransporte.add(campoLista, cons2);
/*
        GridBagConstraints cons3 = new GridBagConstraints();
        JScrollBar scrollLista = new JScrollBar(JScrollBar.VERTICAL, 1, 10, 0, 100);
        cons3.gridwidth = 0;
        cons3.gridheight = 4;
        cons3.gridx = 2;
        cons3.gridy = 3;
        cons3.fill = GridBagConstraints.BOTH;
        cons3.anchor = GridBagConstraints.WEST;
        //cons3.insets = new Insets(55,0,40,0);
        panelEditarEstacion.add(scrollLista,cons3);
 */
        GridBagConstraints cons11 = new GridBagConstraints();
        JButton botonEditar = new JButton("Editar");
        cons11.gridwidth = 2;
        cons11.gridx = 0;
        cons11.gridy = 2;
        cons11.fill = GridBagConstraints.HORIZONTAL;
        cons11.insets = new Insets(10,0,30,0);
        panelEditarTransporte.add(botonEditar,cons11);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 3;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(30,0,60,0);
        panelEditarTransporte.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarTransporte.getInstance().getPanelRegistrarTransporte()));

        botonEditar.addActionListener(e -> {
        	Integer index = campoLista.getSelectedIndex();
        	InterfazFrame.setPanel(BotonEditarTransporte.getInstance().getPanelBotonEditarTransporte(index));
        });
    }
}
