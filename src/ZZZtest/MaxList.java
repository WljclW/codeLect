package ZZZtest;
import java.util.ArrayList;
public class MaxList {
    public static void main(String[] args) {
        int[] num = {65, 22, 34, 99, 27, 25, 100, 33, 35, 200, 44, 57};
        list(num);
    }

    private static void list(int[] num) {
        if (num == null) {
            return;
        }
        int max = 0;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < num.length; i++) {
            if (max == 0) {
                arrayList.add(num[i]);
                max ++;
                continue;
            }
            if (arrayList.get(max - 1) < num[i]) {
                arrayList.add(num[i]);
                max ++;
            } else {
                // 找到比它大，但是最小的那个数据，替换掉
                int left = binarySearch(arrayList, max, num[i]);
                arrayList.set(left, num[i]);
            }
        }
        System.out.println("最大递增长度为:" + max);
    }

    private static int binarySearch(ArrayList<Integer> arrayList, int right, int n) {
        int left = 0;
        while (left < right){
            int mid = (right + left) / 2;
            if (arrayList.get(mid) >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
