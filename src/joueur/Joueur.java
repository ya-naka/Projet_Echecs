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
		//v�rifie que le pion peut �tre d�plac�
		do {
			//v�rification de la saisie du joueur
			do {
				saisie = sin.nextLine();
				//S�parer l'affichage console de la classe
				if(saisieOk = SaisieUtils.estSaisieValide(saisie)) {
					System.out.println("Veuillez saisir un coup valide et/ou 2 cases diff�rentes");
				}
			}while(!saisieOk);
			Deplacement dep = new Deplacement(plateau, saisie);
			if(deplacementOk = dep.estDeplacementValide()) {
				plateau.deplacer(dep);
			}
		}while(!deplacementOk);
	}
}
