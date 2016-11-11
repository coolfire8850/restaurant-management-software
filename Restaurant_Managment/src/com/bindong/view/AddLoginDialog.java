package com.bindong.view;
/**
 * Pour ajouter les informations des utilisateurs
 */
import java.awt.*;

import javax.swing.*;

import com.bindong.model.*;
import com.bindong.tools.MyTools;

import java.awt.event.*;

public class AddLoginDialog extends JDialog implements ActionListener,KeyListener{

	JPanel p1,p2,p3;
	JLabel jl1,jl2;
	JTextField jtf1,jtf2;
	JButton jb1,jb2;
	String soundpath=null;
	EmpLogin empLogin;
	
	
	public AddLoginDialog(EmpLogin empLogin,String title,boolean model)
	{
		//super(empInfo,title,model);
		this.empLogin = empLogin;
		p1=new JPanel(new GridLayout(2,1));
		jl1=new JLabel("empid");
		jl2=new JLabel("passwd");
		
		p1.add(jl1);
		p1.add(jl2);
		p2=new JPanel(new GridLayout(2,1));
		jtf1=new JTextField();
		jtf1.addKeyListener(this);
		jtf2=new JTextField();
		
		p2.add(jtf1);
		p2.add(jtf2);
		
		p3=new JPanel(new FlowLayout(FlowLayout.CENTER));
		jb1=new JButton("Add");
		jb1.setFont(MyTools.f4);
		jb1.addActionListener(this);
		jb2=new JButton("Cancel");
		jb2.setFont(MyTools.f4);
		jb2.addActionListener(this);
		p3.add(jb1);
		p3.add(jb2);
		this.setLayout(new BorderLayout());
		this.add(p1,"West");
		this.add(p2,"Center");
		this.add(p3,"South");
		
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width / 2 - 200, height / 2 - 200);

		this.setSize(400,150);

		this.setTitle("Add User");
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
	
		if(arg0.getSource()==jb1)
		{
			EmpModel em=new EmpModel();
			String[] params={jtf1.getText()};
			String sql = "select * from rszl where empid = ?";
			if(!em.checkIdExistence(sql, params))
				JOptionPane.showMessageDialog(null, "Sorry,you should input the id which existe in the employer table!");
			else {
				sql = "select * from login where empid = ?";
				if(em.checkIdExistence(sql, params))
					JOptionPane.showMessageDialog(null, "Sorry,it exsite this user already!");
				else {
					sql ="insert into login values(?,?)";
					String[] params2={jtf1.getText(),jtf2.getText()};
					
					if(!em.UpdateModel(sql, params2))
					{
						JOptionPane.showMessageDialog(null, "Failure,It doesn't exist the user information in the database!");
					}else{
						
						JOptionPane.showMessageDialog(null, "Congratulation!Add with success");
						this.dispose();
						empLogin.refreshTable();
						
					}
				}
			}
		    
			
			
			
			/*soundpath=new OperatChoose().getSoundpath();
			System.out.println(soundpath);
			
			AePlayWave apw= new AePlayWave(soundpath);
			 apw.start();*/
			
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		keyInput(e);
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keyInput(e);
	}
	
	private void keyInput(KeyEvent e) {
		
			if (e.getSource() == jtf1) {
				if (!isNumeric(jtf1.getText())) {
					JOptionPane.showMessageDialog(null,
							"Please input the integer");
					jtf1.setText("");
				}
			}
		
	}

	private boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isEmpty(String str){
		if(str.length()==0||str.isEmpty())
			return true;
		else 
			return false;
		
	}
	
	
	
	
}
