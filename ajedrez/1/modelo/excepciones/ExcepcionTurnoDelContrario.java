package modelo.excepciones;

import modelo.Movimiento;
/**
 * Excepcion Turno Del Contrario
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class ExcepcionTurnoDelContrario extends ExcepcionMovimiento {
	/**
	 * Variables:
	 * <br>Variable estatica constante de la clase Exception
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor con parametro Movimiento
	 * @param m :Movimiento del error
	 */
	public ExcepcionTurnoDelContrario(Movimiento m) {
		super(m);
	}
	/**
	 * Get message
	 * @return string :Mensaje de error
	 */
	public String getMessage(){
		return "Excepcion: En este turno se ha intentado mover una pieza de otro color";
	}
}
