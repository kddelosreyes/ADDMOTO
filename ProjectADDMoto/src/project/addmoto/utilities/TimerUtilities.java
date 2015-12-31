package project.addmoto.utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class TimerUtilities {
    
    public static void runTime(JLabel timeLabel) {
        
        ActionListener timerListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Date date = new Date();
                timeLabel.setText(Formatter.formatDate(date));
            }
        };
        
        javax.swing.Timer timer = new javax.swing.Timer(1000, timerListener);
        timer.setInitialDelay(0);
        timer.start();
    }
}