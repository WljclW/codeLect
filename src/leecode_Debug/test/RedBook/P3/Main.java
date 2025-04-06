package leecode_Debug.test.RedBook.P3;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        int t = S.nextInt();
        for(int i=0;i<t;i++){
            int n = S.nextInt();
            int x = S.nextInt();
            int[] cur = new int[n]; //原始数组
            for(int j=0;j<n;j++){
                cur[j] = S.nextInt();
            }
            int[] ori = new int[n];
            int[] res = new int[n];
            ori[0] = res[0] = cur[0];
            for(int now=1;now<n;now++){
                int p1 = cur[i]<x?x:cur[i];
                int p2 = cur[i]+ori[i-1];


                ori[i] = Math.max(cur[i],cur[i]+ori[i-1]);
                if(p1>p2)
                    if(p1<x)
                        res[i] = x;
                    else
                        res[i] = p1;
                else if(p1<p2){

                }
            }
        }
    }
}