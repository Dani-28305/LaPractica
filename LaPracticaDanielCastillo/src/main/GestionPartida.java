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
		String numero;
		int numeroInt=0;
		try {

			System.out.println("Dime que tipo de partida quieres jugar");
			System.out.println("1. Partida Rapida");
			System.out.println("2. Partida Corta");
			System.out.println("3. Partida Normal");
			System.out.println("4. Partida Larga");

			numero = s1.nextLine();
			numero=Constantes.quitarEspacios(numero);
			numeroInt=Integer.parseInt(numero);
			if (numeroInt < 5 && numeroInt > 0)
				if (numeroInt == 1)
					numeroInt = 3;
				else if (numeroInt == 2)
					numeroInt = 5;
				else if (numeroInt == 3)
					numeroInt = 10;
				else if (numeroInt == 4)
					numeroInt = 20;
				else {
					System.out.println("Numero mal introducido, cargando partida rapida por defecto");
					numeroInt = 3;
				}
		} catch (Exception e) {
			System.out.println("Numero mal introducido, cargando partida rapida por defecto");
			numeroInt = 3;
			GestionLog.FalloLog(e.getMessage());
		}
		return numeroInt;

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
			return "Empate";
		else
			return ganador.getNombre();
		}

	/**
	 * Metodo privado para saber los jugadores de la partida
	 * 
	 * @return ArrayList con los jugadores que van a jugar la partida
	 */
	
	//TODO duepurar metodo muy largo
	private static ArrayList preguntarJugadores() {
		int primerJugador = 0;
		Scanner s1 = new Scanner(System.in);
		boolean encontrado = false, repetido = false;
		ArrayList<Jugadores> jugadoresPartida = new ArrayList<Jugadores>();
		try {
			ArrayList<String> nombresJugadores = (ArrayList<String>) Files.readAllLines(Constantes.archivoJugadores);

			System.out.println("¿Cuantos jugadores van a jugar?");
			String numeroJugadores = s1.nextLine();
			numeroJugadores=Constantes.quitarEspacios(numeroJugadores);
			int numeroJugadoresInt=Integer.parseInt(numeroJugadores);
			if (numeroJugadoresInt > 4) {
				System.out.println("El número maximo de jugadores por partida son 4, cambiando los jugadores a 4");
				numeroJugadoresInt = 4;
			}
			System.out.println("¿Cuantos son humanos?");
			String jugadoresHumanos = s1.nextLine();
			jugadoresHumanos=Constantes.quitarEspacios(jugadoresHumanos);
			int jugadoresHumanosInt = Integer.parseInt(jugadoresHumanos);
			if (numeroJugadoresInt < jugadoresHumanosInt) {
				System.out.println(
						"Numero de Humanos superior al de jugadores, el numero de humanos pasara a ser el mismo que de jugadores");
				jugadoresHumanos = numeroJugadores;
			}
			System.out.println("Dime el nombre de los jugadores");
			for (int i = 0; i < jugadoresHumanosInt; i++) {
				String jugador = s1.nextLine();
				jugador=Constantes.quitarEspacios(jugador);
				for (String n : nombresJugadores) {
					if (n.equalsIgnoreCase(jugador)) {
						if (primerJugador < 1) {
							jugadoresPartida.add(new Humanos(0, jugador));
							encontrado = true;
							System.out.println("Encontrado");
							primerJugador++;
						} else {
							for (Jugadores m : jugadoresPartida) {
								if (m.getNombre().equalsIgnoreCase(jugador)) {
									System.out.println("Jugador ya añadido a la partida anteriormente");
									encontrado = true;
									repetido = true;
									i--;
								}
							}
								if(!repetido){
								jugadoresPartida.add(new Humanos(0, jugador));
								encontrado = true;
								System.out.println("Encontrado");
							}

						}
					}
				}
				if (!encontrado) {
					System.out.println("Nombre no encontrado, introduce uno de los siguientes");
					Humanos.ensenarJugadores();
					i--;
				}
				encontrado = false;
				repetido = false;
			}
			numeroJugadoresInt -= jugadoresHumanosInt;
			for (int i = 0; i < numeroJugadoresInt; i++) {
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
			} else
				System.out.println("El ganador de la partida a sido " + ganador(jugadoresPartida));
			GestionHistorico.escribirHistorico(jugadoresPartida);
			GestionRanking.actualizarRanking(jugadoresPartida);
			GestionLog.FinPartidaLog(rondas, ganador(jugadoresPartida), jugadoresPartida);

		} catch (Exception e) {
			System.out.println("Algo sucedio mal " + e.getMessage());
			GestionLog.FalloLog(e.getMessage());
		}

	}
}
