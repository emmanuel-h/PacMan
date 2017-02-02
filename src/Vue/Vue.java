package Vue;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import Modele.Ghost;
import Modele.Pacman;

/**
 * Vue est la classe centralisant toutes données visibles par l'utilisateur
 * (panneau, input) du Pacman
 * 
 * @author Duchene Herrmann Rety
 *
 */

@SuppressWarnings("serial")
public class Vue extends JFrame {

	/**
	 * Panneau qui gère le jeu
	 */
	PanelInGame panIG;

	/**
	 * Panneau qui gère le menu
	 */
	PanelStart panS;

	/**
	 * Panneau qui gère les crédits
	 */
	PanelAbout panA;

	/**
	 * Panneau qui gère les options
	 */
	PanelOption panO;

	/**
	 * Panneau qui gère la fin de jeu
	 */
	PanelEndGame panEG;

	/**
	 * Panneau qui gère le leaderboard
	 */
	PanelLeaderBoard panLB;

	/**
	 * lisetener du clavier et de la souris en jeu
	 */
	KeyboardAndMouseMovementsInputs controle_kammi;

	/**
	 * listener de la souris sur des boutons
	 */
	MouseClic controle_c;

	/**
	 * bouton pour retourner au menu
	 */
	Bouton returnAbout;
	Bouton quit;
	Bouton back;
	/**
	 * champs de texte
	 */
	JTextField name;

	/**
	 * choix des niveaux à jouer
	 */
	JComboBox<String> comboLevel;

	/**
	 * choix des fichiers de scores à effacer
	 */
	JComboBox<String> comboDelete;

	/**
	 * choix de la difficulté des fantomes
	 */
	JComboBox<String> comboDifficulty;

	/**
	 * Constructeur de la Vue création de la fenêtre et des panneaux
	 * 
	 * @param maxX
	 *            largeur de la fenêtre
	 * @param maxY
	 *            hauteur de la fenêtre
	 *
	 */
	public Vue(int maxX, int maxY) {
		this.setTitle("Pac-Man");
		this.setSize(maxX, maxY);
		create();
	}

	/**
	 * Initialisation des panneaux et des boutons d'option création du bouton
	 * retourn to menu, de choix des niveaux et des textes correspondants
	 * 
	 */
	public void create() {
		panIG = new PanelInGame();
		panS = new PanelStart();
		panA = new PanelAbout(this.getHeight());
		panO = new PanelOption();
		panEG = new PanelEndGame();
		panLB = new PanelLeaderBoard();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		returnAbout = new Bouton("Menu");
		quit = new Bouton("Quit");
		back = new Bouton("Back");
		name = new JTextField(3);
		name.setText("");
		String[] choices_level = { "All", "1", "2", "3", "4", "5", "6" };
		String[] choices_delete = { "None", "General", "Level 1", "Level 2", "Level 3", "Level 4", "Level 5",
				"Level 6" };
		String[] choices_difficulty = { "Easy", "Medium", "Hard" };
		comboDifficulty = new JComboBox<String>(choices_difficulty);
		comboLevel = new JComboBox<String>(choices_level);
		comboDelete = new JComboBox<String>(choices_delete);
	}

	/**
	 * Met à jour la vue pour afficher le panel Leaderboard, qui affiche les
	 * highscores du niveau courant à la fin d'une run
	 * 
	 * @param _controle_c
	 *            Le listener correspondant
	 * @param tab
	 *            Le tableau de highscore à afficher
	 */
	public void setPanelLeaderBoard(MouseClic _controle_c, String[][] tab) {
		panLB = new PanelLeaderBoard();
		controle_c = _controle_c;
		panLB.setLayout(null);
		this.setContentPane(panLB);
		this.revalidate();
		panLB.setTab(tab);
		returnAbout.setBounds(3, 570, 80, 50);
		returnAbout.addActionListener(controle_c);
		panLB.add(returnAbout);
		returnAbout.setVisible(true);
		quit.setBounds(560, 570, 80, 50);
		quit.addActionListener(controle_c);
		quit.setVisible(true);
		panLB.add(quit);
	}

	/**
	 * Met à jour la vue en affichant le leaderboard à partir de la startpage,
	 * et donc en proposant de naviguer entre les highscore de tous les niveaux
	 * 
	 * @param _controle_c
	 *            le listener correspondant
	 * @param tab
	 *            Le tableau de highscore courant à afficher
	 * @param level
	 *            Le nom du niveau courant
	 */
	public void setPanelLeaderBoardFromStartPage(MouseClic _controle_c, String[][] tab, String level) {
		controle_c = _controle_c;
		panLB.setLayout(null);
		this.setContentPane(panLB);
		this.revalidate();
		panLB.setTab(tab);
		panLB.setLevel(level);
		returnAbout.setBounds(560, 570, 80, 50);
		returnAbout.addActionListener(controle_c);
		panLB.add(returnAbout);
		Bouton next = new Bouton("Next");
		next.setBounds(10, 570, 80, 50);
		next.addActionListener(controle_c);
		panLB.add(next);

	}

