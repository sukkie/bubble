package test.ex16;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class Enemy extends JLabel implements Moveable {

    private BubbleFrame mContext;

    // 위치상태
    private int x;
    private int y;

    // 적군의 방향(방울 방향을 위해)
    private EnemyWay enemyWay;

    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    // 0 : 살아있는 상태, 1 : 물방울에 같힌 상태
    private int state;

    // 적군의 속도 상태
    private final static int SPEED = 3;

    private final static int JUMP_SPEED = 1;

    private ImageIcon enemyR, enemyL;

    public Enemy(BubbleFrame mContext) {
        this.mContext = mContext;
        initObject();
        initSetting();
        initBackgroundEnemyService();
    }

    private void initObject() {
        enemyR = new ImageIcon(BubbleFrame.loader.getResource("image/enemyR.png"));
        enemyL = new ImageIcon(BubbleFrame.loader.getResource("image/enemyL.png"));
    }

    private void initSetting() {
        x = 480;
        y = 178;

        up = false;
        down = false;
        left = false;
        right = false;

        state = 0;

        enemyWay = EnemyWay.RIGHT;

        setIcon(enemyR);
        setSize(50, 50);
        setLocation(x, y);
    }

    private void initBackgroundEnemyService() {
//        new Thread(new BackgroundPlayerService(this)).start();
    }

    // left + up, right + up
    @Override
    public void up() {
        up = true;
        new Thread(() -> {
            for (int i = 0; i < 130 / JUMP_SPEED; i++) {
                y = y - JUMP_SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            up = false;
            down();
        }).start();
    }

    @Override
    public void down() {
        down = true;
        new Thread(() -> {
            while (down) {
                y = y + JUMP_SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            down = false;
        }).start();
    }

    @Override
    public void left() {
        left = true;
        enemyWay = EnemyWay.LEFT;
        new Thread(() -> {
            while (left) {
                setIcon(enemyL);
                x = x - SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void right() {
        right = true;
        enemyWay = EnemyWay.RIGHT;
        new Thread(() -> {
            System.out.println("right start");
            while (right) {
                setIcon(enemyR);
                x = x + SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
