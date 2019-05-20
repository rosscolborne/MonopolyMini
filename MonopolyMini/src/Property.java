public class Property {
	String name;
	int owner;
	int rent;
	int price;
	int houses;
	int houseCost;
	int position;

	/**
	 * This constructor initializes the values for the player.
	 * 
	 * @param n:
	 *            The property name
	 * @param o:
	 *            The property's owner
	 * @param r:
	 *            The property's rent with no houses
	 * @param p
	 *            : The property's price
	 * @param h
	 *            : The number of houses on the property
	 * @param hc
	 *            : The property's house cost
	 * @param os
	 *            : The property's position on the board
	 */
	public Property(String n, int o, int r, int p, int h, int hc, int pos) {
		name = n;
		owner = o;
		rent = r;
		price = p;
		houses = h;
		houseCost = hc;
		position = p;
	}

	// The below methods serve as accessor methods for the variables in the
	// above constructor.
	void setRent() {
		rent *= 2;
	}

	int getHouses() {
		return houses;
	}

	void setHouses(int h) {
		houses += h;
	}

	int getOwner() {
		return owner;
	}

	void setOwner(int o) {
		owner = o;
	}

	String getName() {
		return name;
	}

	int getHouseCost() {
		return houseCost;
	}

	int getRent() {
		return rent;
	}

	int getPrice() {
		return price;
	}

}