	/**
	 * Met à jour la Vue pour afficher le panneau PanelEndGame et utiliser le
	 * bon controleur ajout du bouton save et des textes correspondants
	 * 
	 * @param _controle_c
	 *            controleur de la souris
	 * 
	 */
	public void setPanelEndGame(MouseClic _controle_c) {
		controle_c = _controle_c;
		panEG.setLayout(null);
		this.setContentPane(panEG);
		this.revalidate();
		Bouton save = new Bouton("Save");
		save.setBounds(560, 570, 80, 50);
		save.addActionListener(controle_c);
		panEG.add(save);
		name = new JTextField(8);
		name.setPreferredSize(name.getPreferredSize());
		Font font = new Font("Courier", Font.BOLD, 20);
		name.setFont(font);
		name.setHorizontalAlignment(JTextField.CENTER);
		panEG.add(name);
		name.setBounds(250, 260, 135, 50);
		name.addActionListener(controle_c);

	}

	/**
	 * Met à jour la Vue pour afficher le panneau PanelInGame et utiliser le bon
	 * controleur
	 * 
	 * @param _controle
	 *            controleur des mouvements du pacman
	 * @param mc
	 *            Le listener correspondant
	 * 
	 */
	public void setPanelIngame(KeyboardAndMouseMovementsInputs _controle, MouseClic mc) {
		controle_c = mc;
		controle_kammi = _controle;
		panIG.setLayout(null);
		this.setContentPane(panIG);
		this.revalidate();

		panIG.add(returnAbout);
		returnAbout.setBounds(213, 330, 110, 50);
		returnAbout.setVisible(false);
		returnAbout.addActionListener(controle_c);

		panIG.add(quit);
		quit.setBounds(213, 400, 110, 50);
		quit.setVisible(false);
		quit.addActionListener(controle_c);

		panIG.add(back);
		back.setBounds(213, 260, 110, 50);
		back.setVisible(false);
		back.addActionListener(controle_c);

		addMouseListener(controle_kammi);
		addKeyListener(controle_kammi);
		requestFocus();

	}

	/**
	 * Met à jour la Vue pour afficher le panneau PanelAbout et utiliser le bon
	 * controleur ajout du bouton retournAbout
	 * 
	 * @param _controle
	 *            controleur de la souris
	 * 
	 */
	public void setPanelAbout(MouseClic _controle) {
		this.panA = new PanelAbout(this.getHeight());
		controle_c = _controle;
		panA.setLayout(null);
		this.setContentPane(panA);
		this.revalidate();
		panA.add(returnAbout);
		returnAbout.setBounds(560, 570, 80, 50);
		returnAbout.addActionListener(controle_c);
	}

	/**
	 * Met à jour la Vue pour afficher le panneau PanelOption et utiliser le bon
	 * controleur ajout du bouton retournAbout et de ceux modifiant la
	 * difficulté, la musique et le leaderBoard
	 * 
	 * @param _controle
	 *            controleur de la souris
	 * @param stopstartmusic
	 *            boolean pour l'activation/désactivation de la musique
	 */
	public void setPanelOption(MouseClic _controle, boolean stopstartmusic) {
		controle_c = _controle;
		panO.setLayout(null);
		this.setContentPane(panO);
		this.revalidate();

		panO.add(returnAbout);
		returnAbout.setBounds(560, 570, 80, 50);
		returnAbout.addActionListener(controle_c);

		JToggleButton music = new JToggleButton("On/Off", !stopstartmusic);
		panO.add(music);
		music.setBounds(350, 165, 80, 25);
		music.addActionListener(controle_c);

		JButton delete = new JButton("Delete");
		panO.add(delete);
		delete.setBounds(490, 115, 80, 25);
		delete.addActionListener(controle_c);

		comboDifficulty.addActionListener(controle_c);
		comboDifficulty.setSelectedIndex(1);
		comboDifficulty.setBounds(350, 15, 80, 25);
		panO.add(comboDifficulty);

		comboLevel.addActionListener(controle_c);
		comboLevel.setBounds(350, 65, 80, 25);
		panO.add(comboLevel);

		comboDelete.addActionListener(controle_c);
		comboDelete.setBounds(350, 115, 80, 25);
		panO.add(comboDelete);

		JButton plus = new Bouton("+");
		panO.add(plus);
		plus.setBounds(410, 215, 25, 25);
		plus.addActionListener(controle_c);

		JButton minus = new Bouton("-");
		panO.add(minus);
		minus.setBounds(350, 215, 25, 25);
		minus.addActionListener(controle_c);
	}

