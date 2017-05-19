package modelo.excepciones;
/**
 * Excepcion Coordenada Erronea
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class ExcepcionCoordenadaErronea extends ExcepcionJuegoTablero {
	/**
	 * Variables:
	 * <br>Variable estatica constante de la clase Exception
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * letra :Letra de la coordenada erronea
	 */
	private char letra;
	/**
	 * y :Y de la coordenada erronea
	 */
	private int y;
	/**
	 * Constructor con parametros Letra e Y
	 * @param letra :Letra que ha causado el error
	 * @param y :Numero que ha causado el error
	 */
	public ExcepcionCoordenadaErronea(char letra,int y) {
		super();
		this.letra=letra;
		this.y=y;
	}
	/**
	 * Get letra
	 * @return letra :Variable letra
	 */
	public char getLetra() {
		return letra;
	}
	/**
	 * Get y
	 * @return y :Variable y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Get message
	 * @return string :Mensaje de error
	 */
	public String getMessage(){ return "Excepcion: Coordenada ("+getLetra()+","+getY()+") erronea"; }
}
