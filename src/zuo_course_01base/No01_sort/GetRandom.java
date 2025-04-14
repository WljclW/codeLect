package zuo_course_01base.No01_sort;

import java.util.Random;

/**
 * @author mini-zch
 * @date 2025/4/14 11:31
 */
public class GetRandom {
    /**
     * 返回参数长度的随机数数组。。。每一个数组的元素都是 100以内的整数
     * */
    public static int[] getInts(int total){
        int[] arr = new int[total]; // 创建长度为20的数组
        Random rand = new Random();        // 创建 Random 对象

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100); // 生成 0-99 之间的随机整数
        }
        return arr;
    }
}
