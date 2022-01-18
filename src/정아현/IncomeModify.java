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
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import 정아현.*;
import java.awt.SystemColor;



public class IncomeModify extends JFrame {

	private JPanel contentPane;
	private JTextField productnameTF;
	private JTextField specTF;
	private JTextField unitTF;
	private JTextField unitpriceTF;

	private JTextField cmNumTF;
	private JTextField salepriceTF;
	private JTextField bigoTF;
	private JTextField dateTF;
	private JTextField modelTF;
	private JTextField quantityTF;
	private JTextField cmNameTF;
	
	
	DB db = new DB();
	Connection conn = db.conn();
	PreparedStatement pstmt;
	int productID=0;
	private JTextField compNameTF;
	
	public static void main(String[] args) {
				
		 int pid = 2;
		 new IncomeModify(pid);
	}


	public IncomeModify(int pid) {
		productID = pid;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 250, 417, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(37, 75, 57, 502);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("\uBB3C\uD488\uBA85");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("\uC785\uACE0\uB0A0\uC9DC");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_10 = new JLabel("\uBB3C\uD488\uBAA8\uB378");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_3 = new JLabel("\uADDC\uACA9");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("\uB2E8\uC704");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_11 = new JLabel("\uC218\uB7C9");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_6 = new JLabel("\uB2E8\uAC00");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("\uD310\uB9E4\uAC00\uACA9");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("\uC5C5\uCCB4");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9_1 = new JLabel("\uBD80\uC18D\uD488");
		lblNewLabel_9_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_9_1);
		
