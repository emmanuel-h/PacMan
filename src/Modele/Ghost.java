package Modele;

import Controller.*;

/**
 * Permet d'instancier des fantomes
 * 
 * @author Duchene Herrmann Rety
 *
 */
public class Ghost {

	/**
	 * Coordonnée x du fantome
	 */
	private int coordX;

	/**
	 * Coordonnée y du fantome
	 */
	private int coordY;

	/**
	 * Etat du fantome : 0 - "Non mangeable" par Pacman 1 - "Mangeable" par
	 * Pacman 2 - Forme d'yeux retournant à la base
	 */
	private int state;

	/**
	 * Nom du fantome
	 */
	private String name;

	/**
	 * Direction actuelle du fantome
	 */
	private Controller.Direction go;

	/**
	 * Direction opposée de la directio nactuelle du fantome
	 */
	private Controller.Direction goToTheOtherSide;

	/**
	 * Noùbre de pixels parcourus à chaque tour de boucle
	 */
	private int deplacement;

	/**
	 * Taille de la hitbox
	 */
	private int length_box;

	/**
	 * Tour de jeu actuel
	 */
	private int game_lap;

	/**
	 * Pourcentage de random dans les deplacements des fantomes
	 */
	private int difficulty;

	/**
	 * Temps d'attente avant de bouger
	 */
	private int wait;

	/**
	 * Constructeur
	 * 
	 * @param _coordX
	 *            La coordonnée X du fantome
	 * @param _coordY
	 *            La coordonnée Y du fantome
	 * @param _state
	 *            L'état initial à donner au fantome
	 * @param _name
	 *            Le nom du fantome
	 * @param _deplacement
	 *            Le nombre de pixel à avancer à chaque tour
	 * @param _length_box
	 *            Taille de la hitbox
	 * @param _difficulty
	 *            Difficulté du fantome en pourcentage de déplacement aléatoire
	 * @param _wait
	 *            Temps d'attente avant de faire partir le fantome
	 */
	public Ghost(int _coordX, int _coordY, int _state, String _name, int _deplacement, int _length_box, int _difficulty,
			int _wait) {
		this.coordX = _coordX;
		this.coordY = _coordY;
		this.state = _state;
		this.name = _name;
		this.deplacement = _deplacement;
		this.length_box = _length_box;
		this.go = Controller.Direction.UP;
		this.goToTheOtherSide = theOtherSide(go);
		this.game_lap = 0;
		this.difficulty = _difficulty;
		this.wait = _wait;
	}

	/**
	 * Déplace le fantome selon son nom
	 * 
	 * @param coordXPacman
	 *            Coordonnée X actuelle de Pacman
	 * @param coordYPacman
	 *            Coordonnée Y actuelle de Pacman
	 * @param goPacman
	 *            Direction dans laquelle a Pacman
	 */
	public void deplaceTheGhost(int coordXPacman, int coordYPacman, Controller.Direction goPacman) {
		game_lap++;
		switch (name) {
		case "Blinky":
			deplaceBlinky(coordXPacman, coordYPacman, false);
			break;
		case "Pinky":
			if (game_lap > wait) {
				deplacePinky(coordXPacman, coordYPacman, goPacman);
			}
			break;
		case "Inky":
			deplaceInky(coordXPacman, coordYPacman);
			break;
		case "Clyde":
			if (game_lap > wait) {
				deplaceClyde();
			}
			break;
		default:
		}
	}

	/**
	 * Bouge dans une direction aléatoire
	 */
	private void moveToARandomWay() {
		Controller.Direction IWantToGoHere;
		do {
			IWantToGoHere = getRandomDirection();
		} while (!canIGoHere(IWantToGoHere) || IWantToGoHere == goToTheOtherSide);
		go = IWantToGoHere;
		goToTheOtherSide = theOtherSide(IWantToGoHere);
		move();
	}

