package Background_making;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel {
   // 使用模具创建背景
    Background bg = new Background();
    BufferedImage img_gameOver;
    Player player = new Player();
    J_attack j_attack=new J_attack();
    K_tx k_tx=new K_tx();

    ArrayList<monster> eps = new ArrayList<>();
    // 游戏是否结束的开关
    boolean gameOver = false;
    boolean gameStart = false;
    boolean k = false;
    //出怪开关，走到底才出怪
    boolean mon_show = false;
    boolean balrog_show = false;
    portal por =new portal();
    Success success = new Success();
    boolean game_success = false;
    Mp3Player background_sound=new Mp3Player("/sound/background.mp3",true);
    int k_sound=App.K_SOUND_N;
//    Mp3Player sheild=new Mp3Player("/sound/sheild.mp3",false);



    ArrayList<Balrog> bals = new ArrayList<>();
    //判定出什么怪
    int type_mon = App.Monster;
    Start start = new Start();

    //怪物计数器
    int counter_mon_show=0;
    int counter_mon_remove=0;
    int counter_balrog_show=0;
    int counter_balrog_remove=0;

    go go = new go();//通关


    public GamePanel(GameFrame frame){
       background_sound.start();

        MouseAdapter adapter1 = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(!gameStart){
                    gameStart = true;
                }
                if(gameOver){
                    gameOver = false;

                    player = new Player();
                    eps.clear();
                    bals.clear();

                }
                repaint();
            }
        };
        addMouseListener(adapter1);
        addMouseMotionListener(adapter1);




        img_gameOver = ImageUtil.getImage("/img/Start/GameOver.bmp");



        KeyAdapter adapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int code =e.getKeyCode();
                //E
                if(code == KeyEvent.VK_E){
                    game_success = true;
                }

                //KeyEvent封装了所右按键的数值
                if(code == KeyEvent.VK_B){
                    player.hp =20;
                    player.Mp = 100;
                }
                if(code == KeyEvent.VK_UP || code==KeyEvent.VK_W){
                    player.isUp = true;
                }
                //下键或者s健
                if(code==KeyEvent.VK_DOWN ||code==KeyEvent.VK_S ){
                    player.isDown = true;
                }
                //左键，A
                if(code==KeyEvent.VK_LEFT ||code==KeyEvent.VK_A ) {
                    player.dir = App.LEFT;
                    j_attack.dir=App.LEFT;
                    k_tx.dir=App.LEFT;
                    player.isLeft = true;

                }
                //右键，D健
                if(code==KeyEvent.VK_RIGHT ||code==KeyEvent.VK_D ){
                    player.dir = App.RIGHT;
                    j_attack.dir=App.RIGHT;
                    k_tx.dir=App.RIGHT;
                    player.isRight = true;
                }
                //攻击 J
                if(code==KeyEvent.VK_J ) {
                    if(player.status!=App.STATUS_K&&player.status!=App.STATUS_O)
                    {player.status = App.STATUS_J;}
                    if(player.status!=App.STATUS_K&&j_attack.status==App.J_TX_N&&player.status!=App.STATUS_O){
                        j_attack.status=App.J_TX;
                        j_attack.j_attack_animation();
                    }


                }
                //被攻击 B键
                if(code==KeyEvent.VK_B ){
                    player.status=App.STATUS_be_hit;
                }
                //防御姿态K键
                if(code==KeyEvent.VK_K ){
                    if (player.status!=App.STATUS_J&&k_tx.status!=App.K_TX&&player.status!=App.STATUS_O){
                    player.status=App.STATUS_K;
                    k_tx.status=App.K_TX;
                    k_tx.k_animation();
//                    sheild.start();
                    }
                }
                if(code==KeyEvent.VK_O ){
                    if (player.status!=App.STATUS_J&&k_tx.status!=App.K_TX&&player.status!=App.STATUS_K){
                        if (player.status!=App.STATUS_O){
                            if (player.character==App.CHARACTER_1){
                                Mp3Player dazhao_1=new Mp3Player("/sound/dazhao_1.mp3",false);
                                dazhao_1.start();
                            }else if(player.character==App.CHARACTER_2){
                                Mp3Player dazhao_2=new Mp3Player("/sound/dazhao_2.mp3",false);
                                dazhao_2.start();
                            }

                        }
                        player.status=App.STATUS_O;

                    }
                }
                if(code==KeyEvent.VK_L){
                    if (j_attack.character==App.CHARACTER_1
                    &&k_tx.character==App.CHARACTER_1&&player.character==App.CHARACTER_1){
                        j_attack.character=App.CHARACTER_2;
                        k_tx.character=App.CHARACTER_2;
                        player.character=App.CHARACTER_2;
                    }else{
                        j_attack.character=App.CHARACTER_1;
                        k_tx.character=App.CHARACTER_1;
                        player.character=App.CHARACTER_1;
                    }
                }
                //刷新界面
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                int code =e.getKeyCode();
                //KeyEvent封装了所右按键的数值
                if(code == KeyEvent.VK_UP || code==KeyEvent.VK_W){
                    //玩家向上移动
                    player.isUp = false;
                }
                //下键或者s健
                if(code==KeyEvent.VK_DOWN ||code==KeyEvent.VK_S ){
                    player.isDown = false;
                }
                //左键，A
                if(code==KeyEvent.VK_LEFT ||code==KeyEvent.VK_A ){
                    player.isLeft = false;
                }
                //右键，D健
                if(code==KeyEvent.VK_RIGHT ||code==KeyEvent.VK_D ){
                    player.isRight = false;
                }
                //防御姿态K键
                if(code==KeyEvent.VK_K ){
                    player.status=App.STATUS_zl;
                    k_tx.status=App.K_TX_N;

                }

                //刷新界面
                repaint();
            }

        };
        frame.addKeyListener(adapter);


        //设置背景
        setBackground(Color.white);
        //手动导入Timer import java.util.Timer;
        Timer timer = new Timer();
        //设置计时器任务
        timer.schedule(new TimerTask() {
           @Override
            public  void run(){
               if(gameStart && !gameOver && !game_success){
                   player.move();//人物移动
                   bg_move();//背景移动

                   //到达-375出第一波，到达-720出第二波
                   if(bg.x1<=-375){
                       mon_show=true;
                   }
                   if(bg.x1<=-720){
                       balrog_show=true;
                   }

                   por.animation();
                   player.animation();//人物动画

                   if(player.hp<=0 || player.Mp<=0){
                       gameOver = true;
                   }

                   //将人物的坐标传给j特效
                   j_attack.man_x= player.x;
                   j_attack.man_y= player.y;
                   k_tx.man_x= player.x;
                   k_tx.man_y= player.y;

                   //判断怪物类型
                   if(type_mon == App.Monster){
                       for (int i = 0; i < eps.size(); i++) {
                           // 获取当前的怪物
                           monster monster = eps.get(i);
                           // 让当前的怪物移动
                           monster.move(player);
                           monster.animation(player);
                           // index_J==6 时，怪物移出屏幕


                           if (monster.hitBy(player) ) {
                               // 删除怪物
                               if(player.index_j == 7 || player.player2_index_j == 7){

                                   monster.status = App.STATUS_be_hit;
                               }

                               if(monster.hp <= 0){

                                   counter_mon_remove++;
                                   //System.out.println("counter_mon_remove: "+counter_mon_remove);

                                   if(counter_mon_remove == App.Counter_Mon){
                                       k = true;
                                       type_mon=App.Balrog;
                                       //System.out.println("k: "+k);
                                   }
                                   eps.remove(monster);

                               }
                           }

                           //
                           if(player.hitByMonster(monster) ){
                               if(monster.index_hit_and_run_l == 3||
                                       monster.index_hit_and_run_r==3){
                                   player.status = App.STATUS_be_hit;


                               }
                           }
                           //

                       }
                   }



                   if(type_mon == App.Balrog){
                       for (int i = 0; i < bals.size(); i++) {
                           // 获取当前的怪物

                           // index_J==6 时，怪物移出屏幕
                           Balrog bal = bals.get(i);
                           bal.move(player);
                           bal.animation(player);

                           if (bal.hitBy(player) ) {
                               // 删除怪物
                               if(player.index_j == 7 || player.player2_index_j == 7){

                                   bal.status = App.STATUS_be_hit;
                               }

                               if(bal.hp <= 0){

                                   counter_balrog_remove++;

                                   bals.remove(bal);
                               }
                           }

                           //
                           if(player.hitByBalrog(bal) ){
                               if(bal.index_hit_and_run_l == 3||
                                       bal.index_hit_and_run_r==3){
                                   player.status = App.STATUS_be_hit;
                               }
                           }
                           //

                       }
                   }


               }




               repaint();
           }
        },0,50);
        //怪物出场

        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!gameOver || !game_success){
                     //怪物入场
                    if(type_mon == App.Monster && counter_mon_show<App.Counter_Mon && mon_show){
                        counter_mon_show++;
                        mon_Enter();
                    }
                    if(k){
                        type_mon=App.Balrog;

                    }
                    if(type_mon == App.Balrog && counter_balrog_show<App.Counter_Balrog && balrog_show){
                        counter_balrog_show++;
                        bal_Enter();
                    }

                }

            }

        },0,1500);
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!gameOver && gameStart) {

                    if (player.character == App.CHARACTER_2) {
                        player.Mp--;
                    }
                }
                repaint();

            }

        }, 0, 500);


    }

    public void mon_Enter(){
        monster monster = new monster();
        eps.add(monster);
    }

    public void bal_Enter(){
        Balrog bal = new Balrog();
        bals.add(bal);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (!gameStart ) {
            start.drawSelf(g);
            g.setColor(Color.white);
            g.setFont(new Font("隶书",Font.ITALIC,20));
            g.drawString("点击任意处开始游戏",15,60);
        }
        if (gameOver) {
            g.drawImage(img_gameOver, 0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT, null);
            g.setColor(Color.white);
            g.setFont(new Font("隶书",Font.ITALIC,20));
            g.drawString("点击重新开始",0,20);
        }



        if (gameStart && !gameOver){
            bg.drawSelf(g);
            //第一波怪打完，且第二波怪的第二只刚出，显示go  && counter_balrog_show<2
            if(k && counter_balrog_show <=1){
                go.drawSelf(g);
            }
            if(counter_balrog_remove==App.Counter_Balrog){
                por.drawSelf(g);
                if(game_success){
                    success.drawSelf(g);
                }
            }

            if(type_mon == App.Monster){
                //***
                int list[]=new int[eps.size()];
                for(int i=0;i<eps.size();i++){
                    list[i]=i;
                }//初始化
                //排序
                for(int i=0;i<eps.size()-1;i++){

                    int min = i;
                    for(int j=i+1;j<eps.size();j++){
                        if(eps.get(j).y<eps.get(i).y){
                            min=j;
                        }
                    }
                    if(min!=i){
                        //i和min交换
                        int t=list[i];
                        list[i]=list[min];
                        list[min]=t;
                    }

                }
                //分三种情况，人在怪物集合的最里层，中间层，最外层

                //最里层
                if(eps.size()!=0&&eps.get(0).y>=player.y){
                    if(!game_success){
                        player.drawSelf(g);
                    }
                    for(int i=0;i<eps.size();i++){
                        eps.get(i).drawSelf(g);
                    }


                } else if(eps.size()!=0&&eps.get(eps.size()-1).y<=player.y){
                    //最外层300+rd.nextInt(100)
                    for(int i=0;i<eps.size();i++){
                        eps.get(i).drawSelf(g);
                    }

                    if(!game_success){
                        player.drawSelf(g);
                    }
                } else if(eps.size()!=0 &&  eps.get(0).y < player.y && eps.get(eps.size()-1).y > player.y) {
                    //中间层
                    for(int i=0;i<eps.size();i++){

                        if(eps.get(i).y<player.y){
                            eps.get(i).drawSelf(g);

                        }
                    }
                    if(!game_success){
                        player.drawSelf(g);
                    }
                    for(int i=0;i<eps.size();i++){

                        if(eps.get(i).y>=player.y){
                            eps.get(i).drawSelf(g);


                        }
                    }
                } else {
                    if(!game_success){
                        player.drawSelf(g);
                    }
                }
                //***
            }

            if(type_mon == App.Balrog){
                //***
                int list[]=new int[bals.size()];
                for(int i=0;i<bals.size();i++){
                    list[i]=i;
                }//初始化
                //排序
                for(int i=0;i<bals.size()-1;i++){

                    int min = i;
                    for(int j=i+1;j<bals.size();j++){
                        if(bals.get(j).y<bals.get(i).y){
                            min=j;
                        }
                    }
                    if(min!=i){
                        //i和min交换
                        int t=list[i];
                        list[i]=list[min];
                        list[min]=t;
                    }

                }
                //分三种情况，人在怪物集合的最里层，中间层，最外层
                //中间层

                //最里层
                if(bals.size()!=0&&bals.get(0).y>=player.y){
                    if(!game_success){
                        player.drawSelf(g);
                    }
                    for(int i=0;i<bals.size();i++){
                        bals.get(i).drawSelf(g);
                    }

                } else if(bals.size()!=0&&bals.get(bals.size()-1).y<=player.y){
                    //最外层
                    for(int i=0;i<bals.size();i++){
                        bals.get(i).drawSelf(g);

                    }
                    if(!game_success){
                        player.drawSelf(g);
                    }

                } else if(bals.size()!=0 && bals.get(0).y < player.y && bals.get(bals.size()-1).y > player.y) {
                    //中间层
                    for(int i=0;i<bals.size();i++){

                        if(bals.get(i).y<player.y){
                            bals.get(i).drawSelf(g);

                        }
                    }
                    if(!game_success){
                        player.drawSelf(g);
                    }

                    for(int i=0;i<bals.size();i++){

                        if(bals.get(i).y>=player.y){
                            bals.get(i).drawSelf(g);

                        }
                    }
                } else{
                    if(!game_success){
                        player.drawSelf(g);
                    }
                }
                //***

            }

        }


            // 绘制所有的怪




//        for(int i=0;i<eps.size();i++){
//            g.setColor(Color.black);
//            g.drawRect(eps.get(i).x+260,eps.get(i).y+260,100,150);//怪
//        }


//        g.drawRect(player.x+260-410,player.y+220,410,70);
//        g.setColor(Color.green);
//        g.drawRect(player.x+360,player.y+220,410,70);
//        g.setColor(Color.red);
//        g.drawRect(player.x+260,player.y+220,100,150);


        //脚的坐标:520,560
        //脚坐标的一般方程:(x+290+30,y+260+150)
        //判定点椭圆方程：脚的坐标设定为：x1和y1
        //(x1)^2/340^2 + (x2)^2/150^2 = 1



        j_attack.drawSelf(g);
        k_tx.drawSelf(g);
        if (player.character==App.CHARACTER_1){
            if (k_tx.status==App.K_TX){
                if (k_tx.dir==App.RIGHT) {
                    BufferedImage img=ImageUtil.getImage("/img/k_tx/sheild.png");
                    g.drawImage(img, player.x + 160, player.y + 250, k_tx.width + 50, k_tx.height + 130, null);
                }
                if(k_tx.dir==App.LEFT){
                    BufferedImage img=ImageUtil.getImage("/img/k_tx/sheild_1.png");
                    g.drawImage(img, player.x + 240, player.y + 250, k_tx.width + 50, k_tx.height + 130, null);
                }
            }
        }
        if (player.character==App.CHARACTER_2){
            if (k_tx.status==App.K_TX){
                if (k_tx.dir==App.RIGHT) {
                    BufferedImage img=ImageUtil.getImage("/img/k_tx/sheild.png");
                    g.drawImage(img, player.x + 200, player.y + 220, k_tx.width + 50, k_tx.height + 130, null);
                }
                if(k_tx.dir==App.LEFT){
                    BufferedImage img=ImageUtil.getImage("/img/k_tx/sheild_1.png");
                    g.drawImage(img, player.x + 220, player.y + 220, k_tx.width + 50, k_tx.height + 130, null);
                }
            }
        }

    }

    public void bg_move(){
        if(player.status!=App.STATUS_J&&player.status!=App.STATUS_K){

            player.Player_speed = App.PLAYER_SPEED;

            if(
                    player.x > 740 && player.x < 900 && player.isRight && bg.x1>-375 && (player.status !=1) ) {
                //右边判定区

                bg.x1 -= App.PLAYER_SPEED;
                player.Player_speed=0;
            }

            if( k &&
                    player.x > 740 && player.x < 900 && player.isRight && bg.x1>-720 && (player.status !=1) ) {
                //右边判定区

                bg.x1 -= App.PLAYER_SPEED;
                player.Player_speed=0;
            }

            if(
                    player.x <160 && player.x >0 && player.isLeft && bg.x1 <0 && (player.status !=1)){
                //左边判定区
                bg.x1 += App.PLAYER_SPEED;
                player.Player_speed=0;
            }

        }
//

    }

}
