package 정아현;

import 정아현.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JSpinner;

public class SaleSearch extends JPanel {

	static JPanel SSpanel;
	JSpinner toSpn;
	JSpinner fromSpn; 
	
	//db에서 데이터를 가져와서 저장할 때 사용
	Object  dbsaledate, dbcompname, dbproductname, dbcname, dbsaledetail, dbproductcode, 
	dbprice, dbpayment, dbsalepersonID, dbsalepersonName, dbtotalprice, dbsid;
	int dbquantity;

	
	int row=-1, rowcount=-1;
	String cidGet;
    Object header[] = {"판매No","시공일", "물품명", "물품모델", "시공내용", "부속품", "수량", "가격","총판매액", "지불방법", "구매자", "영업사원"};
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
		new SaleSearch();	
	}

	
	public SaleSearch() {

		setBounds(100, 100, 928, 358);	
		SSpanel = new JPanel();
		SSpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		SSpanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(56, 78, 749, 31);
		SSpanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\uC77C\uC790From");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 71, 31);
		panel.add(lblNewLabel_1);
		
		JButton searchB = new JButton("\uAC80\uC0C9");
		searchB.setBounds(557, 4, 97, 23);
		searchB.addActionListener(new MyActionListener());
		panel.add(searchB);
		
		
		SimpleDateFormat format;
		SpinnerDateModel tospM = new SpinnerDateModel();
		
		toSpn = new JSpinner(tospM);
		toSpn.setBounds(314, 0, 135, 31);
		panel.add(toSpn);
		toSpn.setEditor(new JSpinner.DateEditor(toSpn, "yyyy-MM-dd"));
		
		SpinnerDateModel fromspM = new SpinnerDateModel();
		fromSpn = new JSpinner(fromspM);
		fromSpn.setBounds(78, 0, 132, 31);
		panel.add(fromSpn);
		fromSpn.setEditor(new JSpinner.DateEditor(fromSpn, "yyyy-MM-dd"));
		
		
		JLabel lblNewLabel_1_1 = new JLabel("\uC77C\uC790To");
		lblNewLabel_1_1.setBounds(250, 0, 58, 31);
		panel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		table.setCellSelectionEnabled(true);
		scrollPane.setBounds(12, 122, 1139, 176);
		scrollPane.setViewportView(table);
		
	    
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(190);
		table.getColumnModel().getColumn(3).setPreferredWidth(180);
		table.getColumnModel().getColumn(4).setPreferredWidth(180);
		table.getColumnModel().getColumn(5).setPreferredWidth(180);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		table.getColumnModel().getColumn(7).setPreferredWidth(70);
		table.addMouseListener(new MyMouseListener());
		SSpanel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(12, 10, 1139, 34);
		SSpanel.add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uD310\uB9E4\uC870\uD68C");
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBackground(new Color(255, 200, 0));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		

		setVisible(true);
		
	   }
	    
	private class MyActionListener implements ActionListener{  
	
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			Object toDate, fromDate;
			
			if(b.getText().equals("검색")) {

				toDate =  toSpn.getValue();	
				DateFormat formatTo = new SimpleDateFormat("yyyy-MM-dd");
				String todateS = formatTo.format(toDate);
				
				fromDate =  fromSpn.getValue();	
				DateFormat formatFrom = new SimpleDateFormat("yyyy-MM-dd");
				String fromdateS = formatFrom.format(fromDate);
				
				
			    String sql = "select * "
			    		+ "from carsale, carcustomer, salespersons "
			    		+ "where saledate between ? and ? "
			    		+ "and  carcustomer.cid = carsale.cid "
			    		+ "and  salespersons.smID = carsale.smID ;" ;
				try {
					System.out.println(fromdateS +" ~ "+ todateS);
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, fromdateS);
					pstmt.setString(2, todateS);
	
					if(rowcount > 0) {	
						for(; rowcount > 0; rowcount--) 	
							System.out.println(rowcount);			
					}
					
					
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()){
						row = 0; 
						//디비의 데이터 가져와서 jtable에 부착하기
						dbsid = rs.getInt("sid");
						dbsaledate = rs.getDate("saledate") ;
						dbcompname = rs.getString("compname"); 
						dbproductname = rs.getString("productname");
						dbsaledetail = rs.getString("saledetail");
						dbproductcode = rs.getString("productModel");
						dbprice = rs.getInt("price");
						dbpayment = rs.getString("payment");
						dbsalepersonID = rs.getString("smID");
						dbsalepersonName =  rs.getString("saleperson");
						dbcname = rs.getString("cname");
						dbquantity =  rs.getInt("quantity");
						dbtotalprice = rs.getInt("totalprice");
						cidGet = rs.getString("cid");
						row++;
					
						Object data[] = {dbsid, dbsaledate, dbproductname, dbproductcode, dbsaledetail, dbcompname, dbquantity, dbprice,
								dbtotalprice, dbpayment, cidGet +"-"+ dbcname, dbsalepersonName};
						tableModel.addRow(data);

						System.out.println(dbsaledate + "\t" + 
								dbcname + "\t" + 
								dbcompname + "\t" +
								dbproductname+ "\t" +
								dbsaledetail + "\t" + 
								dbproductcode + "\t" + 
								dbprice + "\t" +
								dbpayment+ "\t" +
								dbsalepersonID+ "\t" );	
					}
				
					rowcount = table.getRowCount();
					
	
			
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				}
			} 			
}
	

	private class MyMouseListener extends MouseAdapter {    
		
		public void mouseClicked(MouseEvent e) {
			//더블클릭 시 판매번호와 고객이름, 판매수량을 파라미터로 보내면서 수정클래스 생성  
			if (e.getClickCount() == 2) {                   
				     int row = table.getSelectedRow();
				     System.out.println("row"+row);
				     int sidValue = 0;
				     int cidValue = 0;
				     String cname = table.getValueAt(row, 10).toString();
				     sidValue = Integer.parseInt(table.getValueAt(row, 0).toString());
				     cidValue = Integer.parseInt(cname.substring(0,1)) ;
				     String cnameValue = cname.substring(2,5);
				     int quantityValue = Integer.parseInt(table.getValueAt(row, 6).toString());

				     new SaleModify(sidValue, cnameValue, quantityValue); 
			    
			     }	
		}
	
	}
	
}
