package interfaces.registrarEstacion;

import interfaces.InterfazFrame;

//import javax.swing.JScrollPane;
import javax.swing.*;
import java.util.List;
import java.util.LinkedList;

import gestores.GestorEstacion;
import dominio.EstacionDeTransbordoMultimodal;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


import java.awt.*;

public class BuscarEstacionesTabla {

    private static BuscarEstacionesTabla singleton;
    private final JPanel panelBuscarEstacionesTabla;
    //
   	private GestorEstacion gestorEstacion;
   	private List<EstacionDeTransbordoMultimodal> estaciones;
    private ModeloTablaEstaciones modeloTablaEstaciones;
    private JTable tablaEstaciones;
   	
    public JPanel getPanelBuscarEstacionesTabla(String[] lista) {
        return panelBuscarEstacionesTabla;
    }

    public static BuscarEstacionesTabla getInstance(String[] lista){
        if(singleton == null){
            singleton = new BuscarEstacionesTabla(lista);
        }
        return singleton;
    }
    
    
    private BuscarEstacionesTabla(String[] lista) {
        panelBuscarEstacionesTabla = new JPanel(new GridBagLayout());
        //
        this.gestorEstacion = new GestorEstacion();
        this.estaciones = gestorEstacion.listarTodas();
        //
        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("TABLA ESTACIONES"); //?
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.ipady = 100;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
      //  cons0.insets = new Insets(55,0,40,0);
        cons0.insets = new Insets(40,0,10,0);
        panelBuscarEstacionesTabla.add(nombreMenu, cons0);


      
        GridBagConstraints cons10 = new GridBagConstraints();
       // GridBagConstraints cons11 = new GridBagConstraints();
 		modeloTablaEstaciones = new ModeloTablaEstaciones(estaciones);
 		tablaEstaciones = new JTable();
 		tablaEstaciones.setFont(new Font("Tahoma", Font.PLAIN, 12));
 		tablaEstaciones.setModel(modeloTablaEstaciones);
 		TableRowSorter<TableModel> orden =  new TableRowSorter<TableModel>(modeloTablaEstaciones);
 		tablaEstaciones.setRowSorter(orden);
 		cons10.gridwidth = 2;
        cons10.gridx = 0;
        cons10.gridy = 1;
        cons10.fill = GridBagConstraints.BOTH;
        cons10.insets = new Insets(15,0,40,0);
        panelBuscarEstacionesTabla.add(tablaEstaciones, cons10);
        

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 3;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(30,0,60,0);
        panelBuscarEstacionesTabla.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> InterfazFrame.setPanel(InterfazRegistrarEstacion.getInstance().getPanelRegistroEstacion()));
        
        
        LinkedList<RowFilter> listaDeFiltros = new LinkedList<RowFilter>();
		

        listaDeFiltros.add(RowFilter.regexFilter("",0));
		//if(!lista[1].isEmpty()) {
        if(!lista[0].isBlank()) {
			listaDeFiltros.add(RowFilter.regexFilter(lista[0].toString(),1));
			
		}
		//if(!lista[2].isEmpty()) {
        if(!lista[1].isBlank()) {
			listaDeFiltros.add(RowFilter.regexFilter(lista[1].toString(),2));
			
		}
		//if(!lista[3].isEmpty()) {
		if(!lista[2].isBlank()) {
			listaDeFiltros.add(RowFilter.regexFilter(lista[2].toString(),3));
			
		}
		
		if(lista[3] != "Ninguno") {
			if(lista[3] == "Operativa")
				lista[3] = "OPERATIVA";
			else
				lista[3] = "EN_MANTENIMIENTO";	
			
			listaDeFiltros.add(RowFilter.regexFilter(lista[3],4));
			
		}
		
		if(listaDeFiltros.size() != 0) {
			
			orden.setRowFilter(RowFilter.andFilter((Iterable)listaDeFiltros)); //?
			
		}
    }
}

