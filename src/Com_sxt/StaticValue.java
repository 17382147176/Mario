package Com_sxt;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaticValue {
    //背景
    public static BufferedImage bg = null;
    public static BufferedImage bg2 = null;
    //左跳跃
    public static BufferedImage jump_l = null;
    //右跳跃
    public static BufferedImage jump_r = null;
    //左站立
    public static BufferedImage stand_l = null;
    //右站立
    public static BufferedImage stand_r = null;
    //城堡
    public static BufferedImage tower = null;
    //旗杆
    public static BufferedImage gan = null;
    //障碍物
    public static List<BufferedImage> obstract = new ArrayList<>();
    //左跑
    public static List<BufferedImage> run_l = new ArrayList<>();
    //右跑
    public static List<BufferedImage> run_r = new ArrayList<>();
    //蘑菇
    public static List<BufferedImage> mogu = new ArrayList<>();
    //食人花
    public static List<BufferedImage> flower = new ArrayList<>();
    //路径
    public static String path = System.getProperty("user.dir") + "/src/images/";
    //初始化
    public static void init() {
        try {
            bg= ImageIO.read(new File(path+"bg.png"));
            bg2= ImageIO.read(new File(path+"bg2.png"));
            jump_l= ImageIO.read(new File(path+"s_mario_jump1_L.png"));
            jump_r= ImageIO.read(new File(path+"s_mario_jump1_R.png"));
            stand_l= ImageIO.read(new File(path+"s_mario_stand_L.png"));
            stand_r= ImageIO.read(new File(path+"s_mario_stand_R.png"));
            tower= ImageIO.read(new File(path+"tower.png"));
            gan= ImageIO.read(new File(path+"gan.png"));
            run_l.add(ImageIO.read(new File(path+"s_mario_run1_L.png")));
            run_l.add(ImageIO.read(new File(path+"s_mario_run2_L.png")));
            run_r.add(ImageIO.read(new File(path+"s_mario_run1_R.png")));
            run_r.add(ImageIO.read(new File(path+"s_mario_run2_R.png")));
            obstract.add(ImageIO.read(new File(path+"brick.png")));
            obstract.add(ImageIO.read(new File(path+"soil_up.png")));
            obstract.add(ImageIO.read(new File(path+"soil_base.png")));
            obstract.add(ImageIO.read(new File(path+"pipe1.png")));
            obstract.add(ImageIO.read(new File(path+"pipe2.png")));
            obstract.add(ImageIO.read(new File(path+"pipe3.png")));
            obstract.add(ImageIO.read(new File(path+"pipe4.png")));
            obstract.add(ImageIO.read(new File(path+"brick2.png")));
            obstract.add(ImageIO.read(new File(path+"flag.png")));
            mogu.add(ImageIO.read(new File(path+"fungus1.png")));
            mogu.add(ImageIO.read(new File(path+"fungus2.png")));
            mogu.add(ImageIO.read(new File(path+"fungus3.png")));
            flower.add(ImageIO.read(new File(path+"flower1.1.png")));
            flower.add(ImageIO.read(new File(path+"flower1.2.png")));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
