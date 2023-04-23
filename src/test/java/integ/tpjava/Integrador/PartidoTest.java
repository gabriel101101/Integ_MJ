package integ.tpjava.Integrador;

import static org.junit.Assert.assertEquals;

//import org.junit.Before;
import org.junit.Test;

public class PartidoTest {
	
	
	
	
	@Test
	public void TestPartidoEmpate(){
		
		//escenario
		Equipo equipo1 = new Equipo("Boca");
		Equipo equipo2 = new Equipo("River");
		
		Partido partido = new Partido(equipo1,3,3, equipo2);
		
		//proceso
		ResultadoEnum resultadoObtenido=partido.resultado(equipo1);
		
		//Evaluacion
		
		assertEquals(ResultadoEnum.EMPATE, resultadoObtenido);
	}
	
	
	@Test
    public void TestPartidoGanador(){
		
		//escenario
		Equipo equipo1 = new Equipo("Boca");
		Equipo equipo2 = new Equipo("River");
		Partido partido = new Partido(equipo1,4,3, equipo2);
		
		
		//proceso
		ResultadoEnum resultadoObtenido1=partido.resultado(equipo1);
		ResultadoEnum resultadoObtenido2=partido.resultado(equipo2);
		//Evaluacion
		
		assertEquals(ResultadoEnum.GANADOR, resultadoObtenido1);
		assertEquals(ResultadoEnum.PERDEDOR, resultadoObtenido2);
		
	}
	
	

}
