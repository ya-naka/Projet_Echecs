package couleur;

public class Noir implements ICouleur{

	@Override
	public boolean estBlanc() {
		return false;
	}

	@Override
	public boolean estNoir() {
		return true;
	}

}
