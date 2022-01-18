package 정아현;

import 정아현.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import 정아현.*;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSpinner;
import java.awt.SystemColor;



public class SaleRegist extends JPanel {
	

	static int custid = 0, smid = 0,  pid = 0, tid = 0, saleprice = 0, inputquantity = 0;  
    static String smname = null, priceS = null,  productname = null, 
    		custname = null, compname = null, productmodel = null;
	
	static JPanel SRpanel;
	private static JTextField compnameTF;
	private static JTextField productnameTF;
	private JTextField detailTF;
	private static JTextField productmodelTF;
	private static JTextField priceTF;
	private static JTextField salepersonTF;
	private JComboBox paymentCB;
	private static JTextField cidTF;
	JSpinner dateSp;
	private JTextField dateTF;
	JPanel panel_2 = new JPanel();
	JPanel panel = new JPanel();
	private JTextField quantityTF;


	
	public static void main(String[] args) {
	    new SaleRegist();
	}

	//고객데이터를 전역변수에 저장하고, 이름을 텍스트필드에 띄움
	static public void setCustName(int searchcustid,  String searchcustname){
		custid = searchcustid;
		custname = searchcustname;
		cidTF.setText(searchcustname);
	}
	
	//영업사원데이터를 전역변수에 저장하고, 이름을 텍스트필드에 띄움	
	static public void setSalepersonName(int searchsmid,  String searchsmname){
		smid = searchsmid;
		smname = searchsmname;
		salepersonTF.setText(smname);
	}
	
	//물품데이터를 전역변수에 저장하고, 이름을 텍스트필드에 띄움		
	static public void setProduct(){
		productname = StockSearch.searchproductname;
		priceS = StockSearch.searchprice;
		pid = StockSearch.searchproductid;
		tid = StockSearch.searchtid;
		compname = StockSearch.searchcompname;
		productmodel = StockSearch.searchproductmodel;
		saleprice = IncomeRegist.salePrice;
		inputquantity = StockSearch.dbquantity;
		
		productmodelTF.setText(productmodel);
		compnameTF.setText(compname);
		productnameTF.setText(productname);
		priceTF.setText(priceS);
	}
	
	
	
	

