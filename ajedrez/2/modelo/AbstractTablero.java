package modelo;

import java.util.List;

import modelo.excepciones.ExcepcionCoordenadaErronea;
/**
 * Clase abstractTablero
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public abstract class AbstractTablero {
	/**
	 * Variable dimx :Tamano del tablero horizontal
	 */
	protected int dimx;
	/**
	 * Variable dimy :Tamano del tablero vertical
	 */
	protected int dimy;
	/**
	 * Matriz de casillas
	 */
	protected Casilla casillas[][];
	/**
	 * Constante MAX_DIMX: numero maximo del tamano de X
	 */
	public final int MAX_DIMX = 26;
	/**
	 * Constante MAX_DIMY: numero maximo del tamano de X
	 */
	public final int MAX_DIMY = Integer.MAX_VALUE;
	/**
	 * Constructor del tablero por parametro Dimx y Dimy
	 * <br>Crea un tablero con las dimensiones dadas
	 * <br>Si no son pares o se pasan del rango se pone un 8 por defecto
	 * @param dimx :Tamanyo de la X
	 * @param dimy :Tamanyo de la Y
	 */
	protected AbstractTablero(int dimx, int dimy) {
		if(0<dimx && dimx<MAX_DIMX)
			this.dimx=dimx;
		else
			this.dimx=0;
		if(0<dimy && dimy<MAX_DIMY)
			this.dimy=dimy;
		else
			this.dimy=0;

		casillas=new Casilla[this.dimy][this.dimx];
	}
	/**
	 * Coloca Pieza at
	 * <br>Coloca la pieza en la coordenada
	 * @param c :Coordenada donde se va a poner la pieza
	 * @param p :Pieza que se quiere colocar
	 * @return true/false :Devuelve un booleano indicando si se a podido colocar la pieza en esa coordenada
	 * @throws ExcepcionCoordenadaErronea :La coordenada para acceder al tablero es erronea
	 */
	public boolean colocaPiezaAt(Coordenada c, AbstractPieza p)
			throws ExcepcionCoordenadaErronea {
				if(c.getLetra()>='A' && (int)(c.getLetra()-'A')<dimx && 0<(c.getY()) && (c.getY()-1)<dimy){
					if(p.isValida() && !casillas[c.getY()-1][(int)(c.getLetra()-'A')].isNula())
						return casillas[c.getY()-1][(int)(c.getLetra()-'A')].setPieza(p);
				}else 
					throw new ExcepcionCoordenadaErronea(c.getLetra(),c.getY());
				return false;
			}

	/**
	 * Get casilla at de coordenada
	 * @param c :Coordenada necesaria para devolver la casilla
	 * @return Casilla :Casilla asociada a esa coordenada, si no existe devuelve CasillaError
	 * @throws ExcepcionCoordenadaErronea :La coordenada para acceder al tablero es erronea
	 */
	public Casilla getCasillaAt(Coordenada c) throws ExcepcionCoordenadaErronea {
		if(c.getLetra()>='A' && (int)(c.getLetra()-'A')<dimx && 0<(c.getY()) && (c.getY()-1)<dimy)
			return getCasillaAt(c.getLetra(),c.getY());//Aprovechamos que es un metodo sobrecargado que realiza lo mismo
		throw new ExcepcionCoordenadaErronea(c.getLetra(),c.getY());
	}
	/**
	 * Get casilla at de parametros
	 * @param c :1 parametro de la coordenada
	 * @param y :2 parametro de la coordenada
	 * @return Casilla :Casilla asociada a esa coordenada, si no existe devuelve CasillaError
	 * @throws ExcepcionCoordenadaErronea 
	 */
	public Casilla getCasillaAt(char c, int y)
			throws ExcepcionCoordenadaErronea {
				if(c>='A' && (int)(c-'A')<dimx && 0<(y) && (y-1)<dimy){
					Casilla ca=casillas[y-1][(int)c-'A'];
					return ca;
				}
				throw new ExcepcionCoordenadaErronea(c,y);
			}
	/**
	 * Get dim x
	 * @return dimx :Dimension X
	 */
	public int getDimx() {
		return dimx;
	}
	/**
	 * Get dim y
	 * @return dimy :Dimension Y
	 */
	public int getDimy() {
		return dimy;
	}
	/**
	 * Metodo get amenazas
	 * <br>Obtiene una lista de piezas del color dado que amenazan la casilla
	 * @param cas :Casilla que se quiere amenazar
	 * @param col :Color de las piezas
	 * @return List<Pieza> :Lista de piezas que amenazan la casilla
	 */
	public abstract List<AbstractPieza> getAmenazas(Casilla cas, Color col);
	/**
	 * Metodo toString
	 * @return String :El tablero con el formato del juego
	 */
	public abstract String toString();
}