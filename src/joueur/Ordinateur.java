package joueur;

import couleur.ICouleur;
import jeu.Plateau;

public class Ordinateur implements IJoueur{
	private final ICouleur camp;
	
	public Ordinateur(ICouleur camp) {
		this.camp = camp;
	}
	
	@Override
	public void jouer(Plateau plateau) {
		try {
			Thread.sleep(2);
			
		} catch (InterruptedException e) {}
		
	}

	@Override
	public ICouleur getCouleur() {
		return this.camp;
	}

}
