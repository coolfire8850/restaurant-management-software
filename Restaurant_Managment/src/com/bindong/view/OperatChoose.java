package com.bindong.view;

import com.bindong.model.*;
import com.bindong.tools.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;
public class OperatChoose extends JPanel implements ActionListener,MouseListener{
	//definir les objets necessaires
	JPanel p1,p2,p3,p4,p5;
	JLabel p1_l1,p3_l1;
	JTextField p1_jtf;
	JButton p1_jb,p4_jb1,p4_jb2,p4_jb3,p4_jb4;
	JTable jtable;
	JScrollPane jsp;
	EmpModel em=null;
	AePlayWave apw=null;
	public String soundpath=null;
	public OperatChoose()
	{
		
		
		//panel au nord
		p1=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1_l1=new JLabel("Enter the information for searching:");
		p1_l1.setFont(MyTools.f1);
		p1_jtf=new JTextField(20);
		p1_jb=new JButton("Search");
		p1_jb.addActionListener(this);
		p1_jb.setFont(MyTools.f4);
		p1.add(p1_l1);
		p1.add(p1_jtf);
		p1.add(p1_jb);
		
		
		//panel au milieu
		
		p2=new JPanel(new BorderLayout());
		String []params={"1"};
		String sql="select *from sound where 1=?";
		em=new EmpModel();
		em.query(sql, params);
		jtable= new JTable(em);
		jtable.setModel(em);
		jsp=new JScrollPane(jtable);

		p2.add(jsp);
		
		//panel au sud
		p5=new JPanel(new BorderLayout());
		p3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		sql="select count(*) from sound";
		em=new EmpModel();
		int sum=em.getNum(sql);
		

		p3_l1=new JLabel("The total number of records:"+sum);
		
		
		p3.add(p3_l1);
		p4=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4_jb1=new JButton("Details");
		p4_jb1.addActionListener(this);
		p4_jb1.setFont(MyTools.f4);
		p4_jb2=new JButton("Add");
		p4_jb2.addActionListener(this);
		p4_jb2.setFont(MyTools.f4);
		p4_jb3=new JButton("Modify");
		p4_jb3.addActionListener(this);
		p4_jb3.setFont(MyTools.f4);
		p4_jb4=new JButton("Delete");
		p4_jb4.addActionListener(this);
		p4_jb4.setFont(MyTools.f4);
		p4.add(p4_jb1);
		p4.add(p4_jb2);
		p4.add(p4_jb3);
		p4.add(p4_jb4);
		p5.add(p3,"West");
		p5.add(p4,"East");
		
		this.setLayout(new BorderLayout());
		this.add(p1,"North");
		this.add(p2,"Center");
		this.add(p5,"South");
		this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		
		if(arg0.getSource().equals(p1_jb))
		{
			
			if(p1_jtf.getText().trim().equals(""))
			{
				String[]params={"1"};
				String sql="select *from sound where 1=?";
				em=new EmpModel();
				em.query(sql, params);
				jtable.setModel(em);
			}
			else{
			String params[]={p1_jtf.getText().trim()};
			String sql="select *from sound where soundid=?";
			em=new EmpModel();
			em.query(sql, params);
			
			jtable.setModel(em);
			}
		}
			else if(arg0.getSource()==p4_jb1){
			int rowNum=this.jtable.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "Please choose one row!");
			}
			else{	
			
			
			JOptionPane.showMessageDialog(null, "Congratulation!Update with success!");
			this.setSoundpath((String)this.jtable.getValueAt(rowNum, 1));
			
			
			AePlayWave apw= new AePlayWave(soundpath);
			 apw.start();
			 
			}
			
			}
		else if(arg0.getSource().equals(p4_jb2))
		{
			
			new AddSoundDialog(this,"Add",true);
			
			
			String sql="select *from sound where 1=?";
			String[]params={"1"};
			em=new EmpModel();
			em.query(sql, params);
			jtable.setModel(em);
			
		}
		else if(arg0.getSource().equals(p4_jb3))
		{
			
			
			int rowNum=this.jtable.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "Please choose one row!");
			}
				
//			String empId=(String)this.jtable.getValueAt(rowNum, 0);
//			String []params={empId};
			else
			{
			new UpdSoundDialog(null,"Modify",true,em,rowNum);
			}
			
			
			String sql="select *from sound where 1=?";
			String[]params={"1"};
			em=new EmpModel();
			em.query(sql, params);
			jtable.setModel(em);
		}
		else if(arg0.getSource().equals(p4_jb4))
		{
			int rowNum=this.jtable.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "Please choose one row!");
			}
			else{	
			String empId=(String)this.jtable.getValueAt(rowNum, 0);
			String sql="delete from sound where soundid=?";
			String []params={empId};
			JOptionPane.showMessageDialog(null, "Congratulation!Delete with success!");
			em=new EmpModel();
			em.UpdateModel(sql, params);
			}
			String[]params={"1"};
			String sql="select *from sound where 1=?";
			em=new EmpModel();
			em.query(sql, params);
			jtable.setModel(em);
		}
	}
	
	public String getSoundpath() {
		return soundpath;
		
	}
	
	public void setSoundpath(String soundpath) {
		this.soundpath = soundpath;
	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
		
	}
	
	public void mouseEntered(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	public void mousePressed(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
}

