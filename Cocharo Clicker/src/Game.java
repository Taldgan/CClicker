import java.awt.Dimension;
import java.awt.Font;
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
	
	private long timer;
	
	private InteractiveComponent jc, sv;
	
	private Font big, small;

	private double clicks, delta;

	private List<InteractiveComponent> buttons;
	
	private ButtonManager bm;
	
	private static final long serialVersionUID = 1L;
	
	public Game(int width, int height) {

		buttons = new ArrayList<InteractiveComponent>();
//--------------FONTS----------------------------------
		big = new Font("DialogInput", Font.BOLD, 100);
		small = new Font("Comic Sans", Font.BOLD, 80);		
//		esmall = new Font("DialogInput", 0, 40);
		
//--------------BUTTONS--------------------
		jc = new CocharoComponent("images/coinresized.png", 100, 112, 200, 200, "attaboys:=", small, 460, 190, "jc"); 
		sv = new InteractiveText("SAVE", big, 70, 980, 320, 100, "sv");
//		coin = new CocharoComponent("images/coincropped.png", 15, 900, 130, 130, "$", esmall, 170, 960, "coin");
		addMouseListener(jc);
		addMouseListener(sv);
//		addMouseListener(coin);
		buttons.add(jc);
		buttons.add(sv);
//		buttons.add(coin);
//-------------BUTTON MANAGER-----------------
		bm = new ButtonManager(buttons);
		
		
		
		clicks = bm.getClicks();
		
		f = new Final();
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		screen = new Screen();
		win = new WinListener(f);
		
		
		
		setSize(new Dimension(width, height));
		setResizable(false);
		setLocationRelativeTo(null);
		this.add(screen);
		addWindowListener(win);
		
		
		setVisible(true);
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
		while(running) {
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
				System.out.println("FPS: " + frames);
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
		screen.draw(buttons, clicks);
		
		bm.test();
		bm.autoClick();
		
		f.update();
		clicks = bm.getClicks();
		
		if (f.save()) {
			bm.save();
			System.exit(0);
		}
	}
	
	
	public void start() {
		running = true;
		thread.start();
		run();
	}
	
	public static void main(String[] args) {
		new Game(1920, 1080);

	}

}
