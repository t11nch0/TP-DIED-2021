package interfaces.ventaBoleto;

import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

import javax.swing.*;

import dominio.Camino;
import dominio.EstacionDeTransbordoMultimodal;
import dominio.LineaTransporte;
import dominio.Ruta;
import dominio.Trayecto;
import excepciones.BaseDeDatosException;
import excepciones.CamposIncorrectosException;
import gestores.GestorBoleto;
import gestores.GestorCamino;
import gestores.GestorEstacion;
import gestores.GestorLineaTransporte;
import gestores.GestorRuta;
import gestores.GestorTrayecto;
import dominio.LineaTransporte.EstadoLinea;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class InterfazVentaBoleto{

    private static InterfazVentaBoleto singleton;
    private final JPanel panelVenta;
	private GestorEstacion gestorEstacion;
	private GestorCamino gestorCamino;
	private GestorBoleto gestorBoleto;
	private GestorTrayecto gestorTrayecto;
	private GestorLineaTransporte gestorLinea;
	private GestorRuta gestorRuta;
	private List<EstacionDeTransbordoMultimodal> estaciones;
	private List<Camino> caminos;

    public JPanel getPanelVenta() {
        return panelVenta;
    }

    public static InterfazVentaBoleto getInstance(){
        if(singleton == null){
            singleton= new InterfazVentaBoleto();
        }
        return singleton;
    }

    private InterfazVentaBoleto() {
        panelVenta= new JPanel(new GridBagLayout());
        this.gestorEstacion = new GestorEstacion();
        this.gestorCamino = new GestorCamino();
        this.gestorBoleto = new GestorBoleto();
        this.gestorTrayecto = new GestorTrayecto();
        this.gestorLinea = new GestorLineaTransporte();
        this.gestorRuta = new GestorRuta();
        this.estaciones = gestorEstacion.listarTodas();
      //  this.gestorTrayecto.relacionarConLineas();

        GridBagConstraints cons0 = new GridBagConstraints();
        JLabel nombreMenu = new JLabel("VENTA DE BOLETO");
        nombreMenu.setFont(new Font("Dialog", Font.BOLD, 25));
        cons0.gridwidth = 2;
        cons0.gridx = 0;
        cons0.gridy = 0;
        cons0.fill = GridBagConstraints.HORIZONTAL;
        cons0.insets = new Insets(55,0,20,0);
        panelVenta.add(nombreMenu, cons0);

        GridBagConstraints cons1 = new GridBagConstraints();
        JLabel nombreOrigen = new JLabel("Estacion de Origen: ");
        nombreOrigen.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons1.gridwidth = 2;
        cons1.gridx = 0;
        cons1.gridy = 1;
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.insets = new Insets(10,0,35,0);
        panelVenta.add(nombreOrigen, cons1);

        GridBagConstraints cons2 = new GridBagConstraints();
        JComboBox<String> campoEstacionOrigen = new JComboBox<>();
        cons2.gridwidth = 2;
        cons2.gridx = 0;
        cons2.gridy = 1;
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.insets = new Insets(35, 5 ,0 ,5);
        campoEstacionOrigen.addItem("Seleccionar estacion...");
        for(EstacionDeTransbordoMultimodal e: estaciones) {
        	campoEstacionOrigen.addItem(e.getNombreEstacion());
        	System.out.println(e.getNombreEstacion());
        }
        panelVenta.add(campoEstacionOrigen,cons2);

        GridBagConstraints cons3 = new GridBagConstraints();
        JLabel nombreDestino = new JLabel("Estacion de Destino: ");
        nombreDestino.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons3.gridwidth = 2;
        cons3.gridx = 0;
        cons3.gridy = 2;
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.insets = new Insets(10,0,35,0);
        panelVenta.add(nombreDestino, cons3);

        GridBagConstraints cons4 = new GridBagConstraints();
        JComboBox<String> campoEstacionDestino = new JComboBox<>();
        cons4.gridwidth = 2;
        cons4.gridx = 0;
        cons4.gridy = 2;
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.insets = new Insets(35, 5 ,0 ,5);
        campoEstacionDestino.addItem("Seleccionar estacion...");
        for(EstacionDeTransbordoMultimodal e: estaciones) {
        	campoEstacionDestino.addItem(e.getNombreEstacion());
        }
        panelVenta.add(campoEstacionDestino,cons4);

        JList<String> listaRapido = new JList<>();
        DefaultListModel<String> modeloRapido = new DefaultListModel<>();
        listaRapido.setModel(modeloRapido);
        modeloRapido.addElement(" ");
        
        JList<String> listaCorto = new JList<>();
        DefaultListModel<String> modeloCorto = new DefaultListModel<>();
        listaCorto.setModel(modeloCorto);
        modeloCorto.addElement(" ");
        
        JList<String> listaBarato = new JList<>();
        DefaultListModel<String> modeloBarato = new DefaultListModel<>();
        listaBarato.setModel(modeloBarato);
        modeloBarato.addElement(" ");

        GridBagConstraints cons5 = new GridBagConstraints();
        JTabbedPane paneles = new JTabbedPane();
        cons5.ipadx = 150;
        cons5.gridx = 0;
        cons5.gridy = 3;
        cons5.fill = GridBagConstraints.BOTH;
        cons5.insets = new Insets(10,0,20,0);
        paneles.addTab("Mas rapido", listaRapido);
        paneles.addTab("Menor Distancia", listaCorto);
        paneles.addTab("Mas Barato", listaBarato);
        panelVenta.add(paneles, cons5);

        GridBagConstraints cons6 = new GridBagConstraints();
        JButton botonBuscar = new JButton("Buscar");
        cons6.gridwidth = 2;
        cons6.gridx = 0;
        cons6.gridy = 6;
        cons6.fill = GridBagConstraints.HORIZONTAL;
        cons6.insets = new Insets(10,40,20,40);
        panelVenta.add(botonBuscar,cons6);

        GridBagConstraints cons7 = new GridBagConstraints();
        JLabel nombreEstaciones = new JLabel("Lista de estaciones: ");
        nombreEstaciones.setFont(new Font("Dialog", Font.PLAIN, 15));
        cons7.gridwidth = 2;
        cons7.gridx = 0;
        cons7.gridy = 7;
        cons7.fill = GridBagConstraints.HORIZONTAL;
        cons7.insets = new Insets(10,0,35,0);
        panelVenta.add(nombreEstaciones, cons7);

        DefaultListModel<String> modelo = new DefaultListModel<>();
        /*
        for(EstacionDeTransbordoMultimodal e: estaciones) {
            modelo.addElement(e.getNombreEstacion());
        }
         */
        if(modelo.isEmpty()){ modelo.add(0, "No hay estaciones disponibles");}

        GridBagConstraints cons8 = new GridBagConstraints();
        JList<String> campoListaEstaciones = new JList<>(modelo);
        cons8.gridwidth = 2;
        cons8.gridx = 0;
        cons8.gridy = 7;
        cons8.fill = GridBagConstraints.HORIZONTAL;
        cons8.insets = new Insets(35, 5 ,0 ,5);
        panelVenta.add(campoListaEstaciones,cons8);
        
        GridBagConstraints cons9 = new GridBagConstraints();
        JButton botonComprar = new JButton("Comprar");
        cons9.gridwidth = 2;
        cons9.gridx = 0;
        cons9.gridy = 8;
        cons9.fill = GridBagConstraints.HORIZONTAL;
        cons9.insets = new Insets(35,0,30,0);
        panelVenta.add(botonComprar,cons9);

        GridBagConstraints cons12 = new GridBagConstraints();
        JButton botonAtras = new JButton("Atras");
        cons12.gridwidth = 2;
        cons12.gridx = 0;
        cons12.gridy = 9;
        cons12.fill = GridBagConstraints.HORIZONTAL;
        cons12.insets = new Insets(30,30,60,30);
        panelVenta.add(botonAtras,cons12);

        botonAtras.addActionListener(e -> {InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()); singleton = null;});

        botonBuscar.addActionListener(e -> {
        	modeloRapido.clear();
        	modeloCorto.clear();
        	modeloBarato.clear();
        	int i = 1;

        	for(EstacionDeTransbordoMultimodal estacionO: estaciones) {
            	
        		if(campoEstacionOrigen.getSelectedItem().equals(estacionO.getNombreEstacion())) //?
            		{
            		for(EstacionDeTransbordoMultimodal estacionD: estaciones)
            			if(campoEstacionDestino.getSelectedItem().equals(estacionD.getNombreEstacion())) {
    
            			Camino caminoRapido = gestorCamino.caminoMasRapido(estacionO, estacionD);
            			Camino caminoCorto = gestorCamino.caminoMasCorto(estacionO, estacionD);
            			Camino caminoBarato = gestorCamino.caminoMasBarato(estacionO, estacionD);

            			modeloRapido.addElement("Duracion total: "+caminoRapido.getDuracionTotal()+" minutos");
            			modeloRapido.addElement("Distancia total: "+caminoRapido.getDistanciaTotal() +" kilometros");
            			modeloRapido.addElement("Costo total: $"+caminoRapido.getCostoTotal());
            			modeloRapido.addElement(" ");

            			
            			modeloCorto.addElement("Duracion total: "+caminoCorto.getDuracionTotal()+" minutos");
            			modeloCorto.addElement("Distancia total: "+caminoCorto.getDistanciaTotal() +" kilometros");
            			modeloCorto.addElement("Costo total: $"+caminoCorto.getCostoTotal());
            			modeloCorto.addElement(" ");
            			
            			modeloBarato.addElement("Duracion total: "+caminoBarato.getDuracionTotal()+" minutos");
            			modeloBarato.addElement("Distancia total: "+caminoBarato.getDistanciaTotal() +" kilometros");
            			modeloBarato.addElement("Costo total: $"+caminoBarato.getCostoTotal());
            			modeloBarato.addElement(" ");
            			
            			for(Ruta r: caminoRapido.getRutas()) {
        		        	modeloRapido.addElement(i+"er ruta: "+r.getOrigen().getNombreEstacion()+" -> "+r.getDestino().getNombreEstacion()
        		        			+" por "+gestorLinea.buscarPorId(r.getTrayecto().getIdLinea()).getNombre()+" - "+gestorLinea.buscarPorId(r.getTrayecto().getIdLinea()).getColor());
        		        	i++;
        				}
        				i = 1;
        				for(Ruta r: caminoCorto.getRutas()) {
        					System.out.println(r.getEstadoRuta());
        					modeloCorto.addElement(i+"er ruta: "+r.getOrigen().getNombreEstacion()+" -> "+r.getDestino().getNombreEstacion()
        		        			+" por "+gestorLinea.buscarPorId(r.getTrayecto().getIdLinea()).getNombre()+" - "+gestorLinea.buscarPorId(r.getTrayecto().getIdLinea()).getColor());
		        					//+" por "+r.getTrayecto().getLinea().getNombre()+" - "+r.getTrayecto().getLinea().getColor());

        					i++;
        		        	
        				}
        				i = 1;
        				for(Ruta r: caminoBarato.getRutas()) {
        		        	modeloBarato.addElement(i+"er ruta: "+r.getOrigen().getNombreEstacion()+" -> "+r.getDestino().getNombreEstacion()
        		        			+" por "+gestorLinea.buscarPorId(r.getTrayecto().getIdLinea()).getNombre()+" - "+gestorLinea.buscarPorId(r.getTrayecto().getIdLinea()).getColor());
        		        	i++;
        				}
            		}
            		}
            }
        	listaRapido.setModel(modeloRapido);
        	listaCorto.setModel(modeloCorto);
        	listaBarato.setModel(modeloBarato);        	
        });
        botonComprar.addActionListener(e->
		{
			try 
			{
                InterfazFrame.setPanel(InterfazComprarBoleto.getInstance().getPanelCompraBoleto());

				this.gestorBoleto.crearBoleto(null, null, null, null, null);

			}
			catch (SQLException | BaseDeDatosException | CamposIncorrectosException e1)
			{
				e1.printStackTrace();
			}
		});
        
        //? verificar que este seleccionado tambien un origen?
        campoEstacionDestino.addActionListener(e -> {
        	//gestorEstacion.buscarPorId(idEstacion) ??? para obtener la estacion
        	
        	
        	/*
        	System.out.println("Index origen: "+campoEstacionOrigen.getSelectedIndex());
        	System.out.println("Item origen: "+campoEstacionOrigen.getSelectedItem());
        	System.out.println("Index origenN: "+estaciones.get(campoEstacionOrigen.getSelectedIndex()-1).getNombreEstacion());
        	System.out.println("Index destino: "+campoEstacionDestino.getSelectedIndex());
        	System.out.println("Item destino: "+campoEstacionDestino.getSelectedItem());
        	System.out.println("Index origenN: "+estaciones.get(campoEstacionDestino.getSelectedIndex()-1).getNombreEstacion());
        	*/
        	//caminos: todos los caminos posibles entre estacion origen y desitno
        	caminos = gestorCamino.todosCaminos(estaciones.get(campoEstacionOrigen.getSelectedIndex()-1), estaciones.get(campoEstacionDestino.getSelectedIndex()-1));
        	
        	// MOSTRAR EN UNA TABLA. Se selecciona un camino. y luego COMPRAR.
        	
        });
        
    }
}