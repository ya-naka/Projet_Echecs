package couleur;

public abstract class Couleur implements ICouleur{

	@Override
	public abstract boolean estBlanc();

	@Override
	public abstract boolean estNoir();
	
	@Override 
	public abstract ICouleur getCouleurOpposee();

	public boolean estMemeCouleur(ICouleur couleur) {
		return this.getClass() == couleur.getClass();
	}

}
