package com.hotelbooking.server;

import com.hotelbooking.api.RoomController;
import com.hotelbooking.models.Customer;
import com.hotelbooking.service.HotelService;

import java.time.LocalDate;
import java.util.Scanner;

public class HotelServer {

    private final HotelService hotelService;
    private final RoomController roomController;

    // Constructor to allow dependency injection
    public HotelServer(HotelService hotelService) {
        this.hotelService = hotelService;
        this.roomController = new RoomController(hotelService.getRoomService());
    }

    public void start() {
        hotelService.initialize();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Hotel Booking system!");

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Manage Rooms");
            System.out.println("2. Add Customer");
            System.out.println("3. Book Room");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. View Reservations");
            System.out.println("6. Exit");
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> roomController.manageRooms();
                case 2 -> addCustomer(scanner);
                case 3 -> bookRoom(scanner);
                case 4 -> cancelReservation(scanner);
                case 5 -> viewReservations();
                case 6 -> {
                    System.out.println("Thank you for using Hotel Booking System!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try Again.");
            }
        }
    }

    protected void addCustomer(Scanner scanner) {
        System.out.println("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline character

        System.out.println("Enter Customer Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Customer Email: ");
        String email = scanner.nextLine();

        Customer customer = new Customer(customerId, name, email);
        hotelService.addCustomer(customer);
        System.out.println("Customer added successfully");
    }

    protected void bookRoom(Scanner scanner) {
        System.out.println("Enter Customer Id: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Room Type: ");
        String roomType = scanner.nextLine();
        System.out.println("Enter CheckIn Date (YYYY-MM-DD): ");
        String checkInDate = scanner.nextLine();
        System.out.println("Enter CheckOut Date (YYYY-MM-DD): ");
        String checkOutDate = scanner.nextLine();

        hotelService.bookRoom(customerId, roomType, LocalDate.parse(checkInDate), LocalDate.parse(checkOutDate));
    }

    protected void cancelReservation(Scanner scanner) {
        System.out.println("Enter Reservation ID: ");
        int reservationId = scanner.nextInt();
        System.out.println("Enter Cancellation Date (YYYY-MM-DD)");
        scanner.nextLine();
        String cancellationDate = scanner.nextLine();

        hotelService.cancelReservation(reservationId, LocalDate.parse(cancellationDate));
    }

    protected void viewReservations() {
        hotelService.getReservations().forEach(System.out::println);
    }

    public static void main(String[] args) {
        HotelService hotelService = new HotelService();
        HotelServer server = new HotelServer(hotelService);
        server.start();
    }
}
