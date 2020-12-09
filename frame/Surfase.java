package frame;

import java.awt.image.BufferedImage;

/**
 * @author a50605
 *
 *画像サイズ　120*6
 */
public class Surfase extends GameChara {
	int num = 0;
	public Surfase(BufferedImage img,int x,int y,int ix) {
		super(img, x, y+ 14, ix*20, 0, 20, 6, 0);
		// TODO Auto-generated constructor stub
		if (num == 5) {
			num = 0;
		}
		image_x = 20*num; 
		hitbox_x = x + 2;
		hitbox_y = y + 14;
		hitbox_w = image_w - 2;
		hitbox_h = 8;
		num++;
	}
	
	public boolean hit(GameChara obj,int a) {
		if (obj.getpoint1().x >= getpoint1().x && obj.getpoint1().x <= getpoint2().x
				|| obj.getpoint2().x >= getpoint1().x && obj.getpoint2().x <= getpoint2().x) {
			if ( obj.getpoint2().y <= getpoint2().y && obj.getpoint2().y >= getpoint1().y) {
				return true;
			}
		}
		return false;
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
}
