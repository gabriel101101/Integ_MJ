package integ.tpjava.Integrador;

public class Partido {
	  Equipo equipo1;
	  Equipo equipo2;
	  int golesEquipo1;
	  int golesEquipo2;

	  // constructor---------------------------------------
	  public Partido(Equipo equipo1, Equipo equipo2) {
		super();
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
	}

	  public Partido(Equipo equipo1, int golesEquipo1, int golesEquipo2, Equipo equipo2) {
	    super();
	    this.equipo1 = equipo1;
	    this.equipo2 = equipo2;
	    this.golesEquipo1 = golesEquipo1;
	    this.golesEquipo2 = golesEquipo2;
	  }

	  // getters y setters---------------------------------

	  public void setEquipo1(Equipo equipo1) {
	    this.equipo1 = equipo1;
	  }

	  public Equipo getEquipo1() {
	    return equipo1;
	  }

	  public void setEquipo2(Equipo equipo2) {
	    this.equipo2 = equipo2;
	  }

	  public Equipo getEquipo2() {
	    return equipo2;
	  }

	  // -----------------golesEquipos----------------------

	  public void setGolesEquipo1(int golesEquipo1) {
	    this.golesEquipo1 = golesEquipo1;
	  }

	  public int getGolesEquipo1() {
	    return golesEquipo1;
	  }

	  public void setGolesEquipo2(int golesEquipo2) {
	    this.golesEquipo2 = golesEquipo2;
	  }

	  public int getGolesEquipo2() {
	    return golesEquipo2;
	  }

	  public ResultadoEnum resultado(Equipo equipo) {
	    if (golesEquipo1 == golesEquipo2) {
	      return ResultadoEnum.EMPATE;
	    }
	    if (equipo.getNombre().equals(equipo1.getNombre())) {
	      if (golesEquipo1 > golesEquipo2) {
	        return ResultadoEnum.GANADOR;
	      } else {
	        return ResultadoEnum.PERDEDOR;
	      }
	    } else {
	      // como equipo no es equipo1, entonces es equipo2
	      if (golesEquipo2 > golesEquipo1) {
	        return ResultadoEnum.GANADOR;
	      } else {
	        return ResultadoEnum.PERDEDOR;
	      }
	    }

	  }

}
