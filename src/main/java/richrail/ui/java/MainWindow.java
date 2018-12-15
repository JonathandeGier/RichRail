package richrail.ui.java;

import richrail.domein.Trein;
import richrail.service.TreinService;

import java.awt.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;


public class MainWindow {

    private String selectedTrain;
    private String selectedComponent;

    private DrawingService drawingService;
    private TrainRegisterService trainRegisterService;

    public MainWindow(DrawingService drawingServiceUsed, TrainRegisterService trainRegisterServiceUsed){
        drawingService = drawingServiceUsed;
        trainRegisterService = trainRegisterServiceUsed;
        mainFrame();
    }

    private void mainFrame(){
        // Create frame for the main screen
        JFrame mainFrame = new JFrame("Rich Rail");
        mainFrame.getContentPane().setLayout(new BorderLayout());

        // Create a GridBag Layout for the drawing panel
        GridBagLayout drawGrid = new GridBagLayout();

        drawGrid.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
        drawGrid.rowHeights = new int[] {1, 1, 1, 1};
        drawGrid.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
        drawGrid.columnWidths = new int[] {1, 1, 1, 1};

        // Create 4 seperate panels for each of the different components
        JPanel drawingPanel = new JPanel(drawGrid);
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
        JComboBox<String> trainSelectionDropdown = new JComboBox<String>();
        JComboBox<String> componentSelectionDropdown = new JComboBox<String>();
        JComboBox<String> componentTypeSelectionDropdown = new JComboBox<String>();

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
        trainRegisterService.fillComboBox(trainSelectionDropdown, "all");

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
            trainRegisterService.registerTrain(drawingPanel, mainFrame, trainNameField.getText());
            trainRegisterService.fillComboBox(trainSelectionDropdown, "all");
        });

        trainSelectionButton.addActionListener(e -> {
            selectedTrain = trainSelectionDropdown.getItemAt(trainSelectionDropdown.getSelectedIndex());
            trainSelectionLabel.setText("Train geselecteerd: " + selectedTrain);
            trainRegisterService.fillComboBox(componentSelectionDropdown, selectedTrain);
        });

        trainDeletionButton.addActionListener(e -> {
            trainRegisterService.deleteTrain(drawingPanel, mainFrame, selectedTrain);
            trainRegisterService.fillComboBox(trainSelectionDropdown, "all");
        });

        consoleButton.addActionListener(e -> {
            DslWindow dsl = new DslWindow(drawingService, trainRegisterService);
        });

        componentSelectionButton.addActionListener(e -> {
            selectedComponent = componentSelectionDropdown.getItemAt(componentSelectionDropdown.getSelectedIndex());
            componentSelectionLabel.setText("Component selected: " + selectedTrain);
        });

        componentDeletionButton.addActionListener(e -> {
            trainRegisterService.deleteComponent(drawingPanel, mainFrame, selectedTrain, selectedComponent);
            trainRegisterService.fillComboBox(componentSelectionDropdown, selectedTrain);
        });

        componentCreationButton.addActionListener(e -> {
            trainRegisterService.registerComponent(drawingPanel, mainFrame, componentNameField.getText(), selectedTrain, componentTypeSelectionDropdown.getItemAt(componentTypeSelectionDropdown.getSelectedIndex()), Integer.parseInt(componentWeightField.getText()), Integer.parseInt(componentSpecialField.getText()));
            trainRegisterService.fillComboBox(componentSelectionDropdown, selectedTrain);
            componentSpecialField.setText("");
            componentWeightField.setText("");
            componentNameField.setText("");
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
    }

}
