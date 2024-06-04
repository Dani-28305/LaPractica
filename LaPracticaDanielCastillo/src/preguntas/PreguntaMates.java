package preguntas;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import main.Constantes;
import main.GestionLog;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
/**
 * Clase de gestión de las preguntas de mates
 * @author Daniel Castillo
 * @version 20240514
 */
public class PreguntaMates {
/**
 * Método para generar las preguntas de Mates
 * @return true si aciertas la pregunta
 */
	public static boolean generarPregunta() {
		String[] operaciones = new String[3];
		int numeroRandom, n = 0;
		int resultado;
		Scanner s1 = new Scanner(System.in);
		String expresion = "";
		operaciones[0] = "+";
		operaciones[1] = "-";
		operaciones[2] = "*";
		Random r1 = new Random();
		int numNumeros = r1.nextInt(4, 9);
		int numRandom2;
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for (int i = 0; i < numNumeros; i++) {
			numeroRandom = r1.nextInt(2, 13);
			numeros.add(numeroRandom);
		}
		for (int i = 0; i < numeros.size(); i++) {
			expresion += numeros.get(n);
			if (i != numeros.size() - 1)
				expresion += operaciones[numRandom2 = r1.nextInt(3)];
			n++;
		}
		System.out.println(expresion);
		Expression e = new ExpressionBuilder(expresion).build();
		resultado = (int) e.evaluate();
		System.out.println("Dime el resultado");
		System.out.println(resultado);
		try {
			if (resultado == s1.nextInt()) {
				System.out.println(Constantes.acierto);
				return true;
			}

			else {
				System.out.println(Constantes.fallo+resultado);
				return false;
			}
		} catch (Exception e2) {
			System.out.println("Fallo, no has introducido un numero correctamente");
			GestionLog.falloLog(e2.getMessage());
			return false;
		}

	}
}