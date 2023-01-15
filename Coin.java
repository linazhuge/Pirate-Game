import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Coin extends Object{
	private ImageIcon coin;
	public int x, y, z, num, height, width;


	public Coin(int x, int y){
		super(x*100+25, y*100+25, 50, 50);
		coin = GamePanel.resize("./otherImages/coin.png", 50, 50);
	}

	public void draw(Graphics g){
		g.drawImage(coin.getImage(), (int)getX(), (int)getY(), null);
	}
}