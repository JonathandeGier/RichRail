package richrail.service;

import java.util.ArrayList;
import java.util.List;

import richrail.domein.ComponentType;
import richrail.domein.RollingComponent;
import richrail.domein.Trein;

public class TreinController implements TreinService {
	private static List<Trein> treinen;
	
	public TreinController() {
		treinen = new ArrayList<Trein>();
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
	
	public boolean removeTrain(String name) {
		Trein tr = getTrein(name);
		return treinen.remove(tr);
	}
	
	public boolean addComponentToTrain(String treinNaam, RollingComponent component) {
		Trein tr = getTrein(treinNaam);
		return tr.addRollingComonent(component);
	}
	
	public RollingComponent getComponentFromTrain(String treinNaam, String componentNaam) {
		Trein tr = getTrein(treinNaam);
		return tr.getRollingComponent(componentNaam);
	}
	
	public boolean removeComponentFromTrain(String treinNaam, String componentNaam) {
		Trein tr = getTrein(treinNaam);
		RollingComponent component = getComponentFromTrain(treinNaam, componentNaam);
		return tr.removeRollingComponent(component);
	}
	
	public RollingComponent createRollingComponent(String name, int gewicht, String typeNaam, int specialeWaarde) {
		ComponentType type = getComponentType(typeNaam, specialeWaarde);
		RollingComponent wagon = new RollingComponent(name, type, gewicht);
		return wagon;
	}
	
	public RollingComponent createRollingComponent(String name, String typeNaam, int specialeWaarde) {
		return createRollingComponent(name, 100, typeNaam, specialeWaarde);
	}
	
	public RollingComponent createRollingComponent(String name, String typeNaam) {
		return createRollingComponent(name, 100, typeNaam, 50);
	}
	
	private ComponentType getComponentType(String typeNaam, int specialeWaarde) {
		ComponentTypeFactory factory = new TypeBasedComponentTypeFactory(typeNaam);
		return factory.createComponentType(specialeWaarde);
	}
}














