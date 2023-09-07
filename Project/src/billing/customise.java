package billing;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;

public class customise extends JFrame {

	private JPanel contentPane;
	private JTextField foodID;
	private JTextField foodName;
	private JTextField f_price;
	private JTextField f_description;
	static String S_NAME;
//	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customise frame = new customise(S_NAME);
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
	public customise(String shopname) {
		
		 String S_NAME =shopname; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.CYAN);
		contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRestaurantBillingSystem = new JLabel("RESTAURANT BILLING SYSTEM");
		lblRestaurantBillingSystem.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblRestaurantBillingSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestaurantBillingSystem.setBounds(413, 10, 681, 52);
		contentPane.add(lblRestaurantBillingSystem);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.RED);
		separator_1.setBounds(0, 72, 1540, 4);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel = new JLabel("Add / Update / Delete Food");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(602, 116, 310, 30);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				billing_system bs = new billing_system(S_NAME);
				bs.update_table(S_NAME);
			}
		});
		btnNewButton.setBounds(10, 72, 75, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Food ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(379, 224, 120, 25);
		contentPane.add(lblNewLabel_1);
		
		foodID = new JTextField();
		foodID.setBounds(509, 225, 160, 28);
		contentPane.add(foodID);
		foodID.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Food Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(379, 292, 120, 25);
		contentPane.add(lblNewLabel_2);
		
		foodName = new JTextField();
		foodName.setBounds(509, 291, 160, 28);
		contentPane.add(foodName);
		foodName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(379, 371, 120, 25);
		contentPane.add(lblNewLabel_3);
		
		f_price = new JTextField();
		f_price.setBounds(509, 370, 160, 28);
		contentPane.add(f_price);
		f_price.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Category");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(868, 230, 120, 25);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Description");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(868, 292, 120, 25);
		contentPane.add(lblNewLabel_5);
		
		f_description = new JTextField();
		f_description.setFont(new Font("Tahoma", Font.PLAIN, 12));
		f_description.setBounds(998, 286, 160, 64);
		contentPane.add(f_description);
		f_description.setColumns(10);
		
		JComboBox<String> f_category = new JComboBox();
		f_category.setModel(new DefaultComboBoxModel(new String[] {"Starter", "Beverages", "Main course", "Ice cream", "Combo"}));
		f_category.setBounds(998, 224, 160, 28);
		contentPane.add(f_category);
		
		JButton addFood = new JButton("ADD FOOD");
		addFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				String f_id = foodID.getText(); 
				String f_name = foodName.getText();
				String food_price = f_price.getText();
				String food_category = f_category.getSelectedItem().toString();
				String food_des = f_description.getText();
				
				int price = Integer.parseInt(food_price);
				
				Connection con1;
				PreparedStatement insert;
				
				try {
					String host = "jdbc:mysql://localhost/"+S_NAME+"";
                    String user = "root";
                    String pass = "";
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					
					
					con1 = DriverManager.getConnection(host,user,pass);
					insert = con1.prepareStatement("INSERT INTO `customise_food`(`f_name`, `price`, `category`, `description`) VALUES (?,?,?,?)");
					insert.setString(1,f_name);
					insert.setInt(2, price);
					insert.setString(3,food_category);
					insert.setString(4,food_des);
					insert.executeUpdate();
					
					
					JOptionPane.showMessageDialog(btnNewButton, "Data Updated");
					foodName.setText("");
					f_price.setText("");
					f_description.setText("");
					foodName.requestFocus();
					
					
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		addFood.setFont(new Font("Tahoma", Font.BOLD, 14));
		addFood.setBounds(379, 504, 145, 30);
		contentPane.add(addFood);
		
		JButton deleteFood = new JButton("DELETE FOOD");
		deleteFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "jdbc:mysql://localhost:3306/"+S_NAME+"";
		        String user = "root";
		        String password = "";
		     

		        // The ID of the row you want to delete
		        String f_name = foodName.getText(); // Replace this with the ID of the row you want to delete

		        String deleteQuery = "DELETE FROM customise_food WHERE f_name = ?";

		        try (Connection connection = DriverManager.getConnection(url, user, password);
		             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

		            // Set the ID of the row to be deleted
		            preparedStatement.setString(1, f_name);

		            // Execute the delete query
		            int rowsAffected = preparedStatement.executeUpdate();

		            if (rowsAffected > 0) {
		                System.out.println("Row deleted successfully!");
		            } else {
		                System.out.println("No row was deleted. Check if the ID exists in the table.");
		            }

		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
			}
		});
		deleteFood.setFont(new Font("Tahoma", Font.BOLD, 14));
		deleteFood.setBounds(1013, 504, 145, 30);
		contentPane.add(deleteFood);
		
		JButton getFood = new JButton("GET FOOD");
		getFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int f_id = Integer.parseInt( foodID.getText());
				
				
				String url = "jdbc:mysql://localhost:3306/"+S_NAME+"";
		        String user = "root";
		        String password = "";
		        String query = "SELECT * FROM customise_food WHERE ID = ?";
		        try {
		            Connection connection = DriverManager.getConnection(url, user, password);
		            PreparedStatement preparedStatement = connection.prepareStatement(query);

		            // Set the condition value using a variable
		          
		            preparedStatement.setInt(1, f_id);

		            ResultSet resultSet = preparedStatement.executeQuery();
		            if (resultSet.next()) {
		                // Assuming you want to retrieve data from the first row and first column
		                String fName = resultSet.getString(2);
		                String fPrice = resultSet.getString(3);
		                String fCategory = resultSet.getString(4);
		                String fDescription = resultSet.getString(5);
		                foodName.setText(fName);
		                f_price.setText(fPrice);
		                f_category.setSelectedItem(fCategory);
		                f_description.setText(fDescription); 
		                
		                
		            } else {
		                // Handle the case when no data is found
		                foodName.setText("No data found");
		            }

		            
		            
		        } catch (Exception e1) {
		            e1.printStackTrace();
		            
		        }
		        
		        
			}
		});
		getFood.setFont(new Font("Tahoma", Font.BOLD, 14));
		getFood.setBounds(584, 504, 145, 30);
		contentPane.add(getFood);
		
		JButton updateFood = new JButton("UPDATE FOOD");
		updateFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String url = "jdbc:mysql://localhost:3306/"+S_NAME+"";
			        String user = "root";
			        String password = "";

			        // The data to be updated
			        int f_ID = Integer.parseInt(foodID.getText());
			        String f_name = foodName.getText();
					String food_price = f_price.getText();
					String food_category = f_category.getSelectedItem().toString();
					String food_des = f_description.getText();
					
					int price = Integer.parseInt(food_price);
					
			        
			        
			        
			      
			        String updateQuery = "UPDATE `customise_food` SET  price = ?, category = ?,description = ?  WHERE f_name = ?";
			        System.out.println(f_name+"    "+price+"    "+food_category+"    "+food_des);
			        try (Connection connection = DriverManager.getConnection(url, user, password);
			             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

			            // Set the new value and the ID of the row to be updated
//			        	preparedStatement.setInt(1, f_ID);
			        	
			        	preparedStatement.setInt(1, price);
			        	preparedStatement.setString(2,food_category);
			        	preparedStatement.setString(3,food_des);
			        	preparedStatement.setString(4,f_name);
//			            
			        	
			        	
			        	
			            // Execute the update query
			            int rowsAffected = preparedStatement.executeUpdate();

			            if (rowsAffected > 0) {
			                System.out.println("Data updated successfully!");
			            } else {
			                System.out.println("No rows were updated.");
			            }

			        } catch (SQLException e1) {
			            e1.printStackTrace();
			        }
			}                       
		});
		updateFood.setFont(new Font("Tahoma", Font.BOLD, 14));
		updateFood.setBounds(792, 504, 145, 30);
		contentPane.add(updateFood);
	}



}
