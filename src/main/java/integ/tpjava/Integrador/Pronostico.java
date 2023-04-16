package integ.tpjava.Integrador;

public class Pronostico {
	
	 // Atributos------------------------------------
	  private Equipo equipo;
	  private Partido partido;
	  private ResultadoEnum resultado;
	 

	  // Metodos

	  // Constructor----------------------------------

	  public Pronostico(Equipo equipo, Partido partido, ResultadoEnum resultado) {
	    
	    this.equipo = equipo;
	    this.partido = partido;
	    this.resultado = resultado;
	    
	   

	  }

	  // getters y setters-----------------------------
	

	  public void setEquipo(Equipo equipo) {
	    this.equipo = equipo;
	  }

	  public Equipo getEquipo() {
	    return equipo;
	  }

	  public void setPartido(Partido partido) {
	    this.partido = partido;
	  }

	  public Partido getPartido() {
	    return partido;
	  }
	  
	  
	  public void setResultadoEnum(ResultadoEnum resultado) {
		    this.resultado=resultado;
		  }

		  public ResultadoEnum getResultadoEnum() {
		    return resultado;
		  }
	  
	  
	  public int puntos() {
	    // this.resultado -> pred
		  ResultadoEnum resultadoReal = this.partido.resultado(this.equipo); //error consola
	    if (this.resultado.equals(resultadoReal)) {
	      return 1;
	    } else {
	      return 0;
	    }

	  }


}