		JLabel lblNewLabel_9 = new JLabel("\uBE44\uACE0");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_9);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(106, 75, 192, 502);
		contentPane.add(panel2);
		panel2.setLayout(new GridLayout(0, 1, 0, 5));
		
		productnameTF = new JTextField();
		productnameTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(productnameTF);
		productnameTF.setColumns(10);
		
		dateTF = new JTextField();
		dateTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(dateTF);
		dateTF.setColumns(10);
		
		modelTF = new JTextField();
		modelTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(modelTF);
		modelTF.setColumns(10);
		
		specTF = new JTextField();
		specTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(specTF);
		specTF.setColumns(10);
		
		unitTF = new JTextField();
		unitTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(unitTF);
		unitTF.setColumns(10);
		
		quantityTF = new JTextField();
		quantityTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(quantityTF);
		quantityTF.setColumns(10);
		
		unitpriceTF = new JTextField();
		unitpriceTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(unitpriceTF);
		unitpriceTF.setColumns(10);
		
		salepriceTF = new JTextField();
		salepriceTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(salepriceTF);
		salepriceTF.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel2.add(panel_1);
		panel_1.setLayout(null);
		
		cmNumTF = new JTextField();
		cmNumTF.setBounds(0, 0, 67, 41);
		panel_1.add(cmNumTF);
		cmNumTF.setHorizontalAlignment(SwingConstants.CENTER);
		cmNumTF.setColumns(10);
		
		cmNameTF = new JTextField();
		cmNameTF.setHorizontalAlignment(SwingConstants.CENTER);
		cmNameTF.setBounds(68, 0, 123, 41);
		panel_1.add(cmNameTF);
		cmNameTF.setColumns(10);
		
		compNameTF = new JTextField();
		compNameTF.setHorizontalAlignment(SwingConstants.CENTER);
		compNameTF.setColumns(10);
		panel2.add(compNameTF);
		
		bigoTF = new JTextField();
		bigoTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(bigoTF);
		bigoTF.setColumns(10);
		
		JButton deleteB = new JButton("삭제");
		deleteB.setFont(new Font("돋움", Font.PLAIN, 17));
		deleteB.setBounds(310, 161, 77, 39);
		deleteB.addActionListener(new MyActionListener());
		contentPane.add(deleteB);
		
		JButton modifyB = new JButton("수정");
		modifyB.setFont(new Font("돋움", Font.PLAIN, 17));
		modifyB.setBounds(310, 103, 77, 39);
		modifyB.addActionListener(new MyActionListener());
		contentPane.add(modifyB);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(SystemColor.activeCaption);
		panel_3_1.setBounds(12, 10, 377, 34);
		contentPane.add(panel_3_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("\uD310\uB9E4\uC218\uC815 & \uC0AD\uC81C ");
		lblNewLabel_8_1.setForeground(Color.BLACK);
		lblNewLabel_8_1.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		panel_3_1.add(lblNewLabel_8_1);
	
		setVisible(true);
		
		System.out.println("ok");

	
		// 클릭한 것에 맞는 정보 보여줌
		String sql = "select * "
				+ " from input "
				+ " where pid = ?  ";
			
		System.out.println("oksql");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
	
			ResultSet rs = pstmt.executeQuery();                 
			while(rs.next()){
				
				String dbproductName = rs.getString("productName");								
				String dbdateIncome = rs.getString("dateIncome");				
				String dbproductModel = rs.getString("productModel");				
				String dbproductSpec = rs.getString("productSpec");
				String dbproductUnit = rs.getString("productUnit");
				String dbquantity = rs.getString("quantity");
				String dbunitPrice = rs.getString("unitPrice");
				String dbtotalPrice = rs.getString("totalPrice");
				String dbsalePrice = rs.getString("salePrice");
				String dbcmNum = rs.getString("cmID");
				String dbcompany = rs.getString("cmName");
				String dbbigo = rs.getString("bigo");
				String dbcmNo = rs.getString("cmNo");
				String dbcompName = rs.getString("compName");
				
			
				System.out.println(  dbproductName + " + "+ dbproductSpec+ " + " + " + " + dbproductModel +
						 " + " +dbproductUnit+ " + " + dbcompany+ " + " +dbcmNum+ " + " + dbdateIncome);
				
				 productnameTF.setText(dbproductName);
				 dateTF.setText(dbdateIncome);
				 specTF.setText(dbproductSpec);
				 unitTF.setText(dbproductUnit);
				 modelTF.setText(dbproductModel);	
				 quantityTF.setText(dbquantity);
				 unitpriceTF.setText(dbunitPrice);
				 salepriceTF.setText(dbsalePrice);
				 cmNumTF.setText(dbcmNum);
				 cmNameTF.setText(dbcompany);
				 bigoTF.setText(dbbigo);
				 
				modelTF.setEnabled(false);
				specTF.setEnabled(false);
				cmNameTF.setEnabled(false);
				cmNumTF.setEnabled(false);
				unitpriceTF.setEnabled(false);				 
				dateTF.setEnabled(false);		
				productnameTF.setEnabled(false);	
	
			}
			System.out.println("while끝");
				
		} catch (SQLException e1) {
			e1.printStackTrace();
			
	}	
	
	}
	
	private class MyActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			
			Object date;
			int unitPrice, totalPrice, salePrice, quantity;          
			String  productName,compName, productSpec, productUnit,  cmName, bigo,  productModel;
			
			if(b.getText().equals("수정")) {
				
				 productName = productnameTF.getText();
				 date = dateTF.getText();
				 bigo = bigoTF.getText(); 
				 productSpec = specTF.getText(); 
				 productModel = modelTF.getText(); 
				 productUnit = unitTF.getText();
				 cmName = cmNameTF.getText(); 
				 unitPrice = Integer.parseInt(unitpriceTF.getText()); 
				 salePrice = Integer.parseInt(salepriceTF.getText());;
				 quantity = Integer.parseInt(quantityTF.getText()); 
				 compName = compNameTF.getText(); 
				 
				 
				String sql = "update input "
						+ "set productName =?,productModel = ?, productSpec = ?,"
						+ " productUnit = ?, quantity = ?, unitPrice = ?, salePrice= ?, "
						+ "cmID = (select cmID from company where cmName = ? and cmModelName= ?), "        //cmName이 수정되면 그거에 맞게 cmID와 cmNo도 함께수정되게 함
						+ "cmName = ?, bigo = ?, totalPrice = ?, compName = ?, "
						+ "cmNo = (select cmNo from company where cmName = ? and cmModelName= ?) "
						+ "where pid = ? ";
	
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, productName);
					pstmt.setString(2, productModel);
					pstmt.setString(3, productSpec);
					pstmt.setString(4, productUnit);
					pstmt.setInt(5, quantity);
					pstmt.setInt(6, unitPrice);
					pstmt.setInt(7, salePrice);
					pstmt.setString(8, cmName);
					pstmt.setString(9, productModel);
					pstmt.setString(10, cmName);
					pstmt.setString(11, bigo);
					pstmt.setInt(12, unitPrice * quantity);
					pstmt.setString(13, compName);
					pstmt.setString(14, cmName);
					pstmt.setString(15, productModel);
					pstmt.setInt(16, productID);
					
					int result = pstmt.executeUpdate();
					System.out.println("result= "+ result);	
					if(result == 1) JOptionPane.showMessageDialog(null,"수정되었습니다.");  
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			
			}
			
			else if(b.getText().equals("삭제")) {
			
				String sql2 = "delete from Stock "
						+ "where pid = ? ";
	
				
				String sql = "delete from input "
						+ "where pid = ? ";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, productID);
	
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, productID);
					
					int result = pstmt.executeUpdate();
					System.out.println("result= "+ result);
					
	
					 productnameTF.setText("");
					 dateTF.setText("");
					 cmNumTF.setText("");
					 specTF.setText("");
					 bigoTF.setText("");
					 modelTF.setText("");
					 unitTF.setText("");
					 cmNameTF.setText("");
					 unitpriceTF.setText("");
					 unitpriceTF.setText("");
					 quantityTF.setText("");
					 salepriceTF.setText("");
					 
					 if(result == 1) JOptionPane.showMessageDialog(null,"삭제되었습니다.");  //new Result(5);
	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
			    
				
		}
	}
}

	
