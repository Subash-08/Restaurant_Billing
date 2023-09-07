package billing;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class order_food extends JFrame {

	private JPanel contentPane;
	private JTextField search_field1;
	private JTable table_1;
	private JTextField get_CName;
	private JTextField Get_no;
	private JTextField f_ID;
	private JTextField quantity;
	private JTable table_2;
	JPanel panel_1;
	static String S_NAME;
	int count=0;
	JTextPane textPane;

	private String getCurrentDate() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy ");
	    Date currentDate = new Date();
	    return dateFormat.format(currentDate);
	}
	private String getCurrentTime() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	    Date currentDate = new Date();
	    return dateFormat.format(currentDate);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					SwingUtilities.invokeLater(() -> {
					order_food od = new order_food(S_NAME);
					od.setVisible(true);
					od.update_table1(S_NAME);
//					od.update_table2();
					  
			        });
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public order_food(String shopname) {
		String S_NAME = shopname;
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
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				billing_system bs =new  billing_system(S_NAME);
				
				
			 bs.update_table(S_NAME);
			}
		});
		btnNewButton.setBounds(0, 72, 75, 25);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK, 3));
		panel.setBounds(30, 124, 400, 620);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TOTAL FOODS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(120, 25, 161, 30);
		panel.add(lblNewLabel);
		
		search_field1 = new JTextField();
		search_field1.setBounds(30, 73, 219, 28);
		panel.add(search_field1);
		search_field1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel Df = (DefaultTableModel)table_1.getModel();
				TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(Df);
				table_1.setRowSorter(obj1);
