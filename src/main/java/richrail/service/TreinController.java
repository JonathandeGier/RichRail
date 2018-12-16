package richrail.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import richrail.domein.*;

public class TreinController implements TreinService {
	private static List<Trein> treinen = new ArrayList<>();
	private static List<RollingComponent> losseComponenten = new ArrayList<>();
	private static List<TreinEventListener> listeners = new ArrayList<>();
	
	public TreinController() {}
	
	public boolean newTrein(String name) {
		if(getTrein(name) == null) {																					// kijken of er al een trein is met dezelfde naam
			Trein tr = new Trein(name);																					// nieuwe trein maken en toevoegen aan de list
			boolean result = treinen.add(tr);
			notifyListeners("Trein "+ name +" aangemaakt.");
			return result;
		}
		return false;
	}
	
	public Trein getTrein(String name) {
		for(Trein tr : treinen) {
			if(tr.getName().equals(name)) {																				// door de treinen list loopen en kijken of de naam overeen komt
				return tr;
			}
		}
		return null;
	}

	public List<Trein> getAlleTreinen() {
		return Collections.unmodifiableList(treinen);																	// list terug geven die je niet kan veranderen
	}
	
	public boolean removeTrain(String name) {																			// trein uit de list verwijderen
		Trein tr = getTrein(name);
		boolean result = treinen.remove(tr);
		notifyListeners("Trein "+ name +" verwijderd.");
		return result;
	}
	
	public boolean addComponentToTrain(String treinNaam, String componentNaam) {
		RollingComponent wagon = getComponent(componentNaam);															// wagon ophalen
		Trein tr = getTrein(treinNaam);																					// trein ophalen
		if(wagon != null && tr != null) {																				// testen of trein en wagon bestaan
			boolean result = losseComponenten.remove(wagon) && tr.addRollingComonent(wagon);
			notifyListeners("Component "+ componentNaam + " aan trein "+ treinNaam +" gekoppeld");
			return result;																								// wagon uit losseComponenten halen en aan trein toevoegen
		}
		return false;
	}
	
	public RollingComponent getComponentFromTrain(String treinNaam, String componentNaam) {
		Trein tr = getTrein(treinNaam);																					// trein ophalen
		if(tr != null) {																								// testen of trein bestaat
			return tr.getRollingComponent(componentNaam);																// component terug geven
		}
		return null;
	}
	
	public boolean removeComponentFromTrain(String treinNaam, String componentNaam) {
		Trein tr = getTrein(treinNaam);																					// trein ophalen
		RollingComponent component = getComponentFromTrain(treinNaam, componentNaam);									// wagon ophalen
		if(tr != null && component != null) {																			// testen of trein en wagon bestaan
			boolean result = tr.removeRollingComponent(component) && losseComponenten.add(component);
			notifyListeners("Component "+ componentNaam +" verwijderd van trein "+ treinNaam);
			return result;																								// wagon van trein afhalen en aan losseComponenten toevoegen
		}
		return false;
	}

	public int getNumWagonSeats(String name) {
		RollingComponent wagon = getComponent(name);
		if(wagon != null) {
			return wagon.passNumberOfSeats();
		}
		return 0;
	}

	public int getNumTrainSeats(String name) {
		Trein tr = getTrein(name);
		if(tr != null) {
			return tr.calculateTotalNumberOfSeats();
		}
		return 0;
	}

	public boolean createRollingComponent(String name, int gewicht, String typeNaam, int specialeWaarde) {
		if(getComponent(name) != null) {																				// testen of naam al bestaat
			return false;
		}
		ComponentType type = getComponentType(typeNaam, specialeWaarde);
		RollingComponent wagon = new RollingComponent(name, type, gewicht);
		boolean result = losseComponenten.add(wagon);
		notifyListeners( typeNaam +" "+ name +" aangemaakt.");
		return result;
	}
	
	public boolean createRollingComponent(String name, String typeNaam, int specialeWaarde) {							// hier kan een builder voor gemaakt worden, maar nu geen tijd voor
		return createRollingComponent(name, 100, typeNaam, specialeWaarde);
	}

	public boolean createRollingComponent(String name, int gewicht,  String typeNaam) {
		return createRollingComponent(name, gewicht, typeNaam, 50);
	}

	public boolean createRollingComponent(String name, String typeNaam) {
		return createRollingComponent(name, 100, typeNaam, 50);
	}

	public RollingComponent getComponent(String componentNaam) {
		RollingComponent wagon = null;																					// wagon standaard null
		for(Trein tr : treinen) {																						// loop over alle treinen
			wagon = tr.getRollingComponent(componentNaam);																// probeer compoennt component op te vragen
			if(wagon != null) {return wagon;}																			// geef terug zodra succesvol
		}

		for(RollingComponent comp : losseComponenten) {																	// als wagon nog steeds null is, loop over losseComponenten
			if(comp.getName().equals(componentNaam)) {																	// als naam gelijk is, return de wagon
				return comp;
			}
		}
		return wagon;
	}

	public List<RollingComponent> getAlleComponenten() {
		return Collections.unmodifiableList(losseComponenten);															// geef een lijst terug die je niet aan kan passen
	}

	public boolean removeComponent(String componentNaam) {
		RollingComponent wagon = getComponent(componentNaam);															// component ophalen
		if(losseComponenten.remove(wagon)) {																			// als component in losseComponenten zat
			notifyListeners("Component "+ wagon.getName() +" verwijderd.");
			return true;
		} else {
			for(Trein tr : treinen) {																					// loop over treinen en verwijder de wagon
				if(tr.removeRollingComponent(wagon)) {																	// stop zodra succesvol
					notifyListeners("Component "+ wagon.getName() +" verwijderd.");
					return true;
				}
			}
		}
		return false;
	}

	private ComponentType getComponentType(String typeNaam, int specialeWaarde) {
		ComponentTypeFactory factory = new TypeBasedComponentTypeFactory(typeNaam);										// FACTORY METHOD!!!
		return factory.createComponentType(specialeWaarde);
	}

	public void subscribeToChanges(TreinEventListener listener) {
		listeners.add(listener);
	}
	public void unsubscribeFromChanges(TreinEventListener listener) {
		listeners.remove(listener);
	}

	private void notifyListeners(String message) {

		for (TreinEventListener listener : listeners) {
			listener.update(message);
		}

	}

}
