package modelo.excepciones;

import modelo.Movimiento;
import modelo.Pieza;
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
	private Pieza p;
	/**
	 * Constructor con parametros Movimiento y Piezas
	 * @param p :Pieza que ha causado el error
	 * @param m :Movimiento que ha causado el error
	 */
	public ExcepcionMovimientoIlegal(Pieza p,Movimiento m) {
		super(m);
		this.p=p;
	}
	/**
	 * Get pieza
	 * @return pieza :Variable pieza
	 */
	public Pieza getPieza() { return p; }
	/**
	 * Get message
	 * @return string :Mensaje de error
	 */
	public String getMessage(){
		if(p!=null)
			return "Excepcion: El movimiento de la pieza "+p.getTipo()+"("+p.getCasilla().getCoordenada()+") "+p.getColor()+" de la casilla "+getMovimiento().getCoordenadaOrigen()+" a la "+getMovimiento().getCoordenadaDestino()+" es ilegal";
		return "Excepcion: El movimiento de la pieza "+p+" de la casilla "+getMovimiento().getCoordenadaOrigen()+" a la "+getMovimiento().getCoordenadaDestino()+" es ilegal";
	}
}
