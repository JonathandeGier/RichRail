package richrail.ui.java;

import richrail.domein.Trein;
import richrail.service.TreinEventListener;
import richrail.service.TreinService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TrainRegisterService {
    GridBagConstraints trainConstraint = new GridBagConstraints(0, 0, 1, 1, 100.0, 100.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
    GridBagConstraints wagonConstraint = new GridBagConstraints(1, 0, 1, 1, 100.0, 100.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

    private TreinService treinService;
    private DrawingService drawingService;

    public TrainRegisterService(TreinService treinServiceUsed, DrawingService drawingServiceUsed) {
        treinService = treinServiceUsed;
        drawingService = drawingServiceUsed;
    }

    public void registerTrain(JPanel panel, JFrame frame, String trainName) {
        treinService.newTrein(trainName);
        drawingService.drawTrain(panel, frame, trainName);
    }

    public void fillComboBox(JComboBox<String> combo, String trainName) {
        combo.removeAllItems();
        if(trainName == "all") {
            for(int i = 0; i < treinService.getAlleTreinen().size(); i++) {
                combo.addItem(treinService.getAlleTreinen().get(i).getName());
            }
            combo.setSelectedIndex(treinService.getAlleTreinen().size() - 1);
        } else {
            int componentCounter = 0;
            for(int i = 0; i < treinService.getAlleTreinen().size(); i++) {
                if(treinService.getAlleTreinen().get(i).getName() == trainName) {
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

    public void deleteTrain(JPanel panel, JFrame frame, String trainName) {
        treinService.removeTrain(trainName);
        drawingService.clearPanel(panel);
        drawingService.drawTrains(panel, frame);
    }

    public void refreshTrain(JPanel panel, JFrame frame) {
        drawingService.clearPanel(panel);
        drawingService.drawTrains(panel, frame);
    }

    public void deleteComponent(JPanel panel, JFrame frame, String trainName, String componentName) {
        treinService.removeComponentFromTrain(trainName, componentName);
        drawingService.clearPanel(panel);
        drawingService.drawTrains(panel, frame);
    }

    public void registerComponent(JPanel panel, JFrame frame, String wagonName, String trainName, String type, int special, int weight) {
        treinService.createRollingComponent(wagonName, weight, type, special);
        treinService.addComponentToTrain(trainName, wagonName);
        drawingService.drawComponent(panel, frame, wagonName, trainName, type);
    }

    public void subscribeTreinService(TreinEventListener tel) {
        treinService.subscribeToChanges(tel);
    }

}
