package main.java;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BitmapItem extends SlideItem {
	public BufferedImage bufferedImage;
	private String imageName;

	protected static final String FILE = "File ";
	protected static final String NOTFOUND = " not found";

	public BitmapItem(int level, String name) {
		super(level);
		this.imageName = name;
		loadImage(name);
	}

	private void loadImage(String imageName) {
		try {
			bufferedImage = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			System.err.println(FILE + imageName + NOTFOUND);
			bufferedImage = null; // Assign null to indicate load failure
		}
	}

	public BitmapItem() {
		this(0, "");
	}

	public String getName() {
		return imageName;
	}

	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
		if (bufferedImage == null) return new Rectangle();
		return new Rectangle((int) (myStyle.getIndent () * scale), 0,
				(int) (bufferedImage.getWidth(observer) * scale),
				((int) (myStyle.getLeading () * scale)) +
						(int) (bufferedImage.getHeight(observer) * scale));
	}

	public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
		if (bufferedImage == null) return; // Do nothing if image failed to load
		int width = x + (int) (myStyle.getIndent () * scale);
		int height = y + (int) (myStyle.getLeading () * scale);
		g.drawImage(bufferedImage, width, height, (int) (bufferedImage.getWidth(observer) * scale),
				(int) (bufferedImage.getHeight(observer) * scale), observer);
	}

	@Override
	public String toString() {
		return "BitmapItem[" + getLevel() + "," + imageName + "]";
	}
}
