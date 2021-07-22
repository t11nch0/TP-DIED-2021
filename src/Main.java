import interfaces.InterfazFrame;
import interfaces.InterfazPrincipal;

public class Main {

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
        public void run() {
            InterfazFrame interfaz = new InterfazFrame();
            InterfazFrame.setPanel(InterfazPrincipal.getInstance().getPanelMenuPrincipal());
            }
        });


    }
}

