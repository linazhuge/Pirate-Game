import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Player extends Rectangle{
	private ImageIcon player;
	public int x, y, width, height;
	public boolean slide, hasShield;
	private String name;

	public Player(String name){
		super(100, 355, 115, 140);
		player = GamePanel.resize("./playerImages/" + name + ".gif", 120, 150);
		slide = false;
		hasShield = false;
		this.name = name;
	}
	
	//getter methods
	public String getName(){
		return name;
	}
	
	//jumping method
	public void add(int num){
		setLocation((int)getX(), (int)getY() - num);
	}
	
	//falling method
	public void fall(int num){
		setLocation((int)getX(), (int)getY() + num);
	}
	
	public void restart(){
		setLocation(100, 355);
	}
	
	//changes player image to sliding
	public void slide(){
		player = GamePanel.resize("./playerImages/" + name + "slide.png", 150, 90);
		setLocation(100, (int)getY() + 50);
		setSize(150, 100);
		slide = true;
	}
	
	public void run(){
		player = GamePanel.resize("./playerImages/" + name + ".gif", 120, 150);
		setLocation(100, (int)getY() - 50);
		setSize(120, 150);
		slide = false;
	}

	public boolean isSlide(){
		return slide;
	}
	
	public void draw(Graphics g){
		g.drawImage(player.getImage(), (int)getX(), (int)getY(), null);
	}
}
		