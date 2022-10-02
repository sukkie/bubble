package test.ex17;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class BGM {

    public BGM() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(
                    BubbleFrame.loader.getResource("sound/bgm.wav").getFile()));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);

            // 소리 설정
            FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // 볼륨 조절
            fc.setValue(-30f);

            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
