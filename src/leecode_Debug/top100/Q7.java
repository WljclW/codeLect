package leecode_Debug.top100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Solution_7 {
    public int reverse(int x) {
        int res = 0;
        while(x!=0){
            //后面要给res乘以10了，这里先检查以下是不是乘10以后它就越界了
            if(res<Integer.MIN_VALUE/10 || res>Integer.MAX_VALUE/10){
                return 0;
            }
            //这就是反转一个数的关键代码————每一次将之前的结果乘10，然后加上当前的余数。
            res += res*10 + x%10;
            x /= 10;
        }
        return res;
    }
}

public class Q7 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int x = Integer.parseInt(line);

            int ret = new Solution_7().reverse(x);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}