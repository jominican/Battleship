package shuhanwu_CSCI201_Assignment2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EndGUI extends JFrame {
	
	private static JLabel [] playerLabel;

	public EndGUI(String player, String name){
		super("Battleship");
		String line;
		int counter = 0;
		setSize(600, 400);
		setLocation(300, 300);
		JLabel highscore = new JLabel("Thanks for playing, " + player + "!");
		
	    Box box = Box.createVerticalBox();
		playerLabel = new JLabel[10];

	    box.add(highscore);
				
		JLabel display[] = new JLabel[11];
		try{
			FileReader fr = new FileReader(name);
			BufferedReader br = new BufferedReader(fr);
			while ( counter <11){
				line = br.readLine();
				System.out.println(line);
				display[counter] = new JLabel (line);
				counter++;
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException fnfe){
			System.out.println("FileNotFoundException: " + fnfe.getMessage());
		} catch (IOException ioe){
			System.out.println("IOException: " + ioe.getMessage());
		}

		for ( int i=0; i<11; i++){
			box.add(display[i]);
		}
		
		add(box, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void setPlayer(String text1, String text2, int text3, int n){
		playerLabel[n].setText(text1+text2+" - "+text3);
	}
}
