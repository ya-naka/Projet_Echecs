package jeu;

import pieces.IPiece;
import utils.SaisieUtils;

public class Deplacement {
	private final int coordCaseActuelle;
	private final int coordNouvelleCase;
	private Plateau plateau;
	
	public Deplacement(Plateau plateau, String mouvement) {
		assert(SaisieUtils.estSaisieValide(mouvement));
		this.coordCaseActuelle = SaisieUtils.saisieToCoordonn�e(mouvement.substring(0, 2));
		this.coordNouvelleCase = SaisieUtils.saisieToCoordonn�e(mouvement.substring(2, 4));
		this.plateau = plateau;
	}
	
	public Deplacement(Plateau plateau, int positionActuelle, int nouvellePosition) {
		assert(Plateau.estCaseValide(positionActuelle) && Plateau.estCaseValide(nouvellePosition));
		this.coordCaseActuelle = positionActuelle;
		this.coordNouvelleCase = nouvellePosition;
		this.plateau = plateau;
	}
	
	//v�rifie si le d�placement respecte les r�gles du jeu
	public boolean estDeplacementValide() {
		//v�rifie qu'une pi�ce se trouve sur la case de d�part
		if(!this.plateau.getCase(this.coordCaseActuelle).estOccup�e()) {
			return false;
		}
		
		//v�rifie que les r�gles des d�placement des pieces soient respect�es
		IPiece piece = this.plateau.getCase(this.coordCaseActuelle).getPiece();
		boolean depPossible = false;
		for(Deplacement dep : piece.getDeplacementsPossibles(this.plateau)) {
			if(dep.equals(this)) {
				depPossible = true;
				break;
			}
		}
		if(!depPossible) {
			return false;
		}
		
		//v�rifie que les r�gles du plateau soient respect�es
		Plateau plateauCopie = this.plateau;
		//effectue le d�placement
		plateauCopie.deplacer(this);
		//v�rifie si le d�placement engendre un �chec dans le camp de la pi�ce d�plac�e
		boolean estEchec = plateauCopie.estEchec(piece.getCouleur());
		//revient � l'�tat pr�c�dent de la partie
		plateauCopie.revenirEnArriere();
		return !estEchec;
	}
	
	public int getCoordActuelle() {
		return this.coordCaseActuelle;
	}
	
	public int getNouvelleCoord() {
		return this.coordNouvelleCase;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this.getClass() != o.getClass()) {
			return false;
		}
		Deplacement dep = (Deplacement)o;
		return (dep.getCoordActuelle() == this.getCoordActuelle()) && (dep.getNouvelleCoord() == this.getNouvelleCoord());
	}

}
