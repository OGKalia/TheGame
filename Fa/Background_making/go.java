package Background_making;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class go {

    BufferedImage img_go_1, img_go;
    int x1;
    int y1;
    int x2;
    int y2;
    int width_1;
    int height_1;
    int width_2;
    int height_2;
    int i;//go的计数器

    public  go(){
        img_go_1 = ImageUtil.getImage("/img/go/go_1.png");
        img_go = ImageUtil.getImage("/img/go/go_2.png");
        width_1 = (int)(img_go_1.getWidth()/2.8);
        height_1 = (int)(img_go_1.getHeight()/2.8);
        width_2 = (int)(img_go.getWidth()/2.8);
        height_2 = (int)(img_go.getHeight()/2.8);
        x1 = 1100;
        y1 = 250;
        x2 = 1100;
        y2 = 250;
        i = 0;

    }


    public void drawSelf(Graphics g){
        i++;
        g.drawImage(img_go_1,x1,y1,width_1,height_1,null);
        g.drawImage(img_go,x2 - (int)(width_2*(i%4/50.0)*0.5 ), y2-(int)(height_2*(i%4/50.0))+1,(int)(width_2*(1+i%4/50.0)),(int)(height_2*(1+i%4/50.0)),null);
    }
}
