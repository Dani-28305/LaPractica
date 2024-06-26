package preguntas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import main.Constantes;

/**
 * Clase para gestionar las preguntas de Ingles
 */
public class PreguntaIngles {
	static ArrayList<String> arrayPreguntasIngles = new ArrayList<String>();

	/**
	 * Metodo para generar un arrayList con todas las preguntas y respuestas de
	 * ingles leyendolas del archivo ingles.txt
	 * 
	 * @throws FileNotFoundException Cuando no se puede leer correctamente el
	 *                               archivo ingles.txt
	 */
	public static void generarArrayPreguntas() throws FileNotFoundException {
		File f1 = new File("src/ingles.txt");
		Scanner s1 = new Scanner(f1);
		while (s1.hasNext()) {
			arrayPreguntasIngles.add(s1.nextLine());
		}
		s1.close();

	}

	/**
	 * Metodo para generar un numero random que equivalga a la linea de una pregunta
	 * en el ArrayList de preguntasIngles
	 * 
	 * @return Número entero random múltiplo de 5
	 */
	private static int generarMultiplo5() {
		Random r1 = new Random();
		int numRandom = r1.nextInt(arrayPreguntasIngles.size() / 5);
		numRandom *= 5;
		return numRandom;

	}

	/**
	 * Metodo para cambiar las letras de la respuesta por numeros
	 * 
	 * @param resp Parámetro que contiene la letra de la respuesta
	 * @return numero entero equivalente de cada letra
	 */
	private static int cambiarLetra(String resp) {
		int respInt = -1;
		if (resp.equalsIgnoreCase("A") || resp.equalsIgnoreCase("a")) {
			respInt = 0;
		} else if (resp.equalsIgnoreCase("B") || resp.equalsIgnoreCase("b")) {
			respInt = 1;
		} else if (resp.equalsIgnoreCase("C") || resp.equalsIgnoreCase("c")) {
			respInt = 2;
		} else if (resp.equalsIgnoreCase("D") || resp.equalsIgnoreCase("d")) {
			respInt = 3;
		} else if (resp.equalsIgnoreCase("Comodin 50")) {
			respInt = 4;
		}

		return respInt;
	}

/**
 * Metodo para generar preguntas de Ingles
 * @return true si se acierta la pregunta
 */
	public static int generarPregunta() {
		int preguntaInt = generarMultiplo5();
		Scanner s1 = new Scanner(System.in);
		Random r1 = new Random();
		String correcta = arrayPreguntasIngles.get(preguntaInt + 1);
		ArrayList<String> respuestas = new ArrayList<String>();
		ArrayList<String> respuestas50 = new ArrayList<String>();
		respuestas50=respuestas;
		for (int i = 1; i < 5; i++) {
			respuestas.add(arrayPreguntasIngles.get(preguntaInt + i));
		}
		Collections.shuffle(respuestas);
		System.out.println(arrayPreguntasIngles.get(preguntaInt));
		System.out.println("A : " + respuestas.get(0));
		System.out.println("B : " + respuestas.get(1));
		System.out.println("C : " + respuestas.get(2));
		System.out.println("D : " + respuestas.get(3));
		System.out.println("COMODIN 50");
		String resp = s1.nextLine();
		int respInt = cambiarLetra(resp);
		if (respInt==4) {
			int numRand = r1.nextInt(4);
			int contRestas=0;
			for (int i = 0;i<2;i++) {
				if(numRand!=respuestas50.indexOf(correcta)) {
					respuestas50.remove(numRand);
					 numRand = r1.nextInt(3);
					 contRestas++;
				} else if(contRestas==0) {
					numRand = r1.nextInt(4);
					i--;
				}else {
					numRand = r1.nextInt(3);
				}
			}
			Collections.shuffle(respuestas50);
			System.out.println("A : " + respuestas.get(0));
			System.out.println("B : " + respuestas.get(1));
			resp = s1.nextLine();
			respInt = cambiarLetra(resp);
			if (respInt == respuestas50.indexOf(correcta)) {
				System.out.println(Constantes.acierto);
				return 1;
			} else {
				System.out.println(Constantes.fallo + correcta);
				System.out.println("Se restara un punto");
				return 2;
			}
		}else {
			if (respInt == respuestas.indexOf(correcta)) {
				System.out.println(Constantes.acierto);
				return 1;
			} else {
				System.out.println(Constantes.fallo + correcta);
				return 0;
			}
		}
		

	}

	/**
	 * Metodo para generar pregunta de Ingles para la CPU
	 * 
	 * @param numeroAleatorio Parámetro que contiene la respuesta aleatoria de la
	 *                        CPU
	 * @return true si acierta la pregunta
	 */
	public static boolean generarPreguntaCPU(int numeroAleatorio) {
		int preguntaInt = generarMultiplo5();
		Scanner s1 = new Scanner(System.in);
		String correcta = arrayPreguntasIngles.get(preguntaInt + 1);
		ArrayList<String> respuestas = new ArrayList<String>();
		for (int i = 1; i < 5; i++) {
			respuestas.add(arrayPreguntasIngles.get(preguntaInt + i));
		}
		Collections.shuffle(respuestas);
		System.out.println(arrayPreguntasIngles.get(preguntaInt));
		System.out.println("A : " + respuestas.get(0));
		System.out.println("B : " + respuestas.get(1));
		System.out.println("C : " + respuestas.get(2));
		System.out.println("D : " + respuestas.get(3));
		int respInt = numeroAleatorio;
		if (respInt == respuestas.indexOf(correcta)) {
			System.out.println(Constantes.acierto);
			return true;
		} else {
			System.out.println(Constantes.fallo + correcta);
			return false;
		}

	}
}
