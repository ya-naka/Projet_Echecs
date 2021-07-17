package minimax;

import couleur.Blanc;
import couleur.Noir;
import jeu.Deplacement;
import jeu.Plateau;
import joueur.IJoueur;

public class Minimax {
	private IEvaluationPlateau evaluation;
	private int profondeur;
	
	public Minimax(IEvaluationPlateau eval, int profondeur) {
		assert(profondeur > 0);
		this.evaluation = eval;
		this.profondeur = profondeur;
	}
	
	public Deplacement choisirDeplacement(Plateau plateau, IJoueur joueur) {
		Deplacement depChoisi = null;
		if(joueur.getCouleur().estBlanc()) {
			int valeurMaxTrouvee = Integer.MAX_VALUE;
			for(Deplacement dep : plateau.getDeplacementsPossibles(joueur.getCouleur())) {
				plateau.deplacer(dep, joueur.getCouleur());
				int nouvelleValeur = mini(plateau, joueur, this.profondeur - 1);
				if(valeurMaxTrouvee < nouvelleValeur) {
					valeurMaxTrouvee = nouvelleValeur;
					depChoisi = dep;
				}
				plateau.revenirEnArriere();
			}
		}else {
			int valeurMiniTrouvee = Integer.MIN_VALUE;
			for(Deplacement dep : plateau.getDeplacementsPossibles(joueur.getCouleur())) {
				plateau.deplacer(dep, joueur.getCouleur());
				int nouvelleValeur = max(plateau, joueur, this.profondeur - 1);
				if(valeurMiniTrouvee < nouvelleValeur) {
					valeurMiniTrouvee = nouvelleValeur;
					depChoisi = dep;
				}
				plateau.revenirEnArriere();
			}
		}
		return depChoisi;
	}
	
	private int mini(Plateau plateau, IJoueur joueur, int profondeur) {
		if(profondeur == 0  
				|| plateau.estEchecEtMat(new Blanc()) 
				|| plateau.estEchecEtMat(new Noir())
				|| plateau.estPat(joueur.getCouleur())) {
			return this.evaluation.evaluer(plateau, profondeur);
		}
		int valeurMiniTrouvee = Integer.MAX_VALUE;
		for(Deplacement dep : plateau.getDeplacementsPossibles(joueur.getCouleur())) {
			plateau.deplacer(dep, joueur.getCouleur());
			int nouvelleValeur = max(plateau, joueur, profondeur - 1);
			if(valeurMiniTrouvee > nouvelleValeur) {
				valeurMiniTrouvee = nouvelleValeur;
			}
			plateau.revenirEnArriere();
		}
		return valeurMiniTrouvee;
	}
	
	private int max(Plateau plateau, IJoueur joueur, int profondeur) {
		if(profondeur == 0 
				|| plateau.estEchecEtMat(new Blanc()) 
				|| plateau.estEchecEtMat(new Noir())
				|| plateau.estPat(joueur.getCouleur())) {
			return this.evaluation.evaluer(plateau, profondeur);
		}
		int valeurMaxTrouvee = Integer.MIN_VALUE;
		for(Deplacement dep : plateau.getDeplacementsPossibles(joueur.getCouleur())) {
			plateau.deplacer(dep, joueur.getCouleur());
			int nouvelleValeur = mini(plateau, joueur, profondeur - 1);
			if(valeurMaxTrouvee < nouvelleValeur) {
				valeurMaxTrouvee = nouvelleValeur;
			}
			plateau.revenirEnArriere();
		}
		return valeurMaxTrouvee;
	}
}
