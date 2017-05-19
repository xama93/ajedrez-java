package neutron;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import modelo.AbstractPartida;
import modelo.AbstractPieza;
import modelo.Casilla;
import modelo.Color;
import modelo.Coordenada;
import modelo.excepciones.ExcepcionCasillaDestinoOcupada;
import modelo.excepciones.ExcepcionCasillaOrigenVacia;
import modelo.excepciones.ExcepcionCoordenadaErronea;
import modelo.excepciones.ExcepcionJuegoTablero;
import modelo.excepciones.ExcepcionMovimientoIlegal;
import modelo.excepciones.ExcepcionNoExisteMovimiento;
import modelo.excepciones.ExcepcionTurnoDelContrario;
import neutron.excepciones.ExcepcionTurnoNeutron;
/**
 * Clase Partida neutron
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class PartidaNeutron extends AbstractPartida {
	
	private static PartidaNeutron instancia;
	private Color turno;
	protected PartidaNeutron(){
		tablero=new TableroNeutron();
		turno=Color.BLANCO;
	}
	/**
	 * Get instancia
	 * @return instancia :Devuelve si se a podido crear una nueva PartidaAjedrez o ya esta creada antes
	 */
	public static PartidaNeutron getInstancia(){
		if(instancia==null)
			instancia = new PartidaNeutron();
		return instancia;
	}
	@Override
	public void run() throws FileNotFoundException {
		File f=new File(fentrada);
		Scanner sc=new Scanner(f);
		sc.close();
		inicializaTablero();
		cargaMovimientos();
		for(int i=0;i<getNumMovimientos();i++){
			try {
				ejecutaMovimiento(i);
			} catch (ExcepcionJuegoTablero e) {
				System.err.println(e.getMessage());
			}
		}
		PrintStream ps = new PrintStream(fsalida);
		ps.print(this.toString());
		ps.close();
	}
	@Override
	public String toString() {
		String salida = new String();
		try {
			for(int i=tablero.getDimy();i>0;i--){
				for(int j=0;j<tablero.getDimx();j++){
					Coordenada c = new Coordenada((char)('A'+j),i);
					if(getPiezaAt(c)!=null){
						salida+=getPiezaAt(c).toString();
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
	@Override
	public void borrarPartida(){
		super.borrarPartida();
		turno=Color.BLANCO;
	}
	/**
	 * Metodo add movimiento
	 * <br>Comprueba si las coordenadas leidas por el fichero son correctas y las añade al array de movs
	 * @param con :Coordenada origen neutron
	 * @param cdn :Coordenada destino neutron
	 * @param coe :Coordenada origen electron
	 * @param cde :Coordenada destino electron
	 * @throws ExcepcionCoordenadaErronea :Lanza la excepcion cuando una coordenada es erronea
	 */
	public void addMovimiento(Coordenada con,Coordenada cdn,Coordenada coe,Coordenada cde) throws ExcepcionCoordenadaErronea{
		if(con!=null && cdn!=null && coe!=null && cde!=null){
			if(con.getLetra()>='A' && con.getLetra()<tablero.getDimx()+'A' && con.getY()>=1 && con.getY()<=tablero.getDimy()){
				if(cdn.getLetra()>='A' && cdn.getLetra()<tablero.getDimx()+'A' && cdn.getY()>=1 && cdn.getY()<=tablero.getDimy()){
					if(coe.getLetra()>='A' && coe.getLetra()<tablero.getDimx()+'A' && coe.getY()>=1 && coe.getY()<=tablero.getDimy()){
						if(cde.getLetra()>='A' && cde.getLetra()<tablero.getDimx()+'A' && cde.getY()>=1 && cde.getY()<=tablero.getDimy()){
							movs.add(new MovimientoNeutron(con,cdn,coe,cde));
						}else throw new ExcepcionCoordenadaErronea(cde.getLetra(),cde.getY());
					}else throw new ExcepcionCoordenadaErronea(coe.getLetra(),coe.getY());
				}else throw new ExcepcionCoordenadaErronea(cdn.getLetra(),cdn.getY());
			}else throw new ExcepcionCoordenadaErronea(con.getLetra(),con.getY());
		}else throw new ExcepcionCoordenadaErronea('0',0);
	}
	/**
	 * Metodo carga movimiento
	 * <br>Lee un guarda los movimientos en el array
	 */
	protected void cargaMovimientos(){
		File f=new File(fentrada);
		try {
			Scanner sc = new Scanner(f);
			String s,aux="";
			while(sc.hasNextLine()){
				s=sc.nextLine();
				try {
					Coordenada co[]=new Coordenada[4];
					int i,j,k,l,coor[]=new int[4];
					
					for(i=1;s.charAt(i)!=' ';i++) aux+=s.charAt(i);
					coor[0]=Integer.parseInt(aux); aux="";
					co[0]=new Coordenada(s.charAt(0),coor[0]);
					
					for(j=i+2;s.charAt(j)!=' ';j++) aux+=s.charAt(j);
					coor[1]=Integer.parseInt(aux); aux="";
					co[1]=new Coordenada(s.charAt(i+1),coor[1]);
					
					for(k=j+2;s.charAt(k)!=' ';k++) aux+=s.charAt(k);
					coor[2]=Integer.parseInt(aux); aux="";
					co[2]=new Coordenada(s.charAt(j+1),coor[2]);
					
					for(l=k+2;l<s.length();l++) aux+=s.charAt(l);
					coor[3]=Integer.parseInt(aux); aux="";
					co[3]=new Coordenada(s.charAt(k+1),coor[3]);
					
					addMovimiento(co[0],co[1],co[2],co[3]);
				} catch (ExcepcionCoordenadaErronea e) {
					System.err.println(e.getMessage());
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metodo ejecuta movimiento
	 * @param i
	 */
	/*
	 * void ejecutaMovimiento(int)
Ejecuta el i-ésimo movimiento de la partida. 
Primero intenta mover el neutrón y si tiene éxito intenta mover el electrón. 
Si no puede mover una de las piezas, no debe mover ninguna de las dos. 
Lanza excepciones derivadas de ExcepcionMovimiento: ExcepciionCasillaOrigenVacia, ExcepcionTurnoDelContrario o ExcepcionTurnoNeutron.
 Esta última se lanza cuando se intenta mover la pieza del jugador antes que el neutrón o viceversa o si se intenta mover dos veces el neutrón o dos piezas del jugador en el mismo movimiento. 
 Si el movimiento completo se realiza con éxito, pasa el turno al siguiente jugador.
	 */
	protected void ejecutaMovimiento(int i) throws ExcepcionCasillaOrigenVacia,ExcepcionTurnoDelContrario,ExcepcionTurnoNeutron{
		try {
			boolean error=false;
			if(getMovimientoAt(i).getClass()==MovimientoNeutron.class){
				MovimientoNeutron m = (MovimientoNeutron)getMovimientoAt(i);
				Coordenada con = m.getCoordenadaOrigen();
				Coordenada cdn = m.getCoordenadaDestino();
				Coordenada coe = m.getCoordenadaOrigenElectron();
				Coordenada cde = m.getCoordenadaDestinoElectron();
				Casilla caon = tablero.getCasillaAt(con);
				Casilla cadn = tablero.getCasillaAt(cdn);
				Casilla caoe = tablero.getCasillaAt(coe);
				Casilla cade = tablero.getCasillaAt(cde);
				AbstractPieza n = caon.getPieza();
				AbstractPieza e = caoe.getPieza();
				if(n!=null && e!=null)
					if(n instanceof Neutron && e instanceof Electron){
						if(((Electron)e).getColor()==turno){
							try{
								n.mueve(cadn);
								e.mueve(cade);
							} catch (ExcepcionCasillaDestinoOcupada ex) {
								error=true;
								//Significa que se ha movido el primero y el segundo a provocado el error
								if(caon.getPieza()==null){
									caon.setPieza(cadn.quitaPieza());
									caon.getPieza().setHaMovido(false);
								}									
								System.err.println(ex.getMessage());
							} catch (ExcepcionMovimientoIlegal ex) {
								error=true;
								//Significa que se ha movido el primero y el segundo a provocado el error
								if(caon.getPieza()==null){
									caon.setPieza(cadn.quitaPieza());
									caon.getPieza().setHaMovido(false);
								}
								System.err.println(ex.getMessage());
							}
						}else throw new ExcepcionTurnoDelContrario(m);
					}else throw new ExcepcionTurnoNeutron(m,(PiezaNeutron)n);
				else throw new ExcepcionCasillaOrigenVacia(m);
				if(!error) cambioDeTurno();
			}
		} catch (ExcepcionNoExisteMovimiento e) {
			System.err.println(e.getMessage());
		} catch (ExcepcionCoordenadaErronea e) {
			System.err.println(e.getMessage());
		}
	}
	@Override
	public void inicializaTablero() {
		borrarPartida();
		for(int i=0;i<tablero.getDimx();i++){
			for(int j=1;j<=tablero.getDimy();j++){
				Coordenada c;
				try {
					c = new Coordenada((char) ('A'+i),j);
					if(j==1){
						tablero.colocaPiezaAt(c,new Electron(Color.BLANCO));
					}else if(j==5){
						tablero.colocaPiezaAt(c,new Electron(Color.NEGRO));
					}else if(i==2 && j==3){
						tablero.colocaPiezaAt(c,new Neutron());
					}
				} catch (ExcepcionCoordenadaErronea e) {;}
			}
		}
	}
	/**
	 * Metodo cambio de turno
	 * <br>Alterna el turno entre blanco y negro
	 */
	private void cambioDeTurno(){
		if(turno==Color.BLANCO) turno=Color.NEGRO;
		else turno=Color.BLANCO;
	}
}
