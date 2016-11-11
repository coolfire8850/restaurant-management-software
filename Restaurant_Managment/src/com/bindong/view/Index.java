package com.bindong.view;
/**
 * Author: bin dong
 * Version: 1.1
 * C'est l'entree du programe
 */
import java.awt.*;

import javax.swing.*;
public class Index extends JWindow implements Runnable{
	
	JProgressBar jpb;//definir Une barre de progression 
	JLabel jl1;// sert a mettre un photo au nord de la barre
	int width,height;
	public static void main(String []args){
		
		Index index=new Index();
		//creer un fil pour l'index
		Thread t=new Thread(index);
		//demarer
		t.start();
	}
	
	public Index()
	{	
		//mettre un photo dans le label
		jl1=new JLabel(new ImageIcon("image/index/test.gif"));
		
		
		jpb=new JProgressBar();
		//configurer les parametres de la barre
		jpb.setStringPainted(true);//afficher les progr¨¨s actuels 
		jpb.setIndeterminate(false);//affirmer les progr¨¨s ne reculent pas
		jpb.setBorderPainted(false);
		jpb.setBackground(Color.pink);
		
		//ajouter les composants
		this.add(jl1,BorderLayout.NORTH);
		this.add(jpb,BorderLayout.SOUTH);
		
	
		this.setSize(400,263);
		//installer la postion de la fenetre
		width=Toolkit.getDefaultToolkit().getScreenSize().width;
		height=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200,height/2-150);
		
		this.setVisible(true);
	}

	public void run() {

		//definir un tableau pour stocker les progr¨¨s
		int []progressValue={0,1,5,8,14,17,26,35,38,43,49,56,65,71,75,78,86,94,98,99,100};
		for(int i=0;i<progressValue.length;i++)
		{
			try {
				//reposer un second
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jpb.setValue(progressValue[i]);//obtenir les valeurs dans le tableau
		}
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		new UserLogin();
		
		this.dispose();
		break;
		}
	}
}