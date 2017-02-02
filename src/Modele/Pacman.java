package Modele;

import Controller.*;

/**
 * Est utilisée pour définir les objets Pacman
 * 
 * @author Duchene Herrmann Rety
 *
 */
public class Pacman {

	/**
	 * Coordonnée X de Pacman
	 */
	private int coordX;

	/**
	 * Coordonnée Y de Pacman
	 */
	private int coordY;

	/**
	 * Direction dans laquelle Pacman va actuellement
	 */
	private Controller.Direction go;

	/**
	 * Direction choisie par l'utilisateur et dans laquelle il faudra aller dès
	 * que Pacman pourra
	 */
	private Controller.Direction toGo;

	/**
	 * Nombre de vies de Pacman
	 */
	private int life;

	/**
	 * Nombre de pixels parcourus par Pacman à chaque tour de boucle
	 */
	private int deplacement;

	/**
	 * Taille de la hitbox de Pacman
	 */
	private int length_box;

	/**
	 * Etat de Pacman, true = vivant, false = mort
	 */
	private boolean state;

	/**
	 * Timer de super Pacman
	 */
	private int timer_superPacman = 0;

	/**
	 * Timer serant à gérer l'animation de bouche ouverte/fermée et l'animation
	 * de mort
	 */
	private int timer_anim = 0;

	/**
	 * Forme de Pacman, true = bouche ouverte, false = bouche fermée
	 */
	private boolean form;

	/**
	 * Constructeur
	 */
	public Pacman() {
		this.life = 3;
	}

	/**
	 * Reset entier du pacman à ses coordonnées d'origine et sa direction
	 * d'origine
	 * 
	 * @param _coordX
	 *            Coordonée X à donner à Pacman
	 * @param _coordY
	 *            Coordonée y à donner à Pacman
	 * @param _go
	 *            Direction actuelle
	 * @param _toGo
	 *            Direction désirée
	 * @param _deplacement
	 *            Nombre de pixels à parcourir
	 * @param _length_box
	 *            Taille de la hitbox de Pacman
	 */
	public void reset(int _coordX, int _coordY, Controller.Direction _go, Controller.Direction _toGo, int _deplacement,
			int _length_box) {
		this.coordX = _coordX;
		this.coordY = _coordY;
		this.go = _go;
		this.toGo = _toGo;
		this.deplacement = _deplacement;
		this.length_box = _length_box;
		this.state = true;
	}

	/**
	 * Actualise les coordonnées x et y de Pacman en fonction de sa direction
	 * actuelle
	 */
	public void actualize_XY() {
		switch (go) {
		case UP:
			if (coordY - deplacement < 0) {
				coordY = Modele.maxY - length_box;
			} else {
				coordY -= deplacement;
			}
			break;
		case DOWN:
			if (coordY + deplacement + length_box > Modele.maxY) {
				coordY = 0;
			} else {
				coordY += deplacement;
			}
			break;
		case LEFT:
			if (coordX - deplacement < 0) {
				coordX = Modele.maxX - length_box - 122;
			} else {
				coordX -= deplacement;
			}
			break;
		case RIGHT:
			if (coordX + deplacement + length_box > Modele.maxX - 122) {
				coordX = 0;
			} else {
				coordX += deplacement;
			}
			break;
		default:
		}
	}

	/**
	 * Teste si Pacman est contre un mur
	 * 
	 * @param tempX
	 *            Coordonée X à vérifier
	 * @param tempY
	 *            Coordonée Y à vérifier
	 * @return false si c'est un mur
	 */
	public boolean letMeDoTheSmartThings(int tempX, int tempY) {
		tempX = (tempX / length_box) % 19;
		tempY = (tempY / length_box) % 22;
		if (Modele.labyrinth[tempX][tempY] > 1)
			return false;
		else
			return true;
	}

	/**
	 * Regarde si Pacman peut avancer dans la direction toTest
	 * 
	 * @param toTest
	 *            Direction à tester
	 * @return true si on peut aller dans cette direction
	 */
	public boolean canIGoHere(Controller.Direction toTest) {

		switch (toTest) {
		case UP:
			return (letMeDoTheSmartThings(coordX + length_box - deplacement, coordY - deplacement)
					&& letMeDoTheSmartThings(coordX, coordY - deplacement));
		case DOWN:
			return (letMeDoTheSmartThings(coordX, coordY + length_box)
					&& letMeDoTheSmartThings(coordX + length_box - deplacement, coordY + length_box));
		case LEFT:
			return letMeDoTheSmartThings(coordX - deplacement, coordY)
					&& letMeDoTheSmartThings(coordX - deplacement, coordY + length_box - deplacement);
		case RIGHT:
			return letMeDoTheSmartThings(coordX + length_box, coordY + length_box - deplacement)
					&& letMeDoTheSmartThings(coordX + length_box, coordY);
		default:
		}
		return false;
	}

