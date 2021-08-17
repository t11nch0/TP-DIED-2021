package interfaces.registrarEstacion;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import dominio.EstacionDeTransbordoMultimodal;

public class ModeloTablaEstaciones extends AbstractTableModel {


    public ModeloTablaEstaciones(List<EstacionDeTransbordoMultimodal> estaciones) {
        this.data = estaciones;
    }

    private final String[] columnNames = {"Id", "Nombre", "Hora Apertura", "Hora Cierre", "Estado"};
    private final List<EstacionDeTransbordoMultimodal> data;

    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public int getRowCount() {
        return data.size();
    }

    public Object getValueAt(int row, int col) {

        EstacionDeTransbordoMultimodal e = data.get(row);

        switch (col) {
            case 0:
                return e.getId();
            case 1:
                return e.getNombreEstacion();
            case 2:
                return e.getHorarioApertura();
            case 3:
                return e.getHorarioCierre();
            case 4:
                return e.getEstado();
        }
        return null;
    }

}

