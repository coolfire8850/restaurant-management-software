package com.bindong.view;
/**
 * Pour ajouter les musiques
 */
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JDialog;

import com.bindong.model.*;
import com.bindong.tools.MyTools;

import java.awt.event.*;

public class AddSoundDialog extends JDialog implements ActionListener{

	JPanel p1,p2,p3;
	JLabel jl1,jl2,jl3;
	JTextField jtf1,jtf2,jtf3;
	JButton jb1,jb2;
	
	public AddSoundDialog(OperatChoose empInfo,String title,boolean model)
	{
		super();
		p1=new JPanel(new GridLayout(3,1));
		jl1=new JLabel("soundid");
		jl2=new JLabel("soundpath");
		jl3=new JLabel("description");
		
		p1.add(jl1);
		p1.add(jl2);
		p1.add(jl3);
		
	
		p2=new JPanel(new GridLayout(3,1));
		jtf1=new JTextField();
		jtf2=new JTextField();
		jtf3=new JTextField();
	

		p2.add(jtf1);
		p2.add(jtf2);
		p2.add(jtf3);
	
		
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
		

		this.setSize(400,200);

		this.setTitle("Add music");
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==jb1)
		{
			String sql="insert into sound values(?,?,?)";
			String []params={jtf1.getText(),jtf2.getText(),jtf3.getText()};
			EmpModel em=new EmpModel();
			if(!em.UpdateModel(sql, params))
			{
				JOptionPane.showMessageDialog(null, "Sorry,please input the correct informations");
			}
			JOptionPane.showMessageDialog(null, "Congratulation, add with success!");
			this.dispose();
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
		}
	}
	
	
	
	
}

