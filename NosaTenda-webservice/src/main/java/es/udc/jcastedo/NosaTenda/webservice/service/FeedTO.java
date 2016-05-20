package es.udc.jcastedo.NosaTenda.webservice.service;

import java.util.ArrayList;
import java.util.List;

public class FeedTO {

	private String title = null;
	private String subtitle = null;
	private String link = null;
	private List<EntryTO> entries = new ArrayList<EntryTO>();
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<EntryTO> getEntries() {
		return entries;
	}
	public void setEntries(List<EntryTO> entries) {
		this.entries = entries;
	}
	
}
