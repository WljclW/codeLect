package leecode_Debug.test.RedBook;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        int n = S.nextInt();
        int k = S.nextInt();
        System.out.println((n*(n+1)/2)*k);
    }
}