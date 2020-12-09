package frame;

import java.awt.image.BufferedImage;

/**
 * @author a50605
 *
 *
 *         画像サイズ20*30
 */
public class Effect extends GameChara {
	boolean end = false;

	public Effect(BufferedImage img, int x, int y) {
		super(img, x, y, 0, 0, 20, 30, 5);
		// TODO Auto-generated constructor stub
		hitbox_x = chara_x;
		hitbox_y = chara_y;
		hitbox_w = image_w;
		hitbox_h = image_h;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		if (sceen == numsceen) {
			end = true;
		}
	}

}
