package jugadores;

/**
 * Clase abstracta para crear las clases Humanos y CPU
 * 
 * @author Daniel Castillo
 * @version 20240512
 * @see {@link CPU}
 * @see {@link Humanos}
 */
public abstract class Jugadores {
	int puntos;
	String nombre;
	int racha;

	/**
	 * Metodo abracto para mostrar la informacion de cada jugador
	 */
	public abstract void mostrar();

	/**
	 * Constructo de los Jugadores
	 * 
	 * @param puntos Parametro que define los puntos que va atener un jugador en la
	 *               partida
	 * @param nombre Parametro que contiene el nombre del jugador
	 */
	public Jugadores(int puntos, String nombre) {
		super();
		this.puntos = puntos;
		this.nombre = nombre;
	}

	public int getRacha() {
		return racha;
	}

	public void setRacha(int racha) {
		this.racha = racha;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Jugadores [" + " puntos=" + puntos + ", nombre=" + nombre + "]";
	}

}
