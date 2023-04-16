package integ.tpjava.Integrador;

public class Equipo {
	
	//Atributos----------------------------------
	
		String nombre;
		String descripcion;

		
	//metodos 
		
	//constructor----------------------------------

		public Equipo(String nombre){
			
			this.nombre= nombre;
			
		}
		



	//getters y setters-----------------------------
		
	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }
		public String getNombre() {
	        return nombre;
	    }

	    

	    public void setDescripcion(String descripcion) {
	        this.descripcion = descripcion;
	    }
		public String getDescripcion() {
	        return descripcion;
	    }

		

}
