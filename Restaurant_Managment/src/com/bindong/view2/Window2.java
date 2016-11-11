package com.bindong.view2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.Timer;

import com.bindong.model.OrderModel;
import com.bindong.tools.ImagePanel;
import com.bindong.tools.MyTools;
/**
 * l'interface principale pour la fonction de caisse
 *
 */
public class Window2 extends JFrame implements ActionListener, MouseListener,
		Runnable {
	Image titleIcon, timeBg, p1_bg, p2_bg, p3_bg;
	// ImagePanel p1_bgImage;
	JMenuBar jmb;
	JSplitPane jsp;
	JMenu jm1, jm2, jm3;
	JMenuItem jmi1, jmi2, jmi3, jmi4, jmi5, jmi6;
	ImageIcon jmi1_icon1, jmi2_icon2, jmi3_icon3, jmi4_icon4, jmi5_icon5,
			jmi6_icon6;
	JToolBar jtb;
	JButton jb1, jb2, jb3, jb4, jb5;
	JPanel jp1, jp2, jp3, jp4;
	JLabel showTime;// montrer le temps de system
	
	JLabel p2_jl1, p2_jl2, p2_jl3, p2_jl4, p2_jl5, p2_jl11;
	JLabel p1_jl1, p1_jl2, p1_jl3, p1_jl4, p1_jl5, p1_jl6, p1_jl7, p1_jl8,
			p1_jl9, p1_jl10, p1_jl11, p1_jl12, p1_jl13, p1_jl14, p1_jl15,
			p1_jl16, p1_jl17, p1_jl18, p1_jl19, p1_jl20, p1_jl21, p1_jl22,
			p1_jl23, p1_jl24, p1_jl25;
	CardLayout myCard;
	static String name = null;
	static String job = null;
	String tableStatusId;
	Object[] options = {"Reserve table","Order menu","Cancel"};
	Object[] options2 = {"Check out","Ajouter menu","Cancel"};
	Object[] options3 = {"Cancel reservation","Ajouter menu","Cancel"};
	Timer t;
	ImagePanel p1_bgImage, p2_bgImage, p3_bgImage;
	Cursor myCursor = new Cursor(HAND_CURSOR);

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

		Window2 w2 = new Window2(name, job);
		Thread a = new Thread(w2);
		a.start();

	}

	// 菜单
	public void initMenu() {
		// 一级菜单

		jmi1_icon1 = new ImageIcon("image/jm1_icon1.jpg");
		jmi2_icon2 = new ImageIcon("image/jm1_icon2.jpg");
		jmi3_icon3 = new ImageIcon("image/jm1_icon3.jpg");

		jm1 = new JMenu("System");
		jm1.setFont(MyTools.f3);
		// 创建其二级菜单
		jmi1 = new JMenuItem("Swtich User", jmi1_icon1);
		jmi1.setFont(MyTools.f2);
		jmi2 = new JMenuItem("Cashier", jmi2_icon2);
		jmi2.setFont(MyTools.f2);
		jmi3 = new JMenuItem("Exit", jmi3_icon3);
		jmi3.setFont(MyTools.f2);

		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);

		jmi4_icon4 = new ImageIcon("image/toolBar_image/jb4.jpg");

		jm2 = new JMenu("Service");
		jm2.setFont(MyTools.f3);
		jmi4 = new JMenuItem("Custom Service", jmi4_icon4);
		jmi4.setFont(MyTools.f2);
		jm2.add(jmi4);

		jmi6_icon6 = new ImageIcon("image/toolBar_image/jb6.jpg");
		jm3 = new JMenu("Help");
		jm3.setFont(MyTools.f3);
		jmi5 = new JMenuItem("Help Contents", jmi6_icon6);
		jmi5.setFont(MyTools.f2);
		jm3.add(jmi5);

		jmb = new JMenuBar();
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);

		this.setJMenuBar(jmb);
	}

	// 工具栏
	public void initToolBar() {
		jtb = new JToolBar();
		jtb.setFloatable(false);
		jb1 = new JButton(new ImageIcon("image/jm1_icon1.jpg"));
		jb2 = new JButton(new ImageIcon("image/jm1_icon2.jpg"));
		jb3 = new JButton(new ImageIcon("image/jm1_icon3.jpg"));
		jb4 = new JButton(new ImageIcon("image/jm1_icon4.jpg"));
		jb5 = new JButton(new ImageIcon("image/toolBar_image/jb5.jpg"));

		jtb.add(jb1);
		jtb.add(jb2);
		jtb.add(jb3);
		jtb.add(jb4);
		jtb.add(jb5);

	}

	public void initCenter() {
		jp1 = new JPanel(new BorderLayout());

		try {
			p1_bg = ImageIO.read(new File("image/orderindex1.jpg"));
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		p1_bgImage = new ImagePanel(p1_bg);
		p1_bgImage.setLayout(new BorderLayout());
		try {
			p3_bg = ImageIO.read(new File("image/desk.jpg"));
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		p3_bgImage = new ImagePanel(p3_bg);
		p3_bgImage.setLayout(new GridLayout(5, 5));

		p1_jl1 = new JLabel(new ImageIcon("image/desk/1.jpg"));
		p1_jl2 = new JLabel(new ImageIcon("image/desk/2.jpg"));
		p1_jl3 = new JLabel(new ImageIcon("image/desk/3.jpg"));
		p1_jl4 = new JLabel(new ImageIcon("image/desk/4.jpg"));
		p1_jl5 = new JLabel(new ImageIcon("image/desk/5.jpg"));
		p1_jl6 = new JLabel(new ImageIcon("image/desk/6.jpg"));
		p1_jl7 = new JLabel(new ImageIcon("image/desk/7.jpg"));
		p1_jl8 = new JLabel(new ImageIcon("image/desk/8.jpg"));
		p1_jl9 = new JLabel(new ImageIcon("image/desk/9.jpg"));
		p1_jl10 = new JLabel(new ImageIcon("image/desk/10.jpg"));
		p1_jl11 = new JLabel(new ImageIcon("image/desk/11.jpg"));
		p1_jl12 = new JLabel(new ImageIcon("image/desk/12.jpg"));
		p1_jl13 = new JLabel(new ImageIcon("image/desk/13.jpg"));
		p1_jl14 = new JLabel(new ImageIcon("image/desk/14.jpg"));
		p1_jl15 = new JLabel(new ImageIcon("image/desk/15.jpg"));
		p1_jl16 = new JLabel(new ImageIcon("image/desk/16.jpg"));
		p1_jl17 = new JLabel(new ImageIcon("image/desk/17.jpg"));
		p1_jl18 = new JLabel(new ImageIcon("image/desk/18.jpg"));
		p1_jl19 = new JLabel(new ImageIcon("image/desk/19.jpg"));
		p1_jl20 = new JLabel(new ImageIcon("image/desk/20.jpg"));
		p1_jl21 = new JLabel(new ImageIcon("image/desk/21.jpg"));
		p1_jl22 = new JLabel(new ImageIcon("image/desk/22.jpg"));
		p1_jl23 = new JLabel(new ImageIcon("image/desk/23.jpg"));
		p1_jl24 = new JLabel(new ImageIcon("image/desk/24.jpg"));
		p1_jl25 = new JLabel(new ImageIcon("image/desk/25.jpg"));
		JLabel[] jlables ={ p1_jl1, p1_jl2, p1_jl3, p1_jl4, p1_jl5, p1_jl6,
				p1_jl7, p1_jl8, p1_jl9, p1_jl10, p1_jl11, p1_jl12, p1_jl13,
				p1_jl14, p1_jl15, p1_jl16, p1_jl17, p1_jl18, p1_jl19, p1_jl20,
				p1_jl21, p1_jl22, p1_jl23, p1_jl24, p1_jl25 };
		
		String status[] = new String[25];
		OrderModel om = new OrderModel();
		boolean[] view = new boolean[25];
		//pour verifier l'etat de la table
		for (int i = 0; i < 25; i++) {

			status[i] = om.geStatusById((i + 1) + "");
			
			/*if ("2".equals(status[i].trim())) {
				view[i] = true;
			} else {
				view[i] = false;
			}*/
			if("0".equals(status[i].trim())){
				//view[i] = false;
				jlables[i].setIcon(new ImageIcon("image/desk/eating.jpg"));
			}
			else if ("1".equals(status[i].trim())) {
				//jlables[i] = new JLabel(new ImageIcon("image/desk/reserve.gif"));
				jlables[i].setIcon(new ImageIcon("image/desk/reserve.gif"));
			} else {
				//view[i] = true;
				jlables[i].setIcon(new ImageIcon("image/desk/"+(i+1)+".jpg"));
			}
			
			//jlables[i].setEnabled(view[i]);
			jlables[i].addMouseListener(this);

		}
		
		
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		p3_bgImage.setLocation(width / 4, height / 4);
		p1_bgImage.add(p3_bgImage, "Center");
		jp1.add(p1_bgImage, "Center");
		jp1.add(p3_bgImage, "East");
		jp2 = new JPanel(new BorderLayout());
		try {
			p2_bg = ImageIO.read(new File("image/manage.jpg"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		p2_bgImage = new ImagePanel(p2_bg);
		p2_bgImage.setLayout(new GridLayout(5, 2));
		p2_jl1 = new JLabel(new ImageIcon("image/center_image/label_1.gif"));
		p2_jl11 = new JLabel();
		p2_jl2 = new JLabel("Current User:");
		p2_jl2.setFont(MyTools.f3);
		p2_jl3 = new JLabel(name);
		p2_jl3.setFont(MyTools.f1);
		p2_jl4 = new JLabel("Job Title:");
		p2_jl4.setFont(MyTools.f3);
		p2_jl5 = new JLabel(job);
		p2_jl5.setFont(MyTools.f1);
		jb1 = new JButton("Reservation");
		jb1.setFont(MyTools.f5);
		jb1.setCursor(myCursor);
		jb1.addActionListener(this);
		jb2 = new JButton("Cancel");
		jb2.setFont(MyTools.f5);
		jb2.setCursor(myCursor);
		jb2.addActionListener(this);
		jb3 = new JButton("Order");
		jb3.setFont(MyTools.f5);
		jb3.setCursor(myCursor);
		jb3.addActionListener(this);
		jb4 = new JButton("Checkout");
		jb4.setFont(MyTools.f5);
		jb4.setCursor(myCursor);
		jb4.addActionListener(this);
		p2_bgImage.add(p2_jl1, "0");
		p2_bgImage.add(p2_jl11, "1");
		p2_bgImage.add(p2_jl2, "2");
		p2_bgImage.add(p2_jl3, "3");
		p2_bgImage.add(p2_jl4, "4");
		p2_bgImage.add(p2_jl5, "5");
		p2_bgImage.add(jb1, "6");
		p2_bgImage.add(jb2, "7");
		p2_bgImage.add(jb3, "8");
		p2_bgImage.add(jb4, "9");

		jp2.add(p2_bgImage, "Center");
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jp1, jp2);
		jsp.setDividerLocation(Toolkit.getDefaultToolkit().getScreenSize().width * 3 / 4);
		jsp.setDividerSize(0);
	}

	// 状态栏
	public void initEnd() {
		jp3 = new JPanel(new BorderLayout());
		t = new Timer(1000, this);// declencher l'evenement chaque second
		showTime = new JLabel("Current Time："
				+ Calendar.getInstance().getTime().toLocaleString() + "   ");
		showTime.setFont(MyTools.f1);

		t.start();
		try {
			timeBg = ImageIO.read(new File("image/time_bg.jpg"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		ImagePanel ip1 = new ImagePanel(timeBg);
		ip1.setLayout(new BorderLayout());
		ip1.add(showTime, "East");
		jp3.add(ip1);
	}

	public Window2(String empname, String zhiwei) {
		try {
			titleIcon = ImageIO.read(new File("image/title.gif"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		name = empname;
		job = zhiwei;
		// 菜单
		this.initMenu();
		// 工具栏
		this.initToolBar();

		// 中间
		this.initCenter();

		// 状态栏
		this.initEnd();

		Container ct = this.getContentPane();
		ct.add(jtb, "North");
		ct.add(jp3, "South");
		ct.add(jsp, "Center");

		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height - 40);
		this.setIconImage(titleIcon);
		this.setTitle("Restaurant Front Desk Management System");
		this.setVisible(true);
	}

	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == p1_jl1) {
			determineStatus("1");
		} else if (arg0.getSource() == p1_jl2) {
			determineStatus("2");
		} else if (arg0.getSource() == p1_jl3) {
			determineStatus("3");
		} else if (arg0.getSource() == p1_jl4) {
			determineStatus("4");
		} else if (arg0.getSource() == p1_jl5) {
			determineStatus("5");
		} else if (arg0.getSource() == p1_jl6) {
			determineStatus("6");
		} else if (arg0.getSource() == p1_jl7) {
			determineStatus("7");
		} else if (arg0.getSource() == p1_jl8) {
			determineStatus("8");
		} else if (arg0.getSource() == p1_jl9) {
			determineStatus("9");
		} else if (arg0.getSource() == p1_jl10) {
			determineStatus("10");
		} else if (arg0.getSource() == p1_jl11) {
			determineStatus("11");
		} else if (arg0.getSource() == p1_jl12) {
			determineStatus("12");
		} else if (arg0.getSource() == p1_jl13) {
			determineStatus("13");
		} else if (arg0.getSource() == p1_jl14) {
			determineStatus("14");
		} else if (arg0.getSource() == p1_jl15) {
			determineStatus("15");
		} else if (arg0.getSource() == p1_jl16) {
			determineStatus("16");
		} else if (arg0.getSource() == p1_jl17) {
			determineStatus("17");
		} else if (arg0.getSource() == p1_jl18) {
			determineStatus("18");
		} else if (arg0.getSource() == p1_jl19) {
			determineStatus("19");
		} else if (arg0.getSource() == p1_jl20) {
			determineStatus("20");
		} else if (arg0.getSource() == p1_jl21) {
			determineStatus("21");
		} else if (arg0.getSource() == p1_jl22) {
			determineStatus("22");
		} else if (arg0.getSource() == p1_jl23) {
			determineStatus("23");
		} else if (arg0.getSource() == p1_jl24) {
			determineStatus("24");
		} else if (arg0.getSource() == p1_jl25) {
			determineStatus("25");
		}
	}
	//verifer l'etat de table par id de table ,selon l'etat pour choisir une operation
	private void determineStatus (String tableId){
		OrderModel om = new OrderModel();
		String statusId = om.geStatusById(tableId);
		if(statusId.trim().equals("2")){
			int response=JOptionPane.showOptionDialog ( null, "The table "+tableId+" is available, Please choose the operation!","Choose the operation",JOptionPane.YES_OPTION ,JOptionPane.PLAIN_MESSAGE,
					null, options, options[0] ) ;
			/*int result = JOptionPane.showConfirmDialog(null,
					"The table "+tableId+" is available, do you want to reserve it?", "Confimez,svp",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.OK_OPTION){
				new AddOrdered(tableId);
			}*/
			if(response == 0)
				new AddOrdered(this,tableId);
			else if(response == 1)
				new BookDish(this,tableId);
			else 
				;
			
		}
			
		else{
			if(statusId.trim().equals("0")){
				int response=JOptionPane.showOptionDialog ( null, "The table "+tableId+" is being using, Please choose the operation!","Choose the operation",JOptionPane.YES_OPTION ,JOptionPane.PLAIN_MESSAGE,
						null, options2, options[0] ) ;
				
				if (response == 0)
					new Accounts(this,tableId);
				else if (response == 1) {
					new BookDish(this,tableId);
				}else {
					;
				}
				
				
				
			}else if(statusId.trim().equals("1")){
				int response=JOptionPane.showOptionDialog ( null, "The table "+tableId+" is being using, Please choose the operation!","Choose the operation",JOptionPane.YES_OPTION ,JOptionPane.PLAIN_MESSAGE,
						null, options3, options[0] ) ;
				
				if (response == 0)
					new Accounts(this,tableId);
				else if (response == 1) {
					new BookDish(this,tableId);
				}else {
					;
				}
			}
			
		}
			
		
	}
	
	
	

	public void mouseEntered(MouseEvent arg0) {

		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	public void mouseReleased(MouseEvent arg0) {

	}

	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		this.showTime.setText("Current time："
				+ Calendar.getInstance().getTime().toLocaleString() + "   ");
		if (e.getSource() == jb1) {

			new AddOrdered(this,"");
		} else if (e.getSource() == jb2) {

			new DelOrdered(this,"");
		} else if (e.getSource() == jb3) {
			new BookDish(this,"");
		} else if (e.getSource() == jb4) {
		    new Accounts(this,"");
		}
	}
	
	public void refreshTables(){
		JLabel[] jlables = { p1_jl1, p1_jl2, p1_jl3, p1_jl4, p1_jl5, p1_jl6,
				p1_jl7, p1_jl8, p1_jl9, p1_jl10, p1_jl11, p1_jl12, p1_jl13,
				p1_jl14, p1_jl15, p1_jl16, p1_jl17, p1_jl18, p1_jl19, p1_jl20,
				p1_jl21, p1_jl22, p1_jl23, p1_jl24, p1_jl25 };
		String status[] = new String[25];
		OrderModel om = new OrderModel();
		boolean[] view = new boolean[25];
		//pour verifier l'etat de la table
		for (int i = 0; i < 25; i++) {

			status[i] = om.geStatusById((i + 1) + "");
			if("0".equals(status[i].trim())){
				//view[i] = false;
				jlables[i].setIcon(new ImageIcon("image/desk/eating.jpg"));
			}
			else if ("1".equals(status[i].trim())) {
				//jlables[i] = new JLabel(new ImageIcon("image/desk/reserve.gif"));
				jlables[i].setIcon(new ImageIcon("image/desk/reserve.gif"));
			} else {
				//view[i] = true;
				jlables[i].setIcon(new ImageIcon("image/desk/"+(i+1)+".jpg"));
			}
			
			//jlables[i].setEnabled(view[i]);
			//jlables[i].addMouseListener(this);

		}
		for(int i=0;i< jlables.length;i++){
			p3_bgImage.add(jlables[i]);
			//jlables[i].setCursor(myCursor);
		}
		
		
	}
	public void run() {
		
		JLabel[] jlables = { p1_jl1, p1_jl2, p1_jl3, p1_jl4, p1_jl5, p1_jl6,
				p1_jl7, p1_jl8, p1_jl9, p1_jl10, p1_jl11, p1_jl12, p1_jl13,
				p1_jl14, p1_jl15, p1_jl16, p1_jl17, p1_jl18, p1_jl19, p1_jl20,
				p1_jl21, p1_jl22, p1_jl23, p1_jl24, p1_jl25 };
		for (int i = 0; i < jlables.length; i++) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
			p3_bgImage.add(jlables[i]);
			jlables[i].setCursor(myCursor);
			// jlables[i].setBackground(Color.gray);
		}
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

			break;
		}
	}

}
