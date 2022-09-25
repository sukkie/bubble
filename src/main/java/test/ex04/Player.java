package test.ex04;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Setter
@Getter
public class Player extends JLabel implements Moveable {

    private static final ClassLoader loader = Player.class.getClassLoader();

    private int default_move = 2;

    // 위치상태
    private int x;
    private int y;

    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

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

    @Override
    public void up() {
        y = y - default_move;
        setLocation(x, y);
    }

    @Override
    public void down() {
        y = y + default_move;
        setLocation(x, y);
    }

    @Override
    public void left() {
        left = true;
        new Thread(() -> {
            System.out.println("left start");
            while(left) {
                setIcon(playerL);
                x = x - default_move;
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
                x = x + default_move;
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
