package jeu;

public abstract class Piece {
	private int x;
	private int y;
	
	public Piece(int x, int y) {
		setPosition(x,y);
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
}
