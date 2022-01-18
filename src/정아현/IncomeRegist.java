package 정아현;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Color;

public class IncomeRegist extends JPanel {

	
	static JPanel IRpanel;
	private static JTextField productnameTF;
	private static JTextField specTF;
	private static JTextField unitTF;
	private static JTextField unitpriceTF;
	private static JTextField cmNumTF;
	private JTextField salepriceTF;
	private JTextField bigoTF;
	JSpinner dateSP ;
	private static JTextField modelTF;
	private JTextField quantityTF;
	private static JTextField cmNameTF;
	private static JTextField compnameTF;


	static String searchproduct;
	static String searchcompany;
	String searchSpec;
	String searchPrice;
	String searchModel;


	///TF에 적혀있는 데이터를 읽어와서 저장할 변수들
	 int date, unitPrice, totalPrice, quantity;
	 static int cmNo, salePrice;
	 String productName, productSpec, productUnit, cmID, cmName, bigo,  productModel, compName;

	 
	 //회사의 데이터를 가져와서 텍스트필드에 나타나게 함.
	static public void setCompanyData(String searchpname,  String searchcname, int searchcmNo){
		 IncomeRegist.productnameTF.setText(searchpname);
		 IncomeRegist.cmNameTF.setText(searchcname);
		 IncomeRegist.specTF.setText(CompanySearch.searchspec);
		 IncomeRegist.modelTF.setText(CompanySearch.searchmodel);
		 IncomeRegist.unitpriceTF.setText(CompanySearch.searchprice);
		 IncomeRegist.cmNumTF.setText(CompanySearch.searchid);
		 IncomeRegist.unitTF.setText(CompanySearch.searchunit);
		 IncomeRegist.compnameTF.setText(CompanySearch.searchcompName);
		 cmNo = searchcmNo;
	}

	public static void main(String[] args) {
		 new IncomeRegist();
	}


	public IncomeRegist() {

		setBounds(100, 100, 417, 620);
		IRpanel = new JPanel();
		IRpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		IRpanel.setLayout(null);
		
		JButton searchB = new JButton("\uAC80\uC0C9");
		searchB.setBounds(353, 71, 72, 23);
		searchB.addActionListener(new MyActionListener());
		IRpanel.add(searchB);
		
		JPanel panel = new JPanel();
		panel.setBounds(37, 64, 57, 440);
		IRpanel.add(panel);
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
		panel2.setBounds(106, 64, 235, 440);
		IRpanel.add(panel2);
		panel2.setLayout(new GridLayout(0, 1, 0, 5));
		
		productnameTF = new JTextField();
		productnameTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(productnameTF);
		productnameTF.setColumns(10);
		
		
		SimpleDateFormat format;
		SpinnerDateModel sm = new SpinnerDateModel();
		dateSP = new JSpinner(sm);
		dateSP.setEditor(new JSpinner.DateEditor(dateSP, "yyyy-MM-dd")); 
		panel2.add(dateSP);
		
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
		cmNumTF.setBounds(0, 0, 82, 35);
		panel_1.add(cmNumTF);
		cmNumTF.setHorizontalAlignment(SwingConstants.CENTER);
		cmNumTF.setColumns(10);
		
		cmNameTF = new JTextField();
		cmNameTF.setHorizontalAlignment(SwingConstants.CENTER);
		cmNameTF.setBounds(86, 0, 149, 35);
		panel_1.add(cmNameTF);
		cmNameTF.setColumns(10);
		
		compnameTF = new JTextField();
		compnameTF.setHorizontalAlignment(SwingConstants.CENTER);
		compnameTF.setColumns(10);
		panel2.add(compnameTF);
		
		bigoTF = new JTextField();
		bigoTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(bigoTF);
		bigoTF.setColumns(10);
		
		JButton okB = new JButton("\uB4F1\uB85D");
		okB.setFont(new Font("돋움", Font.PLAIN, 17));
		okB.setBounds(221, 520, 77, 39);
		okB.addActionListener(new MyActionListener());
		IRpanel.add(okB);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaption);
		panel_3.setBounds(12, 10, 426, 32);
		IRpanel.add(panel_3);
		
		JLabel lblNewLabel_8_1 = new JLabel("\uC785\uACE0\uB4F1\uB85D");
		lblNewLabel_8_1.setForeground(Color.BLACK);
		lblNewLabel_8_1.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		panel_3.add(lblNewLabel_8_1);
	
