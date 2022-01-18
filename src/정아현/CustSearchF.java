package 정아현;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import 정아현.*;
import java.awt.ScrollPane;
import javax.swing.JScrollBar;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CustSearchF extends JFrame {

	
	static JPanel contentPane;
	private JTextField cnameTF;
	private JTextField cphoneTF;
	JButton okB = new JButton("\uD655\uC778");;
	JButton b = null;
	static int searchid;
	static String searchname, searchphone, cartype, carnumber;
	
	
	int rowIndex = 0;            // jtable의 행 인덱스 0부터 증가시키면서 값을 넣어줌
	int rowcount = -1;           // db에서 데이터를 받아온 다음의 행의 수, 이전의 검색결과를 제거하거나 고객의 등록여부를 확인하는데에 사용  
	JScrollPane scrollPane;	    
    Object header[] = {"고객ID", "이름", "전화번호", "차량종류", "차량번호"};
	Object data[][];
	

	
	private final DefaultTableModel model = new DefaultTableModel(data, header){
		 public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
	 
	 JTable table = new JTable(model);
	

	public static void main(String[] args) {
		new	CustSearchF();
	}


	public CustSearchF() {
		
 		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 250, 461, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(163, 74, 189, 104);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 5));
		
		cnameTF = new JTextField();
		cnameTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(cnameTF);
		cnameTF.setColumns(10);
		
		cphoneTF = new JTextField();
		cphoneTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(cphoneTF);
		cphoneTF.setColumns(10);
	
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(85, 74, 57, 104);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC804\uD654\uBC88\uD638");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);
		
		JButton searchB = new JButton("조회");
		searchB.setBounds(255, 193, 97, 23);
		searchB.addActionListener(new MyActionListener());
		contentPane.add(searchB);
			
	
		scrollPane = new JScrollPane(table);
		scrollPane.setToolTipText("");
		scrollPane.setBounds(12, 241, 421, 96);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(39);
		table.getColumnModel().getColumn(2).setPreferredWidth(116);
		table.getColumnModel().getColumn(4).setPreferredWidth(88);
	    table.setToolTipText("\uAD6C\uB9E4\uBAA9\uB85D");
		table.addMouseListener(new MyMouseListener());
		contentPane.add(scrollPane);
		
		
		okB.setBounds(322, 347, 97, 23);
		okB.addActionListener(new MyActionListener());
		contentPane.add(okB);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(12, 20, 421, 32);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("\uACE0\uAC1D\uC870\uD68C");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		lblNewLabel.setBounds(0, 0, 421, 32);
		panel_2.add(lblNewLabel);
	    
		setVisible(true);
	}
	
	private class MyActionListener implements ActionListener{  
		DB db = new DB();
		Connection conn = db.conn();
		PreparedStatement pstmt;
		
		public void actionPerformed(ActionEvent e) {
			b = (JButton)e.getSource();
			String cname, cphone;
			
			if(b.getText().equals("조회")) {
				cname = cnameTF.getText();
			    cphone = cphoneTF.getText();
			    Object dbid, dbname, dbphone, dbcartype, dbcarNum;
			    
			    String sql = "select cid, cname, cphone, ctype, cnumber "
						+ "from carcustomer "
						+ "where cname=? "
						+ "and cphone like ?;";
				
				try {
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cname);
					pstmt.setString(2,"%" + cphone);
					
					//이전의 검색결과 제거, 행의 수가 0보다 크면 행을 하나씩 지우는 코드
					if(rowcount> 0) {
					for(; rowcount > 0; rowcount--) {	
						model.removeRow(rowcount-1);}                  
					}
					
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()){
						rowIndex = 0;         
						dbid = rs.getInt("cid");						
						dbname = rs.getString("cname");					
						dbphone = rs.getString("cphone");					
						dbcartype = rs.getString("ctype");				
						dbcarNum = rs.getString("cnumber");				
						rowIndex++;
						
						//db에서 가져온 데이터를 jtable에 붙이기
						Object data[] = {dbid, dbname, dbphone, dbcartype, dbcarNum};    
						model.addRow(data);
						
						System.out.println(dbid + "\t" + 
								dbname + "\t" + 
								dbphone + "\t" +
								dbcartype+ "\t" +
								dbcarNum);
					}
					rowcount = model.getRowCount();      //whlie문이 끝난후 행의 개수 

					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				// whlie문이 끝난후 행의 개수가 0이면 들록없음 팝업창 띄우기
				if(rowcount == 0) JOptionPane.showMessageDialog(null,"등록되어 있지 않습니다.");
				
				 cnameTF.setText("");
				 cphoneTF.setText("");
				 
			}
			
			if(b.getText().equals("확인")) {
				 SaleRegist.setCustName(searchid, searchname);
				 dispose();
			}
		}
	}
			
	
	
	private class MyMouseListener extends MouseAdapter {    
	
		public void mouseClicked(MouseEvent e) {
			//클릭한 것의 데이터를 전역변수에 저장해서 저장한 데이터를 확인버튼에서 사용
			if(e.getButton() == 1) {
				 int row = table.getSelectedRow();
				 String idstring = table.getValueAt(row, 0).toString();
				 searchid = Integer.parseInt(idstring);
				 searchname = table.getValueAt(row, 1).toString();
			}
		}
	}
}













