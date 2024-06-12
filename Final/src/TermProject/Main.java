package TermProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) {
		List<String> dataList1 = new ArrayList<>();
		
		try{
			File file = new File("src/TermProject/userinfo.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String each = scanner.nextLine();
                dataList1.add(each);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
		
		User u;
		List<User> userList = new ArrayList<>();
		for(String info : dataList1) {
			String[] infoarray = info.split(" ");
			u = new User(infoarray[0], Integer.parseInt(infoarray[1]), infoarray[2]);
			userList.add(u);
		}
		
		Scanner keyboard = new Scanner(System.in);
		boolean flag = true;
		int clientnum = 0;
		System.out.println("===== Air Ticket reservation program - Login =====");
		
		while(flag) {
			System.out.print("Registration status(Y,N) > ");
			String rs = keyboard.next();
			keyboard.nextLine();
			
			if(rs.equals("Y")) {
				int check = -1;
				System.out.print("==Login==\n" + "ID > ");
				String rid = keyboard.nextLine();
				System.out.print("PW > ");
				int rpw = keyboard.nextInt();
				keyboard.nextLine();
				for(int i=0; i<userList.size(); i++) {
					if(userList.get(i).getEmail().equals(rid)) {
						check = i;
						clientnum = check;
						if(userList.get(check).getDate()==rpw) {
							//TicketManager client = new TicketManager(userList.get(check));
							System.out.println("Login completed");
							flag = false;
						}
						else {
							System.out.println("Wrong password. Try again.");
							continue;
						}
					}
				}
				if(check==-1) {
					System.out.println("No ID found. Try again.");
					continue;
				}
			}
			else if(rs.equals("N")) {
				System.out.print("==Join==\n" + "Name > ");
				String newname = keyboard.nextLine();
				System.out.print("Birthdate(ex.021024) > ");
				int newdate = keyboard.nextInt();
				keyboard.nextLine();
				System.out.print("Email address > ");
				String newemail = keyboard.nextLine();
				
				User newbee = new User(newname, newdate, newemail);
				userList.add(newbee);
				
				System.out.println("Registration completed");
				continue;
			}
			else {
				System.out.println("Try again");
				continue;
			}
		}
		
		
		List<String> dataList2 = new ArrayList<>();
		
		try{
			File file = new File("src/TermProject/tickets.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String each = scanner.nextLine();
                dataList2.add(each);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
		
		Ticket t;
		TicketManager tman= new TicketManager(userList.get(clientnum));
		for(String info : dataList2) {
			String[] tinfo = info.split(" ");
			t = new Ticket(tinfo[0], tinfo[1], tinfo[2], tinfo[3], tinfo[4], tinfo[5], Integer.parseInt(tinfo[6]), tinfo[7]);
			tman.setTicketList(t);
		}
		
		System.out.println("\n===== Air Ticket reservation program =====");
		flag = true;
		while(flag) {
			System.out.printf("\n[1]List [2]Search [3]Reserve [4]Modify [5]Cancel [6]Save [0]Exit > ");
			int choice = keyboard.nextInt();
			keyboard.nextLine();
			
			switch(choice) {
				case 1 : tman.print();
						 break;
				case 2 : tman.search();
						 break;
				case 3 : tman.reservation();
						 break;
				case 4 : tman.modify();
						 break;
				case 5 : tman.cancel();
						 break;
				case 6 : try (PrintWriter writer = new PrintWriter(new FileWriter("src/TermProject/userinfo.txt"))) {
		            		for (User newbe : userList) {
		            			writer.println(newbe.getName() + " " + String.format("%06d", newbe.getDate()) + " " + newbe.getEmail());
		            		}
		            		System.out.println("Changes have been saved to userinfo.txt");
		        		} catch (IOException e) {
		        			System.out.println("An error occurred while saving info to userinfo.txt");
		        			e.printStackTrace();
		        		} 
						 tman.save();
						 break;
				case 0 : System.out.println("Quit program");
						 flag = false;
						 break;
			}
		}
	}
}