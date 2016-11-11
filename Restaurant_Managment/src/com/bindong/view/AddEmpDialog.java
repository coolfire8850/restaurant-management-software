package com.bindong.view;

/**
 * pour ajouter les informations des emplois
 */
import java.awt.*;

import javax.swing.*;

import com.bindong.model.*;
import com.bindong.tools.MyTools;

import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddEmpDialog extends JDialog implements ActionListener,
		KeyListener {
	// definir les objets necessaires
	JPanel p1, p2, p3;
	JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9, jl10;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf9, jtf10;
	JButton jb1, jb2;
	EmpInfo empInfo;

	public AddEmpDialog(EmpInfo empInfo, String title, boolean model) {
		// super(empInfo,title,model);
		this.empInfo = empInfo;
		p1 = new JPanel(new GridLayout(10, 1));
		jl1 = new JLabel("empid");
		jl2 = new JLabel("empname");
		jl3 = new JLabel("sex");
		jl4 = new JLabel("address");
		jl5 = new JLabel("nas");
		jl6 = new JLabel("edu");
		jl7 = new JLabel("jobtitle");
		jl8 = new JLabel("tel1");
		jl9 = new JLabel("tel2");
		jl10 = new JLabel("mail");
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
		p2 = new JPanel(new GridLayout(10, 1));
		jtf1 = new JTextField();
		jtf1.addKeyListener(this);
		jtf2 = new JTextField();
		jtf3 = new JTextField();
		jtf4 = new JTextField();
		jtf5 = new JTextField();
		jtf6 = new JTextField();
		jtf7 = new JTextField();
		jtf8 = new JTextField();
		jtf9 = new JTextField();
		jtf10 = new JTextField();
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
		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jb1 = new JButton("Add");
		jb1.setFont(MyTools.f4);
		jb1.addActionListener(this);
		jb2 = new JButton("Cancel");
		jb2.setFont(MyTools.f4);
		jb2.addActionListener(this);
		p3.add(jb1);
		p3.add(jb2);
		this.setLayout(new BorderLayout());
		this.add(p1, "West");
		this.add(p2, "Center");
		this.add(p3, "South");

		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width / 2 - 200, height / 2 - 200);
		this.setSize(400, 250);

		this.setTitle("Add staff");
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if (arg0.getSource() == jb1) {
			EmpModel em=new EmpModel();
			String[] params={jtf1.getText()};
			String sql = "select * from rszl where empid = ?";
			if(em.checkIdExistence(sql, params)){
				JOptionPane.showMessageDialog(null, "Sorry,The emplyee id existe already!");
				jtf1.setText("");
			}else if (isEmpty(jtf1.getText())) {
				JOptionPane.showMessageDialog(null, "Sorry,You should input the employee id!");
			}
				
			else {
				SimpleDateFormat sdFormat = new SimpleDateFormat("dd-MM-YY HH:mm:ss");
				sql = "insert into rszl(empid,empname,sex,address,nas,edu,jobtitle,tel1,tel2,mail,regtime) values(?,?,?,?,?,?,?,?,?,?,?)";
				String[] params2 = { jtf1.getText(), jtf2.getText(), jtf3.getText(),
						jtf4.getText(), jtf5.getText(), jtf6.getText(),
						jtf7.getText(), jtf8.getText(), jtf9.getText(),
						jtf10.getText(),
						sdFormat.format(Calendar.getInstance().getTime())};
				
				if (!em.UpdateModel(sql, params2)) {
					JOptionPane.showMessageDialog(null,
							"Add failed!Please enter the correct information!");
				} else {
					JOptionPane.showMessageDialog(null,
							"Congratulation!Add with success!");
					empInfo.refreshTable();
					this.dispose();
				    

				}

			}
			
		} else if (arg0.getSource() == jb2) {
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
				JOptionPane.showMessageDialog(null, "Please input the integer");
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

	private boolean isEmpty(String str) {
		if (str.length() == 0 || str.isEmpty())
			return true;
		else
			return false;

	}

}
