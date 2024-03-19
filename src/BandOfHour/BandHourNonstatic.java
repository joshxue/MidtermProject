package BandOfHour;

import java.util.Scanner;

public class BandHourNonstatic {

    static Scanner scanner = new Scanner(System.in);

    static final int MAX_POSITIONS = 8;
    static final double MAX_WEIGHT = 200.0;
    static final double MIN_WEIGHT = 45.0;
    static double[][] weights;
    static int numberOfRows ;
    static int[] positionNumber;
    static char[] rows = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    public BandHourNonstatic(int rowNumber, int[] positionNum) {
        numberOfRows = rowNumber;
        positionNumber = positionNum;
        weights = new double[numberOfRows][];
        for (int i = 0; i < numberOfRows; i++) {
            weights[i] = new double[positionNum[i]];
            for (int j = 0; j < positionNum[i]; j++) {
                weights[i][j] = 0.0;
            }
        }

    };
    public void addMusician() {
        System.out.print("Please enter row letter: ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);
        int rowIndex = rowLetter - 'A';

        while (rowIndex < 0 || rowIndex >= numberOfRows) {
            System.out.print("ERROR: Out of range, try again: ");
            rowLetter = scanner.next().toUpperCase().charAt(0);
            rowIndex = rowLetter - 'A';
        }

        System.out.printf("Please enter position number (1 to %d): ", positionNumber[rowIndex]);
        int position = scanner.nextInt() - 1;
        while (position < 0 || position >= positionNumber[rowIndex]) {
            System.out.print("ERROR: Out of range, try again: ");
            position = scanner.nextInt() - 1;
        }

        if (weights[rowIndex][position] != 0.0) {
            System.out.println("ERROR: There is already a musician there.");
            return;
        }

        System.out.print("Please enter weight (45.0 to 200.0): ");
        double weight = scanner.nextDouble();
        while (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
            System.out.print("ERROR: Out of range, try again: ");
            weight = scanner.nextDouble();
        }

        double newRowTotal = calculateRowTotalWeight(rowIndex) + weight;
        if (newRowTotal > positionNumber[rowIndex] * 100) {
            System.out.println("ERROR: That would exceed the average weight limit.");
            return;
        }

        weights[rowIndex][position] = weight;
        System.out.println("****** Musician added.");
    }

    public void removeMusician() {
        System.out.print("Please enter row letter: ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);
        int rowIndex = rowLetter - 'A';
        while (rowIndex < 0 || rowIndex >= numberOfRows) {
            System.out.print("ERROR: Out of range, try again: ");
            rowLetter = scanner.next().toUpperCase().charAt(0);
            rowIndex = rowLetter - 'A';
        }

        System.out.printf("Please enter position number (1 to %d): ", positionNumber[rowIndex]);
        int position = scanner.nextInt() - 1;
        if (position < 0 || position >= positionNumber[rowIndex] || weights[rowIndex][position] == 0.0) {
            System.out.println("ERROR: That position is vacant.");
            return;
        }

        weights[rowIndex][position] = 0.0;
        System.out.println("****** Musician removed.");
    }

    public void printAssignments() {
        for (int i = 0; i < numberOfRows; i++) {
            System.out.print(rows[i] + ": ");
            double totalWeight = 0;
            for (int j = 0; j < positionNumber[i]; j++) {
                System.out.printf("%6.1f ", weights[i][j]);
                totalWeight += weights[i][j];
            }
            double averageWeight;
            if (totalWeight > 0) {
                averageWeight = totalWeight / weights[i].length;
            } else {
                averageWeight = 0;
            }
            System.out.printf("        [%6.1f, %6.1f]\n", totalWeight, averageWeight);
        }
    }

    public double calculateRowTotalWeight(int rowIndex) {
        double total = 0;
        for (double weight : weights[rowIndex]) {
            total += weight;
        }
        return total;
    }

}
