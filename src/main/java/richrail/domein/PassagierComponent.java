package richrail.domein;

public class PassagierComponent implements ComponentType {
	private String type = "PassagierWagon";
	private int zitplaatsen;
	
	public PassagierComponent(int zitplaatsen) {
		this.zitplaatsen = zitplaatsen;
	}
	
	public int getSpecialeWaarde() {
		return zitplaatsen;
	}

	public void setSpecialeWaarde(int waarde) {
		zitplaatsen = waarde;
	}

	public int getSeats() {return zitplaatsen;}

	public void setSeats(int zitplaatsen) { this.zitplaatsen = zitplaatsen; }

	public String getTypeName() {
		return type;
	}
	
	public String toString() {
		return type + " met een capaciteit van " + zitplaatsen + " personen";
	}
}
