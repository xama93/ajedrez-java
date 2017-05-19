package modelo;

import java.util.HashSet;
import java.util.Set;
/**
 * Clase torre
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class Torre extends Pieza {
	/**
	 * Variables:
	 * <br>tipo :variable constante estatica que define el tipo de la pieza
	 */
	private final static char tipo = 'T';
	/**
	 * Constructor con parametros Color
	 * <br<Invoca al superconstructor Pieza(c)
	 * @param c :Color de la pieza
	 */
	public Torre(Color c) {
		super(c);
		valor=5;
	}
	@Override
	public boolean isMismoTipo(Pieza p) {
		return (p instanceof Torre);
	}
	@Override
	public char getTipo() {
		return tipo;
	}
	@Override
	public Set<Casilla> getCasillasAmenazadas() {
		Set<Casilla> amenazas = new HashSet<Casilla>();
		
		if(PartidaAjedrez.getInstancia()!=null && getCasilla()!=null)
		{
			amenazas.addAll(PartidaAjedrez.getInstancia().getTablero().getColumnaAbajo(getCasilla()));
			amenazas.addAll(PartidaAjedrez.getInstancia().getTablero().getColumnaArriba(getCasilla()));
			amenazas.addAll(PartidaAjedrez.getInstancia().getTablero().getFilaDerecha(getCasilla()));
			amenazas.addAll(PartidaAjedrez.getInstancia().getTablero().getFilaIzquierda(getCasilla()));
		}
		return amenazas;
	}
}
