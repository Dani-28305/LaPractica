package preguntas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import main.Constantes;

public class PreguntaIngles {
	static ArrayList<String> arrayPreguntasIngles = new ArrayList<String>();

	public static void generarArrayPreguntas() throws FileNotFoundException {
		File f1 = new File("src/ingles.txt");
		Scanner s1 = new Scanner(f1);
		while (s1.hasNext()) {
			arrayPreguntasIngles.add(s1.nextLine());
		}
		s1.close();

	}

	private static int generarMultiplo5() {
		Random r1 = new Random();
		int numRandom = r1.nextInt(arrayPreguntasIngles.size()/5);
		numRandom *=5;
		return numRandom;

	}

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
		}

		return respInt;
	}
	public static boolean generarPregunta() {
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
		String resp = s1.nextLine();
		int respInt= cambiarLetra(resp);
		if (respInt==respuestas.indexOf(correcta)) {
			System.out.println(Constantes.acierto);
			return true;
		}else {
			System.out.println(Constantes.fallo + correcta);
			return false;
		}
		
	}
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
		int respInt= numeroAleatorio;
		if (respInt==respuestas.indexOf(correcta)) {
			System.out.println(Constantes.acierto);
			return true;
		}else {
			System.out.println(Constantes.fallo + correcta);
			return false;
		}
		
	}
}
