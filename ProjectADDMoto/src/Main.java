
import javax.swing.UIManager;
import project.addmoto.view.UI_Login;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        } catch(Exception exc) {
            exc.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI_Login().setVisible(true);
            }
        });
    }
}