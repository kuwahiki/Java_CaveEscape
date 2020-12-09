package frame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class initGame extends MapReader {

	ArrayList<Object> effect;
	Player player;
	BufferedImage player1;
	public initGame(String title) {
		super(title);
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	protected void initTitle() {
		// TODO Auto-generated method stub
	
		gamescene = GAMETITLE;
		gametimer = 1000;
		Iterator it;
		for (int i = 0; i < 3; i++) {
			it = ground2[i].iterator();
			while (it.hasNext() == true) {
				Ground gd = (Ground) it.next();
				it.remove();
			}
		}
		it = enemy1.iterator();
		while (it.hasNext() == true) {
			Enemy em = (Enemy) it.next();
			it.remove();
		}
		it = surfase1.iterator();
		while (it.hasNext() == true) {
			Surfase sf = (Surfase) it.next();
			it.remove();
		}
		position.x = 0;
		position.y = 0;
		player.speed_x = 0;
		player.speed_y = 0;
	}

	@Override
	protected void initplay() {
		// TODO Auto-generated method stub
		player = new Player(player1, 40, 400);
		for (int i = 0; i < 3; i++) {
			ground2[i] = new ArrayList<Object>();
		}
		effect = new ArrayList<Object>();
		surfase1 = new ArrayList<Object>();
		enemy1 = new ArrayList<Object>();
		/*
		 * for (int i = 0; i < 3; i++) { ground2[0].add(new Ground(ground, 0, (Window_h
		 * / 2) + 20*i, 0)); ground2[1].add(new Ground(ground, (Window_w / 2) - 10,
		 * (Window_h / 2) + 20 * i, 20)); } for (int i = 0; i < 30; i++) {
		 * ground2[1].add(new Ground(ground, 20 + 20 * i, (Window_h / 2) + 40, 20)); }
		 * ground2[2].add(new Ground(ground, Window_w - 20, (Window_h / 2) + 40, 40));
		 * for (int i = 0; i < 3; i++) { Iterator it = ground2[i].iterator(); while
		 * (it.hasNext() == true) { Ground gd = (Ground) it.next(); surfase1.add(new
		 * Surfase(surfase, gd)); } }
		 */
	
		// マップの作成
		mapreader();
	
		gamescene = GAMEPLAY;
		waittimer = 50;
		midiseq.setTickPosition(0);
	}

	@Override
	protected void initGAMEOVER() {
		// TODO Auto-generated method stub
		gamescene = GAMEOVER;
	
	}

	@Override
	protected void initclear() {
		// TODO Auto-generated method stub
		gamescene = GAMECLEAR;
	
	}

}