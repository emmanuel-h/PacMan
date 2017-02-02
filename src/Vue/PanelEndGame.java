package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import Controller.Controller;

/**
 * Affiche l'écran de fin
 * 
 * @author Duchene Herrmann Rety
 *
 */
@SuppressWarnings("serial")
public class PanelEndGame extends JPanel {

	/**
	 * Nom de l'utilisateur
	 */
	String name;

	/**
	 * Constructeur
	 */
	public PanelEndGame() {
		this.name = "";
	}

	/**
	 * Affiche la page
	 */
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Font font = new Font("Courier", Font.BOLD, 25);
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString("Enter your name:", this.getWidth() / 3 - 20, 235);
		g.drawString("Score : ", this.getWidth() / 2 - 100, 350);
		g.drawString("" + Controller.getScore(), this.getWidth() / 2 + 15, 350);
	}

	/**
	 * Change le nom
	 * 
	 * @param _name
	 *            Le nouveau nom
	 */
	public void setName(String _name) {
		this.name = _name;
	}

}
