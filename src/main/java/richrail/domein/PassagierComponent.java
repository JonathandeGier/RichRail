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
	
	public String getType() {
		return type;
	}
	
	public String toString() {
		return type + " met een capaciteit van " + zitplaatsen + " personen";
	}
}
