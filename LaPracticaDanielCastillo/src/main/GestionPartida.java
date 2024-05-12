package main;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import jugadores.CPU;
import jugadores.Humanos;
import jugadores.Jugadores;
import preguntas.PreguntaIngles;
import preguntas.PreguntaLengua;
import preguntas.PreguntaMates;

/**
 * Clase que gestiona todo el funcionamiento de la partida del programa
 * 
 * @author Daniel Castillo
 * @version 20240512
 */
public class GestionPartida {
	/**
	 * Metodo para preguntar el tipo de partida que se quiera jugar
	 * 
	 * @return el numero de rondas que se van a jugar
	 */
	private static int numRondas() {
		Scanner s1 = new Scanner(System.in);
		int numero;
		try {

			System.out.println("Dime que tipo de partida quieres jugar");
			System.out.println("1. Partida Rapida");
			System.out.println("2. Partida Corta");
			System.out.println("3. Partida Normal");
			System.out.println("4. Partida Larga");

			numero = s1.nextInt();
			if (numero < 5 && numero > 0)
				if (numero == 1)
					numero = 3;
				else if (numero == 2)
					numero = 5;
				else if (numero == 3)
					numero = 10;
				else if (numero == 4)
					numero = 20;
				else {
					System.out.println("Numero mal introducido, cargando partida rapida por defecto");
					numero = 3;
				}
		} catch (Exception e) {
			System.out.println("Numero mal introducido, cargando partida rapida por defecto");
			numero = 1;
			GestionLog.FalloLog(e.getMessage());
		}
		return numero;

	}

	/**
	 * Metodo para pintar quien es el ganador de la partida
	 * 
	 * @param lista Parametro con los jigadores de la partida
	 */
	private static String ganador(ArrayList<Jugadores> lista) {
		Jugadores ganador = new Humanos(0, "default");
		for (Jugadores jugador1 : lista) {
			for (Jugadores jugador2 : lista) {
				if (jugador1.getPuntos() > jugador2.getPuntos()) {
					ganador = jugador1;
				}
			}
		}
		if (ganador.getNombre().equalsIgnoreCase("default"))
			//System.out.println("Empate en la partida");
		return "Empate";
		else
			return ganador.getNombre();
			//System.out.println("El ganador de la partida a sido " + ganador.getNombre());
	}

	/**
	 * Metodo privado para saber los jugadores de la partida
	 * 
	 * @return ArrayList con los jugadores que van a jugar la partida
	 */
	private static ArrayList preguntarJugadores() {
		Scanner s1 = new Scanner(System.in);
		boolean encontrado = false;
		ArrayList<Jugadores> jugadoresPartida = new ArrayList<Jugadores>();
		try {
			ArrayList<String> nombresJugadores = (ArrayList<String>) Files.readAllLines(Constantes.archivoJugadores);

			System.out.println("¿Cuantos jugadores van a jugar?");
			int numeroJugadores = s1.nextInt();
			if (numeroJugadores > 4) {
				System.out.println("El número maximo de jugadores por partida son 4, cambiando los jugadores a 4");
				numeroJugadores = 4;
			}
			System.out.println("¿Cuantos son humanos?");
			int jugadoresHumanos = s1.nextInt();
			if (jugadoresHumanos > numeroJugadores) {
				System.out.println(
						"Numero de Humanos superior al de jugadores, el numero de humanos pasara a ser el mismo que de jugadores");
				jugadoresHumanos = numeroJugadores;
			}
			System.out.println("Dime el nombre de los jugadores");
			for (int i = 0; i < jugadoresHumanos; i++) {
				String jugador = s1.next();
				for (String n : nombresJugadores) {
					if (n.equalsIgnoreCase(jugador)) {
						jugadoresPartida.add(new Humanos(0, jugador));
						encontrado = true;
						System.out.println("Encontrado");
					}
				}
				if (!encontrado) {
					System.out.println("Nombre no encontrado, vuelve a introducirlo");
					i--;
				}
				encontrado = false;
			}
			numeroJugadores -= jugadoresHumanos;
			for (int i = 0; i < numeroJugadores; i++) {
				String nombre = "CPU" + (i + 1);
				jugadoresPartida.add(new CPU(0, nombre));
			}
		} catch (Exception e) {
			System.out.println(
					"Error en la seleccion de jugadores, empezando con un jugador por defecto" + e.getMessage());
			jugadoresPartida.add(new Humanos(0, "default"));
			GestionLog.FalloLog(e.getMessage());
		}
		return jugadoresPartida;
	}

	/**
	 * Metodo para jugar la partida
	 */
	public static void jugarPartida() {
		ArrayList<Jugadores> jugadoresPartida = preguntarJugadores();
		Random r1 = new Random();
		int numeroRandom;
		Collections.shuffle(jugadoresPartida);
		int rondas = numRondas();
		GestionLog.InicioPartidaLog(rondas, jugadoresPartida);
		try {
			for (int i = 0; i < rondas; i++) {
				System.out.println("Ronda número " + i + " de " + rondas);
				for (Jugadores n : jugadoresPartida) {
					n.mostrar();
				}
				for (Jugadores n : jugadoresPartida) {
					numeroRandom = r1.nextInt(0, 3);

					System.out.println("-- Turno de " + n.getNombre());
					if (numeroRandom == 0) {
						if (n instanceof CPU) {
							System.out.println("La CPU ha elegido una repuesta al azar(como simpre...)");
							int respuestaAleatoria = r1.nextInt(1, 5);
							PreguntaIngles.generarPreguntaCPU(respuestaAleatoria);

						} else if (PreguntaIngles.generarPregunta()) {
							n.setPuntos(n.getPuntos() + 1);
						}
					} else if (numeroRandom == 1) {
						if (n instanceof CPU) {
							System.out.println("La CPU ha fallado su pregunta de Lengua(como simpre...)");
						} else if (PreguntaLengua.generarPregunta()) {
							n.setPuntos(n.getPuntos() + 1);
						}
					} else if (numeroRandom == 2) {
						if (n instanceof CPU) {
							System.out.println("La CPU ha acertado su pregunta de Mates(como simpre...)");
							n.setPuntos(n.getPuntos() + 1);
						} else if (PreguntaMates.generarPregunta()) {
							n.setPuntos(n.getPuntos() + 1);
						}
					}
				}
			}
			if (ganador(jugadoresPartida).equals("Empate")) {
				System.out.println("Empate");
			}else	System.out.println("El ganador de la partida a sido "+ganador(jugadoresPartida));
			GestionHistorico.escribirHistorico(jugadoresPartida);
			GestionRanking.actualizarRanking(jugadoresPartida);
			GestionLog.FinPartidaLog(rondas, ganador(jugadoresPartida), jugadoresPartida);

		} catch (Exception e) {
			System.out.println("Algo sucedio mal " + e.getMessage());
			GestionLog.FalloLog(e.getMessage());
		}

	}
}
