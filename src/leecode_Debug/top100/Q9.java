package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution_9 {
    public boolean isPalindrome(int x) {
        if(x<0)
            return false;
        String s = new Integer(x).toString();
        int l = 0,r = s.length()-1;
        while(l<r){
            if (s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }
}

public class Q9 {
    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int x = Integer.parseInt(line);

            boolean ret = new Solution_9().isPalindrome(x);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }
}