package pieces;

import java.util.List;

import couleur.ICouleur;
import jeu.Deplacement;
import jeu.Plateau;

public abstract class Piece implements IPiece{
	private int position;
	private ICouleur couleur;
	private String nom;
	
	/**
	 * Constructeur
	 * @param nom
	 * @param position
	 * @param couleur
	 */
	public Piece(String nom, int position, ICouleur couleur) {
		this.nom = nom;
		setPosition(position);
		this.couleur = couleur;
	}
	
	/**
	 * change la position de la pi�ce
	 */
	public void deplacer(Plateau plateau, int position){
		assert(peutDeplacer(plateau, position));
		setPosition(position);
	}
	
	public abstract List<Deplacement> getDeplacementsPossibles(Plateau plateau);
	
	/**
	 * v�rifie que la position envoy�e est la case d'arriv�e d'au moins un d�placement possible de la pi�ce
	 */
	public boolean peutDeplacer(Plateau plateau, int nouvellePosition) {
		List<Deplacement> deplacementsPossibles = getDeplacementsPossibles(plateau);
		for(Deplacement dep : deplacementsPossibles) {
			if(dep.getNouvelleCoord() == nouvellePosition) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * retourne la position de la pi�ce
	 */
	public int getPosition() {
		return this.position;
	}
	
	/**
	 * change la position de la pi�ce
	 * @param position
	 */
	private void setPosition(int position) {
		assert(Plateau.estCaseValide(position));
		this.position = position;
	}
	
	/**
	 * retourne la couleur de la pi�ce
	 */
	public ICouleur getCouleur() {
		return this.couleur;
	}
	
	/**
	 * @return affichage de la pi�ce
	 */
	public String toString() {
		if(getCouleur().estBlanc()) {
			return this.nom.toUpperCase();
		}
		return this.nom;
	}
	
}
