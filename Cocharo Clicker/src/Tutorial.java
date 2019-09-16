import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Tutorial extends JPanel {
	
	private BufferedImage calc, store;
	
	private InteractiveComponent ok;
	
	private MouseManager m;
	
	private boolean exit, active;
	
	public Tutorial() {
		try {
			calc = ImageIO.read(getClass().getResource("images/tutcalc.png"));
			store = ImageIO.read(getClass().getResource("images/tutstore.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		m = new MouseManager();
		exit = false;
		ok = new InteractiveText("CLOSE", new Font("DialogInput", Font.BOLD, 35), 100,  300, 0, 0, "ok", 0, 0, 0, State.STATE_ALL);
		addMouseListener(m);
		addMouseMotionListener(m);
		repaint();
		active = false;
	}
	
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean flag) {
		active = flag;
	}
	
	public void update() {
		if(m.isMouseDown() && InputUtil.isClickedi(m.getMouseX(), m.getMouseY(), ok.getX()-10, ok.getY()-60, 200, 80, true)) {
			exit = true;
		}
	}
	
	public boolean getExit() {
		return exit;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 1000, 700);
		g.setColor(Color.gray);
		g.fillRect(5, 5, 990, 590);
		g.drawImage(calc, 550, 30, 300, 200, null);
		g.drawImage(store, 550, 300, 300, 200, null);
		g.setFont(new Font("DialogInput", Font.BOLD, 35));
		g.setColor(Color.black);
		g.drawRect(ok.getX()-12, ok.getY()-62, 202, 82);
		g.setColor(Color.magenta);
		g.fillRect(ok.getX()-10, ok.getY()-60, 200, 80);
		g.setFont(new Font("DilaogInput", Font.BOLD, 40));
		g.setColor(Color.yellow);
		g.drawString("Where to get Attaboys:", 30, 140);
		g.drawString("Where to buy upgrades:", 30, 400);
		g.drawString(ok.getText(), ok.getX()+15, ok.getY()-5);
		g.dispose();
	}
}
