
import javax.swing.UIManager;
import project.addmoto.database.Query;
import project.addmoto.view.Login;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Main {
    public static void main(String[] args) {
        Query query = new Query();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login(query).setVisible(true);
            }
        });
    }
}