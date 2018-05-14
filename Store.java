import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Store extends JPanel implements ActionListener {
	
	public Store(){
		this.setBackground(Color.LIGHT_GRAY);
		JLabel store = new JLabel("Store");
		store.setFont(new Font("Comic Sans MS", Font.PLAIN, 45));
		store.setForeground(Color.YELLOW);
		this.add(store);
	}

	public void actionPerformed(ActionEvent arg0) {

	}

}
