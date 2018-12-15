package richrail.ui.java;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DslWindow {
    private String selectedTrain;
    private String selectedComponent;

    private DrawingService drawingService;
    private TrainRegisterService trainRegisterService;

    public DslWindow(DrawingService drawingServiceUsed, TrainRegisterService trainRegisterServiceUsed){
        drawingService = drawingServiceUsed;
        trainRegisterService = trainRegisterServiceUsed;
        mainFrame();
    }

    private void mainFrame(){
        // Create frame for the main screen
        JFrame mainFrame = new JFrame("Rich Rail");
        mainFrame.getContentPane().setLayout(new BorderLayout());

        JTextArea output = new JTextArea(10, 40);
        JTextField input = new JTextField();

        mainFrame.add(output, BorderLayout.PAGE_START);
        mainFrame.add(input, BorderLayout.PAGE_END);
        // Finalize frame creation
        mainFrame.setSize(200, 200);
        mainFrame.setVisible(true);
    }
}
