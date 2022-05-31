package Com_sxt;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
    private BufferedImage bgImage = null;
    private int sort;
    private boolean flag;

    private BufferedImage gan=null;
    private BufferedImage tower=null;

    private List<Obstacle> obstacleList = new ArrayList<>();

    private List<Enemy> enemyList=new ArrayList<>();

    private boolean isReach=false;

    private boolean isBase=false;

    public BackGround() {
    }

    public BackGround(int sort, Boolean flag) {
        this.sort = sort;
        this.flag = flag;
        if (flag == true) {
            bgImage = StaticValue.bg2;
        } else {
            bgImage = StaticValue.bg;
        }
        //判断是否是第一关
        if (sort == 1) {
            //绘制第一关的地面，上地面的type是type=1，下底面的type=2
            for (int i = 0; i < 27; i++) {
                obstacleList.add(new Obstacle(i * 30, 420, 1, this));
            }
            for (int i = 0; i <= 120; i += 30) {
                for (int j = 0; j < 27; j++) {
                    obstacleList.add(new Obstacle(j * 30, 570 - i, 2, this));
                }
            }
            //绘制砖块A
            for (int i = 120; i <= 150; i += 30) {
                obstacleList.add(new Obstacle(i, 300, 7, this));
            }
            //绘制砖块B-F
            for (int i = 300; i <= 570; i += 30) {
                if (i == 360 || i == 390 || i == 480 || i == 510 || i == 540) {
                    obstacleList.add(new Obstacle(i, 300, 7, this));
                } else {
                    obstacleList.add(new Obstacle(i, 300, 0, this));
                }
            }
            //绘制砖块G
            for (int i = 420; i <= 450; i += 30) {
                obstacleList.add(new Obstacle(i, 240, 7, this));
            }
            //绘制水管
            for (int i = 360; i <= 600; i += 25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(620, i, 3, this));
                    obstacleList.add(new Obstacle(645, i, 4, this));
                } else {
                    obstacleList.add(new Obstacle(620, i, 5, this));
                    obstacleList.add(new Obstacle(645, i, 6, this));
                }
            }

            //绘制第一关的蘑菇敌人
            enemyList.add(new Enemy(580,385,true,1,this));
            //绘制第一关的食人花敌人
            enemyList.add(new Enemy(635,420,true,2,328,428,this));



        }
        //判断是否是第二关
        if (sort == 2) {
            //绘制第二关的地面，上地面的type是type=1，下底面的type=2
            for (int i = 0; i < 27; i++) {
                obstacleList.add(new Obstacle(i * 30, 420, 1, this));
            }
            for (int i = 0; i <= 120; i += 30) {
                for (int j = 0; j < 27; j++) {
                    obstacleList.add(new Obstacle(j * 30, 570 - i, 2, this));
                }
            }

            //绘制第一个水管
            for (int i = 360; i <= 600; i += 25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(60, i, 3, this));
                    obstacleList.add(new Obstacle(85, i, 4, this));
                } else {
                    obstacleList.add(new Obstacle(60, i, 5, this));
                    obstacleList.add(new Obstacle(85, i, 6, this));
                }
            }
            //绘制第二根水管
            for (int i = 330; i <= 600; i += 25) {
                if (i == 330) {
                    obstacleList.add(new Obstacle(620, i, 3, this));
                    obstacleList.add(new Obstacle(645, i, 4, this));
                } else {
                    obstacleList.add(new Obstacle(620, i, 5, this));
                    obstacleList.add(new Obstacle(645, i, 6, this));
                }
            }
            //绘制砖块C
            obstacleList.add(new Obstacle(300, 330, 0, this));

            //绘制砖块B,E,G
            for (int i = 270; i <= 330; i += 30) {
                if (i == 270 || i == 330) {
                    obstacleList.add(new Obstacle(i, 360, 0, this));
                } else {
                    obstacleList.add(new Obstacle(i, 360, 7, this));
                }
            }

            //绘制砖块A,D,H,I
            for (int i = 240; i <= 360; i += 30) {
                if (i == 240 || i == 360) {
                    obstacleList.add(new Obstacle(i, 390, 0, this));
                } else {
                    obstacleList.add(new Obstacle(i, 390, 7, this));
                }
            }

            //绘制妨碍1砖块
            obstacleList.add(new Obstacle(240,300,0,this));

             //绘制空1-4砖块
            for (int i = 360; i <= 540; i += 60) {
                obstacleList.add(new Obstacle(i, 270, 7, this));
            }
            //绘制第二关的蘑菇敌人1
            enemyList.add(new Enemy(200,385,true,1,this));
            //绘制第二关的蘑菇敌人2
            enemyList.add(new Enemy(500,385,true,1,this));
            //绘制第二关的食人花敌人1
            enemyList.add(new Enemy(75,420,true,2,328,418,this));
            //绘制第二关的食人花敌人2
            enemyList.add(new Enemy(635,420,true,2,298,388,this));


        }
        //判断是否是第三关
        if(sort==3){
            //绘制第三关的地面，上地面的type是type=1，下底面的type=2
            for (int i = 0; i < 27; i++) {
                obstacleList.add(new Obstacle(i * 30, 420, 1, this));
            }
            for (int i = 0; i <= 120; i += 30) {
                for (int j = 0; j < 27; j++) {
                    obstacleList.add(new Obstacle(j * 30, 570 - i, 2, this));
                }
            }

            //绘制A-O砖块
            int tmp=290;
            for (int i = 390; i >=270; i-=30) {
                  for(int j=tmp;j<=410;j+=30){
                      obstacleList.add(new Obstacle(j,i,7,this));
                  }
                  tmp+=30;
            }

            //绘制P-R砖块
            tmp=60;
            for(int i=390;i>=360;i-=30){
                for(int j=tmp;j<=90;j+=30){
                    obstacleList.add(new Obstacle(j,i,7,this));
                }
                tmp+=30;
            }

            //绘制旗杆
            gan=StaticValue.gan;

            //绘制城堡
            tower=StaticValue.tower;

            //添加旗子到杆上
            obstacleList.add(new Obstacle(510,220,8,this));


        }



    }

    public BufferedImage getBgImage() {
        return bgImage;
    }

    public int getSort() {
        return sort;
    }

    public boolean isFlag() {
        return flag;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public BufferedImage getGan() {
        return gan;
    }

    public BufferedImage getTower() {
        return tower;
    }

    public boolean isReach() {
        return isReach;
    }

    public void setReach(boolean reach) {
        isReach = reach;
    }

    public void setBgImage(BufferedImage bgImage) {
        this.bgImage = bgImage;
    }

    public boolean isBase() {
        return isBase;
    }


    public void setBase(boolean base) {
        isBase = base;
    }


    public List<Enemy> getEnemyList() {
        return enemyList;
    }

}
