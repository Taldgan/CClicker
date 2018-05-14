import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Background extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7367744853050053731L;
	
	private Image back;
	
	private int width, height;

	public Background(String path, int width, int height){
		try {
			back = ImageIO.read(this.getClass().getResourceAsStream(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.width = width;
		this.height = height;
		
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(back, 0, 0, width, height, null);
	}
	
}
