import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;


public class w2051984_PlaneManagement {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int[][] planeseat = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        Ticket[][] tickets;
        tickets = new Ticket[][]{
                new Ticket[14],new Ticket[12],new Ticket[12],new Ticket[14]
        };

        while (true) {

            System.out.println("Welcome to the Plane Management application");
            System.out.println(
                    "**************************************** \n" +
                            "*             MENU OPTIONS             * \n" +
                            "**************************************** \n" +
                            "\t 1) Buy a Seat\n" +
                            "\t 2) Cancel a Seat\n" +
                            "\t 3) Find first available\n" +
                            "\t 4) Show seating plan\n" +
                            "\t 5) Print tickets information and total sales\n" +
                            "\t 6) Search ticket\n" +
                            "\t 0) Quit\n" +
                            "**************************************** \n" +
                            "Please select an option: ");

            try {
                int option = input.nextInt();

                if (option == 0) {
                    break;
                }

                switch (option) {
                    case 1:
                        buy_seat(planeseat, input, tickets);
                        break;
                    case 2:
                        cancel_seat(planeseat, input, tickets);
                        break;
                    case 3:
                        find_first_available(planeseat);
                        break;
                    case 4:
                        show_seating_plan(planeseat);
                        break;
                    case 5:
                        print_tickets_info(tickets);
                        break;
                    case 6:
                        search_ticket(tickets, input);
                        break;
                    default:
                        System.out.println("Please enter a valid option");
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input.Please enter a valid number.");
                input.next();
            }
        }


    }

    private static void buy_seat(int[][] planeseat, Scanner input, Ticket[][] tickets) {

        System.out.println("Buy seat");
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter the row letter (A, B, C, or D): ");
                char row = input.next().toUpperCase().charAt(0);
                if (row < 'A' || row > 'D') {
                    throw new InputMismatchException(); // Throw InputMismatchException for invalid row letter
                }

                int rowIndex = row - 'A';

                int maxSeat;
                if (rowIndex == 0 || rowIndex == 3) {
                    maxSeat = 14;
                } else {
                    maxSeat = 12;
                }

                System.out.println("Enter the seat number (1-" + maxSeat + "): ");
                int seat = input.nextInt();

                if (seat < 1 || seat > maxSeat) {
                    throw new InputMismatchException(); // Throw InputMismatchException for invalid seat number
                }

                input.nextLine(); // Consume the newline character

                if (planeseat[rowIndex][seat - 1] == 0) {
                    planeseat[rowIndex][seat - 1] = 1;

                    System.out.println("Enter your name: ");
                    String name = input.nextLine();

                    System.out.println("Enter your surname: ");
                    String surname = input.nextLine();

                    System.out.println("Enter your email: ");
                    String email = input.nextLine();

                    Person newPerson = new Person(name, surname, email);

                    int price;
                    if (seat <= 5) {
                        price = 200;
                    } else if (seat <= 9) {
                        price = 150;
                    } else {
                        price = 180;
                    }
                    Ticket newTicket = new Ticket(row, seat, price, newPerson);

                    tickets[rowIndex][seat - 1] = newTicket;

                    newTicket.save();
                    System.out.println("Successfully booked.");
                    validInput = true; // Set to true to exit the loop when input is valid
                } else {
                    System.out.println("The seat is already booked.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid inputs.");
                input.nextLine(); // Consume the invalid input
            }
        }

    }

    private static void cancel_seat(int[][] planeseat, Scanner input, Ticket[][] tickets) {

        System.out.println("Cancel Seat");

        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter the row letter: ");
                char row = input.next().toUpperCase().charAt(0);

                if (row < 'A' || row > 'D') {
                    throw new InputMismatchException(); // Throw InputMismatchException for invalid row letter
                }

                int rowIndex = row - 'A';
                int maxSeat;

                if (rowIndex == 0 || rowIndex == 3) {
                    maxSeat = 14;
                } else {
                    maxSeat = 12;
                }

                System.out.println("Enter the seat number (1-" + maxSeat + "): ");
                int seat = input.nextInt();

                if (seat < 1 || seat > maxSeat) {
                    throw new InputMismatchException(); // Throw InputMismatchException for invalid seat number
                }

                if (planeseat[rowIndex][seat - 1] == 1) {
                    planeseat[rowIndex][seat - 1] = 0;
                    System.out.println("Seat is successfully canceled.");

                    tickets[rowIndex][seat - 1] = null;

                    String path = row + "" + seat + ".txt";

                    File delfile = new File(path);
                    delfile.delete();

                    validInput = true;
                } else {
                    System.out.println("Seat is not booked.");
                    validInput = true; // Set to true to exit the loop when input is valid
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter inputs.");
                input.nextLine(); // Consume the invalid input
            }
        }


    }

    private static void find_first_available(int[][] planeseat) {
        for (int rowIndex = 0; rowIndex < planeseat.length; rowIndex++) {
            for (int seatNumber = 0; seatNumber < planeseat[rowIndex].length; seatNumber++) {
                if (planeseat[rowIndex][seatNumber] == 0) {
                    char rowletter = (char) (rowIndex + 'A');

                    System.out.println("The available seat is:" + rowletter + (seatNumber + 1));
                    return;
                }
            }
        }
        System.out.println("All seats are booked.");
    }

    private static void show_seating_plan(int[][] planeseat) {
        for (int rowIndex = 0; rowIndex < planeseat.length; rowIndex++) {
            for (int seatNumber = 0; seatNumber < planeseat[rowIndex].length; seatNumber++) {

                if (planeseat[rowIndex][seatNumber] == 0) {
                    System.out.print("0");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }

    private static void print_tickets_info(Ticket[][] tickets) {

        int totalprice = 0;

        for (int rowIndex = 0; rowIndex < tickets.length; rowIndex++) {
            for (int seatNumber = 0; seatNumber < tickets[rowIndex].length; seatNumber++) {
                if (tickets[rowIndex][seatNumber] != null) {
                    tickets[rowIndex][seatNumber].TicketInfo();
                    totalprice = totalprice + tickets[rowIndex][seatNumber].getPrice();

                }

            }

        }
        System.out.println("The total price is:$" + totalprice);

    }

    private static void search_ticket(Ticket[][] tickets , Scanner input){
        System.out.println("Search Ticket");

        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter the row letter (A, B, C, or D): ");
                char row = input.next().toUpperCase().charAt(0);
                if (row < 'A' || row > 'D') {
                    throw new InputMismatchException(); // Throw InputMismatchException for invalid row letter
                }

                int rowIndex = row - 'A';
                int maxSeat;

                if (rowIndex == 0 || rowIndex == 3) {
                    maxSeat = 14;
                } else {
                    maxSeat = 12;
                }

                System.out.println("Enter the seat number (1-" + maxSeat + "): ");
                int seat = input.nextInt();

                if (seat < 1 || seat > maxSeat) {
                    throw new InputMismatchException(); // Throw InputMismatchException for invalid seat number
                }

                if (tickets[rowIndex][seat - 1] != null) {
                    tickets[rowIndex][seat - 1].TicketInfo();
                } else {
                    System.out.println("Seat is not booked.");
                }

                validInput = true; // Set to true to exit the loop when input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid row letter and seat number.");
                input.nextLine(); // Consume the invalid input
            }
        }
    }
}


