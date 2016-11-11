package com.bindong.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bindong.model.EmpModel;
import com.bindong.tools.MyTools;
/**
 * Pour modifier les informations de l'entrepot
 *
 */
public class UpdWareHouseDialog extends JDialog implements ActionListener{
	
	JPanel p1,p2,p3;
	JLabel jl1,jl2,jl3,jl4;
	JTextField jtf1,jtf2,jtf3,jtf4;
	JButton jb1,jb2;
	WareHouseInfo wareHouseInfo;
	EmpModel em;

	public UpdWareHouseDialog(WareHouseInfo wareHouseInfo, String string,
			boolean b, EmpModel em, int rowNum) {
		// TODO Auto-generated constructor stub
		super();
		this.wareHouseInfo = wareHouseInfo;
		this.em       = em;
		p1=new JPanel(new GridLayout(4,1));
		jl1=new JLabel("code");
		jl2=new JLabel("name");
		jl3=new JLabel("quantity");
		jl4=new JLabel("comment");
		p1.add(jl1);
		p1.add(jl2);
		p1.add(jl3);
		p1.add(jl4);
		
		p2=new JPanel(new GridLayout(4,1));
		jtf1=new JTextField();
		jtf1.setText(em.getValueAt(rowNum, 1)+"");
		jtf1.setEditable(false);
		//System.out.println(em.getValueAt(rowNum, 0)+"");
		jtf2=new JTextField();
		jtf2.setText( em.getValueAt(rowNum, 2)+"");
		jtf3=new JTextField();
		jtf3.setText( em.getValueAt(rowNum, 3)+"");
		jtf4=new JTextField();
		jtf4.setText( em.getValueAt(rowNum, 4)+"");
		
		p2.add(jtf1);
		p2.add(jtf2);
		p2.add(jtf3);
		p2.add(jtf4);

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
		this.setSize(400,200);

		
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==jb1)
		{
			String sql="update warehouse set material_name=?,quantity_kg=? ,comment=?  where material_code=?";
			String []params={jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf1.getText().trim()};
			//em=new EmpModel();
			em.UpdateModel(sql, params);
			if(!em.UpdateModel(sql, params))
			{
				JOptionPane.showMessageDialog(null, "Sorry,please input the correct information!");
			}else {
				JOptionPane.showMessageDialog(null, "Congratulation,Modify with success!");
				this.dispose();
				wareHouseInfo.refreshTable();
			}
			
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
		}
	}

}
