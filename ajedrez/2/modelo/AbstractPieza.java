package modelo;

import java.util.Set;

import modelo.excepciones.ExcepcionCasillaDestinoOcupada;
import modelo.excepciones.ExcepcionMovimientoIlegal;
/**
 * Clase abstractPieza
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public abstract class AbstractPieza {
	/**
	 * casilla :Casilla asociada a la pieza
	 */
	protected Casilla casilla;
	/**
	 * haMovido :Indica si se ha movido o no la pieza
	 */
	protected boolean haMovido;
	/**
	 * valor :Valor de la pieza
	 */
	protected double valor;
	/**
	 * Get casilla
	 * @return casilla :Casilla donde se encuentra la pieza
	 */
	public Casilla getCasilla() { return casilla; }
	/**
	 * Set casilla
	 * <br>Pone la casilla a la que esta asociada la pieza
	 * <br>La casilla y la pieza estan sincronizadas
	 * @param c :Casilla donde se desea mover la pieza
	 * @return true/false :Devuelve si se ha podido colocar la pieza
	 */
	public boolean setCasilla(Casilla c) {
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
	public Casilla quitaDeCasilla() {
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
	 * Metodo ha movido
	 * @return true/false :Depende de si se ha movido alguna vez o no
	 */
	public boolean haMovido() { return haMovido; }
	/**
	 * Metodo get valor
	 * @return valor :Valor actual de la pieza
	 */
	public double getValor() { return valor; }
	/**
	 * Metodo abstracto getCasillasAmenazadas
	 * @return Set<Casilla> :Casilla que amenaza la pieza
	 */
	public abstract Set<Casilla> getCasillasAmenazadas();
	/**
	 * Metodo mueve
	 * <br>Mueve la pieza a donde se le indica si es posible
	 * @param c :Casilla donde se va a mover la pieza
	 * @throws ExcepcionCasillaDestinoOcupada :La casilla a la que se intenta mover esta ocupada
	 * @throws ExcepcionMovimientoIlegal :El movimiento del peon es ilegal
	 */
	public abstract void mueve(Casilla c)
			throws ExcepcionCasillaDestinoOcupada, ExcepcionMovimientoIlegal;
	/**
	 * Metodo puede mover
	 * <br>No comprueba si en el destino existe una pieza o no, solo comprueba el movimiento
	 * @param c :Casilla donde se mira si se puede mover
	 * @return true/false :Devuelve si se puede mover o no (pero no mueve)
	 */
	public abstract boolean puedeMover(Casilla c);
	/**
	 * Meteodo es valida
	 * <br>Comprueba que la pieza no sea de nulo
	 * @return boolean :Devuelve true o false si la pieza es nula o no
	 */
	public abstract boolean isValida();
	/**
	 * Set ha movido
	 * @param m :Cambia el valor de haMovido
	 */
	public void setHaMovido(boolean m){
		haMovido=m;
	}
}