package Background_making;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class portal {
    ArrayList<BufferedImage> portals = new ArrayList<>();
    BufferedImage img ;
    int x;int y;
    int width; int height;
    int index=0;

    public portal(){
        img = ImageUtil.getImage("/img/portal/传送门_0000.png");
        width = img.getWidth()*2;
        height = img.getHeight()*2;
        for(int i =0;i<=14;i++){
            if(i<10){
                img = ImageUtil.getImage("/img/portal/传送门_000"+i+".png");
                portals.add(img);
                img = ImageUtil.getImage("/img/portal/传送门_000"+i+"@2x.png");
            }
            else{
                img = ImageUtil.getImage("/img/portal/传送门_00"+i+".png");
                portals.add(img);
                img = ImageUtil.getImage("/img/portal/传送门_00"+i+"@2x.png");
            }

        }
    }

    public void animation(){
        index ++;
        if(index >= portals.size()){
            index = 0;
        }
        img = portals.get(index);
    }

    public  void  drawSelf(Graphics g){
        g.drawImage(img,940,320,width,height,null);
    }
}
