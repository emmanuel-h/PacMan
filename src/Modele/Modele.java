package Modele;

import java.io.File;
import java.io.IOException;

import Controller.*;

/**
 * Modele est la classe centralisant tous les algorithmes de données du Pacman
 * 
 * @author Duchene Herrmann Rety
 * 
 *
 */

public class Modele {

	/**
	 * taille X de la fenêtre
	 */
	public static int maxX = 660;

	/**
	 * taille Y de la fenêtre
	 */
	public static int maxY = 652;

	/**
	 * taille du labyrinthe
	 */
	public static int[][] labyrinth;

	/**
	 * taille d'une case en pixels
	 */
	public static int length_box = 28;

	/**
	 * nombre de gommes dans le labyrinthe
	 */
	public static int gumGum;

	/**
	 * nombre de gommes total
	 */
	public static int totalGumGum;

	/**
	 * Nom du fichier dans lequel le niveau est enregistré
	 */
	public static String file_name;

	/**
	 * nombre de pixels parcourus par tour
	 */
	public static int deplacement = 1;

	/**
	 * tableau des bonus : false = Bonus pas encore apparu et true = Bonus
	 * apparu
	 */
	public static boolean[] bonus_pop;

	/**
	 * tableau des bonus mangés : false = Bonus pas encore apparu et true =
	 * Bonus apparu
	 */
	public static boolean[] bonus_eat;

	/**
	 * Nombre de points inscrits lors d'une partie
	 */
	public static int score;

	/**
	 * Difficulté du fantome en pourcentage d'aléatoire dans la direction des
	 * fantomes
	 */
	public static int difficulty = 50;

	/**
	 * Nom choisi par l'utilisateur
	 */
	public static String username = "";

	/**
	 * Stage choisi par l'utilisateur
	 */
	public static int stagePlaying;

	/**
	 * Nombre de fantomes
	 */
	public static int ghostSquad = 4;

	/**
	 * Remplit la matrice en fonction des labyrinthes préchargés dans des
	 * fichiers texte
	 */
	public static void fillMyTab() {
		try {
			IOTreatment.readMatrix(file_name);
		} catch (IOException e) {
			System.out.println("Erreur IO");
		}
	}

