package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Affiche la page de l'écran d'accueil
 * 
 * @author Duchene Herrmann Rety
 *
 */
@SuppressWarnings("serial")
public class PanelStart extends JPanel {

	/**
	 * Image de blinky affichée
	 */
	private Image blinky_down;

	/**
	 * Image de clyde affichée
	 */
	private Image clyde_down;

	/**
	 * Image de inky affichée
	 */
	private Image inky_down;

	/**
	 * Image de pinky affichée
	 */
	private Image pinky_down;

	/**
	 * Image de fond
	 */
	private Image startpage;

	/**
	 * Coordonée x de blinky
	 */
	private int blinkyX;

	/**
	 * Coordonnée y de blinky
	 */
	private int blinkyY;

	/**
	 * Coordonnée x d'inky
	 */
	private int inkyX;

	/**
	 * Coordonnée y d'inky
	 */
	private int inkyY;

	/**
	 * Coordonnée x de pinky
	 */
	private int pinkyX;

	/**
	 * Coordonnée y de pinky
	 */
	private int pinkyY;

	/**
	 * Coordonnée x de clyde
	 */
	private int clydeX;

	/**
	 * Coordonnée y de clyde
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
		 * On place blinky et inky à une position aléatoire en haut de la
		 * fenêtre, et pinky et clyde à une position aléatoire à gauche de la
		 * fenêtre
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
	 * Renvoie une direction aléatoire
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
	 * Si l'un des quatre fantomes touche le bord de la fenêtre, on modifie sa
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