package Com_sxt;

import java.awt.image.BufferedImage;

public class Mario implements Runnable {
    //用于表示横纵坐标
    private int x;
    private int y;
    //用于表示当前状态
    private String status;
    //用于表示当前状态所对应的图像
    private BufferedImage show = null;
    //定义一个BackGround对象，用于获取障碍物的信息
    private BackGround backGround = new BackGround();
    //用来实现马里奥的动作
    private Thread thread = null;
    //马里奥的移动速度
    private int xspead;
    //马里奥的跳跃速度
    private int yspead;
    //定义一个索引
    private int index;
    //表示马里奥的上升时间
    private int upTime = 0;
    //判断马里奥是否走到了城堡门口
    private boolean isOk;
    //判断马里奥是否死亡
    private boolean isDeath = false;
    //表示分数
    private int score=0;

    public Mario() {
    }

    public Mario(int x, int y) {
        this.x = x;
        this.y = y;
        show = StaticValue.stand_r;
        this.status = "stand--right";
        thread = new Thread(this);
        thread.start();
    }

    //马里奥死亡方法
    public void death() {
        isDeath = true;
    }

    //马里奥左移动
    public void leftmove() {
        //改变速度
        xspead = -5;
        //判断马里奥是否碰到了旗子
        if (backGround.isReach()) {
            xspead = 0;
        }
        //判断是否处在空中
        if (status.indexOf("jump") != -1) {
            status = "jump--left";
        } else {
            status = "move--left";
        }
    }

    //马里奥右移动
    public void rightmove() {
        //改变速度
        xspead = 5;
        //判断马里奥是否碰到了旗子
        if (backGround.isReach()) {
            xspead = 0;
        }
        //判断是否处在空中
        if (status.indexOf("jump") != -1) {
            status = "jump--right";
        } else {
            status = "move--right";
        }
    }

    //马里奥左停止
    public void leftstop() {
        //改变速度
        xspead = 0;
        //判断是否处在空中
        if (status.indexOf("jump") != -1) {
            status = "stop--left";
        } else {
            status = "stop--left";
        }
    }

    //马里奥右停止
    public void rightstop() {
        //改变速度
        xspead = 0;
        //判断是否处在空中
        if (status.indexOf("jump") != -1) {
            status = "stop--right";
        } else {
            status = "stop--right";
        }
    }

    //马里奥跳跃
    public void jump() {

        if (status.indexOf("jump") == -1) {
            if (status.indexOf("left") != -1) {
                status = "jump--left";
            } else {
                status = "jump--right";
            }
            yspead = -10;
            upTime = 7;
        }
        //判断马里奥是否碰到了旗子
        if (backGround.isReach()) {
            yspead = 0;
        }
    }

    //马里奥下落
    public void fall() {
        if (status.indexOf("left") != -1) {
            status = "jump--left";
        } else {
            status = "jump--right";
        }
        yspead = 10;
    }


    @Override
    public void run() {
        while (true) {
            //判断是否处在障碍物上
            boolean onObstacle = false;
            //判断是否可以向右走
            boolean canRight = true;
            //判断是否可以向左走
            boolean canLeft = true;
            //判断马里奥是否走到旗杆的位置
            if (backGround.isFlag() && this.x >= 500) {
                this.backGround.setReach(true);

                //判断旗子是否下落完成
                if (this.backGround.isBase()) {
                    status = "move--right";
                    if (x < 690) {
                        x += 5;
                    } else {
                        isOk = true;

                    }
                } else {
                    if (y < 395) {
                        xspead = 0;
                        this.y += 5;
                        status = "jump--right";
                    }

                    if (y > 395) {
                        xspead = 0;
                        status = "stop--right";
                    }
                }

            } else {

                //遍历当前场景里所有的障碍物
                for (int i = 0; i < backGround.getObstacleList().size(); i++) {
                    //判断是否位于障碍物上
                    Obstacle ob = backGround.getObstacleList().get(i);
                    if (ob.getY() == this.y + 25 && (ob.getX() > this.x - 30 && ob.getX() < this.x + 25)) {
                        onObstacle = true;
                    }
                    //判断是否跳起来顶到砖块
                    if (ob.getY() >= this.y - 30 && ob.getY() <= this.y - 20 && ob.getX() > this.x - 30 && ob.getX() < this.x + 25) {
                        if (ob.getType() == 0) {
                            backGround.getObstacleList().remove(ob);
                            score+=1;
                        }
                        upTime = 0;
                    }
                    //判断马里奥是否可以往右走
                    if (ob.getX() == this.x + 25 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 25)) {
                        canRight = false;
                    }
                    //判断马里奥是否可以往左走
                    if (ob.getX() == this.x - 30 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 25)) {
                        canLeft = false;
                    }
                }
                for (int i = 0; i < backGround.getEnemyList().size(); i++) {
                    Enemy e = backGround.getEnemyList().get(i);
                    if (e.getY() == this.y + 20 && (e.getX() - 25 <= this.x && e.getX() + 35 >= this.x)) {
                        if (e.getType() == 1) {
                            e.death();
                            score+=2;
                            upTime = 3;
                            yspead = -10;
                        } else if (e.getType() == 2) {
                            death();

                        }
                    }
                    if ((e.getX() + 35 > this.x && e.getX() - 25 < this.x) && (e.getY() + 35 > this.y && e.getY() - 20 < this.y)) {
                        death();
                    }
                }

                //进行马里奥的跳跃操作
                if (onObstacle && upTime == 0) {
                    if (status.indexOf("left") != -1) {
                        if (xspead != 0) {
                            status = "move--left";
                        } else {
                            status = "stop--left";
                        }
                    } else {
                        if (xspead != 0) {
                            status = "move--right";
                        } else {
                            status = "stop--right";
                        }
                    }
                } else {
                    if (upTime != 0) {
                        upTime--;
                    } else {
                        fall();
                    }
                    y += yspead;
                }
            }


            if ((canRight && xspead > 0) || (xspead < 0 && canLeft)) {
                x += xspead;
                //判断是否到达了屏幕的最左边
                if (x < 0) {
                    x = 0;
                }
            }
            //判断当前是否是在移动
            if (status.contains("move")) {
                index = index == 0 ? 1 : 0;
            }
            //判断是否向左移动
            if ("move--left".equals(status)) {
                show = StaticValue.run_l.get(index);
            }
            //判断是否向右移动
            if ("move--right".equals(status)) {
                show = StaticValue.run_r.get(index);
            }
            //判断是否左停止
            if ("stop--left".equals(status)) {
                show = StaticValue.stand_l;
            }
            //判断是否右停止
            if ("stop--right".equals(status)) {
                show = StaticValue.stand_r;
            }
            //判断是否向左跳跃
            if ("jump--left".equals(status)) {
                show = StaticValue.jump_l;
            }
            //判断是否向右跳跃
            if ("jump--right".equals(status)) {
                show = StaticValue.jump_r;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getShow() {
        return show;
    }

    public void setShow(BufferedImage show) {
        this.show = show;
    }

    public void setBackGround(BackGround backGround) {
        this.backGround = backGround;
    }

    public boolean isOk() {
        return isOk;
    }

    public boolean isDeath() {
        return isDeath;
    }

    public int getScore() {
        return score;
    }
}