//				System.out.println(Df);
				obj1.setRowFilter(RowFilter.regexFilter(search_field1.getText()));
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_1.setBounds(269, 72, 99, 30);
		panel.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 123, 340, 480);
		panel.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Food ID", "Food Name", "Food Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.BLACK, 3));
		panel_1.setBounds(1088, 124, 400, 620);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBill = new JLabel("BILL");
		lblBill.setHorizontalAlignment(SwingConstants.CENTER);
		lblBill.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBill.setBounds(119, 25, 161, 30);
		panel_1.add(lblBill);
		
		JButton btnNewButton_2 = new JButton("PRINT");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBounds(68, 554, 100, 35);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("CLEAR");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_3.setBounds(230, 554, 100, 35);
		panel_1.add(btnNewButton_3);
		
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.BLACK, 3));
		panel_2.setBounds(494, 124, 530, 620);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblOrderFood = new JLabel("ORDER FOOD");
		lblOrderFood.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderFood.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblOrderFood.setBounds(189, 25, 161, 30);
		panel_2.add(lblOrderFood);
		
		get_CName = new JTextField();
		get_CName.setBounds(250, 65, 193, 30);
		panel_2.add(get_CName);
		get_CName.setColumns(10);
		
		Get_no = new JTextField();
		Get_no.setBounds(250, 111, 193, 30);
		panel_2.add(Get_no);
		Get_no.setColumns(10);
		
		f_ID = new JTextField();
		f_ID.setBounds(250, 162, 193, 30);
		panel_2.add(f_ID);
		f_ID.setColumns(10);
		
		quantity = new JTextField();
		quantity.setBounds(250, 209, 193, 30);
		panel_2.add(quantity);
		quantity.setColumns(10);
	
		JButton btnNewButton_4 = new JButton("ADD FOOD");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		            
				update_table2(S_NAME);
				
				
				
				
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_4.setBounds(214, 249, 126, 30);
		panel_2.add(btnNewButton_4);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(117, 65, 101, 25);
		panel_2.add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(40, 283, 450, 250);
		panel_2.add(scrollPane_1);
		
		table_2 = new JTable();
		table_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane_1.setViewportView(table_2);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Food Name", "Quantity", "Food Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JLabel lblNewLabel_1_1 = new JLabel("Mobile.NO");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(117, 111, 101, 25);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Food ID");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(117, 162, 101, 25);
		panel_2.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Quantity");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(117, 209, 101, 25);
		panel_2.add(lblNewLabel_1_3);
		
		JButton btnNewButton_5 = new JButton("ADD");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				JTextPane newPanel = createNewPanel();
                panel_1.add(newPanel);
                newPanel.setBounds(30, 75, 340, 450);
                JTextPane newPanel1 = createNewPanel1();
                panel_1.add(newPanel1);
                newPanel.setBounds(30, 200, 340, 450);
                contentPane.revalidate(); // Refresh the layout
                contentPane.repaint();    // Repaint the frame
				
                String C_Name = get_CName.getText();
                int Mobile_no = Integer.parseInt(Get_no.getText());
                String time = getCurrentTime();
                String date = getCurrentDate();
               
                
                String FOOD_N;
                int QUANTITY,PRICE; 
                DefaultTableModel Df2 = (DefaultTableModel)table_2.getModel();
				if(Df2.getRowCount() == 0) {
					JOptionPane.showMessageDialog(btnNewButton_5 ,"No Food is added" );
				}
				else {
			
					try {
					String host = "jdbc:mysql://localhost/"+S_NAME+"";
					String user = "root";
		     		String pass = "";
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con1 = DriverManager.getConnection(host,user,pass);
					
					for(int i = 0;i<Df2.getRowCount();i++) {
						FOOD_N = Df2.getValueAt(i, 0).toString();
						QUANTITY = Integer.parseInt(Df2.getValueAt(i, 1).toString());
						PRICE = Integer.parseInt(Df2.getValueAt(i, 2).toString());
						
						String query = "insert into customer_info (c_name,number,foodname,quantity,price,time,date) values (?,?,?,?,?,?,?)";
						
						PreparedStatement p = con1.prepareStatement(query);
						p.setString(1, C_Name);
						p.setInt(2,Mobile_no);
//						p.setInt(3,FOODID);
						p.setString(3, FOOD_N);
						p.setInt(4,QUANTITY);
						p.setInt(5,PRICE);
						p.setString(6, time);
						p.setString(7, date);
						System.out.println(C_Name+"   "+Mobile_no+"   "+FOOD_N+"   "+QUANTITY+"   "+PRICE+"   "+time+"   "+date);
						
						p.execute();
						
					}
					
					} catch (SQLException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}			        
				}
			}

			
			
			
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_5.setBounds(85, 565, 117, 30);
		panel_2.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("CANCEL");
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_6.setBounds(326, 565, 117, 30);
		panel_2.add(btnNewButton_6);
	}
	
	public void update_table1(String shopname) {
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
				insert = con1.prepareStatement("select  `ID`, `f_name`, `price` from `customise_food`");
				
				ResultSet rs = insert.executeQuery();
				ResultSetMetaData res = rs.getMetaData();
				c= res.getColumnCount();
				
				DefaultTableModel Df = (DefaultTableModel)table_1.getModel();
				Df.setRowCount(0);
				while(rs.next()) {
					Vector v2 = new Vector();
					
					for(int a=1;a<=1;a++) {
						v2.add(rs.getString("ID"));
						v2.add(rs.getString("f_name"));
						v2.add(rs.getString("price"));
						
					}
//					System.out.println(v2);
					
					Df.addRow(v2);
					
				}
				
				
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}
	
	public void update_table2(String shopname) {
		// TODO Auto-generated method stub
		int f_id = Integer.parseInt( f_ID.getText());
		int f_quantity = Integer.parseInt(quantity.getText());
		
		
		String url = "jdbc:mysql://localhost:3306/"+shopname+"";
        String user = "root";
        String password = "";
        String query = "SELECT * FROM customise_food WHERE ID = ?";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set the condition value using a variable
          
            preparedStatement.setInt(1, f_id);

				
				ResultSet rs = preparedStatement.executeQuery();
				String fName = null;
				int fPrice;
			
				if (rs.next()) {
	                // Assuming you want to retrieve data from the first row and first column
	                fName = rs.getString(2);
	                fPrice = rs.getInt(3);
//	                fCategory = rs.getString(4);
//			 }
					
				ResultSetMetaData res = rs.getMetaData();
				 
				
				int c= res.getColumnCount();
			
				DefaultTableModel Df = (DefaultTableModel)table_2.getModel();
				
				Df.setRowCount(count);
				
//				while(rs.next()) {
					
//					System.out.println(fName);
					Vector v2 = new Vector();
					
					for(int a=1;a<=c;a++) {
					v2.add(fName);
					
					v2.add(f_quantity);
					v2.add(fPrice*f_quantity);
					}
//					
					Df.addRow(v2);
					
					
					f_ID.setText("");
					quantity.setText("");
					
//				}
				}
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        count++;
//   
     
	}
	
	private JTextPane createNewPanel() {
		// TODO Auto-generated method stub
		
//		
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(30, 75, 340, 450);
		textPane.setText("***************WELCOME ************** \n"
					+    "-------------------------------------- ");
		textPane.setText("tfghjk");
		
		
		
//		textPane.add(textPane);
		
		
		return textPane;
	}
	
	private JTextPane createNewPanel1() {
	JTextPane textPane = new JTextPane();
//	textPane.setBounds(30, 75, 340, 450);
	textPane.setText("gfchjk");
	
	
	
	
//	textPane.add(textPane);
	
	
	return textPane;
}
}
