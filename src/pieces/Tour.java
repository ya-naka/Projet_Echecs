package pieces;

import java.util.List;

import couleur.ICouleur;
import jeu.Deplacement;
import jeu.Plateau;

public class Tour extends Piece{
	private static final int[] coordDeplacements = {};

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
	public List<Deplacement> getDeplacementsPossibles(Plateau plateau) {
		return null;
	}

}
