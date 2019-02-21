import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Final extends JPanel 
{
	private static final long serialVersionUID = -5151856106361032128L;
	private InteractiveComponent yes, no;
//	private JLabel save;
	private Font font;
//	private JPanel space;
	private boolean clicked;
	
	public Final() {
		clicked = false; 
		this.setSize(800, 400);	
		font = new Font("Comic Sans", Font.BOLD, 100);
		yes = new InteractiveComponent("YES  ", font, 50, 100, 250, 150, "");
		no = new InteractiveComponent("NO", font , 470, 100, 250, 150, "");
		addMouseListener(yes);
		addMouseListener(no);	
	}
	
	public boolean save() {
		return clicked;
	}
	
	public void update(){
		if(yes.isClicked()) {
			clicked = true;
		}
		if(no.isClicked()) {
			System.exit(0);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, 800, 400);
		g.setColor(Color.pink);
		g.setFont(font);
		g.fillRect(yes.getTX(), yes.getTY(), yes.getW(), yes.getH());
		g.fillRect(no.getTX(), no.getTY(), no.getW(), no.getH());
		g.setColor(Color.black);
		g.drawString("Save?", 250, 85);
		g.drawString(yes.getText(), yes.getTX()+20, yes.getTY()+110);
		g.drawString(no.getText(), no.getTX()+50, no.getTY()+110);
	}
}
