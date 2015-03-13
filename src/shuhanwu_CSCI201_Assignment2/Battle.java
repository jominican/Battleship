package shuhanwu_CSCI201_Assignment2;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.lang.Object;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;






import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Battle{
		
	public static boolean checkRow(char [][] b, int r, int i, int j){
		//System.out.println("Checking row " + r + " at index " + i); 
		boolean flag = false;
		int count = 0;
		if (j == 5){
			while (b[r][i] == 'A' ){
				i++;
				count++;
				if( i == 10) break;
			}
			if (count >= j) flag = true;
		}
		else if (j == 4){
			while (b[r][i] == 'B' ){
				i++;
				count++;
				if( i == 10) break;
			}
			if (count >= j) flag = true;
		}
		else if (j == 3){
			while (b[r][i] == 'C'){
				i++;
				count++;
				if( i == 10) break;
			}
			if (count >= j) flag = true;
		}
		else{
			while (b[r][i] == 'D' ){
				i++;
				count++;
				if( i == 10) break;
			}
			if (count >= j) flag = true;
		}
		//System.out.println(flag);
		return flag;
	}
	
	public static boolean checkCol(char [][] b, int r, int i, int j){
		//System.out.println("Checking column " + r + " at index " + i);
		boolean flag = false;
		int count = 0;
		if (j == 5){
			while (b[r][i] == 'A'){
				r++;
				count++;
				if( r == 10) break;
			}
			if (count == j) flag = true;
		}
		else if (j == 4){
			while (b[r][i] == 'B' ){
				r++;
				count++;
				if( r == 10) break;
			}
			if (count == j) flag = true;
		}
		else if (j == 3){
			while (b[r][i] == 'C'){
				r++;
				count++;
				if( r == 10) break;
			}
			if (count == j) flag = true;
		}
		else{
			while (b[r][i] == 'D' ){
				r++;
				count++;
				if( r == 10) break;
			}
			if (count == j) flag = true;
		}
		//System.out.println(flag);
		return flag;
	}
	
	public static void main (String [] args){
		
			BattleGUI BattleGUI = new BattleGUI();
	}
}
	