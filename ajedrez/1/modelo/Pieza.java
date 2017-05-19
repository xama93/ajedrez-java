package modelo;

import java.util.Set;

import modelo.excepciones.ExcepcionCasillaDestinoOcupada;
import modelo.excepciones.ExcepcionCoordenadaErronea;
import modelo.excepciones.ExcepcionMovimientoIlegal;
/**
 * Clase pieza
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public abstract class Pieza {
	/**
	 * <b>Variables</b>
     * <br>color :Variable en el eje OX
	 */
	private Color color;
	/**
	 * casilla :Casilla asociada a la pieza
	 */
	private Casilla casilla;
	/**
	 * haMovido :Indica si se ha movido o no la pieza
	 */
	protected boolean haMovido;
	/**
	 * valor :Valor de la pieza
	 */
	protected double valor;
	/**
	 * Constructor con parametro Color
	 * <br>Crea una pieza con el color c
	 * @param c :Color de la nueva pieza
	 */
	public Pieza(Color c){ color=c;casilla=null; }
	/**
	 * Metodo es del mismo color
	 * <br>Comprueba que las 2 piezas sea iguales
	 * @param p :Pieza con la que comparar el color
	 * @return boolean :Devuelve true o false dependiendo si son iguales o no
	 */
	public boolean isMismoColor(Pieza p){ return (color==p.getColor()); }
	/**
	 * Get color
	 * @return color :Devuelve el color de la pieza
	 */
	public Color getColor(){ return color; }
	/**
	 * Metodo set color
	 * @param c :Color nuevo de la pieza
	 */
	public void setColor(Color c){ color = c; }
	/**
	 * Meteodo es valida
	 * <br>Comprueba que la pieza no sea de nulo
	 * @return boolean :Devuelve true o false si la pieza es nula o no
	 */
	public boolean isValida(){ return (color!=Color.NULO); }
	/**
	 * Get casilla
	 * @return casilla :Casilla donde se encuentra la pieza
	 */
	public Casilla getCasilla(){ return casilla; }
	/**
	 * Set casilla
	 * <br>Pone la casilla a la que esta asociada la pieza
	 * <br>La casilla y la pieza estan sincronizadas
	 * @param c :Casilla donde se desea mover la pieza
	 * @return true/false :Devuelve si se ha podido colocar la pieza
	 */
	public boolean setCasilla(Casilla c){
		if(!c.isNula() && this.isValida()){
			if(casilla==null){
				casilla=c;
				if(casilla.getPieza()==null || casilla.getPieza()==this){
					return casilla.setPieza(this);
				}
				casilla=null;
			}else{
				if(casilla.getPieza()==this && casilla==c){
					return true;
				}else if(casilla.getPieza()!=this && casilla!=c){
					return false;
				}
			}
		}
		return false;
	}
	/**
	 * Quita casilla
	 * <br>Quita la casilla donde esta la pieza
	 * <br>La casilla y la pieza estan sincronizadas
	 * @return casilla :Devuelve la casilla en la que estaba la pieza
	 */
	public Casilla quitaDeCasilla(){
		Casilla c=null;
		if(casilla!=null){
			c=casilla;
			casilla=null;
			if(c.getPieza()!=null){
				c.quitaPieza();
			}
		}
		return c;
	}
	/**
	 * Metodo puede mover
	 * <br>No comprueba si en el destino existe una pieza o no, solo comprueba el movimiento
	 * @param c :Casilla donde se mira si se puede mover
	 * @return true/false :Devuelve si se puede mover o no (pero no mueve)
	 */
	public boolean puedeMover(Casilla c){
		if(this.isValida() && c!=null && this.getCasilla()!=null)
		{
			if(this.getCasillasAmenazadas().contains(c))
				if(c.getPieza()==null || c.getPieza().getColor()!=this.getColor())
					return true;
		}
		return false;
	}
	/**
	 * Metodo mueve
	 * <br>Mueve la pieza a donde se le indica si es posible
	 * @param c :Casilla donde se va a mover la pieza
	 * @throws ExcepcionCasillaDestinoOcupada :La casilla a la que se intenta mover esta ocupada
	 * @throws ExcepcionMovimientoIlegal :El movimiento del peon es ilegal
	 */
	public void mueve(Casilla c) throws ExcepcionCasillaDestinoOcupada, ExcepcionMovimientoIlegal{
		if(this.puedeMover(c)){
			c.quitaPieza();
			this.getCasilla().quitaPieza();
			c.setPieza(this);
			haMovido=true;
		}else if(this instanceof Rey && PartidaAjedrez.getInstancia()!=null){
			if(getCasilla().getCoordenada().getY()==c.getCoordenada().getY() && 2==Math.abs(getCasilla().getCoordenada().getLetra()-c.getCoordenada().getLetra())){
				if(getCasilla().getCoordenada().getLetra()-c.getCoordenada().getLetra()==2){//Largo
					Casilla torre_or=null;
					try {
						torre_or = PartidaAjedrez.getInstancia().getTablero().getCasillaAt(new Coordenada('A',c.getCoordenada().getY()));
					} catch (ExcepcionCoordenadaErronea e1) {;}
					if(((Rey)this).comprobarEnroque(torre_or)){
						this.quitaDeCasilla();
						Casilla rey = null,torre = null;
						try {
							rey = PartidaAjedrez.getInstancia().getTablero().getCasillaAt(c.getCoordenada());
							torre = PartidaAjedrez.getInstancia().getTablero().getCasillaAt('D',c.getCoordenada().getY());
						} catch (ExcepcionCoordenadaErronea e) {;}
						torre.setPieza(torre_or.quitaPieza());
						this.setCasilla(rey);
						haMovido=true;
					}else throw new ExcepcionMovimientoIlegal(this,new MovimientoOrdinario(getCasilla().getCoordenada(),c.getCoordenada()));
				}else{
					Casilla torre_or=null;
					try {
						torre_or = PartidaAjedrez.getInstancia().getTablero().getCasillaAt(new Coordenada('H',c.getCoordenada().getY()));
					} catch (ExcepcionCoordenadaErronea e1) {;}
					if(((Rey)this).comprobarEnroque(torre_or)){
						this.quitaDeCasilla();
						Casilla rey = null,torre = null;
						try {
							rey = PartidaAjedrez.getInstancia().getTablero().getCasillaAt(c.getCoordenada());
							torre = PartidaAjedrez.getInstancia().getTablero().getCasillaAt('F',c.getCoordenada().getY());
						} catch (ExcepcionCoordenadaErronea e) {;}
						torre.setPieza(torre_or.quitaPieza());
						this.setCasilla(rey);
						haMovido=true;
					}else throw new ExcepcionMovimientoIlegal(this,new MovimientoOrdinario(getCasilla().getCoordenada(),c.getCoordenada()));
				}
			}else if(c.getPieza()==null) throw new ExcepcionMovimientoIlegal(this,new MovimientoOrdinario(getCasilla().getCoordenada(),c.getCoordenada()));
			else throw new ExcepcionCasillaDestinoOcupada(new MovimientoOrdinario(this.getCasilla().getCoordenada(),c.getCoordenada()));
		}
		else if(c.getPieza()==null) throw new ExcepcionMovimientoIlegal(this,new MovimientoOrdinario(getCasilla().getCoordenada(),c.getCoordenada()));
		else throw new ExcepcionCasillaDestinoOcupada(new MovimientoOrdinario(this.getCasilla().getCoordenada(),c.getCoordenada()));
		
	}
	/**
	 * Metodo ha movido
	 * @return true/false :Depende de si se ha movido alguna vez o no
	 */
	public boolean haMovido(){ return haMovido; }
	/**
	 * Metodo get valor
	 * @return valor :Valor actual de la pieza
	 */
	public double getValor(){ return valor; }
	/**
	 * Metodo abstracto isMismoTipo
	 * @param p :Pieza con la que comparar el tipo
	 * @return true/false :Devuelve si son o no del mismo tipo
	 */
	public abstract boolean isMismoTipo(Pieza p);
	/**
	 * Metodo abstracto getTipo
	 * @return tipo :Tipo de la pieza
	 */
	public abstract char getTipo();
	/**
	 * Metodo abstracto getCasillasAmenazadas
	 * @return Set<Casilla> :Casilla que amenaza la pieza
	 */
	public abstract Set<Casilla> getCasillasAmenazadas();
}
