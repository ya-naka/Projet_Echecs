package pieces;

import java.util.ArrayList;
import java.util.List;

import couleur.ICouleur;
import jeu.Deplacement;
import jeu.Plateau;

public class Roi extends Piece{
	private static final int[] coordDeplacements = {-9, -1, 1, 9}; 

	public Roi(int position, ICouleur couleur) {
		super(position, couleur);
	}
	
	public String toString() {
		if(super.getCouleur().estBlanc()) {
			return "r".toUpperCase();
		}
		return "r";
	}

	@Override
	public List<Deplacement> getDeplacementsPossibles(Plateau plateau) {
		List<Deplacement> depPossibles = new ArrayList<>();
		for(int i = 0; i < coordDeplacements.length; i++) {
			int position = coordDeplacements[i]+super.getPosition();
			if(!plateau.getCase(position).getPiece().getCouleur().toString().equals(super.getCouleur())
					&& (position < plateau.NB_CASES && position >= 0)) {
				depPossibles.add(new Deplacement(plateau, super.getPosition(), position));
			}
		}
		return depPossibles;
	}

}
