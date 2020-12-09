package frame;

import java.io.IOException;

import javax.imageio.ImageIO;

public class testMain extends runGame {
	public testMain(String title) {
		super(title);
		// TODO Auto-generated constructor stub
		map = "/frame/map.csv";
		try {
			player1 = ImageIO.read(getClass().getResource("player1.png"));
			ground = ImageIO.read(getClass().getResource("ground.png"));
			surfase = ImageIO.read(getClass().getResource("surfase.png"));
			Reffect = ImageIO.read(getClass().getResource("effcut_right.png"));
			Leffect = ImageIO.read(getClass().getResource("effcut_left.png"));
			enemy = ImageIO.read(getClass().getResource("enemy.png"));
			titleimage = ImageIO.read(getClass().getResource("title.jpg"));
			backimage = ImageIO.read(getClass().getResource("backplay.jpg"));
			gamestart = ImageIO.read(getClass().getResource("start.png"));
			gameclear = ImageIO.read(getClass().getResource("gameclear.jpg"));
			gameover = ImageIO.read(getClass().getResource("gameover.png"));
			exit = ImageIO.read(getClass().getResource("exit.png"));
			midiRead("BGM.mid");
			clip1 = wavRead("attack.wav");
			clip2 = wavRead("jump.wav");
			clip3 = wavRead("sound_gameover.wav");
			clip4 = wavRead("clear.wav");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testMain test = new testMain("CAVE ESCAPE");
	}
}
