package examReplacementCW;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Dashboard {
	public static void main(String[] args) {
		Main log = new Main();
	}
}

class Main {
	Main() {
		JFrame f = new JFrame("Dashboard");
		JButton btnAdd, btnView, btnViewSold, btnExit;
		JLabel lZean;

		lZean = new JLabel("Book Store");
		f.add(lZean);
		lZean.setBounds(245, 30, 3000, 100);

		btnAdd = new JButton("Add books");
		f.add(btnAdd);
		btnAdd.setBounds(210, 150, 170, 50);
		
		btnView = new JButton("View Available Books");
		f.add(btnView);
		btnView.setBounds(210, 250, 170, 50);

		btnViewSold = new JButton("View Sold Books");
		f.add(btnViewSold);
		btnViewSold.setBounds(210, 350, 170, 50);
		
		btnExit = new JButton("Exit");
		f.add(btnExit);
		btnExit.setBounds(210, 450, 170, 50);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new bookStore();
				f.dispose();

			}
		});
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Viewbooks();
				f.dispose();

			}
		});

		btnViewSold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SoldBooks();
				f.dispose();

			}
		});
		
		btnExit.addActionListener((ActionListener) new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int select = JOptionPane.showConfirmDialog(btnExit, "Do you want to Exit");

				if (select == 0) {
					f.dispose();
				}
			}
		});

		f.setLayout(null);
		f.setSize(600, 600);
		f.setVisible(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	}
}
