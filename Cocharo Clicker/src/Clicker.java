import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Clicker {
	private double clicks, clickFactor, cMult;
	private boolean clicked;
	private Save sclicks;
	
	private ArrayList<Double> data = new ArrayList<Double>();

//	private List<Upgrade> upgrades;
	

	private Load lclicks;

	public Clicker(/*List<Upgrade> up*/) {
//		upgrades = up;
		lclicks = new Load("CC_lib/data/savestest.txt", "savestest");
		try {
			sclicks = new Save("CC_lib/data/savestest.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		clicked = false;
		clicks = 0;
		cMult = 1;
		clickFactor = 0;
		if(lclicks.getData().size() > 0) {
			setClicks(lclicks.getData().get(0));
		}
	}

	public void click() {
//		System.out.println(cMult);
		clicks += cMult;
	}

	public void unclick() {
		clicked = false;
	}

	public void autoClick() {
		clicks += clickFactor;
	}

	public double getClicks() {
		return clicks;
	}
	
	public double getCPC() {
		return cMult;
	}
	
	public void subClicks(double num) {
		clicks -= num;
	}
	
	public void setCPS(List<Upgrade> up) {
		int index = 0;
		clickFactor = 0;
		for(Upgrade upgrade : up) {
			if(index == 0 && getCPC() == 1 && !(upgrade.getCount() > 0)) {
				cMult = 1;
			}
			else if(index == 0) {
				cMult = (upgrade.getCount()*upgrade.getWorth()*10000 + 1);
			}
			else {
				clickFactor += (upgrade.getCount()*upgrade.getWorth());
			}
			index++;
		}
	}

	public void setClicks(double num) {
		clicks = num;
	}
	
	public void save() {
		data.add(clicks);
		sclicks.saveGame(data);
		data.clear();
		
	}

	public double getCPS() {
//		System.out.println(clickFactor * 1000);
		return clickFactor * 1000;

	}
}
