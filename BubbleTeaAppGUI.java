package Proiect;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BubbleTeaAppGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BubbleTea bubbleTea;

	public BubbleTeaAppGUI() {
		initUI();
	}

	private void initUI() {
		setTitle("Bubble Tea Shop");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));

		JLabel label = new JLabel("Alege tipul de bubble tea:");
		panel.add(label);

		JButton fruityButton = new JButton("Bubble tea cu fructe: 15 lei");
		fruityButton.addActionListener(new FruityButtonListener());
		panel.add(fruityButton);

		JButton milkyButton = new JButton("Bubble tea cu lapte: 20 lei");
		milkyButton.addActionListener(new MilkyButtonListener());
		panel.add(milkyButton);

		JButton extraButton = new JButton("Arome extra +6 lei");
		extraButton.addActionListener(new ExtraButtonListener());
		panel.add(extraButton);

		JButton orderButton = new JButton("Plaseaza comanda");
		orderButton.addActionListener(new ComandaListener());
		panel.add(orderButton);

		add(panel);
	}

	private class FruityButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] fruityFlavors = { "Mango", "Capsuna", "Pepene", "Portocale", "Ananas" };
			int aromaAlegere = aromaExtra("Alege o aroma", fruityFlavors);
			if (aromaAlegere != -1) {
				bubbleTea = new FruityBubbleTea(fruityFlavors[aromaAlegere]);
			}
		}
	}

	private class MilkyButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] aromeLapte = { "Cafea", "Caramel", "Vanilie", "Ciocolata", "Yerba matte" };
			int aromaAlegere = aromaExtra("Alege o aroma", aromeLapte);
			if (aromaAlegere != -1) {
				int soia = soiaDaNu("Vrei lapte de soia?");
				String flavor = aromeLapte[aromaAlegere];
				boolean cuSoia = (soia == JOptionPane.YES_OPTION);
				bubbleTea = new MilkyBubbleTea(flavor, cuSoia);
			}
		}
	}

	private class ExtraButtonListener implements ActionListener {
		private List<String> topping = new ArrayList<>();

		@Override
		public void actionPerformed(ActionEvent e) {
			if (bubbleTea != null) {
				String[] toppings = { "Mar", "Tapioca", "Afine", "Portocala", "Cocos" };
				topping.clear();
				while (true) {
					int aromaAlegere = aromaExtra("Alegeti un topping (sau mai multe)", toppings);
					if (aromaAlegere == -1) {
						break;
					}

					topping.add(toppings[aromaAlegere]);
				}

				if (!topping.isEmpty()) {
						bubbleTea = new FlavorDecorator(bubbleTea,new ArrayList<> (topping),6*topping.size());
				}
			} else {
				showMessageDialog("Alege o baza mai intai.");
			}
		}
	}

	private class ComandaListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (bubbleTea != null) {
				showMessageDialog(
						"Comanda dumneavoastra: " + bubbleTea.desc() + "\nPret: " + bubbleTea.getPret() + " lei");
			} else {
				showMessageDialog("Alege o baza mai intai.");
			}
		}
	}

	private int aromaExtra(String message, String[] options) {
		return JOptionPane.showOptionDialog(null, message, "PIP Bubble Tea", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
	}

	private int soiaDaNu(String message) {
		return JOptionPane.showOptionDialog(null, message, "PIP Bubble Tea", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new String[] { "Da", "Nu" }, "Nu");
	}

	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "PIP Bubble Tea", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			BubbleTeaAppGUI gui = new BubbleTeaAppGUI();
			gui.setVisible(true);
		});
	}
}