import java.io.IOException;
import java.util.List;

public class ButtonManager {
	private List<InteractiveComponent> buttons;
	
	private Clicker clicker;
	
	private Save saveClicks;
	
	private Load loadClicks;
	
	private double eTime;
	
	
	public ButtonManager(List<InteractiveComponent> ok) {
		eTime = 0;
		clicker = new Clicker(0);
		//--------------SAVE/LOAD------------------
			try {
				loadClicks = new Load("CC_lib/data/savestest.txt", "savestest.txt");		
				saveClicks = new Save("CC_lib/data/savestest.txt");		
			} catch (IOException e) {
				e.printStackTrace();
			}
		buttons = ok;
		clicker.setClicks(loadClicks.getClicks());
	}
	
	public void save() {
		saveClicks.saveGame(clicker.getClicks());
	}
	public void test() {
		for(InteractiveComponent b : buttons) {
			if(b.getID().equals("sv") && b.isClicked()) {
				save();
				b.no();
			}
			if(b.getID().equals("jc") && b.isClicked()) {
				clicker.Click();
				b.no();
			}
		}
		if(eTime >= 9000 && eTime <= 1100) {
			System.out.println(eTime);
			clicker.Click();
		}
	}
	
	public void autoClick() {
		clicker.autoClick();
	}
	
	public void setTime(long time) {
		eTime = time;
	}
	
	public double getTime() {
		return eTime;
	}
	
	public double getClicks() {
		return clicker.getClicks();
	}
}
