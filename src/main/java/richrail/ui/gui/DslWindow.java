package richrail.ui.gui;

import richrail.service.TreinEventListener;
import richrail.service.TreinService;
import richrail.ui.dsl.DSLMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DslWindow implements TreinEventListener {

    private JTextArea output = new JTextArea(10, 40);
    private JTextField input = new JTextField();
    private TreinService service;

    public DslWindow(TreinService service){
        mainFrame();
        this.service = service;
    }

    private void mainFrame(){
        // Create frame for the main screen
        JFrame mainFrameDsl = new JFrame("Rich Rail");
        mainFrameDsl.getContentPane().setLayout(new BorderLayout());

        output.setPreferredSize(new Dimension(200, 150));
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
        mainFrameDsl.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                service.unsubscribeFromChanges(DslWindow.this);
                mainFrameDsl.dispose();
            }
        });
    }


    public void update(String message) {
        output.setFont(new Font("console", Font.BOLD, 11));
        System.out.println(message);
        output.append(message + '\n');
    }
}
