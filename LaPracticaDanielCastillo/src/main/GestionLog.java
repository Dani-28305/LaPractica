package main;

import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import jugadores.Humanos;
import jugadores.Jugadores;

public class GestionLog {
	static String fecha = "[" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	static String fechaHora = "[" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "]" + "["
			+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "]";

	/**
	 * Método para comprobar que el fichero de log es del dia de hoy
	 */
	private static void comprobarFecha() {
		try {
			ArrayList<String> lineas = (ArrayList<String>) Files.readAllLines(Constantes.archivoLogGeneral);
			ArrayList<String[]> ejemplo = new ArrayList<String[]>();
			ejemplo.add(lineas.get(0).split("]"));
			if (ejemplo.get(0)[0].equals(fecha)) {
			} else {
				Files.move(Constantes.archivoLogGeneral, Constantes.archivoLogAnterior);
				Files.createFile(Constantes.archivoLogGeneral);
				Files.write(Constantes.archivoLogGeneral, (fechaHora + "\n").getBytes());
			}
		} catch (Exception e) {
			System.out.println(Constantes.falloLog);
			FalloLog(e.getMessage());
		}
	}

	/**
	 * Método para añadir al log cuando un jugador es creado en el programa
	 * 
	 * @param nombre Parámetro que contiene el nombre del jugador creado
	 */
	public static void AnadirJugadorLog(String nombre) {
		try {
			comprobarFecha();
			String escribir = fechaHora + "Jugador " + nombre + " añadido al programa \n";
			Files.write(Constantes.archivoLogGeneral, escribir.getBytes(), StandardOpenOption.APPEND);
		} catch (Exception e) {
			System.out.println(Constantes.falloLog);
			FalloLog(e.getMessage());

		}
	}

	/**
	 * Método para añadir al log cuando se elimina un jugador del programa
	 * 
	 * @param nombre Parámetro que conteien el nombre del jugador eliminado
	 */

	public static void EliminarJugadorLog(String nombre) {
		try {
			comprobarFecha();
			String escribir = fechaHora + "Jugador " + nombre + " borrado del programa \n";
			Files.write(Constantes.archivoLogGeneral, escribir.getBytes(), StandardOpenOption.APPEND);
		} catch (Exception e) {
			System.out.println(Constantes.falloLog);
			FalloLog(e.getMessage());

		}
	}

	/**
	 * Método para añadir al log cuando sse comienza una partida nueva
	 * 
	 * @param rondas           Parámetro que contiene el número de rondas de la
	 *                         partida jugada
	 * @param jugadoresPartida Parámetro que contiene el ArrayList de jugadores de
	 *                         la partida
	 */
	public static void InicioPartidaLog(int rondas, ArrayList<Jugadores> jugadoresPartida) {
		int humanos = 0;
		for (Jugadores n : jugadoresPartida) {
			if (n instanceof Humanos) {
				humanos += 1;
			}
		}
		try {
			comprobarFecha();
			String escribir = fechaHora + "Inicio de partida de " + rondas + " rondas con " + humanos
					+ " jugadores Humanos y " + (jugadoresPartida.size() - humanos) + " jugadores CPU \n";
			Files.write(Constantes.archivoLogGeneral, escribir.getBytes(), StandardOpenOption.APPEND);
		} catch (Exception e) {
			System.out.println(Constantes.falloLog);
			FalloLog(e.getMessage());

		}
	}

	/**
	 * Método para añadir al log cuando se finaliza una partida
	 * 
	 * @param rondas           Parámetro que indica el número de rondas de la
	 *                         partida
	 * @param ganador          Parámetro que indica el ganador de la partida
	 * @param jugadoresPartida Parámetro que contiene el ArrayList de jugadores de
	 *                         la partida
	 */
	public static void FinPartidaLog(int rondas, String ganador, ArrayList<Jugadores> jugadoresPartida) {
		int humanos = 0;
		for (Jugadores n : jugadoresPartida) {
			if (n instanceof Humanos) {
				humanos += 1;
			}
		}
		try {
			comprobarFecha();
			if (ganador.equals("Empate")) {
				String escribir = fechaHora + "Fin de partida de " + rondas + " rondas con " + humanos
						+ " jugadores Humanos y " + (jugadoresPartida.size() - humanos)
						+ " jugadores CPU, el resultado de la partida a sido empate \n";
				Files.write(Constantes.archivoLogGeneral, escribir.getBytes(), StandardOpenOption.APPEND);
			} else {

				String escribir = fechaHora + "Fin de partida de " + rondas + " rondas con " + humanos
						+ " jugadores Humanos y " + (jugadoresPartida.size() - humanos)
						+ " jugadores CPU, el ganador de la partida a sido " + ganador + "\n";
				Files.write(Constantes.archivoLogGeneral, escribir.getBytes(), StandardOpenOption.APPEND);

			}
		} catch (Exception e) {
			System.out.println(Constantes.falloLog);
			FalloLog(e.getMessage());
		}
	}

	public static void FalloLog(String fallo) {
		try {
			comprobarFecha();
			String escribir = fechaHora + "Se ha producido un error en la aplicacion: " + fallo;
			Files.write(Constantes.archivoLogGeneral, escribir.getBytes(), StandardOpenOption.APPEND);
		} catch (Exception e) {
			System.out.println(Constantes.falloLog);
		}
	}
}
