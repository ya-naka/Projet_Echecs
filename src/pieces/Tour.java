package pieces;

import java.util.List;

import couleur.ICouleur;
import jeu.Deplacement;

public class Tour extends Piece{

	public Tour(int position, ICouleur couleur) {
		super(position, couleur);
	}
	
	public String toString() {
		if(super.getCouleur().estBlanc()) {
			return "t".toUpperCase();
		}
		return "t";
	}

	@Override
	public List<Deplacement> getDeplacementsPossibles() {
		return null;
	}

}
