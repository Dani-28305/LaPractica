package jugadores;

import java.nio.file.Files;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

import main.Constantes;
import main.GestionLog;
import main.GestionRanking;

/**
 * Clase para gestionar los jugadores Humanpos de las partidas
 */
public class Humanos extends Jugadores {

	/**
	 * Constructor de la clase Humanos
	 * 
	 * @param puntos Parámetro que contiene los puntos de cada jugador durante la
	 *               partida
	 * @param nombre Parámetro que conteien el nombre del jugador Humano
	 */
	public Humanos(int puntos, String nombre) {
		super(puntos, nombre);
	}

	/**
	 * Método para enseñar todos los jugadores registrados en el programa
	 */
	public static void ensenarJugadores() {
		try {
			ArrayList<String> nombresJugadores = (ArrayList<String>) Files.readAllLines(Constantes.archivoJugadores);

			for (String n : nombresJugadores) {
				System.out.println(n);
			}
		} catch (Exception e) {
			System.out.println("Fallo al leer los jugadores");
			GestionLog.falloLog(e.getMessage());
		}
	}

	/**
	 * Método para añadir jugadores al programa
	 */
	public static void crearJugador() {
		Scanner s1 = new Scanner(System.in);
		System.out.println("Dime el nombre del jugador que quieres crear sin espacios");
		boolean existeJugador = false;
		String nuevoJugador = s1.nextLine();
		nuevoJugador = Constantes.quitarEspacios(nuevoJugador);
		try {
			ArrayList<String> nombresJugadores = (ArrayList<String>) Files.readAllLines(Constantes.archivoJugadores);
			for (String n : nombresJugadores) {
				if (n.equalsIgnoreCase(nuevoJugador)) {
					existeJugador = true;
				}
			}
		} catch (Exception e) {
			System.out.println("Fallo al leer los jugadores");
			existeJugador = true;
			GestionLog.falloLog(e.getMessage());

		}
		if (!existeJugador && !nuevoJugador.equals("default")) {
			try {
				GestionRanking.nuevoJugadorRanking(nuevoJugador + " 0\n");
				GestionLog.anadirJugadorLog(nuevoJugador);
				nuevoJugador += '\n';
				Files.write(Constantes.archivoJugadores, nuevoJugador.getBytes(), StandardOpenOption.APPEND);
				System.out.println("Jugador creado con exito");
			} catch (Exception e) {
				System.out.println("Fallo al añadir el jugador");
				GestionLog.falloLog(e.getMessage());

			}
		} else
			System.out.println("Jugador ya existente");

	}

	/**
	 * Metodo para eliminar jugadores del programa
	 * 
	 * @param nombre Parámetro que contiene el nombre del jugador a eliminar del
	 *               programa
	 */
	public static void eliminarJugador(String nombre) {
		boolean existeJugador = false;
		try {
			ArrayList<String> nombresJugadores = (ArrayList<String>) Files.readAllLines(Constantes.archivoJugadores);
			ArrayList<String> lineasModificar = new ArrayList<String>();
			for (String n : nombresJugadores) {
				if (n.equalsIgnoreCase(nombre)) {
					existeJugador = true;
					System.out.println("Jugador a eliminar encontrado");
				} else {
					lineasModificar.add(n);
				}
			}
			try {
				if (existeJugador) {
					GestionRanking.eliminarJugadorRanking(nombre);
					Files.write(Constantes.archivoJugadores, lineasModificar);
					System.out.println("Jugador eliminado correctamente");
				}
			} catch (Exception e) {
				System.out.println("Fallo al eliminar el jugador" + e.getMessage());
				GestionLog.falloLog(e.getMessage());

			}
		} catch (Exception e) {
			System.out.println("Fallo al leer los jugadores");
			existeJugador = false;
			GestionLog.falloLog(e.getMessage());
		}
		GestionLog.eliminarJugadorLog(nombre);

	}

	/**
	 * Metodo heredado para mostrar la informacion de los jugadores Humanos
	 */
	@Override
	public void mostrar() {
		System.out.println("Nombre del jugador : " + this.nombre);
		System.out.println("Puntos : " + this.puntos);
	}
}
