package test.ex03;

import javax.swing.*;

public class Player extends JLabel implements Moveable {

    private static final ClassLoader loader = Player.class.getClassLoader();

    private int default_move = 10;

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
        setIcon(playerL);
        x = x - default_move;
        setLocation(x, y);
    }

    @Override
    public void right() {
        setIcon(playerR);
        x = x + default_move;
        setLocation(x, y);
    }
}
