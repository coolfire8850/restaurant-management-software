package com.bindong.view2;

import java.awt.*;

import javax.swing.*;

import com.bindong.model.*;
import com.bindong.tools.MyTools;

import java.awt.event.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * L'interface pour commander le menu
 *
 */
public class BookDetail extends JDialog implements ActionListener, KeyListener {

	JPanel p1, p2, p3, p4;
	JLabel jl1, jl2;

	JTextField[] jtxf1, jtxf2;
	JLabel[] jLabels;
	JButton jb1, jb2;
	String deskid, bookid;
	HashMap<String, String> map;
	Window2 window2;

	public BookDetail(Window2 window2,BookDish bd) {

		super();
		this.window2 =window2;
		deskid = bd.jtf1.getText().trim();
		bookid = bd.jtf2.getText().trim();

		String[] params = { "1" };
		String sql = "select * from menu where 1=?";
		EmpModel em = new EmpModel();
		map = em.getPairValues(sql, params);
		// System.out.println(map.size()+"haha");

		jLabels = new JLabel[map.size()];
		jtxf1 = new JTextField[map.size()];
		jtxf2 = new JTextField[map.size()];

		p1 = new JPanel(new GridLayout(map.size(), 1));
		p2 = new JPanel(new GridLayout(map.size(), 1));
		p3 = new JPanel(new GridLayout(map.size(), 1));

		Iterator iter = map.entrySet().iterator();
		int i = 0;
		for (Entry<String, String> entry : map.entrySet()) {
			jLabels[i] = new JLabel(entry.getKey() + " ");
			jLabels[i].setPreferredSize(new Dimension(15, 15));
			p1.add(jLabels[i]);
			jtxf1[i] = new JTextField(entry.getValue());
			jtxf1[i].setEditable(false);
			p2.add(jtxf1[i]);
			jtxf2[i] = new JTextField(5);
			jtxf2[i].addKeyListener(this);
			p3.add(jtxf2[i]);
			i++;
		}

		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jb1 = new JButton("Add");
		jb1.setFont(MyTools.f4);
		jb1.addActionListener(this);
		jb2 = new JButton("Cancel");
		jb2.setFont(MyTools.f4);
		jb2.addActionListener(this);
		p4.add(jb1);
		p4.add(jb2);
		this.setLayout(new BorderLayout());
		this.add(p1, "West");
		this.add(p2, "Center");
		this.add(p3, "East");
		this.add(p4, "South");

		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width / 2 - 200, height / 2 - 200);
		this.setSize(460, 300);

		this.setTitle("Order menu");
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==jb1)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			
			for (int i = 0 ;i< jtxf2.length;i++){
				if(!isEmpty(jtxf2[i].getText()))
					map.put(jLabels[i].getText().trim(), jtxf2[i].getText().trim());
			}
			
			Iterator iter = map.entrySet().iterator();
			String [][] params = new String[map.size()][3];
			int k = 0;
			for (Entry<String, String> entry : map.entrySet()) {
				for(int j = 0;j<3;j++){
					switch (j) {
					case 0:
						params[k][j] =bookid;
						break;
					case 1:
						params[k][j] =entry.getKey();
						break;
					case 2:
						params[k][j] =entry.getValue();
						break;
					default:
						break;
					}
				}
					
				//System.out.println(entry.getKey()+" ,"+entry.getValue());
				k++;
			}
			
			/*for(int i =0;i<params.length;i++){
				for(int j =0;j<params[i].length;j++)
					System.out.println(params[i][j]);
			}*/
			
			String sql="insert into bookdetail values(?,?,?)";
			OrderModel om=new OrderModel();
			
			for(int i=0;i<params.length;i++)
			{
				om.UpdateModel(sql, params[i]);
			}
			
			if(!checkBookDishId()){
				String sql1="insert into bookdish values(?,?)";
				String []params1={deskid,bookid};
				
				om.UpdateModel(sql1, params1);
			}
			
			String[]params10={deskid};
			String sql3="update status set deskstatus=0 where deskid=?";
			om.UpdateModel(sql3, params10);
			JOptionPane.showMessageDialog(null, "Congratulation,add with success!");
			window2.refreshTables();
			this.dispose();
		
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
			
		}
	}
	
	private boolean checkBookDishId(){
		boolean a = true;
		String sql = "select * from bookdish where deskid =?";
		String[] params ={deskid};
		OrderModel om=new OrderModel();
		String idExist = om.query(sql, params);
		if(isEmpty(idExist))
			a = false;
		else 
			a = true;
		
		return a ;
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
		for (int i = 0; i < jtxf2.length; i++) {
			if (e.getSource() == jtxf2[i]) {
				if (!isNumeric(jtxf2[i].getText())) {
					JOptionPane.showMessageDialog(null,
							"Please input the integer");
					jtxf2[i].setText("");
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
	
	private boolean isEmpty(String str){
		if(str.length()==0||str.isEmpty())
			return true;
		else 
			return false;
		
	}

}

