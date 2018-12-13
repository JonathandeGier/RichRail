package richrail.domein;

public class VrachtComponent implements ComponentType {
	private String type = "Vrachtwagon";
	private int lading;
	
	public VrachtComponent(int lading) {
		this.lading = lading;
	}
	
	public int getSpecialeWaarde() {
		return lading;
	}

	public void setSpecialeWaarde(int waarde) {
		lading = waarde;
	}

	public int getSeats() {return 0;}

	public String getTypeName() {
		return type;
	}
	
	public String toString() {
		return type + " met een lading van " + lading;
	}
}
