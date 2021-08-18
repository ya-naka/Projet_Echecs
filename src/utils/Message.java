package utils;

import couleur.Blanc;
import couleur.Noir;
import jeu.Deplacement;
import jeu.Plateau;
import joueur.IJoueur;

public class Message {
	/**
	 * affiche un message lorsque la partie est finie, selon son �tat
	 * @param plateau
	 * @param joueurActif
	 */
	public static void partieFinie(Plateau plateau, IJoueur joueurActif) {
		if(plateau.estPat(joueurActif.getCouleur())) {
			System.out.println(joueurActif.toString() + " ne peut plus jouer.\n"
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
	
	/**
	 * affiche un message indiquant que le joueur envoy� est �chec
	 * @param joueurActif
	 */
	public static void joueurActifEstEchec(IJoueur joueurActif) {
		System.out.println("Attention, " + joueurActif.getCouleur().toString() + " est �chec !");
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
	 * affiche un message indiquant le coup jou� par le joueur envoy�
	 * @param joueurActif
	 * @param dep
	 */
	public static void coupJou�(IJoueur joueurActif, Deplacement dep) {
		System.out.println(joueurActif.getCouleur().toString() + " a jou� " + dep.toString());
	}
	
	/**
	 * affiche un message indiquant que le coup saisi n'est pas valide
	 */
	public static void coupInvalide() {
		System.out.println("Veuillez saisir un coup valide et/ou 2 cases diff�rentes");
	}
	
	/**
	 * affiche un message indiquant que le coup saisi n'est pas un coup l�gal
	 */
	public static void coupNonLegal() {
		System.out.println("Veuillez choisir un coup l�gal");
	}
}
