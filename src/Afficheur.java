/**
 * Classe Afficheur qui s'occupe d'afficher les problemes generes,
 * ainsi que les solution eventuellement trouvees. 
 */

public class Afficheur {
	
	private Jeu jeu;
	
	public Afficheur (Jeu jeu_p) {
		this.jeu = jeu_p;
	}
	
	public void afficherSolution() {
		
		System.out.println("Solution trouvee :");
		// il cree un plateau vide de taille N (size)
		Plateau p = new Plateau(jeu.getLargeur());
		
		// il place les triominos dans les colonnes du plateau
		int k = 0;
		for (int i = 0; i < jeu.getLargeur(); i++) {
		// il y a N (size) colonnes dans le plateau
			for (int j = 0; j < 2*i+1; j++) {
			// il y a 2*i+1 case dans la colonne i
				// il recupere le k ieme triomino dans le jeu et le place dans le plateau
				p.set(i, j, jeu.get(k));
				k++;
			}
		}
		affiche_plateau_mini(p);
	}

	/*  
	 * conversion d'un entier positif (<62) en caractere [0-9A-Za-z]
	 * ! inutile car on fixe la base a 16 !
	 */
//	static char onechar(int value) {
//		if (value >= 0) {
//			if (value < 10) {
//			// si la valeur est inferieure a 10 on l'affiche simplement
//				return (char) ('0'+value);
//			} else if (value < 36) {
//				return (char) ('A'+value-10);
//			} else if (value < 62) {
//				return (char) ('a'+value-36);
//			}
//		} 
//		return('\0');
//	}
	
	// methode qui renvoit une etoile comme valeur si le triomino est null
	// et qui renvoit la valeur si le triomino est non null
	// private static int charorstar(Triomino t, int c) {
	// 	if (t == null) {
	// 		return ('*');
	// 	} else {
	// 		return(c);
	//	}
	// }

	/*
	 * affiche un plateau, meme partiellement rempli.
	 */

	private static void affiche_plateau_mini(Plateau p) {
				
		for (int i = 0 ; i < p.largeur ; i++) {
		// boucle qui parcourt le plateau dans sa largeur
		// largeur plateau = nombre de colonnes = size = N
			for (int l = 0; l < 3; l++) {      
			// pour chaque colonne du plateau, il fait 3 passages dans cette boucle
			// c-a-d un passage par ligne necessaire a construire un triomino
				
				for(int k = 0 ; k < p.largeur-i; k++) {
				// il place 3 espaces au debut de la ligne autant de fois que le nombre d'etage ou on se trouve
				// c-a-d N-i fois (on commence par le haut donc par le dernier etage, 
				// quand i=0 on place donc N fois les 3 espaces)
					System.out.print("   ");
				}
				
				if (l == 0) {
					// si on se trouve sur la 1ere ligne alors on place 2 espaces
						System.out.print("  ");
					}
				if (l == 1) {
				// si on se trouve sur la 2e ligne alors on place 1 espace
					System.out.print(" ");
				}
				
				for (int j = 0 ; j <= 2*i ; j++ ){
					Triomino t = p.get(i, j);
					switch (l) {
					case 0:
					// 1ere ligne
						if (j%2 != 0) {
						// on verifie si le triomino a la pointe vers le haut ou vers le bas
							// s'il est vers le bas on ecrit a
							System.out.print("\\-" + Integer.toHexString(t.getA()).toUpperCase() + "-/");
							// System.out.print("\\-" + "x" + "-/");
						} else {
							// s'il est vers le haut on ecrit la pointe
							System.out.print("^");
						}
						break;
					case 1:
					// 2e ligne
						if (j%2 != 0) {
						// on verifie si le triomino a la pointe vers le haut ou vers le bas
							// s'il est vers le bas on ecrit d'abord b puis c  
							System.out.print(Integer.toHexString(t.getB()).toUpperCase() + " " 
											+ Integer.toHexString(t.getC()).toUpperCase());
							// System.out.print("x" + " " + "x");
						} else {
							// s'il est vers le haut on ecrit d'abord c puis b  
							System.out.print(Integer.toHexString(t.getC()).toUpperCase() + " " 
											+ Integer.toHexString(t.getB()).toUpperCase());
							// System.out.print("x x");
						}
						break;
					case 2:
					// 3e ligne
						if (j%2 != 0) {
						// on verifie si le triomino a la pointe vers le haut ou vers le bas
							// s'il est vers le bas on ecrit juste un espace pour faire la pointe
							System.out.print("v");
						} else {
							// s'il est vers le haut on ecrit le coin inferieur gauche puis a puis le coin inferieur droit
							System.out.print("/_" + Integer.toHexString(t.getA()).toUpperCase() + "_\\");
							// System.out.print("/_x_\\");
						}
					}
				}
				System.out.println("");
			}
		}
	}


}