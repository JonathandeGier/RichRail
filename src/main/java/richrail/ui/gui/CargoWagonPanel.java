package richrail.ui.gui;

import java.awt.*;
import javax.swing.*;

public class CargoWagonPanel extends JPanel {

    private String wagonName;

    public CargoWagonPanel(String name) {
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
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(20,45, 80,20);
        g.setColor(Color.DARK_GRAY);
        g.drawString(wagonName, 40, 50);
    }
}
