package interfaces;

import javax.swing.*;
import java.awt.*;

public class InterfazFrame {

    public static JFrame inicio = new JFrame("TP integrador DIED 2021");

    public static void setPanel(JPanel panelRecibido) {

        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //inicio.setResizable(false);

        /* Marca el tamanio de pantalla al 3/4 de la resolucion nativa*/

        int screenX = Toolkit.getDefaultToolkit().getScreenSize().width * 3 / 4;
        int screenY = Toolkit.getDefaultToolkit().getScreenSize().height * 3 / 4;

        inicio.setContentPane(panelRecibido);
        inicio.validate();
        inicio.pack();
        inicio.setSize(screenX, screenY);
        //inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);

    }
}
