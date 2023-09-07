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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class total_orders extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static String S_NAME;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					total_orders frame = new total_orders(S_NAME);
					frame.setVisible(true);
					frame.update_Ctable(S_NAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public total_orders(String shopname) {
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
				billing_system bs = new billing_system(S_NAME);
				bs.update_table(S_NAME);
			}
		});
		btnNewButton.setBounds(0, 72, 75, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("TOTAL ORDERS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(659, 108, 215, 35);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 153, 1300, 520);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Customer name", "Food Name", "Quantity", "Price", "Time", "Date"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Integer.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JButton btnNewButton_1 = new JButton("LOGOUT");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(581, 698, 150, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("EXIT");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1_1.setBounds(891, 698, 150, 30);
		contentPane.add(btnNewButton_1_1);
	}
	
	public void update_Ctable(String shopname) {
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
				insert = con1.prepareStatement("select * from `customer_info`");
				
				ResultSet rs = insert.executeQuery();
				ResultSetMetaData res = rs.getMetaData();
				c= res.getColumnCount();
				
				DefaultTableModel Df = (DefaultTableModel)table.getModel();
				Df.setRowCount(0);
				while(rs.next()) {
					Vector v2 = new Vector();
					
					for(int a=1;a<=c;a++) {
						v2.add(rs.getString("c_name"));
						v2.add(rs.getString("foodname"));
						v2.add(rs.getString("quantity"));
						v2.add(rs.getString("price"));
						v2.add(rs.getString("time"));
						v2.add(rs.getString("date"));
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
