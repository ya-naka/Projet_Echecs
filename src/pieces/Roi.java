package pieces;

public class Roi extends Piece{

	public Roi(int x, int y, String couleur) {
		super(x, y, couleur);
	}

	@Override
	protected boolean peutDeplacer(int x, int y) {
		return false;
	}
	
	public String toString() {
		if(super.getCouleur().equals("blanc")) {
			return "r".toUpperCase();
		}
		return "r";
	}

}
