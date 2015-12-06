package Utils;

import java.io.IOException;
import org.json.*;

public class WeatherFetcher {

	
	private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?callback=test&units=metric&appid=";
	private static final String API_KEY = "a2be4f9b02ac3bf7b8bfad61cdbf81bc";
	
	public static WeatherNode fetchWeatherNode(String latitude, String longitude)
			throws IOException {
		String 	forecast,
				locationName;
		
		double 	temperature,
				humidity,
				pressure,
				tempMin,
				tempMax;
		
		String url = API_URL + API_KEY + "&lat="+latitude+"&lon="+longitude;
		JSONObject response, weather, main;
		
		WeatherNode weatherNode = null;
		
		try {
			response	= JSONUtils.readJsonFromUrl(url);
			weather		= response.getJSONArray("weather").getJSONObject(0);
			main		= response.getJSONObject("main");
			forecast	= weather.getString("main");
			locationName= response.getString("name");
			temperature = main.getDouble("temp");
			humidity	= main.getDouble("humidity");
			pressure	= main.getDouble("pressure");
			tempMin		= main.getDouble("temp_min");
			tempMax		= main.getDouble("temp_max");
			weatherNode = 
					new WeatherNode(
							forecast, locationName,
							temperature, humidity,
							pressure, tempMin, tempMax
					);
		} catch (JSONException e) {
			System.out.println("Weather Fetcher Error: " + e.getMessage());
			return null;
		}

		return weatherNode;
	}

}
