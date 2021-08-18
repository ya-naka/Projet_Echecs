package jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import couleur.Blanc;
import couleur.ICouleur;
import couleur.Noir;
import pieces.Fou;
import pieces.IPiece;
import pieces.Roi;
import pieces.Tour;

public class PlateauFactory {
	private static final int NB_TYPE_PIECES = 6; //nb max de type de pièces dans un jeu d'échec
	private static final int NB_CASES = 64; //nb de cases sur le plateau
	
	/**
	 * crée une liste de pièces (sélectionnées aléatoirement) à disposer sur un plateau pour commencer une partie
	 * @param nbPieces pour chaque camp
	 * @return
	 */
	public static List<IPiece> initAleatoire(int nbPieces){ //nb de pièces à placer sur le plateau pour chaque camp
		List<IPiece> pieces = new ArrayList<>();
		ICouleur blanc = new Blanc();
		ICouleur noir = new Noir();
		List<Integer> positionsPrises = new ArrayList<>();
		Random rand = new Random();
		
		//on choisit aléatoirement toutes les cases sur lesquelles les pièces se positionneront
		for(int i = 0; i < NB_TYPE_PIECES*2; i++) {
			int nouvPosition;
			do {
				nouvPosition = rand.nextInt(NB_CASES);
			}while(positionsPrises.contains(nouvPosition));
			positionsPrises.add(nouvPosition);
		}
		
		//on crée une liste pour les pièces noires et pour les pièces blanches
		List<IPiece> blancs = new ArrayList<>();
		List<IPiece> noirs = new ArrayList<>();
		
		//on crée toutes les pièces blanches possibles en leur assignant une place présélectionnée
		blancs.add(new Roi(positionsPrises.remove(rand.nextInt(positionsPrises.size())), blanc));
		blancs.add(new Tour(positionsPrises.remove(rand.nextInt(positionsPrises.size())), blanc));
		blancs.add(new Fou(positionsPrises.remove(rand.nextInt(positionsPrises.size())), blanc));
		
		//on crée toutes les pièces noires possibles en leur assignant une place présélectionnée
		noirs.add(new Roi(positionsPrises.remove(rand.nextInt(positionsPrises.size())), noir));
		noirs.add(new Tour(positionsPrises.remove(rand.nextInt(positionsPrises.size())), noir));
		noirs.add(new Fou(positionsPrises.remove(rand.nextInt(positionsPrises.size())), noir));
		
		//on ajoute les 2 rois
		pieces.add(blancs.remove(0));
		pieces.add(noirs.remove(0));
		
		for(int i = 0; i < nbPieces-1; i++) { // nbPieces-1 car les rois ont déjà été placés
			if(!blancs.isEmpty()) {
				pieces.add(blancs.remove(rand.nextInt(blancs.size())));
			}
			if(!noirs.isEmpty()) {
				pieces.add(noirs.remove(rand.nextInt(noirs.size())));
			}
		}
		
		return pieces;
	}
}
