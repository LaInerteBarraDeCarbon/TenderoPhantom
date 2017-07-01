package electrificacion;

import java.io.File;

import org.junit.Test;

public class TenderoTest {

	private static String archivoIn = "Preparacion de Prueba/Entrada/";
	private static String archivoOut = "Ejecuccion de Prueba/Salida Obtenida/";

	@Test
	public void testEnunciado() {
		Tendero tendero = new Tendero(new File(archivoIn + "00_Enunciado.in"),
				new File(archivoOut + "00_Enunciado.out"));
		tendero.resolver();
		tendero.grabarArchivo();
	}
}
