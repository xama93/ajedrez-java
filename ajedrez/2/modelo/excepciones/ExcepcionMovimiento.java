package modelo.excepciones;

import modelo.IMovimiento;
/**
 * Excepcion Movimiento
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public abstract class ExcepcionMovimiento extends ExcepcionJuegoTablero {
	/**
	 * Variables:
	 * <br>Variable estatica constante de la clase Exception
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * mov :Variable auxiliar movimiento
	 */
	private IMovimiento mov;
	/**
	 * Constructor con parametro Movimiento
	 * @param m :Movimiento que ha causado el error
	 */
	public ExcepcionMovimiento(IMovimiento m) {
		super();
		mov=m;
	}
	/**
	 * Get mov
	 * @return mov :Variable movimiento
	 */
	public IMovimiento getMovimiento(){
		return mov;
	}
}
