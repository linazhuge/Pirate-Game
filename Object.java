import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

//super class for coins, land, obstacle, and high obstacle
public class Object extends Rectangle{
	private ImageIcon ground, obstacle, coin;
	public int z, num;
	private boolean hurt;
	
	public Object(){
	}
	
	public Object(int x, int y, int width, int height){
		super(x, y, width, height);
		hurt = false;
	}

	public void setZ(int num){
		this.num += num;
		setLocation((int)getX() - num, (int)getY());
	}
	
	public void resetZ(int speed){
		setLocation((int)getX() + num, (int)getY());
		num = 0;
	}
	
	public boolean isHurt(){
		return hurt;
	}
	
	public void changeHurt(){
		hurt = true;
	}

	public void draw(Graphics g){
	}
}