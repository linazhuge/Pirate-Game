import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Obstacle extends Object{
	private ImageIcon obstacle;
	public int x, y, z, num, height, width;
	private boolean hurt;

	public Obstacle(int x, int y){
		super(x*100+25, y*100+50, 50, 50);
		obstacle = GamePanel.resize("./otherImages/obstacle.png", 50, 50);
		hurt = false;
	}

	public void draw(Graphics g){
		g.drawImage(obstacle.getImage(), (int)getX(), (int)getY(), null);
	}
}