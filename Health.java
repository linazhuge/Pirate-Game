import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

//class for health of player, displays and changes
public class Health{
	private ImageIcon lives;
	private String amount;
	private int num, x, y;

	public Health(String amount){
		this.amount = amount;
		if(amount.equals("less"))
			num = 3;
		else
			num = 4;
		lives = GamePanel.resize(("./hearts/" + amount + "Heart" + num + ".png"), 500, 300);
	}
	
	public int getNum(){
		return num;
	}
	
	public void changeHealth(){
		num--;
		changeLives();
	}
	
	public void addHealth(){
		if(num<3)
			num++;
		changeLives();
	}
	public void resetHealth(){
		num = 3;
	}
	
	public void changeLives(){
		lives = GamePanel.resize(("./hearts/" + amount + "Heart" + num + ".png"), 500, 300);
	}
	
	public void draw(Graphics g){
		g.drawImage(lives.getImage(), 320, -75, null);
	}
}