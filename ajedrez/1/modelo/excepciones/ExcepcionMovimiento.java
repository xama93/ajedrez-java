package modelo.excepciones;

import modelo.Movimiento;
/**
 * Excepcion Movimiento
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public abstract class ExcepcionMovimiento extends Exception {
	/**
	 * Variables:
	 * <br>Variable estatica constante de la clase Exception
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * mov :Variable auxiliar movimiento
	 */
	private Movimiento mov;
	/**
	 * Constructor con parametro Movimiento
	 * @param m :Movimiento que ha causado el error
	 */
	public ExcepcionMovimiento(Movimiento m) {
		super();
		mov=m;
	}
	/**
	 * Get message
	 * @return string :Mensaje de error
	 */
	public String getMessage(){
		return "Excepcion: error en el movimiento ("+mov.getCoordenadaOrigen().toString()+") ("+mov.getCoordenadaDestino().toString()+")";
	}
	/**
	 * Get mov
	 * @return mov :Variable movimiento
	 */
	public Movimiento getMovimiento(){
		return mov;
	}
}
