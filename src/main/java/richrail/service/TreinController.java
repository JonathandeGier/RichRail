package richrail.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import richrail.domein.ComponentType;
import richrail.domein.RollingComponent;
import richrail.domein.Trein;

public class TreinController implements TreinService {
	private static List<Trein> treinen;
	private static List<RollingComponent> losseComponenten;
	
	public TreinController() {
		treinen = new ArrayList<Trein>();
		losseComponenten = new ArrayList<RollingComponent>();
	}
	
	public boolean newTrein(String name) {
		Trein tr = new Trein(name);
		return treinen.add(tr);
	}
	
	public Trein getTrein(String name) {
		for(Trein tr : treinen) {
			if(tr.getName().equals(name)) {
				return tr;
			}
		}
		return null;
	}

	public List<Trein> getAlleTreinen() {
		return Collections.unmodifiableList(treinen);
	}
	
	public boolean removeTrain(String name) {
		Trein tr = getTrein(name);
		return treinen.remove(tr);
	}
	
	public boolean addComponentToTrain(String treinNaam, String componentNaam) {
		RollingComponent wagon = getComponent(componentNaam);
		Trein tr = getTrein(treinNaam);
		return losseComponenten.remove(wagon) && tr.addRollingComonent(wagon);
	}
	
	public RollingComponent getComponentFromTrain(String treinNaam, String componentNaam) {
		Trein tr = getTrein(treinNaam);
		return tr.getRollingComponent(componentNaam);
	}
	
	public boolean removeComponentFromTrain(String treinNaam, String componentNaam) {
		Trein tr = getTrein(treinNaam);
		RollingComponent component = getComponentFromTrain(treinNaam, componentNaam);
		return tr.removeRollingComponent(component) && losseComponenten.add(component);
	}

	public int getNumWagonSeats(String name) {
		RollingComponent wagon = getComponent(name);
		if(wagon != null) {
			return wagon.getNumberOfSeats();
		}
		return 0;
	}

	public int getNumTrainSeats(String name) {
		Trein tr = getTrein(name);
		if(tr != null) {
			return tr.getTotalNumberOfSeats();
		}
		return 0;
	}

	public boolean createRollingComponent(String name, int gewicht, String typeNaam, int specialeWaarde) {
		ComponentType type = getComponentType(typeNaam, specialeWaarde);
		RollingComponent wagon = new RollingComponent(name, type, gewicht);
		return losseComponenten.add(wagon);
	}
	
	public boolean createRollingComponent(String name, String typeNaam, int specialeWaarde) {
		return createRollingComponent(name, 100, typeNaam, specialeWaarde);
	}

	public boolean createRollingComponent(String name, int gewicht,  String typeNaam) {
		return createRollingComponent(name, gewicht, typeNaam, 50);
	}

	public boolean createRollingComponent(String name, String typeNaam) {
		return createRollingComponent(name, 100, typeNaam, 50);
	}

	public RollingComponent getComponent(String componentNaam) {
		RollingComponent wagon = null;
		for(Trein tr : treinen) {
			wagon = tr.getRollingComponent(componentNaam);
			if(wagon != null) {return wagon;}
		}

		for(RollingComponent comp : losseComponenten) {
			if(comp.getName().equals(componentNaam)) {
				return comp;
			}
		}
		return wagon;
	}

	public List<RollingComponent> getAlleComponenten() {
		return Collections.unmodifiableList(losseComponenten);
	}

	public boolean removeComponent(String componentNaam) {
		RollingComponent wagon = getComponent(componentNaam);
		return treinen.remove(wagon) || losseComponenten.remove(wagon);
	}

	private ComponentType getComponentType(String typeNaam, int specialeWaarde) {
		ComponentTypeFactory factory = new TypeBasedComponentTypeFactory(typeNaam);
		return factory.createComponentType(specialeWaarde);
	}
}














