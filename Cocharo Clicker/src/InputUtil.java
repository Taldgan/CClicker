import java.awt.event.MouseEvent;

public class InputUtil {
	@Deprecated()
	public static boolean isClicked(MouseEvent e, int x, int y, int w, int h) {
		return (e.getX() <= x + w && e.getX() >= x && e.getY() >= y && e.getY() <= y + h);
	}

	public static boolean isHoveri(int mx, int my, int tx, int ty, int tw, int th, boolean matches) {
		return (mx >= tx && mx <= tx + tw && my >= ty && my <= ty + th && matches);
	}

	public static boolean isClickedi(int mx, int my, int tx, int ty, int tw, int th, boolean matches) {
		return (mx >= tx && mx <= tx + tw && my >= ty && my <= ty + th && matches);
	}

}
