package minimax;

import couleur.Blanc;
import couleur.Noir;
import jeu.Deplacement;
import jeu.Plateau;
import joueur.IJoueur;

public class Minimax {
	private IEvaluationPlateau evaluation;
	private int profondeur;
	private Deplacement depChoisi;
	
	public Minimax(IEvaluationPlateau eval, int profondeur) {
		assert(profondeur > 0);
		this.evaluation = eval;
		this.profondeur = profondeur;
		this.depChoisi = null;
	}
	
	public Deplacement choisirCoup(Plateau plateau, boolean maximazingPlayer) {
		int score = minimax(plateau, this.profondeur, maximazingPlayer);
		return this.depChoisi;
	}
	
	public int minimax(Plateau plateau, int profondeur, boolean maximazingPlayer) {
		if(profondeur == 0 || plateau.estPartieFinie()) {
			return this.evaluation.evaluer(plateau, profondeur);
		}
		if(maximazingPlayer) { /* BLANC */
			int score = Integer.MIN_VALUE;
			for(Deplacement dep : plateau.getDeplacementsPossibles(new Blanc())) {
				plateau.deplacer(dep, new Blanc());
				//score = Math.max(score, minimax(plateau, profondeur - 1, false));
				int nouveauScore = minimax(plateau, this.profondeur - 1, false);
				if(nouveauScore >= score) {
					score = nouveauScore;
					this.depChoisi = dep;
				}
				plateau.revenirEnArriere();
			}
			return score;
		}else { /* NOIR */
			int score = Integer.MAX_VALUE;
			for(Deplacement dep : plateau.getDeplacementsPossibles(new Noir())) {
				plateau.deplacer(dep, new Noir());
				int nouveauScore = minimax(plateau, this.profondeur - 1, true);
				if(nouveauScore <= score) {
					score = nouveauScore;
					this.depChoisi = dep;
				}
				plateau.revenirEnArriere();
			}
			return score;
		}
	}
	
}
