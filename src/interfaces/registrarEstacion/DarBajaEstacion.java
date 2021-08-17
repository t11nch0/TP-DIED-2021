package interfaces.registrarEstacion;

import interfaces.InterfazFrame;
import javax.swing.*;
import java.util.List;
import gestores.GestorEstacion;
import dominio.EstacionDeTransbordoMultimodal;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;
import java.awt.*;
import java.sql.SQLException;

public class DarBajaEstacion {

    private static DarBajaEstacion singleton;
    private final JPanel panelDarBajaEstacion;
   	private final GestorEstacion gestorEstacion;
   	private final List<EstacionDeTransbordoMultimodal> estaciones;
   	
    public JPanel getPanelDarBajaEstacion() {
        return panelDarBajaEstacion;
    }

    public static DarBajaEstacion getInstance(){
        if(singleton == null){
            singleton = new DarBajaEstacion();
        }
        return singleton;
    }

    private DarBajaEstacion() {
        panelDarBajaEstacion = new JPanel(new GridBagLayout());
        gestorEstacion = new GestorEstacion();
        estaciones = gestorEstacion.listarTodas();

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("DAR BAJA ESTACION");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,40,0);
        panelDarBajaEstacion.add(nombreMenu, cons0);

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
        cons1.insets = new Insets(15,0,40,0);
        panelDarBajaEstacion.add(campoLista, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JButton botonEliminar = new JButton("Eliminar");
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 2;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(10,0,30,0);
        panelDarBajaEstacion.add(botonEliminar,cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons3.gridwidth = 2;
        cons3.gridx = 0;
        cons3.gridy = 3;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(30,0,60,0);
        panelDarBajaEstacion.add(botonAtras,cons3);

        botonAtras.addActionListener(e -> {InterfazFrame.setPanel(InterfazRegistrarEstacion.getInstance().getPanelRegistroEstacion()); singleton = null;});

        botonEliminar.addActionListener(e->
      		{
      			try 
      			{

      			    int index = campoLista.getSelectedIndex();
      				this.gestorEstacion.eliminarEstacion(estaciones.get(index));
      				modelo.remove(index);
                    campoLista.setModel(modelo);

                    if(modelo.isEmpty()){ modelo.add(0, "No hay estaciones disponibles");}

      				InterfazFrame.setPanel(DarBajaEstacion.getInstance().getPanelDarBajaEstacion());

      			}
      			catch (SQLException | BaseDeDatosException | CamposIncorrectosException e1)
      			{
      				e1.printStackTrace();
      			}
            });
    }
}
