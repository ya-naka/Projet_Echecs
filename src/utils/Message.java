package utils;

import couleur.Blanc;
import couleur.Noir;
import jeu.Deplacement;
import jeu.Plateau;
import joueur.IJoueur;

public class Message {
	/**
	 * affiche un message lorsque la partie est finie, selon son état
	 * @param plateau
	 * @param joueurActif
	 */
	public static void partieFinie(Plateau plateau, IJoueur joueurActif) {
		if(plateau.estPat(joueurActif.getCouleur())) {
			System.out.println(joueurActif.toString() + " ne peut plus jouer.\n"
					+ "La partie est déclarée nulle");
		}
		if(plateau.estEchecEtMat(new Blanc())) {
			System.out.println("Blanc est échec et mat.\n"
					+ "Noir a gagné la partie !");
		}
		if(plateau.estEchecEtMat(new Noir())) {
			System.out.println("Noir est échec et mat.\n"
					+ "Blanc a gagné la partie !");
		}
	}
	
	/**
	 * affiche un message indiquant que le joueur envoyé est échec
	 * @param joueurActif
	 */
	public static void joueurActifEstEchec(IJoueur joueurActif) {
		System.out.println("Attention, " + joueurActif.getCouleur().toString() + " est échec !");
	}
	
	/**
	 * affiche un message indiquant les consignes de saisie au joueur
	 * @param joueurActif
	 */
	public static void saisieJoueur(IJoueur joueurActif) {
		System.out.println("A " + joueurActif.getCouleur().toString() + " de jouer."
				+ " Veuillez saisir un coup (ex: a1b2) :");
	}
	
	/**
	 * affiche un message indiquant le coup joué par le joueur envoyé
	 * @param joueurActif
	 * @param dep
	 */
	public static void coupJoué(IJoueur joueurActif, Deplacement dep) {
		System.out.println(joueurActif.getCouleur().toString() + " a joué " + dep.toString());
	}
	
	/**
	 * affiche un message indiquant que le coup saisi n'est pas valide
	 */
	public static void coupInvalide() {
		System.out.println("Veuillez saisir un coup valide et/ou 2 cases différentes");
	}
	
	/**
	 * affiche un message indiquant que le coup saisi n'est pas un coup légal
	 */
	public static void coupNonLegal() {
		System.out.println("Veuillez choisir un coup légal");
	}
}
