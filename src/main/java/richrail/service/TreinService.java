package richrail.service;

import richrail.domein.RollingComponent;
import richrail.domein.Trein;

public interface TreinService {
	
	public boolean newTrein(String name);
	public Trein getTrein(String name);
	public boolean removeTrain(String name);
	
	public boolean addComponentToTrain(String treinNaam, String componentNaam);
	public RollingComponent getComponentFromTrain(String treinNaam, String componentNaam);
	public boolean removeComponentFromTrain(String treinNaam, String componentNaam);

	public int getNumWagonSeats(String name);
	public int getNumTrainSeats(String name);
	
	public boolean createRollingComponent(String name, int gewicht, String typeNaam, int specialeWaarde);
	public boolean createRollingComponent(String name, String typeNaam, int specialeWaarde);
	public boolean createRollingComponent(String name, int gewicht,  String typeNaam);
	public boolean createRollingComponent(String name, String typeNaam);
	public RollingComponent getComponent(String componentNaam);
	public boolean removeComponent(String componentNaam);
}
