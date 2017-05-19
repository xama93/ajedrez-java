package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.FactoriaPieza;

import modelo.excepciones.ExcepcionCasillaDestinoOcupada;
import modelo.excepciones.ExcepcionCasillaOrigenVacia;
import modelo.excepciones.ExcepcionCoordenadaErronea;
import modelo.excepciones.ExcepcionMovimientoCoronacion;
import modelo.excepciones.ExcepcionMovimientoIlegal;
import modelo.excepciones.ExcepcionNoExisteMovimiento;
import modelo.excepciones.ExcepcionPiezaDesconocida;
import modelo.excepciones.ExcepcionPosicionNoValida;
import modelo.excepciones.ExcepcionTurnoDelContrario;
/**
 * Clase partidaAjedrez
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class PartidaAjedrez {
	/**
	 * Variables:
	 * <br>tablero :Tablero (8x8) de la partida
	 */
	private Tablero tablero;
	/**
	 * piezas :Lista de piezas que se encuentran en el tablero
	 */
	private ArrayList<Pieza> piezas;
	/**
	 * movs :Lista de movimientos que se han realizado en el tablero
	 */
	private ArrayList<Movimiento> movs;
	/**
	 * fentrada :Nombre del fichero de entrada
	 */
	private String fentrada = null;
	/**
	 * fsalida: Nombre del fichero de salida
	 */
	private String fsalida = null;
	/**
	 * f :Archivo de entrada
	 */
	private File f;
	/**
	 * turno :Turno de la partida
	 */
	private Color turno;
	/**
	 * Variable estatica instancia
	 * <br>instancia :Guarda la instancia de la clase
	 */
	private static PartidaAjedrez instancia = null;
	/**
	 * Constructor partidaAjedrez
	 * <br>Metodo protegido
	 * <br>Crea un tablero 8x8
	 * <br>Crea las dos listas (piezas,movs) 
	 */
	protected PartidaAjedrez(){
		tablero = new Tablero(8,8);
		movs = new ArrayList<Movimiento>();
		piezas = new ArrayList<Pieza>();
		turno = Color.BLANCO;
	}
	/**
	 * Metodo run
	 * <br>Lee el fichero de entrada, coloca las piezas, realiza los movimientos y guarda el tablero final en otro fichero
	 */
	public void run(){
        try {
            f = new File(fentrada);
		
            this.colocaPiezas();
            this.cargaMovimientos();

            for(int i=0;i<getNumMovimientos();i++){
                try {
                	this.ejecutaMovimiento(i);
				} catch (ExcepcionTurnoDelContrario e) {
					System.err.println(e.getMessage());
				} catch (ExcepcionCasillaOrigenVacia e) {
					System.err.println(e.getMessage());
				} catch (ExcepcionNoExisteMovimiento e) {
					System.err.println(e.getMessage());
				}
            }

			PrintStream ps = new PrintStream(fsalida);
			ps.print(this.toString());
			ps.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Metodo inicializar tablero
	 * <br>Pone las piezas por defecto como serian en un tablero normal
	 */
	private void inicializaTablero(){
		try {
			//Peones blancos
			for(int i=0;i<tablero.getDimx();i++){
				Pieza p = FactoriaPieza.creaPieza("Peon",Color.BLANCO);
				tablero.colocaPiezaAt(new Coordenada((char)(i+'A'),2),p);
				piezas.add(p);
			}
			//Peones negros
			for(int i=0;i<tablero.getDimx();i++){
				Pieza p = FactoriaPieza.creaPieza("Peon",Color.NEGRO);
				tablero.colocaPiezaAt(new Coordenada((char)(i+'A'),7),p);
				piezas.add(p);
			}
			//Rey blanco y negro
			Pieza r1 = FactoriaPieza.creaPieza("Rey",Color.BLANCO),r2 = FactoriaPieza.creaPieza("Rey",Color.NEGRO);
			tablero.colocaPiezaAt(new Coordenada('E',1),r1);
			piezas.add(r1);
			tablero.colocaPiezaAt(new Coordenada('E',8),r2);
			piezas.add(r2);
			//Dama blanco y negro
			Pieza d1 = FactoriaPieza.creaPieza("Dama",Color.BLANCO),d2 = FactoriaPieza.creaPieza("Dama",Color.NEGRO);
			tablero.colocaPiezaAt(new Coordenada('D',1),d1);
			piezas.add(d1);
			tablero.colocaPiezaAt(new Coordenada('D',8),d2);
			piezas.add(d2);
			//Alfil blanco
			Pieza a1 = FactoriaPieza.creaPieza("Alfil",Color.BLANCO),a2 = FactoriaPieza.creaPieza("Alfil",Color.BLANCO);
			tablero.colocaPiezaAt(new Coordenada('C',1),a1);
			piezas.add(a1);
			tablero.colocaPiezaAt(new Coordenada('F',1),a2);
			piezas.add(a2);
			//Alfil negro
			Pieza a3 = FactoriaPieza.creaPieza("Alfil",Color.NEGRO),a4 = FactoriaPieza.creaPieza("Alfil",Color.NEGRO);
			tablero.colocaPiezaAt(new Coordenada('C',8),a3);
			piezas.add(a3);
			tablero.colocaPiezaAt(new Coordenada('F',8),a4);
			piezas.add(a4);
			//Caballo blanco
			Pieza c1 = FactoriaPieza.creaPieza("Caballo",Color.BLANCO),c2 = FactoriaPieza.creaPieza("Caballo",Color.BLANCO);
			tablero.colocaPiezaAt(new Coordenada('B',1),c1);
			piezas.add(c1);
			tablero.colocaPiezaAt(new Coordenada('G',1),c2);
			piezas.add(c2);
			//Caballo negro
			Pieza c3 = FactoriaPieza.creaPieza("Caballo",Color.NEGRO),c4 = FactoriaPieza.creaPieza("Caballo",Color.NEGRO);;
			tablero.colocaPiezaAt(new Coordenada('B',8),c3);
			piezas.add(c3);
			tablero.colocaPiezaAt(new Coordenada('G',8),c4);
			piezas.add(c4);
			//Torre blanco
			Pieza t1 = FactoriaPieza.creaPieza("Torre",Color.BLANCO),t2 = FactoriaPieza.creaPieza("Torre",Color.BLANCO);
			tablero.colocaPiezaAt(new Coordenada('A',1),t1);
			piezas.add(t1);
			tablero.colocaPiezaAt(new Coordenada('H',1),t2);
			piezas.add(t2);
			//Torre negro
			Pieza t3 = FactoriaPieza.creaPieza("Torre",Color.NEGRO),t4 = FactoriaPieza.creaPieza("Torre",Color.NEGRO);
			tablero.colocaPiezaAt(new Coordenada('A',8),t3);
			piezas.add(t3);
			tablero.colocaPiezaAt(new Coordenada('H',8),t4);
			piezas.add(t4);
		} catch (ExcepcionCoordenadaErronea e) {
			System.err.println(e.getMessage());
		} catch (ExcepcionPiezaDesconocida e) {
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Set entrada
	 * @param f :Nombre del fichero de entrada
	 */
	public void setEntrada(String f){ fentrada=f; }
	/**
	 * Set salida
	 * @param f :nombre del fichero de salida
	 */
	public void setSalida(String f){ fsalida=f; }
	/**
	 * Get entrada
	 * @return fentrada :Nombre del fichero de entrada
	 */
	public String getEntrada(){ return fentrada; }
	/**
	 * Get salida
	 * @return fentrada :Nombre del fichero de salida
	 */
	public String getSalida(){ return fsalida; }
	/**
	 * Get tablero
	 * @return tablero :Devuelve el tablero
	 */
	public Tablero getTablero(){ return tablero; }
	/**
	 * Get pieza por coordenada
	 * <br>Devuelce la pieza en la coordenada c
	 * @param c :Coordenada de la pieza a buscar
	 * @return pieza :pieza asociada a esa coordenada o null si no hay ninguna
	 * @throws ExcepcionCoordenadaErronea :Se llanza cuando la coordenada no esta dentro de los limites del tablero
	 */
	public Pieza getPiezaAt(Coordenada c) throws ExcepcionCoordenadaErronea{
		return tablero.getCasillaAt(c).getPieza();
	}
	/**
	 * Get ovimiento por indice
	 * <br>Devuelve el movimiento asociado a esa posicion
	 * @param numMov :Numero del movimiento que se busca
	 * @return movimiento :Movimiento numero n (n=indice)
	 * @throws ExcepcionNoExisteMovimiento :Se lanza cuando se pasa del rango del array de movs
	 */
	public Movimiento getMovimientoAt(int numMov) throws ExcepcionNoExisteMovimiento{
		if(0<=numMov && numMov<getNumMovimientos()){
			Movimiento m = movs.get(numMov);
			return m;
		}
		throw new ExcepcionNoExisteMovimiento(numMov);
	}
	/**
	 * Metodo add movimiento
	 * <br>Anyade un nuevo movimiento
	 * @param co :Coordenada origen
	 * @param cd :Coordenada destino
	 * @throws ExcepcionCoordenadaErronea :Se  lanza cuando las coordenadas son iguales, o cuando se salen del rango del tablero
	 */
	public void addMovimiento(Coordenada co,Coordenada cd) throws ExcepcionCoordenadaErronea{
		if(co.getLetra()>='A' && (int)(co.getLetra()-'A')<tablero.getDimx() && 0<(co.getY()) && (co.getY()-1)<tablero.getDimy()){
			if(cd.getLetra()>='A' && (int)(cd.getLetra()-'A')<tablero.getDimx() && 0<(cd.getY()) && (cd.getY()-1)<tablero.getDimy()){
				if(!co.equals(cd)){
					Movimiento mov = new MovimientoOrdinario(co,cd);
					movs.add(mov);
				}else throw new ExcepcionCoordenadaErronea(co.getLetra(),co.getY());//Da igual porque son iguales
			}else throw new ExcepcionCoordenadaErronea(cd.getLetra(),cd.getY());
		}else throw new ExcepcionCoordenadaErronea(co.getLetra(),co.getY());
	}
	/**
	 * Metodo protegido add movimiento coronacion
	 * <br>Anyade un nuevo movimiento de coronacion
	 * @param co :Coordenada origen
	 * @param cd :Coordenada destino
	 * @param p :Pieza a la que coronar
	 * @throws ExcepcionCoordenadaErronea :Se  lanza cuando las coordenadas son iguales, o cuando se salen del rango del tablero 
	 */
	protected void addMovimientoCoronacion(Coordenada co,Coordenada cd,Pieza p) throws ExcepcionCoordenadaErronea{
		if(co.getLetra()>='A' && (int)(co.getLetra()-'A')<tablero.getDimx() && 0<(co.getY()) && (co.getY()-1)<tablero.getDimy()){
			if(cd.getLetra()>='A' && (int)(cd.getLetra()-'A')<tablero.getDimx() && 0<(cd.getY()) && (cd.getY()-1)<tablero.getDimy()){
				if(!co.equals(cd)){
					Movimiento mov = new MovimientoCoronacion(co,cd,p);
					movs.add(mov);
				}else throw new ExcepcionCoordenadaErronea(co.getLetra(),co.getY());//Da igual porque son iguales
			}else throw new ExcepcionCoordenadaErronea(cd.getLetra(),cd.getY());
		}else throw new ExcepcionCoordenadaErronea(co.getLetra(),co.getY());
	}
	/**
	 * Get num movimientos
	 * @return num_mov :Numero de movimientos guardados
	 */
	public int getNumMovimientos(){ return movs.size(); }
	/**
	 * Metodo toString
	 * <br>Mayus -> BLANCO
	 * <br>Minus -> NEGRO
	 * <br>" - " -> VACIA
	 * @return string :Formato de salida
	 */
	public String toString(){ 
		String salida = new String();
		try {
			for(int i=tablero.getDimy();i>0;i--){
				for(int j=0;j<tablero.getDimx();j++){
					Coordenada c = new Coordenada((char)('A'+j),i);
					if(this.getPiezaAt(c)!=null){
						if(this.getPiezaAt(c).getColor()==Color.BLANCO){
							salida+=getPiezaAt(c).getTipo();
						}else{
							salida+=(char)(getPiezaAt(c).getTipo()-'A'+'a');
						}
					}else{
						salida+="-";
					}
					if(j>=0 && j<7){ salida+=" "; }
				}
				salida+="\n";
			}
		} catch (ExcepcionCoordenadaErronea e) { System.err.println(e.getMessage()); }
		return salida;
	}
	/**
	 * Metodo coloca piezas
	 * <br>Va extrayendo las lineas del fichero de entrada y las coloca
	 */
	protected void colocaPiezas(){
		try {
			Scanner sc = new Scanner(f);
			String s = "";
			int bucle=0;
			boolean no_hay_piezas=false;
			for(;s!="\n" && sc.hasNextLine() && !no_hay_piezas;bucle++){
				s = sc.nextLine();
				if(s.length()>0)
					try {
						this.colocaPieza(s);
					} catch (ExcepcionPiezaDesconocida e){
						System.err.println(e.getMessage());
					}
				else{
					no_hay_piezas=true;
					if(bucle==0)
						this.inicializaTablero();
				}
			}
			sc.close();
		} catch (FileNotFoundException e){
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Metodo coloca pieza
	 * <br>Comprueba que la pieza y el color sean correctos y la coloca
	 * @param s :Linea del fichero
	 * @throws ExcepcionPiezaDesconocida :La pieza de la linea no es una pieza valida
	 */
	protected void colocaPieza(String s) throws ExcepcionPiezaDesconocida{
		Color c;
		Pieza p;
		switch(s.charAt(1)){
			case 'b':
				c = Color.BLANCO;
				p = FactoriaPieza.creaPieza(s.charAt(0),c);
				break;
			case 'n':
				c = Color.NEGRO;
				p = FactoriaPieza.creaPieza(s.charAt(0),c);
				break;
			default:
				throw new ExcepcionPiezaDesconocida(s.charAt(1));
		}				
		try {
			Coordenada coor = new Coordenada(s.charAt(3),s.charAt(4)-'0');
			if(tablero.getCasillaAt(coor).getPieza()!=null) throw new ExcepcionPosicionNoValida(coor);
			tablero.colocaPiezaAt(coor,p);
			this.piezas.add(p);
		} catch (ExcepcionCoordenadaErronea e) {
			System.err.println(e.getMessage());
		} catch (ExcepcionPosicionNoValida e) {
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Metodo carga movimientos
	 * <br>Lee los movimientos del fichero de entrada y los guarda en un ArryList
	 */
	protected void cargaMovimientos(){
		try {
			Scanner sc = new Scanner(f);
			String s = "";
			boolean no_hay_piezas=false;
			while(s!="\n" && sc.hasNextLine() && !no_hay_piezas){
				if(sc.nextLine().length()==0)
					no_hay_piezas=true;
			}
			while(s!="\n" && sc.hasNextLine()){
				s = sc.nextLine();
				if(s.length()>0){
					try {
						int cont=0;
						String num = "",num2 = "",pieza = "";
						int i,j,k,num_1,num_2;
						for(i=1;s.charAt(i)!=' ';i++){ num+=s.charAt(i); }
						for(j=i+2;(j<s.length() && s.charAt(j)!=' ');j++){ num2+=s.charAt(j); cont=1; }
						for(k=j+1;k<s.length();k++){ pieza+=s.charAt(k);cont=2; }
						Coordenada co,cd;
						Pieza p = null;
						num_1 = Integer.parseInt(num);
						num_2 = Integer.parseInt(num2);
						co = new Coordenada(s.charAt(0),num_1);
						cd = new Coordenada(s.charAt(i+1),num_2);
						if(cont==2){
								p = FactoriaPieza.creaPieza(pieza,Color.BLANCO);
								addMovimientoCoronacion(co,cd,p);
						}else addMovimiento(co, cd);
					} catch (ExcepcionCoordenadaErronea e) {
						System.err.println(e.getMessage());
					} catch (ExcepcionPiezaDesconocida e) {
						System.err.println(e.getMessage());
					}
				}
			}
			sc.close();
		} catch (FileNotFoundException e){
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Metodo ejecuta movimiento
	 * <br>Se le pasa un numero y ejecuta el movimiento que se encuentra en la posicion i-esima desde que se han introducido
	 * @param i :Numero del movimiento que se desea ejecutar
	 * @throws ExcepcionTurnoDelContrario :Se lanza cuando se intenta mover una pieza de otro turno
	 * @throws ExcepcionCasillaOrigenVacia :La casilla que marca el inicio del mov esta vacia
	 * @throws ExcepcionNoExisteMovimiento :Cuando se pasa del rango del array
	 */
	protected void ejecutaMovimiento(int i) throws ExcepcionTurnoDelContrario, ExcepcionCasillaOrigenVacia, ExcepcionNoExisteMovimiento{
		try {
			if(getMovimientoAt(i).getClass()==MovimientoOrdinario.class){
				MovimientoOrdinario m = (MovimientoOrdinario)this.getMovimientoAt(i);
				Coordenada co = m.getCoordenadaOrigen();
				Coordenada cd = m.getCoordenadaDestino();
				Casilla cao = tablero.getCasillaAt(co);
				Casilla cad = tablero.getCasillaAt(cd);
				Pieza p = cao.getPieza();
				if(p!=null)
					if(p.getColor()==turno){
	                    p.mueve(cad);
					}else throw new ExcepcionTurnoDelContrario(m);
				else throw new ExcepcionCasillaOrigenVacia(m);
	
	            this.cambioDeTurno();
			}else if(getMovimientoAt(i).getClass()==MovimientoCoronacion.class){
				MovimientoCoronacion m = (MovimientoCoronacion)this.getMovimientoAt(i);
				Coordenada co = m.getCoordenadaOrigen();
				Coordenada cd = m.getCoordenadaDestino();
				Casilla cao = tablero.getCasillaAt(co);
				Casilla cad = tablero.getCasillaAt(cd);
				Pieza p = cao.getPieza();
				Pieza cor = m.getPieza();
				if(p!=null)
					if(p.getColor()==turno){
							if(p instanceof Peon){
								p.mueve(cad);
								cor.setColor(p.getColor());
								coronar((Peon) p,cor);
							}else throw new ExcepcionMovimientoCoronacion(p,cor);
					}else throw new ExcepcionTurnoDelContrario(m);
				else throw new ExcepcionCasillaOrigenVacia(m);
	
	            this.cambioDeTurno();
			}
		} catch (ExcepcionCasillaDestinoOcupada e) {
			System.err.println(e.getMessage());
		} catch (ExcepcionCoordenadaErronea e) {
			System.err.println(e.getMessage());
		} catch (ExcepcionMovimientoIlegal e) {
			System.err.println(e.getMessage());
		} catch (ExcepcionMovimientoCoronacion e) {
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Metodo privado cambio de turno
	 * <br>Simplemente alterna los turnos de BLANCO a NEGRO y viceversa
	 */
	private void cambioDeTurno(){
		if(turno==Color.BLANCO) turno=Color.NEGRO;
		else turno=Color.BLANCO;
	}
	/**
	 * Get instancia
	 * @return instancia :Devuelve si se a podido crear una nueva PartidaAjedrez o ya esta creada antes
	 */
	public static PartidaAjedrez getInstancia(){
		if(instancia==null)
			instancia = new PartidaAjedrez();
		return instancia;
	}
	/**
	 * Metodo get piezas
	 * <br>Devuelve las piezas del mismo color
	 * @param c :Color de las piezas que quieres que se muestren
	 * @return List<Pieza> :Lista de piezas del color
	 */
	public List<Pieza> getPiezas(Color c){
		List<Pieza> piezas = null;
		piezas = new ArrayList<Pieza>();
		
		for(Pieza p : this.piezas){
			if(c==p.getColor()){
				piezas.add(p);
			}
		}
		return piezas;
	}
	/**
	 * Metodo coronar
	 * <br>Intenta coronar al peon si es posible
	 * @param peon :Peon que se quiere coronar
	 * @param pieza :Pieza a la que corona
	 * @throws ExcepcionMovimientoCoronacion :Si no se puede coronar se lanza
	 */
	protected void coronar(Peon peon,Pieza pieza) throws ExcepcionMovimientoCoronacion{
		if(pieza!=null && pieza.getCasilla()==null && peon!=null && peon.getCasilla()!=null){
			Class<? extends Pieza> p1 = pieza.getClass();
			if(!(pieza instanceof Peon || pieza instanceof Rey))
				if(peon.isEnUltimaFila() && peon.getColor()==pieza.getColor()){
					String p = "";
					for(int i=7;i<p1.toString().length();i++) p+=p1.toString().charAt(i);
					try { pieza=FactoriaPieza.creaPieza(p,peon.getColor());
					} catch (ExcepcionPiezaDesconocida e) { ; }
					if(pieza!=null){
						pieza.setCasilla(peon.quitaDeCasilla());
						pieza.setColor(peon.getColor());
						piezas.add(pieza);
					}else throw new ExcepcionMovimientoCoronacion(peon,pieza);
				}else throw new ExcepcionMovimientoCoronacion(peon,pieza);
			else throw new ExcepcionMovimientoCoronacion(peon,pieza);
		}else throw new ExcepcionMovimientoCoronacion(peon,pieza);
	}
}
