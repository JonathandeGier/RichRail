package richrail.ui.java;

import java.awt.*;
import javax.swing.*;

public class CargoWagonPanel extends JPanel {

    public Dimension getPreferredSize() {
        return new Dimension(100,200);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        //Make sure the track is painted first
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(25, 60, 20, 20, 20, 20);
        g.fillRoundRect(70, 60, 20, 20, 20, 20);
        g.drawString("Barry2", 40, 50);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(20,55, 80,20);
    }
}
