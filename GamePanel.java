import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class GamePanel extends JPanel implements KeyListener{
	//declaring variables
	private Object [][] display = new Object[6][11];
	private int count1, count2, jumpCount, coins, totalCoins, keyPress, speed, score, highScore, fallNum;
	private long start, end;
	private ImageIcon background, coin;
	private Player player;
	private boolean jump, fall;
	private Health health;
	private JLabel [] playerLabel = new JLabel[5];
	javax.swing.Timer timer;
	Scanner sc = new Scanner(new File("./lands/land1.txt"));
	
	public GamePanel() throws Exception{
		//setting layout and size
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(1000, 600));
		this.setBackground(Color.WHITE);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setRequestFocusEnabled(true);

		this.addComponentListener( new ComponentAdapter() {
        public void componentShown( ComponentEvent e ) {
            GamePanel.this.requestFocusInWindow();
			}
		});
		
		count1 = count2 = jumpCount = coins = totalCoins = 0;
		jump = false;
		fall = true;
		speed = 10;
		
		background = resize("./otherImages/background.png", 1000, 600);
		coin = resize("./otherImages/coin.png", 30, 30);
		
		player = new Player("orange");
		health = new Health("less");	

		for(int i=0; i<6; i++){
			for(int j=0; j<11; j++){
				display[i][j] = new Object();
			}
		}

		for(int i=0; i<11; i++)
			display[5][i] = new Land(i, 5);
		
		ActionListener move = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				count1 += speed;
				count2++;
				fall = true;
				if(count2%5 == 0)
					score++;
				if(count2 % 600 == 0 && player.getName().equals("orange"))
					health.addHealth();
				if(count1 < 100){
					for(int i=0; i<6; i++){
						for(int j=0; j<11; j++){
							display[i][j].setZ(speed);
						}
					}
				}
				else{
					count1 = 0;
					//increases speed
					if(count2 % 100 == 0 && !player.getName().equals("plum"))
						speed++;
					else if(count2 % 500 == 0 && player.getName().equals("plum"))
						speed++; 
					
					try{
						int num;
						//changes the object 2D array
						for(int i=0; i<6; i++){
							for(int j=0; j<10; j++){
								if(display[i][j+1] instanceof Land){
									if(display[i][j] instanceof Land)
										display[i][j].resetZ(speed);
									else
										display[i][j] = new Land(j, i);
								}
								else if(display[i][j+1] instanceof Coin){
									if(display[i][j] instanceof Coin)
										display[i][j].resetZ(speed);
									else
										display[i][j] = new Coin(j, i);
								}
								else if(display[i][j+1] instanceof Obstacle){
									if(display[i][j] instanceof Obstacle)
										display[i][j].resetZ(speed);
									else
										display[i][j] = new Obstacle(j, i);
								}	
								else if(display[i][j+1] instanceof HighObstacle){
									if(display[i][j] instanceof HighObstacle)
										display[i][j].resetZ(speed);
									else
										display[i][j] = new HighObstacle(j, i);
								}	
								else{
										display[i][j] = new Object();
								}
							}
						}
						
						//change objects for the last column of the 2D object array
						for(int i=0; i<6; i++){
							if(sc.hasNextInt()==false)
								reload();
							num = sc.nextInt();
							if(num == 1){
								if(display[i][10] instanceof Land)
									display[i][10].resetZ(speed);
								else
									display[i][10] = new Land(10, i);
							}
							else if(num == 2){
								if(display[i][10] instanceof Obstacle)
									display[i][10].resetZ(speed);
								else
									display[i][10] = new Obstacle(10, i);
							}
							else if(num == 3){
								if(display[i][10] instanceof Coin)
									display[i][10].resetZ(speed);
								else
									display[i][10] = new Coin(10, i);
							}
							else if(num == 4){
								if(display[i][10] instanceof HighObstacle)
									display[i][10].resetZ(speed);
								else
									display[i][10] = new HighObstacle(10, i);
							}
							else 
								display[i][10] = new Object();
						}
					}
					catch(Exception e){
						System.out.println("Somethings wrong");
					}
				}
				//jumping
				if(jump==true){
					jumpCount ++;
					if(jumpCount<=9)
						player.add(10);
					else
						player.add(5);
					if(jumpCount == 13){
						jump = false;
						jumpCount = 0;
					}
				}
				
				for(int i=0; i<6; i++){
					if(player.intersects(display[i][1]) == true){
						//instance where player hits obstacle
						if(display[i][1] instanceof Obstacle){
							if(display[i][1].isHurt() == false){
								health.changeHealth();
								display[i][1].changeHurt();
							}
						}
						//player hits the high obstacles
						if(display[i][1] instanceof HighObstacle){
							if(display[i][1].isHurt() == false){
								health.changeHealth();
								display[i][1].changeHurt();
							}
						}
						//player collects coins
						if(display[i][1] instanceof Coin){
							display[i][1] = new Object();
							coins++;
							if(player.getName().equals("peach"))
								coins++;
							score+=5;
						}
					}
					//makes sure player doesn't fall if they are on land
					if(player.isSlide()){
						if((display[i][1].contains(190, (int)player.getY() + 100) && display[i][1] instanceof Land) || (display[i][2].contains(190, (int)player.getY()+100) && display[i][2] instanceof Land)){
							fall = false;
							fallNum = 0;
						}
					}
					else{
						if((display[i][1].contains(150, (int)player.getY() + 150) && display[i][1] instanceof Land) || (display[i][2].contains(150, (int)player.getY()+150) && display[i][2] instanceof Land)){
							fall = false;
							fallNum = 0;
						}
					}
				}
				//player falls if not touching land
				if(fall == true && jump == false){
					fallNum++;
					if(player.isSlide())
						player.fall(speed+5);
					else if(fallNum < 4)
						player.fall(5);
					else
						player.fall(speed);
				}
				repaint();
				if(player.getY() >= 600 || health.getNum() == 0){
					try{
						restart();
					}
					catch(Exception e){
						System.out.println("Something went wrong");
					}
					GameFrame.cardsL.show(GameFrame.c, "Score");
				}
			}
		};
		timer = new javax.swing.Timer(50, move);
	}

	//static methods for resizing images
	public static ImageIcon resize(String name, int width, int height){
		Image scaleImage;
		ImageIcon pic;
		pic = new ImageIcon(name);
		scaleImage = pic.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		pic = new ImageIcon(scaleImage);
		return pic;
	}
	
	//getter methods
	public int getScore(){
		return score;
	} 
	
	public int getHighScore(){
		return highScore;
	}
	
	public int getCoins(){
		return coins;
	}
	
	public int getTotalCoins(){
		return totalCoins;
	}
	
	//setter methods
	public void setCoins(int num){
		totalCoins -= num;
	}
	
	//loads files to get game layout/map
	public void reload() throws Exception{
		int num;
		try{
			num = (int)(Math.random()*4) + 1;
			sc.close();
			sc = new Scanner(new File("./lands/land" + num+ ".txt"));
		}
		catch(Exception e){
			System.out.println("Something went wrong");
		}
	}
	
	//sets player depending on user choice
	public void setPlayer(String name){
		player = new Player(name);
		if(name.equals("mango"))
			health = new Health("more");
		else
			health = new Health("less");
	}
	
	//starts the game
	public void start(){
		count1 = count2 =  score = coins = 0;
		timer.start();
	}
	
	//stops and resets the game
	public void restart() throws Exception{
		speed = 10;
		if(score>highScore)
			highScore = score;
		totalCoins += coins;
		timer.stop();
		try{
			reload();
		}
		catch(Exception e){
			System.out.println("Something went wrong");
		}
		for(int i=0; i<6; i++){
			for(int j=0; j<11; j++){
				display[i][j] = new Object();
			}
		}
		for(int i=0; i<11; i++)
			display[5][i] = new Land(i, 5);
		player.restart();
		health = new Health("less");	
		if(player.getName().equals("mango"))
			health = new Health("more");
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, null); 
		g.drawImage(coin.getImage(), 10, 40, null); 
		g.setFont(new Font("Verdana", Font.PLAIN, 18));
		g.drawString("" + coins, 50, 65);
		g.drawString("Score: " + score, 10, 90);
		player.draw(g);
		health.draw(g);
		for(int i=0; i<6; i++){
			for(int j=0; j<11; j++){
				display[i][j].draw(g);
			}
		}
	}
 
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == 38 && keyPress == 0 && fall == false){
			jump = true;
			keyPress++;
		}
		else if(e.getKeyCode() == 40 && keyPress == 0){
			player.slide();
			keyPress++;
		}
	}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == 40)
			player.run();
		keyPress = 0;
	}
	
	public void keyTyped(KeyEvent e){
	}
}