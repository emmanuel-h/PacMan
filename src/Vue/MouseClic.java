package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ecoute et retient toutes les actions utilisateur reli�es � des clics sur des
 * boutons
 * 
 * @author Duchene Herrmann Rety
 *
 */
public class MouseClic implements ActionListener {

	/**
	 * Enregistre le nom de la derni�re action effectu�e
	 */
	private String action;

	/**
	 * Enregistre le nom de l'utilisateur
	 */
	private String name;

	/**
	 * Enregistre le niveau demand�
	 */
	private String level;

	/**
	 * Enregistre le niveau � supprimer
	 */
	private String delete;

	/**
	 * Enregistre le niveau de difficult� des fantomes
	 */
	private String difficulty;

	/**
	 * Constructeur
	 */
	public MouseClic() {
		this.action = "Nothing";
		this.name = "";
		this.level = "All";
		this.delete = "All";
		this.difficulty = "Medium";
	}

	/**
	 * Enregistre dans la String action ce qui a �t� demand� par l'utilisateur
	 */
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Start":
		case "Options":
		case "Scoreboard":
		case "About":
		case "Quit":
		case "Next":
		case "Easy":
		case "Medium":
		case "Hard":
		case "Delete":
		case "Save":
		case "On/Off":
		case "-":
		case "+":
		case "Back":
			this.action = e.getActionCommand();
			break;
		case "Menu":
			this.action = "ReturnAbout";
			break;
		default:
		}
	}

	/**
	 * Renvoie l'action demand�e
	 * 
	 * @return l'action
	 */
	public String getAction() {
		return this.action;
	}

	/**
	 * Modifie l'action demand�e
	 * 
	 * @param _action
	 *            la nouvelle action
	 */
	public void setAction(String _action) {
		this.action = _action;
	}

	/**
	 * Modifie le nom de l'utilisateur
	 * 
	 * @param _name
	 *            le nouveau nom
	 */
	public void setName(String _name) {
		this.name = _name;
	}

	/**
	 * Renvoie le nom de l'utilisateur
	 * 
	 * @return le nom
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Modifie le niveau � jouer
	 * 
	 * @param _level
	 *            le nouveau niveau
	 */
	public void setLevel(String _level) {
		this.level = _level;
	}

	/**
	 * Renvoie le niveau � jouer
	 * 
	 * @return le niveau
	 */
	public String getLevel() {
		return this.level;
	}

	/**
	 * Modifie le niveau � supprimer
	 * 
	 * @param _delete
	 *            le nouveau niveau
	 */
	public void setDelete(String _delete) {
		this.delete = _delete;
	}

	/**
	 * Renvoie le niveau � supprimer
	 * 
	 * @return le niveau
	 */
	public String getDelete() {
		return this.delete;
	}

	/**
	 * Modifie le niveau des fantomes
	 * 
	 * @param _difficulty
	 *            le nouveau niveau
	 */
	public void setDifficulty(String _difficulty) {
		this.difficulty = _difficulty;
	}

	/**
	 * Renvoie le niveau des fantomes
	 * 
	 * @return le niveau
	 */
	public String getDifficulty() {
		return this.difficulty;
	}

}
