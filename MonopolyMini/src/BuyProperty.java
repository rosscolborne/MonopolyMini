import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuyProperty {

	static String name;
	static int price;
	static int rent;
	static Player player;
	static Property property;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void buyProperty(Property pr, Player pl) {
		// Details about the property being bought/managed
		name = pr.getName();
		price = pr.getPrice();
		rent = pr.getRent();
		player = pl;
		property = pr;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyProperty window = new BuyProperty();
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
	public BuyProperty() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 275, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblName = new JLabel(name);
		lblName.setForeground(new Color(0, 128, 0));
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 25));

		// Displays information about the property
		JLabel lblNewLabel = new JLabel("Rent: " + rent);
		JLabel label = new JLabel("1 House: " + rent * 2);
		JLabel lblHouses = new JLabel("2 Houses: " + rent * 4);
		JLabel lblHouses_1 = new JLabel("3 Houses: " + rent * 8);
		JLabel lblHouses_2 = new JLabel("4 Houses: " + rent * 16);

		JLabel lblPrice = new JLabel("Price: " + price);
		lblPrice.setForeground(new Color(30, 144, 255));
		lblPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

		// Buttons to buy the property and houses
		JButton btnBuy = new JButton("Buy");
		JButton btnBuyHouse = new JButton("Buy House");

		// Disables buttons if player does not have enough money.
		if (property.getOwner() != 0 || player.getMoney() < property.getPrice()) {
			btnBuy.setEnabled(false);
		}
		if (player.getMoney() < property.getHouseCost()) {
			btnBuyHouse.setEnabled(false);
		}

		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Disables button if player does not have enough money
				if (player.getMoney() < property.getPrice()) {
					btnBuyHouse.setEnabled(false);
				} else {
					player.withdraw(price);
					property.setOwner(player.getNum());
					btnBuy.setEnabled(false);
					btnBuyHouse.setEnabled(true);
					if (player.getMoney() < property.getHouseCost()) {
						btnBuyHouse.setEnabled(false);
					}
				}
			}
		});

		// Closes window if player clicks "Dismiss"
		JButton btnIgnore = new JButton("Dismiss");
		btnIgnore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		// Disables button if property already has 4 houses
		if (property.getHouses() >= 4 || player.getMoney() < property.getHouseCost() || property.getOwner() == 0) {
			btnBuyHouse.setEnabled(false);
		}

		btnBuyHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				property.setHouses(1);
				property.setRent();
				player.withdraw(property.getHouseCost());
				if (property.getHouses() == 4) {
					btnBuyHouse.setEnabled(false);
				}
				if (player.getMoney() < property.getHouseCost()) {
					btnBuyHouse.setEnabled(false);
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(26).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblHouses_2, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
								.addComponent(label)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout
												.createParallelGroup(Alignment.LEADING).addComponent(lblName)
												.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 140,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblHouses, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
														187, Short.MAX_VALUE)
												.addComponent(lblHouses_1, GroupLayout.DEFAULT_SIZE, 187,
														Short.MAX_VALUE))
										.addGap(81))))
						.addGroup(groupLayout.createSequentialGroup().addGap(15)
								.addComponent(btnBuy, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnIgnore, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnBuyHouse, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(21).addComponent(lblName)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE).addGap(25)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblHouses, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblHouses_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblHouses_2).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnBuy)
								.addComponent(btnIgnore).addComponent(btnBuyHouse))
						.addContainerGap(47, Short.MAX_VALUE)));
		frame.getContentPane().setLayout(groupLayout);
	}

}
