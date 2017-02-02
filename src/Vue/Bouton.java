package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * Permet la g�n�ration de bouton transparents et changeant de couleur au survol
 * de la souris
 * 
 * @author Duchene Herrmann Rety
 *
 */

@SuppressWarnings("serial")
public class Bouton extends JButton implements MouseListener {

	/**
	 * Constructeur
	 * 
	 * @param str
	 *            Le nom que le bouton doit avoir
	 */
	public Bouton(String str) {
		super(str);
		this.setFocusPainted(false);
		this.setMargin(null);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setFont(new Font("Courier", Font.BOLD, 25));
		this.setForeground(Color.YELLOW);
		this.addMouseListener(this);
	}

	/**
	 * Non utilis�
	 */
	public void mouseClicked(MouseEvent e) {

	}

	/**
	 * Non utilis�
	 */
	public void mouseEntered(MouseEvent e) {
		this.setForeground(Color.WHITE);

	}

	/**
	 * Non utilis�
	 */
	public void mouseExited(MouseEvent e) {
		this.setForeground(Color.YELLOW);

	}

	/**
	 * Non utilis�
	 */
	public void mousePressed(MouseEvent e) {

	}

	/**
	 * Non utilis�
	 */
	public void mouseReleased(MouseEvent e) {
		this.setForeground(Color.YELLOW);

	}
}