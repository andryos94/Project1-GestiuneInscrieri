package com.guests;

import java.util.ArrayList;
import java.util.Scanner;

public class GuestsListRO {
	private GuestsList gList;

	public GuestsListRO(GuestsList gList) {
		this.gList = gList;
	}

	public void add(Scanner sc) {
		if (this.check(sc) != null) {
			System.out.println("Persoana pe care vrei sa o adaugi este deja inscrisa!");
			return;
		}
		System.out.println("Persoana pe care vrei sa o adaugi NU este inscrisa!");

		System.out.println("Se adauga o noua persoana...");
		System.out.println("Introduceti numele de familie:");
		String lastName = sc.next();
		System.out.println("Introduceti prenumele:");
		String firstName = sc.next();
		System.out.println("Introduceti email:");
		String email = sc.next();
		System.out.println("Introduceti numar de telefon (format „+40733386463“):");
		String phoneNumber = sc.next();

		Guest g = new Guest(lastName, firstName, phoneNumber, email);

		if (gList.add(g) > 0) {
			System.out.println("[" + g.getLastName() + " " + g.getFirstName()
					+ "] Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine "
					+ gList.getWaitList().size() + ". Te vom notifica daca un loc devine disponibil");
		} else {
			System.out.println("[" + g.getLastName() + " " + g.getFirstName()
					+ "] Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
		}
	}

	public Guest check(Scanner sc) {
		System.out.println("Alege modul de autentificare, tastand:\r\n" + "\"1\" - Nume si prenume\r\n"
				+ "\"2\" - Email\r\n" + "\"3\" - Numar de telefon (format \"+40733386463\")");
		int option = sc.nextInt();
		switch (option) {
		case 1:
			System.out.println("Introduceti numele de familie:");
			System.out.println("Introduceti prenumele:");
			return gList.checkByFirstAndLastName(sc);
		case 2:
			System.out.println("Introduceti email:");
			return gList.checkByEmail(sc);
		case 3:
			System.out.println("Introduceti numarul de telefon:");
			return gList.checkByPhoneNumber(sc);
		}
		return null;
	}

	public boolean remove(Scanner sc) {
		if (gList.getGuests().size() == 0) {
			System.out.println("Eroare: Lista permanenta este goala!");
			return false;
		}

		Guest g = this.check(sc);
		if (g == null) {
			System.out.println("Eroare: Persoana nu era inscrisa!");
			return false;
		}
		gList.remove(g);
		System.out.println("Persoana a fost stearsa cu succes!");
		return true;
	}

	public void update(Scanner sc) {
		System.out.println("Se actualizeaza detaliile unei persoane…");
		Guest g = this.check(sc);
		if (g == null) {
			System.out.println("Persoana cautata nu este inscrisa!");
			return;
		}

		System.out.println("Alege campul de actualizat, tastand:\r\n" + "\"1\" - Nume\r\n" + "\"2\" - Prenume\r\n"
				+ "\"3\" - Email\r\n" + "\"4\" - Numar de telefon (format \"+40733386463\")");
		int option = sc.nextInt();
		switch (option) {
		case 1:
			System.out.println("Introduceti numele de familie:");
			String lastName = sc.next();
			g.setLastName(lastName);
			break;
		case 2:
			System.out.println("Introduceti prenumele:");
			String firstName = sc.next();
			g.setFirstName(firstName);
			break;
		case 3:
			System.out.println("Introduceti email:");
			String email = sc.next();
			g.setEmail(email);
			break;
		case 4:
			System.out.println("Introduceti numar de telefon (format „+40733386463“):");
			String phoneNumber = sc.next();
			g.setPhoneNumber(phoneNumber);
			break;
		}
	}
	
	public void guests() {
		if(gList.getGuests().size() == 0) {
			System.out.println("Niciun participant inscris…");
			return;
		}
		System.out.println("Persoanele inscrise pe lista permanenta la eveniment sunt urmatoarele:");
		gList.guests();
	}
	
	public void waitlist() {
		if(gList.getWaitList().size() == 0) {
			System.out.println("Lista de asteptare este goala…");
			return;
		}
		System.out.println("Persoanele inscrise pe lista de asteptare la eveniment sunt urmatoarele:");
		gList.waitlist();
	}
	
	public void available() {
		System.out.println("Numarul de locuri disponibile este: " + gList.available());
	}
	
	public void guests_no() {
		System.out.println("Numarul de persoane inscrise pe lista permanenta este: " + gList.guests_no());
	}
	
	public void waitlist_no() {
		System.out.println("Numarul de persoane inscrise pe lista de asteptare este: " + gList.waitlist_no());
	}
	
	public void subscribe_no() {
		System.out.println("Numarul total de persoane inscrise la eveniment este: " + gList.subscribe_no());
	}
	
	public void searchCaseInsensitive(Scanner sc) {
		String str = sc.next();
		ArrayList<Guest> allGuests = new ArrayList<Guest>();
		allGuests.addAll(gList.getGuests());
		allGuests.addAll(gList.getWaitList());

		if (allGuests.size() == 0) {
			System.out.println("Caracterele nu indica catre niciun contact!(Motiv:Lista participanti goala)");
			return;
		}
		
		boolean flag = false;
		int j = 0;
		
		for(int i = 0; i < allGuests.size(); i++) {
			Guest g = allGuests.get(i);
			if(gList.searchCaseInsensitive(str, g) != "") {
				j++;
				System.out.println("Contact " + j + " contine: " + gList.searchCaseInsensitive(str, g));
				flag = true;
			}
		}
		
		if(flag == false) {
			System.out.println("Caracterele nu indica catre niciun contact!");
		}
	}
}
