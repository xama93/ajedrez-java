package modelo;

import java.util.HashSet;
import java.util.Set;

import modelo.excepciones.ExcepcionCoordenadaErronea;
/**
 * Clase rey
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class Rey extends Pieza {
	/**
	 * <b>Variables</b>
     * <br>tipo :variable constante estatica que define el tipo de la pieza
	 */
	private final static char tipo = 'R';
	/**
	 * Constructor con parametro Color
	 * @param c :Color de la nueva pieza
	 */
	public Rey(Color c) {
		super(c);
	}
	/**
	 * Metodo es el mismo tipo
	 * <br>Compara el tipo de las piezas
	 * @param p :Pieza con la comparar
	 * @return true/false :Si es la misma pieza
	 */
	public boolean isMismoTipo(Pieza p){
		if(p instanceof Rey)
			if(p!=null)
				if(((Rey)p).getTipo()==tipo)
					return true;
		return false;
	}
	/**
	 * Get tipo
	 * @return tipo :Tipo de la pieza
	 */
	public char getTipo() { return tipo; }
	/**
	 * Metodo get casillas amenazadas
	 * <br>Devuelve todas las casillas que amenaza la pieza
	 * @return Set<Casilla> :Casilla que amenaza la pieza
	 */
	public Set<Casilla> getCasillasAmenazadas() {
		Set<Casilla> amenazas = new HashSet<Casilla>();
		
		if(PartidaAjedrez.getInstancia()!=null && getCasilla()!=null)
		{
			if(PartidaAjedrez.getInstancia().getTablero().getColumnaAbajo(getCasilla()).size()>0)
				amenazas.add(PartidaAjedrez.getInstancia().getTablero().getColumnaAbajo(getCasilla()).get(0));
			if(PartidaAjedrez.getInstancia().getTablero().getColumnaArriba(getCasilla()).size()>0)
				amenazas.add(PartidaAjedrez.getInstancia().getTablero().getColumnaArriba(getCasilla()).get(0));
			if(PartidaAjedrez.getInstancia().getTablero().getFilaDerecha(getCasilla()).size()>0)
				amenazas.add(PartidaAjedrez.getInstancia().getTablero().getFilaDerecha(getCasilla()).get(0));
			if(PartidaAjedrez.getInstancia().getTablero().getFilaIzquierda(getCasilla()).size()>0)
				amenazas.add(PartidaAjedrez.getInstancia().getTablero().getFilaIzquierda(getCasilla()).get(0));
			if(PartidaAjedrez.getInstancia().getTablero().getDiagonalNO(getCasilla()).size()>0)
				amenazas.add(PartidaAjedrez.getInstancia().getTablero().getDiagonalNO(getCasilla()).get(0));
			if(PartidaAjedrez.getInstancia().getTablero().getDiagonalNE(getCasilla()).size()>0)
				amenazas.add(PartidaAjedrez.getInstancia().getTablero().getDiagonalNE(getCasilla()).get(0));
			if(PartidaAjedrez.getInstancia().getTablero().getDiagonalSE(getCasilla()).size()>0)
				amenazas.add(PartidaAjedrez.getInstancia().getTablero().getDiagonalSE(getCasilla()).get(0));
			if(PartidaAjedrez.getInstancia().getTablero().getDiagonalSO(getCasilla()).size()>0)
				amenazas.add(PartidaAjedrez.getInstancia().getTablero().getDiagonalSO(getCasilla()).get(0));
		}
		return amenazas;
	}
	/**
	 * Metodo comprobar enroque
	 * @param cas :Casilla con la que se quiere hacer un enroque el Rey
	 * @return true/false :Si se puede hacer un enroque
	 */
	public boolean comprobarEnroque(Casilla cas){
		PartidaAjedrez pa = PartidaAjedrez.getInstancia();
		if(pa!=null && !haMovido() && this.getCasilla()!=null && cas!=null){
			if(cas.getPieza()!=null && cas.getPieza() instanceof Torre && !cas.getPieza().haMovido()){
				Color c; 
				if(getColor()==Color.BLANCO) c=Color.NEGRO;
				else c=Color.BLANCO;
				if(!this.getCasilla().isAmenazada(c) && this.getColor()==cas.getPieza().getColor()){
					Casilla c1 = null,c2 = null;
					if(cas.getCoordenada().getLetra()=='A'){//Enroque largo	
						try { c1 = pa.getTablero().getCasillaAt('C',getCasilla().getCoordenada().getY());
						c2 = pa.getTablero().getCasillaAt('D',getCasilla().getCoordenada().getY());} catch (ExcepcionCoordenadaErronea e) { ; }
						if(pa.getTablero().getAmenazas(c1,c).isEmpty() && pa.getTablero().getAmenazas(c2,c).isEmpty()){
							if(pa.getTablero().getFilaDerecha(cas).size()==4){//Esto significa que no hay piezas hasta el rey
								return true;
							}
						}
					}else if(cas.getCoordenada().getLetra()=='H'){//Enroque corto
						try { c1 = pa.getTablero().getCasillaAt('G',getCasilla().getCoordenada().getY());
						c2 = pa.getTablero().getCasillaAt('F',getCasilla().getCoordenada().getY());} catch (ExcepcionCoordenadaErronea e) { ; }
						if(pa.getTablero().getAmenazas(c1,c).isEmpty() && pa.getTablero().getAmenazas(c2,c).isEmpty()){
							if(pa.getTablero().getFilaIzquierda(cas).size()==3){//Esto significa que no hay piezas hasta el rey
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
}
