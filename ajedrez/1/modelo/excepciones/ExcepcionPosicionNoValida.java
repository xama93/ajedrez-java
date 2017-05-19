package modelo.excepciones;

import modelo.Coordenada;;
/**
 * Excepcion Posicion No Valida
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class ExcepcionPosicionNoValida extends Exception {
	/**
	 * Variables:
	 * <br>Variable estatica constante de la clase Exception
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * coor :Variable auxiliar coordenada
	 */
	private Coordenada coor;
	/**
	 * Constructor con parametro Coordenada
	 * @param c :Coordenada del error
	 */
	public ExcepcionPosicionNoValida(Coordenada c) {
		super();
		coor = new Coordenada(c);
	}
	/**
	 * Get coordenada
	 * @return coor :Variable coordenada
	 */
	public Coordenada getCoordenada(){ return coor; }
	/**
	 * Get message
	 * @return string :Mensaje de error
	 */
	public String getMessage(){
		return "Excepcion: En la coordenada ("+coor.getLetra()+","+coor.getY()+") ya se encuentra otra pieza";
	}
}
