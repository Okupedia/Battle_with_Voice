import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.media.AudioClip;

public class Bgm {
    static AudioClip voice0;
    static AudioClip voice1;
    static AudioClip voice2;
    static AudioClip bgm2;
    static AudioClip bgm3;
    static AudioClip bgm4;
    static AudioClip start;
    static AudioClip clear;

    Bgm(int num,File file) {
        switch(num){
            case 0:
	    voice0 = new AudioClip(file.toURI().toString());
	    break;
        case 1:
        voice1 = new AudioClip(new File("voice1.wav").toURI().toString());
	    break;
        case 2:
        voice2 = new AudioClip(new File("voice2.wav").toURI().toString());
        break;
        }
    }

    Bgm(){
        bgm2 = new AudioClip(new File("bgm2.wav").toURI().toString());
        bgm3 = new AudioClip(new File("bgm3.wav").toURI().toString());
        bgm4 = new AudioClip(new File("bgm4.wav").toURI().toString());
        start = new AudioClip(new File("start.wav").toURI().toString());
        clear = new AudioClip(new File("clear.wav").toURI().toString());
    }


    static void playBgm2() {
	//無限ループ指定
	bgm2.setCycleCount(AudioClip.INDEFINITE);
	bgm2.setPriority(1);
	bgm2.setVolume(0.5);
	bgm2.play();
	System.out.println(bgm2.getPriority());
	//Thread.sleep(12000);
    }

    static void stopBgm2() {
	bgm2.stop();
    }

    static void playBgm3() {
	//無限ループ指定
	bgm3.setCycleCount(AudioClip.INDEFINITE);
	bgm3.setPriority(1);
	bgm3.setVolume(0.5);
	bgm3.play();
	System.out.println(bgm3.getPriority());
	//Thread.sleep(12000);
    }

    static void stopBgm4() {
	bgm4.stop();
    }

    static void playBgm4() {
	//無限ループ指定
	bgm4.setCycleCount(AudioClip.INDEFINITE);
	bgm4.setPriority(1);
	bgm4.setVolume(0.5);
	bgm4.play();
	System.out.println(bgm4.getPriority());
	//Thread.sleep(12000);
    }

    static void stopBgm3() {
	bgm3.stop();
    }

    static void playVoice0(){
	voice0.setVolume(0.8);
	voice0.play();
    }

    void stopVoice0() {
	voice0.stop();
    }

    static void playVoice1(){
	voice1.setVolume(0.8);
	voice1.play();
    }

    void stopVoice1() {
	voice1.stop();
    }

    static void playVoice2(){
	voice2.setVolume(0.8);
	voice2.play();
    }

    void stopVoice2() {
	voice2.stop();
    }


    static void playStart() {
	//無限ループ指定
	start.setCycleCount(AudioClip.INDEFINITE);
	start.setPriority(1);
	start.setVolume(0.5);
	start.play();
	System.out.println(start.getPriority());
	//Thread.sleep(12000);
    }

    static void stopStart() {
	start.stop();
    }

    static void playClear() {
	//無限ループ指定
	clear.setCycleCount(AudioClip.INDEFINITE);
	clear.setPriority(1);
	clear.setVolume(0.5);
	clear.play();
	System.out.println(clear.getPriority());
	//Thread.sleep(12000);
    }

    static void stopClear() {
	clear.stop();
    }
}
