package ������;

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

import ������.MenagerRogin;


public class Result extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JLabel lblNewLabel;

	
	public static void main(String[] args) {
		new Result(3);
	}
	
	// �˸�â���� ����Ϸ� ������ ����� �ٸ� �ڵ带 ��� ��
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
				setTitle("�α��οϷ�");
			    lblNewLabel =  new JLabel("�α��� �Ϸ�Ǿ����ϴ�");
				lblNewLabel.setFont(new Font("����", Font.PLAIN, 14));
				lblNewLabel.setBounds(49, 35, 170, 32);
				contentPanel.add(lblNewLabel);
				}
			else if(result == 0) {
				setBounds(400,250, 256, 171);
				setTitle("�α��ν���");
				lblNewLabel = new JLabel("�α��� �����Ͽ����ϴ�.");
				lblNewLabel.setFont(new Font("����", Font.PLAIN, 14));
				lblNewLabel.setBounds(49, 35, 170, 32);
				contentPanel.add(lblNewLabel);	
			}
			
			else if(result == 3) {
				setBounds(400,250, 250, 171);
				setTitle("�����Ϸ�");
			    lblNewLabel =  new JLabel("�����Ǿ����ϴ�.");
				lblNewLabel.setFont(new Font("����", Font.PLAIN, 14));
				lblNewLabel.setBounds(55, 35, 155, 32);
				contentPanel.add(lblNewLabel);
			}
			else if(result == 4) {
				setBounds(400,250, 300, 171);
				setTitle("�α��ΰ��");
			    lblNewLabel =  new JLabel("�α��� �Ǿ����� �ʽ��ϴ�.");
				lblNewLabel.setFont(new Font("����", Font.PLAIN, 14));
				lblNewLabel.setBounds(49, 35, 180, 32);
				contentPanel.add(lblNewLabel);
			}
			
			else if(result == 5) {
				setBounds(400,250, 250, 171);
				setTitle("�����Ϸ�");
			    lblNewLabel =  new JLabel("�����Ǿ����ϴ�.");
				lblNewLabel.setFont(new Font("����", Font.PLAIN, 14));
				lblNewLabel.setBounds(55, 35, 155, 32);
				contentPanel.add(lblNewLabel);
			}
			
			else if(result == 6) {
				setBounds(400,250, 256, 171);
				setTitle("�α׾ƿ��Ϸ�");
				lblNewLabel = new JLabel("�α׾ƿ� �Ϸ�Ǿ����ϴ�.");
				lblNewLabel.setFont(new Font("����", Font.PLAIN, 14));
				lblNewLabel.setBounds(49, 35, 170, 32);
				contentPanel.add(lblNewLabel);	
			
			}
			
			else if(result == 7) {
				setBounds(400,250, 256, 171);
				setTitle("�˻�����");
			    lblNewLabel =  new JLabel("��ϵǾ� ���� �ʽ��ϴ�.");
				lblNewLabel.setFont(new Font("����", Font.PLAIN, 14));
				lblNewLabel.setBounds(49, 35, 300, 32);
				contentPanel.add(lblNewLabel);
				}
			
			else if(result == 8) {
				setBounds(400,250, 256, 171);
				setTitle("�˻�����");
				lblNewLabel = new JLabel("�ش� ��ǰ�� �����ϴ�.");
				lblNewLabel.setFont(new Font("����", Font.PLAIN, 14));
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

