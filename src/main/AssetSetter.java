package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Mask;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 45 * gp.tileSize;
        gp.obj[0].worldY = 26 * gp.tileSize;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 39 * gp.tileSize;
        gp.obj[1].worldY = 25 * gp.tileSize;

        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].worldX = 39 * gp.tileSize;
        gp.obj[2].worldY = 21 * gp.tileSize;

        gp.obj[3] = new OBJ_Key();
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 26 * gp.tileSize;

        gp.obj[4] = new OBJ_Mask();
        gp.obj[4].worldX = 19 * gp.tileSize;
        gp.obj[4].worldY = 21* gp.tileSize;



    }
}
