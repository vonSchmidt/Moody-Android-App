package Main;
import java.util.ArrayList;
import Providers.*;
import static Providers.Provider.*;

public class QueryGenerator {

	private ArrayList<Provider> providers = new ArrayList<Provider>();
	
	
	
	public QueryGenerator() {
		providers.add(TIME_PROVIDER, new TimeProvider());
		providers.add(SEASON_PROVIDER, new SeasonProvider());
		providers.add(WEATHER_PROVIDER, new WeatherProvider());
	}
	
	public void setActive(int provider) {
		providers.get(provider).setActive();
	}
	
	public void setInactive(int provider) {
		providers.get(provider).setInactive();
	}
	
	public void setAllActive() {
		for(Provider p : providers)
			p.setActive();
	}
	
	public void setAllInactive() {
		for(Provider p : providers)
			p.setInactive();
	}
	
	public Provider getProvider(int provider) {
		return providers.get(provider);
	}
	
	public String[] generateQuery() {
		ArrayList <String> sb = new ArrayList<String>();
		String[] terms;
		for (Provider p : providers) {
			terms = p.getTerms();
			
			if(terms == null) continue;
			for (String term : terms) {
				sb.add(term);
			}
		}
		return sb.toArray(new String[sb.size()]);
	}
	
}
