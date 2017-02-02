package Vue;

import Controller.Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import Modele.Ghost;
import Modele.Pacman;

/**
 * PanelInGame est la classe centralisant toutes les méthodes d'affichage durant
 * le jeu
 * 
 * @author Duchene Herrmann Rety
 *
 */
@SuppressWarnings("serial")
public class PanelInGame extends JPanel {

	/**
	 * boolean qui gère la pause du jeu
	 */
	private boolean pause;

	/**
	 * entier qui gère le compteur de début de jeu
	 */
	private int counter;

	/**
	 * attribut qui gère le fantôme Blinky
	 */
	private Ghost[] ghost;

	/**
	 * attribut qui gère le Pacman
	 */
	private Pacman hero;

	/**
	 * tableau des bonus affichés
	 */
	private boolean[] bonus;

	/**
	 * attribut contenant toutes les images
	 */
	private MyImage myimage;

	/**
	 * Contructeur de PanelInGame, initialisation des images et des attributs du
	 * panneau
	 * 
	 */
	public PanelInGame() {
		pause = false;
		counter = 0;
		myimage = new MyImage();
	}

	/**
	 * Affichage du labyrinth, du pacman, des fantômes et des options affichage
	 * de la pause et du compteur si besoin
	 * 
	 * @param g
	 *            Fenêtre actuelle
	 */
	public void paintComponent(Graphics g) {
		drawLabyrinth(g);
		drawOptions(g);
		drawGhost(g);
		drawPacman(g);
		if (pause) {
			drawPause(g);
		}
		if (counter != 0) {
			Font font = new Font("Courier", Font.BOLD, 150);
			g.setFont(font);
			g.setColor(Color.YELLOW);
			g.drawString(Integer.toString(counter), this.getWidth() / 2 - 100, this.getHeight() / 2 + 32);
		}
	}

