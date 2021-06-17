package pieces;

import java.util.List;

import couleur.ICouleur;
import jeu.Deplacement;

public interface IPiece {
	void deplacer(int position);
	List<Deplacement> getDeplacementsPossibles();
	boolean peutDeplacer(int nouvellePosition);
	int getPosition();
	ICouleur getCouleur();
}
