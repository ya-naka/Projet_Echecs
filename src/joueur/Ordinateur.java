package joueur;

import couleur.ICouleur;
import jeu.Deplacement;
import jeu.Plateau;
import minimax.EvaluationPlateauParValeur;
import minimax.EvaluationPlateauSimple;
import minimax.Minimax;
import utils.Message;

public class Ordinateur implements IJoueur{
	private final ICouleur camp;
	
	/**
	 * Constructeur
	 * @param camp
	 */
	public Ordinateur(ICouleur camp) {
		this.camp = camp;
	}
	
	/**
	 * sélectionne automatiquement le meilleur coup à jouer et joue son coup sur le plateau
	 */
	@Override
	public void jouer(Plateau plateau) {
		try {
			Thread.sleep(2000);
			Minimax minimax = new Minimax(new EvaluationPlateauSimple(), 2);
			Deplacement depChoisi = minimax.choisirCoup(plateau, this.camp);
			plateau.deplacer(depChoisi, getCouleur());
			Message.coupJoué(this, depChoisi);
		} catch (InterruptedException e) {}
		
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
