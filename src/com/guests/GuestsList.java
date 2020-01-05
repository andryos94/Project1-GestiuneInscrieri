package com.guests;

import java.util.*;

public class GuestsList {
	private final int seatsAvailable;
	private ArrayList<Guest> guests;
	private ArrayList<Guest> waitList;

	public ArrayList<Guest> getGuests() {
		return guests;
	}

	public ArrayList<Guest> getWaitList() {
		return waitList;
	}

	public GuestsList(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
		this.guests = new ArrayList<Guest>();
		this.waitList = new ArrayList<Guest>();
	}

	public int getSeatsAvailable() {
		return this.seatsAvailable;
	}

	public int add(Guest g) {
		if (this.guests.size() == this.getSeatsAvailable()) {
			this.waitList.add(g);
			return this.waitList.size();
		}

		this.guests.add(g);
		return 0;
	}

	public Guest checkByFirstAndLastName(Scanner sc) {
		ArrayList<Guest> allGuests = new ArrayList<Guest>();
		allGuests.addAll(this.guests);
		allGuests.addAll(this.waitList);

		if (allGuests.size() == 0) {
			return null;
		}
		
		String lastName = sc.next();
		String firstName = sc.next();
		for (int i = 0; i < allGuests.size(); i++) {
			if (allGuests.get(i).getFirstName().equals(firstName) && allGuests.get(i).getLastName().equals(lastName)) {
				return allGuests.get(i);
			}
		}
		return null;
	}

	public Guest checkByEmail(Scanner sc) {
		ArrayList<Guest> allGuests = new ArrayList<Guest>();
		allGuests.addAll(this.guests);
		allGuests.addAll(this.waitList);

		if (allGuests.size() == 0) {
			return null;
		}
		String email = sc.next();
		for (int i = 0; i < allGuests.size(); i++) {
			if (allGuests.get(i).getEmail().equals(email)) {
				return allGuests.get(i);
			}
		}
		return null;
	}

	public Guest checkByPhoneNumber(Scanner sc) {
		ArrayList<Guest> allGuests = new ArrayList<Guest>();
		allGuests.addAll(this.guests);
		allGuests.addAll(this.waitList);

		if (allGuests.size() == 0) {
			return null;
		}

		String phoneNumber = sc.next();
		for (int i = 0; i < allGuests.size(); i++) {
			if (allGuests.get(i).getPhoneNumber().equals(phoneNumber)) {
				return allGuests.get(i);
			}
		}
		return null;
	}

	/// daca am 2 participanti cu acelasi numar de telefon, cauta dupa urmatoarele
	/// criterii...si apoi sterge
	public void remove(Guest g) {
		if (this.guests.indexOf(g) >= 0) {
			this.guests.remove(g);
			if (this.waitList.size() != 0) {
				this.guests.add(this.waitList.get(0));
				this.waitList.remove(0);
			}
		} else if (this.waitList.indexOf(g) >= 0) {
			this.waitList.remove(g);
		}
	}

	public void guests() {
		for (int i = 0; i < getGuests().size(); i++) {
			System.out.println(i + 1 + ". " + getGuests().get(i));
		}
	}

	public void waitlist() {
		for (int i = 0; i < getWaitList().size(); i++) {
			System.out.println(i + 1 + ". " + getWaitList().get(i));
		}
	}

	public int available() {
		return (getSeatsAvailable() - this.guests.size());
	}

	public int guests_no() {
		return this.guests.size();
	}

	public int waitlist_no() {
		return this.waitList.size();
	}

	public int subscribe_no() {
		return guests_no() + waitlist_no();
	}

	public String searchCaseInsensitive(String str, Guest g) {

		String firstName = g.getFirstName().toLowerCase();
		String lastName = g.getLastName().toLowerCase();
		String email = g.getEmail().toLowerCase();
		String phoneNumber = g.getPhoneNumber().toLowerCase();
		String str1 = str.toLowerCase();

		if (firstName.contains(str1)) {
			return "firstName = " +g.getFirstName();
		} else if (lastName.contains(str1)) {
			return "lastName = " + g.getLastName();
		} else if (email.contains(str1)) {
			return "email = " + g.getEmail();
		} else if (phoneNumber.contains(str1)) {
			return "phoneNumber = " + g.getPhoneNumber();
		}
		return "";
	}
}