	public SaleRegist() {
		
		setBounds(100, 100, 400, 458);
		SRpanel = new JPanel();
		SRpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		SRpanel.setLayout(null);
		
		panel.setBounds(34, 61, 302, 354);
		SRpanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 10, 73, 334);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel0 = new JLabel("\uAD6C\uB9E4\uC790");
		lblNewLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel0);
		
		JLabel lblNewLabel = new JLabel("\uC2DC\uACF5\uC77C");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uBB3C\uD488\uBA85");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("\uBB3C\uD488\uBAA8\uB378");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("\uC2DC\uACF5\uB0B4\uC6A9");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("\uBD80\uC18D\uD488");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5_1 = new JLabel("\uC218\uB7C9");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5 = new JLabel("\uD310\uB9E4\uAC00\uACA9");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\uC9C0\uBD88\uBC29\uBC95");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("\uC601\uC5C5\uC0AC\uC6D0");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_7);
		
		
		
		panel_2.setBounds(89, 10, 201, 334);
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 5));
		
		cidTF = new JTextField();
		panel_2.add(cidTF);
		cidTF.setColumns(10);
		

		SimpleDateFormat format;
		SpinnerDateModel sm = new SpinnerDateModel();
		dateSp = new JSpinner(sm);
		dateSp.setEditor(new JSpinner.DateEditor(dateSp, "yyyy-MM-dd")); 
		panel_2.add(dateSp);
	
		productnameTF = new JTextField();
		productnameTF.setColumns(10);
		panel_2.add(productnameTF);
		
		productmodelTF = new JTextField();
		productmodelTF.setColumns(10);
		panel_2.add(productmodelTF);
		
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
		
		salepersonTF = new JTextField();
		panel_2.add(salepersonTF);
		
		JButton saveB = new JButton("저장");
		saveB.setBounds(194, 436, 97, 23);
		saveB.addActionListener(new MyActionListener());
		SRpanel.add(saveB);
		
		JButton custSearchB = new JButton("\uACE0\uAC1D\uC870\uD68C");
		custSearchB.setFont(new Font("돋움", Font.PLAIN, 12));
		custSearchB.setBounds(348, 73, 87, 26);
		custSearchB.addActionListener(new MyActionListener());
		SRpanel.add(custSearchB);
		
		JButton salepersonSearchB = new JButton("\uC0AC\uC6D0\uC870\uD68C");
		salepersonSearchB.setFont(new Font("돋움", Font.PLAIN, 12));
		salepersonSearchB.setBounds(348, 374, 87, 26);
		salepersonSearchB.addActionListener(new MyActionListener());
		SRpanel.add(salepersonSearchB);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaption);
		panel_3.setBounds(12, 10, 480, 34);
		SRpanel.add(panel_3);
		
		JLabel lblNewLabel_8 = new JLabel("\uD310\uB9E4\uB4F1\uB85D");
		panel_3.add(lblNewLabel_8);
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		
		JButton incomeSearchB = new JButton("\uBB3C\uD488\uC870\uD68C");
		incomeSearchB.setFont(new Font("돋움", Font.PLAIN, 12));
		incomeSearchB.setBounds(348, 141, 87, 26);
		incomeSearchB.addActionListener(new MyActionListener());
		SRpanel.add(incomeSearchB);

		setVisible(true);
	}
	
	
	private class MyActionListener implements ActionListener{

		DB db = new DB();
		Connection conn = db.conn();
		PreparedStatement pstmt;
		public void actionPerformed(ActionEvent e) {
			
			JButton b = (JButton)e.getSource();
			JButton b2 = (JButton) e.getSource();
			String saledate = null;
			String saledetail, saleperson , payment;
			int priceInt, quantity;

			
			if(b.getText().equals("고객조회")) {
				 new CustSearchF();
			}
			
			else if(b.getText().equals("사원조회")) {
				new SalePerson();
			}
			
			else if(b.getText().equals("물품조회")){
				productname = productnameTF.getText();
//				new ImcomeSearchF(productname);
				new StockSearch(productname);
			}
			
			
			if (b.getText().equals("저장")) {
			      
				 saledetail = detailTF.getText();
				 payment = paymentCB.getSelectedItem().toString(); 
				 quantity = Integer.parseInt(quantityTF.getText()); 
				 
				 
				 String saledateS; Date saledateD; Object dateO;
				 dateO =  dateSp.getValue();	
				 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				 String date = format.format(dateO);
				 
				//판매 데이터 insert 
				String sql = "insert into carsale(saledate, productname, productModel, saledetail, "
				 		+ "compname, price, payment, saleperson, cid, tid, quantity, smID, cname, totalPrice) "
						+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				try {
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, date);
					pstmt.setString(2, productname);
					pstmt.setString(3, productmodel);
					pstmt.setString(4, saledetail);
					pstmt.setString(5, compname);
					pstmt.setInt(6,saleprice);
					pstmt.setString(7,payment);
					pstmt.setString(8,smname);
					pstmt.setInt(9,custid);
					pstmt.setInt(10,tid);
					pstmt.setInt(11,quantity);
					pstmt.setInt(12,smid);
					pstmt.setString(13,custname);
					pstmt.setInt(14, saleprice*quantity);
					
					int result = pstmt.executeUpdate();
					System.out.println("result= "+ result);
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
					
				//판매된 수량에 맞게 재고의 수량 수정하기
				String sql2 = "update stock " + 
			             "set quantityLeft = ? "
			             + "where tid = ?";
				try {
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, inputquantity - quantity);
					pstmt.setInt(2, tid);

					
					int result = pstmt.executeUpdate();
					System.out.println("result= "+ result);
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
				 cidTF.setText("");
				 productnameTF.setText("");
				 detailTF.setText("");
				 productmodelTF.setText("");
				 compnameTF.setText("");
				 priceTF.setText("");
				 salepersonTF.setText("");

				 }
		
			}
		
		
		}
}







