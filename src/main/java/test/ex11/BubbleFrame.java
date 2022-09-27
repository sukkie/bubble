package test.ex11;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BubbleFrame extends JFrame {

    public static final ClassLoader loader = BubbleFrame.class.getClassLoader();

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

            // 키보드 클릭 이벤트 핸들러
            @Override
            public void keyPressed(KeyEvent e) {
//                System.out.println(e.getKeyCode());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (!player.isLeft() && !player.isLeftWallCrash()) {
                            player.left();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!player.isRight() && !player.isRightWallCrash()) {
                            player.right();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (!player.isUp() && !player.isDown()) {
                            player.up();
                        }
                        break;
//                    case KeyEvent.VK_DOWN:
//                        player.down();
//                        break;
                    case KeyEvent.VK_SPACE:
                        Bubble bubble = new Bubble(player);
                        add(bubble);
                        break;
                }
            }

            // 키보드 해제 이벤트 핸들
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        player.setLeft(false);
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.setRight(false);
                        break;
                }
            }
        });
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }
}
