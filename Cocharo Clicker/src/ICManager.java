import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ICManager {
	private List<InteractiveComponent> buttons = new ArrayList<InteractiveComponent>();

	private Clicker clicker;

	private List<Fade> fades;

	private List<FloatComponent> fs;

	private DecimalFormat f;

	private List<Upgrade> upgrades;

	public ICManager(List<InteractiveComponent> but, List<Upgrade> up, List<FloatComponent> fs) {
		buttons = but;
		upgrades = up;
		clicker = new Clicker();
		fades = new ArrayList<Fade>();
		this.fs = fs;
		f = new DecimalFormat("0");
	}

	public void save() {
		clicker.save();
	}

	public void autoClick() {
		clicker.autoClick();
	}

	public double getClicks() {
		return clicker.getClicks();
	}

	public double getCPS() {
		return clicker.getCPS();
	}

	public void setCPS() {
		clicker.setCPS(upgrades);
	}

	public double getCPC() {
		return clicker.getCPC();
	}

	public List<Fade> getFades() {
		return fades;
	}

	public List<FloatComponent> getFloats() {
		return fs;
	}

	public void setFloats(List<FloatComponent> f) {
		fs = f;
	}

	public void update() {
		setCPS();
		autoClick();
		for (InteractiveComponent comp : buttons) {

			boolean hc = InputUtil.isHoveri(MouseManager.mm.getMouseX(), MouseManager.mm.getMouseY(), comp.getCX(),
					comp.getCY(), comp.getCW(), comp.getCH(), StateManager.state == State.STATE_CALCULATOR);
			boolean hs = InputUtil.isHoveri(MouseManager.mm.getMouseX(), MouseManager.mm.getMouseY(), comp.getCX(),
					comp.getCY(), comp.getCW(), comp.getCH(), StateManager.state == State.STATE_STORE);
			boolean cc = InputUtil.isHoveri(MouseManager.mm.getMouseX(), MouseManager.mm.getMouseY(), comp.getCX(),
					comp.getCY(), comp.getCW(), comp.getCH(), StateManager.state == State.STATE_CALCULATOR)
					&& MouseManager.mm.isMouseDown();
			boolean cs = InputUtil.isHoveri(MouseManager.mm.getMouseX(), MouseManager.mm.getMouseY(), comp.getCX(),
					comp.getCY(), comp.getCW(), comp.getCH(), StateManager.state == State.STATE_STORE)
					&& MouseManager.mm.isMouseDown();

			boolean ac = InputUtil.isHoveri(MouseManager.mm.getMouseX(), MouseManager.mm.getMouseY(), comp.getCX(),
					comp.getCY(), comp.getCW(), comp.getCH(), true) && MouseManager.mm.isMouseDown();

			boolean fc;

			if (comp.getID().equals("menu") && hc && !comp.isHover()) {
				comp.setImage("images/hmenu.png");
				comp.hover();
			}
//			if (comp.getID().equals("menu") && !hc && comp.isHover()) {
//				comp.setImage("images/menu.png");
//				comp.unhover();
//			}
//			if (comp.getID().equals("menu") && cc && !comp.isClicked()) {
//				comp.click();
////				store.vis();
//			}
//			if (comp.getID().equals("menu") && !cc && comp.isClicked()) {
//				comp.unclick();
//			}

			if (comp instanceof InteractiveButton) {
//				comp.setColor(255, 165, 0, (int) comp.getA());
//				comp.subA();
//				System.out.println(comp.getA());
				if (cc && comp.getID().equals("jc") && !comp.isClicked()) {
					comp.click();
					fades.add(new Fade("+" + f.format(clicker.getCPC()),
							MouseManager.mm.getMouseX() + new Random().nextInt(50 + 1 + 50) - 100,
							MouseManager.mm.getMouseY() + new Random().nextInt(80 + 1 + 50) - 50));
					new playSound("sounds/snap.wav");
					comp.scale();
				}
				if (!MouseManager.mm.isMouseDown() && comp.getID().equals("jc")) {
					if (comp.isClicked()) {
						clicker.click();
					}
					comp.unclick();
					comp.unscale();
				}
				if (ac && !comp.isClicked() && MouseManager.mm.isMouseDown() && comp.getID().equals("storebutton")) {
					comp.scale();
					StateManager.state = State.STATE_STORE;
					comp.click();
				}
				if (!MouseManager.mm.isMouseDown()) {
					comp.unscale();
					comp.unclick();
				}
				if (ac && !comp.isClicked() && comp.getID().equals("calcstatebutton")) {
					comp.scale();
					StateManager.state = State.STATE_CALCULATOR;
					comp.click();
				}
				if (!MouseManager.mm.isMouseDown()) {
					comp.unscale();
					comp.unclick();
				}
				if (ac && !comp.isClicked() && comp.getID().equals("savebutton")) {
					comp.scale();
					StateManager.state = State.STATE_MANAGESAVE;
//					FloatComponent test = new FloatComponent("images/jc.jpg", 400, 400, 200, 200, "sounds/snap.wav");
//					test.playS();
					comp.click();
				}
				if (!MouseManager.mm.isMouseDown()) {
					comp.unscale();
					comp.unclick();
				}
			}

			if (comp instanceof StoreComponent) {
				((StoreComponent) comp).getUp().checkBuy(getClicks());

				// BELT
//				System.out.println(((StoreComponent) comp).getUp().isBuyable());
				if (cs && comp.getID().equals("beltitem") && !comp.isClicked()
						&& ((StoreComponent) comp).getUp().isBuyable()) {
					((StoreComponent) comp).getUp().upCount();
					clicker.subClicks(((StoreComponent) comp).getUp().getCost());
					((StoreComponent) comp).getUp().upCost();
//					((StoreComponent) comp).getUp().upWorth();

					comp.click();
					comp.scale();
				}
				if (hs && comp.getID().equals("beltitem")) {
					((StoreComponent) comp).setTip(true);
				}
				if (!hs && comp.getID().equals("beltitem")) {
					((StoreComponent) comp).setTip(false);
				}
				if (!MouseManager.mm.isMouseDown() && comp.getID().equals("beltitem")) {
					comp.unclick();
					comp.unscale();
				}
				// EAGLE
				if (cs && comp.getID().equals("eagleitem") && !comp.isClicked()
						&& ((StoreComponent) comp).getUp().isBuyable()) {
					((StoreComponent) comp).getUp().upCount();
					clicker.subClicks(((StoreComponent) comp).getUp().getCost());
					((StoreComponent) comp).getUp().upCost();
//					((StoreComponent) comp).getUp().upWorth();

					comp.click();
					comp.scale();
				}
				if (hs && comp.getID().equals("eagleitem")) {
					((StoreComponent) comp).setTip(true);
				}
				if (!hs && comp.getID().equals("eagleitem")) {
					((StoreComponent) comp).setTip(false);
				}
				if (!MouseManager.mm.isMouseDown() && comp.getID().equals("eagleitem")) {
					comp.unclick();
					comp.unscale();
				}
				// EYEPATCH
				if (cs && comp.getID().equals("eyepatchitem") && !comp.isClicked()
						&& ((StoreComponent) comp).getUp().isBuyable()) {
					((StoreComponent) comp).getUp().upCount();
					clicker.subClicks(((StoreComponent) comp).getUp().getCost());
					((StoreComponent) comp).getUp().upCost();
//					((StoreComponent) comp).getUp().upWorth();

					comp.click();
					comp.scale();
				}
				if (hs && comp.getID().equals("eyepatchitem")) {
					((StoreComponent) comp).setTip(true);
				}
				if (!hs && comp.getID().equals("eyepatchitem")) {
					((StoreComponent) comp).setTip(false);
				}
				if (!MouseManager.mm.isMouseDown() && comp.getID().equals("eyepatchitem")) {
					comp.unclick();
					comp.unscale();
				}

				// WATERPILL

				if (cs && comp.getID().equals("waterpillitem") && !comp.isClicked()
						&& ((StoreComponent) comp).getUp().isBuyable()) {
					((StoreComponent) comp).getUp().upCount();
					clicker.subClicks(((StoreComponent) comp).getUp().getCost());
					((StoreComponent) comp).getUp().upCost();
//					((StoreComponent) comp).getUp().upWorth();

					comp.click();
					comp.scale();
				}
				if (hs && comp.getID().equals("waterpillitem")) {
					((StoreComponent) comp).setTip(true);
				}
				if (!hs && comp.getID().equals("waterpillitem")) {
					((StoreComponent) comp).setTip(false);
				}
				if (!MouseManager.mm.isMouseDown() && comp.getID().equals("waterpillitem")) {
					comp.unclick();
					comp.unscale();
				}
				// BLUEVELVET
				if (cs && comp.getID().equals("bluevelvetitem") && !comp.isClicked()
						&& ((StoreComponent) comp).getUp().isBuyable()) {
					((StoreComponent) comp).getUp().upCount();
					clicker.subClicks(((StoreComponent) comp).getUp().getCost());
					((StoreComponent) comp).getUp().upCost();
//					((StoreComponent) comp).getUp().upWorth();

					comp.click();
					comp.scale();
				}
				if (hs && comp.getID().equals("bluevelvetitem")) {
					((StoreComponent) comp).setTip(true);
				}
				if (!hs && comp.getID().equals("bluevelvetitem")) {
					((StoreComponent) comp).setTip(false);
				}
				if (!MouseManager.mm.isMouseDown() && comp.getID().equals("bluevelvetitem")) {
					comp.unclick();
					comp.unscale();
				}
				// FERRISWHEEL
				if (cs && comp.getID().equals("ferriswheelitem") && !comp.isClicked()
						&& ((StoreComponent) comp).getUp().isBuyable()) {
					((StoreComponent) comp).getUp().upCount();
					clicker.subClicks(((StoreComponent) comp).getUp().getCost());
					((StoreComponent) comp).getUp().upCost();
//					((StoreComponent) comp).getUp().upWorth();

					comp.click();
					comp.scale();
				}
				if (hs && comp.getID().equals("ferriswheelitem")) {
					((StoreComponent) comp).setTip(true);
				}
				if (!hs && comp.getID().equals("ferriswheelitem")) {
					((StoreComponent) comp).setTip(false);
				}
				if (!MouseManager.mm.isMouseDown() && comp.getID().equals("ferriswheelitem")) {
					comp.unclick();
					comp.unscale();
				}
			}

			if (comp instanceof InteractiveText) {
				if (MouseManager.mm.isClicked() && ac && comp.getID().equals("sv") && !comp.isClicked()) {
					clicker.save();
					for (Upgrade up : upgrades) {
						up.save();
					}
					System.out.println("Save!");
					comp.click();
				}
				if (!MouseManager.mm.isMouseDown() && comp.getID().equals("sv")) {
					comp.unclick();
				}
			}

		}
		for (FloatComponent comp : fs) {
			boolean fc;
			if (comp instanceof FloatComponent) {
				fc = InputUtil.isHoveri(MouseManager.mm.getMouseX(), MouseManager.mm.getMouseY(),
						(int) ((FloatComponent) comp).getFX(), (int) ((FloatComponent) comp).getFY(), comp.getCW(),
						comp.getCH(), true) && MouseManager.mm.isMouseDown();
				if (comp.isScreen()) {
					if (fc && comp.getID().equals("beltf") && !comp.isClicked()) {
						comp.click();
//						System.out.println("beltclick");
						comp.getUp().upWorth();
						comp.playCS();
						comp.die();

					}
					if (!MouseManager.mm.isMouseDown() && comp.getID().equals("beltf")) {
						comp.unclick();
					}
					if (fc && comp.getID().equals("eaglef") && !comp.isClicked()) {
						comp.click();
//						System.out.println("beltclick");
						comp.getUp().upWorth();
						comp.playCS();
						comp.die();

					}
					if (!MouseManager.mm.isMouseDown() && comp.getID().equals("eaglef")) {
						comp.unclick();
					}
					if (fc && comp.getID().equals("waterpillf") && !comp.isClicked()) {
						comp.click();
//						System.out.println("eagle");
						comp.getUp().upWorth();
						comp.playCS();
						comp.die();

					}
					if (!MouseManager.mm.isMouseDown() && comp.getID().equals("waterpillf")) {
						comp.unclick();
					}
					
					if (fc && comp.getID().equals("ferriswheelf") && !comp.isClicked()) {
						comp.click();
//						System.out.println("eagle");
						comp.getUp().upWorth();
						comp.playCS();
						comp.die();

					}
					if (!MouseManager.mm.isMouseDown() && comp.getID().equals("ferriswheelf")) {
						comp.unclick();
					}
					
					if (fc && comp.getID().equals("bluevelvetf") && !comp.isClicked()) {
						comp.click();
//						System.out.println("eagle");
						comp.getUp().upWorth();
						comp.playCS();
						comp.die();

					}
					if (!MouseManager.mm.isMouseDown() && comp.getID().equals("bluevelvetf")) {
						comp.unclick();
					}
					
					if (fc && comp.getID().equals("epatchf") && !comp.isClicked()) {
						comp.click();
//						System.out.println("eagle");
						comp.getUp().upWorth();
						comp.playCS();
						comp.die();

					}
					if (!MouseManager.mm.isMouseDown() && comp.getID().equals("epatchf")) {
						comp.unclick();
					}
					
				} else {
					comp.die();
				}

			}
		}
	}
}
