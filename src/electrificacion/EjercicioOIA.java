package electrificacion;

import java.io.File;

/**
 * Clase que administra la resolución del ejercicio. <br>
 */
public abstract class EjercicioOIA {
	/**
	 * Archivo de entrada. <br>
	 */
	protected File entrada;
	/**
	 * Archivo de salida. <br>
	 */
	protected File salida;

	/**
	 * Carga el archivo de entrada para resolver y el de salida con el
	 * resultado. <br>
	 * 
	 * @param entrada
	 *            Archivo de entrada. <br>
	 * @param salida
	 *            Archivo de salida. <br>
	 */
	public EjercicioOIA(File entrada, File salida) {
		this.entrada = entrada;
		this.salida = salida;
	}

	/**
	 * Resuelve el ejercicio. <br>
	 */
	public abstract void resolver();
}