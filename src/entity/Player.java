package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;

	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	int idleCounter = 0;
	
	

	public Player(GamePanel gp, KeyHandler keyH)	{
		
		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth/2 - (gp.tileSize/2); //draws character in center
		screenY = gp.screenHeight/2 - (gp.tileSize/2);

		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;

		setDefaultValues();
		getPlayerImage();
		
		
	}
	public void setDefaultValues()		{
		
		worldX = gp.tileSize * 8;
		worldY = gp.tileSize * 23;
		speed = 4;
		direction = "down";
		
	}
	public void getPlayerImage()	{
		
		up1 = setup("witch_up_1");
		up2 = setup("witch_up_3");
		down1 = setup("witch_down_1");
		down2 = setup("witch_down_3");
		left1 = setup("witch_left_1");
		left2 = setup("witch_left_2");
		right1 = setup("witch_right_1");
		right2 = setup("witch_right_2");
		
	}
	public BufferedImage setup(String imageName){

		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		}catch(IOException e){
			e.printStackTrace();

		}
		return image;
	}
	
		public void update()	{
			
			if(keyH.upPressed == true || keyH.downPressed == true || //idle animation currently none 
					keyH.leftPressed == true || keyH.rightPressed == true)	{
				if(keyH.upPressed == true) {
					direction = "up";
				}
				else if(keyH.downPressed == true) {
					direction = "down";
				}
				else if(keyH.leftPressed == true) {
					direction = "left";
				}
				else if(keyH.rightPressed == true) {
					direction = "right";
				}

				//tile collssion
				collisionOn = false;
				gp.cChecker.checkTile(this);

				//check  obj collision
				int objIndex = gp.cChecker.checkObject(this, true);
				pickUpObject(objIndex);

				if(collisionOn == false){
					switch(direction){
						case "up":
						worldY -= speed;
							break;

						case "down":
						worldY += speed;
							break;

						case "left":
						worldX -= speed;
							break;

						case "right":
							worldX += speed; 
							break;
							
					}
				}
				
				spriteCounter++;  //how fast the animation is for player sprite
				if(spriteCounter > 10)	{ 
					if(spriteNum == 1)	{
						spriteNum = 2;
					}
					else if(spriteNum == 2)	{
						spriteNum = 1;
					}
					spriteCounter = 0;
				}
			}
			else{
				idleCounter++;

				if(idleCounter == 20){
					spriteNum = 2;
					idleCounter = 0;
				}
				
			}


			
			
		}
		public void pickUpObject(int i){

			if(i != 727){
				String objectName = gp.obj[i].name;

				switch(objectName){
				case "Key":
					gp.playSE(2);
					hasKey++;
					gp.obj[i] = null;
					gp.ui.showMessage("Key get!");
					break;

				case "Door":
					
					if(hasKey > 0){
						gp.playSE(1);
						gp.obj[i] = null;
						gp.ui.showMessage("Opened a door!");
						hasKey--;
					}else{
						gp.ui.showMessage("You have no key dipshit");
					}
					break;
				case "Mask":
					gp.playSE(3);
					speed += 3;
					gp.obj[i] = null;
					gp.ui.showMessage("Speed boost!");
					break;
				case "Chest":
				if(hasKey > 0){
						gp.obj[i] = null;
						gp.ui.gameFinished = true;
						gp.stopMusic();
						gp.playSE(3);
						hasKey--;
					}else{
						gp.ui.showMessage("You have no key dipshit");
					}
					break;


				}
			}
		}

		public void draw(Graphics2D g2)	{
			
//			g2.setColor(Color.yellow);
			
//			g2.fillRect(x, y, gp.tileSize, gp.tileSize);
			
			BufferedImage image = null;
			
			switch(direction)	{
			case "up":
				if(spriteNum == 1)	{
					image = up1;
				}
				if(spriteNum == 2) {
					image = up2;
				}
				break;
			case "down":
				if(spriteNum == 1)	{
					image = down1;
				}
				if(spriteNum == 2)	{
					image = down2;
				}
				break;
			case "left":
				if(spriteNum == 1)	{
					image = left1;
				}
				if(spriteNum == 2)	{
					image = left2;
				}				
				break;
			case "right":
				if(spriteNum == 1)	{
				image = right1;
				}
				if(spriteNum == 2)	{
					image = right2;
				}
				break;
			}
			g2.drawImage(image,  screenX,  screenY, null);

			//g2.setColor(Color.blue);	used to show player hitbox uncomment to see :)
			//g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, + solidArea.height);
			
		}
		
}
