import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame implements WindowListener {
	
	private int WIDTH, HEIGHT;
	
	private Dimension win; 
	
	public Window(String title, int width, int height, Game game){
		
		//Frame requirements
		win = new Dimension(width, height);
		WIDTH = width;
		HEIGHT = height;
		this.setTitle(title);
		this.setSize(win);
		this.setResizable(true);
		this.setPreferredSize(win);
		this.addWindowListener(this);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		Background back = new Background("tinspire.PNG", width, height);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		Store store = new Store();
		JPanel pane = new JPanel();
		pane.setForeground(Color.magenta);
		c.fill = GridBagConstraints.VERTICAL;
		back.add(pane, c);

		c.fill = GridBagConstraints.BOTH;
		this.add(back, c);
		c.weightx = .25;
		c.gridx = 1;
		
		this.add(store, c);
		this.setVisible(true);
		game.start();
	}

	
	public void windowActivated(WindowEvent arg0) {
		
		
	}

	
	public void windowClosed(WindowEvent arg0) {
		
		
	}

	
	public void windowClosing(WindowEvent arg0) {
		System.out.println("Closing!");
		System.exit(0);
		
	}

	
	public void windowDeactivated(WindowEvent arg0) {
		
		
	}

	
	public void windowDeiconified(WindowEvent arg0) {
		
		
	}

	
	public void windowIconified(WindowEvent arg0) {
		
		
	}

	
	public void windowOpened(WindowEvent arg0) {
		
		
	}
	
}
