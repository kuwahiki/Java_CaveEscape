package frame;

import java.awt.image.BufferedImage;

/**
 * @author a50605
 *
 *
 *画像サイズ　40*40
 */
public class Exit extends GameChara {

	public Exit(BufferedImage img, int x, int y) {
		super(img, x, y, 0, 0, 40, 40, 1);
		// TODO Auto-generated constructor stub
		hitbox_x = chara_x + 20; 
		hitbox_y = chara_y;
		hitbox_w = 20;
		hitbox_h = image_h;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

}
