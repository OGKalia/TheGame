package Background_making;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
    BufferedImage img_1 ;//远景
    BufferedImage img_2 ;//中景
    BufferedImage img_3 ;//近景
    BufferedImage img_4 ;//前景



    int x1;
    int y1;

    int width;//确定游戏背景宽度
    int height;//确定高度



    //制作背景的模具
    public Background(){
        img_1 = ImageUtil.getImage("/img/png/gold_desert_1_BS.png");
        img_2 = ImageUtil.getImage("/img/png/gold_desert_1_DS.png");
        img_3 = ImageUtil.getImage("/img/png/gold_desert_1_CS.png");
        img_4 = ImageUtil.getImage("/img/png/gold_desert_1_FS.png");

        //确定背景图片的大小
        width =img_1.getWidth();
        height =img_1.getHeight();
        //位置确定
        x1 = 0;
        y1 = 0;


    }



    public void drawSelf(Graphics g){
        g.drawImage(img_1,x1,y1,width,height,null);
        g.drawImage(img_2,x1,y1,width,height,null);
        g.drawImage(img_3,x1,y1,width,height,null);
        g.drawImage(img_4,x1,y1,width,height,null);
    }



}
