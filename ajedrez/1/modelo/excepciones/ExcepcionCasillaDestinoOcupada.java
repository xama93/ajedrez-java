/**
 * 
 */
package modelo.excepciones;

import modelo.Movimiento;
/**
 * Excepcion Casilla Destino Ocupada
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class ExcepcionCasillaDestinoOcupada extends ExcepcionMovimiento {
	/**
	 * Variables:
	 * <br>Variable estatica constante de la clase Exception
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor con parametro Movimiento
	 * @param m :Movimiento que ha causado el error
	 */
	public ExcepcionCasillaDestinoOcupada(Movimiento m) {
		super(m);
	}
	/**
	 * Mensaje que se muestra
	 * @return string :Cadena con el mensaje personalizado
	 */
	public String getMessage(){
		return "Excepcion: La casilla ("+this.getMovimiento().getCoordenadaDestino().toString()+") esta ocupada por una pieza del mismo color";
	}

}
