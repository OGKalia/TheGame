package Background_making;


/*
* 游戏的配置文件，可以通过修改该文件的数据来调整游戏的难易程度
* */
public interface App {
    /* 游戏名称*/
    String name = "黑暗世纪";

    int SCREEN_WIDTH = 1440;
    int SCREEN_HEIGHT= 780;

    //移动方向
    /** 向左*/
    int LEFT = 1;
    /** 向右*/
    int RIGHT = 2;

    //玩家移动的速度
    int PLAYER_SPEED = 15;
    int STATUS_zl = 1;//站立
    int STATUS_mv = 2;//行走
    int STATUS_be_hit = 3;//被攻击
    int STATUS_J = 4;//攻击
    int STATUS_K=5; //是否处于防御姿态
    int STATUS_O=6;

    int J_TX=1;
    int J_TX_N=0;

    int K_TX=1;
    int K_TX_N=0;
    int Admonish_range = 400;
    int Monster_Speed = 4;

    int CHARACTER_1=1;
    int CHARACTER_2=2;

    int Balrog_Speed = 2;
    int Balrog = 2;
    int Monster = 1;

    //怪物计数器
    int Counter_Mon=12;
    int Counter_Balrog=12;

    int K_SOUND=1;
    int K_SOUND_N=0;




}
