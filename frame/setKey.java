package frame;

import java.awt.event.KeyEvent;

/**
 * @author a50605
 * @version 1.0
 */
public abstract class setKey extends gameframe {

	boolean left;
	boolean right;
	boolean shift;
	boolean space;

	public setKey(String title) {
		super(title);
	}

	@Override
	protected void pressedmain(int key) {
		// TODO Auto-generated method stub
		if (key == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if (key == KeyEvent.VK_LEFT) {
			left = true;
		}
		if (key == KeyEvent.VK_SPACE) {
			space = true;
		}
		if (key == KeyEvent.VK_SHIFT) {
			shift = true;
		}
	}

	@Override
	protected void releasedmain(int key) {
		// TODO Auto-generated method stub
		if (key == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if (key == KeyEvent.VK_LEFT) {
			left = false;
		}
		if (key == KeyEvent.VK_SPACE) {
			space = false;
		}
		if (key == KeyEvent.VK_SHIFT) {
			shift = false;
		}
	}

}