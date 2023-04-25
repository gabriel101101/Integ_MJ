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
		
		/* NOTA: utilize el archivo "resultados.csv" que se encuentra en la carpeta "archivos" del programa, 
		 * para que el programa funcione correctamente. */

		//CONFIG BD
		String BD="jdbc:mysql://localhost:3306/mundial", DIR="root",CONT="";
		
	    Collection<Partido> partidosMundial = new ArrayList<Partido>();
	    Path pathResultados = Paths.get(args[0]);
	    List<String> lineasResultados = null;
	    
		String [][]Pronst_Resul= new String [10][100];
		String participante="", M_participante="",ronda="";
		
		int a=0,b=1,num=4,M_puntos=0, puntos=0;
	
        try {
        	
	        lineasResultados = Files.readAllLines(pathResultados);
	              
	    } catch (IOException e) {
	      System.out.println("No se pudo leer la linea de resultados...");
	      System.out.println(e.getMessage());
	      System.exit(1);
	    }
	    
	    System.out.println("===================== RESULTADOS DEPORTIVOS ===================== \n");
	    
	    boolean primera = true;

	    for (String lineaResultado : lineasResultados) {
	    	
	      if (primera) {
	    	  String[] campos = lineaResultado.split(",");
		      ronda=campos[0];
	    	  System.out.println("-------------"+ronda+"---------------");
	          primera = false; 	  
	      }
	      else{

	        String[] campos = lineaResultado.split(",");
	        if(campos.length!=5) { 
		    	  System.out.println("ERROR DEL PROGRAMA : No coincide el numero de campos del archivo resultados. ");
		    	  primera =true;
	    		  break;
		      }
	        
	        Equipo equipo1 = new Equipo(campos[1]);
	        Equipo equipo2 = new Equipo(campos[4]);
	        Partido partido = new Partido(equipo1,1,2,equipo2);
	        
	        partido.setGolesEquipo1(Integer.parseInt(campos[2]));
	        partido.setGolesEquipo2(Integer.parseInt(campos[3]));
	        
	        System.out.println(equipo1.getNombre() + "  " + partido.getGolesEquipo1());
	        System.out.println(" VS ");
	        System.out.println(equipo2.getNombre() + "  " + partido.getGolesEquipo2());
	        System.out.println("----------------------------");
	        
	        partidosMundial.add(partido);
	        
	      }
	    }
	    a=0;b=3;
	    
	    System.out.println("\n"+"===================== PRONOSTICOS DEPORTIVOS ======================="+"\n");
	    /* NOTA: Utilize el archivo "pronostico.csv" que se encuentra en la carpeta archivos
		 * del programa para cargar la base de datos y asi el programa funcionara correctamente*/
	    
			try {
				if(primera==true) {
			    	BD=null;
			    }
				Connection miConexion;
				
				miConexion = DriverManager.getConnection(BD,DIR,CONT);
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
						  System.out.println("<*>"+Pronst_Resul[b+1][a] + " vs " +
				          Pronst_Resul[b+2][a] + "---->" + resultado + ": " + equipo.getNombre());
				        }
				        if ("X".equals( Pronst_Resul[b+5][a])) {
				          equipo = equipo1;
				          resultado = ResultadoEnum.EMPATE;
						  System.out.println("<*>"+Pronst_Resul[b+1][a] + " vs " +
				          Pronst_Resul[b+2][a] + "---->" + resultado );
				        }
				        if ("X".equals(Pronst_Resul[b+4][a])) {
				          equipo = equipo1;
				          resultado = ResultadoEnum.PERDEDOR;
				        }

						if ("X".equals(Pronst_Resul[b+4][a])) {
							equipo = equipo2;
							resultado = ResultadoEnum.GANADOR;
							System.out.println("<*>"+Pronst_Resul[b+1][a] + " vs " +
							Pronst_Resul[b+2][a] + "---->" + resultado + ": " + equipo.getNombre());
						}
						
						 Pronostico pronostico = new Pronostico(equipo, partido, resultado);  
					    // sumar los puntos correspondientes
					     puntos += pronostico.puntos();
						
					     a++;
					     
					   while((a/num)==1){   
						   //------------PUNTOS ESXTRA--------------------
						   if(puntos==4){
							   System.out.println("\n<+>"+participante+ " Hacerto todos los pronosticos y sumo 2 puntos extra.\n");
							   puntos=puntos +2;
						   }  
						//---MOSTRAR PARTICIPANTE Y PUNTOS OBTENIDOS------------	
						System.out.println("\n"+"Partcipante: " + participante);
						System.out.println("Puntos Obtenidos: " + puntos + "\n" +
						 "--------------------------------------------------------" + "\n");
						
						//-------------MEJOR PUNTAJE----------------------
						if(puntos > M_puntos) {
							M_puntos=puntos;
							M_participante=participante;	
						}
						num=num+4;
						puntos=0;
					  }   
				 }
				 
		    System.out.println("\n"+"PARTICIPANTE CON MAYOR PUNTAJE :" + M_participante +" con un total de "+M_puntos+" Puntos.");
				
			} catch (SQLException e) {
				System.out.println("No se pudo leer la linea de pronosticos...");
				System.out.println(e.getMessage());
			    System.exit(1);
			}
        
	  }

}
