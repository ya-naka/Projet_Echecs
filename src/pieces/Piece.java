package pieces;

import java.util.List;

import couleur.ICouleur;
import jeu.Deplacement;
import jeu.Plateau;

public abstract class Piece implements IPiece{
	private int position;
	private ICouleur couleur;
	
	public Piece(int position, ICouleur couleur) {
		this.position = position;
		this.couleur = couleur;
	}
	
	public void deplacer(Plateau plateau, int position){
		assert(peutDeplacer(plateau, position));
		this.position = position;
	}
	
	public abstract List<Deplacement> getDeplacementsPossibles(Plateau plateau);
	
	public boolean peutDeplacer(Plateau plateau, int nouvellePosition) {
		List<Deplacement> deplacementsPossibles = getDeplacementsPossibles(plateau);
		for(Deplacement dep : deplacementsPossibles) {
			if(dep.getNouvelleCoord() == nouvellePosition) {
				return true;
			}
		}
		return false;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public ICouleur getCouleur() {
		return this.couleur;
	}
	
}
