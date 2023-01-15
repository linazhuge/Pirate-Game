/*Lina Zhuge
Assignment: Final Project
Date: June 19, 2020
ICS3U7-01 Ms. Strelkovska*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame{
	static CardLayout cardsL;
	static Container c;
	
	//different panels in game
	static CharacterPanel cp;
	static GamePanel gp;
	static ScorePanel sp;
	static MainPanel mp;
	static InstructionPanel ip;
	
	public GameFrame() throws Exception{
		c=getContentPane();
		cardsL=new CardLayout();
		c.setLayout(cardsL);
		
		cp = new CharacterPanel();
		gp = new GamePanel();
		sp = new ScorePanel();
		mp = new MainPanel();
		ip = new InstructionPanel();
		
		c.add("MainMenu", mp);
		c.add("Instructions", ip);
		c.add("Characters", cp);
		c.add("Game", gp);
		c.add("Score", sp);
	}
	public static void main(String[] args) throws Exception{
		GameFrame frame = new GameFrame();
		frame.setSize(1000,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}

}
