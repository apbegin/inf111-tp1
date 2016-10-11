import java.util.Random;

/**
 * Représente un cadenas virtuel dans le cadre de
 * 
 *
 * @author Mathieu Nayrolles et Pierre Bélisle (MNOP code inc).
 * copyright : septembre 2016
 *
 */
public class Cadenas {

	
    // Deux constantes pour afficher un simili cadenas.
	// \r\n pour sauter une ligne peut importe la plate-forme 
	// (Max, Linux, Windows).
	private static String OUVERT = 
					"\r\n      .--------. "+          
					"\r\n     / .------. \\"+
					"\r\n    / /        \\ \\"+           
					"\r\n    | |         "+
					"\r\n    | |         "+
					"\r\n   _| |________   _ "+
					"\r\n .' |_|        |_| '. "+
					"\r\n '._____ ____ _____.' "+
					"\r\n |     .'____'.     | "+
					"\r\n '.__.'.'    '.'.__.' "+
					"\r\n '.__  |INF111|  __.' "+
					"\r\n |   '.'.____.'.'   | "+
					"\r\n '.____'.____.'____.' "+
					"\r\n '.________________.' ";
	
	
	private static String FERME = 
			"\r\n      .--------. "+          
			"\r\n     / .------. \\"+
			"\r\n    / /        \\ \\"+           
			"\r\n    | |        | |"+
			"\r\n    | |        | |"+
			"\r\n   _| |________| |_"+
			"\r\n .' |_|            '."+
			"\r\n '._____ ____ _____.' "+
			"\r\n |     .'____'.     | "+
			"\r\n '.__.'.'    '.'.__.' "+
			"\r\n '.__  |INF111|  __.' "+
			"\r\n |   '.'.____.'.'   | "+
			"\r\n '.____'.____.'____.' "+
			"\r\n '.________________.' ";
	
	
	

	
	// Les constantes	

	// Le nombre d'indices servant à la génération de la combinaison.
	// à modifier pour avoir une plus grande combinaison.
	public static final int MAX_INDICES = 10;
	
	// Nombre de lettres majuscules au départ de la combinaison.
	private static int NB_LETTRES = 2;
	
	// Les attributs
	private boolean estOuvert = false;
	
	// à générer.
	private String combinaison = "";
	
	// En tout temps, on retient le nombre de lettre trouvée.
	private int nbLettreTrouvee = 0;
	
	// Aide à générer la combinaison
	private int[] tabIndices = new int[MAX_INDICES];
	
	// Tableau de présences.  Sert à retenir les caractère dévoilés.
	private boolean[] dejaTrouve;

	/**
	 * Constructeur
	 * 
	 * Crée un cadenas qui peut être ouvert ou non.   Il s'ouvre si tous les
	 * caractères de la combinaison ont été trouvés.  La vérification se fait 
	 * à l'aide de la fonction booléenne placerCarALaPosition.
	 * 
	 * L'état du cadenas est affiché en mode console ainsi que la combinaison
	 * lorsqu'elle est trouvée.
	 */
	public Cadenas() {
		
		/*
		 * Les tâches sont déléguées à des SP privés.
		 */
		genererCombinaison();
		
		// Il faut attendre d'avoir la combinaison pour créer le tableau de présences.
		dejaTrouve = new boolean[combinaison.length()];
		
		fermer();
		
		// Utile pour le débogage.  On ne fait pas cela habituellement.
		afficherCadenas();
	}

	/**
	 * Remplit le tableau d'indices avec des chiffres aléatoires.
	 */
	private void genererTabIndices() {
		
		for (int i = 0; i < tabIndices.length; i++) {
				tabIndices[i] = 
						new Random().nextInt(M_Fonctions.MAX_CHIFFRES);
		}
	}

	/**
	 * Affiche l'état du cadenas
	 */
	private void afficherCadenas() {

		System.out.println();
		System.out.println();

		// Selon l'état de l'attribut qui est modifié par placerCarAlaPosition.
		if (estOuvert) {
			System.out.println(OUVERT);
		} else {
			System.out.println(FERME);
		}

		System.out.println();
		System.out.println();
	}

