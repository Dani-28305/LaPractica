package preguntas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import main.Constantes;

public class PreguntaLengua {
	static ArrayList<String> arrayPreguntasLengua = new ArrayList<String>();

	public static void generarArrayPreguntas() throws FileNotFoundException {
		File f1 = new File("src/diccionario.txt");
		Scanner s1 = new Scanner(f1);

		while (s1.hasNext()) {
			arrayPreguntasLengua.add(s1.next());
		}
		s1.close();
	}

	public static boolean generarPregunta() {
		String respuesta;
		char[] respuestaChar;
		int numOcultar;
		Scanner s1 = new Scanner(System.in);
		Random r1 = new Random();
		respuesta = arrayPreguntasLengua.get(r1.nextInt(arrayPreguntasLengua.size() + 1));
		respuestaChar = respuesta.toCharArray();
		numOcultar = (int) respuestaChar.length / 3;
		for (int i = 0; i < numOcultar; i++) {
			int numAsterisco = r1.nextInt(respuestaChar.length);
			if (respuestaChar[numAsterisco] != '*') {
				respuestaChar[numAsterisco] = '*';
			} else {
				i--;
			}
		}
		System.out.println("Introduce la palabra correcta");
		for (int i = 0; i < respuestaChar.length; i++) {

			System.out.print(respuestaChar[i]);

		}
		System.out.println();
		if (s1.next().equalsIgnoreCase(respuesta)) {
			System.out.println(Constantes.acierto);
			return true;
		} else {
			System.out.println(Constantes.fallo + respuesta);
			return false;
		}

	}

}
