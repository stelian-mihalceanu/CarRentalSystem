import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CarRentalSystem {
	private static Scanner sc = new Scanner(System.in);
	private HashMap<String, String> rentedCars = new HashMap<String, String>(100, 0.5f);
	private HashMap<String, ArrayList<String>> carsByOwner = new HashMap<String, ArrayList<String>>(100, 0.5f);
	private int numberOfRentedCars = 0;
	 
	  private static String getPlateNo() {
	    System.out.println("Introduceti numarul de inmatriculare:");
	    try {
	        System.out.println("Introdu un sir:");
	        return sc.nextLine();
	      } catch (InputMismatchException e) {
	        sc.nextLine();
	        System.out.println("Nu ai introdus un sir valid. Te rog sa reincerci.");
	      }
	    return sc.nextLine();
	  }
	 
	  private static String getOwnerName() {
	    System.out.println("Introduceti numele proprietarului:");
	    try {
	        System.out.println("Introdu un sir:");
	        return sc.nextLine();
	      } catch (InputMismatchException e) {
	        sc.nextLine();
	        System.out.println("Nu ai introdus un sir valid. Te rog sa reincerci.");
	      }
	    return sc.nextLine();
	  }
	 
	  // search for a key in hashtable
	  private boolean isCarRent(String plateNo) {
		  boolean rent = false;
		  if(rentedCars.containsKey(plateNo)) {
			  rent = true;
			  System.out.println("Masina este inchiriata");
		  }
	    return rentedCars.containsKey(plateNo);
	  }
	 
	  // get the value associated to a key
	  private String getCarRent(String plateNo) {
	    return rentedCars.get(plateNo);
	  }
	 
	  // add a new (key, value) pair
	  private void rentCar(String plateNo, String ownerName) {
	    if(!carsByOwner.containsKey(ownerName)) {
	    	rentedCars.put(plateNo, ownerName);
	    	numberOfRentedCars++;
	    	carsByOwner.put(ownerName, new ArrayList<String>());
	    }
	    try {
	    	carsByOwner.containsKey(ownerName);
		    } catch (NullPointerException e) {
		    	System.out.println("Null Pointer Exception");
		    }
	    	
	    carsByOwner.get(ownerName).add(plateNo);
	  }
	 
	  // remove an existing (key, value) pair
	  private void returnCar(String plateNo) {
		  String ownerName = rentedCars.get(plateNo);
		  if(rentedCars.remove(plateNo) == null) {
			  System.out.println("Aceasta masina nu exista");
		  } else {
			  carsByOwner.get(ownerName).remove(plateNo);
			  System.out.println("Aceasta masina a fost stearsa din registru");
			  
		  }
	  }
	  
	  private int totalRentedCars() {
		  return numberOfRentedCars;
	  }
	 
	  private static void printCommandsList() {
	    System.out.println("help         - Afiseaza aceasta lista de comenzi");
	    System.out.println("add          - Adauga o noua pereche (masina, sofer)");
	    System.out.println("check        - Verifica daca o masina este deja luata");
	    System.out.println("remove       - Sterge o masina existenta din hashtable");
	    System.out.println("getOwner     - Afiseaza proprietarul curent al masinii");
	    System.out.println("quit         - Inchide aplicatia");
	  }
	 
	  public void run() {
	    boolean quit = false;
	    while(!quit) {
	      System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
	      String command = sc.nextLine();
	      switch(command) {
	        case "help":
	          printCommandsList();
	          break;
	        case "add":
	          rentCar(getPlateNo(), getOwnerName());
	          break;
	        case "check":
	          System.out.println(isCarRent(getPlateNo()));
	          break;
	        case "remove":
	          returnCar(getPlateNo());
	          break;
	        case "getOwner":
	          System.out.println(getCarRent(getPlateNo()));
	          break;
	        case "quit":
	          System.out.println("Aplicatia se inchide...");
	          quit = true;
	          break;
	        default:
	          System.out.println("Unknown command. Choose from:");
	          printCommandsList();
	      }
	    }
	  }

	public static void main(String[] args) {
		new CarRentalSystem().run();
	}



}
