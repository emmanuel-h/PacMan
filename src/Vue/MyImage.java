package Vue;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Précharge toutes les images du jeu pour pouvoir les afficher plus rapidement
 * et proprement
 * 
 * @author Duchene Herrmann Rety
 *
 */
public class MyImage {

	/**
	 * tableau des images du labyrinth
	 */
	private HashMap<String, Image> mylab;

	/**
	 * tableau des images des bonus
	 */
	private HashMap<String, Image> mybonus;

	/**
	 * tableau des images du pacman
	 */
	private HashMap<String, Image> mypacman;

	/**
	 * tableau des images des fantômes
	 */
	private HashMap<String, Image> myghost;

	/**
	 * Contructeur de MyImage, chargement de toutes les images du dossier image
	 * et création des tableaux regroupant ces images
	 * 
	 */
	public MyImage() {
		mylab = new HashMap<>();
		mybonus = new HashMap<>();
		mypacman = new HashMap<>();
		myghost = new HashMap<>();
		try { // chargement des images

			mylab.put("case0", ImageIO.read(Vue.class.getResource("image/case0.png")));
			mylab.put("case1", ImageIO.read(Vue.class.getResource("image/case1.png")));
			mylab.put("case-5", ImageIO.read(Vue.class.getResource("image/case-5.png")));
			mylab.put("case2", ImageIO.read(Vue.class.getResource("image/case2.png")));
			mylab.put("case3", ImageIO.read(Vue.class.getResource("image/case3.png")));
			mylab.put("case4", ImageIO.read(Vue.class.getResource("image/case4.png")));
			mylab.put("case5", ImageIO.read(Vue.class.getResource("image/case5.png")));
			mylab.put("case6", ImageIO.read(Vue.class.getResource("image/case6.png")));
			mylab.put("case7", ImageIO.read(Vue.class.getResource("image/case7.png")));
			mylab.put("case8", ImageIO.read(Vue.class.getResource("image/case8.png")));
			mylab.put("case9", ImageIO.read(Vue.class.getResource("image/case9.png")));
			mylab.put("case10", ImageIO.read(Vue.class.getResource("image/case10.png")));
			mylab.put("case11", ImageIO.read(Vue.class.getResource("image/case11.png")));
			mylab.put("case12", ImageIO.read(Vue.class.getResource("image/case12.png")));
			mylab.put("case13", ImageIO.read(Vue.class.getResource("image/case13.png")));
			mylab.put("case14", ImageIO.read(Vue.class.getResource("image/case14.png")));
			mylab.put("case15", ImageIO.read(Vue.class.getResource("image/case15.png")));
			mylab.put("case16", ImageIO.read(Vue.class.getResource("image/case16.png")));
			mylab.put("case17", ImageIO.read(Vue.class.getResource("image/case17.png")));
			mylab.put("case18", ImageIO.read(Vue.class.getResource("image/case18.png")));
			mylab.put("case19", ImageIO.read(Vue.class.getResource("image/case19.png")));
			mylab.put("case20", ImageIO.read(Vue.class.getResource("image/case20.png")));
			mylab.put("case21", ImageIO.read(Vue.class.getResource("image/case21.png")));
			mylab.put("case22", ImageIO.read(Vue.class.getResource("image/case22.png")));
			mylab.put("case23", ImageIO.read(Vue.class.getResource("image/case23.png")));
			mylab.put("case24", ImageIO.read(Vue.class.getResource("image/case24.png")));
			mylab.put("case25", ImageIO.read(Vue.class.getResource("image/case25.png")));
			mypacman.put("Pacman_left", ImageIO.read(Vue.class.getResource("image/Pacman_left.png")));
			mypacman.put("Pacman_right", ImageIO.read(Vue.class.getResource("image/Pacman_right.png")));
			mypacman.put("Pacman_up", ImageIO.read(Vue.class.getResource("image/Pacman_up.png")));
			mypacman.put("Pacman_down", ImageIO.read(Vue.class.getResource("image/Pacman_down.png")));
			mypacman.put("Pacman_close", ImageIO.read(Vue.class.getResource("image/Pacman_close.png")));
			mypacman.put("Pacman_mort1", ImageIO.read(Vue.class.getResource("image/Pacman_mort1.png")));
			mypacman.put("Pacman_mort2", ImageIO.read(Vue.class.getResource("image/Pacman_mort2.png")));
			mypacman.put("Pacman_mort3", ImageIO.read(Vue.class.getResource("image/Pacman_mort3.png")));
			myghost.put("Blinky_left", ImageIO.read(Vue.class.getResource("image/Blinky_left.png")));
			myghost.put("Blinky_up", ImageIO.read(Vue.class.getResource("image/Blinky_up.png")));
			myghost.put("Blinky_down", ImageIO.read(Vue.class.getResource("image/Blinky_down.png")));
			myghost.put("Blinky_right", ImageIO.read(Vue.class.getResource("image/Blinky_right.png")));
			myghost.put("Clyde_left", ImageIO.read(Vue.class.getResource("image/Clyde_left.png")));
			myghost.put("Clyde_up", ImageIO.read(Vue.class.getResource("image/Clyde_up.png")));
			myghost.put("Clyde_down", ImageIO.read(Vue.class.getResource("image/Clyde_down.png")));
			myghost.put("Clyde_right", ImageIO.read(Vue.class.getResource("image/Clyde_right.png")));
			myghost.put("Inky_left", ImageIO.read(Vue.class.getResource("image/Inky_left.png")));
			myghost.put("Inky_up", ImageIO.read(Vue.class.getResource("image/Inky_up.png")));
			myghost.put("Inky_down", ImageIO.read(Vue.class.getResource("image/Inky_down.png")));
			myghost.put("Inky_right", ImageIO.read(Vue.class.getResource("image/Inky_right.png")));
			myghost.put("Pinky_left", ImageIO.read(Vue.class.getResource("image/Pinky_left.png")));
			myghost.put("Pinky_up", ImageIO.read(Vue.class.getResource("image/Pinky_up.png")));
			myghost.put("Pinky_down", ImageIO.read(Vue.class.getResource("image/Pinky_down.png")));
			myghost.put("Pinky_right", ImageIO.read(Vue.class.getResource("image/Pinky_right.png")));
			myghost.put("Blue_Ghost", ImageIO.read(Vue.class.getResource("image/Blue_Ghost.png")));
			myghost.put("White_Ghost", ImageIO.read(Vue.class.getResource("image/White_Ghost.png")));
			myghost.put("Eyes_right", ImageIO.read(Vue.class.getResource("image/Eyes_right.png")));
			myghost.put("Eyes_up", ImageIO.read(Vue.class.getResource("image/Eyes_up.png")));
			myghost.put("Eyes_down", ImageIO.read(Vue.class.getResource("image/Eyes_down.png")));
			myghost.put("Eyes_left", ImageIO.read(Vue.class.getResource("image/Eyes_left.png")));
			mybonus.put("Fraise", ImageIO.read(Vue.class.getResource("image/Fraise.png")));
			mybonus.put("Orange", ImageIO.read(Vue.class.getResource("image/Orange.png")));
			mybonus.put("Cerise", ImageIO.read(Vue.class.getResource("image/Cerise.png")));
			mybonus.put("Pomme", ImageIO.read(Vue.class.getResource("image/Pomme.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retourne l'image à la case i du tableau lab
	 * 
	 * @param name
	 *            Le nom du sprite à afficher
	 * @return l'image correspondante
	 */
	public Image getCase(String name) {
		return mylab.get(name);
	}

	/**
	 * Retourne l'image d'un fantôme dans une direction du tableau myghost
	 * 
	 * @param name
	 *            nom du fantôme
	 * @return l'image correspondante
	 */
	public Image getGhost(String name) {
		return myghost.get(name);
	}

	/**
	 * Retourne l'image d'une bonus du tableau mybonus
	 * 
	 * @param name
	 *            nom du bonus
	 * @return l'image correspondante
	 */
	public Image getBonus(String name) {
		return mybonus.get(name);
	}

	/**
	 * Retourne l'image d'une direction de pacman du tableau mypacman
	 * 
	 * @param name
	 *            nom de la direction du pacman
	 * @return l'image correspondante
	 */
	public Image getPacman(String name) {
		return mypacman.get(name);
	}
}