package richrail.domein;

public class RollingComponent {
	private String name;
	private ComponentType type;
	private int gewicht;
	
	public RollingComponent(String name, ComponentType type, int gewicht) {
		rename(name);
		this.type = type;
		this.gewicht = gewicht;
	}
	
	public RollingComponent(String name, ComponentType type) {
		this(name, type, 100);
	}
	
	public String getName() {return this.name;}
	public int getGewicht() {return this.gewicht;}
	
	public void rename(String name) {
		this.name = name;
	}
	
	public boolean equals(Object o) {
		boolean result = false;
		if(o instanceof RollingComponent) {
			RollingComponent wagon = (RollingComponent) o;
			if(		wagon.name == this.name &&
					wagon.type == this.type) {
				result = true;
				
			}
				
		}
		return result;
	}
	
	public String toString() {
		return name + ": " + type.toString() + " en een gewicht van " + gewicht;
	}
	
}
