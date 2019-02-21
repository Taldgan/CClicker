import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ScreenListener implements MouseListener, MouseMotionListener {

	public String mousePos;
	
	public ScreenListener() {
		mousePos = "(0, 0)";
	}
	public void mouseMoved(MouseEvent e) {
		getMouse(e);
//		System.out.println(mousePos);
	}
	
	public void getMouse(MouseEvent e) {
		mousePos = "(" + e.getX() + ", " + e.getY() + ")";
	}
	
	public void mouseClicked(MouseEvent e) {
				
		boolean c = InputUtil.isClicked(e, 42, 5, 350-42, 311-5);
		if (c) {
			
		} else {
			getMouse(e);
			System.out.println(mousePos);
		}
		
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		boolean c = InputUtil.isClicked(e, 42, 5, 350-42, 311-5);
		if (c) {
			
		}
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		getMouse(e);
		System.out.println("Dragged!");
	}
}
