package mains;

import java.io.FileNotFoundException;

import neutron.PartidaNeutron;
/**
 * Main 5
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class Main5 {
	/**
	 * @param args
	 */
	public static void main(String[] args){
		PartidaNeutron pn=PartidaNeutron.getInstancia(); // obtenemos el objeto que representa a la aplicacion
		pn.setEntrada(args[0]); // args[0] es el fichero de entrada, args[1] el de salida
		pn.setSalida(args[1]);
		try {
			pn.run();
		} catch (FileNotFoundException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} 
	}
}
