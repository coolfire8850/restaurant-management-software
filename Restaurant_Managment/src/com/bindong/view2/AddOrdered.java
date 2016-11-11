package com.bindong.view2;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JDialog;

import java.util.Calendar;
import java.util.Vector;

import com.bindong.model.*;
import com.bindong.tools.MyTools;

import java.awt.event.*;
/**
 * L'interface pour enregitrer les informations de reservations
 *
 */
public class AddOrdered extends JDialog implements ActionListener{

	JPanel p1,p2,p3;
	JLabel jl1,jl2,jl3,jl4;
	JTextField jtf1,jtf2,jtf3,jtf4;
	JButton jb1,jb2;
	String tableId;
	Window2 window2;
	
	public AddOrdered(Window2 window2,String tableId)
	{
		super();
		this.window2 = window2;
		this.tableId = tableId;
		p1=new JPanel(new GridLayout(4,1));
		jl1=new JLabel("deskid");
		jl2=new JLabel("customname");
		jl3=new JLabel("tel");
		jl4=new JLabel("number");
	
		p1.add(jl1);
		p1.add(jl2);
		p1.add(jl3);
		p1.add(jl4);
	
		p2=new JPanel(new GridLayout(4,1));
		jtf1=new JTextField();
		if(!isEmpty(tableId)){
			jtf1.setText(tableId);
			jtf1.setEditable(false);
		}
		
		jtf2=new JTextField();
		jtf3=new JTextField();
		jtf4=new JTextField();
		
		p2.add(jtf1);
		p2.add(jtf2);
		p2.add(jtf3);
		p2.add(jtf4);
		
		p3=new JPanel(new FlowLayout(FlowLayout.CENTER));
		jb1=new JButton("Confirm");
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
		this.setSize(400,250);

		this.setTitle("Reservation");
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==jb1)
		{
			
			OrderModel om=new OrderModel();
			
			String status=om.geStatusById(jtf1.getText());
			//System.out.print(status);
			if((0+"").equals(status.trim()))
			{
				JOptionPane.showMessageDialog(null, "Sorry,The table "+tableId+" is being using");
			}
			else if((1+"").equals(status.trim()))
			{
				JOptionPane.showMessageDialog(null, "Sorry,The table "+tableId+" was reserved");
			}
			else if((2+"").equals(status.trim()))
			{
				String sql="insert into orderdesk values(?,?,?,?,?)";
				String []params={jtf1.getText(),jtf2.getText(),jtf3.getText(),jtf4.getText(),Calendar.getInstance().getTime().toLocaleString()};
				OrderModel em=new OrderModel();
				em.UpdateModel(sql, params);
				String params2[]={jtf1.getText()};
				String sql2="update status set deskstatus=1 where deskid=?";
				em.UpdateModel(sql2, params2);
				JOptionPane.showMessageDialog(null, "Congratulation,The table "+tableId+" is reserved!");
				
			}
			window2.refreshTables();
			this.dispose();
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
		}
	}
	
	
	private boolean isEmpty(String str) {
		if (str.length() == 0 || str.isEmpty())
			return true;
		else
			return false;

	}
	
	
}

