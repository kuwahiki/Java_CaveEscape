package frame;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 * @author a50605
 * @version 1.0
 */
public abstract class gameframe {

	JFrame frame;
	BufferStrategy strategy;
	static int Window_w = 640;
	static int Window_h = 480;

	int waittimer = 0;
	int gametimer = 2000;

	static final int GAMETITLE = 0;
	static final int GAMEPLAY = 1;
	static final int GAMECLEAR = 2;
	static final int GAMEOVER = 3;
	int gamescene = 0;
	
	public Sequencer midiseq = null;

	public gameframe(String title) {
		// TODO Auto-generated constructor stub
		frame = new JFrame(title);
		frame.setVisible(true);
		Insets insets = frame.getInsets();
		frame.setSize(Window_w + insets.left + insets.right, Window_h + insets.top + insets.bottom);
		frame.setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		frame.createBufferStrategy(2);
		strategy = frame.getBufferStrategy();

		frame.addKeyListener(new MyKeyAdapter());

		Timer t = new Timer();
		t.schedule(new MyTimerTask(), 10, 30);

	}

	public void midiRead(String fname) {
		if (midiseq == null) {
			try {
				midiseq = MidiSystem.getSequencer();
				midiseq.open();
			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			}
		}
		try {
			Sequence seq = MidiSystem.getSequence(getClass().getResource(fname));
			midiseq.setSequence(seq);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Clip wavRead(String fname) {
		Clip clip = null;

		try {
			AudioInputStream aistream = AudioSystem.getAudioInputStream(getClass().getResource(fname));
			DataLine.Info info = new DataLine.Info(Clip.class, aistream.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(aistream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		return clip;
	}
	
	
	/**
	 * @author a50605
	 * @version 1.0
	 *
	 */
	private class MyKeyAdapter extends KeyAdapter {

		@SuppressWarnings("static-access")
		@Override
		public void keyPressed(KeyEvent ev) {
			// TODO Auto-generated method stub
			super.keyPressed(ev);
			int keyread = ev.getKeyCode();
			switch (gamescene) {
			case 0:
				if (keyread == ev.VK_P)
					initplay();
				break;
			case 1:
				pressedmain(keyread);
				break;
			case 2:
				if (keyread == ev.VK_P)
					initTitle();
				break;
			case 3:
				if (keyread == ev.VK_P)
					initTitle();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent ev) {
			// TODO Auto-generated method stub
			super.keyReleased(ev);
			int keyread = ev.getKeyCode();
				releasedmain(keyread);
		}

	}

	/**
	 * @author a50605
	 * @version 1.0
	 *
	 */
	private class MyTimerTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Graphics g = strategy.getDrawGraphics();
			if (strategy.contentsLost() == false) {
				Insets inset = frame.getInsets();
				g.translate(inset.left, inset.top);
				g.clearRect(0, 0, Window_w, Window_h);
				switch (gamescene) {
				case 0:
					runtitle(g);
					break;
				case 1:
					if (waittimer > 0) {
						runstart(g);
						waittimer--;
					} else {
						runplay(g);
						gametimer--;
					}
					break;
				case 2:
					runclear(g);
					break;
				case 3:
					runover(g);
					break;
				}

				strategy.show();
				g.dispose();
			}

		}

	}

	public abstract void runtitle(Graphics g);

	protected abstract void releasedmain(int key);

	protected abstract void pressedmain(int key);

	protected abstract void initGAMEOVER();

	protected abstract void initclear();

	protected abstract void runstart(Graphics g);

	protected abstract void initTitle();

	protected abstract void initplay();

	public abstract void runover(Graphics g);

	public abstract void runclear(Graphics g);

	public abstract void runplay(Graphics g);
}
