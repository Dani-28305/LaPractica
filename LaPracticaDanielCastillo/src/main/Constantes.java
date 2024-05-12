package main;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

import jugadores.CPU;
import jugadores.Humanos;
import jugadores.Jugadores;
import preguntas.PreguntaIngles;
import preguntas.PreguntaLengua;
import preguntas.PreguntaMates;

/**
 * Esta clase contiene la mayoria de metodos con los que gestionar la aplicación
 * 
 * @author Daniel Castillo
 * @version 20240512
 * 
 */
public class Constantes {
	public static String acierto = "¡Acertasete!, ganaste un punto";
	public static String fallo = "Fallaste, la respuesta correcta era la siguiente: ";
	public static String falloLog = "Error al escribir en el log";
	public static Path archivoJugadores = Paths.get("src/jugadores.txt");
	public static Path archivoHistorico = Paths.get("src/historico.txt");
	public static Path archivoRanking = Paths.get("src/ranking.txt");
	public static Path archivoLogGeneral = Paths.get("src/salida.log");
	public static Path archivoLogAnterior = Paths.get("src/salida.log" + LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")));

	/**
	 * Metodo para pintar el menu general
	 */
	public static void menuGeneral() {
		System.out.println("");
		System.out.println("1.Jugar Partida");
		System.out.println("2.Ranking");
		System.out.println("3.Historico");
		System.out.println("4.Jugadores");
		System.out.println("5.Salir");
		System.out.println("");
	}

	/**
	 * Metodo para pintar el menu de los jugadores
	 */
	public static void menuJugadores() {
		System.out.println("1. Ver Jugadores ");
		System.out.println("2. Añadir Jugadores");
		System.out.println("3. Eliminar Jugadores");
		System.out.println("4. Volver");
	}
}