	/**
	 *
	 * Regarde sur un couple lettre/position est valide pour la combinaison du
	 * cadenas.
	 *
	 * @param position La position à regarder.
	 * @param car La lettre proposée pour la position.
	 * @return -1 Si la lettre proposée est avant la lettre dans la table ascii.
	 * @return 1 Si la lettre proposée est après la lettre dans la table ascii.
	 * @return 0 Si la lettre proposée est la bonne lettre.
	 */
	public int placerCarAlaPosition(char car, int position) {

		/**
		 * STRATEGIE: On récupère le caractère de la combinaison pour la 
		 * position reçue puis on le compare avec le caractère proposé.
		 *
		 * Si les caractères sont égaux, on incrémente nbLettreTrouvee.
		 * Finalement, si tous les caractères ont été trouvés, alors on affiche
		 * le cadenas ouvert.
		 */
		char carCourant = combinaison.charAt(position);
		
		// Valeur à retourner.
		int etatRecherche = 0;

		// Pour l'aide au décodage.
		System.out.print(car);

		if (carCourant == car) {
			
			// Retenir que ce caractère vient d'être trouvé.
			dejaTrouve[nbLettreTrouvee] = true;
			nbLettreTrouvee++;
			
			// Indique que le caractère est trouvé.
			System.out.print('*');
			
			// S'ils sont tous dévoilée, on l'ouvre.
			if (nbLettreTrouvee == combinaison.length()) {
				estOuvert = true;
			}			
			
			// Affiche pour le suivi de la simulation.
			afficherCadenas();
			afficherStatusCombinaison();

		}
		// Retourne l'état selon la comparaison entre le caractère courant 
		// et celui reçu.
		else {
			etatRecherche = (carCourant > car)?1:-1;
		}
		
		return etatRecherche;
	}

	/**
	 * Affiche les caractères trouvés. Les caractères non trouvés sont remplacés
	 * par des *.
	 */
	private void afficherStatusCombinaison() {

		System.out.print("Combinaison : ");

		// Rappel: Le tableau de booléens et la combinaison ont la même taille.
		for (int i = 0; i < dejaTrouve.length; i++) {

			if(dejaTrouve[i]){
				
				System.out.print(combinaison.charAt(i));
			}
			else{
				System.out.print("*");				
			}
		}

		System.out.println();
	}
	
	/*
	 * Génére les NB_LETTRES majuscules au début de la combinaison.
	 */
	private void genererPremiereLettre(){
	
   	        // Choisit les deux premières lettres au hasard et les concaténe à la 
			// combinaison.
			for (int i = 0; i < NB_LETTRES; i++) {
				combinaison += 
						M_Fonctions.LETTRES_MAJUSCULES
							.charAt((int) Math.floor(Math.random() *
									M_Fonctions.LETTRES_MAJUSCULES.length()));
			}
	}
	
	/*
	 * Génére les chiffres de la combinaison en se servant d'un tableau d'indices
	 * générer au hasard.
	 */
	private void genererChiffres(){

		// Générer le tableau d'indices.
		genererTabIndices();

		// Chaque caractère suivant est le résultat de l'opération choisit au hasard.
		for (int i = 0; i < tabIndices.length; i = i + 2) {
			
			String op;

			// éviter la division par 0.
			do{
				op = M_Fonctions.OPERATIONS[(int) Math.floor(Math.random() *
						M_Fonctions.OPERATIONS.length)];
			} while( tabIndices[i + 1] == 0 && op == "/");
			
			combinaison += 
					M_Fonctions.resultatOperation(tabIndices[i], 
							                      tabIndices[i + 1],
							                      op);
		}

	}
	/**
	 * Génére une combinaison pour le cadenas
	 */
	private void genererCombinaison() {

		/**
		 * STRATEGIE: On génère une  combinaison composée de 2 lettres + un
		 * nombre. Le nombre est généré aléatoirement en utilisant les
		 * opérations mathématiques standards entre chaque nombre du tableau
		 * tabIndices et en concatenant les résultats. Par ex:
		 *
		 * numbers : 2 7 9 5 7 3 
		 *                        2+7 = 9 (MDP : AA9) 
		 *                        9-5 = 4 (MDP : AA94) 
		 *                        7*3 = 21 (MDP : AA9421)
		 */

		genererPremiereLettre();
		genererChiffres();

	}

	/**
	 *
	 * @return les nombres aléatoires utilisés.
	 */
	public int[] tabIndices() {
		return tabIndices;
	}

	/**
	 *
	 * @return l'état du cadenas.
	 */
	public boolean estOuvert() {
		return estOuvert;
	}

	/*
	 * Réinitialise les valeurs pour se retrouver dans l'état "pas ouvert". 
	 */
	public void fermer(){
		
		estOuvert = false;
		nbLettreTrouvee = 0;
				
		for(int i = 0; i < dejaTrouve.length;i++)
			dejaTrouve[i] = false;
	}
}