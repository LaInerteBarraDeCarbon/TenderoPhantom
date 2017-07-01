package electrificacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Tendero extends EjercicioOIA {
	/**
	 * 
	 */
	private int cantidadNodos;
	private int[] centrales;
	private int[][] matrizSimetrica;
	private int[] sinCentral;
	private int[] costosFinales;
	private List<Dijsktra> conjuntoSolucion = new ArrayList<Dijsktra>();
	private int[] recorrido;

	/**
	 * Carga un ejercicio de Tendero. <br>
	 * 
	 * @param entrada
	 *            Archivo de entrada. <br>
	 * @param salida
	 *            Archivo de salida. <br>
	 */
	public Tendero(final File entrada, final File salida) {
		super(entrada, salida);
		try {
			this.leerArchivo(super.entrada);
		} catch (IOException e) {
			System.out.println("Error abrir archivo.");
			e.printStackTrace();
		}
	}

	@Override
	public void resolver() {
		for (int actual = 0; actual < this.centrales.length; actual++) {
			this.conjuntoSolucion.add(new Dijsktra(this.matrizSimetrica, this.centrales[actual]));
		}
		for (ListIterator<Dijsktra> iterator = this.conjuntoSolucion.listIterator(); iterator.hasNext();) {
			Dijsktra actual = iterator.next();
			for (int i = 0; i < this.sinCentral.length; i++) {

				if (this.costosFinales[i] > actual.getCostoNodo(this.sinCentral[i])) {
					this.costosFinales[i] = actual.getCostoNodo(this.sinCentral[i]);
					this.recorrido[sinCentral[i]] = actual.getNodoInicio();
				}
			}
		}
	}

	/**
	 * Graba la salida del ejercicio. <br>
	 */
	public void grabarArchivo() {
		PrintWriter salida;
		try {
			salida = new PrintWriter(new FileWriter(super.salida));
			int costoFinal = 0;
			for (int i = 0; i < this.sinCentral.length; i++) {
				costoFinal += this.costosFinales[i];
			}
			salida.println(costoFinal);
			int vector[];
			for (int i = 0; i < this.sinCentral.length; i++) {
				salida.print(this.sinCentral[i]);
				vector = this.conjuntoSolucion.get(i).getRecorrido(this.sinCentral[i]);
				for (int j = 0; j < vector.length; j++) {
					salida.print(vector[j] + " ");
				}
				salida.print(this.recorrido[i]);
			}
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lee el archivo de entrada. <br>
	 * 
	 * @param archivo
	 *            Archivo de entrada. <br>
	 * @throws IOException
	 *             Si hay algún dato mal cargado, sale. <br>
	 */
	private void leerArchivo(final File archivo) throws IOException {
		try {
			Scanner sc = new Scanner(archivo);
			this.cantidadNodos = sc.nextInt();
			this.centrales = new int[sc.nextInt()];
			if (this.cantidadNodos > 100) {
				this.limiteSuperado("cantidad de ciudades");
			}
			if (this.centrales.length > this.cantidadNodos && this.centrales.length < 0) {
				this.limiteSuperado("cantidad de centrales");
			}
			for (int k = 0; k < this.centrales.length; k++) {
				this.centrales[k] = sc.nextInt() - 1;
				System.out.println(this.centrales[k]);
			}
			this.matrizSimetrica = new int[this.cantidadNodos][this.cantidadNodos];
			for (int i = 0; i < this.cantidadNodos; i++) {
				for (int j = 0; j < this.cantidadNodos; j++) {
					this.matrizSimetrica[i][j] = sc.nextInt();
					if ((i != j && this.matrizSimetrica[i][j] <= 0) || (i == j && this.matrizSimetrica[i][j] != 0)) {
						this.limiteSuperado("costo entre ciudades");
					}
				}
			}
			this.sinCentral = new int[this.cantidadNodos - this.centrales.length];
			int i = 0, j = 0;
			while (i < this.cantidadNodos - this.centrales.length) {
				if (i == this.centrales[j]) {
					j++;
				} else {
					this.sinCentral[i] = i;
					i++;
				}
			}
			this.costosFinales = new int[this.cantidadNodos - this.centrales.length];
			for (int k = 0; k < this.costosFinales.length; k++) {
				this.costosFinales[k] = Integer.MAX_VALUE;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Indica que supera el l�mite aceptado. <br>
	 */
	private void limiteSuperado(final String text) {
		throw new ArithmeticException("Cantidad superada de " + text + ".");
	}
}
