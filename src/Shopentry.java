
/**
 * @author Timo 'eXodiquas' Netzer
 *
 */
public class Shopentry {
	public String name;
	public double latitude;
	public double longitude;
	
	
	/**
	 * @param n Name des Supermarkteintrages
	 * @param lon Längengrad des Supermarkts
	 * @param lat Breitengrad des Supermarkts
	 */
	public Shopentry(String n, double lon, double lat) {
		this.name = n;
		this.latitude = lat;
		this.longitude = lon;
	}
	
	public String toString() {
		return "Name: " + this.name + "\nLat: " + this.latitude + "\nLon: " + this.longitude + "\n";
		
	}
}
