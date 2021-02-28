import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class RentedCars {
	private static LinkedList<String> cars = new LinkedList<>();
	private int rentedCars = 0;
	
	public void addCar(String car) {
		if(!cars.contains(car)) {
			cars.add(car);
			rentedCars++;
		}
	}
	
	public void removeCar(String car) {
		int index = cars.indexOf(car);
		if(index != -1) {
			cars.remove(index);
			rentedCars--;
		}
		
	}

	public int getRentedCars() {
		return rentedCars;
	}
	
	public static void writeToBinaryFile(LinkedList<String> data) throws IOException{
	    try(ObjectOutputStream binaryFileOut = new ObjectOutputStream(
	        new BufferedOutputStream(new FileOutputStream("cars.dat")))) {
	        binaryFileOut.writeObject(data);
	    }
	  }
	 
  public static LinkedList<String> readFromBinaryFile() throws IOException {
	  LinkedList<String> data = null;
 
    try(ObjectInputStream binaryFileIn = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream("cars.dat")))) {
      data = (LinkedList<String>) binaryFileIn.readObject();
    } catch (ClassNotFoundException e) {
      System.out.println("A class not found exception: " + e.getMessage());
    }
 
    return data;
  }

	@Override
	public String toString() {
		String s = " ";
		ListIterator<String> it = cars.listIterator();
		while (it.hasNext()) {
			s += it.next() + " ";
		}
		return s;
	}


}
