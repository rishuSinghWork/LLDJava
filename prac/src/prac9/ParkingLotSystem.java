package prac9;

public class ParkingLotSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParkingService service = new ParkingService();

        // Add parking spots
        service.addParkingSpot(1, "Car");
        service.addParkingSpot(2, "Car");
        service.addParkingSpot(3, "Bike");
        service.addParkingSpot(4, "Truck");

        // Park vehicles
        service.parkVehicle("ABC123", "Car");
        service.parkVehicle("XYZ789", "Car");
        service.parkVehicle("MNO456", "Bike");
        service.parkVehicle("TRK111", "Truck");

        // View available spots
        System.out.println("\nAvailable spots:");
        service.viewAvailableSpots("Car");
        service.viewAvailableSpots("Bike");

        // Unpark a vehicle
        service.unparkVehicle("ABC123");

        // View available spots after unparking
        System.out.println("\nAvailable spots after unparking:");
        service.viewAvailableSpots("Car");
	}

}
