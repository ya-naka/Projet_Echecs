package pieces;

import java.util.List;

import couleur.ICouleur;
import jeu.Deplacement;
import jeu.Plateau;

public interface IPiece {
	void deplacer(Plateau plateau, int position);
	List<Deplacement> getDeplacementsPossibles(Plateau plateau);
	boolean peutDeplacer(Plateau plateau, int nouvellePosition);
	int getPosition();
	int getValeur();
	ICouleur getCouleur();
}
