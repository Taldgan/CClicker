import java.awt.Font;

public class InteractiveButton extends InteractiveComponent {
	public InteractiveButton(String path, int x, int y, int w, int h, String text, Font font, int tx, int ty, String id) {
		super(path, x, y, w, h, text, font, tx, ty, id);
	}
	public InteractiveButton(String path, int x, int y, int w, int h, String id) {
		super(path, x, y, w, h, id);
	}
}
