package richrail.service;

import richrail.domein.RollingComponent;
import richrail.domein.Trein;

import java.util.List;

public interface TreinService {
	
	boolean newTrein(String name);
	Trein getTrein(String name);
	List<Trein> getAlleTreinen();
	boolean removeTrain(String name);

	boolean addComponentToTrain(String treinNaam, String componentNaam);
	RollingComponent getComponentFromTrain(String treinNaam, String componentNaam);
	boolean removeComponentFromTrain(String treinNaam, String componentNaam);

	int getNumWagonSeats(String name);
	int getNumTrainSeats(String name);
	
	boolean createRollingComponent(String name, int gewicht, String typeNaam, int specialeWaarde);
	boolean createRollingComponent(String name, String typeNaam, int specialeWaarde);
	boolean createRollingComponent(String name, int gewicht,  String typeNaam);
	boolean createRollingComponent(String name, String typeNaam);
	RollingComponent getComponent(String componentNaam);
	List<RollingComponent> getAlleComponenten();
	boolean removeComponent(String componentNaam);

	void subscribeToChanges(TreinEventListener listener);
	void unsubscribeFromChanges(TreinEventListener listener);
	void notifyListeners(String message);
}
