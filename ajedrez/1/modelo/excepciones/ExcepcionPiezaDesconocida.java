package modelo.excepciones;
/**
 * Excepcion Pieza Desconocida
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class ExcepcionPiezaDesconocida extends Exception {
	/**
	 * Variables:
	 * <br>Variable estatica constante de la clase Exception
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * letra :Variable auxiliar letra
	 */
	private char letra;
	/**
	 * Constructor con parametro Letra
	 * @param letra :Tipo de la pieza
	 */
	public ExcepcionPiezaDesconocida(char letra) {
		super();
		this.letra=letra;
	}
	/**
	 * Get letra
	 * @return letra :Letra de la pieza
	 */
	public char getLetra(){ return letra; }
	/**
	 * Get message
	 * @return string :Mensaje de error
	 */
	public String getMessage(){
		return "Excepcion: El caracter \'"+getLetra()+"\' no se reconoce como pieza o el color es erroneo";
	}
}
