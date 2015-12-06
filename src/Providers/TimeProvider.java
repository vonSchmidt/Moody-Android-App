package Providers;

import java.util.Calendar;

public class TimeProvider extends Provider {

	@Override
	public String[] getTerms() {
		if (activated) {
			int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			if (hour >= 6 && hour <= 12)
				return new String[] {"morning"};
			else if (hour > 12 && hour <= 18)
				return new String[] {"afternoon"};
			else
				return new String[] {"evening"};
		}
		return null;
	}
	

}
