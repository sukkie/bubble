package test.ex09;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// 메인쓰레드 바쁨 - 키보드 이벤트를 처리하기 바쁨
// 백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable {

    private BufferedImage image;
    private Player player;

    public BackgroundPlayerService(Player player) {
        this.player = player;
        try {
            image = ImageIO.read(BubbleFrame.loader.getResource("image/backgroundMapService.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            Color leftColor = new Color(image.getRGB(player.getX() - 10, player.getY() + 25));
            Color rightColor = new Color(image.getRGB(player.getX() + 50 + 10, player.getY() + 25));
            // -2는 왼쪽 오른쪽 둘다 흰색이라는 의미
            int bottomColor = image.getRGB(player.getX() + 10, player.getY() + 50 + 5)
                    + image.getRGB(player.getX() + 50 - 10, player.getY() + 50 + 5);
//            System.out.println("leftColor 색상 : " + leftColor);
//            System.out.println("rightColor 색상 : " + rightColor);
//            System.out.println("bottomColor 색상 : " + bottomColor);

            // 바닥 충돌 확인
            if (bottomColor != -2) {
//                System.out.println("바닥에 충돌");
                player.setDown(false);
                // 바닥이 낭떨어지지 이면
            } else {
                // 위로 올라가고 내려가는 상태가 아닌경우
                if (!player.isUp() && !player.isDown()) {
                    player.down();
                }
            }

            // 외벽 충돌  확인
            if (leftColor.getRed() == 255 && leftColor.getGreen() == 0  && leftColor.getBlue() == 0) {
//                System.out.println("왼쪽벽에 충돌");
                player.setLeft(false);
                player.setLeftWallCrash(true);
            } else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0  && rightColor.getBlue() == 0) {
//                System.out.println("오른쪽벽에 충돌");
                player.setRight(false);
                player.setRightWallCrash(true);
            } else {
                player.setLeftWallCrash(false);
                player.setRightWallCrash(false);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
