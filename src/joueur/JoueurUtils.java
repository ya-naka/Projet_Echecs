package joueur;

public class JoueurUtils {
	public static boolean estSaisieValide(String saisie) {
		if(saisie.length() != 4) {
			return false;
		}
		return (estColonneValide(saisie.charAt(0)) && estColonneValide(saisie.charAt(2)) 
				&& estLigneValide(saisie.charAt(1)) && estLigneValide(saisie.charAt(3))
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
}
