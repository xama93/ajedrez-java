/**
 * 
 */
package mains;

import modelo.PartidaAjedrez;

/**
 * @author Josrom
 *
 */
public class Main3 {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		PartidaAjedrez pa=PartidaAjedrez.getInstancia(); // obtenemos el objeto que representa a la aplicacion
		pa.setEntrada(args[0]); // args[0] es el fichero de entrada, args[1] el de salida
		pa.setSalida(args[1]);
		pa.run(); 
	}

}
