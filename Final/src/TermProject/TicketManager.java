package TermProject;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TicketManager {
	Scanner keyboard = new Scanner(System.in);
	private List<Ticket> ticketList = new ArrayList<>();
	private User customer = new User();
	
	TicketManager(User c){
		customer = c;
	}
	
	public void setTicketList(Ticket t) {
		ticketList.add(t);
	}
	
	public void setUser(User c) {
		customer = c;
	}
	
	public List<Ticket> getTicketList(){
		return ticketList;
	}
	public User getUser() {
		return customer;
	}
	
	public void print() {
		int num = 1;
		for(Ticket plane : ticketList) {
			System.out.println(num + ". " + plane.toString());
			num++;
		}
	}
	
	public void search() {
		System.out.print("Search condition : 1. Airline 2. Destination 3. Price 4. Dept time > ");
		int choice = keyboard.nextInt();
		keyboard.nextLine();
		int count = 0;
		if(choice==1) {
			System.out.print("Enter airline > ");
			String airline = keyboard.nextLine();
			System.out.println("Available tickets");
			for(Ticket plane : ticketList) {
				if(plane.getAirline().equals(airline) && plane.getStatus().equals("true")) {
					count++;
					System.out.println(count + ". " + plane.toString());
				}
			}
			if(count==0) System.out.println("No record.");
		}
		else if(choice==2) {
			System.out.print("Enter destination > ");
			String where = keyboard.nextLine();
			System.out.println("Available tickets");
			for(Ticket plane : ticketList) {
				if(plane.getDest().equals(where) && plane.getStatus().equals("true")) {
					count++;
					System.out.println(count + ". " + plane.toString());
				}
			}
			if(count==0) System.out.println("No record.");
		}
		else if(choice==3) {
			System.out.print("Enter a price range(Min, Max) > ");
			int min = keyboard.nextInt();
			int max = keyboard.nextInt();
			System.out.println("Available tickets");
			for(Ticket plane : ticketList) {
				if((plane.getPrice()>=min && plane.getPrice()<=max) && plane.getStatus().equals("true")) {
					count++;
					System.out.println(count + ". " + plane.toString());
				}
			}
			if(count==0) System.out.println("No record.");
		}
		else if(choice==4) {
			System.out.print("Enter estimated departure time > ");
			int time = keyboard.nextInt();
			System.out.println("Available tickets");
			for(Ticket plane : ticketList) {
				if(Integer.parseInt(plane.getDepTime())==time) {
					count++;
					System.out.println(count + ". " + plane.toString());
				}
			}
			if(count==0) System.out.println("No record.");
		}
		else {
			System.out.println("Wrong number.");
		}
	}
	
	public void reservation() {
		if(customer.getmyDetail()!=null) {
			System.out.print("You already have ticket.");
		}
		else {
			System.out.print("Enter a number of ticket to reserve > ");
			int resnum = keyboard.nextInt();
			keyboard.nextLine();
			if(resnum<0 || resnum>ticketList.size()) {
				System.out.println("Wrong number.");
			}
			else if(ticketList.get(resnum-1).getStatus().equals("false")) {
				System.out.println("This ticket is already reserved.");
			}
			else {
				Random r = new Random();
				int[] newnum = new int[6];
				for(int i=0; i<6; i++) {
					newnum[i] = r.nextInt(10)+0;
					for(int j=0; j<i; j++) {
						if(newnum[i]==newnum[j]) i--;
					}
				}
				ticketList.get(resnum-1).setReserveNum(newnum);
				ticketList.get(resnum-1).setStatus("false");
				customer.setTicket(ticketList.get(resnum-1));
				customer.setmyDetail(ticketList.get(resnum-1));
				System.out.println("Reserved ticket: " + ticketList.get(resnum-1).toString());
			}
		}
	}
	
	public void modify() {
		System.out.print("Select part want to change\n1.name 2.birthdate 3.email > ");
		int choice = keyboard.nextInt();
		keyboard.nextLine();
		switch(choice) {
			case 1 : System.out.print("New name > ");
					 String nname = keyboard.nextLine();
					 customer.setName(nname);
					 break;
			case 2 : System.out.print("New birthdate > ");
			 		 int ndate = keyboard.nextInt();
			 		 keyboard.nextLine();
			 		 customer.setDate(ndate);
			 		 break;
			case 3 : System.out.print("New email > ");
			 		 String nemail = keyboard.nextLine();
			 		 customer.setEmail(nemail);
			 		 break;
			default : System.out.println("Wrong number");
					  break;
		}
		System.out.println("Modified. New info > " + customer.getName() + " " + String.format("%06d", customer.getDate()) + " " + customer.getEmail());
	}
	
	public void cancel() {
		if(customer.getTicket()==null) {
			System.out.println("No tickets to cancel");
		}
		else {
			Ticket tocancel = customer.getTicket();
			System.out.println("Ticket to be canceled : " + tocancel.toString());
			tocancel.setStatus("true");
			tocancel.setReserveNum(null);
			customer.setTicket(null);
            customer.setmyDetail(null);
            System.out.println("Your reservation has been canceled.");
		}
	}
	
	public void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/TermProject/tickets.txt"))) {
            for (Ticket t : ticketList) {
            	writer.println(t.getAirline() + " " + t.getDest() + " " + t.getDepTime() + " " +
            				   t.getArrTime() + " " + t.getSeatn() + " " + t.getGrade() + " " + t.getPrice() + " " + t.getStatus());
            }
            System.out.println("Tickets have been saved to tickets.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tickets to tickets.txt");
            e.printStackTrace();
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/TermProject/myticket.txt"))) {
            if (customer.getTicket() != null) {
            	writer.println(customer.getName() + " " + String.format("%06d", customer.getDate()) + " " + customer.getEmail());
                writer.println(customer.getTicket().toString() + " Reserve Number: " + customer.printmyDetail());
            } else {
            	writer.println(customer.getName() + " " + String.format("%06d", customer.getDate()) + " " + customer.getEmail());
                writer.println("No ticket reserved.");
            }
            System.out.println(customer.getName() + "'s ticket information has been saved to myticket.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while saving user's ticket information to myticket.txt");
            e.printStackTrace();
        }
    }
}