package Controller;

import Vue.*;
import Modele.*;

/**
 * Le controleur est le lien entre le mod�le et la vue, il leur sert de
 * transition
 * 
 * @author Duchene Herrmann Rety
 *
 */
public class Controller {

	/**
	 * Type enum utilis� par toutes les classes pour d�finir les directions
	 *
	 */
	public enum Direction {
		UP, DOWN, LEFT, RIGHT, SPACE, UNKNOW
	}

	/**
	 * Vue dans laquelle on va travailler
	 */
	Vue vue;

	/**
	 * Listener pour les entr�es clavier et les clics de souris en jeu
	 */
	KeyboardAndMouseMovementsInputs kammi;

	/**
	 * Listener pour les clics de souris sur des boutons
	 */
	MouseClic mc;

	/**
	 * Objet Pacman avec lequel travailler
	 */
	Pacman hero;

	/**
	 * Musique actuelle
	 */
	Music current_music;

	/**
	 * true si la musique doit �tre jou�e, false si l'utilisateur a demand� de
	 * l'�teindre
	 */
	boolean stopstartmusic = true;

	/**
	 * Constructeur
	 * 
	 * @param maxX
	 *            La largeur de la fen�tre
	 * @param maxY
	 *            La hauteur de la fen�tre
	 */
	public Controller(int maxX, int maxY) {
		this.kammi = new KeyboardAndMouseMovementsInputs(Direction.UP);
		this.mc = new MouseClic();
		this.vue = new Vue(maxX, maxY);
		current_music = new Music();
	}

	/**
	 * Regarde si l'utilisateur a utilis� les touches de direction ou un clic de
	 * souris pour d�placer son pacman
	 * 
	 * @return true si un clic a eu lieu
	 */
	public boolean isItAClickOrATap() {
		return kammi.isGimmeACheese();
	}

	/**
	 * Renvoie le score actuel
	 * 
	 * @return le score
	 */
	public static int getScore() {
		return Modele.score;
	}

	/**
	 * Renvoie le labyrinthe courant
	 * 
	 * @return le labyrinthe
	 */
	public static int[][] getLabyrinth() {
		return Modele.labyrinth;
	}

	/**
	 * Demande � la vue d'afficher la pause
	 */
	public void showPause() {
		vue.showPause();
	}

	/**
	 * Demande � la vue d'enlever la pause
	 */
	public void hidePause() {
		vue.hidePause();
	}

	/**
	 * Met � jour la vue avec de nouveaux param�tres
	 * 
	 * @param hero
	 *            Le pacman actuel
	 * @param maxX
	 *            La largeur de la fen�tre
	 * @param maxY
	 *            La hauteur de la fen�tre
	 * @param ghost
	 *            Le tableau de fantomes
	 * @param bonus
	 *            Les bonus mang�s
	 */
	public void majVue(Pacman hero, int maxX, int maxY, Ghost[] ghost, boolean[] bonus) {
		vue.majVue(hero, maxX, maxY, ghost, bonus);
	}

	/**
	 * Dit au pacman quelle m�thode utiliser pour calculer la direction demand�e
	 * par l'utilisateur par rapport � s'il a utilis� la souris ou le clavier
	 * pour se diriger
	 */
	public void tellMeTheWayToGoPlease() {

		// On prend la direction de la souris s'il y a eu un clic...
		if (kammi.isGimmeACheese()) {
			kammi.setGimmeACheese(false);
			hero.setToGo(kammi.getMouseX(), kammi.getMouseY());
			kammi.setGo(hero.setToGo(kammi.getMouseX(), kammi.getMouseY()));
			// ... ou sinon l'entr�e clavier par d�faut
		} else {
			// Test si l'utilisateur souhaite mettre en pause le jeu
			if (kammi.tellMeTheWayToGoPlease() == Direction.SPACE) {
				kammi.setGo(hero.getGo());
				hero.setToGo(Direction.SPACE);
			} else {
				hero.setToGo(kammi.tellMeTheWayToGoPlease());
			}
		}
	}

	/**
	 * Remet la direction du listener par d�faut (up)
	 */
	public void resetDirection() {
		kammi.setGo(Direction.UP);
	}

	/**
	 * Demande � la vue de rafraichir son affichage en jeu
	 * 
	 * @param counter
	 *            Entier � afficher, 0 si rien � afficher
	 */
	public void refresh(int counter) {
		vue.refresh(counter);
	}

	/**
	 * Donne � la vue le scoreboard courant
	 * 
	 * @param tab
	 *            le scoreboard
	 */
	public void leaderBoard(String[][] tab) {
		vue.setPanelLeaderBoard(mc, tab);
	}

	/**
	 * Demande � la vue de cr�er son affichage de fin de jeu
	 */
	public void endPage() {
		vue.setPanelEndGame(mc);
	}

