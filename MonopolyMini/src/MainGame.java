import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGame {
	private JLabel lblBalance = new JLabel();

	static int withdraw;
	static int p; // The property number
	static int turn = 1; //The player whose turn it is
	static int notTurn = 2; //The player whose turn it is not
	static int roll; // The player's roll

	//Text to be displayed on JLables
	String actionText = ""; 
	String rollText = ""; 
	String landText = "";
	String turnText = "";
	String balanceText = "";

	static Random rn = new Random();
	static Player[] player = new Player[3];
	static Property[] property = new Property[8];

	private JFrame frame;

	/**
	 * This method is used to change the player's position on the board.
	 * 
	 * @param p:
	 *            The player's current position
	 * @param r:
	 *            The roll of the die (number of squares to move)
	 * @param t:
	 *            The player number whose turn it is.
	 * @return y: Returns the player's new position on the board.
	 */
	public static int move(int p, int r, int t) {
		for (int x = 0; x < r; x++) {
			if (p < 16) {
				p += 1; // increases the player's position by 1
			} else {
				p = 1; // Resets the player to square 1 when he passes go
			}
			if (x > 0 && p == 16) {
				player[t].deposit(200); // Gives player $200 if he lands on go
			}
		}
		return p;
	}

	/**
	 * This method is used to draw a random chance card when the player lands on
	 * a chance square.
	 * 
	 * @param t:
	 *            The number of the player whose turn it is.
	 * @param nt:
	 *            The number of the player whose turn it isn't.
	 * @return: Returns the chance card's text, to be displayed on screen
	 */

	public static String chance(int t, int nt) {
		Random rn = new Random();
		int card;
		card = rn.nextInt(4) + 1; // Generates a random number, which determines
									// which chance card will be drawn

		// The five chance cards
		switch (card) {
		case 1:
			player[t].deposit(50);
			return "Bank Pays you Dividend of $50!";
		case 2:
			player[t].withdraw(100);
			return "Pay Poor Tax of $100!";
		case 3:
			player[t].deposit(100);
			return "Pay Raise: Collect $100!";
		case 4:
			player[t].withdraw(250);
			player[nt].deposit(250);
			return "Pay Oppenent $250 for Property Damage";
		case 5:
			player[t].deposit(200);
			return "You have Won the Lottery! Collect $200";
		default:
			return "";
		}
	}

	/**
	 * Launch the application.
	 */
	public static void mainGame(String n1, String n2) {

		// initializing the array of properties
		property[0] = new Property("Baltic Avenue", 0, 4, 60, 0, 50, 1);
		property[1] = new Property("Connecticut Avenue", 0, 8, 120, 0, 50, 3);
		property[2] = new Property("Virginia Avenue", 0, 12, 160, 0, 100, 5);
		property[3] = new Property("New York Avenue", 0, 16, 200, 0, 100, 7);
		property[4] = new Property("Illinois Avenue", 0, 20, 240, 0, 150, 9);
		property[5] = new Property("Marvin Gardins", 0, 24, 280, 0, 150, 11);
		property[6] = new Property("Pennsyvania Avenue", 0, 28, 320, 0, 200, 13);
		property[7] = new Property("Boardwalk", 0, 50, 400, 0, 200, 15);

		// initializing the two players
		player[1] = new Player(1000, 0, n1, 1);
		player[2] = new Player(1000, 0, n2, 2);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGame window = new MainGame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblMainGame = new JLabel("Main Game");
		lblMainGame.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblMainGame.setForeground(new Color(0, 128, 0));
		JButton btnBuy = new JButton("Buy/Manage");

		turnText = "It is " + player[turn].getName() + "'s Turn";
		roll = rn.nextInt(5) + 1;
		rollText = "You rolled " + roll;
		player[turn].setPositon(move(player[turn].getPosition(), roll, turn));

		// If the player lands on an even numbered square, he has landed on a
		// property
		if (player[turn].getPosition() % 2 != 0) {
			p = (int) ((player[turn].getPosition()) / 2);
			landText = "You landed on " + property[p].getName();
			// Property is unowned
			if (property[p].getOwner() == 0) {
				actionText = property[p].getName() + " is unowned!";

				// Player already owns property
			}
			if (property[p].getOwner() == turn) {
				actionText = "You already own " + property[p].getName();
			}
			// Property is owned by other player
			if (property[p].getOwner() == notTurn) {
				btnBuy.setEnabled(false);
				actionText = "You paid " + player[notTurn].getName() + " $" + property[p].getRent() + " in rent";
				// Paying rent to owner of property
				player[turn].withdraw(property[p].getRent());
				player[notTurn].deposit(property[p].getRent());
			}
		}
		// If the player lands on an odd numbered square, he has landed on a
		// chance or corner space
		if (player[turn].getPosition() % 2 == 0) {
			btnBuy.setEnabled(false);
			// Player lands on free parking
			if (player[turn].getPosition() == 4 || player[turn].getPosition() == 12) {
				landText = "You landed on free Parking";
				actionText = "Enjoy your stay!";
				// Player lands on income tax
			} else if (player[turn].getPosition() == 8) {
				landText = "You landed on income tax";
				actionText = "Pay 10% of your earnings!";
				player[turn].withdraw((int) (player[turn].getMoney() * 0.1));
				// Player lands on GO
			} else if (player[turn].getPosition() == 16) {
				landText = "You landed on GO!";
				actionText = "Collect $200!";
				// Player lands on chance
			} else {
				landText = "You landed on Chance!";
				actionText = chance(turn, notTurn);
			}
			if (player[turn].getMoney() < 0) {
				frame.dispose();
			}
		}
		balanceText = "Balance: " + player[turn].getMoney();

		// Initializing Labels
		JLabel lblLand = new JLabel(landText);
		JLabel lblTurn = new JLabel(turnText);
		JLabel lblRoll = new JLabel(rollText);
		JLabel lblAction = new JLabel(actionText);

		JButton btnNewButton = new JButton("End Turn");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Switching Turns
				turn += 1;
				if (turn > 2) {
					turn = 1;
				}
				notTurn += 1;
				if (notTurn > 2) {
					notTurn = 1;
				}
				// ends the game if the player has gone bankrupt
				if (player[notTurn].getMoney() < 0) {
					frame.dispose();
					EndGame.endGame(player[turn].getName(), player[notTurn].getName());
					// opens a new window for the next turn
				} else {
					frame.dispose();
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								MainGame window = new MainGame();
								window.frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}

			}
		});

		lblBalance = new JLabel(balanceText);

		// Button allowing player to buy property
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyProperty.buyProperty(property[p], player[turn]);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(35)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addComponent(lblMainGame).addGap(41)
								.addComponent(lblBalance).addGap(149))
						.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnBuy, GroupLayout.PREFERRED_SIZE, 137,
												GroupLayout.PREFERRED_SIZE)
										.addGap(160).addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 82,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(lblTurn)
								.addComponent(lblRoll, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLand, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
								.addComponent(lblAction, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE))
								.addGap(58)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblMainGame)
						.addComponent(lblBalance))
				.addGap(12).addComponent(lblTurn).addGap(28).addComponent(lblRoll).addGap(26).addComponent(lblLand)
				.addGap(28).addComponent(lblAction)
				.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE).addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(btnBuy).addComponent(btnNewButton))
				.addGap(17)));
		frame.getContentPane().setLayout(groupLayout);
	}
}
