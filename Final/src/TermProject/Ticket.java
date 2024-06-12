package TermProject;

public class Ticket {
	private String airline;
	private int[] reservenum;
	private String destination;
	private String deptime;
	private String arrtime;
	private String seatnum;
	private String seatgrade;
	private int price;
	private String status;
	
	public Ticket(String al, String d, String dep, String arr, String sn, String sg, int p, String s){
		airline = al;
		destination = d;
		deptime = dep;
		arrtime = arr;
		seatnum = sn;
		seatgrade = sg;
		price = p;
		status = s;
	}
	
	public void setAirline(String al) {
		airline = al;
	}
	public void setReserveNum(int[] sn) {
		reservenum = sn;
	}
	public void setDest(String d) {
		destination = d;
	}
	public void setDepTime(String dep) {
		arrtime = dep;
	}
	public void setArrTime(String arr) {
		arrtime = arr;
	}
	public void setSeatn(String sn) {
		seatnum = sn;
	}
	public void setGrade(String sg) {
		seatgrade = sg;
	}
	public void setPrice(int p) {
		price = p;
	}
	public void setStatus(String s) {
		status = s;
	}
	
	public String getAirline() {
		return airline;
	}
	public int[] getReserveNum() {
		return reservenum;
	}
	public String getDest() {
		return destination;
	}
	public String getDepTime() {
		return deptime;
	}
	public String getArrTime() {
		return arrtime;
	}
	public String getSeatn() {
		return seatnum;
	}
	public String getGrade() {
		return seatgrade;
	}
	public int getPrice() {
		return price;
	}
	public String getStatus() {
		return status;
	}
	public int printReserveNum() {
		int total = 0, div = 100000;
		for(int i=0; i<6; i++) {
			total += this.reservenum[i]*div;
			div /= 10;
		}
		return total;
	}
	
	public String toString() {
		return  airline + " " + destination + " [Dept / Arr time]: " + getDepTime() + " / " + getArrTime() +
				" [Grade]: " + getGrade() + " [Price]: " + getPrice() + " [Status]: " + getStatus();
	}
}