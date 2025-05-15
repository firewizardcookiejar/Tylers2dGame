package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {

    GamePanel gp;
    Font sans_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = " ";
    int messageCounter = 0;
    public boolean gameFinished = false;

    DecimalFormat dFormat = new DecimalFormat("#0.00");
    double playTime;

    public UI(GamePanel gp){
        this.gp = gp;

        sans_40 = new Font("Comic Sans MS", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
        
        
        
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    
    public void draw(Graphics2D g2){
    if(playTime >= 25){    
        g2.setFont(sans_40);
        g2.setColor(Color.black);
        String text;
        int textLength;
        int x;
        int y;
        text = "You died of hunger";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //returns length of text
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - gp.tileSize*3;
            g2.drawString(text, x, y);
            gp.gameThread = null;
            gp.stopMusic();
    }
        if(gameFinished == true){

            g2.setFont(sans_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the chest of food!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //returns length of text
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "Your final time is: " + dFormat.format(playTime);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //returns length of text
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + gp.tileSize*4;
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Yaaaay!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + gp.tileSize*3;
            g2.drawString(text, x, y);


            gp.gameThread = null;


        }else{

        

        g2.setFont(sans_40);
        g2.setColor(Color.ORANGE);
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, 80, 60);
        
        // TIMER
        playTime +=(double)1/60; //game runs at 60fps 
        g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11 , 65);

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


