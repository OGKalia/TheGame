package Background_making;

/*
* 加载图片的工具类
* */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageUtil {
    /*
    * 加载图片的工具方法
    * @param path 要读取的图片的路径
    * */
    public static BufferedImage getImage(String path){

        try {
            //        1.将path对应的图片转化为流
            ImageUtil.class.getResource(path);
            //2.使用imageIo.read() 将转换的流通过管道传输到程序中
            ImageIO.read(ImageUtil.class.getResource(path));
//        3.使用BufferedImage接收
            //尝试拿着地址找图片，如果找得到，就获得图片

            BufferedImage img = ImageIO.read(ImageUtil.class.getResource(path));
            return img;

            //如果找不到，就抓取错误，问一下找不到的原因（找错误抛出）
            // Exception  类似记事本
        }catch (Exception e){
            //从记事本中翻出找不到的原因
            e.printStackTrace();
        }

        return null ;

    }
}
