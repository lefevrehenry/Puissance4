package graphic;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.Controler;
import application.GameObserver;

public class GameBoard extends JPanel implements GameObserver, MouseListener, MouseMotionListener {
	
	private static final long serialVersionUID = 1L;
	private JLabel spriteDamier;
	private JLabel spriteSelect;
	
	private Controler controler;
	private Jeton[][] arrayJetons;
	
	/** Constructeur */
	public GameBoard(Controler controler) {
		super();
		this.controler = controler;
		this.arrayJetons = new Jeton[6][];
		for (int i = 0; i < 6; i++) {
			arrayJetons[i] = new Jeton[7];
			for (int j = 0; j < 7; j++) {
				Jeton jeton = new Jeton(i, j);
				arrayJetons[i][j] = jeton;
				add(jeton);
			}
		}
		
		setLayout(null);
		setPreferredSize(new Dimension(504, 432));
		//setBackground(Color.black);
		
		// Image du puissance4
		spriteDamier = new JLabel();
		spriteDamier.setIcon(new ImageIcon("ressources/Damier.png"));
		spriteDamier.setBounds(0, 0, 504, 432);
		add(spriteDamier);
		
		// Image de la surbrillance
		spriteSelect = new JLabel();
		spriteSelect.setIcon(new ImageIcon("ressources/Surbrillance.png"));
		spriteSelect.setBounds(0, 0, 102, 432);
		spriteSelect.setVisible(false);
		add(spriteSelect);
		
		// Definit les priorites de superposition des images
		setComponentZOrder(spriteDamier, 1);
		setComponentZOrder(spriteSelect, 0);
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	@Override
	public void updatePlateau(int line, int column, int color) {
		arrayJetons[line][column].setIconJeton(color);
	}
	
	@Override
	public void clearPlateau() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				arrayJetons[i][j].setIconJeton(0);
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent ev) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent ev) {
		spriteSelect.setVisible(true);
	}
	
	@Override
	public void mouseExited(MouseEvent ev) {
		spriteSelect.setVisible(false);
	}
	
	@Override
	public void mousePressed(MouseEvent ev) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent ev) {
		int column = ev.getX() / 72;
		controler.columnClicked(column);
	}
	
	@Override
	public void mouseDragged(MouseEvent ev) {
		
	}
	
	@Override
	public void mouseMoved(MouseEvent ev) {
		int column = ev.getX() / 72;
		spriteSelect.setLocation((column * 72) - 15, 0);
	}
}
