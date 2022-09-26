package test.ex05;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Setter
@Getter
public class Player extends JLabel implements Moveable {

    private static final ClassLoader loader = Player.class.getClassLoader();

    // 위치상태
    private int x;
    private int y;

    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    // 플레이어의 속도 상태
    private final static int SPEED = 4;

    private final static int JUMP_SPEED = 3;

    private ImageIcon playerR, playerL;

    public Player() {
        initObject();
        initSetting();
    }

    private void initObject() {
        playerR = new ImageIcon(loader.getResource("image/playerR.png"));
        playerL = new ImageIcon(loader.getResource("image/playerL.png"));
    }

    private void initSetting() {
        x = 70;
        y = 540;

        up = false;
        down = false;
        left = false;
        right = false;

        setIcon(playerR);
        setSize(50, 50);
        setLocation(x, y);
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
            for (int i = 0; i < 130 / JUMP_SPEED; i++) {
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
        new Thread(() -> {
            System.out.println("left start");
            while(left) {
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
        new Thread(() -> {
            System.out.println("right start");
            while(right) {
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
}
