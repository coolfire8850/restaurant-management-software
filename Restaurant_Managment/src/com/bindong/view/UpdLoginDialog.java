package com.bindong.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.JDialog;
import com.bindong.model.*;
import com.bindong.tools.MyTools;

import java.awt.event.*;
/**
 * 
 * L'interface graphique pour modifier les informations des utilisateurs
 *
 */

public class UpdLoginDialog extends JDialog implements ActionListener{

	JPanel p1,p2,p3;
	JLabel jl1,jl2;
	JTextField jtf1,jtf2;
	JButton jb1,jb2;
	EmpLogin empLogin;
	EmpModel em;
	
	public UpdLoginDialog(EmpLogin empLogin,String title,boolean model,EmpModel em,int rowNum)
	{
		super();
		this.empLogin = empLogin;
		this.em       = em;
		p1=new JPanel(new GridLayout(2,1));
		jl1=new JLabel("empid");
		jl2=new JLabel("password");
		
		p1.add(jl1);
		p1.add(jl2);
		
		
		p2=new JPanel(new GridLayout(2,1));
		jtf1=new JTextField();
		jtf1.setText(em.getValueAt(rowNum, 0)+"");
		jtf1.setEditable(false);
		//System.out.println(em.getValueAt(rowNum, 0)+"");
		jtf2=new JTextField();
		//jtf2.setText( em.getValueAt(rowNum, 1)+"");
		
		
		
		p2.add(jtf1);
		p2.add(jtf2);
		
		
		p3=new JPanel(new FlowLayout(FlowLayout.CENTER));
		jb1=new JButton("Modify");
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

		this.setTitle("Modify user information");
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==jb1)
		{
//			String empId=jtf1.getText();
			String sql="update login set passwd=? where empid=?";
			String []params={jtf2.getText(),jtf1.getText()};
			//EmpModel em=new EmpModel();
			if(!em.UpdateModel(sql, params))
			{
				JOptionPane.showMessageDialog(null, "Sorry, please input the correct information!");
			}else{
				JOptionPane.showMessageDialog(null, "Congrtulation,update with success!");
				this.dispose();
				empLogin.refreshTable();
			}
			
			
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
		}
	}
	
	
	
	
}

