import java.awt.Font;

public class FloatComponent extends InteractiveComponent {

	private String sound, cSound;

	private double fx, fy, px, py;

	private boolean screen, die;
	
	private Upgrade upgrade;
	
	public FloatComponent(String path, int x, int y, int w, int h, int px, int py, String s, String cs, Upgrade up) {
		super(path, x, y, w, h, up.getName() + "f", 0, 0, 0, State.STATE_ALL);
		upgrade = up;
		die = false;
		this.fx = x;
		this.fy = y;
		this.px = px;
		this.py = py;
		sound = s;
		cSound = cs;
		screen = true;
	}

	public void plusX(double num) {
		fx += num / 20;
	}

	public void minusX(double num) {
		fx -= num / 20;
	}

	public void plusY(double num) {
		fy += num / 20;
	}

	public void minusY(double num) {
		fy -= num/20;
	}
	
	public double getPX() {
		return px;
	}

	public double getPY() {
		return py;
	}

	public double getFX() {
		return fx;
	}

	public double getFY() {
		return fy;
	}

	public void playS() {
		new playSound(sound);
	}
	
	public void playCS() {
		new playSound(cSound);
	}

	public Upgrade getUp() {
		return upgrade;
	}
	
	public boolean isScreen() {
		return (fx > 0 && fx < 1920 && fy > 0 && fy < 1080);
	}
	
	
	public boolean isDie() {
		return die;
	}
	
	public void die() {
		die = true;
	}
}