package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Affiche la page de l'�cran d'accueil
 * 
 * @author Duchene Herrmann Rety
 *
 */
@SuppressWarnings("serial")
public class PanelStart extends JPanel {

	/**
	 * Image de blinky affich�e
	 */
	private Image blinky_down;

	/**
	 * Image de clyde affich�e
	 */
	private Image clyde_down;

	/**
	 * Image de inky affich�e
	 */
	private Image inky_down;

	/**
	 * Image de pinky affich�e
	 */
	private Image pinky_down;

	/**
	 * Image de fond
	 */
	private Image startpage;

	/**
	 * Coordon�e x de blinky
	 */
	private int blinkyX;

	/**
	 * Coordonn�e y de blinky
	 */
	private int blinkyY;

	/**
	 * Coordonn�e x d'inky
	 */
	private int inkyX;

	/**
	 * Coordonn�e y d'inky
	 */
	private int inkyY;

	/**
	 * Coordonn�e x de pinky
	 */
	private int pinkyX;

	/**
	 * Coordonn�e y de pinky
	 */
	private int pinkyY;

	/**
	 * Coordonn�e x de clyde
	 */
	private int clydeX;

	/**
	 * Coordonn�e y de clyde
	 */
	private int clydeY;

	/**
	 * Direction x d'inky
	 */
	private int inkyDirX;

	/**
	 * Direction y d'inky
	 */
	private int inkyDirY;

	/**
	 * Direction x de pinky
	 */
	private int pinkyDirX;

	/**
	 * Direction y de pinky
	 */
	private int pinkyDirY;

	/**
	 * Direction x de blinky
	 */
	private int blinkyDirX;

	/**
	 * Direction y de blinky
	 */
	private int blinkyDirY;

	/**
	 * Direction x de clyde
	 */
	private int clydeDirX;

	/**
	 * Direction y de clyde
	 */
	private int clydeDirY;

	/**
	 * Constructeur
	 */
	public PanelStart() {
		try {
			startpage = ImageIO.read(Vue.class.getResource("image/Startpage.png"));
			blinky_down = ImageIO.read(Vue.class.getResource("image/Blinky_down.png"));
			clyde_down = ImageIO.read(Vue.class.getResource("image/Clyde_down.png"));
			inky_down = ImageIO.read(Vue.class.getResource("image/Inky_down.png"));
			pinky_down = ImageIO.read(Vue.class.getResource("image/Pinky_down.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * On place blinky et inky � une position al�atoire en haut de la
		 * fen�tre, et pinky et clyde � une position al�atoire � gauche de la
		 * fen�tre
		 */
		blinkyX = (int) (Math.random() * 620);
		blinkyY = 10;
		inkyX = (int) (Math.random() * 620);
		inkyY = 10;
		pinkyX = 10;
		pinkyY = (int) (Math.random() * 590);
		clydeX = 10;
		clydeY = (int) (Math.random() * 590);
		blinkyDirX = chooseDir();
		blinkyDirY = chooseDir();
		inkyDirX = chooseDir();
		inkyDirY = chooseDir();
		pinkyDirX = chooseDir();
		pinkyDirY = chooseDir();
		clydeDirX = chooseDir();
		clydeDirY = chooseDir();
	}

	/**
	 * Renvoie une direction al�atoire
	 * 
	 * @return la direction
	 */
	private int chooseDir() {
		int random = (int) (Math.random() * 2);
		if (random == 0) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * Actualise l'affichage de la page
	 */
	public void paintComponent(Graphics g) {
		blinkyX += blinkyDirX;
		blinkyY += blinkyDirY;
		inkyX += inkyDirX;
		inkyY += inkyDirY;
		pinkyX += pinkyDirX;
		pinkyY += pinkyDirY;
		clydeX += clydeDirX;
		clydeY += clydeDirY;
		changeDirection();
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(startpage, 0, 0, this);

		g.drawImage(blinky_down, blinkyX, blinkyY, this);
		g.drawImage(inky_down, inkyX, inkyY, this);
		g.drawImage(pinky_down, pinkyX, pinkyY, this);
		g.drawImage(clyde_down, clydeX, clydeY, this);
	}

	/**
	 * Si l'un des quatre fantomes touche le bord de la fen�tre, on modifie sa
	 * direction
	 */
	private void changeDirection() {
		if (blinkyX + 28 == this.getWidth()) {
			blinkyDirX = -1;
		}
		if (blinkyY + 28 == this.getHeight()) {
			blinkyDirY = -1;
		}
		if (blinkyX == 0) {
			blinkyDirX = +1;
		}
		if (blinkyY == 0) {
			blinkyDirY = +1;
		}
		if (inkyX + 28 == this.getWidth()) {
			inkyDirX = -1;
		}
		if (inkyY + 28 == this.getHeight()) {
			inkyDirY = -1;
		}
		if (inkyX == 0) {
			inkyDirX = +1;
		}
		if (inkyY == 0) {
			inkyDirY = +1;
		}
		if (pinkyX + 28 == this.getWidth()) {
			pinkyDirX = -1;
		}
		if (pinkyY + 28 == this.getHeight()) {
			pinkyDirY = -1;
		}
		if (pinkyX == 0) {
			pinkyDirX = +1;
		}
		if (pinkyY == 0) {
			pinkyDirY = +1;
		}
		if (clydeX + 28 == this.getWidth()) {
			clydeDirX = -1;
		}
		if (clydeY + 28 == this.getHeight()) {
			clydeDirY = -1;
		}
		if (clydeX == 0) {
			clydeDirX = +1;
		}
		if (clydeY == 0) {
			clydeDirY = +1;
		}
	}
}