package Vue;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Gère toutes les interactions avec la musique
 * 
 * @author Duchene Herrmann Rety
 *
 */
public class Music {

	/**
	 * url de la musique actuellement jouée
	 */
	URL current_music;

	/**
	 * clip de la musique actuellement jouée
	 */
	Clip current_clip;

	/**
	 * Contructeur de Music lancement de la musique du panneau Start
	 * 
	 */
	public Music() {
	}

	/**
	 * Permet de changer de musique, test si une musique est déjà en train
	 * d'être joué
	 * 
	 * @param music
	 *            nouvelle musique
	 */
	public void changeMusic(String music) {
		if (current_clip != null) {
			if (current_clip.isRunning()) {
				current_clip.close();
			}
		}
		setMusic(music);
	}

	/**
	 * Permet de lire une nouvelle musique, charge la musique et la joue en
	 * boucle
	 * 
	 * @param music
	 *            nouvelle musique
	 */
	public void setMusic(String music) {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(music));
			current_clip = AudioSystem.getClip();
			current_clip.open(audioIn);
			current_clip.start();
			current_clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			System.out.println("error sound");
		}
	}

	/**
	 * Permet d'arrêter ou de relancer la musique actuelle
	 */
	public void stopMusic() {
		if (current_clip.isRunning()) {
			current_clip.stop();
		} else {
			current_clip.start();
		}
	}

}
