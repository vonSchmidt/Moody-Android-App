package Utils;

public class Wallpaper {

	private String thumbnailLink;
	private String wpLink;
	private String previewLink;
	private String resourceName;
	private String id;
	
	public final String resPrefix = "http://whvn.cc/";
	
	public Wallpaper(String thumbnailLink) {
		this.thumbnailLink = thumbnailLink;
		resourceName = thumbnailLink
				.substring(thumbnailLink.lastIndexOf("/th-") + 4,
						thumbnailLink.length()
				);
		this.setId(resourceName.substring(0, resourceName.indexOf('.')));
		setPreviewLink(resPrefix + id);
		resourceName = "wp-" + resourceName;
	}
	
	public void setThumbnailLink(String thumbnailLink) {
		this.thumbnailLink = thumbnailLink;
	}
	
	public void setWPLink(String wpLink) {
		this.wpLink = wpLink;
	}
	
	public String getThumbnailLink() {
		return this.thumbnailLink;
	}
	
	public String getWPLink() {
		return this.wpLink;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getPreviewLink() {
		return previewLink;
	}

	public void setPreviewLink(String previewLink) {
		this.previewLink = previewLink;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Wallpaper [thumbnailLink=" + thumbnailLink + ", wpLink="
				+ wpLink + ", previewLink=" + previewLink + ", resourceName="
				+ resourceName + ", id=" + id + ", resPrefix=" + resPrefix
				+ "]";
	}
	
}