	/**
	 * Met à jour la Vue pour afficher le panneau Start et utiliser le bon
	 * controleur ajout des boutons des différents menu
	 * (start,option,about,scoreboard et quit)
	 * 
	 * @param _controle
	 *            controleur de la souris
	 * 
	 */
	public void setPanelStart(MouseClic _controle) {
		controle_c = _controle;
		panS.setLayout(null);
		this.setContentPane(panS);
		this.revalidate();
		Bouton start = new Bouton("Start");
		panS.add(start);
		start.setBounds(260, 36, 110, 50);
		start.addActionListener(controle_c);
		Bouton options = new Bouton("Options");
		panS.add(options);
		options.setBounds(260, 106, 110, 50);
		options.addActionListener(controle_c);

		Bouton scoreboard = new Bouton("Scoreboard");
		panS.add(scoreboard);
		scoreboard.setBounds(230, 176, 170, 50);
		scoreboard.addActionListener(controle_c);

		Bouton about = new Bouton("About");
		panS.add(about);
		about.setBounds(260, 246, 110, 50);
		about.addActionListener(controle_c);

		panS.add(quit);
		quit.setBounds(260, 316, 110, 50);
		quit.addActionListener(controle_c);

	}

	/**
	 * Actualise le panneau PanelLeaderBoard
	 * 
	 */
	public void refreshLeaderBoard() {
		panLB.repaint();
	}

	/**
	 * Actualise le panneau PanelEndGame
	 * 
	 */
	public void refreshEndGame() {
		controle_c.setName(this.name.getText());
		panEG.repaint();
	}

	/**
	 * Actualise le panneau PanelInGame
	 * 
	 * @param counter
	 *            Entier à afficher, 0 si rien à afficher
	 */
	public void refresh(int counter) {
		panIG.setCounter(counter);
		panIG.repaint();
	}

	/**
	 * Actualise le panneau PanelAbout
	 * 
	 */
	public void refreshAbout() {
		panA.repaint();
	}

	/**
	 * Actualise le panneau PanelOption et la difficulté du pacman
	 * 
	 * @param ghostNumber
	 *            Le nombre de fantomes
	 */
	public void refreshOption(String ghostNumber) {
		controle_c.setLevel((String) this.comboLevel.getSelectedItem());
		controle_c.setDelete((String) this.comboDelete.getSelectedItem());
		controle_c.setDifficulty((String) this.comboDifficulty.getSelectedItem());
		panO.setGhostNumber(ghostNumber);
		panO.repaint();
	}

	/**
	 * Actualise le panneau PanelStart
	 * 
	 */
	public void refreshStart() {
		panS.repaint();
	}

	/**
	 * Permet de mettre à jour la vue et le panneau avec les coordonnées
	 * actuelles de Pacman et des fantomes
	 * 
	 * @param hero
	 *            Le Pacman courant
	 * @param maxX
	 *            Largeur de la fenêtre
	 * @param maxY
	 *            Hauteur de la fenêtre
	 * @param ghost
	 *            Tableau des fantomes
	 * @param bonus
	 *            Tableau des bonus mangés
	 * 
	 */
	public void majVue(Pacman hero, int maxX, int maxY, Ghost[] ghost, boolean[] bonus) {
		panIG.setHero(hero);
		panIG.setGhost(ghost);
		panIG.setBonus(bonus);
		this.setContentPane(panIG);
	}

	/**
	 * Affichage de la pause
	 * 
	 */
	public void showPause() {
		panIG.setPause(true);
		quit.setVisible(true);
		back.setVisible(true);
		returnAbout.setVisible(true);
	}

	/**
	 * Annulation de la pause
	 * 
	 */
	public void hidePause() {
		panIG.setPause(false);
		returnAbout.setVisible(false);
		quit.setVisible(false);
		back.setVisible(false);
		requestFocus();
	}

	/**
	 * Permet de mettre à jour le compteur
	 * 
	 * @param i
	 *            la nouvelle valeur
	 */
	public void setCounter(int i) {
		panIG.setCounter(i);
	}

	/**
	 * Met à jour le tableau et le niveau à afficher dans le leaderboard
	 * 
	 * @param extract
	 *            Le nouveau tableau
	 * @param level
	 *            Le nouveau niveau
	 */
	public void setTabLeaderBoard(String[][] extract, String level) {
		panLB.setTab(extract);
		panLB.setLevel(level);
		panLB.repaint();
	}

}
