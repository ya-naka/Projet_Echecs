package pieces;

import java.util.List;

import couleur.ICouleur;
import jeu.Deplacement;

public abstract class Piece implements IPiece{
	private int position;
	private ICouleur couleur;
	
	public Piece(int position, ICouleur couleur) {
		this.position = position;
		this.couleur = couleur;
	}
	
	public void deplacer(int position){
		assert(peutDeplacer(position));
		this.position = position;
	}
	
	public abstract List<Deplacement> getDeplacementsPossibles();
	
	public boolean peutDeplacer(int nouvellePosition) {
		List<Deplacement> deplacementsPossibles = getDeplacementsPossibles();
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
