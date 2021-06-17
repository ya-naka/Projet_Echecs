package utils;

import jeu.Case;

public class SaisieUtils {
	public static boolean estSaisieValide(String saisie) {
		if(saisie.length() != 4) {
			return false;
		}
		return (estColonneValide(saisie.charAt(0)) && estColonneValide(saisie.charAt(2)) 
				&& estLigneValide(saisie.charAt(1)) && estLigneValide(saisie.charAt(3))
				//vérifie que les coups saisis ne sont pas identiques
				&& !saisie.substring(0, 2).equals(saisie.substring(2, 4)));
	}
	
	public static boolean estColonneValide(char colonne) {
		//conversion numérique de la colonne
		int col = charToInt(colonne);
		return (col >= 97 && col <= 104);
	}
	
	public static boolean estLigneValide(char ligne) {
		//conversion numérique de la ligne
		int li = charToInt(ligne);
		return (li >= 49 && li <= 56);
	}
	
	public static int charToInt(char caractere) {
		int x = caractere;
		return x;
	}
	
	public static int saisieToCoordonnée(String saisie) {
		assert(saisie.length() == 2);
		char col = saisie.charAt(0);
		char ligne = saisie.charAt(1);
		assert(estColonneValide(col) && estLigneValide(ligne));
		return ((ligne-49)*8) + (col-97);
	}
}
