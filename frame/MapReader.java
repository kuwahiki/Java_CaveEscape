package frame;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public abstract class MapReader extends setKey {

	public static final String delimiter = ",";
	BufferedImage enemy;
	ArrayList<Object> enemy1;
	BufferedImage ground;
	@SuppressWarnings("rawtypes")
	ArrayList[] ground2 = new ArrayList[3];
	String map;
	BufferedImage surfase;
	ArrayList<Object> surfase1;
	BufferedImage exit;
	Exit exit1;
	Point position = new Point(0, 0);

	public MapReader(String title) {
		super(title);
	}

	@SuppressWarnings({ "resource", "unchecked" })
	public void mapreader() {
		try {
			Random rand = new Random();
			InputStream fr = getClass().getResourceAsStream(map); 
			BufferedReader br = new BufferedReader(new InputStreamReader(fr));
			String line = "";
			String[] tempArr;
			while ((line = br.readLine()) != null) {
				tempArr = line.split(delimiter);
				for (String tempStr : tempArr) {
					System.out.print(tempStr + " ");
					switch (tempStr) {
					case "0":
						break;
					case "1":
						ground2[0].add(new Ground(ground, position.x, position.y, 0));
						break;
					case "2":
						ground2[1].add(new Ground(ground, position.x, position.y, 20));
						break;
					case "3":
						ground2[2].add(new Ground(ground, position.x, position.y, 40));
						break;
					case "4":
						surfase1.add(new Surfase(surfase, position.x, position.y, rand.nextInt(5)));
						break;
					case "5":
						exit1 = new Exit(exit, position.x, position.y);
						break;
					case "6":
						surfase1.add(new Surfase(surfase, position.x, position.y, rand.nextInt(5)));
						enemy1.add(new Enemy(enemy, position.x, position.y - 1, rand.nextBoolean()));
					}
					position.x += 20;
				}
				position.y += 20;
				position.x = 0;
				System.out.println();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}