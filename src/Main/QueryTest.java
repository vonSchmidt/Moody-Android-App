package Main;
import java.io.IOException;

import Providers.*;
import Utils.WallhavenScrapper;
import Utils.Wallpaper;

public class QueryTest {

	public static void main(String[] args) {
		QueryGenerator qg = new QueryGenerator();
		qg.setAllActive();
		((WeatherProvider)qg.getProvider(Provider.WEATHER_PROVIDER)).setLocation("33.9254477", "35.6576024");
		qg.setInactive(Provider.SEASON_PROVIDER);
		String[] x = qg.generateQuery();

		for(String a : x)
			System.out.print(a + " ");
		System.out.println();
		
		WallhavenScrapper ws = new WallhavenScrapper();
		try {
			System.out.println("Starting Search");
			Wallpaper[] wps = ws.search(x);
			for(Wallpaper wp : wps) {
				System.out.println("Downloading " + wp.getResourceName());
				ws.download(wp);
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
