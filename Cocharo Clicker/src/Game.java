import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Game extends JFrame implements Runnable {

	private Thread thread;

	private boolean running;

	private Screen screen;

	private WinListener win;

	private Final f;
	
	private Tutorial tut;

	private long timer;
	
	private JFrame temp;

	private InteractiveComponent jc, sv, menu, calcb, saveb, storeb, belt, eagle, epatch, wpill, bvelvet, fwheel;

	private Item atta, rmenu, cps, ibelt, ieagle, iepatch, iwpill, ibvelvet, ifwheel;

	private Upgrade ubelt, ueagle, uepatch, uwpill, ubvelvet, ufwheel;

	private Font big, small, esmall, eesmall;

	private double clicks, delta, cs, cp;

	private List<InteractiveComponent> buttons;

	private List<Item> items;

	private List<Upgrade> upgrades;

	private List<Fade> fades;
	
	private List<FloatComponent> floats;
	
	private ICManager ic;
	
	private Decider decider;

	private static final long serialVersionUID = 1L;

	public Game(int width, int height) {
		StateManager.state = State.STATE_CALCULATOR;
		

//--------------ARRAYLISTS----------------------------
		buttons = new ArrayList<InteractiveComponent>();
		items = new ArrayList<Item>();
		upgrades = new ArrayList<Upgrade>();
		fades = new ArrayList<Fade>();
		floats = new ArrayList<FloatComponent>();

//--------------FONTS---------------------------------
		big = new Font("DialogInput", Font.BOLD, 100);
		small = new Font("Comic Sans", Font.BOLD, 80);
		eesmall = new Font("Comic Sans", Font.BOLD,60);
		esmall = new Font("DialogInput", 0, 30);
//-------------UPGRADES-----------------------------
		ubelt = new Upgrade("belt", 0.0001);
		ueagle = new Upgrade("eagle", 0.001);
		uepatch = new Upgrade("epatch", 0.01);
		uwpill = new Upgrade("waterpill", 0.1);
		ubvelvet = new Upgrade("bluevelvet", 10);
		ufwheel = new Upgrade("ferriswheel", 1000);
		upgrades.add(ubelt);
		upgrades.add(ueagle);
		upgrades.add(uepatch);
		upgrades.add(uwpill);
		upgrades.add(ubvelvet);
		upgrades.add(ufwheel);
		
//-----------DECIDER-----------------------------------
		decider = new Decider(upgrades);
		
//--------------BUTTONS-------------------------------
		jc = new InteractiveButton("images/coinresized.png", 60, 112, 200, 200, "jc", 0, 0, 0, State.STATE_CALCULATOR);
		sv = new InteractiveText("SAVE", big, 32, 900, 240, 80, "sv", 0, 0, 0, State.STATE_CALCULATOR);
		menu = new InteractiveButton("images/menu.png", 249, 450, 48, 34, "menu", 0, 0, 0, State.STATE_CALCULATOR);
		calcb = new InteractiveButton("images/hcalc.png", 135, 74, 48, 32, "calcstatebutton", 0, 0, 0,
				State.STATE_CALCULATOR);
		saveb = new InteractiveButton("images/hsave.png", 215, 74, 48, 33, "savebutton", 0, 0, 0,
				State.STATE_MANAGESAVE);
		storeb = new InteractiveButton("images/hstore.png", 55, 74, 48, 32, "storebutton", 0, 0, 0, State.STATE_STORE);
		belt = new StoreComponent("images/beltitem.png", 75, 120, 160, 80, "beltitem", 0, 0, 0, State.STATE_STORE,
				upgrades.get(0));
		eagle = new StoreComponent("images/eagleitem.png", 75, 245, 160, 80, "eagleitem", 0, 0, 0, State.STATE_STORE, upgrades.get(1));
		epatch = new StoreComponent("images/epatchitem.png", 75, 370, 160, 80, "eyepatchitem", 0, 0, 0, State.STATE_STORE, upgrades.get(2));
		wpill = new StoreComponent("images/waterpillitem.png", 75, 495, 160, 80, "waterpillitem", 0, 0, 0, State.STATE_STORE, upgrades.get(3));
		bvelvet = new StoreComponent("images/bluevelvetitem.png", 75, 620, 160, 80, "bluevelvetitem", 0, 0, 0, State.STATE_STORE, upgrades.get(4));
		fwheel = new StoreComponent("images/ferriswheelitem.png", 75, 745, 160, 80, "ferriswheelitem", 0, 0, 0, State.STATE_STORE, upgrades.get(5));
		buttons.add(jc);
		buttons.add(sv);
		buttons.add(menu);
		buttons.add(calcb);
		buttons.add(saveb);
		buttons.add(storeb);
		buttons.add(belt);
		buttons.add(eagle);
		buttons.add(epatch);
		buttons.add(wpill);
		buttons.add(bvelvet);
		buttons.add(fwheel);
//-------------ITEMS---------------------------------
		atta = new AttaItem("attaboys:=", 510, 190, small, new Color(9, 9, 254), "atta");
		ibelt = new AttaItem("belts:=", 510, 280, eesmall, new Color(1, 128, 1), "ibelt");
		ieagle = new AttaItem("eagles:=", 510, 370, eesmall, new Color(1, 128, 1), "ieagle");
		iepatch = new AttaItem("eyepatches:=", 510, 460, eesmall, new Color(1, 128, 1), "iepatch");
		iwpill = new AttaItem("waterpills:=", 510, 550, eesmall, new Color(1, 128, 1), "iwpill");
		ibvelvet = new AttaItem("bluevelvets:=", 510, 640, eesmall, new Color(1, 128, 1), "ibvelvet");
		ifwheel = new AttaItem("ferriswheels:=", 510, 730, eesmall, new Color(1, 128, 1), "ifwheel");
		
		cps = new CPSItem("AB/s: ", 60, 340, esmall, Color.black, "cps");

		items.add(atta);
		items.add(ibelt);
		items.add(ieagle);
		items.add(iepatch);
		items.add(iwpill);
		items.add(ibvelvet);
		items.add(ifwheel);
		items.add(rmenu);
		items.add(cps);

//-------------MANAGERS-----------------------------
		ic = new ICManager(buttons, upgrades, floats);
		
		f = new Final();

		clicks = ic.getClicks();
		
		cs = ic.getCPS();
		cp = ic.getCPC();



		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		screen = new Screen("images/calcstate.png");
		win = new WinListener(f);
		setSize(new Dimension(width, height));
		setResizable(false);
		setLocationRelativeTo(null);
		this.add(screen);
		addWindowListener(win);
		setVisible(true);
		temp = new JFrame();
		tut = new Tutorial();
		if(clicks == 0) {
			temp.setSize(1000, 600);
			Point middle = new Point((1920/2)+200, (1080/2)+200);
			temp.setResizable(false);
			temp.setLocation(middle);
			tut.setActive(true);
			temp.add(tut);
			temp.setUndecorated(true);
			temp.setVisible(true);
		}
		running = false;
		thread = new Thread();
		start();
	
	}

	public void run() {
		long lastTime = System.nanoTime();
		timer = System.currentTimeMillis();
		double bestTime = (Math.pow(10, 9) / 1000);
		delta = 0;
		int frames = 0;
		while (running) {
			long thisTime = System.nanoTime();
			long elapsedTime = thisTime - lastTime;
			lastTime = thisTime;
			delta += elapsedTime / bestTime;

			while (delta >= 1) {
				update();
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer >= 1000) {
//				System.out.println("FPS: " + frames);
				timer = System.currentTimeMillis();
//				n = 0;
				frames = 0;
			}
		}
	}

	public void render() {
		screen.render();
	}

	public void update() {
		ic.update();
		clicks = ic.getClicks();
		cs = ic.getCPS();
		cp = ic.getCPC();
		fades = ic.getFades();
		decider.update();
		floats = decider.getFloats();
		for(FloatComponent f : floats) {
			f.plusX(f.getPX());
			f.plusY(f.getPY());
		}
		ic.setFloats(floats);
		screen.draw(buttons, items, fades, clicks, cs, cp, upgrades, floats);
		menu.update();
		if(tut.isActive()) {
//			tut.setActive(true);
			tut.update();
			if(tut.getExit()) {
				temp.dispose();
			}
		}
		
		f.update();
		if (f.save()) {
			ic.save();
			for (Upgrade up : upgrades) {
				up.save();
			}
			System.exit(0);
		}
	}

	public void start() {
		running = true;
		thread.start();
		run();
	}

	public static void main(String[] args) {
		new Game(1926, 1115);

	}

}
