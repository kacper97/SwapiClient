package SWAPI.es.uma.rysd.app;

import javax.net.ssl.HttpsURLConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.Gson;

import SWAPI.es.uma.rysd.entities.SearchResponse;
import SWAPI.es.uma.rysd.entities.Planet;
import SWAPI.es.uma.rysd.entities.People;
import SWAPI.es.uma.rysd.entities.Film;

public class SWClient {

    private final String app_name = "Willy, the weasel";
    
    private final String url_api = "https://swapi.co/api/";
    private final String people = "people/";
    private final String search = "?search=";

    public People search(String name){
    	People p = null;

        try {
           name  = URLEncoder.encode(name, "utf-8");

            String url = url_api + people + search + name;

            Gson parser = new Gson();
            InputStream in = getSWAPI(url);

            SearchResponse lp = parser.fromJson(new InputStreamReader(in), SearchResponse.class);

            //If no match is found
            if (lp.results.length == 0) return null;
            p = lp.results[0];

            p.movies = getMovies(p.films);

            p.homeplanet = getPlanet(p.homeworld);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	return p;
    }

    private Film[] getMovies(String[] films) throws IOException {
        Film[] movies = new Film[films.length];
        Gson parser = new Gson();

        for (int i = 0; i<films.length ; i++) {
            InputStream in = getSWAPI(films[i]);
            movies[i] = parser.fromJson(new InputStreamReader(in), Film.class);
        }

        return movies;
    }

    private Planet getPlanet(String world) throws IOException {

        Gson parser = new Gson();

        InputStream in = getSWAPI(world);
        Planet planet = parser.fromJson(new InputStreamReader(in), Planet.class);
        return planet;
    }

    private InputStream getSWAPI(String url) throws IOException{

        URL service = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) service.openConnection();
        connection.setRequestProperty("User-Agent", app_name);
        connection.setRequestProperty("Accept","application/json");
        connection.setRequestMethod("GET");

        //Check errors
        if (connection.getResponseCode() >= 400) {
            System.err.println("Error on connection " + connection.getResponseCode());
            return null;
        }
        return connection.getInputStream();
    }


}
            
          