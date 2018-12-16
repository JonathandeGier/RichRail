package richrail.service;

import richrail.logging.Logger;
import richrail.persistence.DataSource;
import richrail.persistence.JsonFileDataSource;
import richrail.ui.gui.DrawingService;
import richrail.ui.gui.MainWindow;

public class Main {

	public static void main(String[] args) {

	    TreinService service = new TreinController();

		DataSource persistentie = new JsonFileDataSource(service);
        Logger logger = new Logger();

		DrawingService drawingService = new DrawingService(service);

		// Open window and subscribe it to changes
		MainWindow window = new MainWindow(drawingService, service);
		service.subscribeToChanges(window);

		// Load trains from file
		persistentie.loadTreinen();

		// Subscribe IO to changes
		service.subscribeToChanges( (TreinEventListener) persistentie );
		service.subscribeToChanges(logger);

	}

}