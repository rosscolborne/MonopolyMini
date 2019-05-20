public class Player {
	int money;
	int position;
	int number;
	String name;

	/**
	 * This constructor initializes the values for the player.
	 * 
	 * @param m:
	 *            The player's money.
	 * @param p:
	 *            The player's position.
	 * @param n:
	 *            The player's name.
	 * @param num
	 *            : The player's number (1 or 2)
	 */
	public Player(int m, int p, String n, int num) {
		money = m;
		position = p;
		name = n;
		number = num;
	}

	// The below methods serve as accessor methods for the variables in the
	// above constructor.
	int getMoney() {
		return money;
	}

	void deposit(int m) {
		money += m;
	}

	void withdraw(int m) {
		money -= m;
	}

	String getName() {
		return name;
	}

	void setName(String n) {
		name = n;
	}

	int getPosition() {
		return position;
	}

	void setPositon(int p) {
		position = p;
	}

	int getNum() {
		return number;
	}
}