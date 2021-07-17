package joueur;

import couleur.ICouleur;
import jeu.Deplacement;
import jeu.Plateau;
import minimax.EvaluationPlateauParValeur;
import minimax.Minimax;

public class Ordinateur implements IJoueur{
	private final ICouleur camp;
	
	public Ordinateur(ICouleur camp) {
		this.camp = camp;
	}
	
	@Override
	public void jouer(Plateau plateau) {
		try {
			Thread.sleep(200);
			Minimax minimax = new Minimax(new EvaluationPlateauParValeur(), 2);
			Deplacement depChoisi = minimax.choisirDeplacement(plateau, this);
			plateau.deplacer(depChoisi, getCouleur());
			System.out.println("Ordinateur a joué " + depChoisi.toString());
		} catch (InterruptedException e) {}
		
	}

	@Override
	public ICouleur getCouleur() {
		return this.camp;
	}

}
