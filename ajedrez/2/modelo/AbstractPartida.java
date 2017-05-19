package modelo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import modelo.excepciones.ExcepcionCoordenadaErronea;
import modelo.excepciones.ExcepcionNoExisteMovimiento;
/**
 * Clase abstractPartida
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public abstract class AbstractPartida {
	/**
	 * Variables:
	 * <br>tablero :Tablero (8x8) de la partida
	 */
	protected AbstractTablero tablero;
	/**
	 * piezas :Lista de piezas que se encuentran en el tablero
	 */
	protected ArrayList<AbstractPieza> piezas;
	/**
	 * movs :Lista de movimientos que se han realizado en el tablero
	 */
	protected ArrayList<IMovimiento> movs;
	/**
	 * fentrada :Nombre del fichero de entrada
	 */
	protected String fentrada = null;
	/**
	 * fsalida: Nombre del fichero de salida
	 */
	protected String fsalida = null;
	/**
	 * Constructor de partidas
	 */
	protected AbstractPartida() {
		super();
		piezas=new ArrayList<AbstractPieza>(0);
		movs=new ArrayList<IMovimiento>(0);
		tablero=null;
	}
	/**
	 * Metodo borrarPartida
	 * <br>Quita las piezas del tablero y elimina las piezas y movimientos que contenga la partida
	 * <br>No elimina el tablero
	 */
	public void borrarPartida(){
		for(AbstractPieza p : piezas){
			p.quitaDeCasilla();
		}
		piezas.clear();
		
		movs.clear();
	}
	/**
	 * Set entrada
	 * @param f :Nombre del fichero de entrada
	 */
	public void setEntrada(String f) { fentrada=f; }

	/**
	 * Set salida
	 * @param f :nombre del fichero de salida
	 */
	public void setSalida(String f) { fsalida=f; }

	/**
	 * Get entrada
	 * @return fentrada :Nombre del fichero de entrada
	 */
	public String getEntrada() { return fentrada; }

	/**
	 * Get salida
	 * @return fentrada :Nombre del fichero de salida
	 */
	public String getSalida() { return fsalida; }

	/**
	 * Get tablero
	 * @return tablero :Devuelve el tablero
	 */
	public AbstractTablero getTablero() { return tablero; }

	/**
	 * Get pieza por coordenada
	 * <br>Devuelce la pieza en la coordenada c
	 * @param c :Coordenada de la pieza a buscar
	 * @return pieza :pieza asociada a esa coordenada o null si no hay ninguna
	 * @throws ExcepcionCoordenadaErronea :Se llanza cuando la coordenada no esta dentro de los limites del tablero
	 */
	public AbstractPieza getPiezaAt(Coordenada c) throws ExcepcionCoordenadaErronea {
		return tablero.getCasillaAt(c).getPieza();
	}

	/**
	 * Get ovimiento por indice
	 * <br>Devuelve el movimiento asociado a esa posicion
	 * @param numMov :Numero del movimiento que se busca
	 * @return movimiento :Movimiento numero n (n=indice)
	 * @throws ExcepcionNoExisteMovimiento :Se lanza cuando se pasa del rango del array de movs
	 */
	public IMovimiento getMovimientoAt(int numMov)
			throws ExcepcionNoExisteMovimiento {
				if(0<=numMov && numMov<getNumMovimientos()){
					IMovimiento m = movs.get(numMov);
					return m;
				}
				throw new ExcepcionNoExisteMovimiento(numMov);
			}

	/**
	 * Get num movimientos
	 * @return num_mov :Numero de movimientos guardados
	 */
	public int getNumMovimientos() { return movs.size(); }
	/**
	 * Metodo abstracto toString
	 * <br>Devuelve un formato dependiendo del juego
	 * @return string :Cadena que representa la clase del juego
	 */
	public abstract String toString();
	/**
	 * Metodo inicializaTablero
	 * <br>Cada partida inicializa la partida de una forma distinta
	 */
	protected abstract void inicializaTablero();
	/**
	 * Metodo run
	 * <br>Este m�todo se encarga del funcionamiento general de la aplicaci�n
	 * <br>Lee las �rdenes del fichero de entrada, ejecutando las �rdenes inclu�das en �l, que depender�n de la aplicaci�n que se implemente
	 * @throws FileNotFoundException :Si no se encuentra el fichero de entrada o salida
	 * 
	 */
	public abstract void run() throws FileNotFoundException;

}