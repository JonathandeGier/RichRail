package richrail.service;

import richrail.domein.RollingComponent;
import richrail.domein.Trein;

public interface TreinService {
	
	public boolean newTrein(String name);
	public Trein getTrein(String name);
	public boolean removeTrain(String name);
	
	public boolean addComponentToTrain(String treinNaam, RollingComponent component);
	public RollingComponent getComponentFromTrain(String treinNaam, String componentNaam);
	public RollingComponent getComponent(String componentNaam);
	public boolean removeComponentFromTrain(String treinNaam, String componentNaam);

	public int getNumWagonSeats(String name);
	public int getNumTrainSeats(String name);
	
	public RollingComponent createRollingComponent(String name, int gewicht, String typeNaam, int specialeWaarde);
	public RollingComponent createRollingComponent(String name, String typeNaam, int specialeWaarde);
	public RollingComponent createRollingComponent(String name, String typeNaam);
}
