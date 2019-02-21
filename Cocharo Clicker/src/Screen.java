import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Screen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage background;
	
	private List<InteractiveComponent> buttons = new ArrayList<InteractiveComponent>();
	
	private double clicks;
	
		
	public Screen() {
		
		try {
			background = ImageIO.read(getClass().getResource("images/tisized.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void draw(List<InteractiveComponent> items, double num) {
		clicks = num;
		buttons = items;
	}
	
	public void render() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		DecimalFormat f = new DecimalFormat("0");
		g.drawImage(background, 0, 0, null);
		
		g.setColor(Color.black);
		g.fillRect(1415, 120, 458, 885);
		
//		g.setColor(new Color(0,128, 1));
		g.setColor(Color.white);
		g.fillRect(1420, 131, 450, 874);
		
		
		for(InteractiveComponent comp : buttons) {
			if(comp instanceof CocharoComponent) {
				g.drawImage(comp.getImage(), comp.getX(), comp.getY(), comp.getW(), comp.getH(), null);
					if(comp.getText() != null) {
						g.setFont(comp.getFont());
						g.setColor(Color.blue);
						g.drawString(comp.getText() + f.format(clicks), comp.getTX(), comp.getTY());
					}
			}
			else if(comp instanceof InteractiveButton) {
				g.drawImage(comp.getImage(), comp.getX(), comp.getY(), comp.getiW(),comp.getiH(), null);
			}
			else if (comp instanceof InteractiveText){	
				g.setColor(Color.black);
				g.setFont(comp.getFont());
				g.drawString(comp.getText(), comp.getTX(), comp.getTY());
			}
		}
		g.dispose();
	}
	
}
