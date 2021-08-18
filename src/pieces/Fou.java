package pieces;

import java.util.ArrayList;
import java.util.List;

import couleur.ICouleur;
import jeu.Deplacement;
import jeu.Plateau;

public class Fou extends Piece{
	private static final int[] coordDeplacements = {-9, -7, 7, 9};
	private static final int[] valeur = {
			-20, -10, -10, -10, -10, -10, -10, -20,
			-10, 0, 0, 0, 0, 0, 0, -10,
			-10, 0, 5, 10, 10, 5, 0, -10,
			-10, 5, 5, 10, 10, 5, 5, -10,
			-10, 0, 10, 10, 10, 10, 0, -10,
			-10, 10, 10, 10, 10, 10, 10, -10,
			-10, 5, 0, 0, 0, 0, 5, -10,
			-20, -10, -10, -10, -10, -10, -10, -20
	};
	
	/**
	 * Constructeur
	 * @param position
	 * @param couleur
	 */
	public Fou(int position, ICouleur couleur) {
		super("f", position, couleur);
	}

	/**
	 * retourne tous les déplacements possibles de la pièce
	 */
	@Override
	public List<Deplacement> getDeplacementsPossibles(Plateau plateau) {
		List<Deplacement> depPossibles = new ArrayList<>();
		
		for(int i = 0; i < coordDeplacements.length; i++) {
			int position = super.getPosition() + coordDeplacements[i];
			while(Plateau.estCaseValide(position) 
					&& estSurTrajectoire(coordDeplacements[i], position)) {
				if(!plateau.getCase(position).estOccupée()) {
					depPossibles.add(new Deplacement(plateau, super.getPosition(), position));
				}else {
					if(!plateau.getCase(position).getPiece().getCouleur().estMemeCouleur(super.getCouleur())) {
						depPossibles.add(new Deplacement(plateau, super.getPosition(), position));
					}
					break;
				}
				position += coordDeplacements[i];
			}
		}
		return depPossibles;
	}
	
	/**
	 * vérifie si la position envoyée est sur la trajectoire de la pièce
	 * @param coord
	 * @param position
	 * @return
	 */
	public boolean estSurTrajectoire(int coord, int position) {
		return ((coord == 9 || coord == -7) && position%8 > super.getPosition()%8) || ((coord == -9 || coord == 7) && position%8 < super.getPosition()%8);
	}
	
	/**
	 * retourne la valeur de la pièce selon sa position
	 */
	@Override
	public int getValeur() {
		return valeur[super.getPosition()];
	}

}
