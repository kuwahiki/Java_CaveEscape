package frame;

import java.awt.image.BufferedImage;

/**
 * @author a50605
 *
 *
 * 　画像サイズ 20*20
 */
public class Ground extends GameChara {

	public Ground(BufferedImage img,int x, int y,int ix) {
		super(img, x, y, ix, 0, 20, 20, 0);
		// TODO Auto-generated constructor stub
		hitbox_x = chara_x;
		hitbox_y = chara_y;
		hitbox_h = image_h;
		hitbox_w = image_w;
	}
	public boolean righthit(GameChara obj) {
		if (obj.getpoint1().y > getpoint1().y && obj.getpoint1().y < getpoint2().y  || obj.getpoint2().y > getpoint1().y && obj.getpoint2().y < getpoint2().y) {
		if (getpoint2().x >= obj.getpoint1().x && getpoint2().x <= obj.getpoint2().x) {
				return true;
			}
		}
		return false;
	}
	public boolean lefthit(GameChara obj) {
		if (obj.getpoint1().y > getpoint1().y && obj.getpoint1().y < getpoint2().y  || obj.getpoint2().y > getpoint1().y && obj.getpoint2().y < getpoint2().y) {
		if (getpoint1().x >= obj.getpoint1().x && getpoint1().x <= obj.getpoint2().x) {
				return true;
			}
		}
		return false;
	}
	public boolean underhit(GameChara obj) {
		if (obj.getpoint1().x > getpoint1().x + 1 && obj.getpoint1().x < getpoint2().x  - 1 || obj.getpoint2().x > getpoint1().x  + 1&& obj.getpoint2().x < getpoint2().x - 1) {
			if (obj.getpoint1().y < getpoint2().y  && obj.getpoint1().y > getpoint1().y) {
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
