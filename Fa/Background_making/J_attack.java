package Background_making;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class J_attack {
    BufferedImage img;
    BufferedImage pre_img;
    int width;
    int height;
    int width_2;
    int height_2;
    int status;
    //用来接收人物坐标的x，y
    int man_x;
    int man_y;
    int dir=App.RIGHT;
    int player_index;
    int character=App.CHARACTER_1;
    //角色1的j特效
    ArrayList<BufferedImage> j_attack_tx_r=new ArrayList<>();//存放j技能的特效动画
    ArrayList<BufferedImage> j_attack_tx_l=new ArrayList<>();//存放j技能的特效动画

    //角色2的j特效
    ArrayList<BufferedImage> player2_j_attack_tx_r=new ArrayList<>();//存放j技能的特效动画
    ArrayList<BufferedImage> player2_j_attack_tx_l=new ArrayList<>();//存放j技能的特效动画

    public J_attack(){
        //man_x= player.x;
        //man_y= player.y;

        //加载角色1j攻击特效
        for (int i=1;i<=6;i++){
            pre_img=ImageUtil.getImage("/img/j_attack_tx/j_attack_tx_l_0"+i+".png");
            j_attack_tx_l.add(pre_img);
            pre_img=ImageUtil.getImage("/img/j_attack_tx/j_attack_tx_r_0"+i+".png");
            j_attack_tx_r.add(pre_img);
        }
        width= pre_img.getWidth();
        height= pre_img.getHeight();
        //加载角色2j攻击特效
        for (int i=1;i<=6;i++){
            pre_img=ImageUtil.getImage("/img/player2_j_attack_tx/"+(i-1)+"_l.png");
            player2_j_attack_tx_l.add(pre_img);
            pre_img=ImageUtil.getImage("/img/player2_j_attack_tx/"+(i-1)+".png");
            player2_j_attack_tx_r.add(pre_img);
        }

        width_2= pre_img.getWidth();
        height_2= pre_img.getHeight();

    }





    public void j_attack_animation() {
        if (character==App.CHARACTER_1) {
            if (status == App.J_TX) {
                Mp3Player sword_sound=new Mp3Player("/sound/sword.mp3",false);
                sword_sound.start();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    int index = -1;

                    @Override
                    public void run() {
                        if (dir == App.RIGHT) {
                            index++;

                            if (index >= 6) {
                                status = App.J_TX_N;
                                index = -1;
                                img = null;
                                timer.cancel();
                            } else {
                                img = j_attack_tx_r.get(index);
                            }

                        }
                        if (dir == App.LEFT) {
                            index++;

                            if (index >= 6) {
                                status = App.J_TX_N;
                                index = -1;
                                img = null;
                                timer.cancel();
                            } else {
                                img = j_attack_tx_l.get(index);
                            }

                        }
                    }
                }, 300, 50);
            }
        }
            if (character==App.CHARACTER_2){
                Mp3Player lighting_sound=new Mp3Player("/sound/lighting.mp3",false);
                lighting_sound.start();
                if (status==App.J_TX){
                    Timer timer=new Timer();
                    timer.schedule(new TimerTask() {
                        int player2_index=-1;
                        @Override
                        public void run() {
                            if (dir==App.RIGHT){
                                player2_index++;

                                if (player2_index >= 6) {
                                    status = App.J_TX_N;
                                    player2_index = -1;
                                    img = null;
                                    timer.cancel();
                                }else {
                                    img = player2_j_attack_tx_r.get(player2_index);
                                }

                            }
                            if (dir==App.LEFT){
                                player2_index++;

                                if (player2_index >= 6) {
                                    status = App.J_TX_N;
                                    player2_index = -1;
                                    img = null;
                                    timer.cancel();
                                }else {
                                    img = player2_j_attack_tx_l.get(player2_index);
                                }

                            }
                        }
                    },300,50);
                }
            }
        }









    public void drawSelf(Graphics g){
        if (character==App.CHARACTER_1){
            if (status==App.J_TX){
                if (dir==App.RIGHT){
                    g.drawImage(img,man_x+310,man_y+290,width,height,null);
                }else if (dir==App.LEFT){
                    g.drawImage(img,man_x-10,man_y+290,width,height,null);
                }
            }
        }
        if (character==App.CHARACTER_2){
            if (status==App.J_TX){
                if (dir==App.RIGHT){
                    g.drawImage(img,man_x+360,man_y+180,width_2*2,height_2*3,null);
                }else if (dir==App.LEFT){
                    g.drawImage(img,man_x-200,man_y+180,width_2*2,height_2*3,null);
                }
            }
        }

    }
}
