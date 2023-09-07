package billing;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.SwingConstants;

public class cr_account extends JFrame {
	
	private String getCurrentDateTime() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    Date currentDate = new Date();
	    return dateFormat.format(currentDate);
	}

	private JPanel contentPane;
	private JTextField u_name;
	private JTextField pass;
	private JTextField c_pass;
	private JTextField s_name;
	private JTextField email;
	private JTextField phone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cr_account frame = new cr_account();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	 cr_account() {
		 	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(0, 0, 1920, 1080);
			setVisible(true);
			JPanel contentPane = new JPanel();
			contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			contentPane.setBackground(Color.CYAN);
			contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
			contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
			contentPane.setBorder(null);
//			setSize(1920,1080);
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("NEW USER REGISTER");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblNewLabel.setBounds(652, 103, 320, 35);
			contentPane.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("User Name");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(266, 237, 145, 27);
			contentPane.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Password");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblNewLabel_2.setBounds(266, 330, 145, 27);
			contentPane.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Confirm Password");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblNewLabel_3.setBounds(266, 431, 145, 27);
			contentPane.add(lblNewLabel_3);
			
			u_name = new JTextField();
			u_name.setBounds(481, 236, 129, 30);
			contentPane.add(u_name);
			u_name.setColumns(10);
			
			pass = new JTextField();
			pass.setBounds(481, 327, 129, 30);
			contentPane.add(pass);
			pass.setColumns(10);
			
			c_pass = new JTextField();
			c_pass.setBounds(481, 425, 129, 30);
			contentPane.add(c_pass);
			c_pass.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("Shop Name");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblNewLabel_4.setBounds(916, 239, 145, 27);
			contentPane.add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("E-mail");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblNewLabel_5.setBounds(916, 330, 145, 27);
			contentPane.add(lblNewLabel_5);
			
			JLabel lblNewLabel_6 = new JLabel("Phone Number");
			lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblNewLabel_6.setBounds(916, 428, 145, 30);
			contentPane.add(lblNewLabel_6);
			
			s_name = new JTextField();
			s_name.setBounds(1135, 238, 129, 30);
			contentPane.add(s_name);
			s_name.setColumns(10);
			
			email = new JTextField();
			email.setBounds(1135, 332, 129, 30);
			contentPane.add(email);
			email.setColumns(10);
			
			phone = new JTextField();
			phone.setBounds(1135, 425, 129, 30);
			contentPane.add(phone);
			phone.setColumns(10);
			
			JButton btnNewButton = new JButton("REGISTER");
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String name=u_name.getText();
	            	String password=pass.getText();
	            	String c_password = c_pass.getText();	            	
	            	String shop=s_name.getText();
	            	String Email=email.getText();
	            	String phone_n=phone.getText();
	            	int phone_number=Integer.parseInt(phone_n);
	            	if(name.trim().length() == 0 && password.trim().length() == 0 && Email.trim().length() == 0 &&
	            			phone_n.trim().length() == 0 && shop.trim().length() == 0 && c_password.trim().length() == 0) {
	            		JOptionPane.showMessageDialog(btnNewButton, "please fill ");
	            		
	            	}else {
	            	
	            	
	            	
	            	
	            	String msg =  name;
	                
	                        try {
	                            String host = "jdbc:mysql://localhost/restaurant";
	                            String user = "root";
	                            String pass = "";
	                            Connection c = DriverManager.getConnection(host, user, pass);
	                            
	                         String checkQuery = 
	                         "SELECT * FROM `account` WHERE name = '"+ name +"' AND shop = '" + shop + "'";
	                         Statement checkStatement = c.createStatement();
	                         ResultSet resultSet = checkStatement.executeQuery(checkQuery);

	                            if (resultSet.next()) {
	                                JOptionPane.showMessageDialog(btnNewButton, "The entry already exists.");
	                            } else {
	                                // Insert the new entry
	                                String insertQuery = "INSERT INTO account VALUES (?, ?, ?, ?, ?, ?)";
	                                PreparedStatement insertStatement = c.prepareStatement(insertQuery);
	                                insertStatement.setString(1, name);
	                                insertStatement.setString(2, password);
	                                insertStatement.setString(3, c_password);
	                                insertStatement.setString(4, shop);
	                                insertStatement.setString(5, Email);
									insertStatement.setInt(6,phone_number);
	                                
	                                
	                                int x = insertStatement.executeUpdate();

	                                if (x > 0) {
	                                    JOptionPane.showMessageDialog(btnNewButton, "Welcome, " + msg + " Your account is successfully created");
	                                    new loginPage();
	                                } else {
	                                    JOptionPane.showMessageDialog(btnNewButton, "Failed to create the account.");
	                                }
	                            }

	                            c.close();
	                        } catch (Exception exception) {
	                            exception.printStackTrace();
	                        }
	            	
					
	            	}
	            	
	            	
	            	
	            	String databaseName =  s_name.getText();// Implement your logic here
	           // Implement your logic here

	                // JDBC connection details
	                String dbURL = "jdbc:mysql://localhost:3306/";
	                String username = "root";
	                String password1 = "";

	                try (Connection conn = DriverManager.getConnection(dbURL, username, password1);
	                     Statement stmt = conn.createStatement()) {

	                    // Create the database (if it doesn't exist)
	                    String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + databaseName;
	                    stmt.executeUpdate(createDatabaseSQL);

	                    System.out.println("Database created successfully.");

	                    // Switch to the newly created database
	                    String useDatabaseSQL = "USE " + databaseName;
	                    stmt.executeUpdate(useDatabaseSQL);

	                    // Create the table
	                    String createTableSQL = "CREATE TABLE customise_food (" +
	                                            "ID INT PRIMARY KEY AUTO_INCREMENT," +
	                                            "f_name VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
	                                            "price INT," +
	                                            "category VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci," +
	                                            "description VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci" +
	                                            ")";
	                    stmt.executeUpdate(createTableSQL);
	                    
	                    String createTable2SQL = "CREATE TABLE customer_info (" +
                                "c_name VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
                                "number INT," +
                                "foodname VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
                                "quantity INT," +
                                "price INT," +
                                "time VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci," +
                                "date VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci," +
                                "sNo INT PRIMARY KEY AUTO_INCREMENT" +
                                ")";
	                    stmt.executeUpdate(createTable2SQL);


	                    System.out.println("Table created successfully.");

	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	            	
				
				
				}
			});
			btnNewButton.setBounds(729, 574, 156, 35);
			contentPane.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Back");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new loginPage();
					
				}
			});
			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnNewButton_1.setBounds(10, 116, 71, 22);
			contentPane.add(btnNewButton_1);

}
}
