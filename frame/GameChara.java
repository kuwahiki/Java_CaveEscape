package frame;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * @author a50605
 *
 */
public abstract class GameChara {
	BufferedImage image;
	Point point1, point2;
	final static int GRAVITY = 2;
	static int MAXSPEED = 8;
	static int MiNSPEED = -8;
	final static int ARIA_X = -20;
	final static int ARIA_Y = -20;
	final static int ARIA_W = gameframe.Window_w + 40;
	final static int ARIA_H = gameframe.Window_h + 40;
	int chara_x, chara_y;
	int image_x, image_y, image_w, image_h;
	int hitbox_x, hitbox_y, hitbox_w, hitbox_h;
	int position;
	int direction = 0;
	int numsceen;
	int sceen = 0;

	public void draw(Graphics g, ImageObserver io) {
		if (numsceen == 0) {
			g.drawImage(image, chara_x, chara_y, chara_x + image_w, chara_y + image_h, image_x, image_y,
					image_x + image_w, image_y + image_h, io);
		} else {
			if (sceen == numsceen) {
				sceen = 0;
			}
			if (sceen < numsceen) {
				g.drawImage(image, chara_x, chara_y, chara_x + image_w, chara_y + image_h, image_x + (image_w * sceen),
						image_y + (image_h * direction), (image_w * sceen) + image_w,
						image_y + image_h + (image_h * direction), io);
				sceen++;
			}

		}
		move();
	}

	public abstract void move();

	public boolean OutSide() {
		if (chara_x + image_w < ARIA_X || chara_x > ARIA_W || chara_y + image_h < ARIA_Y || chara_y > ARIA_H) {
			return true;
		} else {
			return false;
		}
	}

	// 左上
	Point getpoint1() {
		point1 = new Point(hitbox_x, hitbox_y);
		return point1;
	}

	// 右下
	Point getpoint2() {
		point2 = new Point(hitbox_x + hitbox_w, hitbox_y + hitbox_h);
		return point2;
	}

	// 当たり判定
	public boolean hit(GameChara obj) {
		if (obj.getpoint1().x >= getpoint1().x && obj.getpoint1().x <= getpoint2().x
				|| obj.getpoint2().x >= getpoint1().x && obj.getpoint2().x <= getpoint2().x) {
			if (obj.getpoint1().y <= getpoint2().y && obj.getpoint1().y >= getpoint1().y
					|| obj.getpoint2().y <= getpoint2().y && obj.getpoint2().y >= getpoint1().y) {
				return true;
			}
		}
		return false;
	}

	public GameChara(BufferedImage img, int x, int y, int ix, int iy, int iw, int ih, int sceen) {
		// TODO Auto-generated constructor stub
		image = img;
		chara_x = x;
		chara_y = y;
		image_x = ix;
		image_y = iy;
		image_w = iw;
		image_h = ih;
		numsceen = sceen;
	}

}
