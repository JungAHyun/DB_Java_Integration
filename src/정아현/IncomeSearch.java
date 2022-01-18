package 정아현;

import 정아현.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.SwingConstants;

public class IncomeSearch extends JPanel {

	static JPanel ISpanel;
	JSpinner toSpn;
	JSpinner fromSpn; 
	
	int row=0, col, rowcount=-1;
	String cidGet;
    Object header[] = {"물품ID", "입고일", "물품명", "물품모델", "규격", "단위", "수량", "단가","부속품" , "총구매액", "판매금액", "업체", "비고"};
	Object data[][];
	
	private final DefaultTableModel tableModel = new DefaultTableModel(data, header){
		 public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
	 
	 JTable table = new JTable(tableModel);
	 
	 
		DB db = new DB();
		Connection conn = db.conn();
		PreparedStatement pstmt;
		
	 
	public static void main(String[] args) {
		new IncomeSearch();	
	}

	
	public IncomeSearch() {
		setBounds(100, 100, 972, 337);
		ISpanel = new JPanel();
		ISpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ISpanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(39, 81, 749, 31);
		ISpanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\uC77C\uC790From");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(25, 0, 66, 31);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uC77C\uC790To");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(261, 0, 58, 31);
		panel.add(lblNewLabel_1_1);
		
		JButton searchB = new JButton("\uAC80\uC0C9");
		searchB.setBounds(557, 4, 97, 23);
		searchB.addActionListener(new MyActionListener());
		panel.add(searchB);
		
		SimpleDateFormat format;
		SpinnerDateModel tospM = new SpinnerDateModel();
		
		toSpn = new JSpinner(tospM);
		toSpn.setBounds(326, 0, 135, 31);
		panel.add(toSpn);
		toSpn.setEditor(new JSpinner.DateEditor(toSpn, "yyyy-MM-dd"));
		
		SpinnerDateModel fromspM = new SpinnerDateModel();
		fromSpn = new JSpinner(fromspM);
		fromSpn.setBounds(94, 0, 132, 31);
		panel.add(fromSpn);
		fromSpn.setEditor(new JSpinner.DateEditor(fromSpn, "yyyy-MM-dd"));
		
		JScrollPane scrollPane = new JScrollPane();
		table.setCellSelectionEnabled(true);
		scrollPane.setBounds(12, 132, 932, 156);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(2).setPreferredWidth(115);
		table.getColumnModel().getColumn(5).setPreferredWidth(55);
		table.getColumnModel().getColumn(6).setPreferredWidth(55);
		table.getColumnModel().getColumn(7).setPreferredWidth(70);
		table.getColumnModel().getColumn(11).setPreferredWidth(120);
		table.addMouseListener(new MyMouseListener());
		ISpanel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(12, 10, 932, 34);
		ISpanel.add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC785\uACE0\uC870\uD68C");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		lblNewLabel_2.setBackground(Color.ORANGE);
		panel_1.add(lblNewLabel_2);
		
		setVisible(true);
	}
	
	private class MyActionListener implements ActionListener{  
	
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			Object fromDate, toDate;
			
			if(b.getText().equals("검색")) {
				
				toDate =  toSpn.getValue();	
				DateFormat formatTo = new SimpleDateFormat("yyyy-MM-dd");
				String todateS = formatTo.format(toDate);
				
				fromDate =  fromSpn.getValue();	
				DateFormat formatFrom = new SimpleDateFormat("yyyy-MM-dd");
				String fromdateS = formatFrom.format(fromDate);
				
				Object  dbpid = null, dbdateIncome, dbproductName, dbproductSpec, productModel,dbcompName,dbcmNo, 
						 dbproductUnit, dbquantity, dbunitPrice, dbtotalPrice, dbsalePrice, dbcmID, dbcmName, dbbigo;  
				
				
			    String sql = "select * "
			    		+ "from input "
			    		+ "where dateIncome between ? and ? " ;
				try {
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, fromdateS);
					pstmt.setString(2, todateS);
	
					if(rowcount > 0) {
						for(; rowcount > 0; rowcount--) {	
							tableModel.removeRow(rowcount-1);}                  
					}
					
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()){
						row = 0; 		
						dbpid = rs.getString("pid") ;
						dbdateIncome = rs.getDate("dateIncome") ;
						dbproductName = rs.getString("productName"); 
						dbproductSpec = rs.getString("productSpec");
						dbproductUnit = rs.getString("productUnit");
						dbquantity = rs.getString("quantity");
						dbunitPrice = rs.getString("unitPrice");
						dbtotalPrice = rs.getString("totalPrice");
						dbsalePrice = rs.getString("salePrice");
						dbcmID = rs.getString("cmID");	
						dbcmName = rs.getString("cmName");
						dbbigo = rs.getString("bigo");
						dbcmNo = rs.getString("cmNo");
						dbcompName = rs.getString("compName");
						productModel = rs.getString("productModel");
						row++;
						
						Object data[] = {dbpid, dbdateIncome, dbproductName,productModel, dbproductSpec, dbproductUnit, 
								dbquantity, dbunitPrice, dbcompName, dbtotalPrice, dbsalePrice, dbcmName, dbbigo };
						tableModel.addRow(data);
	
						rowcount = table.getRowCount();
					}	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	 
					 
			} 			
		}	
	}
	
	private class MyMouseListener extends MouseAdapter {    
		
		public void mouseClicked(MouseEvent e) {
			//더블클릭 시 수정할 수 있는 창 띄우면서 pid넘겨준다.     
			if (e.getClickCount() == 2) {                   
			    	 int row = table.getSelectedRow();		    	 
				     String productID = table.getValueAt(row, 0).toString();
				     
				     int pid =  Integer.parseInt(productID);
					 new IncomeModify(pid); 
			    
			     }	
		}
	}

}	
	


