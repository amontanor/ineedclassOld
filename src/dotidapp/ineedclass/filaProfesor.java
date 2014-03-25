package dotidapp.ineedclass;

import android.graphics.drawable.Drawable;


public class filaProfesor  {

	private Drawable imagen;
	private String nombre, precio;
	private int id;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public filaProfesor(Drawable imagen, String nombre, String precio) {
		super();
		this.imagen = imagen;
		this.nombre = nombre;
		this.precio = precio;
	}

	public Drawable getImagen() {
		return imagen;
	}
	public void setImagen(Drawable imagen) {
		this.imagen = imagen;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
	
	
}
