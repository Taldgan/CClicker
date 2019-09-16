import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Item {
	
	private String path, key, iText;
	private int posx, posy, width, height, iw, ih;
	private Font nFont;
	private Color color;
	private BufferedImage iImage;
	
	public Item(String path, int x, int y, int w, int h, String id) {
		try {
			this.setPath(path);
			iImage = ImageIO.read(getClass().getResourceAsStream(path));
			posx = x;
			posy = y;
			width = w;
			height = h;
			iw = iImage.getWidth();
			ih = iImage.getHeight();	
			key = id;
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Item(String text, int x, int y, Font font, Color c, String id) {
		iText = text;
		posx = x;
		posy = y;
		nFont = font;
		color = c;
		key = id;
	}

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public String getText() {
		return iText;
	}
	
	public Font getFont() {
		return nFont;
	}
	
	public String getID() {
		return key;
	}
	
	public int getX() {
		return posx;
	}

	public void setX(int x) {
		posx = x;
	}

	public int getY() {
		return posy;
	}

	public void setY(int y) {
		posy = y;
	}

	public int getW() {
		return width;
	}

	public int getH() {
		return height;
	}
	
	public BufferedImage getImage() {
		return iImage;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		path = path;
	}
}
