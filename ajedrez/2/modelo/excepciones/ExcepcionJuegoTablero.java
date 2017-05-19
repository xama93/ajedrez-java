package modelo.excepciones;
/**
 * Excepcion juego tablero
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public abstract class ExcepcionJuegoTablero extends Exception {
	/**
	 * Variables:
	 * <br>Variable estatica constante de la clase Exception
	 */
	private static final long serialVersionUID = -6372249860844636250L;
	/**
	 * Get message
	 * @return string :Mensaje de error
	 */
	public abstract String getMessage();
}