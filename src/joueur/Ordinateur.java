package joueur;

import couleur.ICouleur;
import jeu.Deplacement;
import jeu.Plateau;
import minimax.EvaluationPlateauParValeur;
import minimax.Minimax;
import utils.Message;

public class Ordinateur implements IJoueur{
	private final ICouleur camp;
	
	public Ordinateur(ICouleur camp) {
		this.camp = camp;
	}
	
	@Override
	public void jouer(Plateau plateau) {
		try {
			Thread.sleep(2000);
			Minimax minimax = new Minimax(new EvaluationPlateauParValeur(), 1); //limitation à une 1 profondeur sinon StackOverflow Erreur
			Deplacement depChoisi = minimax.choisirCoup(plateau, plateau.getJoueurActif().getCouleur().estBlanc());
			plateau.deplacer(depChoisi, getCouleur());
			Message.coupJoué(this, depChoisi);
		} catch (InterruptedException e) {}
		
	}

	@Override
	public ICouleur getCouleur() {
		return this.camp;
	}

}
