import java.util.Random;

public class Triominos {
	
	public static void main(String args[]) {
		
		System.out.println("Jeu des triominos");
		Random generator = new Random();
		int base, size;
		// il choisit une base au hasard entre 2 et 16, 
		// c-a-d que les valeur des triominos qui seront generes seront <= a la base choisie
		// base =  2 + Math.abs(generator.nextInt())%15;
		// ! on fixe la base a 10 pour des raisons de commodite !
		base = 10;
		// il choisit une taille de probleme entre 1 et 6 
		// c-a-d la valeur de N pour creer un probleme a N² triominos
		size =  1 + Math.abs(generator.nextInt())%6;
		
		// il cree le jeu (qui lui cree les triominos)
		Jeu jeu = new Jeu(size, base);
		// il cree un plateau vide de taille N (size)
		Plateau p = new Plateau(size);
		// il affiche tous les triominos cotes a cotes, 12 par ligne 
		jeu.affiche();

		// il place les triominos dans les colonnes du plateau
		int k = 0;
		for (int i = 0; i < size; i++) {
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
	 * ! inutile car on fixe la base a 10 !
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
	static int charorstar(Triomino t, int c) {
		if (t != null) {
			return (c);
		} else {
			return('*');
		}
	}

	/*
	 * affiche un plateau, meme partiellement rempli.
	 */

	static void affiche_plateau_mini(Plateau p) {
				
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
					Triomino t = p.get(i,j);
					switch (l) {
					case 0:
					// 1ere ligne
						if (j%2 != 0) {
						// on verifie si le triomino a la pointe vers le haut ou vers le bas
							// s'il est vers le bas on ecrit a
							System.out.print("\\-" + charorstar(t, t.a) + "-/");
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
							System.out.print(charorstar(t, t.b)+" "+charorstar(t, t.c));
						} else {
							// s'il est vers le haut on ecrit d'abord c puis b  
							System.out.print(charorstar(t, t.c)+" "+charorstar(t, t.b));
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
							System.out.print("/_" + charorstar(t,t.a) + "_\\");
						}
					}
				}
				System.out.println("");
			}
		}
	}


}