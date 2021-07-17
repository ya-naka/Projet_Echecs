package pieces;

import java.util.ArrayList;
import java.util.List;

import couleur.ICouleur;
import jeu.Deplacement;
import jeu.Plateau;

public class Roi extends Piece{
	private static final int[] coordDeplacements = {-9, -8, -7, -1, 1, 7, 8, 9}; 
	private static final int[] valeur = {
			-50, -30, -30, -30, -30, -30, -30, -50,
			-30, -30, 0, 0, 0, 0, -30, -30,
			-30, -10, 20, 30, 30, 20, -10, -30,
			-30, -10, 30, 40, 40, 30, -10, -30, 
			-30, -10, 30, 40, 40, 30, -10, -30, 
			-30, -10, 20, 30, 30, 20, -10, -30,
			-30, -20, -10, 0, 0, -10, -20, -30,
			-50, -40, -30, -20, -20, -30, -40, -50
	};

	public Roi(int position, ICouleur couleur) {
		super(position, couleur);
	}
	
	public String toString() {
		if(super.getCouleur().estBlanc()) {
			return "r".toUpperCase();
		}
		return "r";
	}

	@Override
	public List<Deplacement> getDeplacementsPossibles(Plateau plateau) {
		List<Deplacement> depPossibles = new ArrayList<>();
		
		for(int i = 0; i < coordDeplacements.length; i++) {
			int position = coordDeplacements[i]+super.getPosition();
			if(Plateau.estCaseValide(position) && estCaseAdjacente(coordDeplacements[i])) {
				if(!plateau.getCase(position).estOccupée()) {
					depPossibles.add(new Deplacement(plateau, super.getPosition(), position));
				}else if(!plateau.getCase(position).getPiece().getCouleur().estMemeCouleur(super.getCouleur())) {
					depPossibles.add(new Deplacement(plateau, super.getPosition(), position));
				}
			}
		}
		return depPossibles;
	}
	
	public boolean estCaseAdjacente(int coordDeplacement) {
		//si première colonne
		if(super.getPosition()%8 == 0) {
			return ! (coordDeplacement == 7 || coordDeplacement == -1 || coordDeplacement == -9);
		}
		//si dernière colonne
		if(super.getPosition()%8 == 7) {
			return ! (coordDeplacement == -7 || coordDeplacement == 1 || coordDeplacement == 9);
		}
		return true;
	}

	@Override
	public int getValeur() {
		return valeur[super.getPosition()];
	}

}
