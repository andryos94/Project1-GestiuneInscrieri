package com.guests;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Bun venit! Introduceti numarul de locuri disponibile:");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		GuestsList gList = new GuestsList(n);
		GuestsListRO gListRO = new GuestsListRO(gList);
		
		String command = "";
		
		String fileName = "C:\\Users\\Andrei\\eclipse-workspace\\Project1_GestiuneInscrieri\\test_gestiuneInscrieri.txt";
		Scanner scanner = new Scanner(new File(fileName));
		scanner.nextLine();
		
		while (scanner.hasNextLine() && !(command.equals("quit"))) {
			String line = scanner.nextLine();
			Scanner scan = new Scanner(line);
			Scanner s = scan.useDelimiter(" ");
			System.out.println();
			System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
			command = s.next();
			
			switch (command) {
			case "help":
				System.out.println(
						"help - Afiseaza aceasta lista de comenzi\r\n" + "add - Adauga o noua persoana (inscriere)\r\n"
								+ "check - Verifica daca o persoana este inscrisa la eveniment\r\n"
								+ "remove - Sterge o persoana existenta din lista\r\n"
								+ "update - Actualizeaza detaliile unei persoane\r\n"
								+ "guests - Lista de persoane care participa la eveniment\r\n"
								+ "waitlist - Persoanele din lista de asteptare\r\n"
								+ "available - Numarul de locuri libere\r\n"
								+ "guests_no - Numarul de persoane care participa la eveniment\r\n"
								+ "waitlist_no - Numarul de persoane din lista de asteptare\r\n"
								+ "subscribe_no - Numarul total de persoane inscrise\r\n"
								+ "search - Cauta toti invitatii conform sirului de caractere introdus\r\n"
								+ "quit - Inchide aplicatia");
				break;
			case "add":
				gListRO.add(s);
				break;
			case "check":
				gListRO.check(s);
				break;
			case "remove":
				gListRO.remove(s);
				break;
			case "update":
				gListRO.update(s);
				break;
			case "guests":
				gListRO.guests();
				break;
			case "waitlist":
				gListRO.waitlist();
				break;
			case "available":
				gListRO.available();
				break;
			case "guests_no":
				gListRO.guests_no();
				break;
			case "waitlist_no":
				gListRO.waitlist_no();
				break;
			case "subscribe_no":
				gListRO.subscribe_no();
				break;
			case "search":
				gListRO.searchCaseInsensitive(s);
				break;
			default:
				command = "quit";
				System.out.println("Aplicatia se inchide...");
				continue;
			}

			s.close();
			scan.close();
		}
		sc.close();
		scanner.close();
	}

}
