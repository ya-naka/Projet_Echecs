package couleur;

public class Noir extends Couleur{

	@Override
	public boolean estBlanc() {
		return false;
	}

	@Override
	public boolean estNoir() {
		return true;
	}

}
