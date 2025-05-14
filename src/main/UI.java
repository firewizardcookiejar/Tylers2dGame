package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

    GamePanel gp;
    Font sans_40;

    public UI(GamePanel gp){
        this.gp = gp;

        sans_40 = new Font("Comic Sans MS", Font.PLAIN, 40);
    }
    
    public void draw(Graphics2D g2){

        g2.setFont(sans_40);
        g2.setColor(Color.ORANGE);
        g2.drawString("Key = " + gp.player.hasKey, 50, 50);
        
    }
}
