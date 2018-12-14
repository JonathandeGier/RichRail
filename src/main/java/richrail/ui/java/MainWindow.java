package richrail.ui.java;

import java.awt.*;

import javax.swing.*;




public class MainWindow {
    private int countery = 0;
    private int counterx = 0;
    private void mainFrame(){
        // Create frame for the main screen
        JFrame mainFrame = new JFrame("Rich Rail");
        GridBagLayout grid = new GridBagLayout();

        grid.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
        grid.rowHeights = new int[] {6, 6, 6, 6};
        grid.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
        grid.columnWidths = new int[] {4, 4, 4, 4};

        mainFrame.setLayout(grid);

        GridBagLayout drawGrid = new GridBagLayout();

        drawGrid.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
        drawGrid.rowHeights = new int[] {1, 1, 1, 1};
        drawGrid.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
        drawGrid.columnWidths = new int[] {1, 1, 1, 1};

        GridBagConstraints componentConstraint = new GridBagConstraints(0, 0, 1, 1, 100.0, 100.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

        // Create 5 seperate panels for each of the different components
        JPanel drawingPanel = new JPanel(drawGrid);
        JPanel componentPanel = new JPanel(new FlowLayout());
        JPanel trainSelectPanel = new JPanel(new FlowLayout());
        JPanel trainEditPanel = new JPanel(new FlowLayout());
        JPanel databasePanel = new JPanel(new FlowLayout());
        drawingPanel.setBackground(Color.white);
        componentPanel.setBackground(Color.black);
        trainSelectPanel.setBackground(Color.cyan);
        trainEditPanel.setBackground(Color.pink);
        databasePanel.setBackground(Color.yellow);

        // Make the drawing pane scrollable
        JScrollPane scrollDrawingPanel = new JScrollPane(drawingPanel);
        scrollDrawingPanel.setAutoscrolls(true);
        scrollDrawingPanel.setBackground(Color.blue);

        // Create buttons
        JButton raise = new JButton("Raise Frame");
        raise.setBounds(130, 100, 100, 40);
        JButton raise2 = new JButton("Raise Frame");
        raise2.setBounds(130, 100, 100, 40);


        // Set action listener, to raise frame
        raise.addActionListener(e -> {
            drawGrid.rowHeights = new int[] {countery+1, countery+1, countery+1, countery+1};
            componentConstraint.gridy = countery+1;
            componentConstraint.gridx = 1;
            drawingPanel.add(new TrainPanel("BarryBoy"), componentConstraint);
            mainFrame.setVisible(true);
            countery += 1;
        });

        raise2.addActionListener(e -> {
            drawGrid.columnWidths = new int[] {counterx+1, counterx+1, counterx+1, counterx+1};
            componentConstraint.gridx = counterx+1;
            drawingPanel.add(new CargoWagonPanel(), componentConstraint);
            mainFrame.setVisible(true);
            counterx += 1;
        });




        // Add panels to the main screen
        mainFrame.add(scrollDrawingPanel, new GridBagConstraints(0, 0, 4, 2, 1.0, 99.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        mainFrame.add(componentPanel, new GridBagConstraints(0, 2, 2, 2, 1.0, 99.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        mainFrame.add(trainSelectPanel, new GridBagConstraints(2, 2, 2, 2, 1.0, 99.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        mainFrame.add(trainEditPanel, new GridBagConstraints(0, 4, 2, 2, 1.0, 99.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        mainFrame.add(databasePanel, new GridBagConstraints(2, 4, 2, 2, 1.0, 99.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        // Add components to the component panel
        componentPanel.add(raise);
        trainSelectPanel.add(raise2);

        // Finalize frame creation
        mainFrame.setSize(400, 500);
        mainFrame.setVisible(true);
    }

    private void consoleFrame() {
        // Create frame for the main screen
        JFrame testFrame = new JFrame("Rich Rail");

        // Create buttons
        JButton raise = new JButton("Raise Frame");
        raise.setBounds(130, 100, 100, 40);

        // Set action listener, to raise frame
        raise.addActionListener(e -> {
            mainFrame();
        });

        // Add button to the screen
        //testFrame.add(new TrainPanel(1));

        // Finalize frame creation
        testFrame.setSize(400, 500);
        testFrame.setVisible(true);
    }

    public MainWindow(){
        mainFrame();
    }
}
