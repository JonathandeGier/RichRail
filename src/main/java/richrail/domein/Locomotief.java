package richrail.domein;

public class Locomotief implements ComponentType {
	private String type = "Locomotief";
	private int vermogen;
	
	public Locomotief(int vermogen) {
		this.vermogen = vermogen;
	}
	
	public int getSpecialeWaarde() {
		return vermogen;
	}

	public void setSpecialeWaarde(int waarde) {
		vermogen = waarde;
		
	}

	public String getType() {
		return type;
	}
	
	public String toString() {
		return type + " met een vermogen van " + vermogen;
	}
}
