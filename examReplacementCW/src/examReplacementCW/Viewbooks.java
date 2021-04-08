package examReplacementCW;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import javax.swing.table.TableModel;

public class Viewbooks {
	public static void main(String[] args) {
		Viewbooks obj = new Viewbooks();

	}

	Viewbooks() {

		JFrame frame = new JFrame("Bookstore Inventory Management System");
		JButton btnBack, btnDelete, btnUpdate,btnRefresh, btnSell, btnSearch;
		JLabel lNo, lAbl;
		String column[] = { "Book Number", "Book Name", "Author Name", "Date Published", "Price",
				"Quantity Available" };

		lAbl = new JLabel("Available Books");
		frame.add(lAbl);
		lAbl.setBounds(400, 30, 300, 100);

		String query = "Select * from books";
		DatabaseConnection db = new DatabaseConnection();
		ArrayList<Books> book = new ArrayList<Books>();

		try {
			ResultSet result = db.connection().executeQuery(query);

			while (result.next()) {
				String BookName = result.getString("book_name");
				String AuthorName = result.getString("author_name");
				String date = result.getString("publish_date");
				int price = Integer.parseInt(result.getString("price"));
				int bookNumber = Integer.parseInt(result.getString("book_code"));
				int Quantity = Integer.parseInt(result.getString("quantity"));
				Books details = new Books(BookName, AuthorName, date, price, bookNumber, Quantity);
				book.add(details);



			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		lNo = new JLabel("Number Of Available Books :" + book.size());
		frame.add(lNo);
		lNo.setBounds(50, 360, 300, 100);

		Object data[][] = new Object[book.size()][column.length];

		for (int i = 0; i < book.size(); i++) {
			data[i][0] = book.get(i).bookNumber;
			data[i][1] = book.get(i).BookName;
			data[i][2] = book.get(i).AuthorName;
			data[i][3] = book.get(i).date;
			data[i][4] = book.get(i).price;
			data[i][5] = book.get(i).Quantity;
		}

		JTable jt = new JTable(data, column);
		JScrollPane sp = new JScrollPane(jt);
		frame.add(sp);
		sp.setBounds(50, 100, 800, 300);

		// button

		btnBack = new JButton("Back");
		frame.add(btnBack);
		btnBack.setBounds(10, 10, 70, 30);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main();
				frame.dispose();
			}
		});

		btnDelete = new JButton("Delete");
		frame.add(btnDelete);
		btnDelete.setBounds(450, 550, 150, 30);
		
		btnRefresh = new JButton("Refresh");
		frame.add(btnRefresh);
		btnRefresh.setBounds(650, 550, 150, 30);
		
		btnRefresh.addActionListener(e->{
			frame.dispose();
			new Viewbooks();
		});
		

