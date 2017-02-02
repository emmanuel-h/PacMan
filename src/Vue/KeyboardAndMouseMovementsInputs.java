package Vue;

import Controller.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Gère toutes les entrées utilisateur, que ce soit au clavier ou à la souris
 * 
 * @author Duchene Herrmann Rety
 */
public class KeyboardAndMouseMovementsInputs implements KeyListener, MouseListener {

	/**
	 * Direction demandée
	 */
	private Controller.Direction go;

	/**
	 * Coordonée x de la souris
	 */
	private int mouseX;

	/**
	 * Coordonée y de la souris
	 */
	private int mouseY;

	/**
	 * true si l'utilisateur a cliqué dans la fenêtre
	 */
	private boolean gimmeACheese;

	/**
	 * Constructeur
	 * 
	 * @param _go
	 *            direction pour initialiser go
	 */
	public KeyboardAndMouseMovementsInputs(Controller.Direction _go) {
		this.go = _go;
		this.mouseX = -1;
		this.mouseY = -1;
		this.gimmeACheese = false;
	}

	/**
	 * Renvoie la direction demandée par l'utilisateur en cas d'entrée au
	 * clavier
	 * 
	 * @return La direction demandée
	 */
	public Controller.Direction tellMeTheWayToGoPlease() {
		return go;
	}

	/**
	 * Déclenché en cas d'entrée clavier
	 */
	public void keyPressed(KeyEvent k) {
		switch (k.getKeyCode()) {
		case KeyEvent.VK_UP:
			go = Controller.Direction.UP;
			break;
		case KeyEvent.VK_DOWN:
			go = Controller.Direction.DOWN;
			break;
		case KeyEvent.VK_LEFT:
			go = Controller.Direction.LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			go = Controller.Direction.RIGHT;
			break;
		case 32:
		case 27:
			go = Controller.Direction.SPACE;
			break;
		}
	}

	/**
	 * Non utilisé
	 */
	public void keyReleased(KeyEvent arg0) {
	}

	/**
	 * Non utilisé
	 */
	public void keyTyped(KeyEvent arg0) {
	}

	/**
	 * Non utilisé
	 */
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Non utilisé
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Non utilisé
	 */
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Déclenchée en cas de clic de souris
	 */
	public void mousePressed(MouseEvent e) {
		this.mouseX = e.getX();
		this.mouseY = e.getY();
		this.gimmeACheese = true;
	}

	/**
	 * Non utilisé
	 */
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Renvoie la valeur de gimmeACheese
	 * 
	 * @return la valeur
	 */
	public boolean isGimmeACheese() {
		return gimmeACheese;
	}

	/**
	 * Remplace la valeur de gimmeACheese
	 * 
	 * @param gimmeACheese
	 *            La valeur
	 */
	public void setGimmeACheese(boolean gimmeACheese) {
		this.gimmeACheese = gimmeACheese;
	}

	/**
	 * Renvoie la valeur du x de la souris
	 * 
	 * @return la valeur
	 */
	public int getMouseX() {
		return mouseX;
	}

	/**
	 * Renvoie la valeur du y de la souris
	 * 
	 * @return la valeur
	 */
	public int getMouseY() {
		return mouseY;
	}

	/**
	 * Réinitialise setGo
	 * 
	 * @param go
	 *            la nouvelle direction
	 */
	public void setGo(Controller.Direction go) {
		this.go = go;
	}
}
