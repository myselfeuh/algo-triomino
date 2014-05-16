public class Plateau {
	
	int largeur;
	ColonneTriominos[] triominos = null;
	
	Plateau(int largeur) {
		this.largeur = largeur;
		triominos = new ColonneTriominos[largeur];
		
		for(int i = 0; i < largeur; i++) {
			triominos[i] = new ColonneTriominos(2*i+1);
		}
	}

	// place le triomino t dans la case j de la colonne i
	void set(int i, int j, Triomino t) {
		triominos[i].set(j, t);
	}
	
	// retourne le triomino t de la case j de la colonne i
	Triomino get(int i, int j) {
		return triominos[i].get(j);
	}

}

