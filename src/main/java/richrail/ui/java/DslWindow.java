package richrail.ui.java;

import richrail.service.TreinEventListener;
import richrail.ui.dsl.DSLMain;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DslWindow implements TreinEventListener {
    private String selectedTrain;
    private String selectedComponent;

    private DrawingService drawingService;
    private TrainRegisterService trainRegisterService;

    JTextArea output = new JTextArea(10, 40);
    JTextField input = new JTextField();

    public DslWindow(DrawingService drawingServiceUsed, TrainRegisterService trainRegisterServiceUsed){
        drawingService = drawingServiceUsed;
        trainRegisterService = trainRegisterServiceUsed;
        mainFrame();
    }

    private void mainFrame(){
        // Create frame for the main screen
        JFrame mainFrameDsl = new JFrame("Rich Rail");
        mainFrameDsl.getContentPane().setLayout(new BorderLayout());

        output.setPreferredSize(new Dimension(200, 100));
        input.setPreferredSize(new Dimension(200, 50));

        output.setBackground(Color.BLACK);
        output.setForeground(Color.WHITE);

        // Listener
        mainFrameDsl.add(output, BorderLayout.PAGE_START);
        mainFrameDsl.add(input, BorderLayout.CENTER);

        input.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    output.setFont(new Font("console", Font.ITALIC, 11));
                    output.append(input.getText() + "\n");
                    DSLMain.interpret(input.getText());
                    input.setText("");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) { }
            @Override
            public void keyReleased(KeyEvent e) { }

        });
        // Finalize frame creation
        mainFrameDsl.setSize(400, 225);
        mainFrameDsl.setVisible(true);
    }


    public void update(String message) {
        output.setFont(new Font("console", Font.BOLD, 1));
        System.out.println(message);
        output.append("     " + message + '\n');
    }
}
