package cn.com.zsyk.crm.ecif.bo.graph;

import java.util.List;

public class GraphInfo {
	private List<SeriesData> data;
	private List<SeriesLinks> links;
	
	public List<SeriesData> getData() {
		return data;
	}
	
	public void setData(List<SeriesData> data) {
		this.data = data;
	}
	
	public List<SeriesLinks> getLinks() {
		return links;
	}
	
	public void setLinks(List<SeriesLinks> links) {
		this.links = links;
	}

}
