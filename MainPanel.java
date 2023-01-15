import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MainPanel extends JPanel implements ActionListener{
	private JButton [] button = new JButton[3];
	private JLabel title;
	private ImageIcon beach, titleimg;
	private Color blue = new Color(183, 233, 240);
	
	public MainPanel(){
		setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(1000, 600));
		this.setBackground(Color.WHITE);
		
		beach = GamePanel.resize("./otherImages/beach.png", 1000, 600);
		titleimg = GamePanel.resize("./otherImages/title.png", 800, 150);
		
		title = new JLabel(titleimg);
		this.add(title);
		
		button[0] = new JButton("PLAY GAME");
		button[1] = new JButton("INSTRUCTIONS");
		button[2] = new JButton("EXIT");
		
		for(int i=0; i<3; i++){
			button[i].setFont(new Font("Corbel", Font.PLAIN, 30));
			button[i].setBackground(blue);
			button[i].addActionListener(this);
			button[i].setPreferredSize(new Dimension(501, 100));
			this.add(button[i]);
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(beach.getImage(), 0, 0, null);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == button[0]){
			GameFrame.cardsL.show(GameFrame.c, "Characters");
		}
		else if(e.getSource() == button[1]){
			GameFrame.cardsL.show(GameFrame.c, "Instructions");
		}
		else
			System.exit(0);
	}
}