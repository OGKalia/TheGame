package Background_making;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Start {
    BufferedImage img1;
    BufferedImage img2;
    BufferedImage img3;
    int x;
    int y;
    int width;
    int height;
    public Start(){
        img1 = ImageUtil.getImage("/img/Start/BGStart.bmp");


    }
    public  void drawSelf(Graphics g){
        g.drawImage(img1,0,0,App.SCREEN_WIDTH,App.SCREEN_HEIGHT,null);
//        g.drawImage(img2,0,130,App.SCREEN_WIDTH,550,null);
//        g.drawImage(img3,(App.SCREEN_WIDTH - width)/2,(App.SCREEN_HEIGHT-height)/2,null);

    }
}
