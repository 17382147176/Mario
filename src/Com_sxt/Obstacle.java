package Com_sxt;

import java.awt.image.BufferedImage;

public class Obstacle implements Runnable {
    //用于表示坐标
    private int x;
    private int y;
    //用于记录障碍物类型
    private int type;
    //用于显示图像
    private BufferedImage show = null;
    //定义当前场景的对象
    private BackGround bg = null;
    //定义一个线程对象
    private Thread thread=new Thread(this);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public BufferedImage getShow() {
        return show;
    }

    public Obstacle(int x, int y, int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bg = bg;
        show = StaticValue.obstract.get(type);
        if(type==8){
            thread.start();
        }
    }

    @Override
    public void run() {
        while (true){
            if(this.bg.isReach()){
                if(this.y<374){
                    this.y+=5;
                }else {
                    this.bg.setBase(true);
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
