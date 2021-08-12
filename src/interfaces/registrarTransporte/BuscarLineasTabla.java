package interfaces.registrarTransporte;

import interfaces.InterfazFrame;

//import javax.swing.JScrollPane;
import javax.swing.*;
import java.util.List;
import java.util.LinkedList;

import gestores.GestorLineaTransporte;
import dominio.LineaTransporte;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


import java.awt.*;

public class BuscarLineasTabla {

    private static BuscarLineasTabla singleton;
    private final JPanel panelBuscarLineasTabla;
    //
    private ModeloTablaLineas modeloTablaLineas;
    private JTable tablaLineas;
    private GestorLineaTransporte gestorLinea;
    private List<LineaTransporte> lineas;
   	
    public JPanel getPanelBuscarLineasTabla(String[] lista) {
        return panelBuscarLineasTabla;
    }

    public static BuscarLineasTabla getInstance(String[] lista){
        if(singleton == null){
            singleton = new BuscarLineasTabla(lista);
        }
        return singleton;
    }
    
    
    private BuscarLineasTabla(String[] lista) {
        panelBuscarLineasTabla = new JPanel(new GridBagLayout());
        //
        this.gestorLinea = new GestorLineaTransporte();
        this.lineas = gestorLinea.listarTodas();
        //
        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("TABLA LINEAS"); //?
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.ipady = 100;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
      //  cons0.insets = new Insets(55,0,40,0);
        cons0.insets = new Insets(40,0,10,0);
        panelBuscarLineasTabla.add(nombreMenu, cons0);


      
        GridBagConstraints cons10 = new GridBagConstraints();
       // GridBagConstraints cons11 = new GridBagConstraints();
 		modeloTablaLineas = new ModeloTablaLineas(lineas);
 		tablaLineas = new JTable();
 		tablaLineas.setFont(new Font("Tahoma", Font.PLAIN, 12));
 		tablaLineas.setModel(modeloTablaLineas);
 		TableRowSorter<TableModel> orden =  new TableRowSorter<TableModel>(modeloTablaLineas);
 		tablaLineas.setRowSorter(orden);
 		cons10.gridwidth = 2;
        cons10.gridx = 0;
        cons10.gridy = 1;
        cons10.fill = GridBagConstraints.BOTH;
        cons10.insets = new Insets(15,0,40,0);
        panelBuscarLineasTabla.add(tablaLineas, cons10);
        

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 3;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(30,0,60,0);
        panelBuscarLineasTabla.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarTransporte.getInstance().getPanelRegistrarTransporte()));
        
        
        LinkedList<RowFilter> listaDeFiltros = new LinkedList<RowFilter>();
		

        listaDeFiltros.add(RowFilter.regexFilter("",0));
		//if(!lista[1].isEmpty()) {
        if(!lista[0].isBlank()) {
        	System.out.println(lista[0]);
			listaDeFiltros.add(RowFilter.regexFilter(lista[0].toString(),1));
			
		}
		//if(!lista[2].isEmpty()) {
        if(!lista[1].isBlank()) {
        	System.out.println(lista[1]);
			listaDeFiltros.add(RowFilter.regexFilter(lista[1].toString(),2));
			
		}
		
		if(lista[2] != "Ninguno") {
			System.out.println(lista[2]);
			if(lista[2] == "Activa")
				lista[2] = "ACTIVA";
			else
				lista[2] = "NO_ACTIVA";	
			
			listaDeFiltros.add(RowFilter.regexFilter(lista[2],3));
			
		}
		
		if(listaDeFiltros.size() != 0) {
			
			orden.setRowFilter(RowFilter.andFilter((Iterable)listaDeFiltros)); //?
			
		}
    }
}
