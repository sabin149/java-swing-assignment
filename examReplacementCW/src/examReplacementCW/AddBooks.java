package examReplacementCW;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class AddBooks {
	public static void main(String[] args) {
		bookStore obj = new bookStore();
	}
}

class bookStore {
	bookStore() {
		JFrame f = new JFrame("Add Books");

		JLabel lbookName, lauthorName, lDate, lPrice;
		JTextField tfbName, tfaName, tfpDate, tfprice;
		JButton btnSave,btnView;

		// user_label
		lbookName = new JLabel("Book Name:");
		f.add(lbookName);
		lbookName.setBounds(100, 100, 100, 30);

		lauthorName = new JLabel("Author Name:");
		f.add(lauthorName);
		lauthorName.setBounds(100, 100, 100, 100);

		lDate = new JLabel("Publish Date :");
		f.add(lDate);
		lDate.setBounds(100, 100, 100, 170);

		lPrice = new JLabel("Book Price :");
		f.add(lPrice);
		lPrice.setBounds(100, 100, 100, 250);

		JLabel lQuantity = new JLabel("Quantity :");
		f.add(lQuantity);
		lQuantity.setBounds(100, 100, 100, 340);

		tfbName = new JTextField(30);
		f.add(tfbName);
		tfbName.setBounds(200, 106, 160, 20);

		tfaName = new JTextField(10);
		f.add(tfaName);
		tfaName.setBounds(200, 145, 160, 20);

		tfpDate = new JTextField(30);
		f.add(tfpDate);
		tfpDate.setBounds(200, 180, 160, 20);

		tfprice = new JTextField(30);
		f.add(tfprice);
		tfprice.setBounds(200, 220, 160, 20);

		JTextField tfQuantity = new JTextField(30);
		f.add(tfQuantity);
		tfQuantity.setBounds(200, 260, 160, 20);

		btnSave = new JButton("Save");
		f.add(btnSave);
		btnSave.setBounds(230, 300, 110, 30);



		btnView = new JButton("View Books");
		f.add(btnView);
		btnView.setBounds(230, 350, 110, 30);

		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Viewbooks();
				f.dispose();

			}
		});


		// save details
		btnSave.addActionListener(e -> {
			String bookName = tfbName.getText();
			String publisherName = tfaName.getText();
			String date = tfpDate.getText();
			String price = tfprice.getText();
			String quantity = tfQuantity.getText();
			try {
				DatabaseConnection db = new DatabaseConnection();
				String query1 = "Select * from books where book_name='" + bookName + "'";
				ResultSet result = db.connection().executeQuery(query1);
				if (result.next()) {
					JOptionPane.showMessageDialog(btnSave, "Book Already Exist");
				} else {

					String query = "insert into books(book_name,author_name,publish_date,price,quantity) values('" + bookName + "','"
							+ publisherName + "','" + date + "','" + price + "','" + quantity + "') ";

					int result1 = db.connection().executeUpdate(query);
					if (result1 > 0) {
						JOptionPane.showMessageDialog(btnSave, "Book Added Successfully");

					}
				}

			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		});

		f.setLayout(null);
		f.setSize(600, 600);
		f.setVisible(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	}
}
