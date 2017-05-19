package modelo.excepciones;

import modelo.Movimiento;
/**
 * Excepcion Casilla Origen Vacia
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class ExcepcionCasillaOrigenVacia extends ExcepcionMovimiento {
	/**
	 * Variables:
	 * <br>Variable estatica constante de la clase Exception
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor con parametro Movimiento
	 * @param m :Movimiento que ha causado el error
	 */
	public ExcepcionCasillaOrigenVacia(Movimiento m) {
		super(m);
	}
	/**
	 * Get message
	 * @return string :Mensaje de error
	 */
	public String getMessage(){
		return "Excepcion: La casilla con las coordenadas origen ("+super.getMovimiento().getCoordenadaOrigen().toString()+") esta vacia";
	}
}
