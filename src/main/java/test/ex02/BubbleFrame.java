package test.ex02;

import javax.swing.*;

public class BubbleFrame extends JFrame {

    private static final ClassLoader loader = BubbleFrame.class.getClassLoader();

    private JLabel backgroundMap;
    private Player player;

    public BubbleFrame() {
        initObject();
        initSetting();
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

    public static void main(String[] args) {
        new BubbleFrame();
    }
}
