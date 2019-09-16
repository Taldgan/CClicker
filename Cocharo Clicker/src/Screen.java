import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Screen extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage background;

	private List<InteractiveComponent> buttons = new ArrayList<InteractiveComponent>();

	private List<Item> items = new ArrayList<Item>();
	
	private List<Fade> fades = new ArrayList<Fade>();
	
	private List<FloatComponent> fs = new ArrayList<FloatComponent>();

	private List<Upgrade> upgrades = new ArrayList<Upgrade>();

	private double clicks, cps, cpc;
	
	private DecimalFormat f;
	
	private FormatUtil fo;
	
	private int x, y;

	public Screen(String path) {
		int x = 0;
		int y = 0;
		try {
			background = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		addMouseListener(MouseManager.mm);
		addMouseMotionListener(MouseManager.mm);
		fo = new FormatUtil();
	}

	public void draw(List<InteractiveComponent> but, List<Item> it, List<Fade> fade, double click, double cs, double cp, List<Upgrade> ups, List<FloatComponent> f) {
		clicks = click;
		cps = cs;
		cpc = cp;
		buttons = but;
		items = it;
		fades = fade;
		upgrades = ups;
		fs = f;
	}

	public void setBackground(String path) {
		try {
			background = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render() {
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		f = new DecimalFormat("0");
		
		if (StateManager.state == State.STATE_CALCULATOR) {
			setBackground("images/calcstate.png");
			g.drawImage(background, 0, 0, 1920, 1080, null);
			g.setColor(new Color(53, 53, 53));
			g.fillRect(188, 75, 20, 28);
			g.fillRect(110, 75, 20, 28);
			for (Item it : items) {
				if (it instanceof AttaItem && it.getID().equals("atta")) {
					g.setColor(it.getColor()); // add get/set color blah blah
					g.setFont(it.getFont());
//					if(clicks < 1000) {
						g.drawString(it.getText() + /*f.format(clicks)*/ fo.format(clicks), it.getX(), it.getY());	
//					}
				}
				if (it instanceof AttaItem && it.getID().equals("ibelt")) {
					g.setColor(it.getColor()); // add get/set color blah blah
					g.setFont(it.getFont());
					g.drawString(it.getText() + upgrades.get(0).getCount(), it.getX(), it.getY());
				}
				if (it instanceof AttaItem && it.getID().equals("ieagle")) {
					g.setColor(it.getColor()); // add get/set color blah blah
					g.setFont(it.getFont());
					g.drawString(it.getText() + upgrades.get(1).getCount(), it.getX(), it.getY());
				}
				if (it instanceof AttaItem && it.getID().equals("iepatch")) {
					g.setColor(it.getColor()); // add get/set color blah blah   waterpill
					g.setFont(it.getFont());
					g.drawString(it.getText() + upgrades.get(2).getCount(), it.getX(), it.getY());
				}
				if (it instanceof AttaItem && it.getID().equals("iwpill")) {
					g.setColor(it.getColor()); // add get/set color blah blah   waterpill
					g.setFont(it.getFont());
					g.drawString(it.getText() + upgrades.get(3).getCount(), it.getX(), it.getY());
				}
				if (it instanceof AttaItem && it.getID().equals("ibvelvet")) {
					g.setColor(it.getColor()); // add get/set color blah blah   waterpill
					g.setFont(it.getFont());
					g.drawString(it.getText() + upgrades.get(4).getCount(), it.getX(), it.getY());
				}
				if (it instanceof AttaItem && it.getID().equals("ifwheel")) {
					g.setColor(it.getColor()); // add get/set color blah blah   waterpill
					g.setFont(it.getFont());
					g.drawString(it.getText() + upgrades.get(5).getCount(), it.getX(), it.getY());
				}
				if (it instanceof ImageItem) {
					g.drawImage(it.getImage(), it.getX(), it.getY(), it.getW(), it.getH(), null);
				}
				if (it instanceof CPSItem) {
					g.setColor(it.getColor()); // add get/set color blah blah
					g.setFont(it.getFont());
					g.drawString(it.getText() + f.format(cps), it.getX(), it.getY() + it.getH());
				}
			}

			for (InteractiveComponent comp : buttons) {
				if (comp instanceof InteractiveButton && comp.getState() == State.STATE_CALCULATOR) {
					g.drawImage(comp.getImage(), comp.getX(), comp.getY(), comp.getW(), comp.getH(), null);
					g.setColor(comp.getColor());
					g.setFont(new Font("Comic Sans", Font.BOLD, 70));
					
					/*
					 * create arraylist of "fade" objects that are added when comp is clicked, then removed once alpha < 0 :o
					 */
					
				} else if (comp instanceof InteractiveText) {
					g.setColor(Color.blue);
					g.setFont(comp.getFont());
					g.drawString(comp.getText(), comp.getX(), comp.getY() + comp.getH());
				}
			}
		}
		else if(StateManager.state == State.STATE_MANAGESAVE) {
			setBackground("images/savestate.png");
			g.drawImage(background, 0, 0, 1920, 1080, null);
			g.setColor(new Color(53, 53, 53));
			g.fillRect(188, 75, 20, 28);
			g.fillRect(110, 75, 20, 28);
			for (Item it : items) {
				if (it instanceof AttaItem) {
					g.setColor(it.getColor()); // add get/set color blah blah
					g.setFont(it.getFont());
					g.drawString(it.getText() + fo.format(clicks), it.getX(), it.getY());
				}
			}
			
			for (InteractiveComponent comp : buttons) {
				if (comp instanceof InteractiveButton && comp.getState() == State.STATE_MANAGESAVE) {
					g.drawImage(comp.getImage(), comp.getX(), comp.getY(), comp.getW(), comp.getH(), null);
				} else if (comp instanceof InteractiveText) {
					g.setColor(Color.blue);
					g.setFont(comp.getFont());
					g.drawString(comp.getText(), comp.getX(), comp.getY() + comp.getH());
				}
			}
		}
		else if(StateManager.state == State.STATE_ALL) {
			g.setColor(new Color(53, 53, 53));
			g.fillRect(188, 75, 20, 28);
			g.fillRect(110, 75, 20, 28);
			for (InteractiveComponent comp : buttons) {
				if (comp instanceof InteractiveButton && comp.getState() == State.STATE_ALL) {
					g.drawImage(comp.getImage(), comp.getX(), comp.getY(), comp.getW(), comp.getH(), null);
				} else if (comp instanceof InteractiveText) {
					g.setColor(Color.blue);
					g.setFont(comp.getFont());
					g.drawString(comp.getText(), comp.getX(), comp.getY() + comp.getH());
				}
			}
		}
		
		else if(StateManager.state == State.STATE_STORE) {
			
			setBackground("images/storestate.png");
			g.drawImage(background, 0, 0, 1920, 1080, null);
			g.setColor(new Color(53, 53, 53));
			g.fillRect(188, 75, 20, 28);
			g.fillRect(110, 75, 20, 28);
			for (Item it : items) {
				if (it instanceof AttaItem && it.getID().equals("atta")) {
					g.setColor(it.getColor()); // add get/set color blah blah
					g.setFont(it.getFont());
					g.drawString(it.getText() + fo.format(clicks), it.getX(), it.getY());
				}
				if (it instanceof AttaItem && it.getID().equals("ibelt")) {
					g.setColor(it.getColor()); // add get/set color blah blah
					g.setFont(it.getFont());
					g.drawString(it.getText() + upgrades.get(0).getCount(), it.getX(), it.getY());
				}
				if (it instanceof AttaItem && it.getID().equals("ieagle")) {
					g.setColor(it.getColor()); // add get/set color blah blah
					g.setFont(it.getFont());
					g.drawString(it.getText() + upgrades.get(1).getCount(), it.getX(), it.getY());
				}
				if (it instanceof AttaItem && it.getID().equals("iepatch")) {
					g.setColor(it.getColor()); // add get/set color blah blah   waterpill
					g.setFont(it.getFont());
					g.drawString(it.getText() + upgrades.get(2).getCount(), it.getX(), it.getY());
				}
				if (it instanceof AttaItem && it.getID().equals("iwpill")) {
					g.setColor(it.getColor()); // add get/set color blah blah   waterpill
					g.setFont(it.getFont());
					g.drawString(it.getText() + upgrades.get(3).getCount(), it.getX(), it.getY());
				}
				if (it instanceof AttaItem && it.getID().equals("ibvelvet")) {
					g.setColor(it.getColor()); // add get/set color blah blah   waterpill
					g.setFont(it.getFont());
					g.drawString(it.getText() + upgrades.get(4).getCount(), it.getX(), it.getY());
				}
				if (it instanceof AttaItem && it.getID().equals("ifwheel")) {
					g.setColor(it.getColor()); // add get/set color blah blah   waterpill
					g.setFont(it.getFont());
					g.drawString(it.getText() + upgrades.get(5).getCount(), it.getX(), it.getY());
				}
			}
			
			for (InteractiveComponent comp : buttons) {
				if (comp instanceof InteractiveButton && comp.getState() == State.STATE_STORE) {
					g.drawImage(comp.getImage(), comp.getX(), comp.getY(), comp.getW(), comp.getH(), null);
				} else if (comp instanceof InteractiveText) {
					g.setColor(Color.blue);
					g.setFont(comp.getFont());
					g.drawString(comp.getText(), comp.getX(), comp.getY() + comp.getH());
				}
			}
			for (InteractiveComponent comp : buttons) {
				if (comp instanceof StoreComponent && comp.getState() == State.STATE_STORE) {
					
					if(((StoreComponent) comp).getUp().isBuyable()) {
						g.setColor(new Color(1, 128, 1));
					}
					else {
						g.setColor(Color.GRAY);
					}
					g.fillRect(comp.getX()-50, comp.getY()-10, comp.getW()+100, comp.getH()+40);
					g.setColor(Color.black);
					g.drawRect(comp.getX()-50, comp.getY()-10, comp.getW()+100, comp.getH()+40);
					g.drawImage(comp.getImage(), comp.getX(), comp.getY(), comp.getW(), comp.getH(), null);
					g.setFont(new Font("Comic Sans", Font.BOLD, 30));
					g.setColor(Color.YELLOW);
					g.drawString(comp.getID().substring(0, comp.getID().length()-4), comp.getX(), comp.getY()+comp.getH()+25);
					if(((StoreComponent) comp).getTip() == true) {
						g.setColor(new Color(255, 255, 204));
						String s;
						if(comp.getID().equals("beltitem")) {
							s = ("Amount: " + ((StoreComponent) comp).getUp().getCount() + "\t Worth: " + f.format((((StoreComponent) comp).getUp().getWorth() * 10000)) + "\t Cost: "  + f.format(((StoreComponent) comp).getUp().getCost()));
						}else {
							s = "Amount: " + ((StoreComponent) comp).getUp().getCount() + "\t Worth: " + f.format(((StoreComponent) comp).getUp().getWorth() * 1000) + "\t Cost: "  + f.format(((StoreComponent) comp).getUp().getCost());
						}
						g.fillRect(MouseManager.mm.getMouseX(), MouseManager.mm.getMouseY()-60, s.length()*9, 60);
						g.setColor(Color.black);
						g.drawRect(MouseManager.mm.getMouseX(), MouseManager.mm.getMouseY()-60, s.length()*9, 60);
						g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
						g.drawString(s, MouseManager.mm.getMouseX()+10, MouseManager.mm.getMouseY()-20);
					}
				} 
			}
		}
		
		for(Fade fade : fades) {
			if(fade.getA() > 0) {
				fade.subA();
				fade.plusY();
				fade.plusX();
//				System.out.println(fade.getA());
				g.setColor(new Color(255, 165, 0, (int) fade.getA()));
				g.drawString(fade.getText(), fade.getX(), fade.getY());
			}
			
		}
//		fs.add(new FloatComponent("images/jc.jpg",new Random().nextInt(1920), new Random().nextInt(1080), 200, 200, "sounds/snap.wav"));
		for(FloatComponent f : fs) {
			if(!f.isDie()) {
				g.drawImage(f.getImage(), (int) f.getFX(), (int) f.getFY(), f.getW(), f.getH(), null);
			}
			
		}
		g.dispose();
	}

}
