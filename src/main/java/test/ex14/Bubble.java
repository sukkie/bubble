package test.ex14;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

    // 의존성 컴포지트
    private BubbleFrame mContext;
    private Player player;

    private BackgroundBubbleService backgroundBubbleService;

    // 위치상태
    private int x;
    private int y;

    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;

    // 적군을 맞춘 상태
    // 0 : 물방울, 1 : 적을 가둔 물방울
    private int state;

    private ImageIcon bubble;   // 물방울
    private ImageIcon bubbled;  //적을 가둔 상태
    private ImageIcon bomb;     // 물방울이 터진상태

    public Bubble(BubbleFrame mContext) {
        this.mContext = mContext;
        this.player = mContext.getPlayer();
        initObject();
        initSetting();
//        initThread();
    }

    private void initObject() {
        bubble = new ImageIcon(BubbleFrame.loader.getResource("image/bubble.png"));
        bubbled = new ImageIcon(BubbleFrame.loader.getResource("image/bubbled.png"));
        bomb = new ImageIcon(BubbleFrame.loader.getResource("image/bomb.png"));

        backgroundBubbleService = new BackgroundBubbleService(this);
    }

    private void initSetting() {
        left = false;
        right = false;
        up = false;

        x = player.getX();
        y = player.getY();

        setIcon(bubble);
        setSize(50, 50);

        state = 0;
    }

//    private void initThread() {
//        // 버블은 쓰레드가 하나만 필요.
//        new Thread(() -> {
//            if (player.getPlayerWay() == PlayerWay.LEFT) {
//                left();
//            } else {
//                right();
//            }
//        }).start();
//    }

    @Override
    public void up() {
        up = true;
        while (up) {
            y--;
            setLocation(x, y);

            if (backgroundBubbleService.topWall()) {
                up = false;
                break;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        clearBubble(); // 천장에 버블이 도착하고 나서 3초 후에 메모리에서 소멸
    }

    private void clearBubble() {
        try {
            Thread.sleep(3000);
            setIcon(bomb);
            Thread.sleep(500);
            mContext.remove(this); // 버블프레의 버블이 메모리에서 소멸된다.
            mContext.repaint(); // 버블프레임 전체를 다시 그린다.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void right() {
        right = true;
        for (int i = 0; i < 400; i++) {
            x++;
            setLocation(x, y);

            if (backgroundBubbleService.rightWall()) {
                right = false;
                break;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        up();
    }

    @Override
    public void left() {
        left = true;
        for (int i = 0; i < 400; i++) {
            x--;
            setLocation(x, y);

            if (backgroundBubbleService.leftWall()) {
                left = false;
                break;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        up();
    }
}
