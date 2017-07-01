package electrificacion;

import java.util.Stack;

/**
 * Clase que resuelve el camino de Dijkstra. <br>
 */
public class Dijsktra {
	/**
	 * Matriz de adyacencia del grafo. <br>
	 */
	private int[][] matrizAdyacencia;
	/**
	 * Nodos visitados. <br>
	 */
	private boolean[] visitados;
	/**
	 * Costo para llegar a un nodo. <br>
	 */
	private int[] costos;
	/**
	 * Nodo inicial. <br>
	 */
	private int nodoInicio;
	/**
	 * Cantidad de nodos del grafo. <br>
	 */
	private int cantidadNodos;
	/**
	 * Ruta para llegar a un nodo. <br>
	 */
	private int[] ruta;

	/**
	 * Resuelve el camino de Dijkstra de un grafo a partir de un nodo de inicio.
	 * <br>
	 * 
	 * @param matrizAdyacencia
	 *            Matriz de adyacencia del grafo. <br>
	 * @param nodoInicio
	 *            Nodo inicial. <br>
	 */
	public Dijsktra(final int[][] matrizAdyacencia, final int nodoInicio) {
		this.matrizAdyacencia = matrizAdyacencia;
		this.nodoInicio = nodoInicio - 1;
		this.cantidadNodos = matrizAdyacencia.length;
		this.visitados = new boolean[this.cantidadNodos];
		this.costos = new int[this.cantidadNodos];
		this.ruta = new int[this.cantidadNodos];
		for (int i = 0; i < cantidadNodos; i++) {
			costos[i] = this.matrizAdyacencia[nodoInicio][i];
			ruta[i] = -1;
		}
		this.visitados[this.nodoInicio] = true;
		this.costos[this.nodoInicio] = Integer.MAX_VALUE;
		int i = 0;
		int siguiente = minimo();
		while (i < cantidadNodos && siguiente != -1) {
			visitados[siguiente] = true;
			for (int adyacente = 0; adyacente < cantidadNodos; adyacente++) {
				if (!visitados[adyacente]) {
					if (this.matrizAdyacencia[siguiente][adyacente] != Integer.MAX_VALUE
							&& (costos[siguiente] + this.matrizAdyacencia[siguiente][adyacente]) < costos[adyacente]) {
						costos[adyacente] = costos[siguiente] + this.matrizAdyacencia[siguiente][adyacente];
						ruta[adyacente] = siguiente;
					}
				}
			}
			siguiente = minimo();
		}
	}

	/**
	 * Encuentra el índice del valor mínimo de un nodo no visitado. <br>
	 * 
	 * @return Índice del valor mínimo. <br>
	 *         De no existir camino a los nodos restantes devuelve -1. <br>
	 */
	private int minimo() {
		int valorMinimo = Integer.MAX_VALUE;
		int indiceMinimo = -1;
		for (int i = 0; i < cantidadNodos; i++) {
			if (!visitados[i] && costos[i] < valorMinimo) {
				valorMinimo = costos[i];
				indiceMinimo = i;
			}
		}
		return indiceMinimo;
	}

	/**
	 * Calcula la ruta de menor peso a tomar para llegar a un nodo .<br>
	 * 
	 * @param destino
	 *            Nodo a llegar. <br>
	 */
	public int[] getRecorrido(final int destino) {
		int[] vector;
		Stack<Integer> pila = new Stack<Integer>();
		int anterior = destino - 1;
		if (this.costos[anterior] != Integer.MAX_VALUE) {
			while (ruta[anterior] != -1) {
				pila.push(ruta[anterior]);
				anterior = ruta[anterior];
			}
		}

		vector = new int[pila.size()];
		for (int i = 0; i < pila.size(); i++) {
			vector[i] = pila.pop();
		}
		return vector;
	}

	/***
	 * Devuelve el costo mínimo total para llegar a un nodo. <br>
	 * 
	 * @param nodo
	 *            Nodo destino. <br>
	 * @return Costo. <br>
	 */
	public int getCostoNodo(final int nodo) {
		return this.costos[nodo];
	}

	/**
	 * Devuelve el nodo de inicio. <br>
	 * 
	 * @return Nodo de inicio. <br>
	 */
	public int getNodoInicio() {
		return this.nodoInicio;
	}
}