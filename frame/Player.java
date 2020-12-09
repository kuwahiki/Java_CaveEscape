package frame;

import java.awt.image.BufferedImage;

/**
 * @author a50605
 *
 *
 *  画像サイズ 12*18
 */
public class Player extends GameChara {
	int speed_x;
	int speed_y;
	int status;
	boolean canjump = false;
	boolean landing = false;
	boolean collision = false;

	public Player(BufferedImage img, int x, int y) {
		super(img, x, y, 0, 0, 12, 18, 3);
		// TODO Auto-generated constructor stub
		
 	}

	@Override
	public void move() {
		chara_x += speed_x;
		chara_y += speed_y;
		if(chara_y <= 0) {
			chara_y = 0;
		}
	}

	public void move(boolean right, boolean left, boolean space) {
		hitbox_x = chara_x + 1;
		hitbox_y = chara_y;
		hitbox_w = image_w - 1;
		hitbox_h = image_h;

		if (right == true) {
			speed_x += 2;
			status = 1;
			direction = 1;
		}
		if (left == true) {
			speed_x -= 2;
			status = 0;
			direction = 0;
		}
		if (right == false && left == false) {
			speed_x = (int) (speed_x / 1.5);
			if (speed_x > -1 && speed_x < 1) {
				speed_x = 0;
			}
		}
		if (speed_x >= MAXSPEED)
			speed_x = MAXSPEED;
		if (speed_x <= MiNSPEED)
			speed_x = MiNSPEED;
		if (speed_y >= MAXSPEED)
			speed_y = MAXSPEED; 

		jump(space);
		if(collision) {
			speed_x = 0;
			speed_y =0;
		}
//		chara_x += speed_x;
//		chara_y += speed_y;
	}

	public void jump(boolean space) {
		if (landing == true) {
			speed_y = 0;    
			canjump = true;
			position = speed_y;
		}else{
			speed_y += GRAVITY;
		}
		if (space == true && canjump == true) {
			System.out.println("jump");
			speed_y -= 16;
			canjump = false;
			landing = false;

		}
	}
	

}
