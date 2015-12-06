package Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WallhavenScrapper {

	public static final String HOST = "http://alpha.wallhaven.cc/";
	public static final String SEARCH = "search?q=";
	
	private File downloadsFolder;
	
	public WallhavenScrapper () {
		downloadsFolder = new File("." + File.separator + "Wallpapers" + File.separator);
		
	}
	
	private void checkDownloadDir() {
		if (!downloadsFolder.exists()) {
			System.out.println("Download directory does not exist. Creating it for the first time...");
			if (!downloadsFolder.mkdir()) {
				System.err.println("Error occured during creation of Downloads directory.");
			}
		}
	}

	public WallhavenScrapper (String dlDir) {
		downloadsFolder = new File(dlDir + File.separator);
	}
	
	public Wallpaper[] search (String args []) throws IOException {
		return search(args, 1);
	}
	
	public Wallpaper[] search (String args [], int page) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (String s : args) {
			sb.append(s);
			sb.append("%20");
		}
		sb.append("&page=" + page);
		System.out.println(sb.toString());
		Document doc = 
				Jsoup
				.connect(HOST + SEARCH + sb.toString())
				.userAgent("Mozilla")
				.timeout(10000)
				.get();
		
		Elements hits = doc.select("img");
		String thumbLink;
		Wallpaper temp;
		ArrayList<Wallpaper> wallpapers = new ArrayList<Wallpaper>();
		
		for (Element e : hits.toArray(new Element [10])) {
			if(e.hasClass("lazyload")) {
				thumbLink = e.attr("data-src");
				temp = new Wallpaper(thumbLink);
				wallpapers.add(temp);
			}
		}
		
		return wallpapers.toArray(new Wallpaper[10]);
	}
	
	public void download(Wallpaper wp) {
		try {
			
			Elements temp = 
					Jsoup
					.connect(wp.getPreviewLink())
					.userAgent("Mozilla")
					.timeout(10000)
					.get().select("img");
			for (Element e : temp) {
				if(!e.id().equals("wallpaper")) continue;
				wp.setWPLink("http:" + e.attr("src"));
				break;
			}
			
			checkDownloadDir();
			
			saveStream(
					wp.getWPLink(),
					new File(downloadsFolder.getAbsolutePath()
							+ File.separator
							+ wp.getResourceName()
					)
			);
		} catch (Exception e) {
			System.err.println("Wallhaven Scrapper Error: "+e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	public String getDownloadsDir() { return downloadsFolder.getName(); }
	
	private static void saveStream(String mURL, File ofile) throws Exception {
	    InputStream in = null;
	    FileOutputStream out = null;
	    try {
	        URL url = new URL(mURL);
	        URLConnection urlConn = url.openConnection();
	        urlConn.addRequestProperty("User-Agent", "Mozilla");
	        in = urlConn.getInputStream();
	        out = new FileOutputStream(ofile);
	        int c;
	        byte[] b = new byte[1024];
	        while ((c = in.read(b)) != -1)
	            out.write(b, 0, c);
	    } finally {
	        if (in != null)
	            in.close();
	        if (out != null)
	            out.close();
	    }
	}
}
