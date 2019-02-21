import java.awt.event.MouseEvent;

public class InputUtil {
	public static boolean isClicked(MouseEvent e, int x, int y, int w, int h) {
		return (e.getX() <= x + w && e.getX() >= x && e.getY() >= y && e.getY() <= y + h);
	}
}
