package Providers;

import java.io.IOException;

import Utils.WeatherFetcher;
import Utils.WeatherNode;

public class WeatherProvider extends Provider {

	String latitude;
	String longitude;
	@Override
	public String[] getTerms() {
		if (activated) {
			if (latitude == null || longitude == null) {
				System.err.println("Weather Provider Warning: Coordinates are unset!");
				return null;
			}
			try {
				WeatherNode node = WeatherFetcher.fetchWeatherNode(latitude, longitude);
				if (node == null) throw new IOException();
				return new String[] {String.valueOf(node.getForecast())};
			} catch (IOException e) {
				System.err.println(e.getLocalizedMessage());
				return null;
			}
		}
		return null;
	}

	public void setLocation(String latitude, String longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	
		
}

