package modelo;

import java.util.HashSet;
import java.util.Set;

import modelo.excepciones.ExcepcionCasillaDestinoOcupada;
import modelo.excepciones.ExcepcionCoordenadaErronea;
import modelo.excepciones.ExcepcionMovimientoIlegal;
/**
 * Clase peon
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class Peon extends Pieza {
	/**
	 * Variables:
	 * <br>tipo :variable constante estatica que define el tipo de la pieza
	 */
	private final static char tipo = 'P';
	/**
	 * Constructor con parametros Color
	 * <br<Invoca al superconstructor Pieza(c)
	 * @param c :Color de la pieza
	 */
	public Peon(Color c) {
		super(c);
		valor=1;
	}
	/**
	 * Metodo esta en la posicion original
	 * <br>Sirve para comprobar que un peon nunca se ha movido
	 * @return true/false :Devuelve si esta en la posicion original
	 */
	public boolean isEnPosicionOriginal(){
		if(getCasilla()!=null)
		{
			if(getCasilla().getCoordenada().getY()==2 && getColor()==Color.BLANCO){
				return true;
			}else if(getCasilla().getCoordenada().getY()==7 && getColor()==Color.NEGRO){
				return true;
			}
		}
		return false;
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
			//Simplificar codigo
			int letra = Math.abs(c.getCoordenada().getLetra()-getCasilla().getCoordenada().getLetra());
			int num = c.getCoordenada().getY()-getCasilla().getCoordenada().getY();
	
			if(Color.BLANCO==getColor() && ((num==1) || (num==2 && this.isEnPosicionOriginal()))){
				if(letra==0 && c.getPieza()==null){
					if(num==1){
						return true;
					}else if(this.isEnPosicionOriginal() && num==2){
						try {
							Coordenada coor = new Coordenada(c.getCoordenada().getLetra(),c.getCoordenada().getY()-1);
							if(PartidaAjedrez.getInstancia()==null) return true;
							if(PartidaAjedrez.getInstancia().getPiezaAt(coor)==null)
								return true;
						} catch (ExcepcionCoordenadaErronea e) {
							System.err.println(e.getMessage());
						}
						return false;
					}
				}else if(1==num && letra==1){
					if(c.getPieza()!=null)
						return getColor()!=c.getPieza().getColor();
					return false;
				}
			//Movimiento de la negra
			}else if(Color.NEGRO==getColor() && ((num==-1) || (num==-2 && this.isEnPosicionOriginal()))){
				if(letra==0 && c.getPieza()==null){
					if(num==-1){
						return true;
					}else if(this.isEnPosicionOriginal() && num==-2){
						try {
							Coordenada coor = new Coordenada(c.getCoordenada().getLetra(),c.getCoordenada().getY()+1);
							if(PartidaAjedrez.getInstancia()==null) return true;
							if(PartidaAjedrez.getInstancia().getPiezaAt(coor)==null)
								return true;
						} catch (ExcepcionCoordenadaErronea e) {
							System.err.println(e.getMessage());
						}
						return false;
					}
				}else if(-1==num && letra==1){
					if(c.getPieza()!=null)
						return getColor()!=c.getPieza().getColor();
					return false;
				}
			}
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
		}else if(c.getPieza()!=null && this.getColor()!=c.getPieza().getColor()) throw new ExcepcionCasillaDestinoOcupada(new MovimientoOrdinario(this.getCasilla().getCoordenada(),c.getCoordenada()));
		else throw new ExcepcionMovimientoIlegal(this,new MovimientoOrdinario(getCasilla().getCoordenada(),c.getCoordenada()));
	}
	/**
	 * Metodo es el mismo tipo
	 * <br>Compara el tipo de las piezas
	 * @param p :Pieza con la comparar
	 * @return true/false :Si es la misma pieza
	 */
	public boolean isMismoTipo(Pieza p){
		if(p instanceof Peon) {
			if(p!=null)
				if(((Peon)p).getTipo()==tipo)
					return true;
		}
		return false;
	}
	@Override
	public char getTipo(){ return tipo; }
	@Override
	public Set<Casilla> getCasillasAmenazadas() {
		Set<Casilla> amenazas = new HashSet<Casilla>();
		
		if(PartidaAjedrez.getInstancia()!=null && getCasilla()!=null)
		{
			Coordenada der = null,izq = null;
			if(getColor()==Color.BLANCO){
				int y = getCasilla().getCoordenada().getY()+1;
				try{
					der = new Coordenada((char)(getCasilla().getCoordenada().getLetra()+1),y);
				}catch (ExcepcionCoordenadaErronea e){;}
				try{
					izq = new Coordenada((char)(getCasilla().getCoordenada().getLetra()-1),y);
				}catch (ExcepcionCoordenadaErronea e){;}
			}else{
				int y = getCasilla().getCoordenada().getY()-1;
				try{
					der = new Coordenada((char)(getCasilla().getCoordenada().getLetra()+1),y);
				}catch (ExcepcionCoordenadaErronea e){;}
				try{
					izq = new Coordenada((char)(getCasilla().getCoordenada().getLetra()-1),y);
				}catch (ExcepcionCoordenadaErronea e){;}
			}
			try{ if(der!=null) amenazas.add(PartidaAjedrez.getInstancia().getTablero().getCasillaAt(der)); }catch (ExcepcionCoordenadaErronea e){;}
			try{ if(izq!=null) amenazas.add(PartidaAjedrez.getInstancia().getTablero().getCasillaAt(izq)); }catch (ExcepcionCoordenadaErronea e){;}
		}
		return amenazas;
	}
	/**
	 * Metodo is en ultima fila
	 * @return true/false :Depende de si esta en la ultima fila de su color o no
	 */
	public boolean isEnUltimaFila(){
		if(this.getColor()==Color.BLANCO){
			if(this.getCasilla()!=null)
				if(this.getCasilla().getCoordenada().getY()==8)
					return true;
		}else if(this.getColor()==Color.NEGRO){
			if(this.getCasilla()!=null)
				if(this.getCasilla().getCoordenada().getY()==1)
					return true;
		}
		return false;
	}
}
