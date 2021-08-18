
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
		//le joueur commençant est Blanc
		joueurActif = j1.getCouleur().estBlanc() ? j1 : j2;
		Plateau plateau = new Plateau();

		System.out.println(plateau.toString());
		do {
			//vérifie si la partie est terminée
			if(plateau.estPartieFinie(joueurActif.getCouleur())){
				Message.partieFinie(plateau, joueurActif);
				break;
	     	}
			
			//avertit le joueur s'il est en échec
			if(plateau.estEchec(joueurActif.getCouleur())) {
				Message.joueurActifEstEchec(joueurActif);
			}
			
			//affiche les instructions au joueur courant
			Message.saisieJoueur(joueurActif);
			
			//tour du joueur courant
			joueurActif.jouer(plateau);
			//affichage du plateau de jeu
			System.out.println(plateau.toString());
			
			//changement de joueur
			joueurActif = joueurActif.getCouleur().estMemeCouleur(j1.getCouleur()) ? j2 : j1;
		}while(true);
	}

}
