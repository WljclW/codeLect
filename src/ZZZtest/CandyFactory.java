package ZZZtest;

import java.util.*;

public class CandyFactory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // number of types of candies
        int a = scanner.nextInt(); // total number of candy packages required
        int b = scanner.nextInt(); // minimum number of candies per package
        int[] production = new int[n]; // array to store the daily production of each type of candy
        for (int i = 0; i < n; i++) {
            production[i] = scanner.nextInt();
        }

        // calculate the total number of candies needed
        int totalCandiesNeeded = a * b;

        // initialize variables
        int days = 0;
        int totalProduction = 0;

        while (totalProduction < totalCandiesNeeded) {
            // increment the day count
            days++;

            // add the daily production of all types of candies to the total production
            for (int i = 0; i < n; i++) {
                totalProduction += production[i];
            }
        }

        System.out.println(days);
    }
}