import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu {

	private JFrame frame;
	private JTextField txtPlayerName;
	private JTextField txtPlayerName_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Title
		JLabel lblMonopolyMini = new JLabel("Monopoly Mini");
		lblMonopolyMini.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblMonopolyMini.setForeground(new Color(0, 128, 0));

		txtPlayerName = new JTextField();
		txtPlayerName.setColumns(10);

		txtPlayerName_1 = new JTextField();
		txtPlayerName_1.setColumns(10);

		JLabel lblEnterTheName = new JLabel("Enter the Name of Player 1");

		JLabel lblEnterTheName_1 = new JLabel("Enter the Name of Player 2");

		JButton btnNewButton = new JButton("Begin");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name1 = txtPlayerName.getText(); // gets name of player 1
				String name2 = txtPlayerName_1.getText(); // gets name of player 2
															
				MainGame.mainGame(name1, name2); // starts the main game
				frame.dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap(47, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup().addComponent(lblMonopolyMini).addGap(110))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblEnterTheName).addComponent(lblEnterTheName_1,
												GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
								.addGap(43)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtPlayerName, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtPlayerName_1, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(64))
						.addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup().addComponent(btnNewButton).addGap(170)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(16).addComponent(lblMonopolyMini).addGap(28)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtPlayerName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEnterTheName))
						.addGap(33)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtPlayerName_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEnterTheName_1))
						.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE).addComponent(btnNewButton)
						.addGap(43)));
		frame.getContentPane().setLayout(groupLayout);
	}
}
