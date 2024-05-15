package main;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import jugadores.Humanos;
import jugadores.Jugadores;
import preguntas.PreguntaIngles;
import preguntas.PreguntaLengua;
import preguntas.PreguntaMates;

/**
 * Clase Principal del programa donde se ejecuta todo
 * 
 * @author Daniel Castillo
 * @version 202405014
 */
public class Main {
	/**
	 * Metodo main del programa
	 * 
	 * @throws IOException Cuando falla la entrada/salida
	 */
	public static void main(String[] args) throws IOException {
		String decision = "";
		int decisionInt = 0;
		Scanner s1 = new Scanner(System.in);
		Random r1 = new Random();
		PreguntaLengua.generarArrayPreguntas();
		PreguntaIngles.generarArrayPreguntas();

		do {
			try {
				Constantes.menuGeneral();
				decision = s1.nextLine();
				decision = Constantes.quitarEspacios(decision);
				decisionInt = Integer.parseInt(decision);
				if (decisionInt > 5 || decisionInt < 1)
					System.out.println("Numero no valido");
				else {
					switch (decisionInt) {
					case 1: {

						GestionPartida.jugarPartida();
						break;
					}
					case 2: {
						GestionRanking.mostrarRanking();
						break;
					}
					case 3: {
						GestionHistorico.mostrarHistorico();
						break;
					}
					case 4: {
						do {
							Constantes.menuJugadores();
							try {
								decision = s1.nextLine();
								decision = Constantes.quitarEspacios(decision);
								decisionInt = Integer.parseInt(decision);
								switch (decisionInt) {
								case 1: {
									Humanos.ensenarJugadores();
									break;
								}
								case 2: {
									try {
										Humanos.crearJugador();
									} catch (Exception e) {
										System.out.println("Nombre mal introducido");
										GestionLog.falloLog(e.getMessage());
									}
									break;
								}
								case 3: {
									System.out.println("Dime el nombre del jugador que quieres eliminar");
									try {
										String eliminarJugador = s1.nextLine();
										eliminarJugador = Constantes.quitarEspacios(eliminarJugador);
										Humanos.eliminarJugador(eliminarJugador);
									} catch (Exception e) {
										System.out.println("Nombre mal introducido");
										GestionLog.falloLog(e.getMessage());
									}
									break;
								}
								case 4: {

									break;
								}
								}

							} catch (Exception e) {
								System.out.println("Introduce un número y que sea valido");
								GestionLog.falloLog(e.getMessage());
							}
						} while (decisionInt != 4);
					}
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Introduce un número y que sea valido");
				GestionLog.falloLog(e.getMessage());
			}

		} while (decisionInt != 5);
	}

}
