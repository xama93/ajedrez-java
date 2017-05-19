package neutron;

import java.util.ArrayList;
import java.util.List;

import modelo.AbstractPieza;
import modelo.AbstractTablero;
import modelo.Casilla;
import modelo.Color;
import modelo.Coordenada;
import modelo.excepciones.ExcepcionCoordenadaErronea;
/**
 * Clase TableroNeutron
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class TableroNeutron extends AbstractTablero {

	protected TableroNeutron() {
		super(5,5);
		for(int i=0;i<dimx;i++){
			for(int j=0;j<dimy;j++){
				try {  casillas[i][j]=new Casilla(Color.BLANCO,new Coordenada((char) ('A'+j),i+1)); }catch(ExcepcionCoordenadaErronea e) {;}
			}
		}
	}

	@Override
	public List<AbstractPieza> getAmenazas(Casilla cas, Color col) {
		List<AbstractPieza> piezas = new ArrayList<AbstractPieza>();
		PartidaNeutron pa = PartidaNeutron.getInstancia();
		if(pa!=null && cas!=null && col!=null){
			AbstractTablero t=pa.getTablero();
			for(int i=0;i<t.getDimy();i++){
				for(int j=0;j<t.getDimx();j++){
					AbstractPieza p = null;
					try {p = t.getCasillaAt((char)('A'+j),i+1).getPieza();} catch (ExcepcionCoordenadaErronea e) {;}
					if(p!=null && p instanceof Electron && ((Electron)p).getColor()==col)
					{
						for(Casilla c : p.getCasillasAmenazadas()){
							if(c!=null && c.equals(cas))
								piezas.add(p);
						}
					}
				}
			}
		}
		return piezas;
	}

	@Override
	public String toString() {
		String tab="";
		for(int i=dimx-1;i>=0;i--){
			for(int j=0;j<dimy;j++){
				if(casillas[i][j].isOcupada()){
					tab+=1;
				}else
					tab+=0;
			}
			tab+='\n';
		}
		return tab;
	}
	/**
	 * Get destino fila derecha
	 * @param c :Casilla desde la cual se quiere comprobar
	 * @return casilla :Ultima casilla a la que se puede acceder
	 */
	public Casilla getDestinoFilaDerecha(Casilla c){
		Casilla casilla = null;
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i=c.getCoordenada().getLetra()-'A';//No ha de contarse a si misma
			while(i<dimx-1 && !oc){
				i++;
				if(!casillas[c.getCoordenada().getY()-1][i].isOcupada()){
					casilla=casillas[c.getCoordenada().getY()-1][i];
				}else{
					oc = true;
				}
			}
		}
		return casilla;
	}
	/**
	 * Get destino fila izquierda
	 * @param c :Casilla desde la cual se quiere comprobar
	 * @return casilla :Ultima casilla a la que se puede acceder
	 */
	public Casilla getDestinoFilaIzquierda(Casilla c){
		Casilla casilla = null;
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i=c.getCoordenada().getLetra()-'A';
			while(i>0 && !oc){
				i--;
				if(!casillas[c.getCoordenada().getY()-1][i].isOcupada()){
					casilla=casillas[c.getCoordenada().getY()-1][i];
				}else{
					oc = true;
				}
			}
		}
		return casilla;
	}
	/**
	 * Get destino columna arriba
	 * @param c :Casilla desde la cual se quiere comprobar
	 * @return casilla :Ultima casilla a la que se puede acceder
	 */
	public Casilla getDestinoColumnaArriba(Casilla c){
		Casilla casilla = null;
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i=c.getCoordenada().getY()-1;
			while(i<dimy-1 && !oc){
				i++;
				if(!casillas[i][c.getCoordenada().getLetra()-'A'].isOcupada()){
					casilla=casillas[i][c.getCoordenada().getLetra()-'A'];
				}else{
					oc = true;
				}
			}
		}
		return casilla;
	}
	/**
	 * Get destino columna abajo
	 * @param c :Casilla desde la cual se quiere comprobar
	 * @return casilla :Ultima casilla a la que se puede acceder
	 */
	public Casilla getDestinoColumnaAbajo(Casilla c){
		Casilla casilla = null;
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i=c.getCoordenada().getY()-1;
			while(i>0 && !oc){
				i--;
				if(!casillas[i][c.getCoordenada().getLetra()-'A'].isOcupada()){
					casilla=casillas[i][c.getCoordenada().getLetra()-'A'];
				}else{
					oc = true;
				}
			}
		}
		return casilla;
	}
	/**
	 * Get destino diagonal NO
	 * @param c :Casilla desde la cual se quiere comprobar
	 * @return casilla :Ultima casilla a la que se puede acceder
	 */
	public Casilla getDestinoDiagonalNO(Casilla c){
		Casilla casilla = null;
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i,j;
			j = c.getCoordenada().getY()-1;
			i = c.getCoordenada().getLetra()-'A';
			
			while(i>0 && j<dimx-1 && !oc){
				i--;
				j++;
				if(!casillas[j][i].isOcupada()){
					casilla=casillas[j][i];
				}else{
					oc = true;
				}
			}
		}
		return casilla;
	}
	/**
	 * Get destino diagonal NE
	 * @param c :Casilla desde la cual se quiere comprobar
	 * @return casilla :Ultima casilla a la que se puede acceder
	 */
	public Casilla getDestinoDiagonalNE(Casilla c){
		Casilla casilla = null;
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i,j;
			j = c.getCoordenada().getY()-1;
			i = c.getCoordenada().getLetra()-'A';
			
			while(i<dimy-1 && j<dimx-1 && !oc){
				i++;
				j++;
				if(!casillas[j][i].isOcupada()){
					casilla=casillas[j][i];
				}else{
					oc = true;
				}
			}
		}
		return casilla;
	}
	/**
	 * Get destino diagonal SO
	 * @param c :Casilla desde la cual se quiere comprobar
	 * @return casilla :Ultima casilla a la que se puede acceder
	 */
	public Casilla getDestinoDiagonalSO(Casilla c){
		Casilla casilla = null;
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i,j;
			j = c.getCoordenada().getY()-1;
			i = c.getCoordenada().getLetra()-'A';
			
			while(i>0 && j>0 && !oc){
				i--;
				j--;
				if(!casillas[j][i].isOcupada()){
					casilla=casillas[j][i];
				}else{
					oc = true;
				}
			}
		}
		return casilla;
	}
	/**
	 * Get destino diagonal SE
	 * @param c :Casilla desde la cual se quiere comprobar
	 * @return casilla :Ultima casilla a la que se puede acceder
	 */
	public Casilla getDestinoDiagonalSE(Casilla c){
		Casilla casilla = null;
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i,j;
			j = c.getCoordenada().getY()-1;
			i = c.getCoordenada().getLetra()-'A';
			
			while(i<dimy-1 && j>0 && !oc){
				i++;
				j--;
				if(!casillas[j][i].isOcupada()){
					casilla=casillas[j][i];
				}else{
					oc = true;
				}
			}
		}
		return casilla;
	}
}
