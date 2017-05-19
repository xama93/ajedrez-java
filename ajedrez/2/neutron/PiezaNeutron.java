package neutron;

import java.util.HashSet;
import java.util.Set;

import modelo.AbstractPieza;
import modelo.Casilla;
import modelo.excepciones.ExcepcionCasillaDestinoOcupada;
import modelo.excepciones.ExcepcionMovimientoIlegal;
/**
 * Clase PiezaNeutron
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public abstract class PiezaNeutron extends AbstractPieza {
	/**
	 * Constructor sin parametros
	 * <br>Incializa el valor de la pieza a cero y el atributo haMovido a falso
	 */
	public PiezaNeutron(){
		haMovido=false;
		valor=0;
	}
	@Override
	public Set<Casilla> getCasillasAmenazadas() {
		Set<Casilla> am = new HashSet<Casilla>(0);
		PartidaNeutron pn=PartidaNeutron.getInstancia();
		if(pn!=null){
			TableroNeutron tab=(TableroNeutron) pn.getTablero();
			if(getCasilla()!=null){
				am.add(tab.getDestinoColumnaAbajo(getCasilla()));
				am.add(tab.getDestinoColumnaArriba(getCasilla()));
				am.add(tab.getDestinoFilaIzquierda(getCasilla()));
				am.add(tab.getDestinoFilaDerecha(getCasilla()));
				am.add(tab.getDestinoDiagonalNE(getCasilla()));
				am.add(tab.getDestinoDiagonalNO(getCasilla()));
				am.add(tab.getDestinoDiagonalSE(getCasilla()));
				am.add(tab.getDestinoDiagonalSO(getCasilla()));
			}
		}
		return am;
	}
	@Override
	public void mueve(Casilla c) throws ExcepcionCasillaDestinoOcupada,ExcepcionMovimientoIlegal {
		if(c!=null && getCasilla()!=null){
			if(!c.isNula() && !c.isOcupada()){
				if(puedeMover(c)){
					quitaDeCasilla();
					setCasilla(c);
					haMovido=true;
				}else throw new ExcepcionMovimientoIlegal(this,new MovimientoNeutron(getCasilla().getCoordenada(),c.getCoordenada()));
			}else throw new ExcepcionCasillaDestinoOcupada(new MovimientoNeutron(getCasilla().getCoordenada(),c.getCoordenada()));
		}else throw new ExcepcionCasillaDestinoOcupada(null);
	}
	@Override
	public boolean puedeMover(Casilla c) {
		if(!c.isNula() && !c.isOcupada()){
			if(isValida() && getCasilla()!=null){
				if(getCasillasAmenazadas().contains(c)){
					return true;
				}
			}
		}
		return false;
	}
}
