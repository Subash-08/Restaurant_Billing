package billing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.transform.Source;

import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.Container;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.JPasswordField;

public class loginPage extends JFrame {
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginPage frame = new loginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

//		 new loginPage();
		 
		
	}
	
	
	public JTextField u_name;
	private JButton cr_button;
	private JPasswordField password;
	String shopname2;
	/**
	 * Create the frame.
	 */
	 loginPage() {
		 
		 
//	
		 
		 
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBackground(Color.CYAN);
		contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.setBorder(null);
//		setSize(1920,1080);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1.setBounds(690, 152, 85, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setName("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(0, 255, 0));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setLocation(768, 21);
		lblNewLabel.setSize(new Dimension(85, 29));
		contentPane.add(lblNewLabel);
		String grestaurant = "restaurant";
		u_name = new JTextField();
		u_name.setBorder(null);
		u_name.setBounds(847, 152, 162, 29);
		contentPane.add(u_name);
		u_name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(690, 221, 85, 29);
		contentPane.add(lblNewLabel_2);
		
		JButton login_b = new JButton("Login");
		login_b.setFont(new Font("Tahoma", Font.BOLD, 14));
		login_b.setBackground(Color.WHITE);
		login_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name=u_name.getText();
            	String password1=password.getText();
            	
            	
            	

            	
            	
            	 try {
                     String host = "jdbc:mysql://localhost/"+grestaurant+"";
                     String user = "root";
                     String pass = "";
                     Connection c = DriverManager.getConnection(host, user, pass);
                     
                     
                     
                     
                  String checkQuery = 
                  "SELECT * FROM `account` WHERE name = '"+ name +"' AND password = '" + password1 + "'";
                  Statement checkStatement = c.createStatement();
                  
                  
                  
                  
                  
                  ResultSet resultSet = checkStatement.executeQuery(checkQuery);

                     if (resultSet.next()) {
                    	 String shopname2 = resultSet.getString(4);  
                    	 System.out.println(shopname2);
                    	 billing_system bs = new billing_system(shopname2);
                    	 bs.update_table(shopname2);
                         
                     } else {
                         // Insert the new entry
                    	 JOptionPane.showMessageDialog(login_b, "User Name or Password incorrect");
                     }
                 } catch (Exception exception) {
                     exception.printStackTrace();
                 }
            	 
            	 
            	 
            	
        		 
        		 
            	 
			}
		});
		
		login_b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		login_b.setBounds(756, 305, 97, 29);
		contentPane.add(login_b);
		
		JLabel lblNewLabel_3 = new JLabel("New User?");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_3.setBounds(690, 359, 85, 29);
		contentPane.add(lblNewLabel_3);
		
		cr_button = new JButton("Create Account");
		cr_button.setFont(new Font("Tahoma", Font.BOLD, 13));
		cr_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cr_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new cr_account();
				
				
			}
		});
		
		
		cr_button.setBorder(null);
		cr_button.setBounds(847, 355, 128, 29);
		contentPane.add(cr_button);
		
		password = new JPasswordField();
		password.setBounds(847, 221, 162, 35);
		contentPane.add(password);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\live wire\\1\\img\\bg.jpg"));
		lblNewLabel_4.setBounds(0, 0, 1540, 845);
		contentPane.add(lblNewLabel_4);
	}
}
