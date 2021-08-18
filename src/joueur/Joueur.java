package joueur;

import java.util.Scanner;

import couleur.ICouleur;
import jeu.Deplacement;
import jeu.Plateau;
import utils.Message;
import utils.SaisieUtils;

public class Joueur implements IJoueur{
	private final ICouleur camp;
	
	/**
	 * Constructeur
	 * @param camp
	 */
	public Joueur(ICouleur camp) {
		this.camp = camp;
	}
	
	/**
	 * vérifie la saisie du joueur et joue son coup sur le plateau
	 */
	@Override
	public void jouer(Plateau plateau) {
		Scanner sin = new Scanner(System.in);
		String saisie;
		boolean saisieOk = false;
		boolean deplacementOk = false;
		Deplacement dep;
		//vérifie que le pion peut être déplacé
		do {
			//vérification de la saisie du joueur
			do {
				saisie = sin.nextLine();
				//Séparer l'affichage console de la classe
				if(!(saisieOk = SaisieUtils.estSaisieValide(saisie))) {
					Message.coupInvalide();
				}
			}while(!saisieOk);
			dep = new Deplacement(plateau, saisie);
			deplacementOk = dep.estDeplacementValide(this.camp);
			if(deplacementOk) {
				plateau.deplacer(dep, this.camp);
			}else {
				Message.coupNonLegal();
			}
		}while(!deplacementOk);
	}

	/**
	 * retourne la couleur du camp du joueur
	 */
	@Override
	public ICouleur getCouleur() {
		return this.camp;
	}
	
	/**
	 * @return la dénomination du joueur
	 */
	public String toString() {
		return getCouleur().toString();
	}
}
