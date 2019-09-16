
public class Fade {

	private String text;

	private int x, y;

	private double alpha, sub;

	public Fade(String t, int posx, int posy) {

		text = t;

		x = posx;

		y = posy;

		alpha = 255;

		sub = 14.92156863;

	}
	
	public void plusY() {
		y -= 1.5;
	}
	
	public void plusX() {
		x += 1.2;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void subA() {
		if(alpha > 0 && alpha - sub >= 0) {
			alpha -= sub;
		}
	}
	
	public double getA() {
		return alpha;
	}

	public String getText() {
		return text;
	}

}
