package pieces;

import jeu.DeplacerException;

public abstract class Piece implements IPiece{
	private int x;
	private int y;
	private String couleur;
	
	public Piece(int x, int y, String couleur) {
		setPosition(x,y);
		this.couleur = couleur;
	}
	
	protected void deplacer(int x, int y) throws DeplacerException {
		if(peutDeplacer(x,y)) {
			setPosition(x,y);
		}else {
			throw new DeplacerException();
		}
	}
	
	protected abstract boolean peutDeplacer(int x, int y);
	
	private void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public String getCouleur() {
		return this.couleur;
	}
	
}
