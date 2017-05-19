
package modelo;

import java.util.HashSet;
import java.util.Set;

import modelo.excepciones.ExcepcionCasillaOrigenVacia;
import modelo.excepciones.ExcepcionCoordenadaErronea;
import modelo.excepciones.ExcepcionNoExisteMovimiento;
import modelo.excepciones.ExcepcionTurnoDelContrario;
/**
 * Clase evaluador ajedrez por posicion
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class EvaluadorAjedrezPorPosicion implements EvaluadorAjedrez {
	/**
	 * Calcula el valor estratégico de las piezas del jugador del color dado en la posición actual de la partida.
	 * Sólo se tienen en cuenta las piezas que están sobre el tablero.
	 * @param pa :Partida que se quiere evaluar
	 * @param c :Color de las piezas
	 * @return double :Valor total de las piezas
	 */
	public double evalua(PartidaAjedrez pa, Color c) {
		if(pa!=null && c!=Color.NULO){
			double total=0;
			Set<Casilla> am = new HashSet<Casilla>(0);
			Set<Casilla> def = new HashSet<Casilla>(0);
			for(Pieza p : pa.getPiezas(c)){
				boolean mod=false;
				if(p.getCasilla()!=null){//Esta en el tablero
					//Caso del peon
					if(p instanceof Peon){
						//Peones blancos
						if(p.getColor()==Color.BLANCO){
							//Peones doblados
							Coordenada aux = null;
							try { aux=new Coordenada(p.getCasilla().getCoordenada().getLetra(),p.getCasilla().getCoordenada().getY()-1);
								if(aux!=null && pa.getPiezaAt(aux)!=null){
									if(pa.getPiezaAt(aux) instanceof Peon && pa.getPiezaAt(aux).getColor()==p.getColor()){
										total-=1;
										mod=true;
									}
								}
							} catch (ExcepcionCoordenadaErronea e) {;}
							//Peon por encima de la media
							if(p.getCasilla().getCoordenada().getY()>4 && !mod){
								total+=1;
								mod=true;
								//Peon por encima de 1/3
								if(p.getCasilla().getCoordenada().getY()>5)
									total+=1;
							}
						}else if(p.getColor()==Color.NEGRO){
							//Peones doblados
							Coordenada aux = null;
							try { aux=new Coordenada(p.getCasilla().getCoordenada().getLetra(),p.getCasilla().getCoordenada().getY()+1);
								if(aux!=null && pa.getPiezaAt(aux)!=null){
									if(pa.getPiezaAt(aux) instanceof Peon && pa.getPiezaAt(aux).getColor()==p.getColor()){
										total-=1;
										mod=true;
									}
								}
							} catch (ExcepcionCoordenadaErronea e) {;}
							//Peon por encima de la media
							if(p.getCasilla().getCoordenada().getY()<5 && !mod){
								total+=1;
								mod=true;
								//Peon por encima de 1/3
								if(p.getCasilla().getCoordenada().getY()<4)
									total+=1;
							}
						}
						//Peones normales
						//Casillas amenazadas de cada pieza
						am.addAll(p.getCasillasAmenazadas());
						//Casillas defendidas de cada pieza
						for(Pieza p2 : pa.getPiezas(c)){
							for(Casilla ca : p2.getCasillasAmenazadas()){
								if(p.getCasilla()==ca){
									def.add(ca);
								}	
							}
						}
					//Resto de piezas
					}else{
						//Casillas amenazadas de cada pieza
						am.addAll(p.getCasillasAmenazadas());
						//Casillas defendidas de cada pieza
						for(Pieza p2 : pa.getPiezas(c)){
							for(Casilla ca : p2.getCasillasAmenazadas()){
								if(p.getCasilla()==ca){
									def.add(ca);
								}	
							}
						}
					}
				}
			}
			total=total+am.size()+def.size();
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
