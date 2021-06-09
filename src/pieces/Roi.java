package pieces;

import java.util.List;

import couleur.ICouleur;
import jeu.Deplacement;

public class Roi extends Piece{

	public Roi(int position, ICouleur couleur) {
		super(position, couleur);
	}

	@Override
	protected boolean peutDeplacer(int position) {
		return false;
	}
	
	public String toString() {
		if(super.getCouleur().estBlanc()) {
			return "r".toUpperCase();
		}
		return "r";
	}

	@Override
	public List<Deplacement> getDeplacementsPossibles() {
		return null;
	}

}