	/**
	 * Affichage des cases du labyrinth et des lignes de démarcation des bonus
	 * 
	 * @param g
	 *            Fenêtre actuelle
	 */
	public void drawLabyrinth(Graphics g) {
		int mat[][] = Controller.getLabyrinth();

		// affichage du fond noir
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// affichage du labyrinth
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 22; j++) {
				switch (mat[i][j]) {
				case -5:
					g.drawImage(myimage.getCase("case-5"), i * 28, j * 28, this);
					break;
				case -4:
					g.drawImage(myimage.getBonus("Fraise"), i * 28, j * 28, this);
					break;
				case -3:
					g.drawImage(myimage.getBonus("Cerise"), i * 28, j * 28, this);
					break;
				case -2:
					g.drawImage(myimage.getBonus("Orange"), i * 28, j * 28, this);
					break;
				case -1:
					g.drawImage(myimage.getBonus("Pomme"), i * 28, j * 28, this);
					break;
				case 0:
					g.drawImage(myimage.getCase("case0"), i * 28, j * 28, this);
					break;
				case 1:
					g.drawImage(myimage.getCase("case1"), i * 28, j * 28, this);
					break;
				case 2:
					g.drawImage(myimage.getCase("case2"), i * 28, j * 28, this);
					break;
				case 3:
					g.drawImage(myimage.getCase("case3"), i * 28, j * 28, this);
					break;
				case 4:
					g.drawImage(myimage.getCase("case4"), i * 28, j * 28, this);
					break;
				case 5:
					g.drawImage(myimage.getCase("case5"), i * 28, j * 28, this);
					break;
				case 6:
					g.drawImage(myimage.getCase("case6"), i * 28, j * 28, this);
					break;
				case 7:
					g.drawImage(myimage.getCase("case7"), i * 28, j * 28, this);
					break;
				case 8:
					g.drawImage(myimage.getCase("case8"), i * 28, j * 28, this);
					break;
				case 9:
					g.drawImage(myimage.getCase("case9"), i * 28, j * 28, this);
					break;
				case 10:
					g.drawImage(myimage.getCase("case10"), i * 28, j * 28, this);
					break;
				case 11:
					g.drawImage(myimage.getCase("case11"), i * 28, j * 28, this);
					break;
				case 12:
					g.drawImage(myimage.getCase("case12"), i * 28, j * 28, this);
					break;
				case 13:
					g.drawImage(myimage.getCase("case13"), i * 28, j * 28, this);
					break;
				case 14:
					g.drawImage(myimage.getCase("case14"), i * 28, j * 28, this);
					break;
				case 15:
					g.drawImage(myimage.getCase("case15"), i * 28, j * 28, this);
					break;
				case 16:
					g.drawImage(myimage.getCase("case16"), i * 28, j * 28, this);
					break;
				case 17:
					g.drawImage(myimage.getCase("case17"), i * 28, j * 28, this);
					break;
				case 18:
					g.drawImage(myimage.getCase("case18"), i * 28, j * 28, this);
					break;
				case 19:
					g.drawImage(myimage.getCase("case19"), i * 28, j * 28, this);
					break;
				case 20:
					g.drawImage(myimage.getCase("case20"), i * 28, j * 28, this);
					break;
				case 21:
					g.drawImage(myimage.getCase("case21"), i * 28, j * 28, this);
					break;
				case 22:
					g.drawImage(myimage.getCase("case22"), i * 28, j * 28, this);
					break;
				case 23:
					g.drawImage(myimage.getCase("case23"), i * 28, j * 28, this);
					break;
				case 24:
					g.drawImage(myimage.getCase("case24"), i * 28, j * 28, this);
					break;
				case 25:
					g.drawImage(myimage.getCase("case25"), i * 28, j * 28, this);
					break;
				}
			}
		}
		// affichage des lignes de séparation labyrinth/option
		g.setColor(Color.white);
		g.fillRect(this.getWidth() - 120, 0, 2, this.getHeight());
		g.fillRect(this.getWidth() - 124, 0, 2, this.getHeight());
	}

	/**
	 * Affichage du pacman en fonction de sa direction et du temps
	 * 
	 * @param g
	 *            Fenêtre actuelle
	 */
	public void drawPacman(Graphics g) {
		/*
		 * affichage de pacman en fonction de sa direction et du timer pour
		 * faire l'animation
		 */
		hero.refreshForm();
		hero.refreshTimer_anim();
		if (hero.isAlive()) {
			if (hero.isOpen() && !this.pause) {
				switch (hero.getGo()) {
				case UP:
					g.drawImage(myimage.getPacman("Pacman_up"), hero.getCoordX(), hero.getCoordY(), this);
					break;
				case DOWN:
					g.drawImage(myimage.getPacman("Pacman_down"), hero.getCoordX(), hero.getCoordY(), this);
					break;
				case LEFT:
					g.drawImage(myimage.getPacman("Pacman_left"), hero.getCoordX(), hero.getCoordY(), this);
					break;
				case RIGHT:
					g.drawImage(myimage.getPacman("Pacman_right"), hero.getCoordX(), hero.getCoordY(), this);
					break;
				default:
				}
			} else {
				g.drawImage(myimage.getPacman("Pacman_close"), hero.getCoordX(), hero.getCoordY(), this);
			}
		} else {
			if (hero.getTimer_anim() > 0 && hero.getTimer_anim() < 4)
				g.drawImage(myimage.getPacman("Pacman_close"), hero.getCoordX(), hero.getCoordY(), this);
			if (hero.getTimer_anim() >= 4 && hero.getTimer_anim() < 8)
				g.drawImage(myimage.getPacman("Pacman_up"), hero.getCoordX(), hero.getCoordY(), this);
			if (hero.getTimer_anim() >= 8 && hero.getTimer_anim() < 12)
				g.drawImage(myimage.getPacman("Pacman_mort1"), hero.getCoordX(), hero.getCoordY(), this);
			if (hero.getTimer_anim() >= 12 && hero.getTimer_anim() < 16)
				g.drawImage(myimage.getPacman("Pacman_mort2"), hero.getCoordX(), hero.getCoordY(), this);
			if (hero.getTimer_anim() >= 16 && hero.getTimer_anim() < 20)
				g.drawImage(myimage.getPacman("Pacman_mort3"), hero.getCoordX(), hero.getCoordY(), this);
		}
	}

	/**
	 * Affichage de la pause
	 * 
	 * @param g
	 *            Fenêtre actuelle
	 */
	public void drawPause(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((this.getWidth() - 124) / 2 - 150, (this.getHeight() / 2) - 200, 300, 400);
		g.setColor(Color.YELLOW);
		g.drawRect((this.getWidth() - 124) / 2 - 150, (this.getHeight() / 2) - 200, 300, 400);
		Font font = new Font("Courier", Font.BOLD, 80);
		g.setFont(font);
		g.drawString("PAUSE", this.getWidth() / 5 + 17, this.getHeight() / 2 - 120);
	}

	/**
	 * Affichage des options (bonus, score, vie)
	 * 
	 * @param g
	 *            Fenêtre actuelle
	 */
	public void drawOptions(Graphics g) {
		/*
		 * affichage des options: - le nombre de vie - les differents bonus
		 */
		Font font = new Font("Courier", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Life:", this.getWidth() - 105, (this.getHeight() / 3) - 28);
		for (int i = 0; i < hero.getLife(); i++) {
			g.drawImage(myimage.getPacman("Pacman_right"), (this.getWidth() - 105) + i * 28,
					(this.getHeight() / 3) - 14, this);
		}
		g.drawString("Bonus:", this.getWidth() - 105, 2 * (this.getHeight() / 3) - 28);
		if (bonus[0])
			g.drawImage(myimage.getBonus("Pomme"), this.getWidth() - 50, 2 * (this.getHeight() / 3) + 32, this);
		if (bonus[1])
			g.drawImage(myimage.getBonus("Orange"), this.getWidth() - 105, 2 * (this.getHeight() / 3) + 32, this);
		if (bonus[2])
			g.drawImage(myimage.getBonus("Cerise"), this.getWidth() - 50, 2 * (this.getHeight() / 3) - 10, this);
		if (bonus[3])
			g.drawImage(myimage.getBonus("Fraise"), this.getWidth() - 105, 2 * (this.getHeight() / 3) - 10, this);

		// affichage du score
		g.drawString("Score:", this.getWidth() - 105, 50);
		g.drawString("" + Controller.getScore(), this.getWidth() - 105, 75);
	}

	/**
	 * Affichage des fantomes en fonction de leur direction
	 * 
	 * @param g
	 *            Fenêtre actuelle
	 * @param actual
	 *            fantôme à afficher
	 * @param up
	 *            image du fantôme vers le haut
	 * @param down
	 *            image du fantôme vers le bas
	 * @param left
	 *            image du fantôme vers la gauche
	 * @param right
	 *            image du fantôme vers la droite
	 */
	public void drawDirectionGhost(Graphics g, Ghost actual, Image up, Image down, Image left, Image right) {
		switch (actual.getGo()) {
		case UP:
			g.drawImage(up, actual.getCoordX(), actual.getCoordY(), this);
			break;
		case DOWN:
			g.drawImage(down, actual.getCoordX(), actual.getCoordY(), this);
			break;
		case LEFT:
			g.drawImage(left, actual.getCoordX(), actual.getCoordY(), this);
			break;
		case RIGHT:
			g.drawImage(right, actual.getCoordX(), actual.getCoordY(), this);
			break;
		default:
		}
	}

	/**
	 * Affichage des fantomes mangeables
	 * 
	 * @param g
	 *            Fenêtre actuelle
	 * @param actual
	 *            fantôme à afficher
	 */
	public void drawEatableGhost(Graphics g, Ghost actual) {
		if (hero.getTimer_superPacman() >= 625) {
			if (hero.getTimer_superPacman() % 25 < 13) {
				g.drawImage(myimage.getGhost("White_Ghost"), actual.getCoordX(), actual.getCoordY(), this);
			} else {
				g.drawImage(myimage.getGhost("Blue_Ghost"), actual.getCoordX(), actual.getCoordY(), this);
			}
		} else {
			g.drawImage(myimage.getGhost("Blue_Ghost"), actual.getCoordX(), actual.getCoordY(), this);
		}
	}

	/**
	 * Affichage des fantomes en fonction de leur direction et de leur état
	 * 
	 * @param g
	 *            Fenêtre actuelle
	 * 
	 */
	public void drawGhost(Graphics g) {
		/*
		 * affichage des fantomes en fonction de: - leur etat
		 * (0->normal,1->bleu,2->yeux) - leur direction
		 */

		for (int i = 0; i < ghost.length; i++) {
			if (i % 4 == 0) {
				switch (ghost[i].getState()) {
				case 0:
					drawDirectionGhost(g, ghost[i], myimage.getGhost("Blinky_up"), myimage.getGhost("Blinky_down"),
							myimage.getGhost("Blinky_left"), myimage.getGhost("Blinky_right"));
					break;
				case 1:
					drawEatableGhost(g, ghost[i]);
					break;
				case 2:
					drawDirectionGhost(g, ghost[i], myimage.getGhost("Eyes_up"), myimage.getGhost("Eyes_down"),
							myimage.getGhost("Eyes_left"), myimage.getGhost("Eyes_right"));
					break;
				}
			}
			if (i % 4 == 1) {
				switch (ghost[i].getState()) {
				case 0:
					drawDirectionGhost(g, ghost[i], myimage.getGhost("Inky_up"), myimage.getGhost("Inky_down"),
							myimage.getGhost("Inky_left"), myimage.getGhost("Inky_right"));
					break;
				case 1:
					drawEatableGhost(g, ghost[i]);
					break;
				case 2:
					drawDirectionGhost(g, ghost[i], myimage.getGhost("Eyes_up"), myimage.getGhost("Eyes_down"),
							myimage.getGhost("Eyes_left"), myimage.getGhost("Eyes_right"));
					break;
				}
			}
			if (i % 4 == 2) {
				switch (ghost[i].getState()) {
				case 0:
					drawDirectionGhost(g, ghost[i], myimage.getGhost("Pinky_up"), myimage.getGhost("Pinky_down"),
							myimage.getGhost("Pinky_left"), myimage.getGhost("Pinky_right"));
					break;
				case 1:
					drawEatableGhost(g, ghost[i]);
					break;
				case 2:
					drawDirectionGhost(g, ghost[i], myimage.getGhost("Eyes_up"), myimage.getGhost("Eyes_down"),
							myimage.getGhost("Eyes_left"), myimage.getGhost("Eyes_right"));
					break;
				}
			}
			if (i % 4 == 3) {
				switch (ghost[i].getState()) {
				case 0:
					drawDirectionGhost(g, ghost[i], myimage.getGhost("Clyde_up"), myimage.getGhost("Clyde_down"),
							myimage.getGhost("Clyde_left"), myimage.getGhost("Clyde_right"));
					break;
				case 1:
					drawEatableGhost(g, ghost[i]);
					break;
				case 2:
					drawDirectionGhost(g, ghost[i], myimage.getGhost("Eyes_up"), myimage.getGhost("Eyes_down"),
							myimage.getGhost("Eyes_left"), myimage.getGhost("Eyes_right"));
					break;
				}
			}
		}
	}

	/**
	 * Setter du pacman
	 * 
	 * @param hero
	 *            nouveau pacman
	 */
	public void setHero(Pacman hero) {
		this.hero = hero;
	}

	/**
	 * Setter de la pause
	 * 
	 * @param _pause
	 *            nouvelle état
	 */
	public void setPause(boolean _pause) {
		this.pause = _pause;
	}

	/**
	 * Setter des nous
	 * 
	 * @param bonus
	 *            nouveaux états
	 */
	public void setBonus(boolean[] bonus) {
		this.bonus = bonus;
	}

	/**
	 * Setter du compteur
	 * 
	 * @param i
	 *            ième seconde
	 */
	public void setCounter(int i) {
		this.counter = i;
	}

	/**
	 * Change le tableau de fantomes
	 * 
	 * @param _ghost
	 *            le nouveau tableau de fantomes
	 */
	public void setGhost(Ghost[] _ghost) {
		this.ghost = _ghost;
	}
}
