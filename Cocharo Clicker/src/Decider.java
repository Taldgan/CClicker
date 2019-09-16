import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Decider {

	private List<Boolean> choice;
	
	private List<Upgrade> upgrades;
	
	private List<FloatComponent> floats;
	private Random rand;
	
	public Decider(List<Upgrade> ups) {
		rand = new Random();
		upgrades = new ArrayList<Upgrade>();
		upgrades = ups;
		choice = new ArrayList<Boolean>();
		floats = new ArrayList<FloatComponent>();
		for(int i = 0; i < upgrades.size(); i++) {
			choice.add(false);
		}
	}

	public void updateUps(List<Upgrade> ups) {
		upgrades.clear();
		upgrades = ups;
	}
	
	public boolean check() {
		return (rand.nextInt(200000) <= 2);
	}
	
	public void update() {
		int i = 0;
		for(Upgrade up : upgrades) {
			if(check() && up.getCount() >= 1) {
				System.out.println(up.getName());
				floats.add(new FloatComponent("images/" + up.getName() + "item.png",rand.nextInt(1920), rand.nextInt(1080), 200, 200, new Random().nextInt(5 + 1 + 5)-5, new Random().nextInt(5 + 1 + 5)-5,  "sounds/snap.wav", "sounds/snap.wav", up));
				floats.get(i).playS();
				System.out.println("floatadded");
			}
		}
	}
	
	public List<FloatComponent> getFloats(){
		return floats;
	}
}
