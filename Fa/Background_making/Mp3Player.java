package Background_making;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;

/**
 * MP3格式音乐播放器
 */
public class Mp3Player extends Thread{

    private String path; //  音乐文件的路径
    private boolean isLoop; // 是否循环播放

    private Player player;// 音乐播放器组件

    public Mp3Player(String path, boolean isLoop){
        this.path = path;
        this.isLoop = isLoop;
    }

    /**
     * 加载音乐并播放音乐
     */
    public void run(){
        do{
            // 加载音乐文件
            BufferedInputStream in =
                    new BufferedInputStream(
                            Mp3Player.class.getResourceAsStream(path));
            try {
                // 创建音乐播放器组件，并指定需要播放的音乐
                player = new Player(in);
                // 播放音乐
                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }while(isLoop);
    }

    /**
     * 关闭音乐播放器
     */
    public void stopMusic(){
        // 停止循环播放的音乐
        isLoop = false;
        // 关闭音乐播放器组件
        player.close();
        this.interrupt(); // 强制关闭
    }
}
