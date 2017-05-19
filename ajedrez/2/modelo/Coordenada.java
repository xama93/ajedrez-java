package modelo;

import modelo.excepciones.ExcepcionCoordenadaErronea;
/**
 * Clase coordenada
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class Coordenada {
    /**
     * Variables:
     * <br>letra :Variable en el eje OX
     */
    private char letra;
    /** 
     * y :Variable en el eje OY
     */
    private int y;
    /** 
	 * Metodo estatico constante
	 * <br>Devuelve la coordenada 00
	 */     
    public static final Coordenada coordenadaError= new Coordenada();
    /**
     * Constructor vacio
     * <br>Crea la coordenada 00 por defecto
     */
    public Coordenada(){ letra='0'; y=0; }
    /**
     * Constructor con parametros
     * <br>Crea una coordenada con los 2 parametros que se le pasan
     * @param le :1 parametro y coordenada X del tablero
     * @param y :2 parametro y coordenada Y del tablero
     * @throws ExcepcionCoordenadaErronea :Los valores de la coordenada no son validos
     */
    public Coordenada(char le,int y) throws ExcepcionCoordenadaErronea{
    	if(le>='A' && le<='Z' && y>0) {
    		letra=le;
    		this.y=y;
    	}else{
    		throw new ExcepcionCoordenadaErronea(le,y);
    	}
    }
    /**
     * Constructor de copia
     * <br>Crea una copia exacta a la coordenada que se le pasa por parametros
     * @param otra :Coordenada a duplicar
     */
    public Coordenada(Coordenada otra) {
        this.letra=otra.letra;
        this.y=otra.y;
    }
    /**
     * Get letra
     * @return letra :Devuelve la coordenada X
     */
    public char getLetra(){ return letra; }
    /**
     * Get y
     * @return y :Devuelve la coordenada Y
     */
    public int getY(){ return y; }
    /**
     * Metodo imprimir
     * <br>Imprime las dos coordenadas seguidas sin retorno de carro
     */
    public void imprimir() {
        System.out.print(letra+""+y);
    }
    /**
     * Metodo equals
     * <br>Comparacion de 2 coordenadas
     * @param obj :Este parametro es el que hay que comparar con la coordenada
     * @return boolean :Devuelve true o false dependiendo si son iguales o no
     */
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (!(obj instanceof Coordenada)) return false;
            Coordenada c = (Coordenada) obj;
            return (this.y == c.y && this.letra == c.letra);
    }
    /**
     *  Metodo hashCode
     *  @return result
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + letra;
		result = prime * result + y;
		return result;
	}
    /**
     * Metodo toString
     * <br>Convierte la coordenada en string
     * @return String :Coordenada en forma string: A1
     */
    public String toString(){
        return letra+""+y;
    }
}
