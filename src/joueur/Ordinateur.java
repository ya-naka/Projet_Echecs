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
		// TODO Auto-generated method stub
		
	}

	@Override
	public ICouleur getCouleur() {
		return this.camp;
	}

}
