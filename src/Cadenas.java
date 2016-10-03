import java.util.Random;

/**
 * Repr�sente un cadenas virtuel dans le cadre de
 * 
 *
 * @author Mathieu Nayrolles et Pierre B�lisle (MNOP code inc).
 * copyright : septembre 2016
 *
 */
public class Cadenas {

	
    // Deux constantes pour afficher un simili cadenas.
	// \r\n pour sauter une ligne peut importe la plate-forme 
	// (Max, Linux, Windows).
	private static String OUVERT = 
			 "\r\n       .----------.     " + 
	         "\r\n      /   .-------.  \\" +
			 "\r\n     /  /             \\ \\.       " + 
	         "\r\n     |  |                          " + 
			 "\r\n     |  |                         " + 
	         "\r\n   _|  |__ ____     _    " + 
			 "\r\n .'  |_|               |_|    '. " + 
	         "\r\n '.____ ____ ____.' "	+ 
			 "\r\n |      .'______'.       | " + 
	         "\r\n '._.'.'  _____  '. '._.' " + 
			 "\r\n '__ |  INF111  | ._.' " +
			 "\r\n |     '.'._____.'.'      | " + 
			 "\r\n '.___'.____.'____.' " + 
			 "\r\n  '.___________.' ";
	
	
	private static String FERME = 
			 "\r\n       .----------.     " + 
	         "\r\n      /   .-------.  \\         " +
			 "\r\n     /  /             \\  \\       " + 
	         "\r\n     |  |               |  |         " + 
			 "\r\n     |  |               |  |         " + 
	         "\r\n   _|  |__ ____ |  | _    " + 
			 "\r\n .'  |_|               |_|    '. " + 
	         "\r\n '.____ ____ ____.' "	+ 
			 "\r\n |      .'______'.       | " + 
	         "\r\n '._.'.'  _____  '. '._.' " + 
			 "\r\n '__ |  INF111  | ._.' " +
			 "\r\n |     '.'._____.'.'      | " + 
			 "\r\n '.___'.____.'____.' " + 
			 "\r\n  '.___________.' ";
	
	// Les constantes	

	// Le nombre d'indices servant � la g�n�ration de la combinaison.
	// � modifier pour avoir une plus grande combinaison.
	public static final int MAX_INDICES = 10;
	
	// Nombre de lettres majuscules au d�part de la combinaison.
	private static int NB_LETTRES = 2;
	
	// Les attributs
	private boolean estOuvert = false;
	
	// � g�n�rer.
	private String combinaison = "";
	
	// En tout temps, on retient le nombre de lettre trouv�e.
	private int nbLettreTrouvee = 0;
	
	// Aide � g�n�rer la combinaison
	private int[] tabIndices = new int[MAX_INDICES];
	
	// Tableau de pr�sences.  Sert � retenir les caract�re d�voil�s.
	private boolean[] dejaTrouve;

	/**
	 * Constructeur
	 * 
	 * Cr�e un cadenas qui peut �tre ouvert ou non.   Il s'ouvre si tous les
	 * caract�res de la combinaison ont �t� trouv�s.  La v�rification se fait 
	 * � l'aide de la fonction bool�enne placerCarALaPosition.
	 * 
	 * L'�tat du cadenas est affich� en mode console ainsi que la combinaison
	 * lorsqu'elle est trouv�e.
	 */
	public Cadenas() {
		
		/*
		 * Les t�ches sont d�l�gu�es � des SP priv�s.
		 */
		genererCombinaison();
		
		// Il faut attendre d'avoir la combinaison pour cr�er le tableau de pr�sences.
		dejaTrouve = new boolean[combinaison.length()];
		
		fermer();
		
		// Utile pour le d�bogage.  On ne fait pas cela habituellement.
		afficherCadenas();
	}

	/**
	 * Remplit le tableau d'indices avec des chiffres al�atoires.
	 */
	private void genererTabIndices() {
		
		for (int i = 0; i < tabIndices.length; i++) {
				tabIndices[i] = 
						new Random().nextInt(M_Fonctions.MAX_CHIFFRES);
		}
	}

