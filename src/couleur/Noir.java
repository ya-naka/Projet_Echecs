package couleur;

public class Noir extends Couleur{

	/**
	 * retourne vrai si le camp est Blanc
	 */
	@Override
	public boolean estBlanc() {
		return false;
	}

	/**
	 * retourne vrai si le camp est Blanc
	 */
	@Override
	public boolean estNoir() {
		return true;
	}
	
	/**
	 * retourne la couleur du camp opposé
	 */
	@Override
	public ICouleur getCouleurOpposee() {
		return new Blanc();
	}
	
	/**
	 * @return affichage Noir
	 */
	@Override
	public String toString() {
		return "Noir";
	}

}
