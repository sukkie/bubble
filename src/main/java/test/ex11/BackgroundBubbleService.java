package test.ex11;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// 메인쓰레드 바쁨 - 키보드 이벤트를 처리하기 바쁨
// 백그라운드에서 계속 관찰
public class BackgroundBubbleService {

    // 플레이어 이미지 사이즈
    private final static int BUBBLE_IMAGE_SIZE = 50;

    private BufferedImage image;
    private Bubble bubble;

    public BackgroundBubbleService(Bubble bubble) {
        this.bubble = bubble;
        try {
            image = ImageIO.read(BubbleFrame.loader.getResource("image/backgroundMapService.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean leftWall() {
        Color leftColor = new Color(image.getRGB(bubble.getX() - 10, bubble.getY() + 25));

        // 외벽 충돌  확인
        if (leftColor.getRed() == 255 && leftColor.getGreen() == 0  && leftColor.getBlue() == 0) {
            return true;
        }
        return false;
    }

    public boolean rightWall() {
        Color rightColor = new Color(image.getRGB(bubble.getX() + BUBBLE_IMAGE_SIZE + 10, bubble.getY() + 25));

        // 외벽 충돌  확인
     if (rightColor.getRed() == 255 && rightColor.getGreen() == 0  && rightColor.getBlue() == 0) {
            return true;
        }
        return false;
    }

    public boolean topWall() {
        Color topColor = new Color(image.getRGB(bubble.getX() + 25 , bubble.getY() - 10));

        // 외벽 충돌  확인
        if (topColor.getRed() == 255 && topColor.getGreen() == 0  && topColor.getBlue() == 0) {
            return true;
        }
        return false;
    }
}
