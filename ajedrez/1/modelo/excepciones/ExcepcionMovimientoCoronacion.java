package modelo.excepciones;

import modelo.Pieza;
/**
 * Excepcion Movimiento Coronacion
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class ExcepcionMovimientoCoronacion extends ExcepcionMovimiento {
	/**
	 * Variables:
	 * <br>Variable estatica constante de la clase Exception
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * peon :Pieza que se intentaba coronar
	 */
	private Pieza peon;
	/**
	 * pieza :Pieza a la que se iva a coronar
	 */
	private Pieza pieza;
	/**
	 * Constructor con parametro Peon Pieza
	 * @param peon :Pieza que se iva a coronar que ha podrucido el error
	 * @param pieza :Pieza que servia para coronar que ha podrucido el error
	 */
	public ExcepcionMovimientoCoronacion(Pieza peon,Pieza pieza) {
		super(null);
		this.peon = peon;
		this.pieza = pieza;
	}
	/**
	 * Metodo get peon
	 * @return peon :Variable peon
	 */
	public Pieza getPeon() {
		return peon;
	}
	/**
	 * Metodo get pieza
	 * @return peon :Variable pieza
	 */
	public Pieza getPieza() {
		return pieza;
	}
	/**
	 * Get message
	 * @return string :Mensaje de error
	 */
	public String getMessage(){
		if(peon!=null)
			return ("Excepcion: El movimiento de conoracion de "+peon.getTipo()+"("+peon.getCasilla().getCoordenada()+") "+peon.getColor()+" no se puede realizar");
		return ("Excepcion: El movimiento de conoracion de "+peon+" no se puede realizar");
	}
}
