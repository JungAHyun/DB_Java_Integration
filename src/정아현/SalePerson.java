package 정아현;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class SalePerson extends JFrame {

	private JPanel contentPane;
	private JTextField smidTF;
	private JTextField smpositionTF;
	private JTextField smnameTF;
	private JTextField smphoneTF;
	static int searchid = 0; static String searchname = null;
	String smid = null, smname = null, smphone, smposition;
	int id = 0;
	
	static SaleRegist sr;
	
	public static void main(String[] args) {
		 new SalePerson();

	}


	
	public SalePerson() {
		setTitle("\uC601\uC5C5\uC0AC\uC6D0\uC870\uD68C");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 250, 289, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		smidTF = new JTextField();
		smidTF.setBounds(82, 72, 91, 29);
		contentPane.add(smidTF);
		smidTF.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\uC0AC\uC6D0ID");
		lblNewLabel_1.setBounds(35, 79, 52, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton searchB = new JButton("\uAC80\uC0C9");
		searchB.setBounds(186, 75, 65, 23);
		searchB.addActionListener(new MyActionListener());
		contentPane.add(searchB);
		
		JPanel imformationP = new JPanel();
		imformationP.setBounds(12, 117, 244, 169);
		contentPane.add(imformationP);
		imformationP.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 65, 160);
		imformationP.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("\uC774\uB984");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("\uC804\uD654\uBC88\uD638");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("\uC9C1\uCC45");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(70, 0, 174, 160);
		imformationP.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 7));
		
		smnameTF = new JTextField();
		smnameTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(smnameTF);
		smnameTF.setColumns(10);
		
		smphoneTF = new JTextField();
		smphoneTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(smphoneTF);
		smphoneTF.setColumns(10);
		
		smpositionTF = new JTextField();
		smpositionTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(smpositionTF);
		smpositionTF.setColumns(10);
		
		JButton okB = new JButton("\uC120\uD0DD");
		okB.setBounds(186, 297, 65, 23);
		okB.addActionListener(new MyActionListener());
		contentPane.add(okB);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(SystemColor.activeCaption);
		panel_2_1.setBounds(12, 10, 244, 32);
		contentPane.add(panel_2_1);
		
		JLabel lblNewLabel = new JLabel("\uC601\uC5C5\uC0AC\uC6D0");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		lblNewLabel.setBounds(0, 0, 244, 32);
		panel_2_1.add(lblNewLabel);
		
		setVisible(true);
	}
	
	private class MyActionListener implements ActionListener{  
		DB db = new DB();
		Connection conn = db.conn();
		PreparedStatement pstmt;
		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();

			if(b.getText().equals("검색")) {
				int count=0;
				smid = smidTF.getText();
				smnameTF.setText(""); 
				smphoneTF.setText(""); 
				smpositionTF.setText("");
				
				
				String sql = "select smid, smname, smphone, smposition "
						+ "from salespersons "
						+ "where smid=?; ";
						
				try {
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, smid);
					
				 
					ResultSet rs = pstmt.executeQuery();
					
					while(rs.next()){
						
						id = rs.getInt("smid");						
						smname = rs.getString("smname");					
						smphone = rs.getString("smphone");					
						smposition = rs.getString("smposition");				
					
						
						System.out.println(smid + "\t" + 
								smname + "\t" + 
								smphone + "\t" +
								smposition + "\t"  );	
						
						smnameTF.setText(smname); 
						smphoneTF.setText(smphone); 
						smpositionTF.setText(smposition); 
						count = 1;
						
					}
					rs.close();
						
					if(count == 0) JOptionPane.showMessageDialog(null,"등록되어 있지 않습니다."); 
						//new Result(count);
			
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				

			}
			
			if(b.getText().equals("선택")) {
				System.out.println(id + smname);
				sr.setSalepersonName(id, smname);
				dispose();
				 
			}
			

		}
}
	}
