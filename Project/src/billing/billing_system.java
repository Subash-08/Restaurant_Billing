package billing;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;

public class billing_system extends JFrame {

	private JPanel contentPane;
	private JTextField search_field;
	private JTextField textField_1;
	JTable table;
	static String S_NAME ;
	
	
	
	  
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					billing_system bs = new billing_system(S_NAME);
					
					bs.setVisible(true);
//					bs.update_table();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	

	/**
	 * Create the frame.
	 */
	 billing_system(String shopname) {
		 
		 String S_NAME =shopname; 
		System.out.println(shopname+"------------------");
//		 update_table();
		 
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
		
		JLabel lblRestaurantBillingSystem = new JLabel(S_NAME+" BILLING SYSTEM");
		lblRestaurantBillingSystem.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblRestaurantBillingSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestaurantBillingSystem.setBounds(413, 10, 681, 52);
		contentPane.add(lblRestaurantBillingSystem);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.RED);
		separator_1.setBounds(10, 72, 1540, 4);
		contentPane.add(separator_1);
		
		JButton btnNewButton = new JButton("ADD FOOD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new customise(S_NAME);
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(73, 139, 151, 30);
		contentPane.add(btnNewButton);
		
		search_field = new JTextField();
		search_field.setBounds(328, 137, 145, 30);
		contentPane.add(search_field);
		search_field.setColumns(10);
		
		JButton search_b = new JButton("Search by Name ");
		search_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel Dfs = (DefaultTableModel) table.getModel();
				TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(Dfs);
				table.setRowSorter(obj);
//				System.out.println(obj);
				obj.setRowFilter(RowFilter.regexFilter(search_field.getText()));
				
			}
		});
		search_b.setFont(new Font("Tahoma", Font.PLAIN, 12));
		search_b.setBounds(498, 139, 133, 30);
		contentPane.add(search_b);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Starter", "Beverages", "Main course", "Ice Cream", "Combo"}));
		comboBox.setBounds(747, 136, 145, 30);
		contentPane.add(comboBox);
		
		JButton searchByC = new JButton("Search by Category");
		searchByC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel Dfs = (DefaultTableModel) table.getModel();
				TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(Dfs);
				table.setRowSorter(obj);
			
//				Object combo =(String)comboBox.getSelectedItem();
//				System.out.println((String)comboBox.getSelectedItem());
				obj.setRowFilter(RowFilter.regexFilter((String)comboBox.getSelectedItem()));
				
				
			}
		});
		searchByC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		searchByC.setBounds(902, 135, 138, 30);
		contentPane.add(searchByC);
		
		textField_1 = new JTextField();
		textField_1.setBounds(1145, 137, 145, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_9 = new JButton("Search by Price");
		btnNewButton_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_9.setBounds(1303, 137, 121, 30);
		contentPane.add(btnNewButton_9);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(328, 199, 1100, 520);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Food Name", "Food Price", "Food Category", "Food Description"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
//		update_table();
//		JTable jTable = new JTable();
//		
		
		JButton btnNewButton_1 = new JButton("UPDATE FOOD");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new customise(S_NAME);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(73, 223, 151, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_10 = new JButton("DELETE FOOD");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new customise(S_NAME);
			}
		});
		btnNewButton_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_10.setBounds(73, 314, 151, 30);
		contentPane.add(btnNewButton_10);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExit.setBounds(73, 689, 151, 30);
		contentPane.add(btnExit);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loginPage();
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogout.setBounds(73, 596, 151, 30);
		contentPane.add(btnLogout);
		
		JButton btnTotalOrders = new JButton("TOTAL ORDERS");
		btnTotalOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				total_orders to =new total_orders(S_NAME);
				to.update_Ctable(S_NAME);
			}
		});
		btnTotalOrders.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTotalOrders.setBounds(73, 502, 151, 30);
		contentPane.add(btnTotalOrders);
		
		JButton btnOrderFood = new JButton("ORDER FOOD");
		btnOrderFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order_food of =new order_food(S_NAME);
				of.update_table1(S_NAME);
				
			}
		});
		btnOrderFood.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOrderFood.setBounds(73, 404, 151, 30);
		contentPane.add(btnOrderFood);
	}

	public void update_table(String shopname) {
		// TODO Auto-generated method stub
		int c;
		 Connection con1;
			PreparedStatement insert;
			
			try {
				String host = "jdbc:mysql://localhost/"+shopname+"";
	     String user = "root";
	     String pass = "";
				Class.forName("com.mysql.cj.jdbc.Driver");
				con1 = DriverManager.getConnection(host,user,pass);
				insert = con1.prepareStatement("select * from `customise_food`");
				
				ResultSet rs = insert.executeQuery();
				ResultSetMetaData res = rs.getMetaData();
				c= res.getColumnCount();
				
				DefaultTableModel Df = (DefaultTableModel)table.getModel();
				Df.setRowCount(0);
				while(rs.next()) {
					Vector v2 = new Vector();
					
					for(int a=1;a<=c;a++) {
						v2.add(rs.getString("ID"));
						v2.add(rs.getString("f_name"));
						v2.add(rs.getString("price"));
						v2.add(rs.getString("category"));
						v2.add(rs.getString("description"));
					}
//					System.out.println(v2);
					
					Df.addRow(v2);
					
				}
				
				
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}
}