	/**
	 * Affiche l'�tat du cadenas
	 */
	private void afficherCadenas() {

		System.out.println();
		System.out.println();

		// Selon l'�tat de l'attribut qui est modifi� par placerCarAlaPosition.
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
	 * @param position La position � regarder.
	 * @param car La lettre propos�e pour la position.
	 * @return -1 Si la lettre propos�e est avant la lettre dans la table ascii.
	 * @return 1 Si la lettre propos�e est apr�s la lettre dans la table ascii.
	 * @return 0 Si la lettre propos�e est la bonne lettre.
	 */
	public int placerCarAlaPosition(char car, int position) {

		/**
		 * STRATEGIE: On r�cup�re le caract�re de la combinaison pour la 
		 * position re�ue puis on le compare avec le caract�re propos�.
		 *
		 * Si les caract�res sont �gaux, on incr�mente nbLettreTrouvee.
		 * Finalement, si tous les caract�res ont �t� trouv�s, alors on affiche
		 * le cadenas ouvert.
		 */
		char carCourant = combinaison.charAt(position);
		
		// Valeur � retourner.
		int etatRecherche = 0;

		// Pour l'aide au d�codage.
		System.out.print(car);

		if (carCourant == car) {
			
			// Retenir que ce caract�re vient d'�tre trouv�.
			dejaTrouve[nbLettreTrouvee] = true;
			nbLettreTrouvee++;
			
			// Indique que le caract�re est trouv�.
			System.out.print('*');
			
			// S'ils sont tous d�voil�e, on l'ouvre.
			if (nbLettreTrouvee == combinaison.length()) {
				estOuvert = true;
			}			
			
			// Affiche pour le suivi de la simulation.
			afficherCadenas();
			afficherStatusCombinaison();

		}
		// Retourne l'�tat selon la comparaison entre le caract�re courant 
		// et celui re�u.
		else {
			etatRecherche = (carCourant > car)?1:-1;
		}
		
		return etatRecherche;
	}

	/**
	 * Affiche les caract�res trouv�s. Les caract�res non trouv�s sont remplac�s
	 * par des *.
	 */
	private void afficherStatusCombinaison() {

		System.out.print("Combinaison : ");

		// Rappel: Le tableau de bool�ens et la combinaison ont la m�me taille.
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
	 * G�n�re les NB_LETTRES majuscules au d�but de la combinaison.
	 */
	private void genererPremiereLettre(){
	
   	        // Choisit les deux premi�res lettres au hasard et les concat�ne � la 
			// combinaison.
			for (int i = 0; i < NB_LETTRES; i++) {
				combinaison += 
						M_Fonctions.LETTRES_MAJUSCULES
							.charAt((int) Math.floor(Math.random() *
									M_Fonctions.LETTRES_MAJUSCULES.length()));
			}
	}
	
	/*
	 * G�n�re les chiffres de la combinaison en se servant d'un tableau d'indices
	 * g�n�rer au hasard.
	 */
	private void genererChiffres(){

		// G�n�rer le tableau d'indices.
		genererTabIndices();

		// Chaque caract�re suivant est le r�sultat de l'op�ration choisit au hasard.
		for (int i = 0; i < tabIndices.length; i = i + 2) {
			
			String op;

			// �viter la division par 0.
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
	 * G�n�re une combinaison pour le cadenas
	 */
	private void genererCombinaison() {

		/**
		 * STRATEGIE: On g�n�re une  combinaison compos�e de 2 lettres + un
		 * nombre. Le nombre est g�n�r� al�atoirement en utilisant les
		 * op�rations math�matiques standards entre chaque nombre du tableau
		 * tabIndices et en concatenant les r�sultats. Par ex:
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
	 * @return les nombres al�atoires utilis�s.
	 */
	public int[] tabIndices() {
		return tabIndices;
	}

	/**
	 *
	 * @return l'�tat du cadenas.
	 */
	public boolean estOuvert() {
		return estOuvert;
	}

	/*
	 * R�initialise les valeurs pour se retrouver dans l'�tat "pas ouvert". 
	 */
	public void fermer(){
		
		estOuvert = false;
		nbLettreTrouvee = 0;
				
		for(int i = 0; i < dejaTrouve.length;i++)
			dejaTrouve[i] = false;
	}
}