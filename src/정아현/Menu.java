package ������;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.hssf.record.HCenterRecord;

import ������.*;


import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Menu extends JFrame {

	 static JPanel menuPanel; 
 
	 private JTextField textField;
	 private MenagerRogin loginMM;

	 
	public static void main(String[] args) {
		new Menu();
	}


	public Menu() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		
		menuPanel = new JPanel();
		menuPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(menuPanel);
		menuPanel.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		JMenu fileMenu = new JMenu("\uD30C\uC77C");
		menuBar.add(fileMenu);
		
		JMenuItem managerModeItem = new JMenuItem("�����ڷα���");
		fileMenu.add(managerModeItem);
		managerModeItem.addActionListener(new MyActionListener());
		
		JMenuItem endItem = new JMenuItem("\uC885\uB8CC");
		fileMenu.add(endItem);
		endItem.addActionListener(new MyActionListener());
		
		JMenu custMenu = new JMenu("\uACE0\uAC1D\uAD00\uB9AC");
		menuBar.add(custMenu);
		
		JMenuItem custRegistItem = new JMenuItem("�����");
		custMenu.add(custRegistItem);
		custRegistItem.addActionListener(new MyActionListener());
		
		JMenuItem custSearchItem = new JMenuItem("����ȸ");
		custMenu.add(custSearchItem);
		custSearchItem.addActionListener(new MyActionListener());
		
		
		JMenu saleMenu = new JMenu("\uD310\uB9E4\uAD00\uB9AC");
		menuBar.add(saleMenu);
		
		JMenuItem saleRegistItem = new JMenuItem("\uD310\uB9E4\uB4F1\uB85D");
		saleMenu.add(saleRegistItem);
		saleRegistItem.addActionListener(new MyActionListener());
		
		JMenuItem saleSearchItem = new JMenuItem("\uD310\uB9E4\uC870\uD68C");
		saleMenu.add(saleSearchItem);
		saleSearchItem.addActionListener(new MyActionListener());
		
		JMenu incomeMenu = new JMenu("\uC785\uACE0\uAD00\uB9AC");
		menuBar.add(incomeMenu);
		
		JMenuItem incomeRegistItem = new JMenuItem("\uC785\uACE0\uB4F1\uB85D");
		incomeMenu.add(incomeRegistItem);
		incomeRegistItem.addActionListener(new MyActionListener());
		
		JMenuItem incomeSearchItem = new JMenuItem("\uC785\uACE0\uC870\uD68C");
		incomeMenu.add(incomeSearchItem);
		incomeSearchItem.addActionListener(new MyActionListener());
		
		JMenu productRegistMenu = new JMenu("\uC81C\uD488\uB4F1\uB85D");
		menuBar.add(productRegistMenu);
		
		JMenu in_outMenu = new JMenu("\uC218\uC785/\uC9C0\uCD9C\uAD00\uB9AC");
		menuBar.add(in_outMenu);
		
		setTitle("�����ڷα���");
		setBounds(300, 300, 480, 350);
		loginMM = new MenagerRogin();
		menuPanel.add(MenagerRogin.LOGINpanel);
		
		setVisible(true);
	}
	
		private class MyActionListener implements ActionListener{  
			DB db = new DB();
			Connection conn = db.conn();
			PreparedStatement pstmt;
	
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				switch(cmd) {
				
				case "�����ڷα���" :
					menuPanel.removeAll(); 
					setTitle("�����ڷα���");
					setBounds(300, 300, 480, 350);
					menuPanel.add(loginMM.LOGINpanel);				
					break;
					
				case "����" :
					System.exit(0);
					break;
				
				case "�����" :
					if(MenagerRogin.roginOk == 0) 
						JOptionPane.showMessageDialog(null,"�α��� �Ǿ����� �ʽ��ϴ�."); //new Result(4);
					else {
						menuPanel.removeAll();
						setTitle("�����");
						setBounds(300, 300, 480, 380);
						new CustRegist();     
						menuPanel.add(CustRegist.PCRpanel); 
					}
					break;
					
				case "����ȸ" :
					if(MenagerRogin.roginOk == 0) 
						JOptionPane.showMessageDialog(null,"�α��� �Ǿ����� �ʽ��ϴ�."); 
					else {
						menuPanel.removeAll();  
						setTitle("����ȸ");
						setBounds(300, 300, 471, 430);
						new CustSearchP();
						menuPanel.add(CustSearchP.CSpanel);  
					}
					break;
					
				case "�Ǹŵ��" :
					if(MenagerRogin.roginOk == 0)
						JOptionPane.showMessageDialog(null,"�α��� �Ǿ����� �ʽ��ϴ�."); 
					else {
						menuPanel.removeAll();
						setTitle("�Ǹŵ��");
						setBounds(300, 300, 600, 600);
						new SaleRegist();
						menuPanel.add(SaleRegist.SRpanel);
					}
					break;
					
				case "�Ǹ���ȸ" :
					if(MenagerRogin.roginOk == 0) 
						JOptionPane.showMessageDialog(null,"�α��� �Ǿ����� �ʽ��ϴ�."); 
					else {
						menuPanel.removeAll();
						setTitle("�Ǹ���ȸ");
						setBounds(300, 300, 1200, 400);
						new SaleSearch();
						menuPanel.add(SaleSearch.SSpanel);
					}
					break;
					
				case "�԰���" :
					if(MenagerRogin.roginOk == 0) 
						JOptionPane.showMessageDialog(null,"�α��� �Ǿ����� �ʽ��ϴ�."); 
					else {
						menuPanel.removeAll();
						setTitle("�԰���");
						setBounds(300, 300, 460, 650);
						new IncomeRegist();
						menuPanel.add(IncomeRegist.IRpanel);
					}
					break;
					
				case "�԰���ȸ" :
					if(MenagerRogin.roginOk == 0) 
						JOptionPane.showMessageDialog(null,"�α��� �Ǿ����� �ʽ��ϴ�."); 
					else {
						menuPanel.removeAll();
						setTitle("�԰���ȸ");
						setBounds(300, 300, 980, 400);
						new IncomeSearch();
						menuPanel.add(IncomeSearch.ISpanel);
					}
					break;
				}
				}
				
				
	
		}
}
		
	
	
	

	

