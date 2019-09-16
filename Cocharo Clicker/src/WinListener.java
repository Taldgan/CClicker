import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;


public class WinListener implements WindowListener {
	
	private Final kk;

	public WinListener(Final oof) {
		kk = oof;
	}
	public void windowActivated(WindowEvent e) {
		
		
	}

	
	public void windowClosed(WindowEvent e) {
		
	
	}

	
	public void windowClosing(WindowEvent e) {
		JFrame end = new JFrame();
		Point middle = new Point((1920/2)-400, (1080/2)-200);
		end.setLocation(middle);
		end.setSize(800, 400);
		end.add(kk);
		end.setResizable(false);
		end.setVisible(true);
		
		
	}
	
	

	
	public void windowDeactivated(WindowEvent e) {
		
		
	}

	
	public void windowDeiconified(WindowEvent e) {
		
		
	}

	
	public void windowIconified(WindowEvent e) {
		
		
	}

	
	public void windowOpened(WindowEvent e) {
		
		
	}

}
