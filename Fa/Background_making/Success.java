package Background_making;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Success {
    BufferedImage img;
    int x; int y;
    int width;int height;

    public Success(){
        img = ImageUtil.getImage("/img/Start/V.png");
        width = img.getWidth();
        height = img.getHeight();
    }

    public void drawSelf(Graphics g){
        g.drawImage(img,(App.SCREEN_WIDTH-width)/2,height
                ,width,height,null);
        g.setColor(Color.white);
        g.setFont(new Font("隶书",Font.ITALIC,20));
        g.drawString("点击结束",0,20);

    }
}
