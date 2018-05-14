import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8154887212424401799L;
	
	private boolean running;
	
	private Thread thread;
	
	private final int FRAME_WIDTH = 2560;
	
	private final int FRAME_HEIGHT = 1440;
	
	private int cCount; //remove
	
	public Game(){
		this.setBackground(Color.magenta);
		this.setOpaque(true);
		cCount = 0;		
		new Window("Cocharo Clicker", FRAME_WIDTH, FRAME_HEIGHT, this); 
		run();
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		g.setFont(new Font("Comic Sans MS", Font.BOLD,  100));
		g.drawString("Attaboys: " +  cCount, FRAME_WIDTH/3, 1050);
	}
	
	public void start(){
		thread = new Thread(this, "CClicker");
		thread.start();
		running = true;
	}
	public void run() {
		
		long lastTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
		while (running) {
			long now = System.nanoTime();
			long updateLength = now - lastTime;
			lastTime = now;
			repaint();
			cCount++;
			try {
				Thread.sleep((lastTime-System.nanoTime() + OPTIMAL_TIME) / 1000000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		stop();
	}
	
	public void stop(){
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getCount(){
		return cCount;
	}
	
	public void setCount(int count){
		cCount = count;
		System.out.println("Count updated!");
	}
	
	public static void main(String[] args) {
		new Game();
	}

}
