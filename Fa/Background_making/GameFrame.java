package Background_making;

import javax.swing.*;


public class GameFrame extends JFrame {

    public GameFrame(){
        setTitle(App.name);
        // 设置大小和尺寸
        setSize(App.SCREEN_WIDTH, App.SCREEN_HEIGHT);
//        setLocation(300,300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        //创建窗体,使用模具，新建一个窗体
        GameFrame frame = new GameFrame();
        GamePanel panel = new GamePanel(frame);
        //将面板加入到窗体中
        frame.add(panel);

        frame.setVisible(true);

    }
}
