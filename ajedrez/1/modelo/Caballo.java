package modelo;

import java.util.HashSet;
import java.util.Set;
/**
 * Clase caballo
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class Caballo extends Pieza {
	/**
	 * Variables:
	 * <br>tipo :variable constante estatica que define el tipo de la pieza
	 */
	private final static char tipo = 'C';
	/**
	 * Constructor con parametros Color
	 * <br<Invoca al superconstructor Pieza(c)
	 * @param c :Color de la pieza
	 */
	public Caballo(Color c) {
		super(c);
		valor=3;
	}
	@Override
	public boolean isMismoTipo(Pieza p) {
		if(p instanceof Caballo) {
			if(p!=null)
				if(((Caballo)p).getTipo()==tipo)
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
			amenazas.addAll(PartidaAjedrez.getInstancia().getTablero().getSaltosCaballo(getCasilla()));
		}
		return amenazas;
	}
}
