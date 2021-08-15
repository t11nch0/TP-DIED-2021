package interfaces.registrarTransporte;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import dominio.LineaTransporte;

public class ModeloTablaLineas extends AbstractTableModel{


	public ModeloTablaLineas(List<LineaTransporte> lineas) {
    	this.data = lineas;
    }

    private final String[] columnNames =  {"Id","Nombre", "Color", "Estado"};
	private final List<LineaTransporte> data ;

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public int getRowCount() {
        return data.size();
    }
     
    public Object getValueAt(int row, int col) {
    
    	LineaTransporte l = data.get(row);
    	
            switch(col) {
    	        case 0:
    	        	return l.getId();
    	        case 1:
    	        	return l.getNombre();
    	        case 2:
    	        	return l.getColor();
    	        case 3:
    	        	return l.getEstado();    	        	
            }

        return null;
    }

}
