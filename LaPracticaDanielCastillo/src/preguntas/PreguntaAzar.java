package preguntas;

import java.util.Random;
import java.util.Scanner;

import main.Constantes;

public class PreguntaAzar {

	public static int generarPregunta() {
		Scanner s1 = new Scanner(System.in);
		Random r1 = new Random();
		int correcto = r1.nextInt(1,11);
		System.out.println("Se ha lanzado un dado, di un numero del 1 al 10");
		System.out.println(correcto);
		String respuesta = s1.nextLine();
		respuesta=Constantes.quitarEspacios(respuesta);
		int respInt = Integer.parseInt(respuesta);
		if(respInt==correcto) {
			System.out.println("Acertaste, obtuviste " + correcto + " puntos");
			return correcto;
		}else {
			System.out.println("Fallaste, la respuesta correcta era " + correcto);
			return 0;
		}
	}
	public static int generarPreguntaCPU() {
		Scanner s1 = new Scanner(System.in);
		Random r1 = new Random();
		int correcto = r1.nextInt(11);
		int numAleatorioCpu = r1.nextInt(11);
		System.out.println("La CPU a elegido el número" + numAleatorioCpu);
		int respInt = numAleatorioCpu;
		if(respInt==correcto) {
			System.out.println("Acertaste, obtuviste " + correcto + " puntos");
			return correcto;
		}else {
			System.out.println("Fallaste, la respuesta correcta era " + correcto);
			return 0;
		}
	}
}
