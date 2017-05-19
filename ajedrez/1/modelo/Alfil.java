package modelo;

import java.util.HashSet;
import java.util.Set;
/**
 * Clase alfil
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class Alfil extends Pieza {
	/**
	 * Variables:
	 * <br>tipo :variable constante estatica que define el tipo de la pieza
	 */
	private final static char tipo = 'A';
	/**
	 * Constructor con parametros Color
	 * <br<Invoca al superconstructor Pieza(c)
	 * @param c :Color de la pieza
	 */
	public Alfil(Color c) {
		super(c);
		valor=3;
	}
	@Override
	public boolean isMismoTipo(Pieza p) {
		if(p instanceof Alfil) {
			if(p!=null)
				if(((Alfil)p).getTipo()==tipo)
					return true;
		}
		return false;
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
			amenazas.addAll(PartidaAjedrez.getInstancia().getTablero().getDiagonalNO(getCasilla()));
			amenazas.addAll(PartidaAjedrez.getInstancia().getTablero().getDiagonalNE(getCasilla()));
			amenazas.addAll(PartidaAjedrez.getInstancia().getTablero().getDiagonalSE(getCasilla()));
			amenazas.addAll(PartidaAjedrez.getInstancia().getTablero().getDiagonalSO(getCasilla()));
		}
		return amenazas;
	}
}
