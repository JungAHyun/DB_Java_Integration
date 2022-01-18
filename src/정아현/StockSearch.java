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

public class StockSearch extends JFrame {

	private JPanel contentPane;
	static String searchproductname ,searchprice, searchproductmodel, searchcompname ;
	static int searchproductid, searchtid;

	
	static int dbquantity;   //판매에서 수량을 수정해주기위해 static사용
	int  dbtid, dbpid, dbunitPrice;  
	String dbproductName,dbproductModel, dbcompName;
	
	int row = -1;
	JScrollPane scrollPane;	    
    Object header[] = {"재고NO", "입고번호", "물품명", "물품모델", "부속품", "재고수량", "단가"};
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
					StockSearch frame = new StockSearch("헤");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	public StockSearch(String productName) {
		setTitle("\uC5C5\uCCB4\uC815\uBCF4");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 919, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		table.setCellSelectionEnabled(true);
		scrollPane.setBounds(12, 75, 873, 156);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		table.getColumnModel().getColumn(2).setPreferredWidth(115);
		table.getColumnModel().getColumn(5).setPreferredWidth(55);
		table.getColumnModel().getColumn(6).setPreferredWidth(55);

		contentPane.add(scrollPane);
		
		okB = new JButton("\uC120\uD0DD");
		okB.setBounds(761, 249, 97, 23);
		okB.addActionListener(new MyActionListener());
		contentPane.add(okB);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(12, 15, 873, 32);
		contentPane.add(panel);
		
		lblNewLabel_8 = new JLabel("\uC7AC\uACE0\uAC80\uC0C9");
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		panel.add(lblNewLabel_8);
		
		setVisible(true);
		
	    int count =0;
		
	    
		// 물품이름에 해당단어가 들어가는 것만 select
	    String sql = "select * "
		+"from stock "
		+"where productName like '%' ? '%' ;" ;

	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,productName);			
		
		ResultSet rs = pstmt.executeQuery();
		
		if(row >= 1) {
			for(row=row+1; row >= 0; row--) {
			model.removeRow(row);}
		}
	 
		while(rs.next()){
			row = 0;				
			dbtid = rs.getInt("tid") ;
			dbpid = rs.getInt("pid") ;
			dbproductName = rs.getString("productName");
			dbquantity = rs.getInt("quantityLeft");
			dbcompName = rs.getString("compName");
			dbproductModel = rs.getString("productModel");
			dbunitPrice  = rs.getInt("price");
			row++;
			
			Object data[] = {dbtid, dbpid, dbproductName,dbproductModel, dbcompName, dbquantity, dbunitPrice };
			model.addRow(data);
			
			count = 1;
		}
		
	} catch (SQLException e1) {
		e1.printStackTrace();
		}
	
	if(count == 0 && dbquantity <= 0) {  
		JOptionPane.showMessageDialog(null,"재고가 없습니다."); 
		dispose();}
	
	}
	
	
	private class MyActionListener implements ActionListener{  
	
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			if(b.getText().equals("선택")) { 
				//재고에서 선택된 데이터를 저장하고 판매등록의 텍스트필드에 나타나게할때 사용됨.
				 int row = table.getSelectedRow();
				 searchtid = Integer.parseInt(table.getValueAt(row, 0).toString());
				 searchproductid = Integer.parseInt(table.getValueAt(row, 1).toString());
				 searchproductname = table.getValueAt(row, 2).toString();
				 searchproductmodel = table.getValueAt(row, 3).toString();
				 searchcompname =  table.getValueAt(row, 4).toString();
				 searchprice = table.getValueAt(row, 6).toString();
				 SaleRegist.setProduct();
				 dispose();
			}
			
			
		}
		
	}
}

