package richrail.domein;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Trein implements Iterable<RollingComponent> {
	private String name;
	private List<RollingComponent> componenten;
	
	public Trein(String name) {
		rename(name);
		componenten = new ArrayList<RollingComponent>();
	}
	
	public String getName() {return this.name;}
	
	public void rename(String name) {
		this.name = name;
	}
	
	public RollingComponent getRollingComponent(String name) {
		for(RollingComponent wagon : componenten) {
			if(wagon.getName().equals(name)) {
				return wagon;
			}
		}
		return null;
	}

	public int getNumOfSeats() {
		int total = 0;
		for(RollingComponent wagon : componenten) {
			total += wagon.getNumberOfSeats();
		}
		return total;
	}
	
	public boolean addRollingComonent(RollingComponent wagon) {
		return componenten.add(wagon);
	}
	
	public boolean removeRollingComponent(RollingComponent wagon) {
		return componenten.remove(wagon);
	}

	public Iterator<RollingComponent> iterator() {
		return componenten.iterator();
	}
	
	public String toString() {
		String s = "Trein " + name + " met:\n";
		for(RollingComponent wagon : componenten) {
			s += "\t" + wagon + "\n";
		}
		return s;
	}
}
