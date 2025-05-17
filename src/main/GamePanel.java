package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
    // SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale =3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile 
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12; 
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;


	
	// FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound se = new Sound();
	Sound music = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread; 

	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[10];
	public Entity npc[] = new Entity[10];

	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;

	
	
	
	public GamePanel(){
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	public void setupGame(){
		aSetter.setObject();
		aSetter.setNPC();
		playMusic(0);
		stopMusic();
		gameState = titleState;
	}
	
	public void startGameThread(){
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
			
			while(gameThread != null)	{
				
				currentTime = System.nanoTime();
				
				delta += (currentTime - lastTime)	/	drawInterval;
				
				lastTime = currentTime;
				
				
				if(delta >= 1)	{
					update();
					repaint();
					delta--;
				}
						
				
			
			}
	}
	public void update() {
		
		if(gameState == playState){
			player.update();
			
			for(int i = 0; i < npc.length; i++){
				if(npc[i] != null){
					npc[i].update();
				}
			}
		}
		if(gameState == pauseState){

		}
		

	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		//Title
		if(gameState == titleState){
			ui.draw(g2);
		}
		//Others
		else{
			//tile
			tileM.draw(g2);

			//object
			for(int i = 0; i < obj.length; i++){
				if(obj[i] != null){
					obj[i].draw(g2, this);
				}
			}
			//NPC
			for(int i = 0; i < npc.length; i++){
				if(npc[i] != null){
					npc[i].draw(g2);
				}
			}
			//player
			player.draw(g2);
			//ui
			ui.draw(g2);			
			//tile
			tileM.draw(g2);
			//object
			for(int i = 0; i < obj.length; i++){
				if(obj[i] != null){
					obj[i].draw(g2, this);
				}
			}
			//NPC
			for(int i = 0; i < npc.length; i++){
				if(npc[i] != null){
					npc[i].draw(g2);
				}
			}
			//player
			player.draw(g2);
			//ui
			ui.draw(g2);
}
		g2.dispose();
	}
	public void playMusic(int i){
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic(){
		music.stop();
	}
	public void playSE(int i){
		se.setFile(i);
		se.play();
	}

		
	
	
}
