package jugadores;

public class CPU extends Jugadores {

	public CPU(int puntos, String nombre) {
		super(puntos, nombre);
	}

	@Override
	public void mostrar() {
		System.out.println("Nombre de la CPU : "+this.nombre);
		System.out.println("Puntos : " +this.puntos);
	}

}
