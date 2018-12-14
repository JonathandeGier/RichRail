package richrail.domein;

public interface ComponentType {
	
	public String getTypeName();
	
	public int getSpecialeWaarde();
	public void setSpecialeWaarde(int waarde);
	public int giveSeats();
	
	public String toString();
}
