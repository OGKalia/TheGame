package Background_making;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {
    BufferedImage img;
    int x;
    int y;
    int width;
    int height;
    //定义方向
    int dir = App.RIGHT;
    boolean isUp,isLeft,isRight,isDown;
    int status=App.STATUS_zl;// 行走和的状态
    int Player_speed;
    monster mon = new monster();
    int attackDir;
    int hp = 20;
    boolean isBeHit ;

    BufferedImage img_Hp_Mask;
    BufferedImage img_Hp_Fill;
    BufferedImage img_Mp;
    int width_Hp_Mask;
    int height_Hp_Mask;
    int width_Hp_Fill;
    int height_Hp_Fill;
    int width_Mp;
    int height_Mp;
    BufferedImage img_Character1;
    BufferedImage img_Character2;
    int character=App.CHARACTER_1;
    int Mp =100;



    //角色1的图片集合
    //存放站立图片
    ArrayList<BufferedImage> imgs_zl_r = new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> imgs_zl_l = new ArrayList<BufferedImage>();
    // 存放行走图片
    ArrayList<BufferedImage> imgs_mv_r = new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> imgs_mv_l = new ArrayList<BufferedImage>();
    //存放攻击照片(j)
    ArrayList<BufferedImage> imgs_j_r = new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> imgs_j_l = new ArrayList<BufferedImage>();
    //存放被攻击照片
    ArrayList<BufferedImage> imgs_be_hit_r = new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> imgs_be_hit_l = new ArrayList<BufferedImage>();
    //存放o技能的动作
    ArrayList<BufferedImage> imgs_o = new ArrayList<BufferedImage>();

    //角色2的图片集合
    //存放站立图片
    ArrayList<BufferedImage> player2_imgs_zl_r = new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> player2_imgs_zl_l = new ArrayList<BufferedImage>();
    // 存放行走图片
    ArrayList<BufferedImage> player2_imgs_mv_r = new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> player2_imgs_mv_l = new ArrayList<BufferedImage>();
    //存放攻击照片(j)
    ArrayList<BufferedImage> player2_imgs_j_r = new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> player2_imgs_j_l = new ArrayList<BufferedImage>();
    //存放被攻击照片
    ArrayList<BufferedImage> player2_imgs_be_hit_r = new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> player2_imgs_be_hit_l = new ArrayList<BufferedImage>();
    //存放o技能的动作
    ArrayList<BufferedImage> player2_imgs_o = new ArrayList<BufferedImage>();

    public void move(){

                if (status == App.STATUS_mv) {
                    if (isUp || isDown || isRight || isLeft) {
                        status = App.STATUS_mv;
                    } else {
                        status = App.STATUS_zl;
                    }

                    if (isUp) {
                        y -= Player_speed / 2;
                        if (y <= 80 ) {
                            y = 80;
                        }

                    }
                    if (isLeft && dir == App.LEFT) {
                        x -= Player_speed;
                        if (x <= -200) {
                            x = -200;
                        }
                    }
                    if (isRight && dir == App.RIGHT) {
                        x += Player_speed;
                        if (x >= 1000) {
                            x = 1000;
                        }
                    }
                    if (isDown) {
                        y += Player_speed / 2;
                        if (y >= 280) {
                            y = 280;
                        }
                    }

                }



    }



    public Player(){

        //加载血条：
        img_Hp_Mask = ImageUtil.getImage("/img/Hp/HpMask_1.png");
        img_Hp_Fill = ImageUtil.getImage("/img/Hp/HpFill.png");
        img_Mp= ImageUtil.getImage("/img/Hp/Mp.png");
        img_Character1 = ImageUtil.getImage("/img/img_hit/01.png");
        img_Character2 = ImageUtil.getImage("/img/ghost_hit/001.png");
        width_Hp_Mask = img_Hp_Mask.getWidth();
        height_Hp_Mask = img_Hp_Mask.getHeight();
        width_Hp_Fill = img_Hp_Fill.getWidth();
        height_Hp_Fill = img_Hp_Fill.getHeight();
        width_Mp = img_Mp.getWidth();
        height_Mp = img_Mp.getHeight();


        //加载角色1的集合
        for(int i=1;i<=8;i++){
            //加载站立动画
            img = ImageUtil.getImage("/img/img_stand/00"+i+".png");
            imgs_zl_r.add(img);
            img = ImageUtil.getImage("/img/img_stand/01"+i+".png");
            imgs_zl_l.add(img);
            // 加载行走的动画图片
            img = ImageUtil.getImage("/img/img_run/00"+i+".png");
            imgs_mv_r.add(img);
            img = ImageUtil.getImage("/img/img_run/01"+i+".png");
            imgs_mv_l.add(img);
        }
        for(int i=1;i<=12;i++){
            // 加载攻击的动画图片
            img = ImageUtil.getImage("/img/img_hit/0"+i+".png");
            imgs_j_r.add(img);
            img = ImageUtil.getImage("/img/img_hit/0"+i+"_"+i+".png");
            imgs_j_l.add(img);
        }
        //被攻击
        for(int i=1;i<=4;i++){
            // 加载攻击的动画图片
            img = ImageUtil.getImage("/img/img_be_hit/00"+i+".png");
            imgs_be_hit_r.add(img);
            img = ImageUtil.getImage("/img/img_be_hit/01"+i+".png");
            imgs_be_hit_l.add(img);
        }
        //加载o特效的动画
        for(int i=1;i<=26;i++){
            // 加载攻击的动画图片
            if (i<=9){
                img = ImageUtil.getImage("/img/o_tx/90255-100"+i+".png");
                imgs_o.add(img);
            }else if(i>=10){
                img = ImageUtil.getImage("/img/o_tx/90255-10"+i+".png");
                imgs_o.add(img);
            }
        }

        //加载角色2的集合
        for(int i=1;i<=8;i++){
            //加载站立动画
            img = ImageUtil.getImage("/img/ghost_zl/00"+i+".png");
            player2_imgs_zl_r.add(img);
            img = ImageUtil.getImage("/img/ghost_zl/00"+i+"_1.png");
            player2_imgs_zl_l.add(img);

            // 加载行走的动画图片
            img = ImageUtil.getImage("/img/ghost_move/00"+i+".png");
            player2_imgs_mv_r.add(img);
            img = ImageUtil.getImage("/img/ghost_move/00"+i+"_1.png");
            player2_imgs_mv_l.add(img);

        }
        for(int i=1;i<=12;i++){
            // 加载攻击的动画图片
            if (i < 10) {
                img = ImageUtil.getImage("/img/ghost_hit/00"+i+".png");
                player2_imgs_j_r.add(img);
                img = ImageUtil.getImage("/img/ghost_hit/00"+i+"_1.png");
                player2_imgs_j_l.add(img);
            }
            if (i>=10){
                img = ImageUtil.getImage("/img/ghost_hit/0"+i+".png");
                player2_imgs_j_r.add(img);
                img = ImageUtil.getImage("/img/ghost_hit/0"+i+"_1.png");
                player2_imgs_j_l.add(img);
            }
        }
        //被攻击
        for(int i=1;i<=4;i++){
            // 加载攻击的动画图片
            img = ImageUtil.getImage("/img/ghost_be_hit/00"+i+".png");
            player2_imgs_be_hit_r.add(img);
            img = ImageUtil.getImage("/img/ghost_be_hit/00"+i+"_1.png");
            player2_imgs_be_hit_l.add(img);
        }
        //加载o特效的动画
        for(int i=1;i<=22;i++){
            // 加载攻击的动画图片
            if (i<=9){
                img = ImageUtil.getImage("/img/player2_o_tx/90367-100"+i+".png");
                player2_imgs_o.add(img);
            }else if(i>=10){
                img = ImageUtil.getImage("/img/player2_o_tx/90367-10"+i+".png");
                player2_imgs_o.add(img);
            }
        }



        img = ImageUtil.getImage("/img/img_stand/001.png");
        width = img.getWidth();
        height = img.getHeight();
        x = 200;//-200
        y=150;//300
    }

    public void  drawSelf(Graphics g){
        if (character==App.CHARACTER_1){
            if (status==App.STATUS_O){
                g.drawImage(img,x-300,y-210,width*2,height*2,null);
                g.setColor(new Color(37,6,21));
                g.drawImage(img_Hp_Fill,60,750-height_Hp_Mask,width_Hp_Fill,height_Hp_Fill,null);
                g.fillArc(13+60,760-height_Hp_Mask,110,110,9*hp-90,-18*hp+360);
                g.drawImage(img_Hp_Mask,60,750-height_Hp_Mask,width_Hp_Mask,height_Hp_Mask,null);
                g.drawImage(img_Character1,-290,750-height_Hp_Mask-260,width,height,null);

            }else {
                g.drawImage(img, x, y, width, height, null);
                //g.drawRect(x+260-10,y+260-30,100,10);
                //g.setColor(new Color(205,38,38));
                //g.fillRect(x+260-10,y+260-30,100/20*hp,10);
                g.setColor(new Color(37,6,21));
                g.drawImage(img_Hp_Fill,60,750-height_Hp_Mask,width_Hp_Fill,height_Hp_Fill,null);
                g.fillArc(13+60,760-height_Hp_Mask,110,110,9*hp-90,-18*hp+360);
                g.drawImage(img_Hp_Mask,60,750-height_Hp_Mask,width_Hp_Mask,height_Hp_Mask,null);
                g.drawImage(img_Character1,-290,750-height_Hp_Mask-260,width,height,null);

            }
        }
        if (character==App.CHARACTER_2){
            if (status==App.STATUS_O){
                g.drawImage(img,x-300,y-210,width*2,height*2,null);
                g.drawImage(img_Character2,-130,-110,width/2,height/2,null);
                g.drawImage(img_Mp,75,40,width_Mp,height_Mp,null);
                g.setColor(Color.black);
                g.drawRect(75,40,width_Mp,height_Mp);
                g.setColor(Color.white);
                g.fillRect(312/100*Mp+75,40,-312/100*Mp+312,17);
                g.setColor(Color.black);
            }else {
                g.drawImage(img, x, y, width, height, null);
                g.drawImage(img_Character2,-130,-110,width/2,height/2,null);
                g.drawImage(img_Mp,75,40,width_Mp,height_Mp,null);
                g.setColor(Color.black);
                g.drawRect(75,40,width_Mp,height_Mp);
                g.setColor(Color.white);
                g.fillRect(312/100*Mp+75,40,-312/100*Mp+312,17);
                g.setColor(Color.black);

            }
        }

    }
    //显示动画的方法
    //角色1
    int index_zl_r = 0; // 用来从向右站立动画集合中取图片的索引
    int index_zl_l = 0; // 用来从向左站立动画集合中取图片的索引
    int index_mv_r = 0; // 用来从向右行走动画集合中取图片的索引
    int index_mv_l = 0; // 用来从向左行走动画集合中取图片的索引
    int index_j=0;
    int index_be_hit_r = 0; // 用来从向右攻击动画集合中取图片的索引
    int index_be_hit_l = 0; // 用来从向左攻击动画集合中取图片的索引
    int index_o=0;

    //角色2
    int player2_index_zl_r = 0; // 用来从向右站立动画集合中取图片的索引
    int player2_index_zl_l = 0; // 用来从向左站立动画集合中取图片的索引
    int player2_index_mv_r = 0; // 用来从向右行走动画集合中取图片的索引
    int player2_index_mv_l = 0; // 用来从向左行走动画集合中取图片的索引
    int player2_index_j=0;
    int player2_index_be_hit_r = 0; // 用来从向右攻击动画集合中取图片的索引
    int player2_index_be_hit_l = 0; // 用来从向左攻击动画集合中取图片的索引
    int player2_index_o=0;

    public void animation(){
        if (character==App.CHARACTER_1){
//            System.out.println("1");
            if(status != App.STATUS_be_hit ){
                if (status != App.STATUS_J&&status != App.STATUS_K&&status != App.STATUS_O) {
                    if (isUp || isDown || isRight || isLeft) {
                        status = App.STATUS_mv;
                    } else {
                        status = App.STATUS_zl;
                    }
                }

                // 如果当前的状态是站立状态
                if(status==App.STATUS_zl) {
                    // 如果方向向右
                    if (dir == App.RIGHT) {
                        // 让索引自增
                        index_zl_r++;
                        if (index_zl_r >= imgs_zl_r.size()) {
                            // 索引归0，重新从第一张图片开始取
                            index_zl_r = 0;
                        }
                        img = imgs_zl_r.get(index_zl_r);
                    }
                    // 如果方向向左
                    if (dir == App.LEFT) {
                        // 让索引自增
                        index_zl_l++;
                        if (index_zl_l >= imgs_zl_l.size()) {
                            // 索引归0，重新从第一张图片开始取
                            index_zl_l = 0;
                        }
                        img = imgs_zl_l.get(index_zl_l);
                    }
                }
                // 如果当前的状态是移动状态
                if(status==App.STATUS_mv){
                    // 如果当前的方向是向左
                    if(dir==App.LEFT){
                        // 索引增加
                        index_mv_l ++;
                        if(index_mv_l>=imgs_mv_l.size()){
                            index_mv_l =0;
                        }
                        img = imgs_mv_l.get(index_mv_l);
                    }

                    // 如果当前的方向是向右
                    if(dir==App.RIGHT){
                        // 索引增加
                        index_mv_r ++;
                        if(index_mv_r>=imgs_mv_r.size()){
                            index_mv_r =0;

                        }

                        img = imgs_mv_r.get(index_mv_r);
                    }
                }


            }
            //***


            //如果当前是被攻击状态
            if(status==App.STATUS_be_hit){


                // 如果当前的方向是向左
                if(dir==App.LEFT){
                    // 索引增加
                    index_be_hit_l ++;
                    if(index_be_hit_l>=imgs_be_hit_l.size()){
                        status=App.STATUS_zl;
                        index_be_hit_l=0;

                    }
                    img = imgs_be_hit_l.get(index_be_hit_l);
                }

                // 如果当前的方向是向右
                if(dir==App.RIGHT){
                    // 索引增加
                    index_be_hit_r ++;
                    if(index_be_hit_r>=imgs_be_hit_r.size()){
                        status=App.STATUS_zl;
                        index_be_hit_r=0;
                    }
                    img = imgs_be_hit_r.get(index_be_hit_r);
                }

            }

            //如果当前是K姿态
            if(status==App.STATUS_K){
                if (dir==App.RIGHT){img=ImageUtil.getImage("/img/img_hit/05.png");}
                if (dir==App.LEFT){img=ImageUtil.getImage("/img/img_hit/05_5.png");}
            }

            //如果当前是J姿态
            if (status==App.STATUS_J){
                if (dir==App.RIGHT){
                    index_j++;
                    if (index_j>=imgs_j_r.size()){
                        status=App.STATUS_zl;
                        index_j=0;
                    }
                    img= imgs_j_r.get(index_j);
                }
                if (dir==App.LEFT){
                    index_j++;
                    if (index_j>=imgs_j_r.size()){
                        status=App.STATUS_zl;
                        index_j=0;
                    }
                    img= imgs_j_l.get(index_j);
                }
            }

            //如果当前是o姿态
            if (status==App.STATUS_O){
                index_o++;
                if (index_o>=imgs_o.size()){
                    status=App.STATUS_zl;
                    index_o=0;
                }
                img= imgs_o.get(index_o);
//            System.out.println("hello");
            }

        }
        if (character==App.CHARACTER_2){
            if (status != App.STATUS_J&&status != App.STATUS_K&&status != App.STATUS_O) {
                if (isUp || isDown || isRight || isLeft) {
                    status = App.STATUS_mv;
                } else {
                    status = App.STATUS_zl;
                }
            }

            // 如果当前的状态是站立状态
            if(status==App.STATUS_zl) {
                // 如果方向向右
                if (dir == App.RIGHT) {
                    // 让索引自增
                    player2_index_zl_r++;
                    if (player2_index_zl_r >= player2_imgs_zl_r.size()) {
                        // 索引归0，重新从第一张图片开始取
                        player2_index_zl_r = 0;
                    }
                    img = player2_imgs_zl_r.get(player2_index_zl_r);
                }
                // 如果方向向左
                if (dir == App.LEFT) {
                    // 让索引自增
                    player2_index_zl_l++;
                    if (player2_index_zl_l >= player2_imgs_zl_l.size()) {
                        // 索引归0，重新从第一张图片开始取
                        player2_index_zl_l = 0;
                    }
                    img = player2_imgs_zl_l.get(player2_index_zl_l);
                }
            }
            // 如果当前的状态是移动状态
            if(status==App.STATUS_mv){
                // 如果当前的方向是向左
                if(dir==App.LEFT){
                    // 索引增加
                    player2_index_mv_l ++;
                    if(player2_index_mv_l>=player2_imgs_mv_l.size()){
                        player2_index_mv_l =0;
                    }
                    img = player2_imgs_mv_l.get(player2_index_mv_l);
                }

                // 如果当前的方向是向右
                if(dir==App.RIGHT){
                    // 索引增加
                    player2_index_mv_r ++;
                    if(player2_index_mv_r>=player2_imgs_mv_r.size()){
                        player2_index_mv_r =0;

                    }

                    img = player2_imgs_mv_r.get(player2_index_mv_r);
                }
            }
            // ***



            //如果当前是被攻击状态
            if(status==App.STATUS_be_hit){
                // 如果当前的方向是向左
                if(dir==App.LEFT){
                    // 索引增加
                    player2_index_be_hit_l ++;
                    if(player2_index_be_hit_l>=player2_imgs_be_hit_l.size()){
                        status=App.STATUS_zl;
                        player2_index_be_hit_l=0;
                    }
                    img = player2_imgs_be_hit_l.get(player2_index_be_hit_l);
                }

                // 如果当前的方向是向右
                if(dir==App.RIGHT){
                    // 索引增加
                    player2_index_be_hit_r ++;
                    if(player2_index_be_hit_r>=player2_imgs_be_hit_r.size()){
                        status=App.STATUS_zl;
                        player2_index_be_hit_r=0;
                    }
                    img = player2_imgs_be_hit_r.get(player2_index_be_hit_r);
                }

            }
//         //如果当前是K姿态
            if(status==App.STATUS_K){
                if (dir==App.RIGHT){img=ImageUtil.getImage("/img/ghost_hit/003.png");}
                if (dir==App.LEFT){img=ImageUtil.getImage("/img/ghost_hit/003_1.png");}
            }
            //如果当前是J姿态
            if (status==App.STATUS_J){
                if (dir==App.RIGHT){
                    player2_index_j++;
                    if (player2_index_j>=player2_imgs_j_r.size()){
                        status=App.STATUS_zl;
                        player2_index_j=0;
                    }
                    img= player2_imgs_j_r.get(player2_index_j);
                }
                if (dir==App.LEFT){
                    player2_index_j++;
                    if (player2_index_j>=player2_imgs_j_r.size()){
                        status=App.STATUS_zl;
                        player2_index_j=0;
                    }
                    img= player2_imgs_j_l.get(player2_index_j);
                }
            }
            //如果当前是o姿态
            if (status==App.STATUS_O){
                player2_index_o++;
                if (player2_index_o>=player2_imgs_o.size()){
                    status=App.STATUS_zl;
                    player2_index_o=0;
                }
                img= player2_imgs_o.get(player2_index_o);
            }
        }



    }

    //***
    public boolean hitByMonster(monster mon){
        //和怪物被攻击相同，也是一个碰撞箱的判定
        //判定一次棒子的攻击,棒子的坐标(x+380,y+310)width 170 height 60
        //左边的坐标：（x+80,y+310)
        // 人物坐标：(x+290,y+260) width 60 height 150
        //人物脚的坐标(x+320,y+410)  怪物的脚(x+310,y+410)

        if (x <= mon.x){
            attackDir = App.RIGHT;
        }
        if(x > mon.x){
            attackDir = App.LEFT;
        }

        if(attackDir == App.RIGHT && x+290+60 >= mon.x +80
                && x+290 <= mon.x+170+80 && status != App.STATUS_K
                && status != App.STATUS_O &&
                Math.pow(mon.y-y,2) <= Math.pow(10*6,2)){
            //幽灵形态才扣血  人物从右侧被攻击
            if( character ==App.CHARACTER_1 && mon.index_hit_and_run_l == 8 ){
                hp = hp -2;
                Mp3Player man_be_hit=new Mp3Player("/sound/man_be_hit.mp3",false);
                man_be_hit.start();
            }
            return true;
        }
        if(attackDir == App.LEFT && mon.x+380+170>=x+290
                && mon.x + 380<=x+290+60 && status != App.STATUS_K
                &&  status != App.STATUS_O &&
                Math.pow(mon.y-y,2) <= Math.pow(Player_speed*6,2)){
            //幽灵形态才扣血  人物从左侧被攻击
            if(character ==App.CHARACTER_1 && mon.index_hit_and_run_r == 8 ){
                hp = hp - 2;
                Mp3Player man_be_hit=new Mp3Player("/sound/man_be_hit.mp3",false);
                man_be_hit.start();
            }
            return  true;
        }
        return false;
    }



    public boolean hitByBalrog(Balrog bal){
        //和怪物被攻击相同，也是一个碰撞箱的判定
        //判定一次棒子的攻击,棒子的坐标(x+380,y+310)width 170 height 60
        //左边的坐标：（x+80,y+310)
        // 人物坐标：(x+290,y+260) width 60 height 150
        //人物脚的坐标(x+320,y+410)  怪物的脚(x+310,y+410)

        if (x <= bal.x){
            attackDir = App.RIGHT;
        }
        if(x > bal.x){
            attackDir = App.LEFT;
        }

        if(attackDir == App.RIGHT && x+290+60 >= bal.x +80
                && x+290 <= bal.x+170+80 && status != App.STATUS_K
                && status != App.STATUS_O &&
                Math.pow(bal.y-y,2) <= Math.pow(10*2,2)){
            //人物从右侧被攻击
            if( character ==App.CHARACTER_1 && bal.index_hit_and_run_l == 8 ){
                hp = hp -2;
            }
            return true;
        }
        if(attackDir == App.LEFT && bal.x+380+170>=x+290
                && bal.x + 380<=x+290+60 && status != App.STATUS_K
                &&  status != App.STATUS_O &&
                Math.pow(bal.y-y,2) <= Math.pow(Player_speed*2,2)){
            //人物从左侧被攻击
            if( character ==App.CHARACTER_1 && bal.index_hit_and_run_r == 8 ){
                hp = hp - 2;
            }
            return  true;
        }
        return false;
    }

}
