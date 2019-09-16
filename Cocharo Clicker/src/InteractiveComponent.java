import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class InteractiveComponent {

	private BufferedImage compImage;

	private int posx, posy, cposx, cposy, iw, ih, cih, ciw, width, height, tposx, tposy, ctposx, ctposy, popx, popy;

	public String mousePos;

	private String compText, key, imagePath;

	private Font nFont, sFont;
	
	private State state;
	
	private double alpha;

	private Color color;
	
	private boolean isClicked, scaleFactor, isText, scaled, hSet;

	public InteractiveComponent(String path, int x, int y, int w, int h, String id, int r, int g, int b, State st) {
		alpha = 0;
		color = new Color(r, g, b, (int) alpha);
		state = st;
		hSet = false;
		scaled = false;
		isText = false;
		imagePath = path;
		key = id;
		scaleFactor = true;
		isClicked = false;
		mousePos = "(0, 0)";
		posx = x;
		posy = y;
		nFont = new Font("Times New Roman", 0, 12);
		try {
			compImage = ImageIO.read(getClass().getResourceAsStream(path));
			iw = compImage.getWidth();
			ih = compImage.getHeight();

			width = w;
			height = h;
			cposx = posx;
			cposy = posy;
			ciw = w;
			cih = h;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InteractiveComponent(String path, int x, int y, int w, int h, String text, Font font, int tx, int ty, String id, int r, int g, int b, State st) {
		alpha = 0;
		color = new Color(r, g, b, (int) alpha);
		state = st;
		hSet = false;
		scaled = false;
		isText = false;
		imagePath = path;
		key = id;
		scaleFactor = true;
		isClicked = false;
		mousePos = "(0, 0)";
		tposx = tx;
		tposy = ty;
		posx = x;
		posy = y;
		cposx = posx;
		cposy = posy;
		ctposx = tposx;
		ctposy = tposy;
		compText = text;
		nFont = font;
		sFont = font;

		try {
			compImage = ImageIO.read(getClass().getResourceAsStream(path));
			iw = compImage.getWidth();
			ih = compImage.getHeight();
			width = w;
			height = h;
			cih = h;
			ciw = w;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InteractiveComponent(String text, Font font, int tx, int ty, int w, int h, String id, int r, int g, int b, State st) {
		alpha = 0;
		color = new Color(r, g, b, (int) alpha);
		state = st;
		scaled = false;
		isText = true;
		key = id;
		scaleFactor = false;
		compText = text;
		nFont = font;
		sFont = font;
		isClicked = false;
		mousePos = "(0,0)";
		posx = tx;
		posy = ty;
		tposx = tx;
		tposy = ty;
		ctposx = tposx;
		ctposy = tposy;
		cposx = posx;
		cposy = posy;
		cih = h;
		ciw = w;
		width = w;
		height = h;
	}

	public InteractiveComponent() {
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(int r, int g, int b, int a) {
		color = new Color(r, g, b, a);
	}
	
	public State getState() {
		return state;
	}

	public String getID() {
		return key;
	}

	public int getTX() {
		return tposx;
	}

	public int getTY() {
		return tposy;
	}

	public int getCX() {
		return cposx;
	}

	public int getCY() {
		return cposy;
	}

	public int getX() {
		return posx;
	}

	public int getY() {
		return posy;
	}

	public int getiH() {
		return ih;
	}

	public int getiW() {
		return iw;
	}

	public int getW() {
		return width;
	}

	public int getCW() {
		return ciw;
	}

	public int getCH() {
		return cih;
	}

	public int getH() {
		return height;
	}

	public Font getFont() {
		return sFont;
	}

	public BufferedImage getImage() {
		return compImage;
	}

	public String getText() {
		return compText;
	}

	public void setImage(String path) {
		try {
			compImage = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void hover() {
		hSet = true;
	}

	public void unhover() {
		hSet = false;
	}

	public boolean isHover() {
		return hSet;
	}

	public void click() {
		isClicked = true;
	}

	public void unclick() {
		isClicked = false;
	}

	public void scale() {
		if (!scaled && scaleFactor) {
			height *= 0.8;
			width *= 0.8;

			posx += (width / 8);
			posy += (height / 8);
			scaled = true;
		}
	}

	public void unscale() {
		height = cih;
		width = ciw;

		posx = cposx;
		posy = cposy;
		scaled = false;
	}

	public void scaleText() {
		tposx += (ciw / 10);
		tposy -= (cih / 10);
		sFont = new Font(nFont.getFontName(), nFont.getStyle(), (int) (nFont.getSize() * 0.8));
	}

	public void unscaleText() {

		sFont = new Font(nFont.getFontName(), nFont.getStyle(), (int) (nFont.getSize()));

		tposx = ctposx;
		tposy = ctposy;
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void update() {
//		System.out.println("update!");
//		boolean h = InputUtil.isHoveri(MouseManager.mm.getMouseX(), MouseManager.mm.getMouseY(), posx, posy, width, height);
//		if (this.getID().equals("menu")) {
//			if (h) {
////				System.out.println("Hover!");
//				this.setImage("images/hmenu.png");
//			} 
//			if (!h) {
////				System.out.println("Nothover");
//				this.setImage("images/menu.png");
//			}
//		}
	}

	public boolean isClicked() {
		return isClicked;
	}

	public int getPopx() {
		return popx;
	}

	public void setPopx(int popx) {
		this.popx = popx;
	}

	public int getPopy() {
		return popy;
	}

	public void setPopy(int popy) {
		this.popy = popy;
	}
}