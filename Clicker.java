
public class Clicker {
	private double clicks, clickFactor;
	
	public Clicker(double num) {
		clicks = num;
		clickFactor = .001;
	}
	
	public void Click() {
		clicks ++;
	}

	public void autoClick() {
		clicks += clickFactor;
	}
	
	public double getClicks() {
		return clicks;
	}
	
	public void setClicks(double num) {
		clicks = num;
	}
}
