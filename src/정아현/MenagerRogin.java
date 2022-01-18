package ������;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MenagerRogin extends JPanel {

	static JPanel LOGINpanel;
	private JTextField idTF;
	private JTextField passwordTF;
	Result dialog;
	JButton loginB;
	JButton logoutB;
	

	static int roginOk = 0;  //0���ιٲٱ�
	static boolean result = false;

	public static void main(String[] args) {
		new MenagerRogin();
	}


	public MenagerRogin() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 236, 235);
		LOGINpanel = new JPanel();
		LOGINpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
		LOGINpanel.setLayout(null);
		
		loginB = new JButton("�α���");
		loginB.setBounds(217, 178, 97, 23);
		loginB.addActionListener(new MyActionListener());
		LOGINpanel.add(loginB);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(SystemColor.activeCaption);
		panel_2_1.setBounds(12, 10, 426, 32);
		LOGINpanel.add(panel_2_1);
		
		JLabel lblNewLabel = new JLabel("\uAD00\uB9AC\uC790\uB85C\uADF8\uC778");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("���� ��� Semilight", Font.BOLD, 16));
		lblNewLabel.setBounds(0, 0, 426, 32);
		panel_2_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(186, 71, 128, 91);
		LOGINpanel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 5));
		
		idTF = new JTextField();
		panel_2.add(idTF);
		idTF.setHorizontalAlignment(SwingConstants.CENTER);
		idTF.setColumns(10);
		
		passwordTF = new JTextField();
		panel_2.add(passwordTF);
		passwordTF.setHorizontalAlignment(SwingConstants.CENTER);
		passwordTF.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(109, 71, 65, 91);
		LOGINpanel.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("\uC544\uC774\uB514");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1);
		
		setVisible(true);
	}
	
	private class MyActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
		
			if(b.getText().equals("�α���")) {
				
				DB db = new DB();
				Connection conn = db.conn();
				PreparedStatement pstmt;
				
				String mid, mpassword;                        // ���� ���̵�� ��й�ȣ
				String realid = null, realpassword = null;    // DB�� �ִ� ��¥ ���̵�� ��й�ȣ
				
				//���� ���̵�� ��й�ȣ��������
				mid = idTF.getText();
			    mpassword = passwordTF.getText();             
			    
				
				// DB�� �ִ� ��¥ ���̵�� ��й�ȣ ã��, ��������
				String sql = "select managerID, managerPW "
						+ " from managerlogin "
						+ " where managerID like ? ";
					
				System.out.println("oksql");
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, mid);

					ResultSet rs = pstmt.executeQuery();
					while(rs.next()){
						realid = rs.getString("managerID") ;      
						realpassword = rs.getString("managerPW");
					}
					
					
					// pstmt.execute()����Ͽ� �α��� Ȯ���ϱ�
/*					while(rs.next()){
						if(mid.equals(rs.getString("managerID")) && password.equals(rs.getString("managerPW"))) {
							realid = rs.getString("managerID") ;
							realpassword = rs.getString("managerPW");
						}
					}
					
					result = pstmt.execute();		
					if(result == true ) {
						JOptionPane.showMessageDialog(null,"�α��� �Ϸ�Ǿ����ϴ�.");
						passwordTF.setText("* * * *");
						loginB.setText("�α׾ƿ�");
						result = true;
					}			
*/
					
					//����� ���̵�, ��й�ȣ�� �Է��� ���̵�, ��й�ȣ�� ��ġ�ϸ� �α��οϷ�
					//roginOk = 1�� ����Ͽ� �ٸ� �޴������� ��� �����ϰ� ��.
   					if(mid.equals(realid) && mpassword.equals(realpassword)) {				
						passwordTF.setText("* * * *");
						loginB.setText("�α׾ƿ�");
						roginOk = 1; 
						}
					else {
						JOptionPane.showMessageDialog(null,"�α��� �����Ͽ����ϴ�.");
						passwordTF.setText("");
						roginOk = 0; ;
						}
		
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
			
			else if(b.getText().equals("�α׾ƿ�")) {
				 loginB.setText("�α���");
				 roginOk = 0;
				 idTF.setText("");
				 passwordTF.setText("");
				 JOptionPane.showMessageDialog(null,"�α׾ƿ� �Ϸ�Ǿ����ϴ�.");   
				 }
			}	
	}
	
}




