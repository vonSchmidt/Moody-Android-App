package Providers;

import java.util.Calendar;


public class SeasonProvider extends Provider {

	@Override
	public String[] getTerms() {
		if (activated) {
			int localeCurrentMonth = Calendar.getInstance().get(Calendar.MONTH);
			return new String[] {getSeason(localeCurrentMonth)};
		}
		return null;
	}
	
	public static String getSeason(int month) {
	    switch(month) {
	          case 11:
	          case 12:
	          case 1:
	          case 2:
	                return "winter";
	          case 3:
	          case 4:
	                return "spring";
	          case 5:
	          case 6:
	          case 7:
	          case 8:
	                return "summer";
	          default:
	                return "autumn";
	      }
	}

}
