import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Land extends Object{
	public ImageIcon ground;
	public int x, y, z, height, width;

	public Land(int x, int y){
		super(x*100, y*100, 100, 40);
		ground = GamePanel.resize("./otherImages/ground.png", 100, 40);
	}
	
	public void draw(Graphics g){
		g.drawImage(ground.getImage(), (int)getX(), (int)getY(), null);
	}
}
		