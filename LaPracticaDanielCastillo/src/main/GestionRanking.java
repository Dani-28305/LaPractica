package main;

import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jugadores.Jugadores;
/**
 * Clase que contiene la gestion del archivo de ranking del programa
 * @author Daniel Castillo
 * @version 20240512
 */
public class GestionRanking {
	/**
	 * Metodo para imprimir el archivo del Ranking
	 */
	public static void mostrarRanking() {
		try {
			ArrayList<String> rankingJugadores = (ArrayList<String>) Files.readAllLines(Constantes.archivoRanking);
			System.out.println("-- Mostrando Ranking");
			for (String nombre : rankingJugadores) {
				System.out.println(nombre);
			}
			System.out.println("-- Fin del Ranking");
		} catch (Exception e) {
			System.out.println("Fallo al leer el ranking");
			GestionLog.FalloLog(e.getMessage());
		}
	}
	/**
	 * Metodo para ordenar el ArrayList del ranking antes de escribirlo en el
	 * archivo de Ranking
	 * 
	 * @param listaOrdenar Es el ArrayList que contiene los nombres de los jugadores
	 *                     y sus puntos desordenados
	 */
	public static void ordenarRanking(ArrayList<String[]> listaOrdenar) {
		Collections.sort(listaOrdenar, new Comparator<String[]>() {
			public int compare(String[] p1, String[] p2) {
				int num1 = Integer.parseInt(p1[1]);
				int num2 = Integer.parseInt(p2[1]);
				return Integer.compare(num2, num1);
			}
		});
	}
	/**
	 * Metodo para actualizar los puntos en el Ranking de las personas que han
	 * disputado una partida
	 * 
	 * @param listaJugadoresPartida Es la lista de jugadores que han jugado la
	 *                              partida
	 */
	public static void actualizarRanking(ArrayList<Jugadores> listaJugadoresPartida) {
		try {
			ArrayList<String> rankingJugadores = (ArrayList<String>) Files.readAllLines(Constantes.archivoRanking);
			ArrayList<String[]> nombrePuntos = new ArrayList<String[]>();
			System.out.println("Actualizando el ranking");
			for (String n : rankingJugadores) {
				nombrePuntos.add(n.split(" "));
			}
			Files.write(Constantes.archivoRanking, "".getBytes());
			for (Jugadores m : listaJugadoresPartida) {
				for (String[] n : nombrePuntos) {
					if (m.getNombre().equalsIgnoreCase(n[0])) {
						System.out.println("Actualizando ranking de " + n[0]);
						int numero = Integer.parseInt(n[1]);
						numero += m.getPuntos();
						n[1] = String.valueOf(numero);
					}
				}
			}
			ordenarRanking(nombrePuntos);
			for (String[] n : nombrePuntos) {
				String escribir = n[0] + " " + n[1] + "\n";
				Files.write(Constantes.archivoRanking, escribir.getBytes(), StandardOpenOption.APPEND);
			}

		} catch (Exception e) {
			System.out.println("Error al actualizar el ranking " + e.getMessage());
			GestionLog.FalloLog(e.getMessage());
		}
	}

	/**
	 * Metodo para añadir al archivo de ranking un nuevo jugador que se haya creado
	 * 
	 * @param nombreNuevo Es el parametro que contiene el nombre del nuevo jugador a
	 *                    añadir al archivo de Ranking
	 */
	public static void nuevoJugadorRanking(String nombreNuevo) {
		try {
			Files.write(Constantes.archivoRanking, nombreNuevo.getBytes(), StandardOpenOption.APPEND);
		} catch (Exception e) {
			System.out.println("Error al inscibir al nuevo jugador en el ranking ");
			GestionLog.FalloLog(e.getMessage());
		}
	}

	/**
	 * Metodo para eliminar jugadores del archivo de ranking
	 * 
	 * @param nombreJugador Parametro que contiene el nombre del jugador a eliminar
	 */
	public static void eliminarJugadorRanking(String nombreJugador) {
		try {

			ArrayList<String> rankingJugadores = (ArrayList<String>) Files.readAllLines(Constantes.archivoRanking);
			ArrayList<String[]> nombrePuntos = new ArrayList<String[]>();
			ArrayList<String[]> aux = new ArrayList<String[]>();
			for (String n : rankingJugadores) {
				nombrePuntos.add(n.split(" "));
			}
			for (String[] n : nombrePuntos) {
				if (n[0].equalsIgnoreCase(nombreJugador)) {
					System.out.println("Jugador a eliminar encontrado en el Ranking");
				} else {
					aux.add(n);
				}
			}
			Files.write(Constantes.archivoRanking, "".getBytes());
			for (String[] n : aux) {
				String escribir = n[0] + " " + n[1] + "\n";
				Files.write(Constantes.archivoRanking, escribir.getBytes(), StandardOpenOption.APPEND);
			}
		} catch (Exception e) {
			System.out.println("Fallo al eliminar el jugador del ranking");
			GestionLog.FalloLog(e.getMessage());
		}
	}
}
