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
import java.awt.Window;

import javax.swing.JScrollBar;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Font;

public class CustSearchP extends JPanel {

	
	static JPanel CSpanel;
	private JTextField cnameTF;
	private JTextField cphoneTF;
	JButton okB = new JButton("\uD655\uC778");;
	JButton b = null;
	static SaleRegist sr;
	int rowIndex = 0;  // jtable�� �� �ε��� 0���� ������Ű�鼭 ���� �־���
	int rowcount = -1; // db���� �����͸� �޾ƿ� ������ ���� ��, ������ �˻������ �����ϰų� ���� ��Ͽ��θ� Ȯ���ϴµ��� ���  

	 static int searchid;
	 static String searchname;
	
	int row = -1, col;
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
		new	CustSearchP();
	}


	public CustSearchP() {
		
		setBounds(100, 100, 461, 399);
		CSpanel = new JPanel();
		CSpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		CSpanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(157, 65, 189, 104);
		CSpanel.add(panel);
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
		panel_1.setBounds(79, 65, 57, 104);
		CSpanel.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC804\uD654\uBC88\uD638");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);
		
		JButton searchB = new JButton("��ȸ");
		searchB.setBounds(261, 184, 97, 23);
		searchB.addActionListener(new MyActionListener());
		CSpanel.add(searchB);
			
	
		scrollPane = new JScrollPane(table);
		scrollPane.setToolTipText("");
		scrollPane.setBounds(12, 224, 421, 96);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(39);
		table.getColumnModel().getColumn(2).setPreferredWidth(116);
		table.getColumnModel().getColumn(4).setPreferredWidth(88);
	    table.setToolTipText("\uAD6C\uB9E4\uBAA9\uB85D");
		table.addMouseListener(new MyMouseListener());
		CSpanel.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(12, 10, 421, 32);
		CSpanel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uACE0\uAC1D\uC870\uD68C");
		lblNewLabel.setFont(new Font("���� ��� Semilight", Font.BOLD, 16));
		lblNewLabel.setBounds(0, 0, 421, 32);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
						model.removeRow(rowcount-1);}                  //rowcount�� ���� ����, �� �ε����� 0���� �����ϹǷ� rowcount���� -1�� �ε����� �����־����.
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
					rowcount = model.getRowCount();    //whlie���� ������ ���� ���� 

					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				// whlie���� ������ ���� ������ 0�̸� ��Ͼ��� �˾�â ����
				if(rowcount == 0) JOptionPane.showMessageDialog(null,"��ϵǾ� ���� �ʽ��ϴ�.");
				
				 cnameTF.setText("");
				 cphoneTF.setText("");
				 
			}
			
		}
	}
			
	
	
	private class MyMouseListener extends MouseAdapter {    
	
		public void mouseClicked(MouseEvent e) {	
			if (e.getClickCount() == 2) {                   
				     int row = table.getSelectedRow();
				     Object idValue = table.getValueAt(row, 0);
				     new CustModify(idValue); 
			    
			     }	
		}
	}
}













