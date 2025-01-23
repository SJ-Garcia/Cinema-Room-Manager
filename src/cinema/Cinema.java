package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rows = enterInteger();

        System.out.println("Enter the number of seats in each row:");
        int seatsEachRow = enterInteger();

        String[][] room = new String[rows][seatsEachRow];
        for (String[] row : room) {
            Arrays.fill(row, "S");
        }

        int choice;
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            choice = enterInteger();

            switch (choice) {
                case 1:
                    printSeats(room);
                    break;
                case 2:
                    buyTicket(room);
                    break;
                case 3:
                    showStats(room);
                    break;
                case 0:
                    scanner.close();
                    return;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    static void showStats(String[][] room) {
        int purchasedTickets = 0;
        float percentage;
        int currentIncome = 0;
        int totalIncome = 0;

        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                if ("B".equals(room[i][j])) {
                    purchasedTickets++;
                    currentIncome += checkPrice(i + 1, room.length, room[i].length);
                }
                totalIncome += checkPrice(i + 1, room.length, room[i].length);
            }
        }
        percentage = (100f * purchasedTickets) / (room.length * room[0].length);

        System.out.println("Number of purchased tickets : " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    static void buyTicket(String[][] room) {
        int rowNumber, seatNumber;
        while (true) {
            System.out.println("Enter a row number:");
            rowNumber = enterInteger();

            System.out.println("Enter a seat number in that row:");
            seatNumber = enterInteger();

            if (rowNumber < 1 || rowNumber > room.length
                    || seatNumber < 1 || seatNumber > room[0].length) {
                System.out.println("Wrong input!");
                continue;
            }

            if ("B".equals(room[rowNumber - 1][seatNumber - 1])) {
                System.out.println("That ticket has already been purchased!");
            } else {
                room[rowNumber - 1][seatNumber - 1] = "B";
                System.out.println("Ticket price: $" + checkPrice(rowNumber, room.length, room[0].length));
                break;
            }
        }

    }

    static boolean isLargeRoom(int rows, int seatsEachRow) {
        return rows * seatsEachRow >= 60;
    }

    static int checkPrice(int rowNumber, int rows, int seatsEachRow) {
        return !isLargeRoom(rows, seatsEachRow) ? 10 : rowNumber <= rows / 2 ? 10 : 8;
    }

    static void printSeats(String[][] room) {
        System.out.print("Cinema:\n  ");
        for (int i = 1; i <= room[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < room.length; i++) {
            System.out.print((i + 1) + " ");
            for (String row : room[i]) {
                System.out.print(row + " ");
            }
            System.out.println();
        }
    }

    static int enterInteger() {
        String input;
        while (true) {
            input = scanner.next();
            try {
                scanner.nextLine();
                return Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Wrong input!");
            }
        }
    }
}