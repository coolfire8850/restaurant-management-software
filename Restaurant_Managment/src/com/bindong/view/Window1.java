package com.bindong.view;

import com.bindong.tools.*;
import com.bindong.view2.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
/**
 * 
 * l'interface principale pour consulter les informations divers
 *
 */
public class Window1 extends JFrame implements ActionListener,MouseListener{
	//defenir les composants necessaires
	Image titleIcon,timeBg,p1_bg,p3Icon,chart;
	ImagePanel p1_bgImage,jp3Image,ct;
	JMenuBar jmb;
	JSplitPane jsp;
	JMenu jm1,jm2,jm3,jm4,jm5,jm6;
	JMenuItem jmi1,jmi2,jmi3,jmi4,jmi5,jmi6,jmi7,jmi8,jmi9,jmi10,jmi11,jmi12;
	ImageIcon jmi1_icon1,jmi2_icon2,jmi3_icon3,jmi4_icon4,jmi5_icon5,jmi6_icon6,jmi7_icon7,jmi8_icon8,jmi9_icon9,jmi10_icon10,jmi11_icon11,jmi12_icon12;
	JToolBar jtb;
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;
	JPanel jp1,jp2,jp3,jp4,jp5;
	JLabel showTime;//montrer le temps
	JLabel p2_jl1,p2_jl2;
	JLabel p1_jl1,p1_jl2,p1_jl3,p1_jl4,p1_jl5,p1_jl6,p1_jl7,p1_jl8;
	CardLayout myCard,myCard2;
	Timer t;
	String userName,positionTitle;
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Window1 w1=new Window1("jason","");
	}
	
	public void initMenu()
	{
		        

				jmi1_icon1=new ImageIcon("image/jm1_icon1.jpg");
				jmi2_icon2=new ImageIcon("image/jm1_icon2.jpg");
				jmi3_icon3=new ImageIcon("image/jm1_icon3.jpg");
				jmi4_icon4=new ImageIcon("image/jm1_icon4.jpg");
				jmi5_icon5=new ImageIcon("image/jm1_icon5.jpg");
				jm1=new JMenu("System");
				jm1.setFont(MyTools.f3);
				//sub menu
				jmi1=new JMenuItem("Switch User",jmi1_icon1);
				jmi1.addActionListener(this);
				jmi1.setFont(MyTools.f2);
				jmi2=new JMenuItem("Cashier",jmi2_icon2);
				jmi2.addActionListener(this);
				jmi2.setFont(MyTools.f2);
				jmi3=new JMenuItem("Login Management",jmi3_icon3);
				jmi3.setFont(MyTools.f2);
				jmi4=new JMenuItem("Calendar",jmi4_icon4);
				jmi4.setFont(MyTools.f2);
				jmi5=new JMenuItem("Exit",jmi5_icon5);
				jmi5.addActionListener(this);
				jmi5.setFont(MyTools.f2);
				
				jm1.add(jmi1);
				jm1.add(jmi2);
				jm1.add(jmi3);
				jm1.add(jmi4);
				jm1.add(jmi5);
				
				
				jmi6_icon6=new ImageIcon("image/toolBar_image/jb4.jpg");
				
				jm2=new JMenu("Staff");
				jm2.setFont(MyTools.f3);
				jmi6=new JMenuItem("Register",jmi6_icon6);
				jmi6.setFont(MyTools.f2);
				jm2.add(jmi6);
				
				jmi7_icon7=new ImageIcon("image/toolBar_image/jb6.jpg");
				jm3=new JMenu("Menu");
				jm3.setFont(MyTools.f3);
				jmi7=new JMenuItem("Price",jmi7_icon7);
				jmi7.setFont(MyTools.f2);
				jm3.add(jmi7);
				
				jmi8_icon8=new ImageIcon("image/toolBar_image/jb5.jpg");
				jm4=new JMenu("Report");
				jm4.setFont(MyTools.f3);
				jmi8=new JMenuItem("Financial statements",jmi8_icon8);
				jmi8.setFont(MyTools.f2);
				jm4.add(jmi8);
				
				
				
				jmi9_icon9=new ImageIcon("image/toolBar_image/jb7.jpg");
				jm5=new JMenu("Costs");
				jm5.setFont(MyTools.f3);
				jmi9=new JMenuItem("Cost Control",jmi9_icon9);
				jmi9.setFont(MyTools.f2);
				jm5.add(jmi9);
				
				jmi10_icon10=new ImageIcon("image/toolBar_image/jb9.jpg");
				jmi11_icon11=new ImageIcon("image/toolBar_image/jb10.jpg");
				jmi12_icon12=new ImageIcon("image/toolBar_image/jb8.jpg");
				jm6=new JMenu("Help");
				jm6.setFont(MyTools.f3);
				jmi10=new JMenuItem("AviHelp",jmi10_icon10);
				jmi10.setFont(MyTools.f2);
				jmi11=new JMenuItem("Help Contents",jmi11_icon11);
				jmi11.setFont(MyTools.f2);
				jmi12=new JMenuItem("About us",jmi12_icon12);
				jmi12.setFont(MyTools.f2);
				jmi10.setFont(MyTools.f2);
				//jm6.add(jmi10);
				jm6.add(jmi11);
				jm6.add(jmi12);
				
				
				
				
				jmb=new JMenuBar();
				jmb.add(jm1);
				jmb.add(jm2);
				jmb.add(jm3);
				jmb.add(jm4);
				jmb.add(jm5);
				jmb.add(jm6);
				this.setJMenuBar(jmb);
	}
	
	public void initToolBar()
	{
		jtb=new JToolBar();
		jtb.setFloatable(false);
		jb1=new JButton(new ImageIcon("image/toolBar_image/jb1.jpg"));
		jb1.setToolTipText("Swtich user");
		jb1.addActionListener(this);
		jb2=new JButton(new ImageIcon("image/toolBar_image/jb2.jpg"));
		jb2.setToolTipText("Cashier");
		jb2.addActionListener(this);
		jb3=new JButton(new ImageIcon("image/toolBar_image/jb3.jpg"));
		jb3.setToolTipText("Login Management");
		jb3.addActionListener(this);
		jb4=new JButton(new ImageIcon("image/toolBar_image/jb4.jpg"));
		jb4.setToolTipText("Calendar");
		jb4.addActionListener(this);
		jb5=new JButton(new ImageIcon("image/toolBar_image/jb5.jpg"));
		jb5.setToolTipText("Financial Statements");
		jb5.addActionListener(this);
		jb6=new JButton(new ImageIcon("image/toolBar_image/jb6.jpg"));
		jb6.setToolTipText("Menu Price");
		jb6.addActionListener(this);
		jb7=new JButton(new ImageIcon("image/toolBar_image/jb7.jpg"));
		jb7.setToolTipText("Menu Cost");
		jb7.addActionListener(this);
		jb8=new JButton(new ImageIcon("image/toolBar_image/jb8.jpg"));
		jb8.setToolTipText("About Us");
		jb8.addActionListener(this);
		jb9=new JButton(new ImageIcon("image/toolBar_image/jb9.jpg"));
		jb9.setToolTipText("Media Player");
		jb9.addActionListener(this);
		jb10=new JButton(new ImageIcon("image/toolBar_image/jb10.jpg"));
		jb10.setToolTipText("Help");
		jb10.addActionListener(this);
		jtb.add(jb1);
		jtb.add(jb2);
		jtb.add(jb3);
		jtb.add(jb4);
		jtb.add(jb5);
		jtb.add(jb6);
		jtb.add(jb7);
		jtb.add(jb8);
		jtb.add(jb9);
		jtb.add(jb10);
	
	}
	public void initCenter()
	{

		
		jp1=new JPanel(new BorderLayout());
		try {
			p1_bg=ImageIO.read(new File("image/center_image/jp1_bg.jpg"));
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		Cursor myCursor=new Cursor(HAND_CURSOR);
		p1_bgImage=new ImagePanel(p1_bg);
		p1_bgImage.setLayout(new GridLayout(8,1));
		p1_jl1=new JLabel(new ImageIcon("image/center_image/label_1.gif"));
		p1_bgImage.add(p1_jl1);
		p1_jl2=new JLabel("Staff Management",new ImageIcon("image/center_image/label_2.jpg"),0);
		p1_jl2.setFont(MyTools.f4);
		
		p1_jl2.setCursor(myCursor);
		p1_jl2.setEnabled(false);
		p1_jl2.addMouseListener(this);
		p1_bgImage.add(p1_jl2);
		p1_jl3=new JLabel("Login Management",new ImageIcon("image/center_image/label_3.jpg"),0);
		p1_jl3.setFont(MyTools.f4);
		p1_jl3.setCursor(myCursor);
		p1_jl3.setEnabled(false);
		p1_jl3.addMouseListener(this);
		p1_bgImage.add(p1_jl3);
		p1_jl4=new JLabel("Menu Price      ",new ImageIcon("image/center_image/label_4.jpg"),0);
		p1_jl4.setFont(MyTools.f4);
		p1_jl4.setCursor(myCursor);
		p1_jl4.setEnabled(false);
		p1_jl4.addMouseListener(this);
		p1_bgImage.add(p1_jl4);
		p1_jl5=new JLabel("Statics         ",new ImageIcon("image/center_image/label_5.jpg"),0);
		p1_jl5.setFont(MyTools.f4);
		p1_jl5.setCursor(myCursor);
		p1_jl5.setEnabled(false);
		p1_jl5.addMouseListener(this);
		p1_bgImage.add(p1_jl5);
		p1_jl6=new JLabel("Menu Cost       ",new ImageIcon("image/center_image/label_6.jpg"),0);
		p1_jl6.setFont(MyTools.f4);
		p1_jl6.setCursor(myCursor);
		p1_jl6.setEnabled(false);
		p1_jl6.addMouseListener(this);
		p1_bgImage.add(p1_jl6);
		p1_jl7=new JLabel("WareHouse       ",new ImageIcon("image/center_image/label_7.jpg"),0);
		p1_jl7.setFont(MyTools.f4);
		p1_jl7.setCursor(myCursor);
		p1_jl7.setEnabled(false);
		p1_jl7.addMouseListener(this);
		p1_bgImage.add(p1_jl7);
		p1_jl8=new JLabel("Media Player     ",new ImageIcon("image/center_image/label_8.jpg"),0);
		p1_jl8.setFont(MyTools.f4);
		p1_jl8.setCursor(myCursor);
		p1_jl8.setEnabled(false);
		p1_jl8.addMouseListener(this);
		p1_bgImage.add(p1_jl8);
		jp1.add(p1_bgImage);
		
	
		myCard=new CardLayout();
		myCard2 = new CardLayout();
		jp4=new JPanel(new BorderLayout());
		jp2=new JPanel(myCard2);
		p2_jl1=new JLabel(new ImageIcon("image/center_image/jp2_left.jpg"));
		//Icone pour basculer
		p2_jl1.addMouseListener(this);
		p2_jl2=new JLabel(new ImageIcon("image/center_image/jp2_right.jpg"));
		p2_jl2.addMouseListener(this);
		jp2.add(p2_jl1,"0");
		jp2.add(p2_jl2,"1");
		
		//ajouter la carte dans jp3 panel
		jp3=new JPanel(myCard);
		
		try {
			p3Icon=ImageIO.read(new File("image/center_image/jp1_bg.jpg"));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		jp3Image=new ImagePanel(p3Icon);
		//ajouter les composants avec l'ordre
		jp3.add(jp3Image,"0");
		
		 
		jp4.add(jp2,"West");
		jp4.add(jp3,"Center");
		jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,jp1,jp4);
		jsp.setDividerLocation(150);
		jsp.setDividerSize(0);
	}
	
	public void initEnd()
	{
		jp5=new JPanel(new BorderLayout());
		t=new Timer(1000, this);//declencher l'evenmenet chaque second
		showTime=new JLabel("Current Time："+Calendar.getInstance().getTime().toLocaleString()+"   ");
		showTime.setFont(MyTools.f1);
		
		t.start();
		try {
			timeBg=ImageIO.read(new File("image/time_bg.jpg"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		ImagePanel ip1=new ImagePanel(timeBg);
		ip1.setLayout(new BorderLayout());
		ip1.add(showTime,"East");
		jp5.add(ip1);
	}
	public Window1(String userName,String positionTitle)
	{
		this.userName = userName;
		this.positionTitle = positionTitle;
		try {
			titleIcon=ImageIO.read(new File("image/title.gif"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		
		this.initMenu();
		
		this.initToolBar();
		
		
		this.initCenter();
		
		
		this.initEnd();
		
		
		Container ct=this.getContentPane();
		ct.add(jtb,"North");
		ct.add(jp5,"South");
		ct.add(jsp,"Center");
		
		
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width,height-40);
		this.setIconImage(titleIcon);
		this.setTitle("Resturant Management System "+"            Current User: "+userName.toUpperCase());
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		this.showTime.setText("Current Time："+Calendar.getInstance().getTime().toLocaleString()+"   ");
		if(e.getSource() == jmi5){
			System.exit(0);
		}else if(e.getSource() == jmi1){
			  
			int result = JOptionPane.showConfirmDialog(null,
					"Do you want to change another user?", "Confirm,Please",
					JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.OK_OPTION){
            	this.dispose();
  		        new UserLogin();
            }
		      
		}else if(e.getSource() == jb1){
			  
			int result = JOptionPane.showConfirmDialog(null,
					"Do you want to change another user?", "Confirm,Please",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				this.dispose();
				new UserLogin();
			}
		      
		}else if (e.getSource() == jmi2){
			int result = JOptionPane.showConfirmDialog(null,
					"Do you want to switch cash window?", "Confirm,Please",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				this.dispose();
				
				Thread a = new Thread(new Window2(userName,positionTitle));
				a.start();
			}
			
		}else if (e.getSource() == jb2){
			int result = JOptionPane.showConfirmDialog(null,
					"Do you want to switch cash window?", "Confirm,Please",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				this.dispose();
				Thread a = new Thread(new Window2(userName,positionTitle));
				a.start();
			}
			
		}
	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==p1_jl2)
		{
			EmpInfo ei=new EmpInfo();
			
			jp3.add(ei,"1");
			this.myCard.show(jp3,"1");
			
		}
		else if(arg0.getSource()==p1_jl3)
		{  
			EmpLogin elEmpLogin = new EmpLogin();
			
			jp3.add(elEmpLogin,"2");
			this.myCard.show(jp3,"2");
			
			
		}
		else if(arg0.getSource()==p1_jl4)
		{
			 MenuInfo mi=new MenuInfo();
			 jp3.add(mi,"3");
			 this.myCard.show(jp3,"3");
			/*MenuInfo mi=new MenuInfo();
			jp3.add(mi,"3");*/
			
		}
		else if(arg0.getSource()==p1_jl5)
		{
			Chart chart = new Chart();
			//ct.setBounds(0, 0, this.getWidth()-50, this.getHeight()-10);
			jp3.add(chart.getChartPanel(),"4");
			this.myCard.show(jp3,"4");
		}
		else if(arg0.getSource()==p1_jl6)
		{
			CostNum cn=new CostNum();
			jp3.add(cn,"5");
			this.myCard.show(jp3,"5");
		}
		else if(arg0.getSource()==p1_jl7)
		{
			WareHouseInfo wh = new WareHouseInfo();
			jp3.add(wh,"6");
			this.myCard.show(jp3,"6");
		}
		else if(arg0.getSource()==p1_jl8)
		{
			MediaHelp mh=new MediaHelp();
			
			
		}
		else if(arg0.getSource()==p2_jl1)
		{
			
			this.myCard2.show(jp2, "1");
			this.jsp.setDividerLocation(0);
		}
		else if(arg0.getSource()==p2_jl2)
		{
			
			this.jsp.setDividerLocation(Toolkit.getDefaultToolkit().getScreenSize().width/9);
			this.myCard2.show(jp2, "0");
			
		}
		
	}
	public void mouseEntered(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==p1_jl2)
		{
			this.p1_jl2.setEnabled(true);
		}else if(arg0.getSource()==p1_jl3)
		{
			this.p1_jl3.setEnabled(true);
		}
		else if(arg0.getSource()==p1_jl4)
		{
			this.p1_jl4.setEnabled(true);
		}
		else if(arg0.getSource()==p1_jl5)
		{
			this.p1_jl5.setEnabled(true);
		}
		else if(arg0.getSource()==p1_jl6)
		{
			this.p1_jl6.setEnabled(true);
		}
		else if(arg0.getSource()==p1_jl7)
		{
			this.p1_jl7.setEnabled(true);
		}
		else if(arg0.getSource()==p1_jl8)
		{
			this.p1_jl8.setEnabled(true);
		}
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==p1_jl2)
		{
		this.p1_jl2.setEnabled(false);
		}else if(arg0.getSource()==p1_jl3)
		{
			this.p1_jl3.setEnabled(false);
		}else if(arg0.getSource()==p1_jl4)
		{
			this.p1_jl4.setEnabled(false);
		}
		else if(arg0.getSource()==p1_jl5)
		{
			this.p1_jl5.setEnabled(false);
		}
		else if(arg0.getSource()==p1_jl6)
		{
			this.p1_jl6.setEnabled(false);
		}
		else if(arg0.getSource()==p1_jl7)
		{
			this.p1_jl7.setEnabled(false);
		}
		else if(arg0.getSource()==p1_jl8)
		{
			this.p1_jl8.setEnabled(false);
		}
	}
	public void mousePressed(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

}
