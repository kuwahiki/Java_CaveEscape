package frame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * @author a50605
 *
 *
 *         画像サイズ 14*19
 */
public class Enemy extends GameChara {
	int speed_y;
	boolean drop = false;
	boolean turn = true; // 右ならtrue,左ならfalse
	int counter = 0;

	public Enemy(BufferedImage img, int x, int y,boolean turn) {
		super(img, x, y, 0, 0, 14, 19, 3);
		// TODO Auto-generated constructor stub
		this.turn = turn;
	}

	public void draw(Graphics g, ImageObserver io, int timer) {
		if (sceen == numsceen) {
			sceen = 0;
		}
		g.drawImage(image, chara_x, chara_y, chara_x + image_w, chara_y + image_h, image_x + (image_w * sceen),
				image_y + (image_h * direction), (image_w * sceen) + image_w, image_y + image_h + (image_h * direction),
				io);
		if (counter == 2) {
			sceen++;
			counter = 0;
		} else {
			counter++;
		}

		move();
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		hitbox_x = chara_x;
		hitbox_y = chara_y;
		hitbox_w = image_w;
		hitbox_h = image_h;
		if (turn == true) {
			chara_x += 2;
			direction = 1;
		} else {
			chara_x -= 2;
			direction = 0;
		}
		if (drop == true) {
			speed_y += GRAVITY;
		} else {
			speed_y = 0;
		}
		if (speed_y >= MAXSPEED)
			speed_y = MAXSPEED;
		chara_y += speed_y;
	}
}
