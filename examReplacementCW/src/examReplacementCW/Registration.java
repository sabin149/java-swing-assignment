package examReplacementCW;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Registration {
	public static void main(String[] args) {
		Register obj = new Register();

	}
}

class Register {
	Register() {
		JFrame frame = new JFrame("Sign Up");

		JLabel lname, lpsw, lUser, lemail;
		JTextField tfuser, tfname, tfemail;
		JPasswordField tfpsw, tfcpass;
		JButton btnreg, btnBack;


		// labels
		
		lname = new JLabel("Full Name : ");
		frame.add(lname);
		lname.setBounds(100, 170, 100, 30);

		lUser = new JLabel("Username : ");
		frame.add(lUser);
		lUser.setBounds(100, 230, 100, 30);
		
		lemail = new JLabel("Email : ");
		frame.add(lemail);
		lemail.setBounds(100, 290, 100, 30);

		lpsw = new JLabel("Password :");
		frame.add(lpsw);
		lpsw.setBounds(100, 350, 100, 30);

		lpsw=new JLabel("Confirm Password:");
		frame.add(lpsw);
		lpsw.setBounds(100,410,200,30);
		
		

// text fields

		tfname = new JTextField(30);
		frame.add(tfname);
		tfname.setBounds(230, 170, 160, 30);

		tfuser = new JTextField(10);
		frame.add(tfuser);
		tfuser.setBounds(230, 230, 160, 30);

		tfemail = new JTextField(30);
		frame.add(tfemail);
		tfemail.setBounds(230, 290, 160, 30);

		tfpsw = new JPasswordField(30);
		frame.add(tfpsw);
		tfpsw.setBounds(230, 350, 160, 30);

		tfcpass = new JPasswordField(30);
		frame.add(tfcpass);
		tfcpass.setBounds(230, 410, 160, 30);

		btnBack = new JButton("Back");
		frame.add(btnBack);
		btnBack.setBounds(10, 10, 70, 30);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				frame.dispose();
			}
		});

		btnreg = new JButton("Signup");
		frame.add(btnreg);
		btnreg.setBounds(250, 500, 110, 30);

		// save action
		btnreg.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String name = tfname.getText();
				String user = tfuser.getText();
				String psw = tfpsw.getText();
				String cpsw = tfcpass.getText();
				String email = tfemail.getText();

				int value = userSignup(name, user, psw, email, cpsw);

				if (value == 1) {
					JOptionPane.showMessageDialog(frame, " You Have Successfully Signed Up!");
					new Login();
					frame.dispose();

				} else if (value == 2) {
					JOptionPane.showMessageDialog(frame, "Please Fill Up All The Fields");
				} 
				else if (value == 3) {
					JOptionPane.showMessageDialog(frame, "Confirmation Password Not Match");
				}
				
				else if (value==4){
					JOptionPane.showMessageDialog(frame, "Username Already Taken!");
				}

			}
		});

		frame.setLayout(null);
		frame.setSize(700, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

	}

	DatabaseConnection db = new DatabaseConnection();

	public int userSignup(String name, String user, String psw, String email, String cpsw) {
		try {
			

			String query1 = "Select * from signup where username='" + user + "'";
			ResultSet result1 = db.connection().executeQuery(query1);
			
			if (name.equals("") || user.equals("") || psw.equals("") || email.equals("")) {
				return 2;

			} else if (!result1.next() && psw.equals(cpsw)) {
				String query = "insert into signup values('" + name + "','" + user + "','" + email + "','" + psw
						+ "')";	
				int result = db.connection().executeUpdate(query);
				if(result>0) {
                    return 1;
                }
			
			}else if (!psw.equals(cpsw)) {
            	return 3;
            	
            }else {
            	return 4;
            }

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return 0;

	}

}
