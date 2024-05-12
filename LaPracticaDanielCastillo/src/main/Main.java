package main;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import jugadores.Humanos;
import jugadores.Jugadores;
import preguntas.PreguntaIngles;
import preguntas.PreguntaLengua;
import preguntas.PreguntaMates;


public class Main {

	public static void main(String[] args) throws IOException {
		int decision = 0;
		Scanner s1 = new Scanner(System.in);
		Random r1 = new Random();
		// Pruebas Jugadores
//		Jugadores j1 = new Humanos(0, "pedro");
//		Jugadores[] list = new Jugadores[4];
//		list[1] = j1;
//		System.out.println(list[1].toString());
//		Humanos.crearJugador("ester");
		// Pruebas Preguntas
//		PreguntaMates.generarPregunta();
		PreguntaLengua.generarArrayPreguntas();
		PreguntaIngles.generarArrayPreguntas();
//		PreguntaLengua.generarPregunta();
//		PreguntaIngles.generarPregunta();

		do {
			try {
				Constantes.menuGeneral();
				decision = s1.nextInt();
				if (decision > 5 || decision < 1)
					System.out.println("Numero no valido");
				else {
					switch (decision) {
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
							decision=s1.nextInt();
							switch (decision) {
							case 1: {
								Humanos.ensenarJugadores();
								break;
							}
							case 2: {
								System.out.println("Dime el nombre del jugador que quieres crear sin espacios");
								try {
									String nuevoJugador = s1.next();
									Humanos.crearJugador(nuevoJugador);
								} catch (Exception e) {
									System.out.println("Nombre mal introducido");
									GestionLog.FalloLog(e.getMessage());
								}
								break;
							}
							case 3: {
								System.out.println("Dime el nombre del jugador que quieres eliminar");
								try {
									String eliminarJugador = s1.next();
									Humanos.eliminarJugador(eliminarJugador);
								} catch (Exception e) {
									System.out.println("Nombre mal introducido");
									GestionLog.FalloLog(e.getMessage());
								}
								break;
							}
							case 4:{
								
								break;
							}
							}

						} catch (Exception e) {
							System.out.println("Introduce un número y que sea valido");
							GestionLog.FalloLog(e.getMessage());
							s1.nextLine();
						}
					}while(decision!=4);
					}
					break;
					}
				}
			} catch (Exception e) {
				System.out.println("Introduce un número y que sea valido");
				GestionLog.FalloLog(e.getMessage());
				s1.nextLine();
			}

		} while (decision != 5);
	}

}
