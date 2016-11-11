package com.bindong.view;

import com.bindong.model.*;
import com.bindong.tools.*;
import com.bindong.view2.Window2;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
/**
 * L'interface graphique pour log in 
 */
public class UserLogin extends JDialog implements ActionListener {

	
	JLabel jl1, jl2, jl3;
	JTextField jname;
	JPasswordField jpass;
	JButton jconfirm, jcancel;

	
	public static void main(String[] args) {

		// TODO 自动生成的方法存根
		UserLogin ul = new UserLogin();
	}

	public UserLogin() {
		
		Container ct = this.getContentPane();
		this.setLayout(null);
		jl1 = new JLabel("UserId:");
		jl1.setFont(MyTools.f1);
		jl1.setBounds(100, 140, 150, 30);
		ct.add(jl1);
		jname = new JTextField(20);
		jname.setFont(MyTools.f1);
		jname.setBounds(180, 140, 120, 30);
		jname.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jname);


		jl3 = new JLabel("PassWord:");
		jl3.setFont(MyTools.f1);
		jl3.setBounds(100, 190, 150, 30);
		ct.add(jl3);
		jpass = new JPasswordField(20);
		jpass.setFont(MyTools.f1);
		jpass.setBounds(180, 190, 120, 30);
		jpass.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jpass);

		jconfirm = new JButton("Confirm");
		jconfirm.addActionListener(this);
		jconfirm.setFont(MyTools.f1);
		jconfirm.setBounds(130, 250, 90, 30);
		ct.add(jconfirm);

		jcancel = new JButton("Cancel");
		jcancel.addActionListener(this);
		jcancel.setFont(MyTools.f1);
		jcancel.setBounds(240, 250, 80, 30);
		ct.add(jcancel);
		BackImage bi = new BackImage();
	
		bi.setBounds(0, 0, 450, 315);
		// this.add(bi);
		ct.add(bi);
		
		this.setUndecorated(true);
		this.setSize(450, 315);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width / 2 - 200, height / 2 - 200);
		this.setVisible(true);

	}

	//inner class pour manipuler l'image
	class BackImage extends JPanel {
		Image img;

		public BackImage() {
			try {
				img = ImageIO.read(new File("image//login.jpg"));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}

		public void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, 450, 315, this);
		}
	}

	
	public void actionPerformed(ActionEvent e) {
	

		if (e.getSource() == jconfirm) {
			String uid = this.jname.getText().trim();
			String p = new String(this.jpass.getPassword());
			if (!isEmpty(uid) && !isEmpty(p)) {
				UserModel um = new UserModel();
				String zhiwei = new String(um.checkUser(uid, p).trim());
				if(zhiwei.equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(this, "Sorry,it doesn't existe this account!");
				}else {
					String empname = um.getNameById(uid);
					if (empname != null) {
						if ("manager".equals(zhiwei) || zhiwei.equals("boss")) {

							new Window1(empname,zhiwei);

							this.hide();
							String welcome = "Welcome: " + empname;
							JOptionPane.showMessageDialog(this, welcome);
							this.dispose();
						} else if ("cashier".equals(zhiwei) || zhiwei.equals("supervisor")|| zhiwei.equals("waiter")) {
							Window2 w2 = new Window2(empname, zhiwei);
							Thread a = new Thread(w2);
							a.start();
							this.hide();
							String welcome = "Welcome: " + empname;
							JOptionPane.showMessageDialog(this, welcome);
							this.dispose();

						} else {
							JOptionPane.showMessageDialog(this, "Sorry, you don't have right to log in!");
						}

					}
				}
				
			} else {
				
				JOptionPane.showMessageDialog(this, "Veuillez completer les champs vides!");	
			}

			// String b=zhiwei;

			// System.out.println(b);
			// System.out.println(a);
			// System.out.println((boolean)(a==b));

		} else if (e.getSource() == jcancel)

		{

			
			this.dispose();
		}

	}

	private boolean isEmpty(String words) {

		if (words.length() == 0 || words.isEmpty())
			return true;
		else {
			return false;
		}

	}

}
