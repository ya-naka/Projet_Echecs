<<<<<<< HEAD
package joueur;

import java.util.Scanner;

import couleur.ICouleur;
import jeu.Deplacement;
import jeu.Plateau;
import utils.SaisieUtils;

public class Joueur implements IJoueur{
	private final ICouleur camp;
	
	public Joueur(ICouleur camp) {
		this.camp = camp;
	}
	
	@Override
	public void jouer(Plateau plateau) {
		Scanner sin = new Scanner(System.in);
		String saisie;
		boolean saisieOk = false;
		boolean deplacementOk = false;
		;
		//vérifie que le pion peut être déplacé
		do {
			//vérification de la saisie du joueur
			do {
				saisie = sin.nextLine();
				//Séparer l'affichage console de la classe
				if(saisieOk = SaisieUtils.estSaisieValide(saisie)) {
					System.out.println("Veuillez saisir un coup valide et/ou 2 cases différentes");
				}
			}while(!saisieOk);
			Deplacement dep = new Deplacement(plateau, saisie);
			if(deplacementOk = dep.estDeplacementValide()) {
				dep.deplacer();
			}
		}while(!deplacementOk);
	}
}
=======
package joueur;

import java.util.Scanner;

import couleur.ICouleur;
import jeu.Deplacement;
import jeu.Plateau;
import utils.SaisieUtils;

public class Joueur implements IJoueur{
	private final ICouleur camp;
	
	public Joueur(ICouleur camp) {
		this.camp = camp;
	}
	
	@Override
	public void jouer(Plateau plateau) {
		Scanner sin = new Scanner(System.in);
		String saisie;
		boolean saisieOk = false;
		boolean deplacementOk = false;
		//vérification de la saisie du joueur
		do {
			do {
				saisie = sin.nextLine();
				//Séparer l'affichage console de la classe
				if(saisieOk = SaisieUtils.estSaisieValide(saisie)) {
					System.out.println("Veuillez saisir un coup valide et/ou 2 cases différentes");
				}
			}while(!saisieOk);
		
			Deplacement dep = new Deplacement(plateau, saisie);
			if(deplacementOk = dep.estDeplacementValide()) {
				dep.deplacer();
			}
		}while(!deplacementOk);
	}
}
>>>>>>> 5eece1ed0bdee223088426b68d198b5a641b4c92
