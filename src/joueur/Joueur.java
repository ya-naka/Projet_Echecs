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
	 * v�rifie la saisie du joueur et joue son coup sur le plateau
	 */
	@Override
	public void jouer(Plateau plateau) {
		Scanner sin = new Scanner(System.in);
		String saisie;
		boolean saisieOk = false;
		boolean deplacementOk = false;
		Deplacement dep;
		//v�rifie que le pion peut �tre d�plac�
		do {
			//v�rification de la saisie du joueur
			do {
				saisie = sin.nextLine();
				//S�parer l'affichage console de la classe
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
	 * @return la d�nomination du joueur
	 */
	public String toString() {
		return getCouleur().toString();
	}
}
