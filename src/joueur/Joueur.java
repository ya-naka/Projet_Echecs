package joueur;

import couleur.ICouleur;
import jeu.Plateau;

public class Joueur implements IJoueur{
	ICouleur camp;
	
	public Joueur(ICouleur camp) {
		this.camp = camp;
	}
	
	@Override
	public void jouer(Plateau plateau) {
		// TODO Auto-generated method stub
		
	}

	
}