	/**
	 * Demande � la vue de cr�er son affichagge de d�but de jeu
	 */
	public void startPage() {
		vue.setPanelStart(mc);
	}

	/**
	 * Demande � la vue de rafraichir l'affichage de la partie About
	 */
	public void refreshAbout() {
		vue.refreshAbout();
	}

	/**
	 * Demande � la vue de cr�er l'affichage about
	 */
	public void aboutPage() {
		vue.setPanelAbout(mc);
	}

	/**
	 * Demande � la vue de cr�er l'affichage options
	 */
	public void optionPage() {
		vue.setPanelOption(mc, stopstartmusic);
	}

	/**
	 * Demande � la vue de cr�er l'affichage de d�but de jeu
	 */
	public void startGame() {
		vue.setPanelIngame(kammi, mc);
	}

	/**
	 * Donne le pacman courant
	 * 
	 * @param hero
	 *            le pacman courant
	 */
	public void setHero(Pacman hero) {
		this.hero = hero;
	}

	/**
	 * Rafraichit l'affichage de la page d'accueil
	 * 
	 * @return la dernier action entendue par le listener
	 */
	public String majStartPage() {
		return mc.getAction();
	}

	/**
	 * Actualise la page options
	 * 
	 * @param ghostNumber
	 *            Le nombre de fantomes
	 */
	public void refreshOption(String ghostNumber) {
		vue.refreshOption(ghostNumber);
	}

	/**
	 * Actualise l'�cran d'accueil
	 */
	public void refreshStart() {
		vue.refreshStart();
	}

	/**
	 * Actualise l'�cran de fin
	 */
	public void refreshEnd() {
		vue.refreshEndGame();
	}

	/**
	 * Actualise l'�cran du leaderboard
	 */
	public void refreshLeaderBoard() {
		vue.refreshLeaderBoard();
	}

	/**
	 * Demande au listener le nom entr� par l'utilisateur
	 * 
	 * @return le nom de l'utilisateur
	 */
	public String getUserName() {
		return mc.getName();
	}

	/**
	 * Demande au listener la difficult� choisie par l'utilisateur
	 * 
	 * @return la difficult�
	 */
	public String whichDifficulty() {
		return mc.getDifficulty();
	}

	/**
	 * Demande au listener quel niveau l'utilisateur veut faire
	 * 
	 * @return le choix du niveau
	 */
	public String whichLevel() {
		return mc.getLevel();
	}

	/**
	 * Demande au listener quel scoreboard l'utilisateur veut suppruler
	 * 
	 * @return le choix du niveau
	 */
	public String whichDelete() {
		return mc.getDelete();
	}

	/**
	 * Change la musique en jeu si le son n'a pas �t� coup�
	 * 
	 * @param new_song
	 *            la nouvelle musique � jouer
	 */
	public void changeMusic(String new_song) {
		if (stopstartmusic)
			current_music.changeMusic(new_song);
	}

	/**
	 * Remet l'action du listener par d�faut
	 */
	public void resetAction() {
		mc.setAction("Nothing");
	}

	/**
	 * Si la musique est en cours, l'arr�te, et si elle est arr�t�e, la lance
	 */
	public void stopMusic() {
		resetAction();
		current_music.stopMusic();
		if (stopstartmusic)
			stopstartmusic = false;
		else
			stopstartmusic = true;
	}

	/**
	 * Setter
	 * 
	 * @param stopstartmusic
	 *            la nouvelle valeur � appliquer
	 */
	public void setStopstartmusic(boolean stopstartmusic) {
		this.stopstartmusic = stopstartmusic;
	}

	/**
	 * Renvoie la valeur de stopstartmusic
	 * 
	 * @return la valeur de stopstartmusic
	 */
	public boolean isStopstartmusic() {
		return stopstartmusic;
	}

	/**
	 * R�initialise la compteur de la vue � l'entier pass� en param�tre
	 * 
	 * @param i
	 *            la nouvelle valeur du compteur
	 */
	public void setCounter(int i) {
		vue.setCounter(i);
	}

	/**
	 * R�initialise tous les param�tres de la vue
	 */
	public void resetVue() {
		vue.create();
	}

	/**
	 * Met � jour l'affichage du leaderboard de la age d'accueil
	 * 
	 * @param extract
	 *            Le tableau du leaderboard
	 * @param level
	 *            Le niveau actuel
	 */
	public void setTabLeaderBoard(String[][] extract, String level) {
		vue.setTabLeaderBoard(extract, level);
	}

	/**
	 * Initialise l'affichage du leaderboard de la page d'accueil
	 * 
	 * @param extract
	 *            Le tableau du leaderboard
	 * @param level
	 *            Le niveau actuel
	 */
	public void setPanelLeaderBoardFromStartPage(String[][] extract, String level) {
		vue.setPanelLeaderBoardFromStartPage(mc, extract, level);
	}

}
