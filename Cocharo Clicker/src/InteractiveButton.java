import java.awt.Color;
import java.awt.Font;

public class InteractiveButton extends InteractiveComponent {
	public InteractiveButton(String path, int x, int y, int w, int h, String text, Font font, int tx, int ty, String id, int r, int g, int b, State st) {
		super(path, x, y, w, h, text, font, tx, ty, id, r, g, b, st);
	}
	public InteractiveButton(String path, int x, int y, int w, int h, String id, int r, int g, int b, State st) {
		super(path, x, y, w, h, id, r, g, b, st);
	}
}