	/**
	 * Calcule la direction opposée à actualGo
	 * 
	 * @param actualGo
	 *            Direction à tester
	 * @return la direction inverse de celle à tester
	 */
	private Controller.Direction theOtherSide(Controller.Direction actualGo) {
		switch (actualGo) {
		case UP:
			return Controller.Direction.DOWN;
		case DOWN:
			return Controller.Direction.UP;
		case LEFT:
			return Controller.Direction.RIGHT;
		case RIGHT:
			return Controller.Direction.LEFT;
		default:
			return Controller.Direction.UNKNOW;
		}
	}

	/**
	 * Donne une direction aléatoire
	 * 
	 * @return la direction trouvée aléatoirement
	 */
	private Controller.Direction getRandomDirection() {
		int a = (int) (Math.random() * 4);
		switch (a) {
		case 0:
			return Controller.Direction.UP;
		case 1:
			return Controller.Direction.DOWN;
		case 2:
			return Controller.Direction.LEFT;
		case 3:
			return Controller.Direction.RIGHT;
		default:
			return Controller.Direction.UNKNOW;
		}
	}

	/**
	 * Clyde se déplace de manière aléatoire sur la carte
	 */
	private void deplaceClyde() {
		// départ
		if (coordX == 224 && coordY == 280) {
			go = Controller.Direction.RIGHT;
			goToTheOtherSide = theOtherSide(go);
			move();
		} else {
			if (coordX == 252 && coordY == 280) {
				go = Controller.Direction.UP;
				goToTheOtherSide = theOtherSide(go);
				move();
			} else {
				// Il suffit de le faire aller de manière aléatoire sur
				// n'importe quelle
				// case
				// qui n'est pas un mur ou une marche arrière
				moveToARandomWay();
			}
		}
	}

