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
		String decision = "";
		int decisionInt = 0;
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
				decision = s1.nextLine();
				decision=Constantes.quitarEspacios(decision);
				decisionInt = Integer.parseInt(decision);
				if (decisionInt>5 || decisionInt<1)
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
							decision=s1.nextLine();
							decision = Constantes.quitarEspacios(decision);
							decisionInt=Integer.parseInt(decision);
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
									GestionLog.FalloLog(e.getMessage());
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
						}
					}while(decisionInt!=4);
					}
					break;
					}
				}
			} catch (Exception e) {
				System.out.println("Introduce un número y que sea valido");
				GestionLog.FalloLog(e.getMessage());
			}

		} while (decisionInt!=5);
	}

}
