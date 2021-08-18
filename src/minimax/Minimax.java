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
	 * retourne le coup � jouer apr�s utilisation de minimax
	 * @param plateau
	 * @param camp
	 * @return
	 */
	public Deplacement choisirCoup(Plateau plateau, ICouleur camp) {
		int score = minimax(plateau, this.profondeur, camp);
		return this.depChoisi;
	}
	
	/**
	 * s�lectionne le meilleur coup � jouer
	 * @param plateau
	 * @param profondeur
	 * @param camp
	 * @return
	 */
	public int minimax(Plateau plateau, int profondeur, ICouleur camp) {
		//�value le plateau s'il est en �tat de partie finie ou si on se trouve au bout de l'arbre
		if(profondeur == 0 || plateau.estPartieFinie(camp)) {
			return this.evaluation.evaluer(plateau, profondeur);
		}
		if(camp.estBlanc()) { /* BLANC */
			//initialise le score avec la plus petite valeur possible
			int score = Integer.MIN_VALUE;
			/*
			 * compare tous les �tats du plateau se trouvant � la m�me profondeur et descendant du m�me noeud
			 * et garde en m�moire celui ayant le score le plus �lev�
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
			 * compare tous les �tats du plateau se trouvant � la m�me profondeur et descendant du m�me noeud
			 * et garde en m�moire celui ayant le score le moins �lev�
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