	/**
	 * Enlève une vie à Pacman
	 */
	public void looseLife() {
		this.life--;
	}

	/**
	 * Renvoie le nombre de vies restantes à Pacman
	 * 
	 * @return le nombre de vie
	 */
	public int getLife() {
		return life;
	}

	/**
	 * Change le nombre de vies de Pacman
	 * 
	 * @param _life
	 *            Nouveau nombre de vie à donner
	 */
	public void setLife(int _life) {
		this.life = _life;
	}

	/**
	 * Renvoie la taille de la hitbox
	 * 
	 * @return taille de la hitbox
	 */
	public int getLength_box() {
		return length_box;
	}

	/**
	 * Renvoie la direction actuelle
	 * 
	 * @return la direction actuelle
	 */
	public Controller.Direction getGo() {
		return go;
	}

	/**
	 * Change la direction actuelle
	 * 
	 * @param go
	 *            Nouvelle direction
	 */
	public void setGo(Controller.Direction go) {
		this.go = go;
	}

	/**
	 * Renvoie la direction désirée
	 * 
	 * @return la direction désirée
	 */
	public Controller.Direction getToGo() {
		return toGo;
	}

	/**
	 * Change la direction désirée
	 * 
	 * @param toGo
	 *            Nouvelle direction désirée
	 */
	public void setToGo(Controller.Direction toGo) {
		this.toGo = toGo;
	}

	/**
	 * Setter spécifique de toGo pour Pacman en cas de clic de la souris de
	 * l'utilisateur
	 * 
	 * @param mouseX
	 *            Coordonée X du pointeur
	 * @param mouseY
	 *            Coordonée Y du pointeur
	 * @return Direction désirée par l'utilisateur
	 */
	public Controller.Direction setToGo(int mouseX, int mouseY) {
		mouseY = mouseY - 25;
		int x = this.coordX+(length_box/2) - mouseX;
		int y = this.coordY+(length_box/2) - mouseY;
		if (Math.abs(x) > Math.abs(y)) {
			if (x > 0) {
				this.toGo = Controller.Direction.LEFT;
			} else {
				this.toGo = Controller.Direction.RIGHT;
			}
		} else {
			if (y > 0) {
				this.toGo = Controller.Direction.UP;
			} else {
				this.toGo = Controller.Direction.DOWN;
			}
		}
		return this.toGo;
	}

	/**
	 * Renvoie la coordonée X
	 * 
	 * @return la coordonée X
	 */
	public int getCoordX() {
		return coordX;
	}

	/**
	 * Change la coordonée X
	 * 
	 * @param coordX
	 *            la coordonnée X
	 */
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	/**
	 * Renvoie la coordonée Y
	 * 
	 * @return la coordonée Y
	 */
	public int getCoordY() {
		return coordY;
	}

	/**
	 * Change la coordonnée Y
	 * 
	 * @param coordY
	 *            La coordonnée Y
	 */
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	/**
	 * Renvoie le nombre de tours écoulés depuis avoir avalé une super gomme
	 * 
	 * @return Le nombre de tours
	 */
	public int getTimer_superPacman() {
		return timer_superPacman;
	}

	/**
	 * Augmente le nombre de tours écoulé depuis avoir avalé une super gomme de
	 * un
	 */
	public void increment_Timer_superPacman() {
		timer_superPacman++;
	}

	/**
	 * Réinitialise le nombre de tours en tant que super pacman
	 */
	public void reset_Timer_superPacman() {
		timer_superPacman = 0;
	}

	/**
	 * Renvoie si le pacman est vivant ou non
	 * 
	 * @return l'état actuel du pacman
	 */
	public boolean isAlive() {
		return state;
	}

	/**
	 * Met l'état de pacman à faux (mort)
	 */
	public void isDead() {
		this.state = false;
	}

	/**
	 * Renvoie le timer qui gère la forme du pacman
	 * 
	 * @return le timer actuel du pacman
	 */
	public int getTimer_anim() {
		return timer_anim;
	}

	/**
	 * Actualise le timer qui gère la forme du pacman
	 */
	public void refreshTimer_anim() {
		if (timer_anim < 20)
			this.timer_anim++;
		else
			resetTimerAnim();
	}

	/**
	 * Réinitialise le timer qui gère la forme du pacman
	 */
	public void resetTimerAnim() {
		timer_anim = 0;
	}

	/**
	 * Renvoie true si le pacman est ouvert et false s'il est fermé
	 * 
	 * @return la forme acutel du pacman
	 */
	public boolean isOpen() {
		return form;
	}

	/**
	 * Actualise la forme du pacman en fonction du timer
	 */
	public void refreshForm() {
		switch (timer_anim) {
		case 20:
			form = false;
			break;
		case 0:
			form = true;
			break;
		}
	}

}
