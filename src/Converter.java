import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

/**
 * @author Timo 'eXodiquas' Netzer
 *
 */
public class Converter {
	String pathToGeoJSON;
	String outputPath;
	ArrayList<Shopentry> shoplist;
	
	/**
	 * @param inp Lokaler Pfad des GEOJSon-Files von der openstreetmap-overpass-turbo-API.
	 * @param outp Ort an dem das transformierte JSON File abgespeichert werden soll.
	 */
	public Converter(String inp, String outp) {
		this.pathToGeoJSON = inp;
		this.outputPath = outp;
		this.shoplist = new ArrayList<Shopentry>();
	}
	
	
	/**
	 * Führt alle Schritte die für die Transoformation nötig sind aus.
	 * @throws IOException Ist etwas lazy gehandelt, wirft eine Exception sobald irgendwas schief geht. :D
	 */
	public void run() throws IOException {
		loadFile();
		convertToJson();
	}
	
	private void convertToJson() throws FileNotFoundException {
		Gson gson = new Gson();
		PrintWriter out = new PrintWriter(outputPath);
		out.println(gson.toJson(this.shoplist));
		out.close();
	}

	private void loadFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(pathToGeoJSON));
		String currentLine;
		StringBuilder sb = new StringBuilder(); 
		while((currentLine = reader.readLine()) != null) {
			sb.append(currentLine);
		}
		
		Gson gson = new Gson();
		
		Map<String, ArrayList> map = gson.fromJson(sb.toString(), Map.class);
		
	    for (Object entry : map.get("features")) {
	    	try {
	    	LinkedTreeMap ltm = (LinkedTreeMap) entry;
	    	Shopentry se = new Shopentry(((LinkedTreeMap)ltm.get("properties")).get("name").toString(), 
	    			((double)((ArrayList<?>)((ArrayList<?>)((ArrayList<?>)((LinkedTreeMap)ltm.get("geometry")).get("coordinates")).get(0)).get(0)).get(0)) , 
	    			((double)((ArrayList<?>)((ArrayList<?>)((ArrayList<?>)((LinkedTreeMap)ltm.get("geometry")).get("coordinates")).get(0)).get(0)).get(1)));
	    	
	    	shoplist.add(se);
	    	} catch(Exception e) {
	    		continue;
	    	}
	    }
	    
	    for(Shopentry s : shoplist) {
	    	System.out.println(s);
	    }
	}
}
