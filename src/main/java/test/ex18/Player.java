package test.ex18;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Player extends JLabel implements Moveable {

    private BubbleFrame mContext;
    private List<Bubble> bubbleList;

    // 위치상태
    private int x;
    private int y;

    // 플레이어의 방향(방울 방향을 위해)
    private PlayerWay playerWay;

    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    // 벽에 충돌한 상태
    private boolean leftWallCrash;
    private boolean rightWallCrash;

    // 플레이어의 속도 상태
    private final static int SPEED = 4;

    private final static int JUMP_SPEED = 3;

    private ImageIcon playerR, playerL;

    public Player(BubbleFrame mContext) {
        this.mContext = mContext;
        initObject();
        initSetting();
        initBackgroundPlayerService();
    }

    private void initObject() {
        playerR = new ImageIcon(BubbleFrame.loader.getResource("image/playerR.png"));
        playerL = new ImageIcon(BubbleFrame.loader.getResource("image/playerL.png"));
        bubbleList = new ArrayList<Bubble>();
    }

    private void initSetting() {
        x = 80;
        y = 540;

        up = false;
        down = false;
        left = false;
        right = false;

        leftWallCrash = false;
        rightWallCrash = false;

        playerWay = PlayerWay.RIGHT;
        setIcon(playerR);
        setSize(50, 50);
        setLocation(x, y);
    }

    private void initBackgroundPlayerService() {
        new Thread(new BackgroundPlayerService(this)).start();
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
        playerWay = PlayerWay.LEFT;
        new Thread(() -> {
            System.out.println("left start");
            while (left) {
                setIcon(playerL);
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
        playerWay = PlayerWay.RIGHT;
        new Thread(() -> {
            System.out.println("right start");
            while (right) {
                setIcon(playerR);
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

    @Override
    public void attack() {
        new Thread(() -> {
            Bubble bubble = new Bubble(mContext);
            mContext.add(bubble);
            bubbleList.add(bubble);
            if (this.getPlayerWay() == PlayerWay.LEFT) {
                bubble.left();
            } else {
                bubble.right();
            }
        }).start();
    }
}
