package leecode_Debug.test.RedBook.p2;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        int n = S.nextInt();
        int m = S.nextInt();
        int k = S.nextInt();
        int max = 0;
        int[] res = new int[n+1];
        for(int i=0;i<m;i++){
            int li = S.nextInt();
            int ri = S.nextInt();
            max = Math.max(ri-li,max);
            for(int j=li;j<=ri;j++){
                res[j]++;
            }
        }
        int[] ress = new int[n+1];
        int maxx = 0;
        for(int i=0;i<=n;i++){
            if(i>k){
                for(int j=i-k;j<=i;j++){
                    ress[i] += res[j];
                }
            }else{
                for(int j=0;j<=i;j++){
                    ress[i] += res[j];
                }
            }
            maxx = Math.max(ress[i],maxx);
        }

        if(max>k) System.out.println(k);
        else System.out.println(maxx);
    }
}