		setVisible(true);
	}
	
	private class MyActionListener implements ActionListener{  
		DB db = new DB();
		Connection conn = db.conn();
		PreparedStatement pstmt;
		private int dbpid;
		
		public void actionPerformed(ActionEvent e) {
			String pname = productnameTF.getText();
			JButton b = (JButton)e.getSource();
			if(b.getText().equals("검색")) {
				new CompanySearch(pname);
				modelTF.setEnabled(false);
				specTF.setEnabled(false);
				cmNameTF.setEnabled(false);
				cmNumTF.setEnabled(false);
				unitpriceTF.setEnabled(false);
				unitTF.setEnabled(false);
				compnameTF.setEnabled(false);
			}
			
			if(b.getText().equals("등록")) {
				
				//텍스트필드에 입력된 데이터 가져오기
				productModel = modelTF.getText();
				productName = productnameTF.getText();
				productSpec = specTF.getText();
				productUnit = unitTF.getText();
				cmName = cmNameTF.getText();
				bigo = bigoTF.getText();
				unitPrice = Integer.parseInt(unitpriceTF.getText()); 
				salePrice = Integer.parseInt(salepriceTF.getText());
				quantity = Integer.parseInt(quantityTF.getText());
				cmID =  cmNumTF.getText();
				compName = compnameTF.getText();
				totalPrice = unitPrice * quantity;
				
				Object dateo = dateSP.getValue();	
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			    String date = format.format(dateo);
				 
			    
				//입고데이터 insert
				String sql = "insert into input(dateIncome, productName, productModel, productSpec, "
				 		+ "productUnit, quantity, unitPrice, totalPrice, salePrice, cmID, cmName, bigo, compName, cmNo) " 
						+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";  
				try {
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,  date);
					pstmt.setString(2, productName);
					pstmt.setString(3, productModel);
					pstmt.setString(4, productSpec);                        
					pstmt.setString(5, productUnit);
					pstmt.setInt(6, quantity);
					pstmt.setInt(7, unitPrice);
					pstmt.setInt(8, totalPrice);
					pstmt.setInt(9, salePrice);
					pstmt.setString(10,cmID);             
					pstmt.setString(11,cmName);          
					pstmt.setString(12,bigo);
					pstmt.setString(13,compName);
					pstmt.setInt(14, cmNo);              	
		
					
					System.out.println(date +"," + productName + "," + productModel + "," 
							+ productSpec + "," + productUnit + "," + compName + "," + quantity + "," 
							+ unitPrice + "," + totalPrice + "," + salePrice + "," + cmNo + "," 
							+ cmID + "," + cmName  + "," + bigo);

					
					int result = pstmt.executeUpdate();
					System.out.println("result= "+ result);

					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				//재고에 pid를 넣기 위해서 입고등록한 것의 pid찾기
				String sql2 = "select pid "
						+ "from input "
						+ "where productName =? and productModel = ? and productSpec = ? and"
						+ " productUnit = ?and quantity = ? and unitPrice = ? and salePrice= ? and "
						+ "cmID = ? and cmName = ? and bigo = ? and totalPrice = ? and compName = ? and cmNo = ?";
						
	
				try {
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, productName);
					pstmt.setString(2, productModel);
					pstmt.setString(3, productSpec);
					pstmt.setString(4, productUnit);
					pstmt.setInt(5, quantity);
					pstmt.setInt(6, unitPrice);
					pstmt.setInt(7, salePrice);
					pstmt.setString(8, cmID);
					pstmt.setString(9, cmName);
					pstmt.setString(10, bigo);
					pstmt.setInt(11, unitPrice * quantity);
					pstmt.setString(12, compName);
					pstmt.setInt(13, cmNo);
					
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()){
						dbpid = rs.getInt("pid") ;
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

		
				
				//입고한 데이터와 수량을 재고에 insert
				String sql3 = "insert into stock(pid, productName, productModel, compName, quantityLeft, price) " 
						+"values(?,?,?,?,?,?);"; 
				try {
					
					pstmt = conn.prepareStatement(sql3);
					pstmt.setInt(1, dbpid);
					pstmt.setString(2, productName);
					pstmt.setString(3, productModel);
					pstmt.setString(4,compName);
					pstmt.setInt(5, quantity);
					pstmt.setInt(6, unitPrice);
								
					int result = pstmt.executeUpdate();
					System.out.println("result= "+ result);

					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		}

	}
}
