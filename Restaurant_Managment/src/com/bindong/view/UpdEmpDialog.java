package com.bindong.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.JDialog;

import sun.security.jca.GetInstance;

import com.bindong.model.*;
import com.bindong.tools.MyTools;

import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * L'interface graphique pour modifier les information d'emploi
 *
 */
public class UpdEmpDialog extends JDialog implements ActionListener{

	JPanel p1,p2,p3;
	JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10,jl11;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8,jtf9,jtf10,jtf11;
	JButton jb1,jb2;
	int colNums;
	EmpInfo empInfo;
	EmpModel em = null;
	RowSorter<TableModel> sorter;
	
	public UpdEmpDialog(EmpInfo empInfo,String title,boolean model,EmpModel em,int rowNum)
	{
		super();
		this.empInfo = empInfo;
		this.em      = em;
		colNums = em.getColumnCount();
		//pour les nombres de coloumn differents
		if(colNums == 6){
			init6Cols(em, rowNum);
		}else {
			init11Cols(em, rowNum);
		}
		System.out.println(em.getColumnCount());
		
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
		
		this.setTitle(title);
		this.setSize(400,250);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width / 2 - 200, height / 2 - 200);

		//this.setTitle("修改信息");
		this.setVisible(true);
	}
	
	private void init6Cols(EmpModel em,int rowNum){
		p1=new JPanel(new GridLayout(6,1));
		jl1=new JLabel("empid");
		jl2=new JLabel("empname");
		jl3=new JLabel("sex");
		jl4=new JLabel("jobtitle");
		jl5=new JLabel("address");
		jl6=new JLabel("edu");
		
		p1.add(jl1);
		p1.add(jl2);
		p1.add(jl3);
		p1.add(jl4);
		p1.add(jl5);
		p1.add(jl6);
		p2=new JPanel(new GridLayout(6,1));
		jtf1=new JTextField();
		jtf1.setText(em.getValueAt(rowNum, 0)+"");
		jtf1.setEditable(false);
		
		jtf2=new JTextField();
		//recuperer les donnees dans le coloumn
		jtf2.setText( em.getValueAt(rowNum, 1)+"");
		jtf3=new JTextField();
		jtf3.setText( em.getValueAt(rowNum, 2)+"");
		jtf4=new JTextField();
		jtf4.setText( em.getValueAt(rowNum, 3)+"");
		jtf5=new JTextField();
		jtf5.setText( em.getValueAt(rowNum, 4)+"");
		jtf6=new JTextField();
		jtf6.setText(em.getValueAt(rowNum, 5)+"");
		p2.add(jtf1);
		p2.add(jtf2);
		p2.add(jtf3);
		p2.add(jtf4);
		p2.add(jtf5);
		p2.add(jtf6);
		
	}
	
	private void init11Cols(EmpModel em,int rowNum){
		p1=new JPanel(new GridLayout(10,1));
		jl1=new JLabel("empid");
		jl2=new JLabel("empname");
		jl3=new JLabel("sex");
		jl4=new JLabel("address");
		jl5=new JLabel("nas");
		jl6=new JLabel("edu");
		jl7=new JLabel("jobtitle");
		jl8=new JLabel("tel1");
		jl9=new JLabel("tel2");
		jl10=new JLabel("mail");
		
		p1.add(jl1);
		p1.add(jl2);
		p1.add(jl3);
		p1.add(jl4);
		p1.add(jl5);
		p1.add(jl6);
		p1.add(jl7);
		p1.add(jl8);
		p1.add(jl9);
		p1.add(jl10);
		
		p2=new JPanel(new GridLayout(10,1));
		jtf1=new JTextField();
		jtf1.setText(em.getValueAt(rowNum, 0)+"");
		jtf1.setEditable(false);
		
		jtf2=new JTextField();
		jtf2.setText( em.getValueAt(rowNum, 1)+"");
		jtf3=new JTextField();
		jtf3.setText( em.getValueAt(rowNum, 2)+"");
		jtf4=new JTextField();
		jtf4.setText( em.getValueAt(rowNum, 3)+"");
		jtf5=new JTextField();
		jtf5.setText( em.getValueAt(rowNum, 4)+"");
		jtf6=new JTextField();
		jtf6.setText(em.getValueAt(rowNum, 5)+"");
		jtf7=new JTextField();
		jtf7.setText(em.getValueAt(rowNum, 6)+"");
		jtf8=new JTextField();
		jtf8.setText(em.getValueAt(rowNum, 7)+"");
		jtf9=new JTextField();
		jtf9.setText(em.getValueAt(rowNum, 8)+"");
		jtf10=new JTextField();
		jtf10.setText(em.getValueAt(rowNum, 9)+"");
		
		p2.add(jtf1);
		p2.add(jtf2);
		p2.add(jtf3);
		p2.add(jtf4);
		p2.add(jtf5);
		p2.add(jtf6);
		p2.add(jtf7);
		p2.add(jtf8);
		p2.add(jtf9);
		p2.add(jtf10);
		
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==jb1)
		{
			SimpleDateFormat sdFormat = new SimpleDateFormat("dd-MM-YY HH:mm:ss");
			if(colNums == 6){
				
				String sql="update rszl set empname=?,sex=? ,jobtitle=? , address=? , edu=? ,modifytime=? where empid=?";
				String []params={jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText(),sdFormat.format(Calendar.getInstance().getTime()),jtf1.getText()};
				EmpModel em=new EmpModel();
				if(!em.UpdateModel(sql, params))
				{
					JOptionPane.showMessageDialog(null, "Sorry,please input the correct information!");
				}else {
					JOptionPane.showMessageDialog(null, "Congratulation, modify with success!");
					this.dispose();
					/*String sql2 = "select empid,empname,sex,jobtitle,address,edu from rszl where 1=?";
					String[] params2 = { "1" };
					
					em.query(sql2, params2);
					empInfo.jtable.setModel(em);
					empInfo.sorter =  new TableRowSorter<TableModel>(em);
					empInfo.jtable.setRowSorter(sorter);*/
					empInfo.refreshTable();
				}
				
			}else{
				String sql="update rszl set empname=?,sex=? ,address=?,nas=?,edu=?,jobtitle=? ,tel1=? ,tel2=?,mail=?, modifytime=? where empid=?";
				String []params={jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText(),jtf7.getText(),jtf8.getText(),jtf9.getText(),jtf10.getText(),sdFormat.format(Calendar.getInstance().getTime()),jtf1.getText()};
				EmpModel em=new EmpModel();
				if(!em.UpdateModel(sql, params))
				{
					JOptionPane.showMessageDialog(null, "Sorry,please input the correct information!");
				}else {
					JOptionPane.showMessageDialog(null, "Congratulation, modify with success!");
					this.dispose();
					/*String sql2 = "select empid,empname,sex,jobtitle,address,edu from rszl where 1=?";
					String[] params2 = { "1" };
					
					em.query(sql2, params2);
					empInfo.jtable.setModel(em);
					empInfo.sorter =  new TableRowSorter<TableModel>(em);
					empInfo.jtable.setRowSorter(sorter);*/
					empInfo.refreshTable();
				}
				
			}
			
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
		}
	}
	
	
	
	
}
