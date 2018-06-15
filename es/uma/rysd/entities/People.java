package SWAPI.es.uma.rysd.entities;

public class People {
	public String name;
	public String birth_year;
	public String eye_color;
	public String gender;
	public String hair_color;
	public String height;
	public String mass;
	public String skin_color;
	public String homeworld;
	public String[] films;
	
	public Film[] movies;
	public Planet homeplanet;
	
	public String toString(){
		String text = name + " ("+ gender +") was borned in " + birth_year+" on the planet of "+homeplanet+"\n";
		text += "Weight: " + mass + " Kg y height: " + height + " cm\n";
		text += "Appears in:\n";
		for(Film f: movies){
			text += "- "+f+"\n";
		}
		return text;		
	}
}
