package modelo;

import neutron.Electron;
import neutron.PartidaNeutron;
import modelo.excepciones.ExcepcionCoordenadaErronea;
/**
 * Clase casilla
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class Casilla {
	/**
     * Variables:
     * <br>color :Color de la casilla
     */
	private Color color;
	/**
	 * pieza :Pieza de la casilla
	 */
	private AbstractPieza pieza;
	/**
	 * coordenada :Coordenada de la casilla
	 */
	private Coordenada coordenada;
	/**
	 * tablero :Tablero donde se encuentra la casilla
	 */
	private AbstractTablero tablero;
	/**
	 * Metodo estatico
     * <br>Devuelve la casilla por defecto (Color.NULO,null,00)
	 */
	public final static Casilla CasillaError = new Casilla();
	/**
	 * Constructor por parametros
	 * <br>La pieza es de color nulo
	 * @param col :Color de la nueva casilla
	 * @param coord :Coordenada de la nueva casilla
	 */
	public Casilla(Color col,Coordenada coord){
		color=col;
		pieza=null;
		coordenada=new Coordenada(coord);
		tablero=null;
	}
	/**
	 * Constructor por parametros
	 * <br>La pieza es de color nulo
	 * @param col :Color de la nueva casilla
	 * @param coord :Coordenada de la nueva casilla
	 * @param tablero :Tablero al cual esta asignada la casilla
	 */
	public Casilla(Color col,Coordenada coord,AbstractTablero tablero){
		color=col;
		pieza=null;
		coordenada=new Coordenada(coord);
		this.tablero=tablero;
	}
	/**
	 * Constructor vacio
	 * <br>La pieza es null
	 * <br>El color es nulo
	 * <br>La coordenada es 00
	 */
	public Casilla(){
		color=Color.NULO;
		pieza=null;
		coordenada=new Coordenada();
		tablero=null;
	}
	/**
	 * Comprueba si la pieza no es null o la que hay en la casilla no es de color nulo
	 * @return boolean :Devuelve true o false si la casilla esta ocupada o no
	 */
	public boolean isOcupada(){
		if(pieza==null)
			return false;
		return pieza.isValida();
	}
	/**
	 * Get pieza
	 * @return pieza :Devuelve la variable pieza
	 */
	public AbstractPieza getPieza(){ return pieza; }
	/**
	 * Comprueba si la casilla tiene color nulo
	 * @return boolean :Devuelve true o false si la casilla es de color nulo o no
	 */
	public boolean isNula(){
		return (color==Color.NULO);
	}
	/**
	 * Set pieza: Cambia la pieza por otra si se puede
	 * @param abstractPieza :Pieza por la que cambiar la que hay
	 * @return boolean :Devuelve true o false si se puede cambiar la pieza o no
	 */
	public boolean setPieza(AbstractPieza abstractPieza){
		if(abstractPieza.isValida() && !this.isNula())
		{
			if(pieza==null){
				pieza=abstractPieza;
				if(pieza.getCasilla()==null || pieza.getCasilla()==this){
					return pieza.setCasilla(this);
				}
				pieza=null;
			}else{
				if(pieza.getCasilla()==this && pieza==abstractPieza){
					return true;
				}else if(pieza.getCasilla()!=this){
					return false;
				}
			}
		}
		return false;
	}
	/**
	 * Quita la pieza si se puede y la devuelve
	 * @return p :Pieza que es quitada, si no hay devuelve una pieza nula
	 */
	public AbstractPieza quitaPieza(){
		AbstractPieza p=null;
		if(isOcupada())
		{
			p=pieza;
			pieza=null;
			if(p.getCasilla()!=null){
				p.quitaDeCasilla();
			}
		}
		return p;
	}
	/**
	 * Get color
	 * @return color :Color de la casilla
	 */
	public Color getColor(){ return color; }
	/**
	 * Set Color
	 * @param C :Color que se quiere asignar a la casilla
	 */
	public void setColor(Color C){ color=C;	}
	/**
	 * Get coordenada
	 * @return coordenada :Coordenada de la casilla
	 */
	public Coordenada getCoordenada(){ return coordenada; }
	/**
	 * Set coordenada con parametros
	 * @param c :1 coordenada
	 * @param y :2 coordenada
	 * @throws ExcepcionCoordenadaErronea 
	 */
	public void setCoordenada(char c,int y) throws ExcepcionCoordenadaErronea {
		coordenada = new Coordenada(c,y);
		if(coordenada.getLetra()=='0' || coordenada.getY()==0){
			throw new ExcepcionCoordenadaErronea(c,y);
			}
	}
	/**
	 * Metodo equals
	 * <br>Comprueba el colorde la casilla y la coordenada
	 * @param obj :Objeto con el que comparar
	 * @return true/false :Devuelve si la coordenada y el color son iguales
	 */
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(obj == null){
			return false;
		}
		if(!(obj instanceof Casilla)){
			return false;
		}
		Casilla aux = (Casilla)obj;
		if(color != aux.color){
			return false;
		}
		if(coordenada == null){
			if(aux.coordenada != null){
				return false;
			}
		}else if(!coordenada.equals(aux.coordenada)){
			return false;
		}
		return true;
	}
	/**
	 * Get tablero
	 * @return tablero :Donde esta la casilla
	 */
	public AbstractTablero getTablero(){
		return tablero;
	}
	/**
	 * Is amenazada
	 * @param c :color de la amenaza
	 * @return true/false :Si esta amenazada
	 */
	public boolean isAmenazada(Color c){
		boolean am=false;
		if(PartidaNeutron.getInstancia()!=null){
			AbstractTablero t=PartidaNeutron.getInstancia().getTablero();
			for(int i=0;i<t.getDimx();i++){
				for(int j=0;j<t.getDimy();j++){
					try {
						if(t.getCasillaAt((char)(i+'A'),j+1).getPieza()!=null)
							if(t.getCasillaAt((char)(i+'A'),j+1).getPieza() instanceof Electron && ((Electron)t.getCasillaAt((char)(i+'A'),j+1).getPieza()).getColor()==c)
								am=t.getCasillaAt((char)(i+'A'),j+1).getPieza().getCasillasAmenazadas().contains(this);
					} catch (ExcepcionCoordenadaErronea e) {;}
				}
			}
		}
		
		return am;
	}
}
