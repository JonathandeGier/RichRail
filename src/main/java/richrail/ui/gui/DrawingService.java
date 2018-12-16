package richrail.ui.gui;

import richrail.domein.ComponentType;
import richrail.domein.RollingComponent;
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

    private void clearPanel(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    void drawTrains(JPanel panel, JFrame frame) {

        clearPanel(panel);

        List<Trein> treinenList = treinService.getAlleTreinen();
        for(int i = 0; i < treinenList.size(); i++) {
            trainConstraint.gridy = i;
            for(int j = 0; j < treinenList.get(i).getComponenten().size(); j++) {
                wagonConstraint.gridy = i;
                wagonConstraint.gridx = j + 1;
                Trein trein = treinenList.get(i);
                RollingComponent component = trein.getComponenten().get(j);
                ComponentType type = component.getComponentType();
                String typeName = type.getTypeName();

                switch (typeName.toLowerCase()) {
                    case "locomotief":
                        panel.add(trainFactory(treinenList.get(i).getComponenten().get(j).getName()), wagonConstraint);
                        break;
                    case "passagiercomponent":
                        panel.add(passengerWagonFactory(treinenList.get(i).getComponenten().get(j).getName()), wagonConstraint);
                        break;
                    case "vrachtcomponent":
                        panel.add(cargoWagonFactory(treinenList.get(i).getComponenten().get(j).getName()), wagonConstraint);
                        break;
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
