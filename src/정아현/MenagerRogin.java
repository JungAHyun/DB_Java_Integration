package 정아현;


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
	

	static int roginOk = 0;  //0으로바꾸기
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
		
		loginB = new JButton("로그인");
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
		lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
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
		
			if(b.getText().equals("로그인")) {
				
				DB db = new DB();
				Connection conn = db.conn();
				PreparedStatement pstmt;
				
				String mid, mpassword;                        // 적은 아이디와 비밀번호
				String realid = null, realpassword = null;    // DB에 있는 진짜 아이디와 비밀번호
				
				//적은 아이디와 비밀번호가져오기
				mid = idTF.getText();
			    mpassword = passwordTF.getText();             
			    
				
				// DB에 있는 진짜 아이디와 비밀번호 찾고, 가져오기
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
					
					
					// pstmt.execute()사용하여 로그인 확인하기
/*					while(rs.next()){
						if(mid.equals(rs.getString("managerID")) && password.equals(rs.getString("managerPW"))) {
							realid = rs.getString("managerID") ;
							realpassword = rs.getString("managerPW");
						}
					}
					
					result = pstmt.execute();		
					if(result == true ) {
						JOptionPane.showMessageDialog(null,"로그인 완료되었습니다.");
						passwordTF.setText("* * * *");
						loginB.setText("로그아웃");
						result = true;
					}			
*/
					
					//디비의 아이디, 비밀번호와 입력한 아이디, 비밀번호가 일치하면 로그인완료
					//roginOk = 1을 사용하여 다른 메뉴아이템 사용 가능하게 함.
   					if(mid.equals(realid) && mpassword.equals(realpassword)) {				
						passwordTF.setText("* * * *");
						loginB.setText("로그아웃");
						roginOk = 1; 
						}
					else {
						JOptionPane.showMessageDialog(null,"로그인 실패하였습니다.");
						passwordTF.setText("");
						roginOk = 0; ;
						}
		
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
			
			else if(b.getText().equals("로그아웃")) {
				 loginB.setText("로그인");
				 roginOk = 0;
				 idTF.setText("");
				 passwordTF.setText("");
				 JOptionPane.showMessageDialog(null,"로그아웃 완료되었습니다.");   
				 }
			}	
	}
	
}




