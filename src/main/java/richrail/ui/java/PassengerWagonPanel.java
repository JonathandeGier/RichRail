package richrail.ui.java;

import javax.swing.*;
import java.awt.*;

public class PassengerWagonPanel extends JPanel {
    private String wagonName;

    public PassengerWagonPanel(String name) {
        wagonName = name;
    }
    public Dimension getPreferredSize() {
        return new Dimension(100,100);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        //Make sure the track is painted first
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(25, 60, 20, 20, 20, 20);
        g.fillRoundRect(70, 60, 20, 20, 20, 20);
        g.setColor(Color.ORANGE);
        g.fillRect(20,30, 80,35);
        g.setColor(Color.blue);
        g.fillRect(20,60, 80,2);
        g.setColor(Color.cyan);
        g.fillRect(25,35, 14,10);
        g.fillRect(44,35, 14,10);
        g.fillRect(63,35, 14,10);
        g.fillRect(82,35, 14,10);
        g.setColor(Color.DARK_GRAY);
        g.drawString(wagonName, 40, 50);
    }
}


