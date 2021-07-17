package couleur;

public class Blanc extends Couleur{

	@Override
	public boolean estBlanc() {
		return true;
	}

	@Override
	public boolean estNoir() {
		return false;
	}

	@Override
	public String toString() {
		return "Blanc";
	}

	@Override
	public ICouleur getCouleurOpposee() {
		return new Noir();
	}
}
