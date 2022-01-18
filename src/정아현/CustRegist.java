package 정아현;

import javax.swing.JPanel;

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

import java.sql.*;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class CustRegist extends JPanel {

	
    static JPanel PCRpanel; 
	private JTextField cnameTF;
	private JTextField cphoneTF;
	private JTextField ctypeTF;
	private JTextField cnumberTF;
	
	public CustRegist() {
		
		PCRpanel = new JPanel();
		PCRpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		PCRpanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(179, 62, 162, 174);
		PCRpanel.add(panel);
		panel.setLayout(new GridLayout(4, 2, 0, 5));
		
		cnameTF = new JTextField();
		cnameTF.setHorizontalAlignment(SwingConstants.CENTER);
		cnameTF.setColumns(10);
		panel.add(cnameTF);
		
		cphoneTF = new JTextField();
		cphoneTF.setHorizontalAlignment(SwingConstants.CENTER);
		cphoneTF.setColumns(10);
		panel.add(cphoneTF);
		
		ctypeTF = new JTextField();
		ctypeTF.setHorizontalAlignment(SwingConstants.CENTER);
		ctypeTF.setColumns(10);
		panel.add(ctypeTF);
		
		cnumberTF = new JTextField();
		cnumberTF.setHorizontalAlignment(SwingConstants.CENTER);
		cnumberTF.setColumns(10);
		panel.add(cnumberTF);
		
		JButton saveButton = new JButton("저장");
		saveButton.setBounds(239, 249, 97, 23);
		saveButton.addActionListener(new MyActionListener());
		PCRpanel.add(saveButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(12, 10, 428, 32);
		PCRpanel.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("\uACE0\uAC1D\uB4F1\uB85D  ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		lblNewLabel.setBounds(0, 0, 428, 32);
		panel_2.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(91, 62, 76, 174);
		PCRpanel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uC804\uD654\uBC88\uD638");
		panel_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1_3 = new JLabel("\uCC28\uB7C9\uC885\uB958");
		panel_1.add(lblNewLabel_1_3);
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1_2 = new JLabel("\uCC28\uB7C9\uBC88\uD638");
		panel_1.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		setVisible(true);
	}
	
	
	private class MyActionListener implements ActionListener{

			DB db = new DB();
			Connection conn = db.conn();
			PreparedStatement pstmt;
			public void actionPerformed(ActionEvent e) {
				
				JButton b = (JButton)e.getSource();
				String cname, cphone, ctype, cnumber;
				

				if(b.getText().equals("저장")) {
					cname = cnameTF.getText();
				    cphone = cphoneTF.getText();
					ctype = ctypeTF.getText();
					cnumber = cnumberTF.getText();

					if(cname == " " || cphone == " " || ctype == " " || cnumber == " ") 
						JOptionPane.showMessageDialog(null,"정보를 모두 입력하세요.");
					else {
						String sql = "insert into carcustomer(cname, cphone, ctype, cnumber) "
								+"values(?,?,?,?);";
						try {
							
							pstmt = conn.prepareStatement(sql);
							
							pstmt.setString(1, cname);
							pstmt.setString(2, cphone);
							pstmt.setString(3, ctype);
							pstmt.setString(4, cnumber);
							
							int result = pstmt.executeUpdate();
							System.out.println("result= "+ result);
	
							if(cname == "" || cphone == "" || ctype == "" || cnumber == "") 
								JOptionPane.showMessageDialog(null,"정보를 모두 입력하세요."); 
							
	
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					
					System.out.println(cname +", " + cphone +", " + ctype +", " + cnumber);
					 cnameTF.setText("");
					 cphoneTF.setText("");
					 ctypeTF.setText("");
					 cnumberTF.setText("");
					 }
				}
			}
	}











