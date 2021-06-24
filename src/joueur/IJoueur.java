package joueur;

import couleur.ICouleur;
import jeu.Plateau;

public interface IJoueur {
	void jouer(Plateau plateau);
	ICouleur getCouleur();
}
