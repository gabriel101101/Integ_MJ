package integ.tpjava.Integrador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Principal {

	public static void main(String[] args) {

	    Collection<Partido> partidosMundial = new ArrayList<Partido>();

	    Path pathResultados = Paths.get("C:\\tp-java\\gabriel\\res\\resultado.csv");
	    List<String> lineasResultados = null;

	    try {
	      lineasResultados = Files.readAllLines(pathResultados);
	    } catch (IOException e) {
	      System.out.println("No se pudo leer la linea de resultados...");
	      System.out.println(e.getMessage());
	      System.exit(1);
	    }
	    
	    System.out.println("===================== RESULTADOS DE PARTIDOS ===================== \n");

	    boolean primera = true;

	    for (String lineaResultado : lineasResultados) {

	      if (primera) {
	        primera = false;
	      } else {

	        // Argentina,1,2,Arabia Saudita
	        String[] campos = lineaResultado.split(",");
	        
	        Equipo equipo1 = new Equipo(campos[0]);
	        Equipo equipo2 = new Equipo(campos[3]);
	        
	        Partido partido = new Partido(equipo1,1,2,equipo2);
	   
	        partido.setGolesEquipo1(Integer.parseInt(campos[1]));
	        partido.setGolesEquipo2(Integer.parseInt(campos[2]));
	        
	        System.out.println(equipo1.getNombre() + "  " + partido.getGolesEquipo1());
	        System.out.println(" VS ");
	        System.out.println(equipo2.getNombre() + "  " + partido.getGolesEquipo2());
	        System.out.println("----------------------------");
	        
	        partidosMundial.add(partido);
	        
	       
	      }

	    }


	    // Leer pronostico
	    int puntos = 0; // total puntos persona

	    Path pathPronostico = Paths.get("C:\\tp-java\\gabriel\\res\\pronostico.csv");
	    List<String> lineasPronostico = null;

	    try {

	      lineasPronostico = Files.readAllLines(pathPronostico);

	    } catch (IOException e) {

	      System.out.println("No se pudo leer la linea de pronosticos...");
	      System.out.println(e.getMessage());
	      System.exit(1);
	    }

	    primera = true;
	    String participante="";

	   
	    System.out.println("\n"+"===================== PRONOSTICOS DEPORTIVOS ======================="+"\n");
        
		int cont=0,div=4,sum=1;

    

	    for (String linea_Pron: lineasPronostico) {

	      if (primera) {
	        primera = false;
	      } else {

			cont++;
	        String[] campos = linea_Pron.split(";");
			
	        Equipo equipo1 = new Equipo(campos[1]);
	        Equipo equipo2 = new Equipo(campos[5]);

	        
	        participante= campos[0];
	        
	        Partido partido= new Partido(equipo1,1,2,equipo2);

	        for (Partido partidoCol : partidosMundial) {
	          if (partidoCol.getEquipo1().getNombre().equals(equipo1.getNombre())
	              && partidoCol.getEquipo2().getNombre().equals(equipo2.getNombre())) {

	            partido = partidoCol;

	          }
	        }

	        Equipo equipo = null;
	        ResultadoEnum resultado = null;
	        

	        if ("X".equals(campos[2])) {
	          equipo = equipo1;
	          resultado = ResultadoEnum.GANADOR;
			  System.out.println("<*>"+campos[1] + " vs " + campos[5] + "---->" + resultado +
			  ": " + equipo.getNombre());

	        }
	        if ("X".equals(campos[3])) {
	          equipo = equipo1;
	          resultado = ResultadoEnum.EMPATE;
			  System.out.println("<*>"+campos[1] + " vs " + campos[5] + "---->" + resultado );

	        }
	        if ("X".equals(campos[4])) {
	          equipo = equipo1;
	          resultado = ResultadoEnum.PERDEDOR;
	        }

			if ("X".equals(campos[4])) {
				equipo = equipo2;
				resultado = ResultadoEnum.GANADOR;
				System.out.println("<*>"+campos[1] + " vs " + campos[5] + "---->" + resultado +
				": " + equipo.getNombre());
  
			}
			


	        Pronostico pronostico = new Pronostico(equipo, partido, resultado);
	        
	        // sumar los puntos correspondientes
	        puntos += pronostico.puntos();

		    
	      }

		  if((cont/div)==sum){
			//System.out.println("vueltas del for: " + cont);

			// mostrar participante y los puntos obtenidos
			System.out.println("\n"+"Partcipante: " + participante);
			System.out.println("Sus puntos fueron: " + puntos + "\n" +
			 "--------------------------------------------------------" + "\n");
			div=div+4;
			puntos=0;
		  }

	    }
	
	   

	  }


}
