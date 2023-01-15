import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class CharacterPanel extends JPanel implements ActionListener{
	//declaring variables
	private JLabel [] playerLabel = new JLabel[5];
	private JLabel title;
	private JButton [] button = new JButton[5];
	private boolean [] owns = new boolean[5];
	private ImageIcon background, titleimg;
	private boolean game = false;
	Font font = new Font("Corbel", Font.PLAIN, 15);
	
	public CharacterPanel(){
		//setting layout
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(1000, 600));
		this.setBackground(Color.WHITE);
		
		background = GamePanel.resize("./otherImages/background.png", 1000, 600);
		titleimg = GamePanel.resize("./otherImages/characterSelection.png", 900, 150);
		
		title = new JLabel(titleimg);
		this.add(title);
		
		for(int i=0; i<5; i++){
			//character labels with image
			ImageIcon img = GamePanel.resize("./playerLabels/pl" + (i + 1) + ".png", 180, 250);
			playerLabel[i] = new JLabel(img);
			playerLabel[i].setSize(180, 250);
			playerLabel[i].setFont(font);
			playerLabel[i].setText("");
			playerLabel[i].setForeground(Color.WHITE);
			playerLabel[i].setBackground(Color.BLACK);
			playerLabel[i].setOpaque(true);
			playerLabel[i].setHorizontalTextPosition(JLabel.CENTER);
			playerLabel[i].setVerticalTextPosition(JLabel.TOP);
			playerLabel[i].setSize(180, 300);
			this.add(playerLabel[i]);
		}			
		
		//adding name and skill text to character labels
		playerLabel[0].setText("<html>Name: Lime-onade<br>Skill: None</html>");
		playerLabel[1].setText("<html>Name: Glum Sugar Plum<br>Skill: Decreases Speed</html>");
		playerLabel[2].setText("<html>Name: Beachy Peach<br>Skill: Doubles Coin Value</html>");
		playerLabel[3].setText("<html>Name: Mango Tango<br>Skill: Adds Extra Life</html>");
		playerLabel[4].setText("<html>Name: Orange Cream Soda<br>Skill: Regains Life Every 30 Sec</html>");
		
		//buttons to select which character
		for(int i=0; i<5; i++){
			button[i] = new JButton("Select");
			button[i].setPreferredSize(new Dimension(180, 100));
			button[i].setFont(font);
			button[i].setForeground(Color.WHITE);
			button[i].setBackground(Color.BLACK);
			button[i].setOpaque(true);
			button[i].setHorizontalTextPosition(JLabel.CENTER);
			button[i].setVerticalTextPosition(JLabel.CENTER);
			this.add(button[i]);
			button[i].addActionListener(this);
		}	
		
		//setting prices for the characters
		button[1].setText("Cost: 1000");
		button[2].setText("Cost: 1000");
		button[3].setText("Cost: 5000");
		button[4].setText("Cost: 12000");
		
		for(int i=1; i<5; i++)
			owns[i] = false;
		owns[0] = true;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, null); 
		g.setFont(new Font("Corbel", Font.BOLD, 30));
		g.setColor(Color.BLACK);
		g.drawString(("Total Coins: " + GameFrame.gp.getTotalCoins()), 10, 590);
	}
	
	public void actionPerformed(ActionEvent e){
		game = false;
		if(e.getSource() == button[0]){
			if(owns[0] == true){
				GameFrame.gp.setPlayer("lime");
				game = true;
			}
		}
		else if(e.getSource() == button[1]){
			if(owns[1] == true){
				GameFrame.gp.setPlayer("plum");
				game = true;
			}
			else if(GameFrame.gp.getTotalCoins() >= 1000){
				owns[1] = true;
				button[1].setText("Select");
				GameFrame.gp.setCoins(1000);
				repaint();
			}
		}
		else if(e.getSource() == button[2]){
			if(owns[2] == true){
				GameFrame.gp.setPlayer("peach");
				game = true;
			}
			else if(GameFrame.gp.getTotalCoins() >= 1000){
				owns[2] = true;
				button[2].setText("Select");
				GameFrame.gp.setCoins(1000);
				repaint();
			}
		}
		else if(e.getSource() == button[3]){
			if(owns[3] == true){
				GameFrame.gp.setPlayer("mango");
				game = true;
			}
			else if(GameFrame.gp.getTotalCoins() >= 5000){
				owns[3] = true;
				button[3].setText("Select");
				GameFrame.gp.setCoins(5000);
				repaint();
			}
		}
		else if(e.getSource() == button[4]){
			if(owns[4] == true){
				GameFrame.gp.setPlayer("orange");
				game = true;
			}
			else if(GameFrame.gp.getTotalCoins() >= 12000){
				owns[4] = true;
				button[4].setText("Select");
				GameFrame.gp.setCoins(12000);
				repaint();
			}
		}
		if(game == true){
			GameFrame.gp.start();
			GameFrame.cardsL.show(GameFrame.c,"Game");
		}
	}
}