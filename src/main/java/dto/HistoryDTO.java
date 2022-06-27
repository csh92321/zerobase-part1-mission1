package dto;

public class HistoryDTO {
	private int id;
	private double lat;
	private double lnt;
	private String checkDttm;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLnt() {
		return lnt;
	}
	public void setLnt(double lnt) {
		this.lnt = lnt;
	}
	public String getCheckDttm() {
		return checkDttm;
	}
	public void setCheckDttm(String checkDttm) {
		this.checkDttm = checkDttm;
	}
	
	
}