
package appli;

import java.util.Scanner;

import couleur.Blanc;
import couleur.ICouleur;
import couleur.Noir;
import jeu.Plateau;
import joueur.IJoueur;
import joueur.Joueur;
import joueur.Ordinateur;

public class Appli {

	public static void main(String[] args) {
		IJoueur j1, j2, joueurActif;
		ICouleur noir = new Noir();
		ICouleur blanc = new Blanc();
		int saisie;
		Scanner sin = new Scanner(System.in);
		do {
			System.out.println("Sélectionnez le mode de jeu : \n");
			System.out.println("1 - Joueur VS Joueur\n"
				+ "2 - Joueur VS Ordinateur\n"
				+ "3 - Ordinateur VS Ordinateur");
			saisie = sin.nextInt();
		}while(saisie < 1 || saisie > 3);
		switch(saisie) {
			case 1:
				j1 = new Joueur(blanc);
				j2 = new Joueur(noir);
				break;
			case 2:
				j1 = new Joueur(blanc);
				j2 = new Ordinateur(noir);
				break;
			case 3:
				j1 = new Ordinateur(blanc);
				j2 = new Ordinateur(noir);
				break;
			default:
				j1 = new Ordinateur(blanc);
				j2 = new Ordinateur(noir);
				break;
		}
		
		joueurActif = j1.getCouleur().estBlanc() ? j1 : j2;
		Plateau plateau = new Plateau();
		System.out.println(plateau.toString());
		do {
			if(plateau.estEchec(joueurActif.getCouleur())) {
				System.out.println("Attention, " + joueurActif.getCouleur().getClass() + " est échec !");
			}
			System.out.println("Veuillez saisir un coup (ex: a1b2) :");
			joueurActif.jouer(plateau);
			System.out.println(plateau.toString());
			joueurActif = joueurActif.getCouleur().estMemeCouleur(j1.getCouleur()) ? j2 : j1;
			boolean estPat = plateau.estPat(joueurActif.getCouleur());
			if(estPat) {
				System.out.println(joueurActif.getCouleur().getClass() + " ne peut plus jouer. Egalité");
				break;
			}
			if(estPat && plateau.estEchec(joueurActif.getCouleur())) {
				System.out.println(joueurActif.getCouleur().getClass() + " a perdu...");
				break;
			}
		}while(true);
		
	}

}
