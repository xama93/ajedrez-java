package modelo;
/**
 * Clase Movimiento coronacion
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class MovimientoCoronacion extends Movimiento {
	/**
	 * Variables:
	 * <br>p :Pieza a la que corona el peon
	 */
	private Pieza p;
	/**
	 * Constructor con parametros co cd p
	 * <br>Crea un movimiento con las coor orig, dest y la pieza
	 * @param co :Coordenada origen del movimiento
	 * @param cd :Coordenada destino del movimiento
	 * @param p :Pieza a la que corona el peon
	 */
	public MovimientoCoronacion(Coordenada co, Coordenada cd,Pieza p) {
		super(co, cd);
		this.p=p;
	}
	/**
	 * Get pieza
	 * @return p :Devuelve la pieza
	 */
	public Pieza getPieza(){ return p; }
}
