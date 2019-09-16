import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

	private int mouseX, mouseY;
	private boolean mouseDown = false;
	private boolean clicked = false;
	
	public static MouseManager mm = new MouseManager();
	
	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseClicked(MouseEvent e) {
		clicked = true;
//		System.out.println("click");
	}
	
	public boolean isClicked() {
		return clicked;
	}
	
	public void no() {
		clicked = false;
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		mouseDown = true;
	}

	public void mouseReleased(MouseEvent e) {
		mouseDown = false;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public boolean isMouseDown() {
		return mouseDown;
	}
	
}
