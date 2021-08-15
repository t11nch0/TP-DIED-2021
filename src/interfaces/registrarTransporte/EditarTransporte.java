package interfaces.registrarTransporte;

import interfaces.InterfazFrame;
import javax.swing.*;
import dominio.LineaTransporte;
import gestores.GestorLineaTransporte;
import java.awt.*;
import java.util.List;

public class EditarTransporte {

    private static EditarTransporte singleton;
    private final JPanel panelEditarTransporte;

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
        GestorLineaTransporte gestorLinea = new GestorLineaTransporte();
        List<LineaTransporte> transportes = gestorLinea.listarTodas();

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("EDITAR TRANSPORTE");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,40,0);
        panelEditarTransporte.add(nombreMenu, cons0);

        DefaultListModel<String> modelo = new DefaultListModel<>();
        for(LineaTransporte t: transportes) {
            modelo.addElement(t.getNombre());
        }
        if(modelo.isEmpty()){ modelo.add(0, "No hay transportes disponibles");}

        GridBagConstraints cons2 = new GridBagConstraints();
        JList<String> campoLista = new JList<>(modelo);
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.BOTH;
        cons2.insets = new Insets(10,0,40,0);
        panelEditarTransporte.add(campoLista, cons2);

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

        botonAtras.addActionListener(e -> {InterfazFrame.setPanel(InterfazRegistrarTransporte.getInstance().getPanelRegistrarTransporte()); singleton = null;});

        botonEditar.addActionListener(e -> {
        	Integer index = campoLista.getSelectedIndex();
            singleton = null;
        	InterfazFrame.setPanel(BotonEditarTransporte.getInstance(index).getPanelBotonEditarTransporte());
        });
    }
}
