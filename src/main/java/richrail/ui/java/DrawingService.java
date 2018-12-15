package richrail.ui.java;

import richrail.domein.Trein;
import richrail.service.TreinService;

import javax.swing.*;
import java.awt.*;

import java.util.List;


public class DrawingService {

    private GridBagConstraints trainConstraint = new GridBagConstraints(0, 0, 1, 1, 100.0, 100.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
    private GridBagConstraints wagonConstraint = new GridBagConstraints(1, 0, 1, 1, 100.0, 100.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

    private TreinService treinService;

    public DrawingService(TreinService treinServiceUsed) {
        treinService = treinServiceUsed;
    }

    public void drawTrain(JPanel panel, JFrame frame, String trainName) {
        List<Trein> treinenList = treinService.getAlleTreinen();
        int trainCounter = 0;
        for(int i = 0; i < treinenList.size(); i++) {
            trainCounter++;
        }
        trainConstraint.gridy = trainCounter - 1;
        panel.add(trainFactory(trainName), trainConstraint);
        frame.setVisible(true);
    }

    public void clearPanel(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    public void drawComponent(JPanel panel, JFrame frame, String wagonName, String trainName, String type) {
        List<Trein> treinenList = treinService.getAlleTreinen();
        int trainCounter = 0;
        int wagonCounter = 0;
        for(int i = 0; i < treinenList.size(); i++) {
            if(treinenList.get(i).getName() == trainName) {
                trainCounter = i;
                for(int j = 0; j < treinenList.get(i).getComponenten().size(); j++) {
                    wagonCounter++;
                }
            }
        }
        wagonConstraint.gridx = wagonCounter + 1;
        wagonConstraint.gridy = trainCounter;
        if(type == "locomotive") {
            panel.add(trainFactory(wagonName), wagonConstraint);
        } else if (type == "vrachtcomponent") {
            panel.add(cargoWagonFactory(wagonName), wagonConstraint);
        } else {
            panel.add(passengerWagonFactory(wagonName), wagonConstraint);
        }
        frame.setVisible(true);
    }

    public void drawTrains(JPanel panel, JFrame frame) {
        List<Trein> treinenList = treinService.getAlleTreinen();
        int trainCounter = 0;
        int wagonCounter = 0;
        for(int i = 0; i < treinenList.size(); i++) {
            trainConstraint.gridy = i;
            panel.add(trainFactory(treinenList.get(i).getName()), trainConstraint);
            for(int j = 0; j < treinenList.get(i).getComponenten().size(); j++) {
                wagonConstraint.gridy = i;
                wagonConstraint.gridx = j + 1;
                System.out.println(i + treinenList.get(i).getName() + treinenList.get(i).getComponenten().get(j).getName() + treinenList.get(i).getComponenten().get(j).getComponentType().getTypeName() + trainConstraint.gridx);
                if(treinenList.get(i).getComponenten().get(j).getComponentType().getTypeName() == "Locomotief") {
                    panel.add(trainFactory(treinenList.get(i).getComponenten().get(j).getName()), wagonConstraint);
                } else if(treinenList.get(i).getComponenten().get(j).getComponentType().getTypeName() == "PassagierWagon") {
                    panel.add(passengerWagonFactory(treinenList.get(i).getComponenten().get(j).getName()), wagonConstraint);
                } else {
                    panel.add(cargoWagonFactory(treinenList.get(i).getComponenten().get(j).getName()), wagonConstraint);
                }
            }
            trainConstraint.gridx = 0;
        }
        frame.setVisible(true);
    }

    private TrainPanel trainFactory(String name) {
        return new TrainPanel(name);
    }

    private CargoWagonPanel cargoWagonFactory(String name) {
        return new CargoWagonPanel(name);
    }

    private PassengerWagonPanel passengerWagonFactory(String name) {
        return new PassengerWagonPanel(name);
    }
}
