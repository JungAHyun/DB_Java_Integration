package 정아현;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import 정아현.*;
import javax.swing.SwingConstants;
import java.awt.SystemColor;


public class SaleModify extends JFrame {

	private JPanel contentPane;
	private JTextField dateTF;
	private JTextField compnameTF;
	private JTextField productnameTF;
	private JTextField detailTF;
	private JTextField productcodeTF;
	private JTextField priceTF;
	private JTextField salepersonidTF;
	private JComboBox paymentCB;
	private JTextField cnameTF;
	Object dateS;
	int dbtid ,sid, salequantity, beforequantity;

	

	DB db = new DB();
	Connection conn = db.conn();
	PreparedStatement pstmt;
	private JTextField salepersonnameTF;
	private JTextField quantityTF;
	
	public static void main(String[] args) {
				
	}


	public SaleModify(int sidValue, String cnameValue,  int salequantity) {
		setTitle("\uD310\uB9E4\uC218\uC815 \uBC0F \uC0AD\uC81C");
		sid = sidValue;
		this.salequantity = salequantity;  //판매에서 작성했던 수량

	
		
		System.out.println("sidMO = " + sid + " ,  " + "sidvalue = " + sidValue);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 250, 308, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 46, 268, 381);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 10, 55, 361);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel0 = new JLabel("\uACE0\uAC1D\uBA85");
		panel_1.add(lblNewLabel0);
		
		JLabel lblNewLabel = new JLabel("\uC2DC\uACF5\uC77C");
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uBB3C\uD488\uBA85");
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("\uBB3C\uD488\uCF54\uB4DC");
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("\uC2DC\uACF5\uB0B4\uC6A9");
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("\uBD80\uC18D\uD488");
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5_1 = new JLabel("\uC218\uB7C9");
		panel_1.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5 = new JLabel("\uD310\uB9E4\uAC00\uACA9");
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\uC9C0\uBD88\uBC29\uBC95");
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("\uC601\uC5C5\uC0AC\uC6D0");
		panel_1.add(lblNewLabel_7);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(68, 10, 188, 361);
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 5));
		
		cnameTF = new JTextField();
		panel_2.add(cnameTF);
		cnameTF.setColumns(10);
		
		dateTF = new JTextField();
		panel_2.add(dateTF);
		dateTF.setColumns(10);
		
		productnameTF = new JTextField();
		productnameTF.setColumns(10);
		panel_2.add(productnameTF);
		
		productcodeTF = new JTextField();
		productcodeTF.setColumns(10);
		panel_2.add(productcodeTF);
		
		detailTF = new JTextField();
		detailTF.setColumns(10);
		panel_2.add(detailTF);
		
		compnameTF = new JTextField();
		panel_2.add(compnameTF);
		compnameTF.setColumns(10);
		
		quantityTF = new JTextField();
		quantityTF.setColumns(10);
		panel_2.add(quantityTF);
		
		priceTF = new JTextField();
		panel_2.add(priceTF);
		priceTF.setColumns(10);
		
		paymentCB = new JComboBox();
		paymentCB.setModel(new DefaultComboBoxModel(new String[] {"\uD604\uAE08", "\uCE74\uB4DC", "\uACC4\uC88C\uC774\uCCB4", "\uBBF8\uB0A9"}));
		panel_2.add(paymentCB);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		salepersonidTF = new JTextField();
		salepersonidTF.setHorizontalAlignment(SwingConstants.CENTER);
		salepersonidTF.setBounds(0, 0, 73, 28);
		panel_3.add(salepersonidTF);
		
		salepersonnameTF = new JTextField();
		salepersonnameTF.setHorizontalAlignment(SwingConstants.CENTER);
		salepersonnameTF.setBounds(80, 0, 108, 28);
		panel_3.add(salepersonnameTF);
		
		JButton modifyB = new JButton("수정");
		modifyB.setBounds(163, 437, 97, 23);
     	modifyB.addActionListener(new MyActionListener());
		contentPane.add(modifyB);
		
		JButton deleteB = new JButton("삭제");
		deleteB.setBounds(36, 437, 97, 23);
		deleteB.addActionListener(new MyActionListener());
		contentPane.add(deleteB);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(SystemColor.activeCaption);
		panel_3_1.setBounds(12, 10, 268, 34);
		contentPane.add(panel_3_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("\uD310\uB9E4\uC218\uC815 & \uC0AD\uC81C ");
		lblNewLabel_8_1.setForeground(Color.BLACK);
		lblNewLabel_8_1.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		panel_3_1.add(lblNewLabel_8_1);
		
		setVisible(true);


		// 클릭한 것에 맞는 정보 보여줌
		String sql = "select * "
				+ " from carsale, salespersons "
				+ " where sid = ? and salespersons.smID = carsale.smID ";
			

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sidValue);

			ResultSet rs = pstmt.executeQuery();
	
			while(rs.next()){
				
				String dbdateGet = rs.getString("saledate");
				String dbpayment = rs.getString("payment");
				int dbcid = rs.getInt("cid");
				String dbproductmodel = rs.getString("productModel");
				String dbproductname = rs.getString("productname");
				String dbsaledetail = rs.getString("saledetail");
				String dbsmID = rs.getString("smID");
				String dbsaleperson = rs.getString("saleperson");
				String dbcompname = rs.getString("compname");
				String dbprice = rs.getString("price");
				String dbquantity = rs.getString("quantity");	
				dbtid =  rs.getInt("tid");
				
				System.out.println(cnameValue +" + " + dbdateGet + " + "+ dbproductname+ " + " +dbprice + " + " + dbproductmodel +
						 " + " +dbsaledetail+ " + " + dbcompname+ " + " +dbsmID + " + " + dbpayment);
				
				 dateTF.setText(dbdateGet);
				 cnameTF. setText(cnameValue);
				 paymentCB.setSelectedItem(dbpayment);
				 productnameTF.setText(dbproductname);
				 detailTF.setText(dbsaledetail);
				 productcodeTF.setText(dbproductmodel);
				 compnameTF.setText(dbcompname);
				 priceTF.setText((String) dbprice);
				 salepersonidTF.setText(dbsmID);
				 salepersonnameTF.setText(dbsaleperson); 
				 quantityTF.setText(dbquantity);
				 dateTF.setEnabled(false);
				 cnameTF.setEnabled(false);
			}

				
		} catch (SQLException e1) {
			e1.printStackTrace();
			
	}	
	
	}
	
	private class MyActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			String saledate = null;
			String productname, productmodel, saledetail, compname,  smname;
			Object payment, cname;
			int quantity,  price, smid;
			int tid = SaleRegist.tid;
			if(b.getText().equals("수정")) {
				
				 productname = productnameTF.getText();
				 productmodel = productcodeTF.getText(); 
				 saledetail = detailTF.getText();
				 compname = compnameTF.getText(); 
				 price = Integer.parseInt(priceTF.getText()); 
				 payment = paymentCB.getSelectedItem();
				 smid = Integer.parseInt(salepersonidTF.getText()); 
				 quantity =Integer.parseInt(quantityTF.getText()); 
				 smname = salepersonnameTF.getText(); 
				 
				 
				 //smID가 수정되었을때 영업사원의 이름도 함꼐 수정되게 함
				String sqlsmNAME = "select smNAME "
						+ "from salespersons "
						+ "where smID = ?";				
				try {
					pstmt = conn.prepareStatement(sqlsmNAME);
					pstmt.setInt(1, smid);
					
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()){
						salepersonnameTF.setText(rs.getString("smNAME"));
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				//판매수정하기
				String sql = "update carsale "
						+ "set productname =?, productModel = ?, saledetail = ?, "
						+ " compname = ?, price = ?, payment = ?, smID = ? , saleperson= ?, quantity = ?  "
						+ "where sid = ? ";

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, productname);
					pstmt.setString(2,productmodel);
					pstmt.setString(3, saledetail);
					pstmt.setString(4, compname);
					pstmt.setInt(5,price);
					pstmt.setString(6,(String) payment);
					pstmt.setInt(7, smid);
					pstmt.setString(8, smname);
					pstmt.setInt(9, quantity);
					pstmt.setInt(10,sid);
					
					int result = pstmt.executeUpdate();
					System.out.println("result1= "+ result);	
					
					if(result == 1) JOptionPane.showMessageDialog(null,"수정되었습니다.");  
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			
				//수정된 데이터의 원래 수량 알아오기
				String sql2 = "select quantityLeft " + 
			             "from stock "
			             + "where tid = ?; ";
				try {
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, dbtid);
					
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()){
						beforequantity = rs.getInt("quantityLeft");
					}
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
				//수량이 수정되었을때 재고의 수량을 함께수정
				//원래의 재고수량에서 수정 전의 수량을 다시 더해주고, 수정한 수량만큼을 빼줌.
				int modifyquantity = beforequantity + salequantity - quantity; 
				System.out.println(modifyquantity);
				
				String sql3 = "update stock " + 
			             "set quantityLeft = ? "
			             + "where tid = ?; ";
				try {
					pstmt = conn.prepareStatement(sql3);
					pstmt.setInt(1, modifyquantity);
					pstmt.setInt(2, tid);

					
					int result = pstmt.executeUpdate();
					System.out.println("result3= "+ result);
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			
			}

			
			else if(b.getText().equals("삭제")) {
			
				String sql = "delete from carsale "
						+ "where sid = ?";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, sid);
					
					
					int result = pstmt.executeUpdate();
					System.out.println("result= "+ result);
					
					 dateTF.setEnabled(true);
					 cnameTF.setEnabled(true);
					 dateTF.setText("");
					 cnameTF.setText("");
					 productnameTF.setText("");
					 detailTF.setText("");
					 productcodeTF.setText("");
					 compnameTF.setText("");
					 priceTF.setText("");
					 salepersonidTF.setText("");
					 salepersonnameTF.setText("");
	
					 if(result == 1) JOptionPane.showMessageDialog(null,"삭제되었습니다."); //new Result(5);
					 
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
			    
				
		}
	}
}

	
