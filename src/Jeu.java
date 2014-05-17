import java.util.ArrayList;

public class Jeu {

	private int largeur;
	private ArrayList<Triomino> listeTriominos;
	
	Jeu(int largeur, ArrayList<Triomino> liste) {
		this.largeur = largeur;
		listeTriominos = liste;
	}

	// Affiche tous les triominos du jeu cotes a cotes, 12 par ligne 
	void affiche() {
		
		for(int i = 0; i < largeur*largeur; i+=12) {
			int j1, j2, j3;
			for(j1 = i ; (j1 < listeTriominos.size()) && (j1 < i+12) ; j1++) {
				System.out.print("   ^  ");
			}
			System.out.println("");
			
			for(j2 = i ; (j2 < listeTriominos.size()) && (j2 < i+12) ; j2++) {
				System.out.print("  "+ Integer.toHexString(listeTriominos.get(j2).getCenter()).toUpperCase() + " " 
									 + Integer.toHexString(listeTriominos.get(j2).getRight()).toUpperCase() + " ");
			}
			System.out.println("");
			
			for(j3 = i ; (j3 < listeTriominos.size()) && (j3 < i+12) ; j3++) {
				System.out.print(" /_" + Integer.toHexString(listeTriominos.get(j3).getLeft()).toUpperCase() + "_\\");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public Triomino getTriomino(int i) {
		return listeTriominos.get(i);
	}

	public int getSize() {
		return listeTriominos.size();
	}

	public int getLargeur() {
		return this.largeur;
	}
	
	public ArrayList<Triomino> getListeTriominos() {
		return this.listeTriominos;
	}

}