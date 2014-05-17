public class ColonneTriominos {
	
	private Triomino[] colonne = null;

	ColonneTriominos(int hauteur) {
		colonne = new Triomino[hauteur];
	}

	void set(int j, Triomino t) {
		colonne[j] = t;
	}

	Triomino get(int j) {
		if (colonne[j] != null ) {
			return colonne[j];
		} else {
			return (new Triomino(0, 0, 0));
		}
		
	}
}
