package com.bindong.view;
/**
 * L'interface graphique pour ajouter ou modifier les couts de menu
 */
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JDialog;

import com.bindong.model.*;
import com.bindong.tools.MyTools;

import java.awt.event.*;


public class UpdCostNumDialog extends JDialog implements ActionListener{
	//definir les objets necessaires
	JPanel p1,p2,p3;
	JLabel jl1,jl2,jl3,jl4,jl5;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5;
	JButton jb1,jb2;
	CostNum costNum;
	public UpdCostNumDialog(CostNum costNum,String title,boolean model,EmpModel em,int rowNum)
	{
		super();
		this.costNum = costNum;
		p1=new JPanel(new GridLayout(5,1));
		jl1=new JLabel("foodid");
		jl2=new JLabel("foodname");
		jl3=new JLabel("materials");
		jl4=new JLabel("matscost");
		jl5=new JLabel("matsnum");
		
		p1.add(jl1);
		p1.add(jl2);
		p1.add(jl3);
		p1.add(jl4);
		p1.add(jl5);
		
		
		p2=new JPanel(new GridLayout(5,1));
		jtf1=new JTextField();
		jtf1.setText(em.getValueAt(rowNum, 0)+"");
		jtf1.setEditable(false);
		
		jtf2=new JTextField();
		jtf2.setText( em.getValueAt(rowNum, 1)+"");
		jtf2.setEditable(false);
		jtf3=new JTextField();
		jtf3.setText( em.getValueAt(rowNum, 2)+"");
		jtf4=new JTextField();
		jtf4.setText( em.getValueAt(rowNum, 3)+"");
		jtf5=new JTextField();
		jtf5.setText( em.getValueAt(rowNum, 4)+"");
		
	
		p2.add(jtf1);
		p2.add(jtf2);
		p2.add(jtf3);
		p2.add(jtf4);
		p2.add(jtf5);
		
	
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
		this.setSize(400,250);

		this.setTitle("Modify menu costs information");
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource()==jb1)
		{
			String sql="update costnum set materials=? , matscost=? , matsnum=? where foodid=?";
			String []params={jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf1.getText()};
			EmpModel em=new EmpModel();
			if(!em.UpdateModel(sql, params))
			{
				JOptionPane.showMessageDialog(null, "Sorry,please input the correct information!");
			}else {
				JOptionPane.showMessageDialog(null, "Congratulation,Modify with success!");
				this.dispose();
				costNum.refreshTable();
			}
			
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
		}
	}
	
	
	
	
}
