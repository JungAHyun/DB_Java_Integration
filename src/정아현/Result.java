package 정아현;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import 정아현.MenagerRogin;


public class Result extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JLabel lblNewLabel;

	
	public static void main(String[] args) {
		new Result(3);
	}
	
	// 알림창으로 사용하려 했으나 현재는 다른 코드를 사용 중
	public Result(int result) {
		
		try {
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(result);
		
		setBounds(400,250, 256, 171);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		{
			if(result == 1) {
				setBounds(400,250, 256, 171);
				setTitle("로그인완료");
			    lblNewLabel =  new JLabel("로그인 완료되었습니다");
				lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
				lblNewLabel.setBounds(49, 35, 170, 32);
				contentPanel.add(lblNewLabel);
				}
			else if(result == 0) {
				setBounds(400,250, 256, 171);
				setTitle("로그인실패");
				lblNewLabel = new JLabel("로그인 실패하였습니다.");
				lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
				lblNewLabel.setBounds(49, 35, 170, 32);
				contentPanel.add(lblNewLabel);	
			}
			
			else if(result == 3) {
				setBounds(400,250, 250, 171);
				setTitle("수정완료");
			    lblNewLabel =  new JLabel("수정되었습니다.");
				lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
				lblNewLabel.setBounds(55, 35, 155, 32);
				contentPanel.add(lblNewLabel);
			}
			else if(result == 4) {
				setBounds(400,250, 300, 171);
				setTitle("로그인경고");
			    lblNewLabel =  new JLabel("로그인 되어있지 않습니다.");
				lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
				lblNewLabel.setBounds(49, 35, 180, 32);
				contentPanel.add(lblNewLabel);
			}
			
			else if(result == 5) {
				setBounds(400,250, 250, 171);
				setTitle("삭제완료");
			    lblNewLabel =  new JLabel("삭제되었습니다.");
				lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
				lblNewLabel.setBounds(55, 35, 155, 32);
				contentPanel.add(lblNewLabel);
			}
			
			else if(result == 6) {
				setBounds(400,250, 256, 171);
				setTitle("로그아웃완료");
				lblNewLabel = new JLabel("로그아웃 완료되었습니다.");
				lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
				lblNewLabel.setBounds(49, 35, 170, 32);
				contentPanel.add(lblNewLabel);	
			
			}
			
			else if(result == 7) {
				setBounds(400,250, 256, 171);
				setTitle("검색실패");
			    lblNewLabel =  new JLabel("등록되어 있지 않습니다.");
				lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
				lblNewLabel.setBounds(49, 35, 300, 32);
				contentPanel.add(lblNewLabel);
				}
			
			else if(result == 8) {
				setBounds(400,250, 256, 171);
				setTitle("검색실패");
				lblNewLabel = new JLabel("해당 물품이 없습니다.");
				lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
				lblNewLabel.setBounds(49, 35, 170, 32);
				contentPanel.add(lblNewLabel);	
			
			}
			
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new MyActionListener());
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	private class MyActionListener implements ActionListener{  
		DB db = new DB();
		Connection conn = db.conn();
		PreparedStatement pstmt;
		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			
			
			if(b.getText().equals("OK")) {
				dispose();
			}
			
		}
		}
	
}

