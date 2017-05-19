package modelo.excepciones;

import modelo.AbstractPieza;
import modelo.IMovimiento;
/**
 * Excepcion Movimiento Ilegal
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class ExcepcionMovimientoIlegal extends ExcepcionMovimiento {
	/**
	 * Variables:
	 * <br>Variable estatica constante de la clase Exception
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * p :Variable auxiliar pieza
	 */
	private AbstractPieza p;
	/**
	 * Constructor con parametros Movimiento y Piezas
	 * @param p :Pieza que ha causado el error
	 * @param m :Movimiento que ha causado el error
	 */
	public ExcepcionMovimientoIlegal(AbstractPieza p,IMovimiento m) {
		super(m);
		this.p=p;
	}
	/**
	 * Get pieza
	 * @return pieza :Variable pieza
	 */
	public AbstractPieza getPieza() { return p; }
	/**
	 * Get message
	 * @return string :Mensaje de error
	 */
	public String getMessage(){
		if(p!=null)
			return "Excepcion: El movimiento de la pieza "+p.getClass()+"("+p.getCasilla().getCoordenada()+") de la casilla "+getMovimiento().getCoordenadaOrigen()+" a la "+getMovimiento().getCoordenadaDestino()+" es ilegal";
		return "Excepcion: El movimiento de la pieza "+p+" de la casilla "+getMovimiento().getCoordenadaOrigen()+" a la "+getMovimiento().getCoordenadaDestino()+" es ilegal";
	}
}