	/**
	 * 
	 * @param controle
	 *            Le controleur référent
	 * @param hero
	 *            Le Pacman courant
	 * @param ghost
	 *            Le tableau de fantomes
	 * @return false si l'utilisateur veut retourner au menu, true s'il veut
	 *         continuer le jeu
	 */
	public static boolean pause(Controller controle, Pacman hero, Ghost[] ghost) {
		boolean echap = false;
		boolean loop = true;
		String action = "";

		while (loop) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			controle.showPause();
			controle.tellMeTheWayToGoPlease();
			updateVue(controle, hero, ghost, bonus_eat);
			action = controle.majStartPage();

			switch (action) {
			case "ReturnAbout":
				echap = true;
				loop = false;
				break;
			case "Quit":
				System.exit(0);
				break;
			case "Back":
				hero.setToGo(Controller.Direction.SPACE);
				controle.resetAction();
				break;
			}

			if (hero.getToGo().equals(Controller.Direction.SPACE)) {
				controle.hidePause();
				hero.setToGo(hero.getGo());
				echap = false;
				loop = false;
			}
		}
		return echap;
	}

	/**
	 * Met à jour la vue via le controlleur avec toutes les données mises à jour
	 * 
	 * @param controle
	 *            Le controleur référent
	 * @param hero
	 *            Le Pacman courant
	 * @param ghost
	 *            Le tableau de fantomes
	 * @param bonus
	 *            Tableau de bonus mangés
	 */
	public static void updateVue(Controller controle, Pacman hero, Ghost[] ghost, boolean[] bonus) {
		controle.majVue(hero, maxX, maxY, ghost, bonus);
	}

	/**
	 * Regarde si Pacman est en train de manger une gomme ou une super gomme
	 * afin de mettre à jour le score et l'affichage
	 * 
	 * @param hero
	 *            Pacman actuel
	 * @return true si on a mangé une super gomme, false sinon
	 */
	public static boolean canIEatTheGum(Pacman hero) {

		int x = (hero.getCoordX() / length_box) % 19;
		int y = (hero.getCoordY() / length_box) % 22;
		switch (hero.getGo()) {
		case DOWN:
			y = ((hero.getCoordY() + (length_box / 2)) / length_box) % 22;
			break;
		case UP:
			y = ((hero.getCoordY() + (length_box / 2)) / length_box) % 22;
			break;
		case LEFT:
			x = ((hero.getCoordX() + (length_box / 2)) / length_box) % 19;
			break;
		case RIGHT:
			x = ((hero.getCoordX() + (length_box / 2)) / length_box) % 19;
			break;
		default:
		}
		if (labyrinth[x][y] == -5) {
			labyrinth[x][y] = 0;
			gumGum--;
			score += 50;
			return true;
		}
		if (labyrinth[x][y] < 0 && labyrinth[x][y] > -5) {
			switch (labyrinth[x][y]) {
			case -1:
				bonus_eat[0] = true;
				break;
			case -2:
				bonus_eat[1] = true;
				break;
			case -3:
				bonus_eat[2] = true;
				break;
			case -4:
				bonus_eat[3] = true;
				break;
			}
			labyrinth[x][y] = 0;
			score += 100;
		}

		if (labyrinth[x][y] == 1) {
			labyrinth[x][y] = 0;
			gumGum--;
			score += 10;
		}
		return false;
	}

	/**
	 * Met l'état des fantomes en "mangeables" par Pacman
	 * 
	 * @param actual
	 *            Le fantome dont l'état doit changer
	 */
	public static void superPacman(Ghost actual) {
		if (actual.getState() == 0 || actual.getState() == 1)
			actual.setState(1);
	}

	/**
	 * Met l'état des fantomes en "non-mangeables" par Pacman
	 * 
	 * @param actual
	 *            Le fantome dont l'état doit changer
	 */
	public static void normalPacman(Ghost actual) {
		if (actual.getState() == 1)
			actual.setState(0);
	}

	/**
	 * Charge le labyrinthe correspondant au niveau actuel
	 * 
	 * @param controle
	 *            Le controleur référent
	 * @return true si le jeu est fini, false si un niveau doit être chargé
	 */
	public static boolean whatsTheName(Controller controle) {

		switch (file_name) {
		case "new":
			if (stagePlaying != 0) {
				file_name = "Stage" + Integer.toString(stagePlaying) + ".txt";
			} else {
				file_name = "Stage1.txt";
			}
			break;
		case "Stage1.txt":
			file_name = "Stage2.txt";
			break;
		case "Stage2.txt":
			file_name = "Stage3.txt";
			break;
		case "Stage3.txt":
			file_name = "Stage4.txt";
			break;
		case "Stage4.txt":
			file_name = "Stage5.txt";
			break;
		case "Stage5.txt":
			file_name = "Stage6.txt";
			break;
		default:
			controle.endPage();
			runEndPage(controle);
			try {
				saveHighScore(score, file_name);
			} catch (IOException e) {
				System.out.println("Erreur écriture");
			}
			File file;
			if (stagePlaying != 0) {
				file = new File(IOTreatment.findFile(file_name));
			} else {
				file = new File("HighScore.txt");
			}
			try {
				controle.leaderBoard(IOTreatment.extract(file));
				runLeaderBoard(controle);
				return false;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		/* Gestion de la musique en fonction du niveau */
		switch (file_name) {
		case "Stage1.txt":
			controle.changeMusic("music/lvl1.wav");
			break;
		case "Stage2.txt":
			controle.changeMusic("music/lvl2.wav");
			break;
		case "Stage3.txt":
			controle.changeMusic("music/lvl3.wav");
			break;
		case "Stage4.txt":
			controle.changeMusic("music/lvl4.wav");
			break;
		case "Stage5.txt":
			controle.changeMusic("music/lvl5.wav");
			break;
		case "Stage6.txt":
			controle.changeMusic("music/lvl6.wav");
			break;
		}
		return true;
	}

	/**
	 * On teste si le centre de Pacman se trouve dans un des quatre coins de la
	 * hitbox du fantome actual
	 * 
	 * @param actual
	 *            Fantome actuellement testé
	 * @param hero
	 *            Pacman actuel
	 * @return true si il y a rencontre, false sinon
	 */
	public static boolean meetTheFantom(Ghost actual, Pacman hero) {

		// haut-gauche
		if ((hero.getCoordX() + hero.getLength_box() / 2) >= actual.getCoordX()
				&& (hero.getCoordX() + hero.getLength_box() / 2) <= (actual.getCoordX() + actual.getLength_box())
				&& (hero.getCoordY() + hero.getLength_box() / 2) >= actual.getCoordY()
				&& (hero.getCoordY() + hero.getLength_box() / 2) <= (actual.getCoordY() + actual.getLength_box()) ||
		// Haut-droit
				(hero.getCoordX() + hero.getLength_box() / 2) >= actual.getCoordX()
						&& (hero.getCoordX() + hero.getLength_box() / 2) <= (actual.getCoordX()
								+ actual.getLength_box())
						&& (hero.getCoordY() + hero.getLength_box() / 2) >= actual.getCoordY() && (hero.getCoordY()
								+ hero.getLength_box() / 2) <= (actual.getCoordY() + actual.getLength_box())
				||
				// bas-gauche
				(hero.getCoordX() + hero.getLength_box() / 2) >= actual.getCoordX()
						&& (hero.getCoordX() + hero.getLength_box() / 2) <= (actual.getCoordX()
								+ actual.getLength_box())
						&& (hero.getCoordY() + hero.getLength_box() / 2) >= actual.getCoordY() && (hero.getCoordY()
								+ hero.getLength_box() / 2) <= (actual.getCoordY() + actual.getLength_box())
				||
				// bas-droite
				(hero.getCoordX() + hero.getLength_box() / 2) >= actual.getCoordX()
						&& (hero.getCoordX() + hero.getLength_box() / 2) <= (actual.getCoordX()
								+ actual.getLength_box())
						&& (hero.getCoordY() + hero.getLength_box() / 2) >= actual.getCoordY() && (hero.getCoordY()
								+ hero.getLength_box() / 2) <= (actual.getCoordY() + actual.getLength_box())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Fait apparaître les quatre bonus du niveau à 20, 40, 60 et 80% de gommes
	 * mangées
	 */
	public static void putBonus() {
		int cpt = ((totalGumGum - gumGum) * 100 / totalGumGum);
		if (cpt == 80 && bonus_pop[0] == false) {
			labyrinth[6][8] = -1;
			bonus_pop[0] = true;
			// placerbonus
		}
		if (cpt == 60 && bonus_pop[1] == false) {
			labyrinth[12][8] = -2;
			bonus_pop[1] = true;
			// placerbonus
		}
		if (cpt == 40 && bonus_pop[2] == false) {
			labyrinth[6][12] = -3;
			bonus_pop[2] = true;
			// placerbonus
		}
		if (cpt == 20 && bonus_pop[3] == false) {
			labyrinth[12][12] = -4;
			bonus_pop[3] = true;
			// placerbonus
		}
	}

	/**
	 * Lance l'animation de mort du Pacman
	 * 
	 * @param controle
	 *            Le controleur référent
	 * @param hero
	 *            Pacman actuel
	 */
	private static void deadPacman(Controller controle, Pacman hero) {
		hero.isDead();
		hero.resetTimerAnim();
		for (int i = 0; i < 10; i++) {
			hero.refreshTimer_anim();
			controle.refresh(0);
			try {
				Thread.sleep(100);
			} catch (Exception e) {

			}
		}
	}

	/**
	 * Lance la page About et fait défiler les crédits via le controleur
	 * 
	 * @param controle
	 *            Le controleur référent
	 */
	public static void runAboutPage(Controller controle) {
		boolean userAction = false;
		String action;
		controle.aboutPage();
		int i = 0;
		while (!userAction) {
			i++;
			if (i % 3 == 0) {
				controle.refreshAbout();
			}
			action = controle.majStartPage();
			switch (action) {
			case "ReturnAbout":
				userAction = true;
				break;
			default:
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		controle.startPage();
	}

	/**
	 * Lance la page d'Options et enregistre les changements demandés par
	 * l'utilisateur (difficulté des IA , choix du niveau, son, suppression des
	 * highscores)
	 * 
	 * @param controle
	 *            Le controleur référent
	 */
	public static void runOptionPage(Controller controle) {
		boolean userAction = false;
		String action;
		controle.optionPage();
		while (!userAction) {
			controle.refreshOption(Integer.toString(ghostSquad));
			action = controle.majStartPage();
			switch (action) {
			case "ReturnAbout":
				userAction = true;
				break;

			// Nombre de fantomes
			case "-":
				if (ghostSquad > 2) {
					ghostSquad--;
					controle.resetAction();
				}
				break;
			case "+":
				if (ghostSquad < 8) {
					ghostSquad++;
					controle.resetAction();
				}
				break;

			// Suppression des highscores
			case "Delete":
				action = controle.whichDelete();
				switch (action) {
				case "Level 1":
				case "Level 2":
				case "Level 3":
				case "Level 4":
				case "Level 5":
				case "Level 6":
					new File("hs/Stage" + action.charAt(6) + "HS.txt").delete();
					break;
				case "General":
					new File("hs/HighScore.txt").delete();
					break;
				}
				break;

			// Controle du son

			case "On/Off":
				controle.stopMusic();
			default:
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Choix du niveau à jouer

			action = controle.whichLevel();
			switch (action) {

			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
				stagePlaying = Integer.parseInt(action);
				break;
			case "All":
				stagePlaying = 0;
				break;
			}

			// Difficulté des fantomes
			action = controle.whichDifficulty();
			switch (action) {
			case "Easy":
				difficulty = 100;
				break;
			case "Medium":
				difficulty = 50;
				break;
			case "Hard":
				difficulty = 0;
				break;
			}
		}
		controle.startPage();
	}

	/**
	 * Lance la page de fin de partie où il est demandé à l'utilisateur son
	 * pseudonyme
	 * 
	 * @param controle
	 *            Le controleur référent
	 */
	public static void runEndPage(Controller controle) {
		boolean userAction = false;
		String action = "";
		while (!userAction) {
			controle.refreshEnd();
			action = controle.majStartPage();
			if (action.equals("Save")) {
				userAction = true;
				username = controle.getUserName();
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lance la page de Leaderboard pour permettre à l'utilisateur de voir son
	 * score parmi ceux enregistré s'il est plus grand que les dix premiers
	 * enregistrés
	 * 
	 * @param controle
	 *            Le controleur référent
	 */
	public static void runLeaderBoard(Controller controle) {
		boolean userAction = false;
		String action = "";
		while (!userAction) {
			controle.refreshLeaderBoard();
			action = controle.majStartPage();
			if (action.equals("Quit")) {
				System.exit(0);
			}
			if (action.equals("ReturnAbout")) {
				userAction = true;
			}
		}
	}

	/**
	 * Lance la page d'accueil
	 * 
	 * @param controle
	 *            Le controleur référent
	 */
	public static void runStartPage(Controller controle) {
		boolean userAction = false;
		String action;
		controle.changeMusic("music/startpage.wav");
		while (!userAction) {
			controle.refreshStart();
			action = controle.majStartPage();
			switch (action) {
			case "Start":
				userAction = true;
				break;
			case "Options":
				runOptionPage(controle);
				break;
			case "Scoreboard":
				runScoreboardFromStartPage(controle);
			case "About":
				runAboutPage(controle);
				break;
			case "Quit":
				System.exit(0);
			default:
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lance l'affichage des sept scoreboards possibles à partir de la startpage
	 * 
	 * @param controle
	 *            Le controleur référent
	 */
	public static void runScoreboardFromStartPage(Controller controle) {
		String file_name_view = "hs/HighScore.txt";
		boolean userAction = false;
		String action = "";
		// On affiche le scoreboard général par défaut
		try {
			controle.setPanelLeaderBoardFromStartPage(IOTreatment.extract(new File(file_name_view)), "General");
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (!userAction) {
			action = controle.majStartPage();

			/*
			 * A chaque fois que l'utilisateur clique sur NEXT, on change la
			 * matrice envoyé à la vue
			 */

			if (action.equals("Next")) {
				controle.resetAction();
				if (file_name_view.equals("hs/HighScore.txt")) {
					file_name_view = "hs/Stage1HS.txt";
				} else {
					if (file_name_view.equals("hs/Stage6HS.txt")) {
						file_name_view = "hs/HighScore.txt";
					} else {
						char i = file_name_view.charAt(8);
						i++;
						file_name_view = file_name_view.substring(0, 8) + i + "HS.txt";
						System.out.println(file_name_view);
					}
				}
				try {
					if (file_name_view.equals("hs/HighScore.txt")) {
						controle.setTabLeaderBoard(IOTreatment.extract(new File(file_name_view)), "General");
					} else {
						controle.setTabLeaderBoard(IOTreatment.extract(new File(file_name_view)),
								file_name_view.substring(3, 8) + " " + file_name_view.charAt(8));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (action.equals("ReturnAbout")) {
				userAction = true;
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Enregistre les meilleurs scores dans un fichier
	 * 
	 * @param score
	 *            Le score actuel à enregistrer
	 * @param file_name
	 *            Le nom du fichier correspondant au niveau joué
	 * @throws IOException
	 *             Si le fichier n'a pas été trouvé
	 */
	public static void saveHighScore(int score, String file_name) throws IOException {
		if (username.equals("")) {
			username = "ABC";
		}
		username = username.replaceAll(" ", "");
		String[][] current_score = new String[10][2];
		File current_file;
		if (stagePlaying != 0) {
			current_file = new File(IOTreatment.findFile(file_name));
		} else {
			current_file = new File("hs/HighScore.txt");
		}
		current_score = IOTreatment.extract(current_file);
		if (Integer.parseInt(current_score[current_score.length - 1][current_score[0].length - 1]) < score) {
			int count = 0;
			while (Integer.parseInt(current_score[count][1]) > score) {
				count++;
			}
			IOTreatment.put(current_score, count, score, username, current_file);
		}
	}

	/**
	 * Crée un tableau de fantome de la taille demandée par l'utilisateur
	 * 
	 * @return Le tableau de fantome
	 */
	public static Ghost[] createGhost() {
		Ghost[] ghost = new Ghost[ghostSquad];
		ghost[0] = new Ghost(252, 224, 0, "Blinky", deplacement, length_box, difficulty, 0);
		ghost[1] = new Ghost(280, 280, 0, "Pinky", deplacement, length_box, difficulty, 1000);
		if (ghostSquad > 2) {
			ghost[2] = new Ghost(252, 280, 0, "Inky", deplacement, length_box, difficulty, 0);
		}
		if (ghostSquad > 3) {
			ghost[3] = new Ghost(224, 280, 0, "Clyde", deplacement, length_box, difficulty, 500);
		}
		if (ghostSquad > 4) {
			ghost[4] = new Ghost(280, 224, 0, "Blinky", deplacement, length_box, difficulty, 0);
		}
		if (ghostSquad > 5) {
			ghost[5] = new Ghost(224, 224, 0, "Pinky", deplacement, length_box, difficulty, 0);
		}
		if (ghostSquad > 6) {
			ghost[6] = new Ghost(196, 224, 0, "Inky", deplacement, length_box, difficulty, 0);
		}
		if (ghostSquad > 7) {
			ghost[7] = new Ghost(308, 224, 0, "Clyde", deplacement, length_box, difficulty, 0);
		}
		return ghost;
	}

	/**
	 * Toute l'algorithmique qui tourne pour chaque jour de jeu, avec
	 * l'actualisation du placement des fantomes et de pacman, l'apparition de
	 * bonus, le détection de collision avec des murs ou entre les entités, la
	 * demande au controleur de mise à jour graphique, ...
	 * 
	 * @param controle
	 *            Le controleur référent
	 */
	public static void run(Controller controle) {
		controle.startPage();
		runStartPage(controle);

		bonus_pop = new boolean[4];
		bonus_eat = new boolean[4];
		for (int i = 0; i < bonus_pop.length; i++) {
			bonus_pop[i] = false;
			bonus_eat[i] = false;
		}

		// Déclaration des fantomes
		Ghost[] ghost;

		// Déclaration du bonus de combo
		int combo = 1;

		// Déclaration du Pacman
		Pacman hero = new Pacman();

		// Vrai si pacman se fait attraper
		boolean catchMeIfYouCan = false;

		boolean loop = true;

		// Vrai si la partie est gagnée
		boolean win = false;
		loop = whatsTheName(controle);
		fillMyTab();

		// On initialise le controller
		controle.setHero(hero);
		controle.startGame();

		hero.setLife(3);

		// On tourne tant que l'utilisateur n'a pas gagné ou perdu
		while (loop) {
			// Init fichier
			if (win) {
				for (int i = 0; i < bonus_pop.length; i++) {
					bonus_pop[i] = false;
					bonus_eat[i] = false;
				}
				loop = whatsTheName(controle);
				win = false;
				// Init labyrinth
				fillMyTab();
			}

			catchMeIfYouCan = false;

			// Init pacman
			hero.reset(252, 448, Controller.Direction.UP, Controller.Direction.UP, deplacement, length_box);

			// Init fantomes
			ghost = createGhost();

			updateVue(controle, hero, ghost, bonus_eat);
			// Attente de 3 secondes avant le début de chaque partie
			try {
				controle.refresh(3);
				Thread.sleep(1000);
				controle.refresh(2);
				Thread.sleep(1000);
				controle.refresh(1);
				Thread.sleep(1000);
				controle.refresh(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Remet par défaut la direction de pacman vers le haut
			controle.resetDirection();

			/*
			 * Tant que Pacman ne se fait pas attraper et qu'il reste des
			 * gommes, on tourne
			 */
			while (gumGum > 0 && !catchMeIfYouCan) {

				// Test s'il faut placer un bonus
				putBonus();

				controle.tellMeTheWayToGoPlease();
				if (hero.getToGo() == Controller.Direction.SPACE) {
					hero.setToGo(hero.getGo());
					loop = !pause(controle, hero, ghost);
					if (!loop) {
						catchMeIfYouCan = true;
						hero.refreshTimer_anim();
					}
				}
				// Deplacement de Pacman
				if (hero.getToGo() != hero.getGo()) {
					if (hero.canIGoHere(hero.getToGo()) == true) {
						hero.setGo(hero.getToGo());
					}
				}
				if (hero.canIGoHere(hero.getGo())) {
					hero.actualize_XY();
				}

				// Test si on mange une gomme
				if (canIEatTheGum(hero)) {
					hero.reset_Timer_superPacman();
					for (int i = 0; i < ghostSquad; i++) {
						superPacman(ghost[i]);
					}
				}

				/*
				 * Si on arrive à la fin du compteur, on repasse les fantomes en
				 * mode normal
				 */
				if (hero.getTimer_superPacman() == 875) {
					hero.reset_Timer_superPacman();
					combo = 1;
					for (int i = 0; i < ghostSquad; i++) {
						normalPacman(ghost[i]);
					}
				}
				hero.refreshTimer_anim();

				// Deplacement des fantomes en fonction de leur état

				for (int i = 0; i < ghostSquad; i++) {
					if (ghost[i].getState() == 2) {
						ghost[i].returnToTheBase();
					} else {
						if ((ghost[i].getState() == 1 && (ghost[i].getGame_lap() % 2) == 0)
								|| ghost[i].getState() == 0) {
							ghost[i].deplaceTheGhost(hero.getCoordX(), hero.getCoordY(), hero.getGo());
						} else {
							ghost[i].anotherLap();
						}
					}
				}

				controle.refresh(0);

				/*
				 * Si les fantomes sont en mode "mangeables", on incrémente le
				 * compteur
				 */
				boolean eatable = false;
				for (int i = 0; i < ghostSquad; i++) {
					if (ghost[i].getState() == 1) {
						eatable = true;
					}
				}
				if (eatable)
					hero.increment_Timer_superPacman();

				/*
				 * On gère les interaction entre pacman et un fantome s'il y en
				 * a un, et en fonction de l'état du fantome
				 */

				for (int i = 0; i < ghostSquad; i++) {
					if (meetTheFantom(ghost[i], hero)) {
						if (ghost[i].getState() == 1) {
							score += combo * 200;
							combo *= 2;
							ghost[i].setState(2);
						} else {
							if (ghost[i].getState() == 0)
								catchMeIfYouCan = true;
						}
					}
				}
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			/*
			 * gumGum est à 0 lorsque toutes les gommes du niveau ont été
			 * mangées et que celui-ci a donc été gagné
			 */
			if (gumGum == 0) {
				win = true;
				/* Si on ne jouait qu'un stage, on lance la EndPage */
				if (stagePlaying != 0) {
					controle.endPage();
					runEndPage(controle);
					try {
						saveHighScore(score, file_name);
					} catch (IOException e) {
						System.out.println("Erreur IO");
					}
					File file;
					if (stagePlaying != 0) {
						file = new File(IOTreatment.findFile(file_name));
					} else {
						file = new File("hs/HighScore.txt");
					}
					try {
						controle.leaderBoard(IOTreatment.extract(file));
						runLeaderBoard(controle);
						loop = false;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				if (loop){
					hero.looseLife();
					deadPacman(controle, hero);
				}
			}
			if (hero.getLife() <= 0) {
				controle.endPage();
				runEndPage(controle);
				try {
					saveHighScore(score, file_name);
				} catch (IOException e) {
					System.out.println("Erreur d'écriture");
				}

				File file;
				if (stagePlaying != 0) {
					file = new File(IOTreatment.findFile(file_name));
				} else {
					file = new File("hs/HighScore.txt");
				}
				try {
					controle.leaderBoard(IOTreatment.extract(file));
					runLeaderBoard(controle);
					loop = false;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	/**
	 * Lance run
	 * 
	 * @param args
	 *            Les arguments du programme
	 */
	public static void main(String[] args) {
		Controller controle = new Controller(maxX, maxY);
		while (true) {
			score = 0;
			file_name = "new";
			stagePlaying = 0;
			controle.resetVue();
			run(controle);
		}
	}
}
