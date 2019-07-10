package IO;

public class comuna {
	int id ;
	int coste ;
	String nombre= null;
	int vecinos[];
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCoste() {
		return coste;
	}
	public void setCoste(int coste) {
		this.coste = coste;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setVecinos(int [] aux) {
		vecinos = new int[aux.length];
		vecinos = aux;
	}
	
	public int getVecino(int i) {
		return vecinos[i];
	}
	
	public int getLength() {
		return vecinos.length;
	}
	
}
