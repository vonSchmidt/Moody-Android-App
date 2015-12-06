package Utils;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.*;


public class JSONUtils {

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			if (jsonText.startsWith("test")) {
				// Test Callback In Motion
				jsonText = jsonText.substring(
						5, jsonText.length() - 2);
				
			}
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
	
}
