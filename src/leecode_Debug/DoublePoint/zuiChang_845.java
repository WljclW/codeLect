package leecode_Debug.DoublePoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution_845 {
    public int longestMountain(int[] arr) {
        int i=0;
        int max =0;
        //i是左边界，j是右边界，每一次j从i+1开始往后找
        while(i<arr.length-1){
            int j = i+1;
            if (arr[j]<=arr[i]){        //如果不满足山脉的左边(增)，则需要移动左指针。
                i++;
                continue;
            }
            while(j<arr.length && arr[j]>arr[j-1]){     //只要满足山脉左边增的性质，就不断移动右指针
                    j++;
            }
            //出了上面的while循环表示arr[j]不大于arr[j-1]；此时需要判断是不是小了，
            //      如果变小了表示出现山脉；然后继续移动右指针直到不满足山脉的右边性质(严格减)
            //      如果等于表示当前方案不存在山脉，则直接将右指针移动到左指针的位置
            while(j<arr.length && arr[j]<arr[j-1]){
                j++;
                max = Math.max(max,j-i);            //一旦进入while表示找到山脉，需更新max.
            }
            i = j-1;
        }
        return max;
    }
}


class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int ans = 0;
        int left = 0;
        while (left + 2 < n) {
            int right = left + 1;
            if (arr[left] < arr[left + 1]) {
                while (right + 1 < n && arr[right] < arr[right + 1]) {
                    ++right;
                }
                if (right < n - 1 && arr[right] > arr[right + 1]) {
                    while (right + 1 < n && arr[right] > arr[right + 1]) {
                        ++right;
                    }
                    ans = Math.max(ans, right - left + 1);
                } else {
                    ++right;
                }
            }
            left = right;
        }
        return ans;
    }
}


public class zuiChang_845 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] arr = stringToIntegerArray(line);

            int ret = new Solution_845().longestMountain(arr);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}