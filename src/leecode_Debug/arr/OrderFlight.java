package leecode_Debug.arr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderFlight {

}


class Solution_1109 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int[] ele:bookings){
            res[ele[0]-1] += ele[2];
            if (ele[1] < res.length)
                res[ele[1]] -= ele[2];
        }
        for (int i=1;i< res.length;i++){
            res[i] = res[i-1]+res[i];
        }
        return res;
    }
}
