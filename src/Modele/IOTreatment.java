package Modele;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Centralise toutes les opérations d'I/O réalisées
 * 
 * @author Duchene Herrmann Rety
 *
 */
public class IOTreatment {

	/**
	 * Lit un fichier texte entré en paramètre pour en extraire la hauteur et la
	 * largeur de la matrice, le nombre de gommes et le remplissage du
	 * labyrinthe
	 * 
	 * @param file_name
	 *            Le nom du fichier à lire
	 * @throws IOException
	 *             si le fichier spécifié est introuvable
	 */
	public static void readMatrix(String file_name) throws IOException {
		InputStream ips = Modele.class.getResourceAsStream("Stage/" + file_name);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String currentLine;
		int width = Integer.parseInt(br.readLine().toString());
		int height = Integer.parseInt(br.readLine().toString());
		Modele.labyrinth = new int[width][height];
		Modele.totalGumGum = Modele.gumGum = Integer.parseInt(br.readLine().toString());
		int count = 0;
		while ((currentLine = br.readLine()) != null) {
			StringTokenizer currentChar = new StringTokenizer(currentLine);
			for (int i = 0; currentChar.hasMoreTokens(); i++) {
				Modele.labyrinth[i][count] = Integer.parseInt(currentChar.nextToken().toString());
			}
			count++;
		}
		br.close();
	}

	/**
	 * Ecrase le fichier avec le nouveau score
	 * 
	 * @param current_score
	 *            Le tableau à rentrer dans le fichier
	 * @param count
	 *            La place dans le tableau où rentrer le nouveau score
	 * @param score
	 *            Le score à rentrer
	 * @param name
	 *            Le pseudonyme de l'utilisateur
	 * @param current_file
	 *            Le fichier où rentrer le tableau
	 * @throws IOException
	 *             Si le fichier est introuvable
	 */
	public static void put(String[][] current_score, int count, int score, String name, File current_file)
			throws IOException {
		FileWriter fw = new FileWriter(current_file);
		BufferedWriter output = new BufferedWriter(fw);
		String newLine = System.getProperty("line.separator");
		for (int i = 0; i < count; i++) {
			output.write(current_score[i][0] + ' ' + current_score[i][1] + newLine);
			output.flush();
		}
		output.write(name + ' ' + score + newLine);
		output.flush();
		for (int i = count; i < current_score.length - 1; i++) {
			output.write(current_score[i][0] + ' ' + current_score[i][1] + newLine);
			output.flush();
		}
		output.close();
	}

	/**
	 * Extrait les meilleurs scores d'un fichier pour les mettre dans un tableau
	 * 
	 * @param f
	 *            Le fichier à parser
	 * @return Un tableau à deux entrées avec les pseudonymes des joueurs et
	 *         leurs scores respectifs
	 * @throws IOException
	 *             Si le fichier est introuvable
	 */
	public static String[][] extract(File f) throws IOException {
		if (!f.exists()) {
			f.createNewFile();
		}
		String[][] current_score = new String[10][2];
		FileInputStream ips = new FileInputStream(f);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String currentLine;
		for (int i = 0; i < current_score.length; i++) {
			if ((currentLine = br.readLine()) != null) {
				StringTokenizer currentChar = new StringTokenizer(currentLine);
				current_score[i][0] = currentChar.nextToken().toString();
				current_score[i][1] = currentChar.nextToken().toString();
			} else {
				current_score[i][0] = "AAA";
				current_score[i][1] = "000";
			}
		}
		br.close();
		return current_score;
	}

	/**
	 * Trouve le fichier correspondant à une chaine de caractère donnée pour
	 * l'enregistrement des highscores par niveau
	 * 
	 * @param file_name
	 *            le nom à transformer en nom de fichier
	 * @return Le nom de fichier
	 */
	public static String findFile(String file_name) {
		return "hs/"+file_name.substring(0, 6) + "HS.txt";
	}
}