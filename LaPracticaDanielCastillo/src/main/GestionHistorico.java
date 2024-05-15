package main;

import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import jugadores.Jugadores;

/**
 * Clase para gestionar el archivo historico del programa
 * @author Daniel Castillo
 * @version 20240512
 */
public class GestionHistorico {
	/**
	 * Metodo para actualizar el archivo historico de las partidas jugadas
	 * 
	 * @param jugadoresPartida Este parametro define la lista de los jugadores que
	 *                         han jugado esa partida
	 */
	public static void escribirHistorico(ArrayList<Jugadores> jugadoresPartida) {
		String escribirFichero = "";
		try {

			for (Jugadores jugador : jugadoresPartida) {

				jugador.mostrar();
				escribirFichero += jugador.getNombre() + " " + jugador.getPuntos() + " ";
			}
			escribirFichero += "\n";
			System.out.println("Escribiendo resultado de la partida en el Historico");
			Files.write(Constantes.archivoHistorico, escribirFichero.getBytes(), StandardOpenOption.APPEND);
		} catch (Exception e) {
			System.out.println("**Error al escribir el resultado de la partida en el historico**" + e.getMessage());
			GestionLog.falloLog(e.getMessage());
		}
	}
	/**
	 * Metodo para imprimir el archivo del Historico
	 */
	public static void mostrarHistorico() {
		try {
			ArrayList<String> historicoJugadores = (ArrayList<String>) Files.readAllLines(Constantes.archivoHistorico);
			System.out.println("-- Mostrando Historico");
			for (String nombre : historicoJugadores) {
				System.out.println(nombre);
			}
			System.out.println("-- Fin del Historico");
		} catch (Exception e) {
			System.out.println("Fallo al leer el historico");
			GestionLog.falloLog(e.getMessage());
		}
	}
}
