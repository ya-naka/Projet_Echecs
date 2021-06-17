package couleur;

public class Blanc implements ICouleur{

	@Override
	public boolean estBlanc() {
		return true;
	}

	@Override
	public boolean estNoir() {
		return false;
	}

}
