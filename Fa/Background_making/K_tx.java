package Background_making;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class K_tx {
    BufferedImage img;
    BufferedImage pre_img;
    int width;
    int height;
    int status;
    //用来接收人物坐标的x，y
    int man_x;
    int man_y;
    int dir=App.RIGHT;
    int character=App.CHARACTER_1;
    ArrayList<BufferedImage> k_tx=new ArrayList<>();//存放k技能的特效动画

    public K_tx(){
        //加载k的护盾特效
        for (int i=1;i<=8;i++){
            pre_img=ImageUtil.getImage("/img/k_tx/"+(i-1)+".png");
            k_tx.add(pre_img);
        }
        width=pre_img.getWidth();
        height=pre_img.getHeight();
    }
    public void k_animation(){
        if (status==App.K_TX){
            Mp3Player sheild=new Mp3Player("/sound/sheild.mp3",false);
            sheild.start();
            Timer timer=new Timer();
            timer.schedule(new TimerTask() {
                int index=0;
                @Override
                public void run() {
                        index++;
                        if (index >= 8) {
                            index = 0;
                        }else {
                            img = k_tx.get(index);
                        }
                }
            },0,130);
        }
    }
    public void drawSelf(Graphics g){
        if (character==App.CHARACTER_1){
            if (status==App.K_TX){
                if(dir==App.RIGHT) {
                    g.drawImage(img, man_x + 140, man_y + 270, width + 80, height + 80, null);
                }
                if (dir==App.LEFT){
                    g.drawImage(img, man_x + 220, man_y + 270, width + 80, height + 80, null);
                }
            }
        }
        if (character==App.CHARACTER_2){
            if (status==App.K_TX){
                if(dir==App.RIGHT) {
                    g.drawImage(img, man_x + 180, man_y + 250, width + 80, height + 80, null);
                }
                if (dir==App.LEFT){
                    g.drawImage(img, man_x + 200, man_y + 250, width + 80, height + 80, null);
                }
            }
        }

    }

}
