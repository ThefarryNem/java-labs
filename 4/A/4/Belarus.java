// это такое тупое задание,
// почему Belarus а не Country?
// может вместо общего региона создать
// MinskRegion, MahiljowRegion и тд????

import java.util.ArrayList;

public class Belarus {
	public static void main(String[] args) {
		var belarus = new Belarus();
		System.out.println(belarus);
		System.out.println("Площадь страны: " + belarus.getArea());
		System.out.println("Областные центры: " + belarus.getRegionCenters());
	}

	private String capital = "Минск";
	private ArrayList<Region> regions = new ArrayList<Region>();

	public Belarus() {
		regions.add(new Region("Минская", "Минск", 39_854));
		regions.add(new Region("Могилёвская", "Могилёв", 29_068));
		regions.add(new Region("Витебская", "Витебск", 40_051));
		regions.add(new Region("Гродненская", "Гродно", 25_127));
		regions.add(new Region("Брестская", "Брест", 32_777));
		regions.add(new Region("Гомельская", "Гомель", 40_372));
	}

	public float getArea() {
		float result = 0;
		for (var region : regions) {
			result += region.getAreaKms();
		}
		return result;
	}

	public String getCapital() {
		return capital;
	}

	public ArrayList<String> getRegionCenters() {
		var regionCenters = new ArrayList<String>();
		for (var region : this.regions) {
			regionCenters.add(region.getCenter());
		}
		return regionCenters;
	}

	@Override
	public String toString() {
		var builder = new StringBuilder("Беларусь:\n");
		for (var region : regions) {
			builder.append("\t" + region.toString() + '\n');
		}
		return builder.toString();
	}
}

class Region {
	private String name;
	private String center;
	private float areaKms;

	public Region(String name, String center, float area) {
		this.name = name;
		this.center = center;
		this.areaKms = area;
	}

	public String getName() {
		return name + " область";
	}

	public String getCenter() {
		return center;
	}

	public float getAreaKms() {
		return areaKms;
	}

	public boolean equals(Object o) {
		if (o == null || o.getClass() != getClass()) {
			return false;
		}

		var casted = (Region) o;
		return name == casted.name;
	}

	@Override
	public int hashCode() {
		return name.hashCode() * 17 + center.hashCode() * 17;
	}

	@Override
	public String toString() {
		return getName() + ": областной центр г." + center + ", площадь: " + areaKms;

	}
}
