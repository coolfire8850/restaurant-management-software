package com.bindong.view2;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JDialog;

import java.util.Calendar;
import java.util.Random;
import java.util.Vector;

import com.bindong.model.*;
import com.bindong.tools.MyTools;

import java.awt.event.*;
/**
 * pour ajouter le id de commande dans la basse de donnee
 *
 */
public class BookDish extends JDialog implements ActionListener,KeyListener{

	JPanel p1,p2,p3;
	JLabel jl1,jl2;
	JTextField jtf1,jtf2;
	JButton jb1,jb2;
	String tableId;
	OrderModel om;
	Window2 window2;
	public BookDish(Window2 window2,String tableId)
	{
		super();
		this.window2 = window2;
		this.tableId = tableId;
		
		
		
		p1=new JPanel(new GridLayout(2,1));
		
		jl1=new JLabel("deskid");
		jl2=new JLabel("bookid");
		p1.add(jl1);
		p1.add(jl2);
		p2=new JPanel(new GridLayout(2,1));
		jtf1=new JTextField();
		jtf2=new JTextField();
		if(!isEmpty(tableId)){
			String bookid = checkBookId(tableId);
			jtf1.setText(tableId);
			jtf1.setEditable(false);
			if(!isEmpty(bookid)){
				jtf2.setText(bookid);
				
			}else {
				jtf2.setText(new Random().nextInt(999999999)+"");
			}
			jtf2.setEditable(false);
		}else {
			jtf1.addKeyListener(this);
		}
		
		
		p2.add(jtf1);
		p2.add(jtf2);
		
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
		this.setSize(300,150);

		this.setTitle("Order menu");
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource()==jb1)
		{
			
			

			    new BookDetail(window2,this);
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

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jtf1){
			
			if(isEmpty(jtf1.getText())){
				jtf2.setText("");
				jtf2.setEditable(true);
				
			}else {
				if(isNumeric(jtf1.getText())&&isRightTableId(jtf1.getText())){
					String deskIdString = jtf1.getText();
					String bookid = checkBookId(deskIdString);
					if(!isEmpty(bookid)){
						//jtf1.setEditable(false);
						jtf2.setText(bookid);
						jtf2.setEditable(false);
					}else if(!isEmpty(deskIdString)){
						jtf2.setText(new Random().nextInt(999999999)+"");
						
						jtf2.setEditable(false);
					}
				}else {
					JOptionPane.showMessageDialog(null,
							"Please input the table id between 1 and 25");
					jtf1.setText("");
					jtf2.setText("");
					jtf2.setEditable(true);
				}
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
	
	private String checkBookId(String tableId){
		String [] params ={tableId};
		String sql = "select bookid from bookdish where deskid = ?";
		om = new OrderModel();
		String bookid = om.query(sql, params);
		return bookid;
	}
	
	private boolean isRightTableId(String tableId){
		boolean a = true;
		if(Integer.parseInt(tableId)<=0||Integer.parseInt(tableId)>25)
			a = false;
		return a ;
	}
}

