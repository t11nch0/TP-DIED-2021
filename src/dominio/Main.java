package dominio;
import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;
import javax.swing.*;

public class Main {

    public static void main(String[] args){

        try{

            JFrame.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

            javax.swing.SwingUtilities.invokeLater( () -> InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal()) );

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

