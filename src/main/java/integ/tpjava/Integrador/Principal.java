package integ.tpjava.Integrador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.sql.*;



public class Principal {

	public static void main(String[] args){

	    Collection<Partido> partidosMundial = new ArrayList<Partido>();
		String [][]Pronst_Resul= new String [10][100];
		int a=0,b=1;

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
	    
	    a=0;b=3;primera = true;
		int div=4,sum=1,puntos=0;// total puntos persona
		String participante="";
	    
	    System.out.println("\n"+"===================== PRONOSTICOS DEPORTIVOS ======================="+"\n");
	    	
			try {
				Connection miConexion;
				
				miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mundial","root","");
				Statement miStatement = miConexion.createStatement(); 
				ResultSet miResultset = miStatement.executeQuery("SELECT * FROM pronostico");
				
				 while(miResultset.next()) {
					 
					 Equipo equipo1 = new Equipo(miResultset.getString("equipo1"));
					 Equipo equipo2 = new Equipo(miResultset.getString("equipo2"));
					 
					 Pronst_Resul[b][a]= miResultset.getString("participante");
					 participante =Pronst_Resul[b][a];
					 
					 Pronst_Resul[b+1][a]= equipo1.getNombre();
					 Pronst_Resul[b+2][a]= equipo2.getNombre();
					 
					 Pronst_Resul[b+3][a]= miResultset.getString("ganador_eq1");
					 Pronst_Resul[b+4][a]= miResultset.getString("ganador_eq2");
					 Pronst_Resul[b+5][a]= miResultset.getString("empate");
					 
					 Partido partido = new Partido(equipo1,1,2,equipo2);
					 
				        for (Partido partidoCol : partidosMundial) {
				          if (partidoCol.getEquipo1().getNombre().equals(equipo1.getNombre())
				              && partidoCol.getEquipo2().getNombre().equals(equipo2.getNombre())) {

				            partido = partidoCol;

				          }
				        }
					 
				      Equipo equipo = null;
				      ResultadoEnum resultado = null;
				      
				      if ("X".equals(Pronst_Resul[b+3][a])) {
				          equipo = equipo1;
				          resultado = ResultadoEnum.GANADOR;
						  System.out.println("<*>"+Pronst_Resul[b+1][a] + " vs " + Pronst_Resul[b+2][a] + "---->" + resultado +
						  ": " + equipo.getNombre());

				        }
				        if ("X".equals( Pronst_Resul[b+5][a])) {
				          equipo = equipo1;
				          resultado = ResultadoEnum.EMPATE;
						  System.out.println("<*>"+Pronst_Resul[b+1][a] + " vs " + Pronst_Resul[b+2][a] + "---->" + resultado );

				        }
				        if ("X".equals(Pronst_Resul[b+4][a])) {
				          equipo = equipo1;
				          resultado = ResultadoEnum.PERDEDOR;
				        }

						if ("X".equals(Pronst_Resul[b+4][a])) {
							equipo = equipo2;
							resultado = ResultadoEnum.GANADOR;
							System.out.println("<*>"+Pronst_Resul[b+1][a] + " vs " + Pronst_Resul[b+2][a] + "---->" + resultado +
							": " + equipo.getNombre());
			  
						}
						
						 Pronostico pronostico = new Pronostico(equipo, partido, resultado);
					        
					    // sumar los puntos correspondientes
					     puntos += pronostico.puntos();
						
					     a++;
					
					if((a/div)==sum){
					
						//mostrar participante y los puntos obtenidos
						
						System.out.println("\n"+"Partcipante: " + participante);
						System.out.println("Sus puntos fueron: " + puntos + "\n" +
						 "--------------------------------------------------------" + "\n");
						div=div+4;
						puntos=0;
					  }
				  
				 }
				
			} catch (SQLException e) {
				
				//e.printStackTrace();
				System.out.println("No se pudo leer la linea de pronosticos...");
			    System.out.println(e.getMessage());
			    System.exit(1);
			}
			
        
	  }


}
