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

public class CustModify extends JFrame {

	private JPanel contentPane;
	private JTextField cnameTF;
	private JTextField ctypeTF;
	private JTextField cnumberTF;
	private JTextField cphoneTF;
	private JButton modifyB;
	private JButton okeyB;
	String cname, cphone, ctype, cnumber, id = null;;
	
	
	
	DB db = new DB();
	Connection conn = db.conn();
	PreparedStatement pstmt;
	
	public static void main(String[] args) {
		
	}
	
	
	public CustModify(Object cid) {
		setTitle("\uACE0\uAC1D\uC870\uD68C\uACB0\uACFC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 250, 338, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uACE0\uAC1D\uC870\uD68C\uACB0\uACFC");
		lblNewLabel.setBounds(126, 10, 96, 15);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 43, 70, 163);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(4, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uCC28\uB7C9\uC885\uB958");
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uCC28\uB7C9\uC885\uB958");
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC804\uD654\uBC88\uD638");
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(99, 43, 191, 163);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(4, 0, 0, 0));
		
		cnameTF = new JTextField();
		panel_1.add(cnameTF);
		cnameTF.setColumns(10);
		
		ctypeTF = new JTextField();
		panel_1.add(ctypeTF);
		ctypeTF.setColumns(10);
		
		cnumberTF = new JTextField();
		panel_1.add(cnumberTF);
		cnumberTF.setColumns(10);
		
		cphoneTF = new JTextField();
		panel_1.add(cphoneTF);
		cphoneTF.setColumns(10);
		
		modifyB = new JButton("수정");
		modifyB.setBounds(75, 216, 97, 23);
		modifyB.addActionListener(new MyActionListener());
		contentPane.add(modifyB);
		
		okeyB = new JButton("저장");
		okeyB.setBounds(184, 216, 97, 23);
		okeyB.addActionListener(new MyActionListener());
		okeyB.setEnabled(false);
		contentPane.add(okeyB);
		
		setVisible(true);
		
		
		// 클릭한 것에 맞는 정보 보여줌
		String sql = "select cid, cname, cphone, ctype, cnumber "
					+ "from carcustomer "
					+ "where cid=? ;";
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,(int) cid);			
			
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);
			while(rs.next()){
				int id = rs.getInt("cid");
				String name = rs.getString("cname");
				
				String phone = rs.getString("cphone");
				String carType = rs.getString("ctype");
				String carNum = rs.getString("cnumber");
				System.out.println(id + "\t" + 
						name + "\t" + 
						phone + "\t" +
						carType+ "\t" +
						carNum);	
				
				cnameTF.setText(name); 
				cphoneTF.setText(phone); 
				ctypeTF.setText(carType); 
				cnumberTF.setText(carNum); 
				
				cnameTF.setEnabled(false);
				cphoneTF.setEnabled(false);
				ctypeTF.setEnabled(false);
				cnumberTF.setEnabled(false);
			}
				
		} catch (SQLException e1) {
			e1.printStackTrace();
			
	}	
}
	
	
	
	private class MyActionListener implements ActionListener{
	
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			
			if(b.getText().equals("수정")) {
				okeyB.setEnabled(true);
				modifyB.setEnabled(false);
				cnameTF.setEnabled(true);
				cphoneTF.setEnabled(true);
				ctypeTF.setEnabled(true);
				cnumberTF.setEnabled(true);
				
				cname = cnameTF.getText();
			    cphone = cphoneTF.getText();
				ctype = ctypeTF.getText();
				cnumber = cnumberTF.getText();
				
				//수정할 정보의 cid 찾는다.
				// 이름과 전화번호가 같은 한 사람이 여러 차를 가지고 있을 수 있기때문에 이름과 전화번호보다 cid를 찾아서 하는게 정확하다.
			
				String sql = "select cid "
						+ "from carcustomer "
						+ "where cname like ? "
						+ "and cphone like ? and ctype  like ? and cnumber like ?;";
				try {
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cname);
					pstmt.setString(2, cphone);
					pstmt.setString(3,ctype);
					pstmt.setString(4,cnumber);
	
					
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()){
						id = rs.getString("cid");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			
			//저장 눌러서 수정하기 
			else if(b.getText().equals("저장")) {
				okeyB.setEnabled(false);
				modifyB.setEnabled(true);
				cname = cnameTF.getText();
			    cphone = cphoneTF.getText();
				ctype = ctypeTF.getText();
				cnumber = cnumberTF.getText();
				
				
				
				String sql = "update CarCustomer " + 
				             "set cname = ?, cphone = ?"+
						     ", ctype = ?, cnumber = ? "
						     + "where cid = ? ;";
				try {
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cname);
					pstmt.setString(2, cphone);
					pstmt.setString(3,ctype);
					pstmt.setString(4,cnumber);
					pstmt.setString(5, id);
					
					int result = pstmt.executeUpdate();
					System.out.println("result= "+ result);
					
					if(result == 1) JOptionPane.showMessageDialog(null,"수정되었습니다."); 
					
					cnameTF.setEnabled(false);
					cphoneTF.setEnabled(false);
					ctypeTF.setEnabled(false);
					cnumberTF.setEnabled(false);
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			    
				
		}
	}
}
