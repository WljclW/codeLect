package ZZZtest;

import java.util.Scanner;

public class ChocolateBoard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        // Find the possible minimum perimeter of the rectangles
        int minimumPerimeter = findMinimumPerimeter(n);

        System.out.println("The possible minimum perimeter of the rectangles is: " + minimumPerimeter);
    }

    // Find the minimum perimeter of the rectangles
    public static int findMinimumPerimeter(int n) {
        int minimumPerimeter = n * 2; // Initialize the minimum perimeter to be the same as the area

        // Find the values of length and width that give the minimum perimeter
        int length = n / 2;
        int width = n / 2;

        // Check if the minimum perimeter has been reached
        if (minimumPerimeter > n * 2) {
            length++;
            width++;
            minimumPerimeter = n * 2;
        }

        return minimumPerimeter;
    }
}