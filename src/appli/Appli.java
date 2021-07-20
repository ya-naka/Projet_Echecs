
package appli;

import java.util.Scanner;

import couleur.Blanc;
import couleur.ICouleur;
import couleur.Noir;
import jeu.Plateau;
import joueur.IJoueur;
import joueur.Joueur;
import joueur.Ordinateur;
import minimax.EvaluationPlateauParValeur;
import minimax.Minimax;
import utils.Message;
import utils.SaisieUtils;

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
		Plateau plateau = new Plateau(j1, j2);
		System.out.println(plateau.toString());
		do {
			/*
			 * if(plateau.estPartieFinie()){
			 * 		Message.partieFinie(plateau);
			 * }
			 */
			
			//vérifie si le joueur a toujours son roi, sinon il a perdu la partie
			if(plateau.getRoi(joueurActif.getCouleur()) == null) {
				System.out.println(joueurActif.getCouleur().toString() + " a perdu");
				break;
			}
			boolean estPat = plateau.estPat(joueurActif.getCouleur());
			//vérifie sir le joueur peut jouer au moins un coup légal, sinon la partie est nulle
			if(estPat) {
				System.out.println(joueurActif.getCouleur().toString() + " ne peut plus jouer.\n"
						+ "La partie est déclarée nulle");
				break;
			}
			
			//avertit le joueur s'il est en échec
			if(plateau.estEchec(joueurActif.getCouleur())) {
				//System.out.println("Attention, " + joueurActif.getCouleur().toString() + " est échec !");
				Message.joueurActifEstEchec(joueurActif);
				//à remplacer par :
				//Message.joueurActifEstEchec(plateau.getJoueurActif());
			}
			
			//instruction de saisie au joueur
			/*System.out.println("A " + joueurActif.getCouleur().toString() + " de jouer."
					+ " Veuillez saisir un coup (ex: a1b2) :");*/
			Message.saisieJoueur(joueurActif);
			
			joueurActif.jouer(plateau);
			System.out.println(plateau.toString());
			
			//changement de joueur
			joueurActif = joueurActif.getCouleur().estMemeCouleur(j1.getCouleur()) ? j2 : j1;
		}while(true);
		
	}

}
