package utils;

import jeu.Case;
import jeu.Plateau;

public class SaisieUtils {
	/**
	 * v�rifie si la saisie est valide
	 * @param saisie
	 * @return
	 */
	public static boolean estSaisieValide(String saisie) {
		if(saisie.length() != 4) {
			return false;
		}
		return (estColonneValide(saisie.charAt(0)) && estColonneValide(saisie.charAt(2)) 
				&& estLigneValide(saisie.charAt(1)) && estLigneValide(saisie.charAt(3))
				//v�rifie que les coups saisis ne sont pas identiques
				&& !saisie.substring(0, 2).equals(saisie.substring(2, 4)));
	}
	
	/**
	 * v�rifie que la colonne saisie est valide
	 * @param colonne
	 * @return
	 */
	public static boolean estColonneValide(char colonne) {
		//conversion num�rique de la colonne
		int col = charToInt(Character.toLowerCase(colonne));
		return (col >= 97 && col <= 104);
	}
	
	/**
	 * v�rifie que la ligne saisie est valide
	 * @param ligne
	 * @return
	 */
	public static boolean estLigneValide(char ligne) {
		//conversion num�rique de la ligne
		int li = charToInt(ligne);
		return (li >= 49 && li <= 56);
	}
	
	/**
	 * convertit un char en int
	 * @param caractere
	 * @return
	 */
	public static int charToInt(char caractere) {
		int x = caractere;
		return x;
	}
	
	/**
	 * convertit la saisie d'un joueur en une coordonn�e
	 * @param saisie
	 * @return
	 */
	public static int saisieToCoordonn�e(String saisie) {
		assert(saisie.length() == 2);
		char col = saisie.charAt(0);
		char ligne = saisie.charAt(1);
		assert(estColonneValide(col) && estLigneValide(ligne));
		return ((ligne-49)*8) + (col-97);
	}
	
	/**
	 * convertit une coordonn�e en chaine de caract�res
	 * @param coord
	 * @return
	 */
	public static String coordonn�eToSaisie(int coord) {
		if(!Plateau.estCaseValide(coord)) {
			return "" + coord;
		}
		char li = (char)((coord/8) + 49);
		char col = (char)((coord%8) + 97);
		return "" + col + li;
	}
}
