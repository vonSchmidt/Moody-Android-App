package Utils;


public class WeatherNode {

	private String 	forecast,
					locationName;

	private double 	temperature,
					humidity,
					pressure,
					tempMin,
					tempMax;

	public WeatherNode(String forecast, String locationName,
			double temperature, double humidity, double pressure,
			double tempMin, double tempMax) {
		this.forecast = forecast;
		this.locationName = locationName;
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public double getTempMin() {
		return tempMin;
	}

	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}

	public double getTempMax() {
		return tempMax;
	}

	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}

	@Override
	public String toString() {
		return "WeatherNode [forecast=" + forecast + ", locationName="
				+ locationName + ", temperature=" + temperature + ", humidity="
				+ humidity + ", pressure=" + pressure + ", tempMin=" + tempMin
				+ ", tempMax=" + tempMax + "]";
	}
}
