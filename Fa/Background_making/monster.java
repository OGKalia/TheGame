package Background_making;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class monster {
    //五个要素
    BufferedImage img ;
    int x;int y;
    int width; int height;

    int dir;
    boolean hit;//碰撞
    int hp = 10;
    int status = App.STATUS_mv;

    Timer timer = new Timer();

    ArrayList<BufferedImage> img_move_l =new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> img_move_r =new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> img_hit_l =new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> img_hit_r =new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> img_be_hit_l =new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> img_be_hit_r =new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> img_hit_and_run_l = new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> img_hit_and_run_r = new ArrayList<BufferedImage>();


    public monster(){
        Random rd = new Random();
        img =ImageUtil.getImage("/img/monster/monster_run/01.png");
        width = img.getWidth();
        height = img.getHeight();
        //-100  400  900
        x = 300+rd.nextInt(100) + 500*(int)Math.pow(-1,rd.nextInt(100));
        y= rd.nextInt(100)+100;

        for(int i=1;i<=8;i++){//被攻击动画

            img = ImageUtil.getImage("/img/monster/monster_be_hit/0"+i+".png");
            img_be_hit_r.add(img);
            img = ImageUtil.getImage("/img/monster/monster_be_hit/01"+i+".png");
            img_be_hit_l.add(img);
        }

        for(int i=1;i<=12;i++){
            // 加载攻击的动画图片
            img = ImageUtil.getImage("/img/monster/monster_hit/0"+i+".png");
            img_hit_r.add(img);
            img_hit_and_run_r.add(img);
            img = ImageUtil.getImage("/img/monster/monster_hit/0"+i+"_"+i+".png");
            img_hit_l.add(img);
            img_hit_and_run_l.add(img);
        }

        for(int i=1;i<=10;i++){
            // 加载行走的动画图片
            img = ImageUtil.getImage("/img/monster/monster_run/0"+i+".png");
            img_move_r.add(img);
            img_hit_and_run_r.add(img);
            img = ImageUtil.getImage("/img/monster/monster_run/0"+i+"_"+i+".png");
            img_move_l.add(img);
            img_hit_and_run_l.add(img);
        }

    }

    public void drawSelf(Graphics g){
        g.drawImage(img,x,y,width,height,null);
        g.drawRect(x+260-10,y+260-30,100,10);
        g.setColor(new Color(205,38,38));
        g.fillRect(x+260-10,y+260-30,100*hp/10,10);
    }
    public void move(Player player){

        //判断人物是否进入怪物的警戒范围，如果进入，则每走一步攻击一下
//            如果没进入，则一直追
        if(Math.pow(player.x-x,2)>=Math.pow(App.Admonish_range,2)){//没进入警戒范围时


            if(player.Player_speed != 0 || player.status !=2){
                //当玩家处于移动状态且不是背景移动或者玩家没有移动时
                //小怪会追逐玩家
                if(Math.pow(player.x - x,2) >= Math.pow(player.y - y,2)){
                    if(player.x >= x ){
                        x += App.Monster_Speed;
                    }
                    if(player.x < x ){
                        x -= App.Monster_Speed;
                    }
                }else{
                    if(player.y >= y ){
                        y += App.Monster_Speed/2;
                    }
                    if(player.y < y ){
                        y -= App.Monster_Speed/2;
                    }
                }
            }
            if(player.status == 2 && player.Player_speed  == 0){
                //当玩家是移动状态，且是背景移动时
                //小怪需要朝着人物移动的反方向移动
                if(Math.pow(player.x - x,2) >= Math.pow(player.y - y,2)){
                    if(player.x >= x ){
                        x += App.Monster_Speed-10;
                    }
                    if(player.x < x ){
                        x -= App.Monster_Speed-10;
                    }
                }else{
                    if(player.y >= y ){
                        y += App.Monster_Speed/2;
                    }
                    if(player.y < y ){
                        y -= App.Monster_Speed/2;
                    }
                }
            }
        }
        else{//进入之后
            if(index_hit_and_run_r <=12 || index_hit_and_run_l <=12){
                if(player.Player_speed == 0 && player.status ==2){
                    //小怪攻击时，背景移动，小怪应该不动，也就是反向移动
                    if(player.x >= x ){
                        if(player.dir == App.LEFT){
                            x -= App.Monster_Speed-14;
                        }
                        if(player.dir == App.RIGHT){
                            x += App.Monster_Speed-14;
                        }

                    }
                    if(player.x < x ){
                        if(player.dir == App.LEFT){
                            x -= App.Monster_Speed-14;
                        }
                        if(player.dir == App.RIGHT){
                            x += App.Monster_Speed-14;
                        }
                    }
                }
            }
            if(index_hit_and_run_l >13 || index_hit_and_run_r >13){
                //攻击动画播放完毕，开始走路
                if(player.Player_speed != 0 || player.status !=2){
                    //当玩家处于移动状态且不是背景移动或者玩家没有移动时
                    //小怪会追逐玩家
                    if(Math.pow(player.x - x,2) >= Math.pow(player.y - y,2)){
                        if(player.x >= x ){
                            x += App.Monster_Speed;
                        }
                        if(player.x < x ){
                            x -= App.Monster_Speed;
                        }
                    }else{
                        if(player.y >= y ){
                            y += App.Monster_Speed/2;
                        }
                        if(player.y < y ){
                            y -= App.Monster_Speed/2;
                        }
                    }
                }
                if(player.status == 2 && player.Player_speed  == 0){
                    //当玩家是移动状态，且是背景移动时
                    if(Math.pow(player.x - x,2) >= Math.pow(player.y - y,2)){
                        if(player.x >= x ) {

                            x += App.Monster_Speed - 10;
                        }
                            if(player.x < x ){
                                x -= App.Monster_Speed-10;
                            }
                    }else{
                        if(player.y >= y ){
                            y += App.Monster_Speed/2;
                        }
                        if(player.y < y ){
                            y -= App.Monster_Speed/2;
                        }
                    }
                }
            }

        }

    }

    //碰撞检测
    public boolean hitBy(Player player){
        Mp3Player m_be_hit=new Mp3Player("/sound/m_be_hit.mp3",false);

        if(player.status==App.STATUS_J ){

            //怪在人的右边，且人向右边攻击
            //判定一次剑气碰撞，剑气(x+350,y+300)width:270;height:60
            //(小怪判定：x+260,y+260) width:100;height=150;
            //y轴的判定点：以怪物和人的脚为参照，▲y<=6*App.Monster_Speed;
            //人物脚的坐标(x+320,y+410)  怪物的脚(x+310,y+410)
            if( player.character ==App.CHARACTER_1 &&
                    (player.x+350+270>=x+260 && player.x+350 <= x+260+100
                    && Math.pow(player.y-y,2) <= Math.pow(App.Monster_Speed*6,2))
                    &&  (  player.dir == App.RIGHT
                     )){
                if(player.index_j == 6){
                    hp = hp-3;
                    m_be_hit.start();

                }

                return true;
            }
            //怪在人的左边，且人向左边攻击
            if( player.character ==App.CHARACTER_1 &&
                    player.x+310-270<=x+260+100 && player.x+310 >= x+260
                    && Math.pow(player.y-y,2) <= Math.pow(App.Monster_Speed*6,2)
                    &&  ( player.dir == App.LEFT
                    )){
                if(player.index_j == 6){
                    hp = hp-3;
                    m_be_hit.start();

                }

                return true;
            }



            //人物2
            //***
            //怪在幽灵的右边，且幽灵向右边攻击
            if( player.character ==App.CHARACTER_2 &&
                    (player.x+360+410>=x+260 && player.x+360 <= x+260+100
                            && Math.pow(player.y-y-40,2) <= Math.pow(App.Monster_Speed*9,2))
                    &&  (  player.dir == App.RIGHT
            )){
//                System.out.println("hello ");
//                System.out.println("player.index_j: "+ player.player2_index_j);
                if(player.player2_index_j == 6){
                    hp = hp-3;
                    m_be_hit.start();

                }

                return true;
            }
            //怪在幽灵的左边，且幽灵向左边攻击
            if( player.character ==App.CHARACTER_2 &&
                    player.x+260>=x+260 && player.x+260-410 <= x+260+100
                    && Math.pow(player.y-y-40,2) <= Math.pow(App.Monster_Speed*9,2)
                    &&  ( player.dir == App.LEFT
            )){
                if(player.player2_index_j == 6){
                    hp = hp-3;
                    m_be_hit.start();

                }

                return true;
            }
            //***

        }
        if(player.status == App.STATUS_O){
            //脚坐标的一般方程:(x+290+30,y+260+150)
            //判定点椭圆方程：脚的坐标设定为：x1和y1
            //(x1)^2/340^2 + (x2)^2/150^2 = 1
            //怪物的脚：(x+310,y+410)
            if(Math.pow(player.x+320 - (x+310),2)/(340*340) +
                    Math.pow(player.y-y,2)/(150*150) <=1 ){

                if(player.character==App.CHARACTER_1 &&
                        player.index_o >=8 && player.player2_index_o <=21){
                    hp=hp-1;

                }
                if(player.character==App.CHARACTER_1 &&
                        player.index_o == 21){
                    hp = hp -6;
                    m_be_hit.start();
                }

                if(player.character==App.CHARACTER_2 &&
                        player.player2_index_o >=4 && player.player2_index_o <=16){
                    hp=hp-1;

                }
                if(player.character==App.CHARACTER_2 &&
                        player.player2_index_o == 16){
                    hp = hp -6;
                    m_be_hit.start();
                }

                return true;
            }
        }

        return false;
    }

    int index_move_r = 0; // 用来从向右行走动画集合中取图片的索引
    int index_move_l = 0; // 用来从向左行走动画集合中取图片的索引
    int index_hit_r = 0; // 用来从向右攻击动画集合中取图片的索引
    int index_hit_l = 0; // 用来从向左攻击动画集合中取图片的索引
    int index_be_hit_r = 0; // 用来从向右攻击动画集合中取图片的索引
    int index_be_hit_l = 0; // 用来从向左攻击动画集合中取图片的索引
    int index_hit_and_run_l=0;
    int index_hit_and_run_r = 0;

    public  void animation(Player player){

        if(player.x <= x){//玩家在怪物的左边
            dir = App.LEFT;

        }
        else{
            dir = App.RIGHT;
        }

        if(status == App.STATUS_mv){
            if(Math.pow(player.x-x,2)>Math.pow(App.Admonish_range,2)){
//            如果没进入攻击范围，根据方向播放移动动画

                if(dir == App.LEFT){
                    index_move_l++;

                    if (index_move_l >= img_move_l.size()) {
                        // 索引归0，重新从第一张图片开始取
                        index_move_l = 0;
                    }
                    img = img_move_l.get(index_move_l);
                }
                if(dir == App.RIGHT){
                    index_move_r++;

                    if (index_move_r >= img_move_r.size()) {
                        // 索引归0，重新从第一张图片开始取
                        index_move_r = 0;
                    }
                    img = img_move_r.get(index_move_r);
                }
            }
            if(Math.pow(player.x-x,2)<=Math.pow(App.Admonish_range,2)){
                //否则，每走一步，攻击一下

                if(dir == App.LEFT){
                    index_hit_and_run_l++;
                    if(index_hit_and_run_l >=img_hit_and_run_l.size()){
                        index_hit_and_run_l=0;
                    }
                    img = img_hit_and_run_l.get(index_hit_and_run_l);
                }
                if(dir == App.RIGHT){
                    index_hit_and_run_r++;
                    if(index_hit_and_run_r >=img_hit_and_run_r.size()){
                        index_hit_and_run_r=0;
                    }
                    img = img_hit_and_run_r.get(index_hit_and_run_r);
                }


            }
        }

        if(status == App.STATUS_be_hit){//状态传进来了
            if(dir == App.LEFT){
                index_be_hit_l++;

                if(index_be_hit_l >= img_be_hit_l.size()){
                    index_be_hit_l = 0;
                    status = App.STATUS_mv;

                }
                img = img_be_hit_l.get(index_be_hit_l);
            }
            if(dir == App.RIGHT){
                index_be_hit_r++;
                if(index_be_hit_r >= img_be_hit_r.size()){
                    index_be_hit_r = 0;
                    status = App.STATUS_mv;
                }
                img = img_be_hit_r.get(index_be_hit_r);
            }
        }

    }



}
