package pieces;

import java.util.ArrayList;
import java.util.List;

import couleur.ICouleur;
import jeu.Deplacement;
import jeu.Plateau;

public class Tour extends Piece{
	private static final int[] coordDeplacements = {-8, -1, 1, 8};
	private static final int[] valeur = {
			0, 5, 5, 5, 5, 5, 5, 0,
			5, 10, 10, 10, 10, 10, 10, 5,
			5, 10, 10, 10, 10, 10, 10, 5,
			5, 10, 10, 10, 10, 10, 10, 5,
			5, 10, 10, 10, 10, 10, 10, 5,
			5, 10, 10, 10, 10, 10, 10, 5,
			5, 10, 10, 10, 10, 10, 10, 5,
			0, 5, 5, 5, 5, 5, 5, 0
	};
	
	/**
	 * Constructeur
	 * @param position
	 * @param couleur
	 */
	public Tour(int position, ICouleur couleur) {
		super("t", position, couleur);
	}

	/**
	 * retourne tous les déplacements possibles de la pièce
	 */
	@Override
	public List<Deplacement> getDeplacementsPossibles(Plateau plateau) {
		List<Deplacement> depPossibles = new ArrayList<>();
		
		for(int i = 0; i < coordDeplacements.length; i++) {
			int position = super.getPosition() + coordDeplacements[i];
			//on inspecte chaque case se trouvant sur la trajectoire
			while(Plateau.estCaseValide(position)) {
				if(estSurTrajectoire(position)) {
					if(!plateau.getCase(position).estOccupée()) {
						depPossibles.add(new Deplacement(plateau, super.getPosition(), position));
					}else {
						if(!plateau.getCase(position).getPiece().getCouleur().estMemeCouleur(super.getCouleur())) {
							depPossibles.add(new Deplacement(plateau, super.getPosition(), position));
						}
						break;
					}
				}
				position += coordDeplacements[i];
			}
		}
		return depPossibles;
	}
	
	/**
	 * vérifie si la position envoyée est sur la trajectoire de la pièce
	 * @param position
	 * @return
	 */
	public boolean estSurTrajectoire(int position) {
		//vérifie si la nouvelle position est sur la même ligne ou colonne de la position initiale
		return (position/8 == super.getPosition()/8) || (position%8 == super.getPosition()%8);
	}

	/**
	 * retourne la valeur de la pièce selon sa position
	 */
	@Override
	public int getValeur() {
		return valeur[super.getPosition()];
	}

}
