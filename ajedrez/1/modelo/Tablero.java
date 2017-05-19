package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.excepciones.ExcepcionCoordenadaErronea;
/**
 * Clase tablero
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class Tablero {
	/**
	 * Variable dimx :Tamano del tablero horizontal
	 */
	private int dimx;
	/**
	 * Variable dimy :Tamano del tablero vertical
	 */
	private int dimy;
	/**
	 * Matriz de casillas
	 */
	private Casilla casillas[][];
	/**
	 * Constante MAX_DIMX: numero maximo del tamano de X
	 */
	public final int MAX_DIMX=26;
	/**
	 * Constante MAX_DIMY: numero maximo del tamano de X
	 */
	public final int MAX_DIMY=Integer.MAX_VALUE;
	/**
	 * Constructor del tablero por parametro Dimx y Dimy
	 * <br>Crea un tablero con las dimensiones dadas
	 * <br>Si no son pares o se pasan del rango se pone un 8 por defecto
	 * @param dimx :Tamanyo de la X
	 * @param dimy :Tamanyo de la Y
	 */
	public Tablero(int dimx,int dimy){
		if(0<dimx && dimx<MAX_DIMX && dimx%2==0)
			this.dimx=dimx;
		else
			this.dimx=8;
		if(0<dimy && dimy<MAX_DIMY && dimy%2==0)
			this.dimy=dimy;
		else
			this.dimy=8;
		//Esto solo crea el tamanyo
		casillas=new Casilla[this.dimy][this.dimx];
		
		//Aqui ya se inicializan y se le anyaden las coordenadas y el color
		for(int i=0;i<this.dimy;i++)//Vertical
		{
			for(int j=0;j<this.dimx;j++)//Horizontal
			{
				if((i%2==0 && j%2==0) || (i%2!=0 && j%2!=0)){
					try {
						casillas[i][j]=new Casilla(Color.NEGRO,new Coordenada((char)(j+'A'), i+1));
					} catch (ExcepcionCoordenadaErronea e) {;}
				}else{
					try {
						casillas[i][j]=new Casilla(Color.BLANCO,new Coordenada((char)(j+'A'), i+1));
					} catch (ExcepcionCoordenadaErronea e) {;}
				}
			}
		}
	}
	/**
	 * Constructor de copia
	 * <br>Creauna copia del tablero, pero no las casillas
	 * @param t :Tablero del cual se sacan las dim del nuevo tablero y le pone el color a las casillas
	 */
	public Tablero(Tablero t)
	{
		dimx=t.dimx;
		dimy=t.dimy;
		casillas=new Casilla[dimx][dimy];
		
		for(int i=0;i<this.dimy;i++)//Vertical
		{
			for(int j=0;j<this.dimx;j++)//Horizontal
			{
				if((i%2==0 && j%2==0) || (i%2!=0 && j%2!=0)){
					try {
						casillas[i][j]=new Casilla(Color.NEGRO,new Coordenada((char)(j+'A'), i+1));
					} catch (ExcepcionCoordenadaErronea e) {;}
				}else{
					try {
						casillas[i][j]=new Casilla(Color.BLANCO,new Coordenada((char)(j+'A'), i+1));
					} catch (ExcepcionCoordenadaErronea e) {;}
				}
			}
		}
	}
	/**
	 * Coloca Pieza at
	 * <br>Coloca la pieza en la coordenada
	 * @param c :Coordenada donde se va a poner la pieza
	 * @param p :Pieza que se quiere colocar
	 * @return true/false :Devuelve un booleano indicando si se a podido colocar la pieza en esa coordenada
	 * @throws ExcepcionCoordenadaErronea :La coordenada para acceder al tablero es erronea
	 */
	public boolean colocaPiezaAt(Coordenada c,Pieza p) throws ExcepcionCoordenadaErronea{
		if(c.getLetra()>='A' && (int)(c.getLetra()-'A')<dimx && 0<(c.getY()) && (c.getY()-1)<dimy){
			if(p.isValida() && !casillas[c.getY()-1][(int)(c.getLetra()-'A')].isNula())
				return casillas[c.getY()-1][(int)(c.getLetra()-'A')].setPieza(p);
		}else 
			throw new ExcepcionCoordenadaErronea(c.getLetra(),c.getY());
		return false;
	}
	/**
	 * Get casilla at de coordenada
	 * @param c :Coordenada necesaria para devolver la casilla
	 * @return Casilla :Casilla asociada a esa coordenada, si no existe devuelve CasillaError
	 * @throws ExcepcionCoordenadaErronea :La coordenada para acceder al tablero es erronea
	 */
	public Casilla getCasillaAt(Coordenada c) throws ExcepcionCoordenadaErronea {
		if(c.getLetra()>='A' && (int)(c.getLetra()-'A')<dimx && 0<(c.getY()) && (c.getY()-1)<dimy)
			return getCasillaAt(c.getLetra(),c.getY());//Aprovechamos que es un metodo sobrecargado que realiza lo mismo
		throw new ExcepcionCoordenadaErronea(c.getLetra(),c.getY());
	}
	/**
	 * Get casilla at de parametros
	 * @param c :1 parametro de la coordenada
	 * @param y :2 parametro de la coordenada
	 * @return Casilla :Casilla asociada a esa coordenada, si no existe devuelve CasillaError
	 * @throws ExcepcionCoordenadaErronea 
	 */
	public Casilla getCasillaAt(char c,int y) throws ExcepcionCoordenadaErronea{
		if(c>='A' && (int)(c-'A')<dimx && 0<(y) && (y-1)<dimy){
			Casilla ca=casillas[y-1][(int)c-'A'];
			return ca;
		}
		throw new ExcepcionCoordenadaErronea(c,y);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dimx;
		result = prime * result + dimy;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Tablero))
			return false;
		Tablero other = (Tablero) obj;
		if (dimx != other.dimx)
			return false;
		if (dimy != other.dimy)
			return false;
		return true;
	}
	/**
	 * Metodo toString
	 * <br>0 -> Casilla vacia
	 * <br>1 -> Casilla ocupada
	 * @return String :El tablero con el formato de arriba
	 */
	public String toString() {
		String tablero = new String();
		for(int i=dimy-1;i>=0;i--)
		{
			for(int j=0;j<dimx;j++)
			{
				if(casillas[i][j].isOcupada())
				{
					tablero+=("1");
				}else{
					tablero+=("0");
				}
			}
			
			tablero+=("\n");
		}
		return tablero;
	}
	/**
	 * Get dim x
	 * @return dimx :Dimension X
	 */
	public int getDimx() {
		return dimx;
	}
	/**
	 * Get dim y
	 * @return dimy :Dimension Y
	 */
	public int getDimy() {
		return dimy;
	}
	/**
	 * Metodo get amenazas
	 * <br>Obtiene una lista de piezas del color dado que amenazan la casilla
	 * @param cas :Casilla que se quiere amenazar
	 * @param col :Color de las piezas
	 * @return List<Pieza> :Lista de piezas que amenazan la casilla
	 */
	public List<Pieza> getAmenazas(Casilla cas,Color col){
		List<Pieza> piezas = new ArrayList<Pieza>();
		
		PartidaAjedrez pa = PartidaAjedrez.getInstancia();
		if(pa!=null && cas!=null && col!=null){
			Tablero t=pa.getTablero();
			for(int i=0;i<t.getDimy();i++){
				for(int j=0;j<t.getDimx();j++){
					Pieza p = t.casillas[i][j].getPieza();
					if(p!=null && p.getColor()==col)
					{
						if(p.getCasillasAmenazadas().contains(cas)){
							piezas.add(p);
						}
					}
				}
			}
		}
		return piezas;
	}
	/**
	 * Metodo get columna arriba
	 * <br>Obtiene la lista de casillas de arriba de la casilla dada hasta la proxima pieza
	 * @param c :Casilla origen
	 * @return List<Casilla> :Lista de casillas en la misma columna hacia arriba
	 */
	public List<Casilla> getColumnaArriba(Casilla c){
		List<Casilla> cas = new ArrayList<Casilla>();
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i=c.getCoordenada().getY()-1;
			while(i<dimy-1 && !oc){
				i++;
				if(!casillas[i][c.getCoordenada().getLetra()-'A'].isOcupada()){
					cas.add(casillas[i][c.getCoordenada().getLetra()-'A']);
				}else{
					oc = true;
					cas.add(casillas[i][c.getCoordenada().getLetra()-'A']);
				}
			}
		}
		return cas;
	}
	/**
	 * Metodo get columna abajo
	 * <br>Obtiene la lista de casillas de abajo de la casilla dada hasta la proxima pieza
	 * @param c :Casilla origen
	 * @return List<Casilla> :Lista de casillas en la misma columna hacia abajo
	 */
	public List<Casilla> getColumnaAbajo(Casilla c){
		List<Casilla> cas = new ArrayList<Casilla>();
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i=c.getCoordenada().getY()-1;
			while(i>0 && !oc){
				i--;
				if(!casillas[i][c.getCoordenada().getLetra()-'A'].isOcupada()){
					cas.add(casillas[i][c.getCoordenada().getLetra()-'A']);
				}else{
					oc = true;
					cas.add(casillas[i][c.getCoordenada().getLetra()-'A']);
				}
			}
		}
		return cas;
	}
	/**
	 * Metodo get fila derecha
	 * <br>Obtiene la lista de casillas de la derecha de la casilla dada hasta la proxima pieza
	 * @param c :Casilla origen
	 * @return List<Casilla> :Lista de casillas en la misma fila hacia derecha
	 */
	public List<Casilla> getFilaDerecha(Casilla c){
		List<Casilla> cas = new ArrayList<Casilla>();
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i=c.getCoordenada().getLetra()-'A';//No ha de contarse a si misma
			while(i<dimx-1 && !oc){
				i++;
				if(!casillas[c.getCoordenada().getY()-1][i].isOcupada()){
					cas.add(casillas[c.getCoordenada().getY()-1][i]);
				}else{
					oc = true;
					cas.add(casillas[c.getCoordenada().getY()-1][i]);
				}
			}
		}	
		return cas;
	}
	/**
	 * Metodo get fila izquierda
	 * <br>Obtiene la lista de casillas de la izquierda de la casilla dada hasta la proxima pieza
	 * @param c :Casilla origen
	 * @return List<Casilla> :Lista de casillas en la misma fila hacia izquierda
	 */
	public List<Casilla> getFilaIzquierda(Casilla c){
		List<Casilla> cas = new ArrayList<Casilla>();
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i=c.getCoordenada().getLetra()-'A';
			while(i>0 && !oc){
				i--;
				if(!casillas[c.getCoordenada().getY()-1][i].isOcupada()){
					cas.add(casillas[c.getCoordenada().getY()-1][i]);
				}else{
					oc = true;
					cas.add(casillas[c.getCoordenada().getY()-1][i]);
				}
			}
		}
		return cas;
	}
	/**
	 * Metodo get diagonal NO
	 * <br>Obtiene la lista de casillas de la diagonal NO de la casilla dada hasta la proxima pieza
	 * @param c :Casilla origen
	 * @return List<Casilla> :Lista de casilla en la diagonal NO desde la casilla
	 */
	public List<Casilla> getDiagonalNO(Casilla c){
		List<Casilla> cas = new ArrayList<Casilla>();
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i,j;
			j = c.getCoordenada().getY()-1;
			i = c.getCoordenada().getLetra()-'A';
			
			while(i>0 && j<dimx-1 && !oc){
				i--;
				j++;
				if(!casillas[j][i].isOcupada()){
					cas.add(casillas[j][i]);
				}else{
					oc = true;
					cas.add(casillas[j][i]);
				}
			}
		}
		return cas;
	}
	/**
	 * Metodo get diagonal NE
	 * <br>Obtiene la lista de casillas de la diagonal NE de la casilla dada hasta la proxima pieza
	 * @param c :Casilla origen
	 * @return List<Casilla> :Lista de casillas en la diagonal NE desde la casilla
	 */
	public List<Casilla> getDiagonalNE(Casilla c){
		List<Casilla> cas = new ArrayList<Casilla>();
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i,j;
			j = c.getCoordenada().getY()-1;
			i = c.getCoordenada().getLetra()-'A';
			
			while(i<dimy-1 && j<dimx-1 && !oc){
				i++;
				j++;
				if(!casillas[j][i].isOcupada()){
					cas.add(casillas[j][i]);
				}else{
					oc = true;
					cas.add(casillas[j][i]);
				}
			}
		}
		return cas;
	}
	/**
	 * Metodo get diagonal SO
	 * <br>Obtiene la lista de casillas de la diagonal SO de la casilla dada hasta la proxima pieza
	 * @param c :Casilla origen
	 * @return List<Casilla> :Lista de casillas en la diagonal SO desde la casilla
	 */
	public List<Casilla> getDiagonalSO(Casilla c){
		List<Casilla> cas = new ArrayList<Casilla>();
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i,j;
			j = c.getCoordenada().getY()-1;
			i = c.getCoordenada().getLetra()-'A';
			
			while(i>0 && j>0 && !oc){
				i--;
				j--;
				if(!casillas[j][i].isOcupada()){
					cas.add(casillas[j][i]);
				}else{
					oc = true;
					cas.add(casillas[j][i]);
				}
			}
		}
		return cas;
	}
	/**
	 * Metodo get diagonal SE
	 * <br>Obtiene la lista de casillas de la diagonal SE de la casilla dada hasta la proxima pieza
	 * @param c :Casilla origen
	 * @return List<Casilla> :Lista de casillas en la diagonal SE desde la casilla
	 */
	public List<Casilla> getDiagonalSE(Casilla c){
		List<Casilla> cas = new ArrayList<Casilla>();
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			int i,j;
			j = c.getCoordenada().getY()-1;
			i = c.getCoordenada().getLetra()-'A';
			
			while(i<dimy-1 && j>0 && !oc){
				i++;
				j--;
				if(!casillas[j][i].isOcupada()){
					cas.add(casillas[j][i]);
				}else{
					oc = true;
					cas.add(casillas[j][i]);
				}
			}
		}
		return cas;
	}
	/**
	 * Metodo get saltos caballo
	 * <br>Obtiene la lista de casillas de todos los saltos posibles del caballo desde la casilla dada hasta la proxima pieza
	 * @param c :Casilla origen
	 * @return List<Casilla> :Lista de casillas de todos los saltos del caballo
	 */
	public List<Casilla> getSaltosCaballo(Casilla c){
		List<Casilla> cas = new ArrayList<Casilla>();
		boolean oc = false;
		if(c!=null && (c.getCoordenada().getLetra()!='0' || c.getCoordenada().getY()!=0)){
			Coordenada[] c8 = new Coordenada[8];
		
			//Crear todas las coordenadas
			try{c8[0]=new Coordenada((char)(c.getCoordenada().getLetra()+1),c.getCoordenada().getY()+2);}catch (ExcepcionCoordenadaErronea e){;}
			try{c8[1]=new Coordenada((char)(c.getCoordenada().getLetra()+2),c.getCoordenada().getY()+1);}catch (ExcepcionCoordenadaErronea e){;}
			try{c8[2]=new Coordenada((char)(c.getCoordenada().getLetra()+2),c.getCoordenada().getY()-1);}catch (ExcepcionCoordenadaErronea e){;}
			try{c8[3]=new Coordenada((char)(c.getCoordenada().getLetra()+1),c.getCoordenada().getY()-2);}catch (ExcepcionCoordenadaErronea e){;}
			try{c8[4]=new Coordenada((char)(c.getCoordenada().getLetra()-1),c.getCoordenada().getY()-2);}catch (ExcepcionCoordenadaErronea e){;}
			try{c8[5]=new Coordenada((char)(c.getCoordenada().getLetra()-2),c.getCoordenada().getY()-1);}catch (ExcepcionCoordenadaErronea e){;}
			try{c8[6]=new Coordenada((char)(c.getCoordenada().getLetra()-2),c.getCoordenada().getY()+1);}catch (ExcepcionCoordenadaErronea e){;}
			try{c8[7]=new Coordenada((char)(c.getCoordenada().getLetra()-1),c.getCoordenada().getY()+2);}catch (ExcepcionCoordenadaErronea e){;}
			
			//Comprobar las piezas
			for(int i=0;!oc && i<8;i++){
				if(c8[i]!=null)
				try {
					cas.add(getCasillaAt(c8[i]));
				}catch (ExcepcionCoordenadaErronea e){;}
			}
		}
		return cas;
	}
}
