import java.util.Random;

public class M_Fonctions {

	// Deux petit tableaux utilitaires pour générer des lettres et des chiffres.
	public static String LETTRES_MAJUSCULES = 
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static String[] OPERATIONS = new String[] { "+", "-", "/", "*" };
	
	public static int MAX_CHIFFRES = 10;


	/**
	 * Applique l'operation arithmétique entre nombre1 et nombre2.
	 *
	 * Le résultat ne peut pas être négatif.
	 *
	 * @param nombre1
	 * @param nombre2
	 * @param operation "_", "-" , "*" ou "/".
	 * @return entier positif
	 */
	public static int resultatOperation(int nombre1, 
			                                                  int nombre2, 
			                                                  String operation) {

		int resultat = 0;

		switch (operation) {
		
		case "/":
			resultat = nombre1 / nombre2;
			break;

		case "*":
			resultat = nombre1 * nombre2;
			break;
			
		case "+":
			resultat = nombre1 + nombre2;
			break;
			
		case "-":
			resultat = nombre1 - nombre2;
			break;
			
		// Purement pédagogique, peut-être enlevé.
		default:
			break;
		}

		// Que des nombres positifs.
		return Math.abs(resultat);

	}
	
	/**
	 * Retourne  le premier caractère d un int.
	 * Par ex 1231 -> '1'
	 * 
	 * @param monInt
	 * @return Le premier chiffre d'un entier en char.
	 */
	public static char premierCarDunEntier(int monInt){
		return premierCarDunString(entierAString(monInt));
	}

	/**
	 * Retourne le 1er caractère d'une chaîne.
	 * 
	 * @param string La chaîne à considérer.
	 * @return Le 1er caractère de string
	 */
	public static char premierCarDunString(String chaine){
		return chaine.charAt(0);
	}

	/**
	 * Transforme un int en String.
	 * @param Le nombreAconvertir
	 * @return Le même nombre version String.
	 */
	public static String entierAString(int nombreAconvertir){
		return "" + nombreAconvertir;
	}

	/**
	 * Génère une lettre entre deux bornes de la table ascii.
	 * @param min
	 * @param max
	 * @return une  lettre entre deux bornes de la table ascii.
	 */
	public static char charEntreDeuxCodeAscii(int min, int max){
		return (char) entierAleatoireEntreMinEtMax(min, max);
	}

	/**
	 * Génère un entier entre min et max.
	 * @param min
	 * @param max
	 * @return un entier entre min et max.
	 */

	public static int entierAleatoireEntreMinEtMax(int min, int max){
		return new Random().nextInt((max - min) + 1) + min;
	}

	/**
	 *
	 * @return Une lettre en majuscule.
	 */
	public static char lettreMajusculeAleatoire(){
		return LETTRES_MAJUSCULES
						.charAt((int) Math.floor(Math.random() * 
								LETTRES_MAJUSCULES.length()));
	}

	/**
	 *
	 * @return Un chiffre entre '0' et '9'.
	 */
	public static char chiffreAleatoire(){
		return premierCarDunEntier(new Random()
						.nextInt(MAX_CHIFFRES));
	}
}