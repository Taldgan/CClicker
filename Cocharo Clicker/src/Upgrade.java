import java.util.ArrayList;

public class Upgrade {
	
	private int upCount, add;
	
	private double worth, cost;
	
	private boolean buyable;
	
	private String name;
	
	private Save save;
	
	private ArrayList<Double> data = new ArrayList<Double>();
	
	private Load load;

	public Upgrade(String name, double wort) {
		this.name = name;
		add = (int) (wort*53120);
		cost = 1;
		buyable = false;
		try {
			load = new Load("CC_lib/data/" + name + ".txt", name + ".txt");
			save = new Save("CC_lib/data/" + name + ".txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(load.getData().size() > 0) {
			upCount = (int) Math.round(load.getData().get(0));
		}
		else{
			upCount = 0;
		}
		
		if(load.getData().size() > 1) {
			worth = load.getData().get(1);
		}
		else{
			worth = wort;
		}
		cost = worth * 100000;
	}
	
	
	public double getCost() {
//		System.out.println("Worth: " + worth + " Cost: " + cost + " #: " + upCount);
		return cost;
	}
	
	public String getName() {
		return name;
	}
	
	public void upCost() {
		if(upCount % 3 == 0 && upCount > 1) {
//			System.out.println("|1| Add: " + add + "\nCost: " + cost + " " + worth + " " + upCount);
			add = (int) (upCount*worth*53120);
			cost = (upCount*add) + (worth * 100000);
		}
		else if(upCount >= 1) {
//			System.out.println("|2|  Add: " + add + "\nCost: " + cost);
			cost = (upCount*add) + (worth * 100000);
		}
		else {
//			System.out.println("|3| Add: " + add + "\nCost: " + cost);
			cost = (upCount*add) + worth * 100000;
		}
	}
	
	public void upWorth() {
		worth *= 2;
	}
	
	public boolean isBuyable() {
		return buyable;
	}
	
	public void checkBuy(double d) {
		if(d >= getCost()) {
			buyable = true;
		}
		else {
			buyable = false;
		}
	}
	
	public void save() {
		data.add((double) upCount);
		data.add(worth);
		save.saveGame(data);
		data.clear();
	}
	
	public int getCount() {
		return upCount;
	}
	
	public void upCount() {
		upCount++;
	}
	
	public double getWorth() {
		return worth;
	}
	
	
}