		btnDelete.addActionListener(e -> {
			int row = jt.getSelectedRow();
			if (row >= 0) {

				TableModel model = jt.getModel();

				String code = Integer.toString((int) model.getValueAt(row, 0));

				String dquery = "delete from books WHERE `book_code` = '" + code + "'";
				try {
					int dresult = db.connection().executeUpdate(dquery);
					if (dresult >= 1) {
						JOptionPane.showMessageDialog(frame, "Book Deleted");
						new Viewbooks();
						frame.dispose();
					}

				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(frame, "Please Select The Row First!!!");
			}

		});

		btnUpdate = new JButton("Update");
		frame.add(btnUpdate);
		btnUpdate.setBounds(250, 550, 150, 30);

		btnUpdate.addActionListener(e -> {
			int row = jt.getSelectedRow();
			if (row >= 0) {

				JLabel lbName = new JLabel("Book Name");
				frame.add(lbName);

				lbName.setBounds(10, 600, 900, 30);

				JTextField tfbName = new JTextField();
				frame.add(tfbName);
				tfbName.setBounds(80, 600, 120, 30);

				TableModel model = jt.getModel();
				String BookName = (String) model.getValueAt(row, 1);

				tfbName.setText(BookName);

				JLabel laName = new JLabel("Author Name");
				frame.add(laName);

				laName.setBounds(210, 600, 300, 30);

				JTextField tfaName = new JTextField();
				frame.add(tfaName);
				tfaName.setBounds(310, 600, 120, 30);

				TableModel model1 = jt.getModel();
				String AuthorName = (String) model1.getValueAt(row, 2);

				tfaName.setText(AuthorName);

				JLabel lpDate = new JLabel("Date");
				frame.add(lpDate);

				lpDate.setBounds(470, 600, 300, 30);

				JTextField tfpDate = new JTextField();
				frame.add(tfpDate);
				tfpDate.setBounds(530, 600, 150, 30);

				TableModel model2 = jt.getModel();

				String date = (String) model2.getValueAt(row, 3);
				tfpDate.setText(date);
				
				JLabel laddBy = new JLabel("Price");
				frame.add(laddBy);

				laddBy.setBounds(690, 600, 300, 30);

				JTextField tfaddBy = new JTextField();
				frame.add(tfaddBy);
				tfaddBy.setBounds(740, 600, 150, 30);
				
				
				JLabel laddQy = new JLabel("Quantity");
				frame.add(laddQy);

				laddQy.setBounds(900, 600, 300, 30);

				JTextField tfaddQy = new JTextField();
				frame.add(tfaddQy);
				tfaddQy.setBounds(980, 600, 150, 30);

				TableModel model3 = jt.getModel();

				String price1 = Integer.toString((int) model3.getValueAt(row, 4));
				tfaddBy.setText(price1);
				
				String quantity1 = Integer.toString((int) model3.getValueAt(row, 5));
				tfaddQy.setText(quantity1);

				JButton btnChange = new JButton("Update");
				frame.add(btnChange);
				btnChange.setBounds(430, 650, 150, 50);

				JButton btnCancel = new JButton("Cancel");
				frame.add(btnCancel);
				btnCancel.setBounds(430, 720, 150, 50);

				// update action

				btnChange.addActionListener(e3 -> {

					String tbName = tfbName.getText();
					String taName = tfaName.getText();
					String tpDate = tfpDate.getText();
					String price = tfaddBy.getText();
					String Quantity = tfaddQy.getText();
				

					TableModel model4 = jt.getModel();
					String Code = Integer.toString((int) model4.getValueAt(row, 0));
					String BookName1 = (String) model4.getValueAt(row, 1);
					
					String uquery = "update `books` set `book_name` = '" + tbName + "',`author_name` = '" + taName
							+ "', `price` = '" + price + "', `quantity` = '" + Quantity + "',  `publish_date` = '" + tpDate + "' WHERE `book_code` = '" + Code
							+ "'";
				
					String squery = "update `soldbooks` set `book_name` = '" + tbName + "',`author_name` = '" + taName
							+ "', `price` = '" + price + "', `quantity` = '" + Quantity + "',  `publish_date` = '" + tpDate + "' WHERE `book_name` = '"
							+ BookName1 + "'";
					
					try {
						int uresult = db.connection().executeUpdate(uquery);
						int sresult = db.connection().executeUpdate(squery);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
						
							JOptionPane.showMessageDialog(frame, "Book Updated");
							new Viewbooks();
							frame.dispose();
						
					

				});

				// cancel action

				btnCancel.addActionListener(e2 -> {

					new Viewbooks();
					frame.dispose();

				});

			} else {
				JOptionPane.showMessageDialog(frame, "Select The Row First!!!");
			}

		});

		JTextField tfbSearch = new JTextField();
		frame.add(tfbSearch);
		tfbSearch.setBounds(50, 70, 170, 30);

		btnSearch = new JButton("Search");
		frame.add(btnSearch);
		btnSearch.setBounds(230, 70, 100, 30);

		btnSearch.addActionListener(e3 -> {
			String Search = tfbSearch.getText();
			String query2 = "select * from books where author_name='" + Search + "' or book_name='" + Search + "' or publish_date='"
					+ Search + "'";
			try {
				ResultSet result4 = db.connection().executeQuery(query2);
				if (result4.next()) {
					int bookNumber = Integer.parseInt(result4.getString("book_code"));

					BinarySearch se = new BinarySearch();
					ArrayList<Integer> Quan = new ArrayList<Integer>();
					for (int i = 0; i < book.size(); i++) {
						int quann = book.get(i).bookNumber;
						Quan.add(quann);

					}

					int Index = se.binarySearch(Quan, bookNumber);
					Object data1[][] = new Object[1][column.length];
					JTable jt1 = new JTable(data1, column);
					JScrollPane sp1 = new JScrollPane(jt1);
					frame.add(sp1);
					sp1.setBounds(50, 450, 800, 38);

					data1[0][0] = book.get(Index).bookNumber;
					data1[0][1] = book.get(Index).BookName;
					data1[0][2] = book.get(Index).AuthorName;
					data1[0][3] = book.get(Index).date;
					data1[0][4] = book.get(Index).price;
					data1[0][5] = book.get(Index).Quantity;
				}

			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		});

		btnSell = new JButton("Sell Book");
		frame.add(btnSell);
		btnSell.setBounds(870, 200, 100, 30);

		btnSell.addActionListener(e -> {
			int row = jt.getSelectedRow();

			if (row >= 0) {

				TableModel model = jt.getModel();

				String code = Integer.toString((int) model.getValueAt(row, 0));

				String BookName = (String) model.getValueAt(row, 1);
				String AuthorName = (String) model.getValueAt(row, 2);
				String date = (String) model.getValueAt(row, 3);

				String price = Integer.toString((int) model.getValueAt(row, 4));
				int Quan = 1;
				int Quantity = ((int) model.getValueAt(row, 5) - 1);

				String query1 = "Select * from soldbooks where book_name='" + BookName + "'";
				try {
					ResultSet result3 = db.connection().executeQuery(query1);

					if (result3.next()) {
						int prevQuan = Integer.parseInt(result3.getString("quantity"));

					
						String uquery = "update `books` set `quantity` = '" + Quantity + "' WHERE `book_code` = '" + code
								+ "'";
						String urquery = "update `soldbooks` set `quantity` = '" + prevQuan++ + "' WHERE `book_name` = '"
								+ BookName + "'";
						int uresult = db.connection().executeUpdate(uquery);
						int uaresult = db.connection().executeUpdate(urquery);
						if (uresult >= 1 && uaresult >= 1) {
							JOptionPane.showMessageDialog(frame, "Book Sold");
							new Viewbooks();
							frame.dispose();
						}
					} else {
						String dquery = "update `books` set `quantity` = '" + Quantity + "' WHERE `book_code` = '" + code
								+ "'";
						String aquery = "insert into soldb(book_name,author_name,publish_date,price,quantity) values('" + BookName
								+ "','" + AuthorName + "','" + date + "','" + price + "','" + Quan + "') ";
						try {
							int dresult = db.connection().executeUpdate(dquery);
							int aresult = db.connection().executeUpdate(aquery);
							if (aresult >= 1 && dresult >= 1) {
								JOptionPane.showMessageDialog(frame, "Book Has Been Sold");
								new Viewbooks();
								frame.dispose();
							}

						} catch (SQLException e1) {

							e1.printStackTrace();
						}
					}
				} catch (SQLException e2) {

					e2.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(frame, "Please Select The Row First!!!!!");
			}

		});

		frame.setLayout(null);
		frame.setSize(1200, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

	}
}
