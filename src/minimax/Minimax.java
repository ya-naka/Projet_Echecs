package minimax;

import couleur.Blanc;
import couleur.ICouleur;
import couleur.Noir;
import jeu.Deplacement;
import jeu.Plateau;
import joueur.IJoueur;

public class Minimax {
	private IEvaluationPlateau evaluation;
	private int profondeur;
	private Deplacement depChoisi;
	
	/**
	 * Constructeur
	 * @param eval
	 * @param profondeur
	 */
	public Minimax(IEvaluationPlateau eval, int profondeur) {
		assert(profondeur > 0);
		this.evaluation = eval;
		this.profondeur = profondeur;
		this.depChoisi = null;
	}
	
	/**
	 * retourne le coup à jouer après utilisation de minimax
	 * @param plateau
	 * @param camp
	 * @return
	 */
	public Deplacement choisirCoup(Plateau plateau, ICouleur camp) {
		int score = minimax(plateau, this.profondeur, camp);
		return this.depChoisi;
	}
	
	/**
	 * sélectionne le meilleur coup à jouer
	 * @param plateau
	 * @param profondeur
	 * @param camp
	 * @return
	 */
	public int minimax(Plateau plateau, int profondeur, ICouleur camp) {
		//évalue le plateau s'il est en état de partie finie ou si on se trouve au bout de l'arbre
		if(profondeur == 0 || plateau.estPartieFinie(camp)) {
			return this.evaluation.evaluer(plateau, profondeur);
		}
		if(camp.estBlanc()) { /* BLANC */
			//initialise le score avec la plus petite valeur possible
			int score = Integer.MIN_VALUE;
			/*
			 * compare tous les états du plateau se trouvant à la même profondeur et descendant du même noeud
			 * et garde en mémoire celui ayant le score le plus élevé
			 */
			for(Deplacement dep : plateau.getDeplacementsPossibles(new Blanc())) {
				if(dep.estDeplacementValide(camp)) {
					plateau.deplacer(dep, new Blanc());
					int nouveauScore = minimax(plateau, profondeur - 1, new Noir());
					plateau.revenirEnArriere();
					if(nouveauScore > score) {
						score = nouveauScore;
						if(profondeur == this.profondeur) {
							this.depChoisi = dep;
						}
					}
				}
			}
			return score;
		}else { /* NOIR */
			//initialise le score avec la plus grande valeur possible
			int score = Integer.MAX_VALUE;
			/*
			 * compare tous les états du plateau se trouvant à la même profondeur et descendant du même noeud
			 * et garde en mémoire celui ayant le score le moins élevé
			 */
			for(Deplacement dep : plateau.getDeplacementsPossibles(new Noir())) {
				if(dep.estDeplacementValide(camp)) {
					plateau.deplacer(dep, new Noir());
					int nouveauScore = minimax(plateau, profondeur - 1, new Blanc());
					plateau.revenirEnArriere();
					if(nouveauScore < score) {
						score = nouveauScore;
						if(profondeur == this.profondeur) {
							this.depChoisi = dep;
						}
					}
				}
			}
			return score;
		}
	}
	
}
