package modelo;

import java.util.HashSet;
import java.util.Set;

import modelo.excepciones.ExcepcionCasillaOrigenVacia;
import modelo.excepciones.ExcepcionCoordenadaErronea;
import modelo.excepciones.ExcepcionNoExisteMovimiento;
import modelo.excepciones.ExcepcionTurnoDelContrario;
/**
 * Clase evaluador ajedrez por valor piezas
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class EvaluadorAjedrezPorValorPiezas implements EvaluadorAjedrez {
	/**
	 * Calcula el valor estratégico de las piezas del jugador del color dado en la posición actual de la partida.
	 * Sólo se tienen en cuenta las piezas que están sobre el tablero.
	 * @param pa :Partida que se quiere evaluar
	 * @param c :Color de las piezas
	 * @return double :Valor total de las piezas
	 */
	public double evalua(PartidaAjedrez pa, Color c) {
		if(pa!=null && c!=Color.NULO){
			int total=0;
			Set<Pieza> piezas = new HashSet<Pieza>(0);
			for(Pieza p : pa.getPiezas(c)){
				if(p.getCasilla()!=null){
					total+=p.getValor();
					piezas.add(p);
				}
			}
			Tablero t=pa.getTablero();
			for(int i=0;i<t.getDimx();i++){
				for(int j=0;j<t.getDimy();j++){
					try {
						if(!piezas.contains(t.getCasillaAt((char) (i+'A'),j+1).getPieza())){
							if(t.getCasillaAt((char) (i+'A'),j+1).getPieza()!=null && t.getCasillaAt((char) (i+'A'),j+1).getPieza().getColor()==c){
								total+=t.getCasillaAt((char) (i+'A'),j+1).getPieza().getValor();
								piezas.add(t.getCasillaAt((char) (i+'A'),j+1).getPieza());
							}
						}
					} catch (ExcepcionCoordenadaErronea e) {;}
				}
			}
			return total;
		}
		return 0;
	}
	/**
	 * Calcula el valor estratégico de las piezas del jugador del color dado,
	 * en la posición de la partida resultante de realizar el movimiento
	 * @param pa :Partida que se quiere evaluar
	 * @param c :Color de las piezas
	 * @param n :Numero del mov que se desea ejecutar
	 * @return double :Valor del movimiento
	 * @throws ExcepcionNoExisteMovimiento 
	 * @throws ExcepcionCasillaOrigenVacia 
	 * @throws ExcepcionTurnoDelContrario 
	 */
	public double evaluaMovimiento(PartidaAjedrez pa, Color c, int n) throws ExcepcionTurnoDelContrario, ExcepcionCasillaOrigenVacia, ExcepcionNoExisteMovimiento {
		pa.ejecutaMovimiento(n);
		return evalua(pa,c);
	}

}
