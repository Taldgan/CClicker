import java.awt.Font;

public class StoreComponent extends InteractiveComponent {
	
	private boolean tTip;
	
	private Upgrade upgrade;
	
	public StoreComponent(String path, int x, int y, int w, int h, String text, Font font, int tx, int ty, String id, int r, int g, int b, State st, Upgrade up) {
		super(path, x, y, w, h, text, font, tx, ty, id, r, g, b, st);
		upgrade = up;
		tTip = false;
	}
	public StoreComponent(String path, int x, int y, int w, int h, String id, int r, int g, int b, State st, Upgrade up) {
		super(path, x, y, w, h, id, r, g, b, st);
		upgrade = up;
		tTip = false;
		
	}
	
	public boolean getTip() {
		return tTip;
	}
	
	public Upgrade getUp() {
		return upgrade;
	}
	
	public void setTip(boolean flag) {
		tTip = flag;
	}
	
}
