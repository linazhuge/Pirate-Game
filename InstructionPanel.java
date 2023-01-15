import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class InstructionPanel extends JPanel implements ActionListener{
	private JButton exit;
	private ImageIcon beach, obstacle, highObstacle;
	private Color blue = new Color(183, 233, 240);
	
	public InstructionPanel(){
		setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1000, 600));
		this.setBackground(Color.WHITE);
		
		//button to exit the instructions
		exit = new JButton("Exit");
		exit.setFont(new Font("Corbel", Font.PLAIN, 30));
		exit.setBackground(blue);
		exit.addActionListener(this);
		exit.setPreferredSize(new Dimension(1000, 50));
		
		add(exit, BorderLayout.SOUTH);

		beach = GamePanel.resize("./otherImages/beach.png", 1000, 600);
		obstacle = GamePanel.resize("./otherImages/obstacle.png", 100, 100);
		highObstacle = GamePanel.resize("./otherImages/highObstacle.png", 100, 300);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(beach.getImage(), 0, 0, null);
		g.setFont(new Font("Ink Free", Font.BOLD, 70));
		g.drawString("INSTRUCTIONS", 250, 75);
		g.setFont(new Font("Corbel", Font.PLAIN, 30));
		g.setColor(Color.BLACK);
		g.drawString("Choose a character to run with,", 50, 150);
		g.drawString("different characters have different skills.", 50, 200);
		g.drawString("You can buy different characters", 50, 250);
		g.drawString("using coins you gain while running.", 50, 300);
		g.drawString("Use the up and down arrow keys", 50, 350);
		g.drawString("to jump and slide.", 50, 400);
		g.drawString("Make sure you avoid obstacles", 50, 450);
		g.drawString("and try to get a high score!", 50, 500);
		g.drawImage(obstacle.getImage(), 600, 400, null);
		g.drawImage(highObstacle.getImage(), 800, 0, null);
	}
	
	public void actionPerformed(ActionEvent e){
		//goes back to main menu
		GameFrame.cardsL.show(GameFrame.c, "MainMenu");
	}
}