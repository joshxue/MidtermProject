package BandOfHour;

import java.util.Scanner;

public class BandOfHourProject{
    static final int MAX_ROWS = 10;
    static final int MAX_POSITIONS = 8;
    static char[] rows = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfRows;
        int[] positionNumber = {0,0,0,0,0,0,0,0};
        char choice;
        BandOfHour.BandHourNonstatic bandAssignment;

        System.out.println("Welcome to the Band of the Hour");
        System.out.println("-------------------------------");

        // To receive user's initial input on Row
        System.out.print("Please enter number of rows: ");
        numberOfRows = scanner.nextInt();
        while (numberOfRows < 1 || numberOfRows > MAX_ROWS) {
            System.out.print("ERROR: Out of range, try again: ");
            numberOfRows = scanner.nextInt();
        }//end of while loop

        // To receive user's initial input on Position
        for (int index = 0; index < numberOfRows; index++) {
            System.out.printf("Please enter number of positions in row " + rows[index] + " : ");
            positionNumber[index] = scanner.nextInt();
            while (positionNumber[index] < 1 || positionNumber[index] > MAX_POSITIONS) {
                System.out.print("ERROR: Out of range, try again: ");
                positionNumber[index] = scanner.nextInt();
            }//end of while loop

        }//end of for loop

        //object of Non-static class
        bandAssignment = new BandOfHour.BandHourNonstatic(numberOfRows, positionNumber);

        do {
            System.out.print("\n(A)dd, (R)emove, (P)rint,          e(X)it : ");
            choice = scanner.next().toUpperCase().charAt(0);
            switch (choice) {
                case 'A':
                    bandAssignment.addMusician();
                    break;
                case 'R':
                    bandAssignment.removeMusician();
                    break;
                case 'P':
                    bandAssignment.printAssignments();
                    break;
                case 'X':
                    System.out.println();
                    break;
                default:
                    System.out.println("ERROR: Invalid option, try again");
            }//end of switch
        } while (choice != 'X');

    }//end of main method

}// end of main class


