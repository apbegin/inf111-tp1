import java.util.Random;

public class Stats {

	public int bruteforce = 0;
	public int bruteForce2LettresPlusNombre = 0;
	public int bruteForceInterval = 0;
	public int retroIngenierie = 0;
	public Random rnd = new Random();
	public static int[] LETTRE_MINUSCULE_ASCII = { 97, 122 };

	public void bruteForce(Cadenas cadenas) {

		int position = 0;
		char car = 0;
		
		while (!cadenas.estOuvert()) {

			switch (rnd.nextInt(2)) {

			case 0:
				car = M_Fonctions.charEntreDeuxCodeAscii(LETTRE_MINUSCULE_ASCII[0],
						LETTRE_MINUSCULE_ASCII[1]);
				break;
				
			case 1: car = M_Fonctions.lettreMajusculeAleatoire();
				break;
			
			case 2: car = M_Fonctions.chiffreAleatoire();
				break;
			}

			if (cadenas.placerCarAlaPosition(car, position) == 0)
				position += 1;
		}
	}

	public void bruteForceOptimise(Cadenas cadenas){

		char car = 0;
		int position = 0;

		while(!cadenas.estOuvert()){
			
			if(position < 2){
				car = M_Fonctions.lettreMajusculeAleatoire();
			}
			else
			{
				car = M_Fonctions.chiffreAleatoire();
			}
			if(cadenas.placerCarAlaPosition(car, position) == 0){
				position += 1;
			}
		}
	}
	
	public void bruteForceOptimiseIntervalles()
}
