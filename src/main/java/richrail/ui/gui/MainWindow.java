package richrail.ui.gui;

import richrail.service.TreinEventListener;
import richrail.service.TreinService;

import java.awt.*;

import javax.swing.*;


public class MainWindow implements TreinEventListener {

    private String selectedTrain, selectedComponent;

    private DrawingService drawingService;

    private TreinService treinService;

    private JFrame mainFrame;
    private JPanel drawingPanel;

    private JComboBox<String> trainSelectionDropdown, componentSelectionDropdown, componentTypeSelectionDropdown;

    public MainWindow(DrawingService drawingServiceUsed, TreinService service){
        drawingService = drawingServiceUsed;
        treinService = service;
        mainFrame();
    }


    private void mainFrame(){
        // Create frame for the main screen
        mainFrame = new JFrame("Rich Rail");
        mainFrame.getContentPane().setLayout(new BorderLayout());

        // Create a GridBag Layout for the drawing panel
        GridBagLayout drawGrid = new GridBagLayout();

        drawGrid.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
        drawGrid.rowHeights = new int[] {1, 1, 1, 1};
        drawGrid.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
        drawGrid.columnWidths = new int[] {1, 1, 1, 1};

        // Create 4 seperate panels for each of the different components
        drawingPanel = new JPanel(drawGrid);
        JPanel componentPanel = new JPanel(new FlowLayout());
        JPanel trainSelectPanel = new JPanel(new FlowLayout());
        JPanel trainEditPanel = new JPanel(new FlowLayout());

        // Set prefered sizes for the panels
        componentPanel.setPreferredSize(new Dimension(200, 150));
        trainSelectPanel.setPreferredSize(new Dimension(200, 150));
        trainEditPanel.setPreferredSize(new Dimension(400, 150));

        // Make the drawing pane scrollable
        JScrollPane scrollDrawingPanel = new JScrollPane(drawingPanel);
        scrollDrawingPanel.setAutoscrolls(true);
        scrollDrawingPanel.setPreferredSize(new Dimension(400, 200));

        // Create a dropdown selection menu for the Trains
        trainSelectionDropdown = new JComboBox<>();
        componentSelectionDropdown = new JComboBox<>();
        componentTypeSelectionDropdown = new JComboBox<>();

        // Add a label to show the selected train
        JLabel trainSelectionLabel = new JLabel("No train selected...");
        trainSelectionLabel.setPreferredSize(new Dimension(170, 20));
        JLabel componentSelectionLabel = new JLabel("No wagon selected...");
        componentSelectionLabel.setPreferredSize(new Dimension(170, 20));


        // Add the names of all the trains to the dropdown
        trainSelectionDropdown.setPreferredSize(new Dimension(165, 20));
        componentSelectionDropdown.setPreferredSize(new Dimension(165, 20));
        componentTypeSelectionDropdown.setPreferredSize(new Dimension(165, 20));
        componentTypeSelectionDropdown.addItem("locomotief");
        componentTypeSelectionDropdown.addItem("vrachtcomponent");
        componentTypeSelectionDropdown.addItem("passagiercomponent");
        updateTrainComboBox();
        updateComponentenComboBox(selectedTrain);

        // Create text field
        JTextField trainNameField = new JTextField();
        trainNameField.setPreferredSize(new Dimension(165, 20));
        JTextField componentNameField = new JTextField();
        componentNameField.setPreferredSize(new Dimension(165, 20));
        JTextField componentWeightField = new JTextField();
        componentWeightField.setPreferredSize(new Dimension(165, 20));
        JTextField componentSpecialField = new JTextField();
        componentSpecialField.setPreferredSize(new Dimension(165, 20));

        // Create buttons
        JButton trainCreationButton = new JButton("Create");
        trainCreationButton.setPreferredSize(new Dimension(165, 20));
        JButton trainSelectionButton = new JButton("Select");
        trainSelectionButton.setPreferredSize(new Dimension(80, 20));
        JButton trainDeletionButton = new JButton("Delete");
        trainDeletionButton.setPreferredSize(new Dimension(80, 20));
        JButton consoleButton = new JButton("DSL Console");
        consoleButton.setPreferredSize(new Dimension(165, 20));
        JButton componentCreationButton = new JButton("Create");
        componentCreationButton.setPreferredSize(new Dimension(165, 20));
        JButton componentSelectionButton = new JButton("Select");
        componentSelectionButton.setPreferredSize(new Dimension(80, 20));
        JButton componentDeletionButton = new JButton("Delete");
        componentDeletionButton.setPreferredSize(new Dimension(80, 20));


        // Set action listener, to raise frame
        trainCreationButton.addActionListener(e -> {

            treinService.newTrein(trainNameField.getText());
            //trainRegisterService.registerTrain(drawingPanel, mainFrame, trainNameField.getText());
            //trainRegisterService.fillComboBox(trainSelectionDropdown, "all");
            //fillComboBox(trainSelectionDropdown, "all");
        });

        trainSelectionButton.addActionListener(e -> {
            selectedTrain = trainSelectionDropdown.getItemAt(trainSelectionDropdown.getSelectedIndex());
            trainSelectionLabel.setText("Train geselecteerd: " + selectedTrain);
            updateComponentenComboBox( selectedTrain );
            //fillComboBox(componentSelectionDropdown, selectedTrain);
        });

        trainDeletionButton.addActionListener(e -> {
            treinService.removeTrain(selectedTrain);
            //fillComboBox(trainSelectionDropdown, "all");
        });

        consoleButton.addActionListener(e -> {
            DslWindow dsl = new DslWindow(treinService);
            treinService.subscribeToChanges(dsl);
        });

        componentSelectionButton.addActionListener(e -> {
            selectedComponent = componentSelectionDropdown.getItemAt(componentSelectionDropdown.getSelectedIndex());
            componentSelectionLabel.setText("Component selected: " + selectedTrain);
        });

        componentDeletionButton.addActionListener(e -> {
            treinService.removeComponentFromTrain(selectedTrain, selectedComponent);
            updateComponentenComboBox( selectedTrain );
        });

        componentCreationButton.addActionListener(e -> {
            try {
                String wagonNaam = componentNameField.getText();
                int wagonGewicht = Integer.parseInt(componentWeightField.getText());
                String wagonType = componentTypeSelectionDropdown.getItemAt(componentTypeSelectionDropdown.getSelectedIndex());
                int wagonSpecial = Integer.parseInt(componentSpecialField.getText());
                treinService.createRollingComponent(wagonNaam, wagonGewicht, wagonType, wagonSpecial);
                treinService.addComponentToTrain(selectedTrain, wagonNaam);
                updateComponentenComboBox( selectedTrain );
                componentSpecialField.setText("");
                componentWeightField.setText("");
                componentNameField.setText("");
                componentSpecialField.setBackground(Color.white);
                componentWeightField.setBackground(Color.white);
            } catch (Exception s){
                componentSpecialField.setBackground(Color.red);
                componentWeightField.setBackground(Color.red);
            }
        });

        // Add panels to the main screen
        mainFrame.add(scrollDrawingPanel, BorderLayout.PAGE_START);
        mainFrame.add(componentPanel, BorderLayout.LINE_START);
        mainFrame.add(trainSelectPanel, BorderLayout.LINE_END);
        mainFrame.add(trainEditPanel, BorderLayout.PAGE_END);

        // Add components to the component panel
        componentPanel.add(trainNameField);
        componentPanel.add(trainCreationButton);
        componentPanel.add(trainSelectionDropdown);
        componentPanel.add(trainSelectionButton);
        componentPanel.add(trainDeletionButton);
        trainSelectPanel.add(trainSelectionLabel);
        trainSelectPanel.add(componentSelectionLabel);
        trainSelectPanel.add(consoleButton);
        trainEditPanel.add(componentSelectionDropdown);
        trainEditPanel.add(componentDeletionButton);
        trainEditPanel.add(componentSelectionButton);
        trainEditPanel.add(componentNameField);
        trainEditPanel.add(componentTypeSelectionDropdown);
        trainEditPanel.add(componentSpecialField);
        trainEditPanel.add(componentWeightField);
        trainEditPanel.add(componentCreationButton);

        // Draw all the trains
        drawingService.drawTrains(drawingPanel, mainFrame);

        // Finalize frame creation
        mainFrame.setSize(400, 500);
        mainFrame.setVisible(true);

        // Exit program upon closing the frame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*
    private void fillComboBox(JComboBox<String> combo, String trainName) {
        combo.removeAllItems();
        if( trainName.equals("all") ) {
            for(int i = 0; i < treinService.getAlleTreinen().size(); i++) {
                combo.addItem(treinService.getAlleTreinen().get(i).getName());
            }
            combo.setSelectedIndex(treinService.getAlleTreinen().size() - 1);
        } else {
            int componentCounter = 0;
            for(int i = 0; i < treinService.getAlleTreinen().size(); i++) {
                if( treinService.getAlleTreinen().get(i).getName().equals(trainName) ) {
                    for(int j = 0; j < treinService.getAlleTreinen().get(i).getComponenten().size(); j++)
                        combo.addItem(treinService.getAlleTreinen().get(i).getComponenten().get(j).getName());
                    componentCounter++;
                }
            }
            try {
                combo.setSelectedIndex(componentCounter - 1);
            } catch (Exception e) {
                System.out.println("Deze trein heeft 0 componenten.");
            }
        }
    }
    */

    private void updateTrainComboBox() {
        trainSelectionDropdown.removeAllItems();
        for (int i=0; i < treinService.getAlleTreinen().size(); i++) {
            trainSelectionDropdown.addItem(treinService.getAlleTreinen().get(i).getName());
        }
        trainSelectionDropdown.setSelectedIndex(trainSelectionDropdown.getItemCount() - 1);
    }

    private void updateComponentenComboBox(String trainName) {
        int componentCounter = 0;
        componentSelectionDropdown.removeAllItems();
        if (trainName == null) { return; }
        for(int i = 0; i < treinService.getAlleTreinen().size(); i++) {
            if( treinService.getAlleTreinen().get(i).getName().equals(trainName) ) {
                for(int j = 0; j < treinService.getAlleTreinen().get(i).getComponenten().size(); j++)
                    componentSelectionDropdown.addItem(treinService.getAlleTreinen().get(i).getComponenten().get(j).getName());
                componentCounter++;
            }
        }
        try {
            componentSelectionDropdown.setSelectedIndex(componentCounter - 1);
        } catch (Exception e) {
            System.out.println("Deze trein heeft 0 componenten.");
        }
    }

    public void update(String message) {
        drawingService.drawTrains(drawingPanel, mainFrame);
        updateTrainComboBox();
    }

}
