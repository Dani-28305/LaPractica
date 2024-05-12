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

public class Humanos extends Jugadores {
	// static File archivoJugadores = new File("src/jugadores.txt");

	String comprobarExiste;

	public Humanos(int puntos, String nombre) {
		super(puntos, nombre);
	}

	public static void ensenarJugadores() {
		try {
			ArrayList<String> nombresJugadores = (ArrayList<String>) Files.readAllLines(Constantes.archivoJugadores);

			for (String n : nombresJugadores) {
				System.out.println(n);
			}
		} catch (Exception e) {
			System.out.println("Fallo al leer los jugadores");
			GestionLog.FalloLog(e.getMessage());
		}
	}

	public static void crearJugador(String nombre) {
		boolean existeJugador = false;
		try {
			ArrayList<String> nombresJugadores = (ArrayList<String>) Files.readAllLines(Constantes.archivoJugadores);

			for (String n : nombresJugadores) {
				if (n.equalsIgnoreCase(nombre)) {
					existeJugador = true;
				}
			}
		} catch (Exception e) {
			System.out.println("Fallo al leer los jugadores");
			existeJugador = true;
			GestionLog.FalloLog(e.getMessage());

		}
		if (!existeJugador && !nombre.equals("default")) {
			try {
				GestionRanking.nuevoJugadorRanking(nombre + " 0\n");
				GestionLog.AnadirJugadorLog(nombre);
				nombre += '\n';
				Files.write(Constantes.archivoJugadores, nombre.getBytes(), StandardOpenOption.APPEND);
				System.out.println("Jugador creado con exito");
			} catch (Exception e) {
				System.out.println("Fallo al añadir el jugador");
				GestionLog.FalloLog(e.getMessage());

			}
		} else
			System.out.println("Jugador ya existente");

	}

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
				GestionLog.FalloLog(e.getMessage());

			}
		} catch (Exception e) {
			System.out.println("Fallo al leer los jugadores");
			existeJugador = false;
			GestionLog.FalloLog(e.getMessage());
		}
		GestionLog.EliminarJugadorLog(nombre);

	}

	@Override
	public void mostrar() {
		System.out.println("Nombre del jugador : " + this.nombre);
		System.out.println("Puntos : " + this.puntos);
	}
}
