package minimax;

import couleur.Blanc;
import couleur.ICouleur;
import couleur.Noir;
import jeu.Plateau;
import pieces.IPiece;

public class EvaluationPlateauParValeur implements IEvaluationPlateau{
	private static final int ECHEC_SCORE = 100;
	private static final int ECHEC_ET_MAT_SCORE = 1000;
	private static final int PAT_SCORE = 1000;
	
	/**
	 * évalue l'état du plateau
	 */
	@Override
	public int evaluer(Plateau plateau, int profondeur) {
		return calculerScore(plateau, new Blanc(), profondeur) - calculerScore(plateau, new Noir(), profondeur);
	}

	/**
	 * attribue un score à l'état du plateau en fonction :
	 * - du camp sélectionné
	 * - de la valeur de chaque pièce sur le plateau 
	 * - de différents paramètres
	 * @param plateau
	 * @param couleur
	 * @param profondeur
	 * @return
	 */
	public int calculerScore(Plateau plateau, ICouleur couleur, int profondeur) {
		int score = 0;
		for(IPiece piece : plateau.getPieces(couleur)) {
			score += piece.getValeur();
		}
		if(plateau.estEchec(couleur)) {
			score -= ECHEC_SCORE;
		}
		if(plateau.estEchec(couleur.getCouleurOpposee())) {
			score += ECHEC_SCORE;
		}
		if(plateau.estEchecEtMat(couleur)) {
			score -= ECHEC_ET_MAT_SCORE;
		}
		if(plateau.estEchecEtMat(couleur.getCouleurOpposee())) {
			score += ECHEC_ET_MAT_SCORE;
		}
		if(plateau.estPat(couleur)) {
			score -= PAT_SCORE;
		}
		if(plateau.estPat(couleur.getCouleurOpposee())) {
			score += PAT_SCORE;
		}
		return score;
	}
}
