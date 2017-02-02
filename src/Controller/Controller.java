package Controller;

import Vue.*;
import Modele.*;

/**
 * Le controleur est le lien entre le modèle et la vue, il leur sert de
 * transition
 * 
 * @author Duchene Herrmann Rety
 *
 */
public class Controller {

	/**
	 * Type enum utilisé par toutes les classes pour définir les directions
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
	 * Listener pour les entrées clavier et les clics de souris en jeu
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
	 * true si la musique doit être jouée, false si l'utilisateur a demandé de
	 * l'éteindre
	 */
	boolean stopstartmusic = true;

	/**
	 * Constructeur
	 * 
	 * @param maxX
	 *            La largeur de la fenêtre
	 * @param maxY
	 *            La hauteur de la fenêtre
	 */
	public Controller(int maxX, int maxY) {
		this.kammi = new KeyboardAndMouseMovementsInputs(Direction.UP);
		this.mc = new MouseClic();
		this.vue = new Vue(maxX, maxY);
		current_music = new Music();
	}

	/**
	 * Regarde si l'utilisateur a utilisé les touches de direction ou un clic de
	 * souris pour déplacer son pacman
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
	 * Demande à la vue d'afficher la pause
	 */
	public void showPause() {
		vue.showPause();
	}

	/**
	 * Demande à la vue d'enlever la pause
	 */
	public void hidePause() {
		vue.hidePause();
	}

	/**
	 * Met à jour la vue avec de nouveaux paramètres
	 * 
	 * @param hero
	 *            Le pacman actuel
	 * @param maxX
	 *            La largeur de la fenêtre
	 * @param maxY
	 *            La hauteur de la fenêtre
	 * @param ghost
	 *            Le tableau de fantomes
	 * @param bonus
	 *            Les bonus mangés
	 */
	public void majVue(Pacman hero, int maxX, int maxY, Ghost[] ghost, boolean[] bonus) {
		vue.majVue(hero, maxX, maxY, ghost, bonus);
	}

	/**
	 * Dit au pacman quelle méthode utiliser pour calculer la direction demandée
	 * par l'utilisateur par rapport à s'il a utilisé la souris ou le clavier
	 * pour se diriger
	 */
	public void tellMeTheWayToGoPlease() {

		// On prend la direction de la souris s'il y a eu un clic...
		if (kammi.isGimmeACheese()) {
			kammi.setGimmeACheese(false);
			hero.setToGo(kammi.getMouseX(), kammi.getMouseY());
			kammi.setGo(hero.setToGo(kammi.getMouseX(), kammi.getMouseY()));
			// ... ou sinon l'entrée clavier par défaut
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
	 * Remet la direction du listener par défaut (up)
	 */
	public void resetDirection() {
		kammi.setGo(Direction.UP);
	}

	/**
	 * Demande à la vue de rafraichir son affichage en jeu
	 * 
	 * @param counter
	 *            Entier à afficher, 0 si rien à afficher
	 */
	public void refresh(int counter) {
		vue.refresh(counter);
	}

	/**
	 * Donne à la vue le scoreboard courant
	 * 
	 * @param tab
	 *            le scoreboard
	 */
	public void leaderBoard(String[][] tab) {
		vue.setPanelLeaderBoard(mc, tab);
	}

	/**
	 * Demande à la vue de créer son affichage de fin de jeu
	 */
	public void endPage() {
		vue.setPanelEndGame(mc);
	}

	/**
	 * Demande à la vue de créer son affichagge de début de jeu
	 */
	public void startPage() {
		vue.setPanelStart(mc);
	}

	/**
	 * Demande à la vue de rafraichir l'affichage de la partie About
	 */
	public void refreshAbout() {
		vue.refreshAbout();
	}

	/**
	 * Demande à la vue de créer l'affichage about
	 */
	public void aboutPage() {
		vue.setPanelAbout(mc);
	}

	/**
	 * Demande à la vue de créer l'affichage options
	 */
	public void optionPage() {
		vue.setPanelOption(mc, stopstartmusic);
	}

	/**
	 * Demande à la vue de créer l'affichage de début de jeu
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
	 * Actualise l'écran d'accueil
	 */
	public void refreshStart() {
		vue.refreshStart();
	}

	/**
	 * Actualise l'écran de fin
	 */
	public void refreshEnd() {
		vue.refreshEndGame();
	}

	/**
	 * Actualise l'écran du leaderboard
	 */
	public void refreshLeaderBoard() {
		vue.refreshLeaderBoard();
	}

	/**
	 * Demande au listener le nom entré par l'utilisateur
	 * 
	 * @return le nom de l'utilisateur
	 */
	public String getUserName() {
		return mc.getName();
	}

	/**
	 * Demande au listener la difficulté choisie par l'utilisateur
	 * 
	 * @return la difficulté
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
	 * Change la musique en jeu si le son n'a pas été coupé
	 * 
	 * @param new_song
	 *            la nouvelle musique à jouer
	 */
	public void changeMusic(String new_song) {
		if (stopstartmusic)
			current_music.changeMusic(new_song);
	}

	/**
	 * Remet l'action du listener par défaut
	 */
	public void resetAction() {
		mc.setAction("Nothing");
	}

	/**
	 * Si la musique est en cours, l'arrête, et si elle est arrêtée, la lance
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
	 *            la nouvelle valeur à appliquer
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
	 * Réinitialise la compteur de la vue à l'entier passé en paramètre
	 * 
	 * @param i
	 *            la nouvelle valeur du compteur
	 */
	public void setCounter(int i) {
		vue.setCounter(i);
	}

	/**
	 * Réinitialise tous les paramètres de la vue
	 */
	public void resetVue() {
		vue.create();
	}

	/**
	 * Met à jour l'affichage du leaderboard de la age d'accueil
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
