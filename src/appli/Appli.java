
package appli;

import java.util.Scanner;

import jeu.Plateau;
import joueur.IJoueur;
import joueur.Joueur;
import joueur.Ordinateur;

public class Appli {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IJoueur j1, j2;
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
				j1 = new Joueur();
				j2 = new Joueur();
				break;
			case 2:
				j1 = new Joueur();
				j2 = new Ordinateur();
				break;
			case 3:
				j1 = new Ordinateur();
				j2 = new Ordinateur();
				break;
			default:
				j1 = new Ordinateur();
				j2 = new Ordinateur();
				break;
		}
		
		
		Plateau jeu = new Plateau(j1, j2);
		System.out.println(jeu.toString());
	}

}
