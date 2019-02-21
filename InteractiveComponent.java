	import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class InteractiveComponent implements MouseListener, MouseMotionListener {
	
	private BufferedImage compImage;
	
	private int posx, posy, cposx, cposy, iw, ih, cih, ciw, width, height, tposx, tposy, ctposx, ctposy;
	
	public String mousePos;
	
	private String compText, key;
	
	private Font nFont, sFont;
	
	private boolean compClicked, scaleFactor;
	
	public InteractiveComponent(String path, int x, int y, int w, int h, String id) { 
		key = id;
		scaleFactor = true;
		compClicked = false;
		mousePos = "(0, 0)";
		posx = x;
		posy = y;
		try {
			compImage = ImageIO.read(getClass().getResourceAsStream(path));
			iw = compImage.getWidth();
			ih = compImage.getHeight();	
			
			width = w;
			height = h;
			cposx = posx;
			cposy = posy;
			ciw = iw;
			cih = ih;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public InteractiveComponent(String path, int x, int y, int w, int h, String text, Font font, int tx, int ty, String id) { 
		key = id;
		scaleFactor = true;
		compClicked = false;
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
	
	
	public InteractiveComponent(String text, Font font, int tx, int ty, int w, int h, String id) {
		key = id;
		scaleFactor = false;
		compText = text;
		nFont = font;
		sFont = font;
		compClicked = false;
		mousePos = "(0,0)";
		posx = tx;
		posy = ty;
		tposx = tx;
		tposy = ty;
		ctposx = tposx;
		ctposy= tposy;
		cposx = posx;
		cposy = posy;
		cih = h;
		ciw = w;
		width = w;
		height = h;	
	}
	
	public InteractiveComponent() {
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
	
	public void scale() {
		if(scaleFactor) {
			height *= 0.8;
			width *= 0.8;
			
			posx += (width/8);
			posy += (height/8);	
		}
	}
	
	public void unscale() {
		height = cih;
		width = ciw;
		
		posx = cposx;
		posy = cposy;
	}
	
	public void scaleText() {
		tposx += (ciw/10);
		tposy -= (cih/10);
		sFont = new Font(nFont.getFontName(), nFont.getStyle(), (int) (nFont.getSize()*0.8));
	}
	
	public void unscaleText() {

		sFont = new Font(nFont.getFontName(), nFont.getStyle(), (int) (nFont.getSize()));
		
		tposx = ctposx;
		tposy = ctposy;
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		getMouse(e);
	}

	public void getMouse(MouseEvent e) {
		mousePos = "(" + e.getX() + ", " + e.getY() + ")";
	}
	
	public void mouseClicked(MouseEvent e) {
		boolean c = InputUtil.isClicked(e, posx, posy, width, height);
		if (c) {
			compClicked = true;
//			System.out.println("compclicked!");
		} else {
			getMouse(e);
		}
	}
	
	public boolean isClicked() {
		return compClicked;
	}
	
	public void mouseEntered(MouseEvent e) {
			
	}
	
	public void mouseExited(MouseEvent e) {
			
	}
	
	public void no() {
		compClicked = false;
	}
	
	public void mousePressed(MouseEvent e) {		
		boolean c = InputUtil.isClicked(e, posx, posy, width, height);
		if(c) {
			scale();
		}
		if(InputUtil.isClicked(e, tposx, tposy, width, height) && scaleFactor == false) {
			scaleText();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		compClicked = false;
		unscale();
		unscaleText();
	}

	
}