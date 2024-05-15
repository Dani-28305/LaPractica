package jugadores;
/**
 * Clase para la gestion de las CPU que pueden jugar 
 */
public class CPU extends Jugadores {
/**
 * Constructor de CPU
 * @param puntos Parametro que guarda los puntos que gana la CPU
 * @param nombre Parametro que guarda el nombre de la CPU
 */
	public CPU(int puntos, String nombre) {
		super(puntos, nombre);
	}
/**
 * Metodo para mostrar informacion de la CPU
 */
	@Override
	public void mostrar() {
		System.out.println("Nombre de la CPU : "+this.nombre);
		System.out.println("Puntos : " +this.puntos);
	}

}
