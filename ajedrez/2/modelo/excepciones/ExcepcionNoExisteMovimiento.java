package modelo.excepciones;
/**
 * Excepcion No Existe Movimiento
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class ExcepcionNoExisteMovimiento extends ExcepcionMovimiento {
	/**
	 * Variables:
	 * <br>Variable estatica constante de la clase Exception
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * indice :Vaiable auxliar int
	 */
	private int indice;
	/**
	 *  Constructor con parametro Indice
	 * @param indice :Numero que ha hecho saltar el error(fuera del rango)
	 */
	public ExcepcionNoExisteMovimiento(int indice){
		super(null);
		this.indice=indice;
	}
	/**
	 * Get message
	 * @return string :Mensaje de error
	 */
	public String getMessage(){
		return "Excepcion: El movimiento con indice "+indice+" no existe";
	}
}
