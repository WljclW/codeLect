package leecode_Debug.test.RedBook;

import java.util.*;

class Solution {

    /* Write Code Here */
    public int isEvenNum(int n) {
        if(n%2==0) return 1;
        else return 0;
    }
}

public class IsOuShu {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        int n;
        n = Integer.parseInt(in.nextLine().trim());

        res = new Solution().isEvenNum(n);
        System.out.println(String.valueOf(res));
    }
}

