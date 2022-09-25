package test.ex03;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BubbleFrame extends JFrame {

    private static final ClassLoader loader = BubbleFrame.class.getClassLoader();

    private JLabel backgroundMap;
    private Player player;

    public BubbleFrame() {
        initObject();
        initSetting();
        initListener();
        setVisible(true);
    }

    private void initObject() {
        backgroundMap = new JLabel(new ImageIcon(loader.getResource("image/backgroundMap.png")));
        setContentPane(backgroundMap);
//        backgroundMap.setSize(1000, 600);
//        add(backgroundMap); // JFrame에 JLabel이 그려진다
        player = new Player();
        backgroundMap.add(player);
    }

    private void initSetting() {
        setSize(1000, 640);
        setLayout(null); // absolute 레이아웃
        setLocationRelativeTo(null); //가운데 배치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창 종료시 JVM도 종료
    }

    private void initListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        player.left();
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.right();
                        break;
                    case KeyEvent.VK_UP:
                        player.up();
                        break;
//                    case KeyEvent.VK_DOWN:
//                        player.down();
//                        break;
                }
            }
        });
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }
}
