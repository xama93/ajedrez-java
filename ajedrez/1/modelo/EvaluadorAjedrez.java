package modelo;

import modelo.excepciones.ExcepcionCasillaOrigenVacia;
import modelo.excepciones.ExcepcionNoExisteMovimiento;
import modelo.excepciones.ExcepcionTurnoDelContrario;
/**
 * Interfaz evaluador ajedrez
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public interface EvaluadorAjedrez {
	/**
	 * Calcula el valor estratégico de las piezas del jugador del color dado en la posición actual de la partida.
	 * Sólo se tienen en cuenta las piezas que están sobre el tablero.
	 * @param pa :Partida que se quiere evaluar
	 * @param c :Color de las piezas
	 * @return double :Valor total de las piezas
	 */
	public double evalua(PartidaAjedrez pa,Color c);
	/**
	 * Calcula el valor estratégico de las piezas del jugador del color dado,
	 * en la posición de la partida resultante de realizar el movimiento
	 * @param pa :Partida que se quiere evaluar
	 * @param c :Color de las piezas
	 * @param n :Numero del mov que se desea ejecutar
	 * @return double :Valor del movimiento
	 * @throws ExcepcionNoExisteMovimiento 
	 * @throws ExcepcionCasillaOrigenVacia 
	 * @throws ExcepcionTurnoDelContrario 
	 */
	public double evaluaMovimiento(PartidaAjedrez pa,Color c,int n) throws ExcepcionTurnoDelContrario, ExcepcionCasillaOrigenVacia, ExcepcionNoExisteMovimiento;
}
