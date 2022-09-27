package test.ex10;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class Bubble extends JLabel {

    // 의존성 컴포지트
    private Player player;

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

    public Bubble(Player player) {
        this.player = player;
        initObject();
        initSetting();
    }

    private void initObject() {
        bubble = new ImageIcon(BubbleFrame.loader.getResource("image/bubble.png"));
        bubbled = new ImageIcon(BubbleFrame.loader.getResource("image/bubbled.png"));
        bomb = new ImageIcon(BubbleFrame.loader.getResource("image/bomb.png"));
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
}
