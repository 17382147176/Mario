package Com_sxt;

import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Myframe extends JFrame implements KeyListener,Runnable{
    //存储所有背景
    private List<BackGround> allbg = new ArrayList<>();
    //存储当前背景
    private BackGround nowbg = new BackGround();
    //用于双缓存
    private Image offScreenImage = null;
    //马里奥对象
    private Mario mario = new Mario();
    //定义一个线程对象，用于实现马里奥的运动
    private Thread thread=new Thread(this);

    public Myframe() throws FileNotFoundException, JavaLayerException {
        //设置窗口大小
        this.setSize(800, 600);
        //设置居中
        this.setLocationRelativeTo(null);
        //设置可见
        this.setVisible(true);
        //设置关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口不可变
        this.setResizable(false);
        //向窗口对象添加键盘监听器
        this.addKeyListener(this);
        //设置窗口名称
        this.setTitle("Mario");
        //初始化方法
        StaticValue.init();
        //初始化马里奥
        mario = new Mario(10, 355);
        //创建全部场景
        for (int i = 1; i <= 3; i++) {
            allbg.add(new BackGround(i, i == 3 ? true : false));
        }
        //将第一个场景设置为当前场景
        nowbg = allbg.get(0);
        mario.setBackGround(nowbg);
        //绘制图像
        repaint();
        thread.start();
        new Music();
    }

    @Override
    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(800, 600);
        }
        Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0, 0, 800, 600);
        //绘制背景
        graphics.drawImage(nowbg.getBgImage(), 0, 0, this);
        //绘制敌人
        for (Enemy e:nowbg.getEnemyList()){
            graphics.drawImage(e.getShow(),e.getX(),e.getY(),this);
        }
        //绘制障碍物
        for (Obstacle ob : nowbg.getObstacleList()) {
            graphics.drawImage(ob.getShow(), ob.getX(), ob.getY(), this);
        }
        //绘制城堡
        graphics.drawImage(nowbg.getTower(), 620, 270, this);
        //绘制旗杆
        graphics.drawImage(nowbg.getGan(), 500, 220, this);
        //绘制马里奥
        graphics.drawImage(mario.getShow(), mario.getX(), mario.getY(), this);
        //添加分数
        Color c=graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("黑体",Font.BOLD,25));
        graphics.drawString("当前的分数为"+mario.getScore(),300,100);
        graphics.setColor(c);
        //将图像绘制到窗口区中
        g.drawImage(offScreenImage, 0, 0, this);

    }

    public static void main(String[] args) throws FileNotFoundException, JavaLayerException {
        new Myframe();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //向右移动
        if (e.getKeyCode() == 39) {
            mario.rightmove();
        }
        //向左移动
        if (e.getKeyCode() == 37) {
            mario.leftmove();
        }
        //跳跃
        if(e.getKeyCode()==38){
            mario.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //向左停止
        if(e.getKeyCode()==37)
        mario.leftstop();
        //向右停止
        if(e.getKeyCode()==39)
        mario.rightstop();
    }

    @Override
    public void run() {
        while (true){
            repaint();
            try {
                Thread.sleep(50);
                if(mario.getX()>=775){
                    nowbg=allbg.get(nowbg.getSort());
                    mario.setBackGround(nowbg);
                    mario.setX(10);
                    mario.setY(355);
                }
                //判断马里奥是否死亡
                if(mario.isDeath()==true){
                    JOptionPane.showMessageDialog(this,"马里奥死亡！");
                    System.exit(0);
                }
                //判断游戏是否结束
                if(mario.isOk()){
                    JOptionPane.showMessageDialog(this,"恭喜你成功通关了");
                    System.exit(0);
                }



            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
