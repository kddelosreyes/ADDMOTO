
import com.alee.laf.WebLookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import project.addmoto.database.Query;
import project.addmoto.view.Login;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Main {

    public static void main(String[] args) {
        Query query = new Query();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //WebLookAndFeel.install();
                new Login(query).setVisible(true);
            }
        });
    }
}
