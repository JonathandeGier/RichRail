package richrail.ui.java;

import javax.swing.*;
import java.awt.*;

public class TrainPanel extends JPanel {

    private String trainName;

    public TrainPanel(String name) {
        trainName = name;
    }
    public Dimension getPreferredSize() {
        return new Dimension(100,100);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        //Make sure the track is painted first
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(25, 60, 20, 20, 20, 20);
        g.fillRoundRect(75, 60, 20, 20, 20, 20);
        g.fillRect(30, 30 , 10, 10);
        g.fillRect(29, 30 , 12, 2);
        g.setColor(Color.RED);
        g.fillRect(20, 40 , 80, 25);
        g.fillRect(70, 30 , 30, 10);
        g.setColor(Color.DARK_GRAY);
        g.drawString(trainName, 35, 55);
    }

}

