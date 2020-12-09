package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import javax.sound.sampled.Clip;

public abstract class runGame extends initGame {

	int attacktime = 10;
	BufferedImage backimage;
	BufferedImage gameclear;
	BufferedImage gameover;
	BufferedImage gamestart;
	boolean i = false;
	boolean i2 = false;
	BufferedImage Leffect;
	BufferedImage Reffect;
	BufferedImage titleimage;
	Clip clip1,clip2,clip3,clip4;

	public runGame(String title) {
		super(title);
	}

	@Override
	public void runtitle(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("title");
		g.drawImage(titleimage, 0, 0, frame);
	
	}

	@Override
	protected void runstart(Graphics g) {
		// TODO Auto-generated method stub
//		g.clearRect(0, 0, Window_w, Window_h);
//		g.setColor(Color.BLACK);
//		g.setFont(new Font("SansSerif", Font.BOLD, 60));
//		g.drawString("start", 0, 60);
		g.drawImage(gamestart, 0, 0, frame);
	
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public void runplay(Graphics g) {
			// TODO Auto-generated method stub
		midiseq.start();
			//
			g.drawImage(backimage, 0, 0, frame);
			if(space == true && player.canjump == true) {
				clip2.stop();
				clip2.setFramePosition(0);
				clip2.start();
			}
			// 攻撃
			if (shift == true && attacktime == 10) {
				Iterator<Object> it = effect.iterator();
				if (player.direction == 0) {
					effect.add(new Effect(Leffect, player.chara_x - 20, player.chara_y - 5));
				}
				if (player.direction == 1) {
					effect.add(new Effect(Reffect, player.chara_x + 10, player.chara_y - 5));
				}
				attacktime = 0;
				clip1.stop();
				clip1.setFramePosition(0);
				clip1.start();
			}
			if (attacktime != 10) {
				attacktime++;
			}
			// プレイヤーの動き
			player.move(right, left, space);
	
			// 当たり判定（プレイヤーとゴール）
			if (exit1.hit(player) == true) {
				midiseq.stop();
				clip4.stop();
				clip4.setFramePosition(0);
				clip4.start();
				gamescene = GAMECLEAR;
			}
			// 当たり判定(地表と自キャラ)
			Iterator<Object> it = surfase1.iterator();
			while (it.hasNext() == true) {
				Surfase sf = (Surfase) it.next();
				if (sf.hit(player) == true) {
					System.out.println("landing");
					player.landing = true;
					i = true;
					if (player.speed_y >= 0) {
						player.chara_y = sf.chara_y - 14;
						player.speed_y = 0;
					}
				} else {
					if (i == false) {
						player.landing = false;
					}
				}
			}
			i = false;
			// 当たり判定(地表と敵)
			it = surfase1.iterator();
			while (it.hasNext() == true) {
				Surfase sf = (Surfase) it.next();
				Iterator<Object> it2 = enemy1.iterator();
				while (it2.hasNext() == true) {
					Enemy em = (Enemy) it2.next();
	
					if (sf.hit(em, 1) == true) {
						i2 = true;
						em.drop = false;
						if (em.speed_y >= 0) {
							em.chara_y = sf.chara_y - 14;
							em.speed_y = 0;
						}
					} else {
						if (i2 == false) {
							em.drop = true;
							if (em.turn == false) {
	//							em.turn = true;
							} else {
	//							em.turn = false;
							}
						}
					}
				}
				i2 = false;
			}
	
			// 当たり判定（地面と自キャラ）
			for (int i = 0; i < 3; i++) {
				Iterator it1 = ground2[i].iterator();
				while (it1.hasNext() == true) {
					Ground gd = (Ground) it1.next();
					if (gd.righthit(player) == true) {
						System.out.println("collision");
						if (player.speed_x < 0) {
							player.speed_x = 0;
							player.chara_x = gd.hitbox_x + gd.hitbox_w;
						}
	
					}
					if (gd.lefthit(player) == true) {
						System.out.println("collision");
						if (player.speed_x > 0) {
							player.speed_x = 0;
							player.chara_x = gd.chara_x - player.image_w;
						}
	
					}
					if (gd.underhit(player) == true) {
						System.out.println("collision");
						if (player.speed_y < 0) {
							player.speed_y = 0;
						}
					}
				}
			}
	
			// 当たり判定（地面と敵）
			for (int i = 0; i < 3; i++) {
				Iterator it1 = ground2[i].iterator();
				while (it1.hasNext() == true) {
					Ground gd = (Ground) it1.next();
					Iterator it2 = enemy1.iterator();
					while (it2.hasNext() == true) {
						Enemy em = (Enemy) it2.next();
						if (gd.righthit(em) == true) {
							em.turn = true;
						}
	
						if (gd.lefthit(em) == true) {
							em.turn = false;
						}
	
					}
				}
			}
			// 当たり判定(敵と自キャラ)
			it = enemy1.iterator();
			while (it.hasNext() == true) {
				Enemy em = (Enemy) it.next();
				if (em.hit(player) == true) {
					midiseq.stop();
					clip3.stop();
					clip3.setFramePosition(0);
					clip3.start();
					gamescene = GAMEOVER;
				}
			}
			// 当たり判定(攻撃と敵)
			it = effect.iterator();
			while (it.hasNext() == true) {
				Effect ef = (Effect) it.next();
				Iterator it2 = enemy1.iterator();
				while (it2.hasNext() == true) {
					Enemy em = (Enemy) it2.next();
					if (ef.hit(em)) {
						it2.remove();
					}
				}
			}
			// 表示(地表)
			for (int i = 0; i < 3; i++) {
				Iterator it1 = surfase1.iterator();
				while (it1.hasNext() == true) {
					Surfase jt = (Surfase) it1.next();
					jt.draw(g, frame);
				}
			}
			// 表示(地面)
			for (int i = 0; i < 3; i++) {
				Iterator it1 = ground2[i].iterator();
				while (it1.hasNext() == true) {
					Ground jt = (Ground) it1.next();
					jt.draw(g, frame);
				}
			}
			// 表示(攻撃)
			Iterator it1 = effect.iterator();
			while (it1.hasNext() == true) {
				Effect jt = (Effect) it1.next();
				jt.draw(g, frame);
				if (jt.end == true) {
					it1.remove();
				}
			}
			// 表示(敵)
			it1 = enemy1.iterator();
			while (it1.hasNext() == true) {
				Enemy em = (Enemy) it1.next();
				em.draw(g, frame, 3);
			}
			// 表示（自キャラ）
			player.draw(g, frame);
			if (player.OutSide() || gametimer <= 0) {
				midiseq.stop();
				clip3.stop();
				clip3.setFramePosition(0);
				clip3.start();
				gamescene = GAMEOVER;
			}
			// 表示（出口）
			exit1.draw(g, frame);
			//表示（制限時間）
			g.setColor(Color.WHITE);
			g.setFont(new Font("SansSerif", Font.BOLD, 10));
			g.drawString("time : " + gametimer, 30, 20);

		}

	@Override
	public void runover(Graphics g) {
			// TODO Auto-generated method stub
	//		g.clearRect(0, 0, Window_w, Window_h);
	//		g.setColor(Color.BLACK);
	//		g.setFont(new Font("SansSerif", Font.BOLD, 60));
	//		g.drawString("GAMEOVER", 0, 60);
			g.drawImage(gameover, 0, 0, frame);
	
		}

	@Override
	public void runclear(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(gameclear, 0, 0, frame);
	
	}

}