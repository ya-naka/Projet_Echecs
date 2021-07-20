package utils;

import couleur.Blanc;
import couleur.Noir;
import jeu.Deplacement;
import jeu.Plateau;
import joueur.IJoueur;

public class Message {
	public static void partieFinie(Plateau plateau) {
		if(plateau.estPat(plateau.getJoueurActif().getCouleur())) {
			System.out.println(plateau.getJoueurActif().toString() + " ne peut plus jouer.\n"
					+ "La partie est d�clar�e nulle");
		}
		if(plateau.estEchecEtMat(new Blanc())) {
			System.out.println("Blanc est �chec et mat.\n"
					+ "Noir a gagn� la partie !");
		}
		if(plateau.estEchecEtMat(new Noir())) {
			System.out.println("Noir est �chec et mat.\n"
					+ "Blanc a gagn� la partie !");
		}
	}
	
	public static void joueurActifEstEchec(IJoueur joueurActif) {
		System.out.println("Attention, " + joueurActif.getCouleur().toString() + " est �chec !");
	}
	
	public static void saisieJoueur(IJoueur joueurActif) {
		System.out.println("A " + joueurActif.getCouleur().toString() + " de jouer."
				+ " Veuillez saisir un coup (ex: a1b2) :");
	}
	
	public static void coupJou�(IJoueur joueurActif, Deplacement dep) {
		System.out.println(joueurActif.getCouleur().toString() + " a jou� " + dep.toString());
	}
}
