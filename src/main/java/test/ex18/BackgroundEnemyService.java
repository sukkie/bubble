package test.ex18;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// 메인쓰레드 바쁨 - 키보드 이벤트를 처리하기 바쁨
// 백그라운드에서 계속 관찰
public class BackgroundEnemyService implements Runnable {

    // 플레이어 이미지 사이즈
    private final static int PLAYER_IMAGE_SIZE = 50;

    private BufferedImage image;
    private Enemy enemy;

    // 플레이어, 버블
    public BackgroundEnemyService(Enemy enemy) {
        this.enemy = enemy;
        try {
            image = ImageIO.read(BubbleFrame.loader.getResource("image/backgroundMapService.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (enemy.getState() == 0) {

            // 2. 벽 충돌 체크
            Color leftColor = new Color(image.getRGB(enemy.getX() - 10, enemy.getY() + 25));
            Color rightColor = new Color(image.getRGB(enemy.getX() + PLAYER_IMAGE_SIZE + 10, enemy.getY() + 25));
            // -2는 왼쪽 오른쪽 둘다 흰색이라는 의미 (-1이 흰색)
            int bottomColor = image.getRGB(enemy.getX() + 10, enemy.getY() + PLAYER_IMAGE_SIZE + 5)
                    + image.getRGB(enemy.getX() + PLAYER_IMAGE_SIZE - 10, enemy.getY() + PLAYER_IMAGE_SIZE + 5);
//            System.out.println("leftColor 색상 : " + leftColor);
//            System.out.println("rightColor 색상 : " + rightColor);
//            System.out.println("bottomColor 색상 : " + bottomColor);

            // 바닥 충돌 확인
            if (bottomColor != -2) {
//                System.out.println("바닥에 충돌");
                enemy.setDown(false);
                // 바닥이 낭떨어지이면
            } else {
                // 위로 올라가고 내려가는 상태가 아닌경우
                if (!enemy.isUp() && !enemy.isDown()) {
                    enemy.down();
                }
            }

            // 외벽 충돌  확인
            if (leftColor.getRed() == 255 && leftColor.getGreen() == 0  && leftColor.getBlue() == 0) {
//                System.out.println("왼쪽벽에 충돌");
                enemy.setLeft(false);
                if (!enemy.isRight()) {
                    enemy.right();
                }
            } else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0  && rightColor.getBlue() == 0) {
//                System.out.println("오른쪽벽에 충돌");
                enemy.setRight(false);
                if (!enemy.isLeft()) {
                    enemy.left();
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
