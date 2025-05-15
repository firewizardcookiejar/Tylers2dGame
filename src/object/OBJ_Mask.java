package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Mask extends SuperObject{

        GamePanel gp;
        public OBJ_Mask(GamePanel gp){
                 this.gp = gp;

        name = "Mask";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/mask.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
