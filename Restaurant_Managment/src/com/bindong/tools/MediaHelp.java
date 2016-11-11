package com.bindong.tools;

import java.io.File;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class MediaHelp extends Frame {

	private static final long serialVersionUID = 1L;
	boolean isStop = true;// controler le fil de music
	boolean hasStop = true;// controler l'etat de fil 

	String filepath;
	String filename;
	AudioInputStream audioInputStream;// flux de fichier
	AudioFormat audioFormat;// format de fichier
	SourceDataLine sourceDataLine;// 输出设备

	List list;// la liste de fichier
	Label labelfilepath;
	Label labelfilename;

	public MediaHelp() {
		// configurer les parametre de la fenetre
		setLayout(new BorderLayout());
		setTitle("MusicPlayer");
		setSize(350, 370);
		setLocation(350, 150);

		// etablit les menubar
		MenuBar menubar = new MenuBar();
		Menu menufile = new Menu("File");
		MenuItem menuopen = new MenuItem("Open", new MenuShortcut(KeyEvent.VK_O));
		menufile.add(menuopen);
		menufile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open();
			}
		});
		menubar.add(menufile);
		setMenuBar(menubar);

		
		list = new List(10);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2) {
					
					filename = list.getSelectedItem();
					play();
				}
			}
		});
		add(list, "Center");

		// afficher les informations de music
		Panel panel = new Panel(new GridLayout(2, 1));
		labelfilepath = new Label("Play Dir:");
		labelfilename = new Label("Play File:");
		panel.add(labelfilepath);
		panel.add(labelfilename);
		add(panel, "North");

		// enregistrer l'operation pour fermer la fenetre
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setVisible(true);
	}

	
	private void open() {
		FileDialog dialog = new FileDialog(this, "Open", 0);
		dialog.setVisible(true);
		filepath = dialog.getDirectory();
		if (filepath != null) {
			labelfilepath.setText("Play Dir:" + filepath);

			
			list.removeAll();
			File filedir = new File(filepath);
			File[] filelist = filedir.listFiles();
			for (File file : filelist) {
				String filename = file.getName().toLowerCase();
				if (filename.endsWith(".mp3") || filename.endsWith(".wav")) {
					list.add(filename);
				}
			}
		}
	}

	
	private void play() {
		try {
			isStop = true;// attendre pour arreter de jouer la musique
			
			System.out.print("开始播放：" + filename);
			while (!hasStop) {
				System.out.print(".");
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			System.out.println("");
			File file = new File(filepath + filename);
			labelfilename.setText("Play File:" + filename);
			// obtenir le flux de fichier entree
			audioInputStream = AudioSystem.getAudioInputStream(file);
			audioFormat = audioInputStream.getFormat();
			// convertir mp3 code
			if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
				audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
						audioFormat.getSampleRate(), 16,
						audioFormat.getChannels(),
						audioFormat.getChannels() * 2,
						audioFormat.getSampleRate(), false);
				audioInputStream = AudioSystem.getAudioInputStream(audioFormat,
						audioInputStream);
			}

			
			DataLine.Info dataLineInfo = new DataLine.Info(
					SourceDataLine.class, audioFormat,
					AudioSystem.NOT_SPECIFIED);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();

			// le fil individuel pour jouer la musique
			isStop = false;
			Thread playThread = new Thread(new PlayThread());
			playThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		new MediaHelp();
	}

	
	class PlayThread extends Thread {
		byte tempBuffer[] = new byte[320];

		public void run() {
			try {
				int cnt;
				hasStop = false;
				// lire les donees pour buffer
				while ((cnt = audioInputStream.read(tempBuffer, 0,
						tempBuffer.length)) != -1) {
					if (isStop)
						break;
					if (cnt > 0) {
						// ecrire les buffer donnees
						sourceDataLine.write(tempBuffer, 0, cnt);
					}
				}
				
				sourceDataLine.drain();
				sourceDataLine.close();
				hasStop = true;
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
}
