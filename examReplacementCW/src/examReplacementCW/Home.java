package examReplacementCW;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Home {
	
	
	public static void main(String[] args) {
		Login obj = new Login();

	}
}

class Login {
	DatabaseConnection db = new DatabaseConnection();

	Login() {
		JFrame frame = new JFrame("Library");
		JLabel lUser, lPsw, lTop;
		JTextField tfUser;
		JPasswordField tfpsw;
		JButton btnLogin, btnSignup,btnExit;


		lUser = new JLabel("Username");
		frame.add(lUser);
		lUser.setBounds(125, 115, 100, 100);

		lPsw = new JLabel("Password");
		frame.add(lPsw);
		lPsw.setBounds(125, 165, 100, 100);

		lTop = new JLabel("Book Store Login");
		frame.add(lTop);
		lTop.setBounds(300, 25, 150, 150);

		tfUser = new JTextField(30);
		frame.add(tfUser);
		tfUser.setBounds(200, 150, 300, 30);

		tfpsw = new JPasswordField(30);
		frame.add(tfpsw);
		tfpsw.setBounds(200, 200, 300, 30);


		
		btnLogin = new JButton("Login");
		frame.add(btnLogin);
		btnLogin.setBounds(300, 300, 100, 50);
		
		btnSignup= new JButton("Signup");
		frame.add(btnSignup);
		btnSignup.setBounds(300,380,100,50);
		
		btnExit= new JButton("Exit");
		frame.add(btnExit);
		btnExit.setBounds(300,460,100,50);

		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String user = tfUser.getText();
				String psw = tfpsw.getText();
				int value = userLogin(user, psw);

				if (value == 1) {
					frame.dispose();
					new Main();
					
				} else if (value == 2) {
					JOptionPane.showMessageDialog(frame, "Password Not Matched ");

				} else {
					JOptionPane.showMessageDialog(frame, "Username Or Password Is Not Correct ");
				}

			}

		});

		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Register();
				frame.dispose();

			}
		});
		
		btnExit.addActionListener((ActionListener) new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int select = JOptionPane.showConfirmDialog(btnExit, "Do you want to Exit");

				if (select == 0) {
					frame.dispose();
				}
			}
		});
			

		frame.setLayout(null);
		frame.setSize(700, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

	public int userLogin(String username, String psw) {
		String query = "select * from signup where username='" + username + "'";

		try {
			ResultSet result = db.connection().executeQuery(query);
			

			if (result.next()) {
				if (result.getString(3).equals(psw)) {
				
					return 1;
				} else if (!result.getString(3).equals(psw)) {
					return 2;

				}

			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return 0;

	}

}
