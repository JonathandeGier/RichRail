package richrail.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import richrail.ui.*;
import richrail.ui.java.DrawingService;
import richrail.ui.java.MainWindow;
import richrail.ui.java.TrainRegisterService;

public class Main {

	public static void main(String[] args) {

		TreinService service = new TreinController();
		DrawingService drawingService = new DrawingService(service);
		TrainRegisterService registerService = new TrainRegisterService(service, drawingService);
		//
		service.newTrein("tr1");
		//
		service.createRollingComponent("wg1", 10000, "vrachtcomponent", 300);
		service.createRollingComponent("wg2", 10000, "passagiercomponent", 300);
		service.addComponentToTrain("tr1", "wg1");
		service.addComponentToTrain("tr1", "wg2");


		service.createRollingComponent("wf1", 10000, "vrachtcomponent", 300);
		service.createRollingComponent("wf2", 10000, "passagiercomponent", 300);

		service.newTrein("tr2");
		service.addComponentToTrain("tr2", "wf1");
		service.addComponentToTrain("tr2", "wf2");

		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(service.getAlleTreinen()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		//

		MainWindow window = new MainWindow(drawingService, registerService);


		//	service.addComponentToTrain("tr1", service.createRollingComponent("wg3", "passagiercomponent", 30));
		//	service.addComponentToTrain("tr1", service.createRollingComponent("wg4", "passagiercomponent", 30));
		//	service.addComponentToTrain("tr1", service.createRollingComponent("wg5", "passagiercomponent", 30));
		//	service.addComponentToTrain("tr1", service.createRollingComponent("wg6", "vrachtcomponent", 3000));
		//	service.addComponentToTrain("tr1", service.createRollingComponent("wg7", "vrachtcomponent", 3000));
		//
		//	System.out.println(service.getTrein("tr1").toString());

		//	DSLMain.dsl();

	}

}