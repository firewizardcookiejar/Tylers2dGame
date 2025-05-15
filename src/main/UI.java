package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Key;

public class UI {

    GamePanel gp;
    Font sans_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = " ";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp){
        this.gp = gp;

        sans_40 = new Font("Comic Sans MS", Font.PLAIN, 40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
        
        
        
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    
    public void draw(Graphics2D g2){

        if(gameFinished == true){

            String text;
            int textLength;
            int x;
            int y;

            text = "Game done.";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //returns length of text


            x = gp.screenWidth/2 - -textLength/2;
            y = gp.screenHeight/2 - gp.tileSize*3;
            g2.drawString(text, x, y);


        }else{

        

        g2.setFont(sans_40);
        g2.setColor(Color.ORANGE);
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, 80, 60);
        
        if(messageOn == true){
            
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

            messageCounter++;
            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
    }
}


