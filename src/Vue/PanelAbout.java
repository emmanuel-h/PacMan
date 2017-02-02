package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelAbout extends JPanel {

	/**
	 * Pixel actuel de Starring
	 */
	private int pixelStarring;

	/**
	 * Pixel actuel de Maxime
	 */
	private int pixelMaxime;

	/**
	 * Pixel actuel de Martin
	 */
	private int pixelMartin;

	/**
	 * Pixel actuel de Manu
	 */
	private int pixelManu;

	/**
	 * Pixel actuel de Pacman
	 */
	private int pixelPacman;

	/**
	 * Pixel actuel de Ghost
	 */
	private int pixelGhost;

	/**
	 * Pixel actuel de Producer
	 */
	private int pixelProducer;

	/**
	 * Pixel actuel de Director
	 */
	private int pixelDirector;

	/**
	 * Pixel actuel de Screenwriter
	 */
	private int pixelScreenwriter;

	/**
	 * Pixel actuel de Musician
	 */
	private int pixelMusician;

	/**
	 * Pixel actuel de Music
	 */
	private int[] pixelMusic;

	/**
	 * Pixel où doit commencer Producer
	 */
	private int baseProducer;

	/**
	 * Pixel où doit commencer Maxime
	 */
	private int baseMaxime;

	/**
	 * Pixel où doit commencer Director
	 */
	private int baseDirector;

	/**
	 * Pixel où doit commencer Manu
	 */
	private int baseManu;

	/**
	 * Pixel où doit commencer Screenwriter
	 */
	private int baseScreenwriter;

	/**
	 * Pixel où doit commencer Martin
	 */
	private int baseMartin;

	/**
	 * Pixel où doit commencer Starring
	 */
	private int baseStarring;

	/**
	 * Pixel où doit commencer Pacman
	 */
	private int basePacman;

	/**
	 * Pixel où doit commencer Ghost
	 */
	private int baseGhost;

	/**
	 * Pixel où doit commencer Musician
	 */
	private int baseMusician;

	/**
	 * Pixel où doit commencer Music
	 */
	private int[] baseMusic;

	/**
	 * Pixel actuel de Tester
	 */
	private int pixelTester;

	/**
	 * Pixel actuel de Lucie
	 */
	private int pixelLucie;

	/**
	 * Pixel actuel de Thomas
	 */
	private int pixelThomas;

	/**
	 * Pixel où doit commencer Tester
	 */
	private int baseTester;

	/**
	 * Pixel où doit commencer Thomas
	 */
	private int baseThomas;

	/**
	 * Pixel où doit commencer Lucie
	 */
	private int baseLucie;

	/**
	 * Constructeur
	 * 
	 * @param height
	 *            hauteur de la fenêtre
	 */
	public PanelAbout(int height) {
		this.baseProducer = height + 20;
		this.baseMaxime = height + 60;
		this.baseDirector = height + 100;
		this.baseManu = height + 140;
		this.baseScreenwriter = height + 180;
		this.baseMartin = height + 220;
		this.baseStarring = height + 260;
		this.basePacman = height + 300;
		this.baseGhost = height + 330;
		this.baseTester = height + 370;
		this.baseLucie = height + 410;
		this.baseThomas = height + 440;
		this.baseMusician = height + 480;
		this.pixelProducer = baseProducer;
		this.pixelMaxime = baseMaxime;
		this.pixelDirector = baseDirector;
		this.pixelManu = baseManu;
		this.pixelScreenwriter = baseScreenwriter;
		this.pixelMartin = baseMartin;
		this.pixelStarring = baseStarring;
		this.pixelPacman = basePacman;
		this.pixelGhost = baseGhost;
		this.pixelMusician = baseMusician;
		this.pixelTester = baseTester;
		this.pixelLucie = baseLucie;
		this.pixelThomas = baseThomas;
		this.baseMusic = new int[7];
		this.pixelMusic = new int[7];
		this.baseMusic[0] = this.pixelMusic[0] = height + 520;
		for (int i = 1; i < baseMusic.length; i++) {
			this.baseMusic[i] = this.pixelMusic[i] = this.pixelMusic[i - 1] + 30;
		}
	}

	/**
	 * Remonte d'un pixel toutes les phrases
	 */
	public void paintComponent(Graphics g) {
		/*
		 * Si la dernière phrase vient de disparaître en haut de l'écran, on
		 * réinitialise toutes les phrases
		 */
		if (this.pixelMusic[this.pixelMusic.length - 1] == -10) {
			this.pixelProducer = baseProducer;
			this.pixelMaxime = baseMaxime;
			this.pixelDirector = baseDirector;
			this.pixelManu = baseManu;
			this.pixelScreenwriter = baseScreenwriter;
			this.pixelMartin = baseMartin;
			this.pixelStarring = baseStarring;
			this.pixelPacman = basePacman;
			this.pixelGhost = baseGhost;
			this.pixelTester = baseTester;
			this.pixelLucie = baseLucie;
			this.pixelThomas = baseThomas;
			this.pixelMusician = baseMusician;
			for (int i = 0; i < baseMusic.length; i++) {
				this.pixelMusic[i] = this.baseMusic[i];
			}
		}

		this.pixelStarring--;
		this.pixelMaxime--;
		this.pixelMartin--;
		this.pixelManu--;
		this.pixelPacman--;
		this.pixelGhost--;
		this.pixelProducer--;
		this.pixelDirector--;
		this.pixelScreenwriter--;
		this.pixelTester--;
		this.pixelLucie--;
		this.pixelThomas--;
		this.pixelMusician--;
		for (int i = 0; i < baseMusic.length; i++) {
			this.pixelMusic[i]--;
		}

		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		Font font = new Font("Courier", Font.BOLD, 25);
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString("Executive Producer", this.getWidth() / 3, pixelProducer);
		g.drawString("Director", this.getWidth() / 3, pixelDirector);
		g.drawString("Screenwriter", this.getWidth() / 3, pixelScreenwriter);
		g.drawString("Starring", this.getWidth() / 3, pixelStarring);
		g.drawString("Music by", this.getWidth() / 3, pixelMusician);
		g.drawString("Special Thanks to", this.getWidth() / 3, pixelTester);

		Font font2 = new Font("Courier", Font.BOLD, 20);
		g.setFont(font2);
		g.setColor(Color.YELLOW);
		g.drawString("Duchene Maxime", this.getWidth() / 3 + 30, pixelMaxime);
		g.drawString("Herrmann Emmanuel", this.getWidth() / 3 + 30, pixelManu);
		g.drawString("Rety Martin", this.getWidth() / 3 + 30, pixelMartin);
		g.drawString("Pacman", this.getWidth() / 3 + 30, pixelPacman);
		g.drawString("and the Ghosts", this.getWidth() / 3 + 30, pixelGhost);
		g.drawString("Gremy Lucie", this.getWidth() / 3 + 30, pixelLucie);
		g.drawString("Jorand Thomas", this.getWidth() / 3 + 30, pixelThomas);
		g.drawString("Even Deeper - Fight", this.getWidth() / 3 + 30, pixelMusic[0]);
		g.drawString("Eyeln - Final Battle", this.getWidth() / 3 + 30, pixelMusic[1]);
		g.drawString("Floating Isle - Booster Ignite", this.getWidth() / 3 + 30, pixelMusic[2]);
		g.drawString("Fun Electronics - Techniques III", this.getWidth() / 3 + 30, pixelMusic[3]);
		g.drawString("Plastic3 - Energetic Funk", this.getWidth() / 3 + 30, pixelMusic[4]);
		g.drawString("Dark Helmetz - 8-bit", this.getWidth() / 3 + 30, pixelMusic[5]);
		g.drawString("Multifaros - Number 3", this.getWidth() / 3 + 30, pixelMusic[6]);
	}
}
