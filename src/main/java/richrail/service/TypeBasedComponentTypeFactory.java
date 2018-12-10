package richrail.service;

import richrail.domein.ComponentType;
import richrail.domein.Locomotief;
import richrail.domein.PassagierComponent;
import richrail.domein.VrachtComponent;

public class TypeBasedComponentTypeFactory implements ComponentTypeFactory {
	private String typeNaam;
	
	public TypeBasedComponentTypeFactory(String typeNaam) {
		this.typeNaam = typeNaam;
	}
	
	public ComponentType createComponentType(int specialeWaarde) {
		typeNaam = typeNaam.toLowerCase();

		switch(typeNaam) {
			case "locomotief":
				return new Locomotief(specialeWaarde);
			case "vrachtcomponent":
				return new VrachtComponent(specialeWaarde);
			case "passagiercomponent":
				return new PassagierComponent(specialeWaarde);
			default:
				return null;
		}
	}

}
