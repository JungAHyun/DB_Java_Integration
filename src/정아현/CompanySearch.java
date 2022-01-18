package ������;

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

public class CompanySearch extends JFrame {

	private JPanel contentPane;
	
	//jtable���� ������ �����ͼ� �԰��Ͽ� �Ѱ��ٶ� ����ϴ� ����
	 String searchname, searchphone,  searchproduct ;
	 static String searchid, searchmodel,searchspec, searchprice, searchunit, searchcompName;
	 static int searchcmNO;
	
	//db���� ������ ������ ����
    Object dbcmNO, dbcmID, dbcmName, dbcmPhone, dbcmProductName, dbcmModelName, 
           dbproductSpec, dbunitPrice, dbproductUnit, dbcompName;  
	
    
    int row = -1;
	JScrollPane scrollPane;	    
    Object header[] = {"��üNO", "��ü��", "��ȭ��ȣ", "��ǰ��", "��ǰ��", "�԰�", "����","�ܰ�","�μ�ǰ"};
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
					CompanySearch frame = new CompanySearch("�׺�");
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
	public CompanySearch(String productname) {
		setTitle("\uC5C5\uCCB4\uC815\uBCF4");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 741, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 67, 701, 156);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(116);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.addMouseListener(new MyMouseListener());
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		
		okB = new JButton("\uC120\uD0DD");
		okB.setBounds(602, 238, 97, 23);
		okB.addActionListener(new MyActionListener());
		contentPane.add(okB);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(12, 15, 701, 32);
		contentPane.add(panel);
		
		lblNewLabel_8 = new JLabel("\uC5C5\uCCB4\uC815\uBCF4");
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setFont(new Font("���� ��� Semilight", Font.BOLD, 16));
		panel.add(lblNewLabel_8);
		
		setVisible(true);
	
		
	    int count =0;

	    String sql = "select * "
		+"from company "
		+"where cmProductName like '%' ? '%' ;" ;

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
			//����ִµ����� �����ͼ� jtable�� �ֱ� 
			dbcmNO = rs.getInt("cmNO");	;
			dbcmID = rs.getString("cmID");						
			dbcmName = rs.getString("cmName");					
			dbcmPhone = rs.getString("cmPhone");		
			dbcmProductName = rs.getString("cmProductName");				
			dbcmModelName = rs.getString("cmModelName");
			dbproductSpec = rs.getString("productSpec");
			dbunitPrice = rs.getInt("unitPrice");
			dbproductUnit = rs.getString("productUnit");
			dbcompName = rs.getString("compName");
			
			
			row++;
			
			Object data[] = {dbcmID, dbcmName, dbcmPhone, dbcmProductName, dbcmModelName, dbproductSpec,dbproductUnit, dbunitPrice,dbcompName};
			model.addRow(data);
			
			System.out.println(dbcmID + "\t" + 
					dbcmName + "\t" + 
					dbcmPhone + "\t" +
					dbcmProductName+ "\t" +  dbproductSpec + "\t" + 
					dbcmModelName);
			count = 1;
		}
		
	} catch (SQLException e1) {
		e1.printStackTrace();
		}
	
	if(count == 0) { 
//		new Result(8); 
		JOptionPane.showMessageDialog(null,"�ش� ��ǰ�� �����ϴ�."); 
		dispose();}
	
	}
	
	
	private class MyMouseListener extends MouseAdapter {    
		
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 1) {
				//jtable�� �ִ� �������� ������ ���� ������ ��������    
				int row = table.getSelectedRow();
				 searchid =  table.getValueAt(row, 0).toString();
				 searchname = table.getValueAt(row, 1).toString();
				 searchphone = table.getValueAt(row, 2).toString();
				 searchproduct = table.getValueAt(row, 3).toString(); 
				 searchmodel = table.getValueAt(row, 4).toString();
				 searchspec = table.getValueAt(row, 5).toString();
				 searchunit = table.getValueAt(row, 6).toString();
				 searchprice = table.getValueAt(row, 7).toString();
				 searchcompName = table.getValueAt(row, 8).toString();
			
				 //jtable�� �ȳ�Ÿ���� cmNo�� cmID�� �°� ã�ƿ�
				 String sql = "select cmNo "
				    		+"from company "
				    		+"where cmID = ? and cmModelName = ?;" ;
				 try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,searchid);
					pstmt.setString(2,searchmodel);	
					ResultSet rs = pstmt.executeQuery();
					
					 while(rs.next()){
						 searchcmNO = rs.getInt("cmNo"); 
					 }
					
					}catch (SQLException e1) {
						e1.printStackTrace();
				}

			}	
		}
	}
	
	
	private class MyActionListener implements ActionListener{  
	
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			if(b.getText().equals("����")) { 
				System.out.println(searchcmNO + ", " +searchid);
				IncomeRegist.setCompanyData(searchproduct, searchname, searchcmNO);
				dispose();
			}
			
			
		}
		
	}
}