package SWAPI.es.uma.rysd.app;

import java.util.Scanner;

import SWAPI.es.uma.rysd.entities.People;

public class Main {	
	private final static String proxy = "proxy.lcc.uma.es";
	private final static String proxy_port = "3128"; 


    public static void main(String[] args) {
	
    	// Descomente las siguiente lineas si lo estas probando en el laboratorio y accede a Internet usando el proxy
    	// System.setProperty("https.proxyHost",proxy); 
    	// System.setProperty("https.proxyPort",proxy_port);
    	
        SWClient sw = new SWClient();
        Scanner sc = new Scanner(System.in);
        String respuesta = null;
        
        do{
	       	System.out.println("Character to search: ");
	       	String name = sc.nextLine();
	       	People p = sw.search(name);
	       	if (p != null) System.out.println(p);
	       	else System.out.println("Error 404: Character not found.");
	       	
	       	System.out.println("\nDo you want to search another character (y/n)?");
	       	respuesta = sc.nextLine();
        }while(respuesta.equals("y"));

        System.out.println("Closing program...");
        sc.close();
    }
}
