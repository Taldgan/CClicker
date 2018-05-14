import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CocharoPane extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1114956457234978017L;
	
	private ImageIcon face;
	
	private int counter;
	
	private Game game;
	
	public CocharoPane(Game game){
		
		counter = game.getCount();
		this.game = game;
		
		face = new ImageIcon("images/jcfit.PNG");
		this.setBackground(Color.red);
		this.setOpaque(true);
		
		
		JLabel cb = new JLabel(face);
		cb.addMouseListener(this);
		cb.setOpaque(true);
		this.setLayout(new GridBagLayout());
		JPanel empty = new JPanel();
		
		
		//FACE CONSTRAINTS (Top row)
		GridBagConstraints c = new GridBagConstraints();		
		c.anchor = GridBagConstraints.WEST;
//		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		this.add(cb, c);	
		//FACE CONSTRAINTS (Top row)
		
		//EMPTY CONSTRAINTS (Bottom row)
		c.gridy = 1;
		this.add(empty, c);
		//EMPTY CONSTRAINTS (Bottom row)
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Click! (CB)");
		counter++;
		game.setCount(counter);
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
