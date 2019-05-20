import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EndGame {
	static String winner;
	static String loser;

	private JFrame frame;

	/**
	 * Launch the application.
	 * 
	 * @return
	 */
	public static void endGame(String w, String l) {
		winner = w;
		loser = l;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EndGame window = new EndGame();
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
	public EndGame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Ends the Game, Displays the winner and loser, and closes the
		// application
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblPlayerWins = new JLabel(loser + " Has Gone Bankrupt");

		JLabel lblThanksForPlaying = new JLabel("Thanks for Playing");

		JLabel label = new JLabel(winner + " Wins");

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING,
						groupLayout.createSequentialGroup().addContainerGap(142, Short.MAX_VALUE)
								.addComponent(lblPlayerWins).addGap(135))
				.addGroup(groupLayout.createSequentialGroup().addGap(166).addComponent(lblThanksForPlaying)
						.addContainerGap(167, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addGap(183)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(246, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addGap(192).addComponent(btnExit).addContainerGap(245,
						Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(25).addComponent(lblPlayerWins).addGap(18)
						.addComponent(label).addGap(34).addComponent(lblThanksForPlaying)
						.addPreferredGap(ComponentPlacement.RELATED, 86, Short.MAX_VALUE).addComponent(btnExit)
						.addGap(38)));
		frame.getContentPane().setLayout(groupLayout);
	}
}
