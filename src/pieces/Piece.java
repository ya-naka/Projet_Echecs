package pieces;

import java.util.List;

import couleur.ICouleur;
import jeu.Deplacement;

public abstract class Piece implements IPiece{
	private int position;
	private ICouleur couleur;
	
	public Piece(int position, ICouleur couleur) {
		//setPosition(position);
		this.position = position;
		this.couleur = couleur;
	}
	
	protected void deplacer(int position){
		if(peutDeplacer(position)) {
			//setPosition(x,y);
			this.position = position;
		}else {}
	}
	
	public abstract List<Deplacement> getDeplacementsPossibles();
	
	protected abstract boolean peutDeplacer(int nouvellePosition);
	
	/*private void setPosition(int position) {
		this.position = position;
	}*/
	
	public int getPosition() {
		return this.position;
	}
	
	public ICouleur getCouleur() {
		return this.couleur;
	}
	
}
