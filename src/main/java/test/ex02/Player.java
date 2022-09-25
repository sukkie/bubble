package test.ex02;

import javax.swing.*;

public class Player extends JLabel {

    private static final ClassLoader loader = Player.class.getClassLoader();

    private int x;

    private int y;

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
        setIcon(playerR);
        setSize(50, 50);
        setLocation(x, y);
    }
}
