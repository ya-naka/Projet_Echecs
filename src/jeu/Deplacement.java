package jeu;

import couleur.ICouleur;
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
	public boolean estDeplacementValide(ICouleur camp) {
		//v�rifie qu'une pi�ce se trouve sur la case de d�part
		if(!this.plateau.getCase(getCoordActuelle()).estOccup�e()) {
			System.out.println("case vide");
			System.out.println(this.plateau.toString());
			return false;
		}
		
		//v�rifie que les r�gles des d�placement des pieces soient respect�es
		IPiece piece = this.plateau.getCase(getCoordActuelle()).getPiece();
		
		if(!(piece.getCouleur().estMemeCouleur(camp))) {
			System.out.println("pas meme camp");
			return false;
		}
		boolean depPossible = false;
		for(Deplacement dep : piece.getDeplacementsPossibles(this.plateau)) {
			if(dep.equals(this)) {
				depPossible = true;
				break;
			}
		}
		if(!depPossible) {
			System.out.println("dep pas possible");
			return false;
		}
		
		//v�rifie que les r�gles du plateau soient respect�es
		//Plateau plateauCopie = this.plateau;
		//effectue le d�placement
		//plateauCopie.deplacer(this, piece.getCouleur());
		plateau.deplacer(this, piece.getCouleur());
		//v�rifie si le d�placement engendre un �chec dans le camp de la pi�ce d�plac�e
		//boolean estEchec = plateauCopie.estEchec(piece.getCouleur());
		boolean estEchec = plateau.estEchec(piece.getCouleur());
		//revient � l'�tat pr�c�dent de la partie
		//plateauCopie.revenirEnArriere();
		plateau.revenirEnArriere();
		if(estEchec) {
			System.out.println("est �chec");
		}
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

	public String toString() {
		return getCoordActuelle() + "-" + getNouvelleCoord();
	}
}
