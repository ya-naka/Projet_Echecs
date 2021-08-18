package couleur;

public abstract class Couleur implements ICouleur{

	@Override
	public abstract boolean estBlanc();

	@Override
	public abstract boolean estNoir();
	
	@Override 
	public abstract ICouleur getCouleurOpposee();

	/**
	 * retourne vrai si les couleurs sont identiques
	 * @param couleur
	 */
	public boolean estMemeCouleur(ICouleur couleur) {
		return this.getClass() == couleur.getClass();
	}

}
