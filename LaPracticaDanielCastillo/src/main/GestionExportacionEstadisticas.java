package main;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestionExportacionEstadisticas {
	private Path archivoEstadisticas = Paths.get("src/estadisticas-preguntas-" + System.currentTimeMillis());
	int matesCorrectas = 0;
	int matesRealizadas = 0;
	int lenguaCorrectas = 0;
	int lenguaRealizadas = 0;
	int inglesCorrectas = 0;
	int inglesRealizadas = 0;

	public void escribirEstadisticas() {
		try {
			String escribir = "Matematicas : total " + matesRealizadas + ", " + matesCorrectas
					+ " contestadas correctamente \n" + "Lengua : total " + lenguaRealizadas + ", " + lenguaCorrectas
					+ " contestadas correctamente \n" + "Ingles : total " + inglesRealizadas + ", " + inglesCorrectas
					+ " contestadas correctamente \n";
			Files.write(archivoEstadisticas, escribir.getBytes());
		} catch (Exception e) {
			System.out.println("Error al escribir en el fichero de Estadisticas");
		}

	}

	public int getMatesRealizadas() {
		return matesRealizadas;
	}

	public void setMatesRealizadas(int matesRealizadas) {
		this.matesRealizadas = matesRealizadas;
	}

	public int getLenguaRealizadas() {
		return lenguaRealizadas;
	}

	public void setLenguaRealizadas(int lenguaRealizadas) {
		this.lenguaRealizadas = lenguaRealizadas;
	}

	public int getInglesRealizadas() {
		return inglesRealizadas;
	}

	public void setInglesRealizadas(int inglesRealizadas) {
		this.inglesRealizadas = inglesRealizadas;
	}

	public int getMatesCorrectas() {
		return matesCorrectas;
	}

	public void setMatesCorrectas(int matesCorrectas) {
		this.matesCorrectas = matesCorrectas;
	}

	public int getLenguaCorrectas() {
		return lenguaCorrectas;
	}

	public void setLenguaCorrectas(int lenguaCorrectas) {
		this.lenguaCorrectas = lenguaCorrectas;
	}

	public int getInglesCorrectas() {
		return inglesCorrectas;
	}

	public void setInglesCorrectas(int inglesCorrectas) {
		this.inglesCorrectas = inglesCorrectas;
	}

}
