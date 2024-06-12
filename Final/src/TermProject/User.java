package TermProject;

public class User {
	private String name;
	private int birthdate;
	private String email;
	private Ticket reserved;
	private int[] mydetail;
	
	public User() {
		
	}
	public User(String n, int b, String e) {
		name = n;
		birthdate = b;
		email = e;
	}
	public User(String n, int b, String e, Ticket t, int[] md) {
		name = n;
		birthdate = b;
		email = e;
		reserved = t;
		mydetail = md;
	}
	
	public void setName(String n) {
		name = n;
	}
	public void setDate(int b) {
		birthdate = b;
	}
	public void setEmail(String e) {
		email = e;
	}
	public void setTicket(Ticket t) {
		if(t == null) reserved = null;
		else reserved = t;
	}
	public void setmyDetail(Ticket t) {
		if(t == null) mydetail = null;
		else mydetail = t.getReserveNum();
	}
	
	public String getName() {
		return name;
	}
	public int getDate() {
		return birthdate;
	}
	public String getEmail() {
		return email;
	}
	public Ticket getTicket() {
		return reserved;
	}
	public int[] getmyDetail() {
		return mydetail;
	}
	public int printmyDetail() {
		int total = 0, div = 100000;
		for(int i=0; i<6; i++) {
			total += this.mydetail[i]*div;
			div /= 10;
		}
		return total;
	}
	
	public String toString() {
		return "Name: " + getName() + " Birthdate: " + getDate() + 
			   " Email: " + getEmail();
	}
}