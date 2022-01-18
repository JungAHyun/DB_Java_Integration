package 정아현;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;

public class ImcomeSearchF extends JFrame {

	private JPanel contentPane;
	static String searchproductname ,searchprice;
	static int searchproductid;

	
	 Object  dbpid = null, dbdateIncome, dbproductName, dbproductSpec, productModel,dbcompName,dbcmNo, 
			 dbproductUnit, dbquantity, dbunitPrice, dbtotalPrice, dbsalePrice, dbcmID, dbcmName, dbbigo;  
	
	 int row = -1;
	JScrollPane scrollPane;	    
    Object header[] = {"물품ID", "입고일", "물품명", "물품모델", "규격", "단위", "수량", "단가","부속품" , "총구매액", "판매금액", "업체", "비고"};
	Object data[][];
	
	private final DefaultTableModel model = new DefaultTableModel(data, header){
		 public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
	 
	 JTable table = new JTable(model);
	
	DB db = new DB();
	Connection conn = db.conn();
	PreparedStatement pstmt;
	private JButton okB;
	private JPanel panel;
	private JLabel lblNewLabel_8;


	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImcomeSearchF frame = new ImcomeSearchF("네비");
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
	public ImcomeSearchF(String productname) {
		setTitle("\uC5C5\uCCB4\uC815\uBCF4");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1029, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		table.setCellSelectionEnabled(true);
		scrollPane.setBounds(12, 75, 989, 156);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(2).setPreferredWidth(115);
		table.getColumnModel().getColumn(5).setPreferredWidth(55);
		table.getColumnModel().getColumn(6).setPreferredWidth(55);
		table.getColumnModel().getColumn(7).setPreferredWidth(70);
		table.getColumnModel().getColumn(11).setPreferredWidth(120);
		contentPane.add(scrollPane);
		
		okB = new JButton("\uC120\uD0DD");
		okB.setBounds(827, 251, 97, 23);
		okB.addActionListener(new MyActionListener());
		contentPane.add(okB);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(12, 15, 989, 32);
		contentPane.add(panel);
		
		lblNewLabel_8 = new JLabel("\uC785\uACE0\uAC80\uC0C9");
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		panel.add(lblNewLabel_8);
		
		setVisible(true);
	
		
	    int count =0;
		
		
	    String sql = "select * "
		+"from input "
		+"where productName like '%' ? '%' ;" ;

	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,productname);			
		
		ResultSet rs = pstmt.executeQuery();
		
		if(row >= 1) {
			for(row=row+1; row >= 0; row--) {
			model.removeRow(row);}
		}
	 
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
			model.addRow(data);
			
			count = 1;
		}
		
	} catch (SQLException e1) {
		e1.printStackTrace();
		}
	
	if(count == 0) {  
		JOptionPane.showMessageDialog(null,"해당 물품이 없습니다."); 
		dispose();}
	
	}
	
	
	private class MyActionListener implements ActionListener{  
	
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			//선택한 행의 데이터를 저장하고 판매등록에서 사용함.
			if(b.getText().equals("선택")) { 
				 int row = table.getSelectedRow();
				 searchproductid = Integer.parseInt(table.getValueAt(row, 0).toString());
				 searchproductname = table.getValueAt(row, 2).toString();
				 searchprice = table.getValueAt(row, 9).toString();
				 SaleRegist.setProduct();
				 dispose();
			}
			
			
		}
		
	}
}
