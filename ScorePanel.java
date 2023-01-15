import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ScorePanel extends JPanel implements ActionListener{
	//declaring variables
	private JButton [] button = new JButton[3];
	private ImageIcon background, scoreimg;
	private JPanel options;
	private Color blue = new Color(183, 233, 240);
	
	public ScorePanel(){
		//setting layout and size
		options = new JPanel();
		options.setLayout(new GridLayout(1, 3));
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1000, 600));
		this.setBackground(Color.WHITE);
		this.add(options, BorderLayout.SOUTH);
		options.setPreferredSize(new Dimension(1000, 100));
		
		button[0] = new JButton("PLAY AGAIN");
		button[1] = new JButton("CHARACTERS");
		button[2] = new JButton("MAIN MENU");
		
		for(int i=0; i<3; i++){
			button[i].setFont(new Font("Corbel", Font.PLAIN, 28));
			button[i].setBackground(blue);
			options.add(button[i]);
			button[i].setFocusable(false);
			button[i].addActionListener(this);
		}

		background = GamePanel.resize("./otherImages/background.png", 1000, 600);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, null); 
		
		//writing out score, high score, coins, and total coins
		g.setFont(new Font("Corbel", Font.BOLD, 75));
		g.drawString("SCORE: " + GameFrame.gp.getScore(), 50, 150);
		g.drawString("HIGH SCORE: " + GameFrame.gp.getHighScore(), 50, 250);
		g.setFont(new Font("Corbel", Font.PLAIN, 50));
		g.drawString("COINS: " + GameFrame.gp.getCoins(), 50, 450);
		g.drawString("TOTAL COINS: " + GameFrame.gp.getTotalCoins(), 450, 450);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == button[0]){
			//starts game
			GameFrame.gp.start();
			GameFrame.cardsL.show(GameFrame.c, "Game");
		}
		else if(e.getSource() == button[1]){
			//goes to character page
			GameFrame.cardsL.show(GameFrame.c, "Characters");
		}
		else{
			//goes to main menu
			GameFrame.cardsL.show(GameFrame.c, "MainMenu");
		}
	}
}