package ������;


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

import ������.*;
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
	
	
	int rowIndex = 0;            // jtable�� �� �ε��� 0���� ������Ű�鼭 ���� �־���
	int rowcount = -1;           // db���� �����͸� �޾ƿ� ������ ���� ��, ������ �˻������ �����ϰų� ���� ��Ͽ��θ� Ȯ���ϴµ��� ���  
	JScrollPane scrollPane;	    
    Object header[] = {"��ID", "�̸�", "��ȭ��ȣ", "��������", "������ȣ"};
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
		
		JButton searchB = new JButton("��ȸ");
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
		lblNewLabel.setFont(new Font("���� ��� Semilight", Font.BOLD, 16));
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
			
			if(b.getText().equals("��ȸ")) {
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
					
					//������ �˻���� ����, ���� ���� 0���� ũ�� ���� �ϳ��� ����� �ڵ�
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
						
						//db���� ������ �����͸� jtable�� ���̱�
						Object data[] = {dbid, dbname, dbphone, dbcartype, dbcarNum};    
						model.addRow(data);
						
						System.out.println(dbid + "\t" + 
								dbname + "\t" + 
								dbphone + "\t" +
								dbcartype+ "\t" +
								dbcarNum);
					}
					rowcount = model.getRowCount();      //whlie���� ������ ���� ���� 

					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				// whlie���� ������ ���� ������ 0�̸� ��Ͼ��� �˾�â ����
				if(rowcount == 0) JOptionPane.showMessageDialog(null,"��ϵǾ� ���� �ʽ��ϴ�.");
				
				 cnameTF.setText("");
				 cphoneTF.setText("");
				 
			}
			
			if(b.getText().equals("Ȯ��")) {
				 SaleRegist.setCustName(searchid, searchname);
				 dispose();
			}
		}
	}
			
	
	
	private class MyMouseListener extends MouseAdapter {    
	
		public void mouseClicked(MouseEvent e) {
			//Ŭ���� ���� �����͸� ���������� �����ؼ� ������ �����͸� Ȯ�ι�ư���� ���
			if(e.getButton() == 1) {
				 int row = table.getSelectedRow();
				 String idstring = table.getValueAt(row, 0).toString();
				 searchid = Integer.parseInt(idstring);
				 searchname = table.getValueAt(row, 1).toString();
			}
		}
	}
}













