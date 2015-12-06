package Providers;

public abstract class Provider {
	
	public static final int TIME_PROVIDER 		= 0,
							SEASON_PROVIDER 	= 1,
							WEATHER_PROVIDER 	= 2;

	protected boolean activated = false;
	public abstract String[] getTerms();
	
	public void setActive(){
		activated = true;
	}
	public void setInactive(){
		activated = false;
	}
	
}