	/**
	 * Bouge le fantome en fonctio nde la direction actuelle
	 */
	public void move() {
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
	 * Regarde si la fantome va dans un mur
	 * 
	 * @param tempX
	 *            Coordonnée X à tester
	 * @param tempY
	 *            Coordonnée Y à tester
	 * @param goTest
	 *            Direction à tester
	 * @return true si le fantome ne va pas dans un mur
	 */
	public boolean letMeDoTheSmartThings(int tempX, int tempY, Controller.Direction goTest) {
		tempX = (tempX / length_box) % 19;
		tempY = (tempY / length_box) % 22;
		// Cas de départ quand le fantome doit sortir
		if ((Modele.labyrinth[tempX][tempY] == 24) && (goTest == Controller.Direction.UP)) {
			return true;
		}
		if (Modele.labyrinth[tempX][tempY] > 1)
			return false;
		else
			return true;
	}

	/**
	 * Regarde si le fantome peut aller dans la direction goTest
	 * 
	 * @param goTest
	 *            Direction à tester
	 * @return true si le fantome peut aller dans cette direction
	 */
	private boolean canIGoHere(Controller.Direction goTest) {
		switch (goTest) {
		case UP:
			return (letMeDoTheSmartThings(coordX + length_box - deplacement, coordY - deplacement, goTest)
					&& letMeDoTheSmartThings(coordX, coordY - deplacement, goTest));
		case DOWN:
			return (letMeDoTheSmartThings(coordX, coordY + length_box, goTest)
					&& letMeDoTheSmartThings(coordX + length_box - deplacement, coordY + length_box, goTest));
		case LEFT:
			return letMeDoTheSmartThings(coordX - deplacement, coordY, goTest)
					&& letMeDoTheSmartThings(coordX - deplacement, coordY + length_box - deplacement, goTest);
		case RIGHT:
			return letMeDoTheSmartThings(coordX + length_box, coordY + length_box - deplacement, goTest)
					&& letMeDoTheSmartThings(coordX + length_box, coordY, goTest);
		default:
		}
		return false;
	}

	/**
	 * Vise l'endroit où va se trouver Pacman
	 * 
	 * @param coordXPacman
	 *            Coordonnée X du Pacman
	 * @param coordYPacman
	 *            Coordonnée Y du Pacman
	 * @param goPacman
	 *            Direction actuelle du Pacman
	 */
	private void deplacePinky(int coordXPacman, int coordYPacman, Controller.Direction goPacman) {
		// Cas de départ
		if (coordX <= 280 && coordX > 252 && coordY == 252) {
			go = Controller.Direction.LEFT;
			goToTheOtherSide = theOtherSide(go);
			move();
		} else {
			if (coordX == 252 && coordY <= 280 && coordY >= 252) {
				go = Controller.Direction.UP;
				goToTheOtherSide = theOtherSide(go);
				move();
			} else {
				int random = (int) (Math.random() * 101);
				if (random < difficulty) {
					moveToARandomWay();
				} else {
					coordXPacman = (coordXPacman / length_box) % 19;
					coordYPacman = (coordYPacman / length_box) % 22;
					boolean bordure = false;
					while ((Modele.labyrinth[coordXPacman][coordYPacman] < 2) && !bordure) {
						switch (goPacman) {
						case UP:
							coordYPacman = (coordYPacman - 1) % 22;
							if (Math.abs(coordYPacman) != (coordYPacman)) {
								coordYPacman = 0;
								bordure = true;
							}
							break;
						case DOWN:
							coordYPacman = (coordYPacman + 1) % 22;
							break;
						case LEFT:
							coordXPacman = (coordXPacman - 1) % 19;
							if (Math.abs(coordXPacman) != (coordXPacman) % 19) {
								coordXPacman = 0;
								bordure = true;
							}
							break;
						case RIGHT:
							coordXPacman = (coordXPacman + 1) % 19;
							break;
						default:
						}
					}
					coordXPacman = coordXPacman * length_box;
					coordYPacman = coordYPacman * length_box;
					deplaceBlinky(coordXPacman, coordYPacman, false);
				}
			}
		}
	}

	/**
	 * Suit Pacman 75% du temps et le fuit sinon
	 * 
	 * @param coordXPacman
	 *            Coordonnée X du Pacman
	 * @param coordYPacman
	 *            Coordonnée Y du Pacman
	 */
	private void deplaceInky(int coordXPacman, int coordYPacman) {
		if (coordX == 252 && coordY <= 280 && coordY >= 252) {
			go = Controller.Direction.UP;
			goToTheOtherSide = theOtherSide(go);
			move();
		} else {
			deplaceBlinky(coordXPacman, coordYPacman, true);
		}

	}

	/**
	 * Retourne la distance de Manhattan entre deux coordonnées
	 * 
	 * @param coordXG
	 *            Coordonnée X du Fantome
	 * @param coordYG
	 *            Coordonnée Y du Fantome
	 * @param coordXP
	 *            Coordonnée X du Pacman
	 * @param coordYP
	 *            Coordonnée Y du Pacman
	 * @return la distance de Manhattan
	 */
	private int distance(int coordXG, int coordYG, int coordXP, int coordYP) {
		return Math.abs(coordXP - coordXG) + Math.abs(coordYP - coordYG);
	}

	/**
	 * Suit Pacman
	 * 
	 * @param coordXPacman
	 *            Coordonnée X de Pacman
	 * @param coordYPacman
	 *            Coordonnée Y de Pacman
	 * @param helloIAmInky
	 *            La méthode possède du code en commun avec le déplacement
	 *            d'Inky, permet de savoir si c'est Inky qui l'utilise ou Blinky
	 */
	private void deplaceBlinky(int coordXPacman, int coordYPacman, boolean helloIAmInky) {
		if (coordX == 252 && coordY <= 280 && coordY >= 252) {
			go = Controller.Direction.UP;
			goToTheOtherSide = theOtherSide(go);
			move();
		} else {
			int r = (int) (Math.random() * 101);
			if (r < difficulty) {
				moveToARandomWay();
			} else {
				int left = 0;
				int right = 0;
				int up = 0;
				int down = 0;

				if (canIGoHere(Controller.Direction.LEFT) && goToTheOtherSide != Controller.Direction.LEFT) {
					left = 1;
				}
				if (canIGoHere(Controller.Direction.RIGHT) && goToTheOtherSide != Controller.Direction.RIGHT) {
					right = 1;
				}
				if (canIGoHere(Controller.Direction.UP) && goToTheOtherSide != Controller.Direction.UP) {
					up = 1;
				}
				if (canIGoHere(Controller.Direction.DOWN) && goToTheOtherSide != Controller.Direction.DOWN) {
					down = 1;
				}
				int min_distance = 1000;

				// si c'est Inky, il y a une chance sur 4 pour qu'il décide de
				// partir
				// dans la direction opposée de pacman
				if (helloIAmInky) {
					int random = (int) (Math.random() * 4);
					if (random == 0) {
						helloIAmInky = true;
						min_distance = 0;
					} else {
						helloIAmInky = false;
					}
				}

				Controller.Direction toGo = Controller.Direction.UNKNOW;
				boolean whereWillIGo;
				if (left == 1) {
					if (!helloIAmInky) {
						whereWillIGo = min_distance > distance(coordX - deplacement, coordY, coordXPacman,
								coordYPacman);
					} else {
						whereWillIGo = min_distance < distance(coordX - deplacement, coordY, coordXPacman,
								coordYPacman);
					}
					if (whereWillIGo) {
						min_distance = distance(coordX - deplacement, coordY, coordXPacman, coordYPacman);
						toGo = Controller.Direction.LEFT;
					}
				}
				if (right == 1) {
					if (!helloIAmInky) {
						whereWillIGo = min_distance > distance(coordX + deplacement, coordY, coordXPacman,
								coordYPacman);
					} else {
						whereWillIGo = min_distance < distance(coordX + deplacement, coordY, coordXPacman,
								coordYPacman);
					}
					if (whereWillIGo) {
						min_distance = distance(coordX + deplacement, coordY, coordXPacman, coordYPacman);
						toGo = Controller.Direction.RIGHT;
					}
				}
				if (up == 1) {
					if (!helloIAmInky) {
						whereWillIGo = min_distance > distance(coordX, coordY - deplacement, coordXPacman,
								coordYPacman);
					} else {
						whereWillIGo = min_distance < distance(coordX, coordY - deplacement, coordXPacman,
								coordYPacman);
					}
					if (whereWillIGo) {
						min_distance = distance(coordX, coordY - deplacement, coordXPacman, coordYPacman);
						toGo = Controller.Direction.UP;
					}
				}
				if (down == 1) {
					if (!helloIAmInky) {
						whereWillIGo = min_distance > distance(coordX, coordY + deplacement, coordXPacman,
								coordYPacman);
					} else {
						whereWillIGo = min_distance < distance(coordX, coordY + deplacement, coordXPacman,
								coordYPacman);
					}
					if (whereWillIGo) {
						min_distance = distance(coordX, coordY + deplacement, coordXPacman, coordYPacman);
						toGo = Controller.Direction.DOWN;
					}
				}
				go = toGo;
				goToTheOtherSide = theOtherSide(go);
				move();
			}
		}
	}

	/**
	 * Si le fantome se trouve en état 2, cette méthode se substitue à la
	 * méthode de déplacement habituelle du fantome pour le faire revenir à sa
	 * case de départ
	 */
	public void returnToTheBase() {
		int left = 0;
		int right = 0;
		int up = 0;
		int down = 0;

		int iComeFromX = 252;
		int iComeFromY = 224;

		if (coordX == 252 && coordY >= 224 && coordY < 280) {
			this.go = Controller.Direction.DOWN;
			this.goToTheOtherSide = theOtherSide(go);
			move();
		} else {
			if (coordX == 252 && coordY == 280) {
				this.state = 0;
				this.go = Controller.Direction.UP;
				this.goToTheOtherSide = theOtherSide(go);

			} else {

				if (canIGoHere(Controller.Direction.LEFT) && goToTheOtherSide != Controller.Direction.LEFT) {
					left = 1;
				}
				if (canIGoHere(Controller.Direction.RIGHT) && goToTheOtherSide != Controller.Direction.RIGHT) {
					right = 1;
				}
				if (canIGoHere(Controller.Direction.UP) && goToTheOtherSide != Controller.Direction.UP) {
					up = 1;
				}
				if (canIGoHere(Controller.Direction.DOWN) && goToTheOtherSide != Controller.Direction.DOWN) {
					down = 1;
				}
				int min_distance = 1000;

				/*
				 * On calcule grace à la distance de Manhattan un chemin pour
				 * retourner relativement rapidement au point de spawn
				 */
				Controller.Direction toGo = Controller.Direction.UNKNOW;
				boolean whereWillIGo;
				if (left == 1) {
					whereWillIGo = min_distance > distance(coordX - deplacement, coordY, iComeFromX, iComeFromY);
					if (whereWillIGo) {
						min_distance = distance(coordX - deplacement, coordY, iComeFromX, iComeFromY);
						toGo = Controller.Direction.LEFT;
					}
				}
				if (right == 1) {
					whereWillIGo = min_distance > distance(coordX + deplacement, coordY, iComeFromX, iComeFromY);
					if (whereWillIGo) {
						min_distance = distance(coordX + deplacement, coordY, iComeFromX, iComeFromY);
						toGo = Controller.Direction.RIGHT;
					}
				}
				if (up == 1) {
					whereWillIGo = min_distance > distance(coordX, coordY - deplacement, iComeFromX, iComeFromY);
					if (whereWillIGo) {
						min_distance = distance(coordX, coordY - deplacement, iComeFromX, iComeFromY);
						toGo = Controller.Direction.UP;
					}
				}
				if (down == 1) {
					whereWillIGo = min_distance > distance(coordX, coordY + deplacement, iComeFromX, iComeFromY);
					if (whereWillIGo) {
						min_distance = distance(coordX, coordY + deplacement, iComeFromX, iComeFromY);
						toGo = Controller.Direction.DOWN;
					}
				}
				go = toGo;
				goToTheOtherSide = theOtherSide(go);
			}
			move();
		}
	}

	/**
	 * Renvoie l'état du fantome
	 * 
	 * @return l'état actuel du fantome
	 */
	public int getState() {
		return state;
	}

	/**
	 * Change l'état du fantome
	 * 
	 * @param _state
	 *            le nouvel état
	 */
	public void setState(int _state) {
		this.state = _state;
	}

	/**
	 * Renvoie la coordonnée X du fantome
	 * 
	 * @return la coordonnée X
	 */
	public int getCoordX() {
		return coordX;
	}

	/**
	 * Change la coordonnée X du fantome
	 * 
	 * @param coordX
	 *            la coordonnée X
	 */
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	/**
	 * Renvoie la coordonnée Y du fantome
	 * 
	 * @return la coordonnée Y
	 */
	public int getCoordY() {
		return coordY;
	}

	/**
	 * Change la coordonnée Y du fantome
	 * 
	 * @param coordY
	 *            la coordonnée Y
	 */
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	/**
	 * Renvoie le nom du fantome
	 */
	public String toString() {
		return this.name;
	}

	/**
	 * Renvoie la direction actuelle du fantome
	 * 
	 * @return la direction actuelle
	 */
	public Controller.Direction getGo() {
		return go;
	}

	/**
	 * Renvoie la taille de la hitbox
	 * 
	 * @return la taille de la hitbox
	 */
	public int getLength_box() {
		return length_box;
	}

	/**
	 * Renvoie le comtpeur de tour
	 * 
	 * @return le compteur de tour
	 */
	public int getGame_lap() {
		return game_lap;
	}

	/**
	 * Incrémente le compteur de tour
	 */
	public void anotherLap() {
		this.game_lap++;
	}
}
