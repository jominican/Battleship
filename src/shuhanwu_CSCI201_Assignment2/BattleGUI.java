package shuhanwu_CSCI201_Assignment2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BattleGUI extends JFrame{
	
	private static JLabel [][] boardLabel;
	private static JLabel [][] boardLabel2;
	private JLabel playerLabel;
	private JLabel computerLabel;
	private JLabel logLabel;
	private JButton selectButton;
	private JLabel selectedFileLabel;
	private JButton startButton;
	private String logText;
	private String name;
	private int readya, readyb, readyc, readyd, count;
	private boolean [][] labelTrue;
	private boolean [][] labelTrue2;
	private boolean [][] labelTrue3;
	private int hitCount, hitCount2;
	private String fileName;
	private JPanel southPanel;
	private int aship, aship1, aship2, aship3, aship4, ashipy, aship1y, aship2y, aship3y, aship4y;
	private int bship, bship1, bship2, bship3, bshipy, bship1y, bship2y, bship3y;
	private int cship, cship1, cship2, cshipy, cship1y, cship2y;
	private int d1ship, d1ship1, d1shipy, d1ship1y;
	private int d2ship, d2ship1, d2shipy, d2ship1y;
	private String p1, c1;
	private char [][] board;
	private Integer[] arr;
	private JTextArea textArea;
	private int firstDigit, secondDigit;
	private ImageIcon question, aimage, bimage, cimage, dimage, miss, hit;
	private GridBagConstraints gbc;
	//private MouseListener ml; 


	
	public BattleGUI(){
		super("Battleship");
		setSize(950, 600);
		setLocation(300, 300);
		
		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel centerPanel2 = new JPanel();
		JPanel centerPanel3 = new JPanel();
		southPanel = new JPanel();
		southPanel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		readya = readyb = readyc = readyd = 0;
		
		question = new ImageIcon("Q.png");
		aimage = new ImageIcon("A.png");
		bimage = new ImageIcon("B.png");
		cimage = new ImageIcon("C.png");
		dimage = new ImageIcon("D.png");
		miss = new ImageIcon("M.png");
		hit = new ImageIcon("hit.png");
	
		playerLabel = new JLabel("PLAYER                                                                                               ");
		computerLabel = new JLabel("COMPUTER");
		selectedFileLabel = new JLabel("You have not selected a file");
		
		northPanel.add(playerLabel);
		northPanel.add(computerLabel);
			  		
		board = new char [11][11];
		d1ship = d1ship1 = d1shipy = d1ship1y = d2ship = d2ship1 = d2shipy = d2ship1y = 0; 
		p1 = new String("N/A");
		c1 = new String("N/A");
		
		count = 0;
		arr = new Integer[100];
	    for (int i = 0; i < arr.length; i++) {
	        arr[i] = i;
	    }
	    Collections.shuffle(Arrays.asList(arr));
	    //System.out.println(Arrays.toString(arr));

		name = new String("Aircraft Carrier");
		boardLabel = new JLabel[11][11];
		labelTrue = new boolean[11][11];
		labelTrue2 = new boolean[11][11];
		labelTrue3 = new boolean[11][11];
		hitCount = 0;
		hitCount2 = 0;
		
		
		
		//menubar
		JMenuBar jmb = new JMenuBar();
		JMenu infoMenu = new JMenu("Info");
		JMenuItem howtoMenuItem = new JMenuItem("How to");
		howtoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		infoMenu.add(howtoMenuItem);	
		infoMenu.add(aboutMenuItem);
		jmb.add(infoMenu);
		setJMenuBar(jmb);
		
		aboutMenuItem.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				JDialog jd = new JDialog();
				jd.setLayout(new BoxLayout(jd.getContentPane(), BoxLayout.Y_AXIS));
				jd.setSize(200,100);
				jd.setLocation(400,400);
				jd.setTitle("About");
				JLabel jl1 = new JLabel("Made by Joseph Wu");
				JLabel jl2 = new JLabel("Created on 3/1. Assignment 3");
				jd.add(jl1);
				jd.add(jl2);
				jd.setModal(true);
				jd.setVisible(true);				
			}
		});
		
		howtoMenuItem.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				JDialog jd = new JDialog();
				jd.setLayout(new BoxLayout(jd.getContentPane(), BoxLayout.Y_AXIS));
				jd.setSize(500,500);
				jd.setLocation(400,400);
				jd.setTitle("Batleship Instructions");
				textArea = new JTextArea();
				try{
					BufferedReader in = new BufferedReader(new FileReader("instruction.txt"));
					String line = in.readLine();
					while(line != null){
					  textArea.append(line + "\n");
					  line = in.readLine();
					}
				}catch(IOException ioe) {
					//System.out.println("Unable to find the instruction file");
				}		

				textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
				textArea.setLineWrap(true);
				
				JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				jd.add(scrollPane);
				jd.setModal(true);
				jd.setVisible(true);				
			}
		});
		
		boardLabel[10][1] = new JLabel("1         ");
		boardLabel[10][2] = new JLabel("2         ");
		boardLabel[10][3] = new JLabel("3         ");
		boardLabel[10][4] = new JLabel("4         ");
		boardLabel[10][5] = new JLabel("5         ");
		boardLabel[10][6] = new JLabel("6         ");
		boardLabel[10][7] = new JLabel("7         ");
		boardLabel[10][8] = new JLabel("8         ");
		boardLabel[10][9] = new JLabel("9         ");
		boardLabel[10][10] = new JLabel("10         ");
		
		boardLabel[0][0] = new JLabel("A         ");
		boardLabel[1][0] = new JLabel("B         ");
		boardLabel[2][0] = new JLabel("C         ");
		boardLabel[3][0] = new JLabel("D         ");
		boardLabel[4][0] = new JLabel("E         ");
		boardLabel[5][0] = new JLabel("F         ");
		boardLabel[6][0] = new JLabel("G         ");
		boardLabel[7][0] = new JLabel("H         ");
		boardLabel[8][0] = new JLabel("I         ");
		boardLabel[9][0] = new JLabel("J         ");
		boardLabel[10][0] = new JLabel("            ");
		
		boardLabel2 = new JLabel[11][11];
		
		boardLabel2[10][1] = new JLabel("1          ");
		boardLabel2[10][2] = new JLabel("2          ");
		boardLabel2[10][3] = new JLabel("3          ");
		boardLabel2[10][4] = new JLabel("4          ");
		boardLabel2[10][5] = new JLabel("5          ");
		boardLabel2[10][6] = new JLabel("6          ");
		boardLabel2[10][7] = new JLabel("7          ");
		boardLabel2[10][8] = new JLabel("8          ");
		boardLabel2[10][9] = new JLabel("9          ");
		boardLabel2[10][10] = new JLabel("10         ");
		
		boardLabel2[0][0] = new JLabel("A          ");
		boardLabel2[1][0] = new JLabel("B          ");
		boardLabel2[2][0] = new JLabel("C          ");
		boardLabel2[3][0] = new JLabel("D          ");
		boardLabel2[4][0] = new JLabel("E          ");
		boardLabel2[5][0] = new JLabel("F          ");
		boardLabel2[6][0] = new JLabel("G          ");
		boardLabel2[7][0] = new JLabel("H          ");
		boardLabel2[8][0] = new JLabel("I          ");
		boardLabel2[9][0] = new JLabel("J          ");
		boardLabel2[10][0] = new JLabel("             ");
		
		GridLayout layout = new GridLayout(11, 11);
		centerPanel.setLayout(layout);
		

		for ( int xpos =0; xpos<10; xpos++){
			for( int ypos=1; ypos<11; ypos++){
				boardLabel[xpos][ypos] = new JLabel(question);
			}
		}

		for ( int i=0; i<11; i++){
			for(int j=0; j<11; j++){
				centerPanel.add(boardLabel[i][j]);
			}
		}
		
		GridLayout layout2 = new GridLayout(11,11);
		centerPanel2.setLayout(layout2);
				
		for ( int xpos =0; xpos<10; xpos++){
			for( int ypos=1; ypos<11; ypos++){
				final int a = xpos;
				final int b = ypos;
				boardLabel2[xpos][ypos] = new JLabel(question);
				if(!(readya + readyb + readyc + readyd == 5)){
					boardLabel2[xpos][ypos].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					boardLabel2[xpos][ypos].addMouseListener(new MouseListener()
					{
					    public void mouseReleased(MouseEvent e)
					    {
	
					    }
	
						public void mouseClicked(MouseEvent e) {
							String letter = "";
							String number = "";
							if(a == 0) letter = "A";
							if(a == 1) letter = "B";
							if(a == 2) letter = "C";
							if(a == 3) letter = "D";
							if(a == 4) letter = "E";
							if(a == 5) letter = "F";
							if(a == 6) letter = "G";
							if(a == 7) letter = "H";
							if(a == 8) letter = "I";
							if(a == 9) letter = "K";
							
							if(b == 1) number = "1";
							if(b == 2) number = "2";
							if(b == 3) number = "3";
							if(b == 4) number = "4";
							if(b == 5) number = "5";
							if(b == 6) number = "6";
							if(b == 7) number = "7";
							if(b == 8) number = "8";
							if(b == 9) number = "9";
							if(b == 10) number = "10";
	
							if(!(readya + readyb + readyc + readyd == 5)){
								if(labelTrue[a][b])
									clearShip(a, b);
								else
									new AddShipGUI(letter, number, a,b);
							}
						
						}
	
						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
	
						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
	
						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
				}
			}
		}

		for ( int i=0; i<11; i++){
			for(int j=0; j<11; j++){
				centerPanel2.add(boardLabel2[i][j]);
			}
		}
		
		JSeparator x = new JSeparator(SwingConstants.VERTICAL);
        x.setPreferredSize(new Dimension(3,500));
        centerPanel3.add(x);
        
        logText = new String("You are in edit mode, click to place your ships");
		logLabel = new JLabel("Log: " + logText);
		gbc.gridx = 0;
		gbc.gridy = 0;
        southPanel.add(logLabel, gbc);
		selectButton = new JButton("Select File...");
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 10;
        southPanel.add(selectButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        southPanel.add(selectedFileLabel, gbc);
		startButton = new JButton("Start");
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.ipadx = 10;
        southPanel.add(startButton, gbc);
        
        
        
		selectButton.addActionListener( new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		    	JFileChooser fc = new JFileChooser(new File (System.getProperty("user.dir")));
		    	//fc.setCurrentDirectory(System.getProperty("user.home") + System.getProperty("file.separator")+ "Music");
		    	FileNameExtensionFilter filter = new FileNameExtensionFilter(
		    		     "Battle Files (*.battle)", "battle");
		    		fc.setFileFilter(filter);
		        //Handle open button action.
		    	try {
			        if (e.getSource() == selectButton) {
			            int returnVal = fc.showOpenDialog(BattleGUI.this);
			            fileName = fc.getSelectedFile().getName();
			            selectedFileLabel.setText("File: " + fileName);
			            readFile(fileName);
	
			 
			            if (returnVal == JFileChooser.APPROVE_OPTION) {
			                //File file = fc.getSelectedFile();
			                //This is where a real application would open the file. Do stuff if you can open the file
			            } else {
			            	//do stuff when you cant open the file
			            }
			 
			        //Handle save button action.
			        }
		    	}catch(NullPointerException npe){
		    		
		    	}
		    }
		});
		
        
		startButton.setEnabled(false);


		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.EAST);
		add(centerPanel2, BorderLayout.WEST);
		add(centerPanel3, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		createActions();
	}
	
	
	private void clearShip(int a, int b){

		if(labelTrue[a][b]){ 
			if((a == aship || a == aship1 || a == aship2 || a == aship3 || a == aship4) && (b == ashipy || b == aship1y || b == aship2y || b == ashipy || b == aship4y)){
				boardLabel2[aship][ashipy].setIcon(question);
				boardLabel2[aship1][aship1y].setIcon(question);
				boardLabel2[aship2][aship2y].setIcon(question);
				boardLabel2[aship3][aship3y].setIcon(question);
				boardLabel2[aship4][aship4y].setIcon(question);
				labelTrue[aship][ashipy] = false;
				labelTrue[aship1][aship1y]= false;
				labelTrue[aship2][aship2y]= false;
				labelTrue[aship3][aship3y]= false;
				labelTrue[aship4][aship4y]= false;
				readya--;
			}		
			if((a == bship || b == aship1 || b == aship2 || b == aship3) && (b == bshipy || b == bship1y || b == bship2y || b == bshipy )){
				boardLabel2[bship][bshipy].setIcon(question);
				boardLabel2[bship1][bship1y].setIcon(question);
				boardLabel2[bship2][bship2y].setIcon(question);
				boardLabel2[bship3][bship3y].setIcon(question);
				labelTrue[bship][bshipy] = false;
				labelTrue[bship1][bship1y]= false;
				labelTrue[bship2][bship2y]= false;
				labelTrue[bship3][bship3y]= false;
				readyb--;
			}
			if((a == cship || a == cship1 || a == cship2) && (b == cshipy || b == cship1y || b == cship2y )){
				boardLabel2[cship][cshipy].setIcon(question);
				boardLabel2[cship1][cship1y].setIcon(question);
				boardLabel2[cship2][cship2y].setIcon(question);
				labelTrue[cship][cshipy] = false;
				labelTrue[cship1][cship1y]= false;
				labelTrue[cship2][cship2y]= false;
				readyc--;
			}
			if((a == d1ship || a == d1ship1) && (b == d1shipy || b == d1ship1y )){
				boardLabel2[d1ship][d1shipy].setIcon(question);
				boardLabel2[d1ship1][d1ship1y].setIcon(question);
				labelTrue[d1ship][d1shipy] = false;
				labelTrue[d1ship1][d1ship1y]= false;
				readyd--;
			}
			if((a == d2ship || a == d2ship1) && (b == d2shipy || b == d2ship1y )){
				boardLabel2[d2ship][d2shipy].setIcon(question);
				boardLabel2[d2ship1][d2ship1y].setIcon(question);
				labelTrue[d2ship][d2shipy] = false;
				labelTrue[d2ship1][d2ship1y]= false;
				readyd--;
			}
		}
		System.out.println("a has " + readya);
		System.out.println("b has " + readyb);
		System.out.println("c has " + readyc);
		System.out.println("d has " + readyd);
	}
	
	public static void setPlayer(String text, int n){
		//playerLabel[n].setText(text);
	}
	
	public static void setBoard(String text, int i, int j){
		boardLabel[i][j].setText(text);
	}
	
	private void createActions(){
		startButton.addActionListener (new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				logLabel.setText("Player:N/A Computer:N/A");
				southPanel.remove(startButton);
				southPanel.remove(selectButton);
				southPanel.invalidate();
				startGame();
			}
		});
	}
	
	private void readFile(String fileName){
		String line = "";
		List<String> name = new LinkedList<String>();
		for (int i=0; i<10; i++){
			name.add(i, "");
		}

		String [] grid = new String [10];
		int [] ranking = new int [10];
		Arrays.fill(ranking, 101);
		int counter = 0;
		int a,b,c,d, ac, bc, cc, dc, x, xa, xb, xc, xd, xd1, xd2, xd3, xd4, yd1, yd2, yd3, yd4;
		a = b = c = d = ac = bc = cc = dc = x = xa = xb = xc = xd = xd1 = xd2 = xd3 = xd4 = yd1 = yd2 = yd3 = yd4 = 0;
		//System.out.println("What file would you like to open?");
		Scanner scan = new Scanner(System.in);
		try{
			String inFileName = fileName;
			FileReader fr = new FileReader(inFileName);
			BufferedReader br = new BufferedReader(fr);	
			try{
				while ( counter <10  && line != null){
					line = br.readLine();
					//System.out.println(line);
					if (line.length() > 10){
						System.out.println("The format of your file is incorrect! haha");
						System.exit(1);
					}
					grid[counter] = line;
					for (int i=0; i<line.length(); i++){
						board[counter][i+1] = line.charAt(i);
						if(line.charAt(i) != 'A' && line.charAt(i) != 'B' && line.charAt(i) != 'C' && line.charAt(i) != 'D' && line.charAt(i) != 'X'){
							System.out.println("One of your characters is invalid");
							System.exit(1);
						}
					}
					counter++;
				}

			} catch (NullPointerException npe){

			}
			br.close();
			fr.close();
			
			for (int j=0; j<10; j++){
				for( int k=1; k<11; k++){
					if(board[j][k] == 'A') ac++;
					if(board[j][k] == 'B') bc++;
					if(board[j][k] == 'C') cc++;
					if(board[j][k] == 'D') dc++;
					if(board[j][k] == 'X') x++;
				}
			}
			
			if(ac != 5 || bc != 4 || cc != 3 || dc != 4 || x != 84){
				System.out.println("The number of battleships is incorrect! hehe");
				System.exit(1);
			}
			
		} catch (FileNotFoundException fnfe){
			System.out.println("The format of your file is incorrect! hoho ");
			System.exit(1);
		} catch (IOException ioe){
			System.out.println("The format of your file is incorrect! huhu");
			System.exit(1);
		} catch (ArrayIndexOutOfBoundsException aiobe){
			System.out.println("The format of your file is incorrect! hihi");
			System.exit(1);
		}
		checkStart();
		//System.out.println("file read");
	}
	
	private void checkStart(){
		String substring = selectedFileLabel.getText().substring(selectedFileLabel.getText().length() - 7);
		if((readya + readyb + readyc + readyd == 5) && substring.equals(".battle")){
			startButton.setEnabled(true);
			logLabel.setText("Log: Player:" + p1 + " Computer:" + c1);

		}
	}
	
	private void checkPlayer(int q, int r){
		System.out.println(q + " " + (r+1));
		if(labelTrue[q][r+1]){
			//System.out.println(labelTrue[q][r+1]);
			hitCount2++;
			boardLabel2[q][r+1].setIcon(hit);
		}
	}
	
	
	private void checkWinner(){
//		System.out.println(hitCount);
//		System.out.println(hitCount2);
		if(hitCount == 16){
			JOptionPane.showMessageDialog(null, "You won!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			super.dispose();
			new BattleGUI();
			//reset();
		}
		if(hitCount2 == 16){
			JOptionPane.showMessageDialog(null, "You lose!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			super.dispose();
			new BattleGUI();

			//reset();
		}
	}
	
	private void chooseShip(int q, int r){
		String letter = "";
		String number = "";
		if(q == 0) letter = "A";
		if(q == 1) letter = "B";
		if(q == 2) letter = "C";
		if(q == 3) letter = "D";
		if(q == 4) letter = "E";
		if(q == 5) letter = "F";
		if(q == 6) letter = "G";
		if(q == 7) letter = "H";
		if(q == 8) letter = "I";
		if(q == 9) letter = "J";
		
		if(r == 1) number = "1";
		if(r == 2) number = "2";
		if(r == 3) number = "3";
		if(r == 4) number = "4";
		if(r == 5) number = "5";
		if(r == 6) number = "6";
		if(r == 7) number = "7";
		if(r == 8) number = "8";
		if(r == 9) number = "9";
		if(r == 10) number = "10";
		p1 = letter + number;
		
		if (labelTrue2[q][r] == true){
			logLabel.setText("You have already hit this spot");
		}
		else{
			if (board[q][r] == 'A' && !labelTrue2[q][r]){
				boardLabel[q][r].setIcon(aimage);;
				hitCount++;
			}
			else if(board[q][r] == 'B' && !labelTrue2[q][r]){
				boardLabel[q][r].setIcon(bimage);;
				hitCount++;
			}
			else if(board[q][r] == 'C' && !labelTrue2[q][r]){
				boardLabel[q][r].setIcon(cimage);;
				hitCount++;
			}
			else if(board[q][r] == 'D' && !labelTrue2[q][r]){
				boardLabel[q][r].setIcon(dimage);;
				hitCount++;
			}
			else{
				boardLabel[q][r].setIcon(miss);
			}
			labelTrue2[q][r] = true;
			computerChoose(count);
			count++;
			logLabel.setText("Log: Player:" + p1 + " Computer:" + c1);
		}
	}
	
	private void computerChoose(int index){
		String letter = "";
		String number = "";
		int length = String.valueOf(arr[index]).length();
		//fix this
		
		if(length == 1){
			firstDigit = 0;
			secondDigit = arr[index];
		}
		else{
			firstDigit =  Integer.parseInt(Integer.toString(arr[index]).substring(0, 1));
			secondDigit =  arr[index] % 10;
		}
		if(firstDigit == 0) letter = "A";
		if(firstDigit == 1) letter = "B";
		if(firstDigit == 2) letter = "C";
		if(firstDigit == 3) letter = "D";
		if(firstDigit == 4) letter = "E";
		if(firstDigit == 5) letter = "F";
		if(firstDigit == 6) letter = "G";
		if(firstDigit == 7) letter = "H";
		if(firstDigit == 8) letter = "I";
		if(firstDigit == 9) letter = "J";
		
		if(secondDigit == 0) number = "1";
		if(secondDigit == 1) number = "2";
		if(secondDigit == 2) number = "3";
		if(secondDigit == 3) number = "4";
		if(secondDigit == 4) number = "5";
		if(secondDigit == 5) number = "6";
		if(secondDigit == 6) number = "7";
		if(secondDigit == 7) number = "8";
		if(secondDigit == 8) number = "9";
		if(secondDigit == 9) number = "10";
		c1 = letter + number;
		//System.out.println(firstDigit + "" + secondDigit + " = " + c1 + " has length of " + length);
		checkPlayer(firstDigit, secondDigit);
		checkWinner();
	} 
	
	private void startGame(){
		for ( int xpos =0; xpos<10; xpos++){
			for( int ypos=1; ypos<11; ypos++){
				final int a = xpos;
				final int b = ypos;
					//System.out.println(xpos + " " + ypos + ": " + labelTrue[xpos][ypos]);
					boardLabel[xpos][ypos].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					boardLabel[xpos][ypos].addMouseListener(new MouseListener()
					{
					    public void mouseReleased(MouseEvent e)
					    {
	
					    }
	
						public void mouseClicked(MouseEvent e) {
							String letter = "";
							String number = "";
							if(a == 0) letter = "A";
							if(a == 1) letter = "B";
							if(a == 2) letter = "C";
							if(a == 3) letter = "D";
							if(a == 4) letter = "E";
							if(a == 5) letter = "F";
							if(a == 6) letter = "G";
							if(a == 7) letter = "H";
							if(a == 8) letter = "I";
							if(a == 9) letter = "J";
							
							if(b == 1) number = "1";
							if(b == 2) number = "2";
							if(b == 3) number = "3";
							if(b == 4) number = "4";
							if(b == 5) number = "5";
							if(b == 6) number = "6";
							if(b == 7) number = "7";
							if(b == 8) number = "8";
							if(b == 9) number = "9";
							if(b == 10) number = "10";
	
							chooseShip(a, b);
							//System.out.println(a + " " + b);
						
						}
	
						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
	
						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
	
						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
				}
			}
		}
	

	
	private class AddShipGUI extends JFrame{
		
		private static final long serialVersionUID = 1;
		private JRadioButton north, south, west, east; 
		private JComboBox<String> shipName;
		private JButton placeShipButton;
		private int size, i, j, state;

		
		public AddShipGUI(String location1, String location2, int a, int b){
			super("Select ship at " + location1 + location2 );
			setSize(250, 250);
			setLocation(400, 400);
			
			String [] types = {"Aircraft Carrier", "Battleship", "Cruiser", "Destroyers"};
			shipName = new JComboBox<String>(types);
			state = 0;	
			i = a;
			j = b;
			size = 5;
			ButtonGroup group = new ButtonGroup();

			//System.out.println(i + " " + j);
			
			north = new JRadioButton("North", true);
			south = new JRadioButton("South");
			west = new JRadioButton("West");
			east = new JRadioButton("East");
			
			placeShipButton = new JButton("Place Ship");
			
			JPanel mainPanel = new JPanel();
			JPanel jp = new JPanel();
			jp.setLayout(new BorderLayout());
			jp.add(placeShipButton, BorderLayout.SOUTH);
			
			JPanel grid = new JPanel();
			grid.setLayout(new GridLayout(2,2));
			group.add(north);
			group.add(south);
			group.add(west);
			group.add(east);
			
			grid.add(north);
			grid.add(south);
			grid.add(west);
			grid.add(east);
			
			
			JLabel label1 = new JLabel("Select Ship: ");
			mainPanel.add(label1);
			mainPanel.add(shipName);
			
			add(mainPanel, BorderLayout.NORTH);
			add(jp, BorderLayout.SOUTH);
			add(grid, BorderLayout.CENTER);

			createActions();
			dispose();
			//jd.setModal(true);
			//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setVisible(true);
		}
		
		private void createActions(){
		
			shipName.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent ie){
					if(ie.getStateChange() == ItemEvent.SELECTED){
						name = (String)ie.getItem();
						if(name == "Aircraft Carrier") size = 5;
						if(name == "Battleship") size = 4;
						if(name == "Cruiser") size = 3;
						if(name == "Destroyers") size = 2;
						//setShip();
					}
				}
			});
			north.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					state = 0;
					//setShip();
				}
			});
			
			south.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					state = 1;
					//setShip();

				}
			});
			west.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					state = 2;
					//setShip();

				}
			});
			east.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					state = 3;
					//setShip();
				}
			});
			
			placeShipButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					setShip();
					checkStart();
					dispose();
				}
				
			});
		}
		
		private void checkStart(){
			String substring = selectedFileLabel.getText().substring(selectedFileLabel.getText().length() - 7);
			if((readya + readyb + readyc + readyd == 5) && substring.equals(".battle")){
				startButton.setEnabled(true);
				logLabel.setText("Log: Player:" + p1 + " Computer:" + c1);

			}
		}
		
		private void setShip(){
			//change the labels on the board, etc	
			//System.out.println(state + name);

					if (size == 5 && readya == 1) JOptionPane.showMessageDialog(null, "You have already placed an Aircraft Carrier", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
					else if (size == 4 && readyb == 1) JOptionPane.showMessageDialog(null, "You have already placed a Battleship", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
					else if (size == 3 && readyc == 1) JOptionPane.showMessageDialog(null, "You have already placed a Cruiser", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
					else if (size == 2 && readyd == 2) JOptionPane.showMessageDialog(null, "You have already placed 2 Destroyers", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
					else{
						try{
							if(size == 5){
								if(state == 0){
									if(labelTrue[i][j] || labelTrue[i-1][j] || labelTrue[i-2][j] || labelTrue[i-3][j] || labelTrue[i-4][j]){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(aimage);
										boardLabel2[i-1][j].setIcon(aimage);
										boardLabel2[i-2][j].setIcon(aimage);
										boardLabel2[i-3][j].setIcon(aimage);
										boardLabel2[i-4][j].setIcon(aimage);
										labelTrue[i][j] = true;
										labelTrue[i-1][j] = true;
										labelTrue[i-2][j] = true;
										labelTrue[i-3][j] = true;
										labelTrue[i-4][j] = true;
										//aship = new Point[5];
										aship = i;
										aship1 = i-1;
										aship2 = i-2;
										aship3 = i-3;
										aship4 = i-4;
										ashipy = aship1y = aship2y = aship3y = aship4y = j;
										readya++;

									}
								}
								else if(state == 1){
									if(labelTrue[i][j] || labelTrue[i+1][j] || labelTrue[i+2][j] || labelTrue[i+3][j] || labelTrue[i+4][j] || (i+4 == 10)){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(aimage);
										boardLabel2[i+1][j].setIcon(aimage);
										boardLabel2[i+2][j].setIcon(aimage);
										boardLabel2[i+3][j].setIcon(aimage);
										boardLabel2[i+4][j].setIcon(aimage);
										labelTrue[i][j] = true;
										labelTrue[i+1][j] = true;
										labelTrue[i+2][j] = true;
										labelTrue[i+3][j] = true;
										labelTrue[i+4][j] = true;
										readya++;
										aship = i;
										aship1 = i+1;
										aship2 = i+2;
										aship3 = i+3;
										aship4 = i+4;
										ashipy = aship1y = aship2y = aship3y = aship4y = j;
									}
								}
								else if(state == 2){
									if(labelTrue[i][j] || labelTrue[i][j-1] || labelTrue[i][j-2] || labelTrue[i][j-3] || labelTrue[i][j-4] || (j-4 == 0)){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(aimage);;
										boardLabel2[i][j-1].setIcon(aimage);;
										boardLabel2[i][j-2].setIcon(aimage);;
										boardLabel2[i][j-3].setIcon(aimage);;
										boardLabel2[i][j-4].setIcon(aimage);;
										labelTrue[i][j] = true;
										labelTrue[i][j-1] = true;
										labelTrue[i][j-2] = true;
										labelTrue[i][j-3] = true;
										labelTrue[i][j-4] = true;
										readya++;
										ashipy = j;
										aship1y = j-1;
										aship2y = j-2;
										aship3y = j-3;
										aship4y = j-4;
										aship = aship1 = aship2 = aship3 = aship4 = i;
									}
								}
								else{
									if(labelTrue[i][j] || labelTrue[i][j+1] || labelTrue[i][j+2] || labelTrue[i][j+3] || labelTrue[i][j+4]){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(aimage);;
										boardLabel2[i][j+1].setIcon(aimage);;
										boardLabel2[i][j+2].setIcon(aimage);;
										boardLabel2[i][j+3].setIcon(aimage);;
										boardLabel2[i][j+4].setIcon(aimage);;
										labelTrue[i][j] = true;
										labelTrue[i][j+1] = true;
										labelTrue[i][j+2] = true;
										labelTrue[i][j+3] = true;
										labelTrue[i][j+4] = true;
										readya++;
										ashipy = j;
										aship1y = j+1;
										aship2y = j+2;
										aship3y = j+3;
										aship4y = j+4;
										aship = aship1 = aship2 = aship3 = aship4 = i;
									}
								}
							}
							if(size == 4){
								if(state == 0){
									if(labelTrue[i][j] || labelTrue[i-1][j] || labelTrue[i-2][j] || labelTrue[i-3][j]){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(bimage);;
										boardLabel2[i-1][j].setIcon(bimage);;
										boardLabel2[i-2][j].setIcon(bimage);;
										boardLabel2[i-3][j].setIcon(bimage);;
										labelTrue[i][j] = true;
										labelTrue[i-1][j] = true;
										labelTrue[i-2][j] = true;
										labelTrue[i-3][j] = true;
										readyb++;
										bship = i;
										bship1 = i-1;
										bship2 = i-2;
										bship3 = i-3;
										bshipy = bship1y = bship2y = bship3y = j;
									}
								}
								else if(state == 1){
									if(labelTrue[i][j] || labelTrue[i+1][j] || labelTrue[i+2][j] || labelTrue[i+3][j] || (i+3 == 10)){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(bimage);;
										boardLabel2[i+1][j].setIcon(bimage);;
										boardLabel2[i+2][j].setIcon(bimage);;
										boardLabel2[i+3][j].setIcon(bimage);;
										labelTrue[i][j] = true;
										labelTrue[i+1][j] = true;
										labelTrue[i+2][j] = true;
										labelTrue[i+3][j] = true;
										readyb++;
										bship = i;
										bship1 = i+1;
										bship2 = i+2;
										bship3 = i+3;
										bshipy = bship1y = bship2y = bship3y = j;
									}
								}
								else if(state == 2){
									if(labelTrue[i][j] || labelTrue[i][j-1] || labelTrue[i][j-2] || labelTrue[i][j-3] || (j-3 == 0)){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(bimage);;
										boardLabel2[i][j-1].setIcon(bimage);;
										boardLabel2[i][j-2].setIcon(bimage);;
										boardLabel2[i][j-3].setIcon(bimage);;
										labelTrue[i][j] = true;
										labelTrue[i][j-1] = true;
										labelTrue[i][j-2] = true;
										labelTrue[i][j-3] = true;
										readyb++;
										bshipy = j;
										bship1y = j-1;
										bship2y = j-2;
										bship3y = j-3;
										bship = bship1 = bship2 = bship3 = i;
									}
								}
								else{
									if(labelTrue[i][j] || labelTrue[i][j+1] || labelTrue[i][j+2] || labelTrue[i][j+3] ){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(bimage);;
										boardLabel2[i][j+1].setIcon(bimage);;
										boardLabel2[i][j+2].setIcon(bimage);;
										boardLabel2[i][j+3].setIcon(bimage);;
										labelTrue[i][j] = true;
										labelTrue[i][j+1] = true;
										labelTrue[i][j+2] = true;
										labelTrue[i][j+3] = true;
										readyb++;
										bshipy = j;
										bship1y = j+1;
										bship2y = j+2;
										bship3y = j+3;
										bship = bship1 = bship2 = bship3 = i;
									}
								}
							}
							if(size == 3){
								if(state == 0){
									if(labelTrue[i][j] || labelTrue[i-1][j] || labelTrue[i-2][j]){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(cimage);;
										boardLabel2[i-1][j].setIcon(cimage);;
										boardLabel2[i-2][j].setIcon(cimage);;
										labelTrue[i][j] = true;
										labelTrue[i-1][j] = true;
										labelTrue[i-2][j] = true;
										readyc++;
										cship = i;
										cship1 = i-1;
										cship2 = i-2;
										cshipy = cship1y = cship2y = j;
									}
								}
								else if(state == 1){
									if(labelTrue[i][j] || labelTrue[i+1][j] || labelTrue[i+2][j] || (i+2 == 10)){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(cimage);;
										boardLabel2[i+1][j].setIcon(cimage);;
										boardLabel2[i+2][j].setIcon(cimage);;
										labelTrue[i][j] = true;
										labelTrue[i+1][j] = true;
										labelTrue[i+2][j] = true;
										readyc++;
										cship = i;
										cship1 = i+1;
										cship2 = i+2;
										cshipy = cship1y = cship2y = j;
									}
								}
								else if(state == 2){
									if(labelTrue[i][j] || labelTrue[i][j-1] || labelTrue[i][j-2] || (j-2 == 0)){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(cimage);;
										boardLabel2[i][j-1].setIcon(cimage);;
										boardLabel2[i][j-2].setIcon(cimage);;
										labelTrue[i][j] = true;
										labelTrue[i][j-1] = true;
										labelTrue[i][j-2] = true;
										readyc++;
										cshipy = j;
										cship1y = j-1;
										cship2y = j-2;
										cship = cship1 = cship2 = i;
									}
								}
								else{
									if(labelTrue[i][j] || labelTrue[i][j+1] || labelTrue[i][j+2]){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(cimage);;
										boardLabel2[i][j+1].setIcon(cimage);;
										boardLabel2[i][j+2].setIcon(cimage);;
										labelTrue[i][j] = true;
										labelTrue[i][j+1] = true;
										labelTrue[i][j+2] = true;
										readyc++;
										cshipy = j;
										cship1y = j+1;
										cship2y = j+2;
										cship = cship1 = cship2 = i;
									}
								}
							}
							if(size == 2){
								if(state == 0){
									if(labelTrue[i][j] || labelTrue[i-1][j] ){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(dimage);;
										boardLabel2[i-1][j].setIcon(dimage);;
										labelTrue[i][j] = true;
										labelTrue[i-1][j] = true;
										if(readyd == 0){
											d1ship = i;
											d1ship1 = i-1;
											d1shipy = d1ship1y = j;
										}
										else{
											d2ship = i;
											d2ship1 = i-1;
											d2shipy = d1ship1y = j;
										}
										readyd++;
									}
								}
								else if(state == 1){
									if(labelTrue[i][j] || labelTrue[i+1][j] || (i+1 == 10)){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(dimage);;
										boardLabel2[i+1][j].setIcon(dimage);;
										labelTrue[i][j] = true;
										labelTrue[i+1][j] = true;
										if(readyd == 0){
											d1ship = i;
											d1ship1 = i+1;
											d1shipy = d1ship1y = j;
										}
										else{
											d2ship = i;
											d2ship1 = i+1;
											d2shipy = d1ship1y = j;
										}
										readyd++;
										
									}
								}
								else if(state == 2){
									if(labelTrue[i][j] || labelTrue[i][j-1] || (j-1 == 0)){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(dimage);;
										boardLabel2[i][j-1].setIcon(dimage);;
										labelTrue[i][j] = true;
										labelTrue[i][j-1] = true;
										if(readyd == 0){
											d1shipy = j;
											d1ship1y = j-1;
											d1ship = d1ship1 = i;
										}
										else{
											d2ship = j;
											d2ship1 = j-1;
											d2shipy = d1ship1y = i;
										}
										readyd++;
									}
								}
								else{
									if(labelTrue[i][j] || labelTrue[i][j+1] ){
										JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										boardLabel2[i][j].setIcon(dimage);;
										boardLabel2[i][j+1].setIcon(dimage);;
										labelTrue[i][j] = true;
										labelTrue[i][j+1] = true;
										if(readyd == 0){
											d1shipy = j;
											d1ship1y = j+1;
											d1ship = d1ship1 = i;
										}
										else{
											d2ship = j;
											d2ship1 = j+1;
											d2shipy = d1ship1y = i;
										}
										readyd++;
									}
								}
							}
					}catch (ArrayIndexOutOfBoundsException aiobe){
						JOptionPane.showMessageDialog(null, "You cannot place this ship at this coordinate", "Warning: " + "invalid ship placement", JOptionPane.INFORMATION_MESSAGE);
					}					
//					System.out.println("a has " + readya);
//					System.out.println("b has " + readyb);
//					System.out.println("c has " + readyc);
//					System.out.println("d has " + readyd);

			}
		}
	}
	
}
