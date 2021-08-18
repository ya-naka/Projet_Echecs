package couleur;

public class Blanc extends Couleur{

	/**
	 * retourne vrai si le camp est Blanc
	 */
	@Override
	public boolean estBlanc() {
		return true;
	}

	/**
	 * retourne vrai si le camp est Noir
	 */
	@Override
	public boolean estNoir() {
		return false;
	}

	/**
	 * retourne la couleur du camp opposé
	 */
	@Override
	public ICouleur getCouleurOpposee() {
		return new Noir();
	}

	/**
	 * @return affichage Blanc
	 */
	@Override
	public String toString() {
		return "Blanc";
	}
}
