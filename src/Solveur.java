import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Solver implementation
 */
public class Solveur {
	/**
	 * Entry point
	 */
	public static void main(String[] args) {
		File fichierSource = new File("source.txt");
		File fichierResultat = new File("resultat.txt");
		BufferedReader bufReader = null;
		BufferedWriter bufWriter = null;
		
		try {
			bufReader = new BufferedReader(new FileReader(fichierSource));
			bufWriter = new BufferedWriter(new FileWriter(fichierResultat));
		    
			String[] middles, middle, bottoms;

			middle = new String[2];
			middles = new String[10];
			bottoms = new String[10];
			
			// initialisation bidon pour démarrer la boucle
			String input = "initialisation";
			int k = 1;
			
			while(input != null) {
				System.out.println("Lecture de la serie " + k + " :");
				int i = 1;
				input = bufReader.readLine();
				
				while(input != null && !input.isEmpty()) {
					input = input.trim();
									
					if (i % 3 == 2) {
						// split the middle
						middles = input.split("  ");	
					} else if (i % 3 == 0) {
						// clean the bottom
						input = input.replaceAll("/_", "");
						input = input.replaceAll("_\\\\", "");
	
						// split the bottom
						bottoms = input.split(" ");
					} else {
						// top: do fuckin' nothing
					}
					i++;
					input = bufReader.readLine();
					System.out.println("Ligne "+i);
					if (input != null) {
						System.out.println(input.toString());
					}
				}
				System.out.println("");
				
				System.out.println("Résultat :");
				Triomino triomino;
				
				for (int j = 0; j < middles.length; j++) {
					middles[j] = middles[j].trim();
					middle = middles[j].split(" ");
	
					triomino = new Triomino(
						Integer.parseInt(middle[0], 16), 
						Integer.parseInt(middle[1], 16), 
						Integer.parseInt(bottoms[j], 16)
					);
					System.out.println("Triomino" + (j+1) + " = " + triomino.toString());
				}
				System.out.println("");
				k++;
			}
			
		} catch(IOException io) {
			io.printStackTrace();
			
		} finally {
		    try {
		       if (bufReader != null)
		    	   bufReader.close();
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		    try {
		       if (bufWriter != null)
		    	   bufWriter.close();
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		}
	}
}
