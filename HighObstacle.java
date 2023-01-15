import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class HighObstacle extends Object{
	private ImageIcon highObstacle;
	public int x, y, z, num, height, width;
	private boolean hurt;

	public HighObstacle(int x, int y){
		super(x*100, y*100 - 200, 90, 300);
		highObstacle = GamePanel.resize("./otherImages/highObstacle.png", 100, 300);
	}


	public void draw(Graphics g){
		g.drawImage(highObstacle.getImage(), (int)getX(), (int)getY(), null);
	}
}