package interfaces.registrarEstacion;

import interfaces.InterfazFrame;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import gestores.GestorEstacion;
import dominio.EstacionDeTransbordoMultimodal;
//import dominio.EstacionDeTransbordoMultimodal.EstadoEstacion;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;

import java.awt.*;
import java.sql.SQLException;

public class DarBajaEstacion {

    private static DarBajaEstacion singleton;
    private final JPanel panelDarBajaEstacion;
    //
   	private GestorEstacion gestorEstacion;
   	private List<EstacionDeTransbordoMultimodal> estaciones; //?
   	
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
        //
        this.gestorEstacion = new GestorEstacion();
        this.estaciones = gestorEstacion.listarTodas();
        //
        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("DAR BAJA ESTACION");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,40,0);
        panelDarBajaEstacion.add(nombreMenu, cons0);

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
        cons2.insets = new Insets(15,0,40,0);
        panelDarBajaEstacion.add(campoLista, cons2);

        GridBagConstraints cons11 = new GridBagConstraints();
        JButton botonEliminar = new JButton("Eliminar");
        cons11.gridwidth = 2;
        cons11.gridx = 0;
        cons11.gridy = 2;
        cons11.fill = GridBagConstraints.HORIZONTAL;
        cons11.insets = new Insets(10,0,30,0);
        panelDarBajaEstacion.add(botonEliminar,cons11);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 3;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(30,0,60,0);
        panelDarBajaEstacion.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarEstacion.getInstance().getPanelRegistroEstacion()));
        
        
        botonEliminar.addActionListener(e->
      		{
      			try 
      			{
      				/*Integer id;
      				for(EstacionDeTransbordoMultimodal est: estaciones)
      					if(campoLista.getSelectedValue() == est.getNombreEstacion())
      						id = est.getId();*/
      				
      				Integer index = campoLista.getSelectedIndex();
      				this.gestorEstacion.eliminarEstacion(estaciones.get(index));
      			}
      			catch (SQLException | BaseDeDatosException e1) 
      			{
      				e1.printStackTrace();
      			}
      			catch (CamposIncorrectosException e2)
      			{
      				e2.printStackTrace();
      			}
      		});
        
    }
}
