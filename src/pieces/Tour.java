package pieces;

public class Tour extends Piece{

	public Tour(int x, int y, String couleur) {
		super(x, y, couleur);
	}

	@Override
	protected boolean peutDeplacer(int x, int y) {
		return false;
	}
	
	public String toString() {
		if(super.getCouleur().equals("blanc")) {
			return "t".toUpperCase();
		}
		return "t";
	}

}
