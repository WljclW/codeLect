package zuo_course_02baseTiSheng.No_3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 单调栈————应用：一列数中找到左右两边离它最近的且大于（或者小于）它的值。
 * 单调栈要求栈中的元素按照严格的单调顺序(注意：不能相等)存放，如果某个元素进栈时发现打破了这种顺序，就要出栈，直到当前元素加入
 *      栈顶时不打破那种规定的顺序。
 * 只要出栈，就生成”出栈元素“所需要的信息；如果出栈的是链表，则会生成“出栈的链表中所有元素”的信息。。比如拿“出栈的是元素”且“查找
 *      左右第一个大于当前元素”来说：一旦某一个元素出栈，则信息确定如下：右边大于该元素的第一个元素就是 即将入栈的元素；左边第一
 *      个大于该元素的元素就是它出栈后 新的栈顶元素
 * */
public class danDiaoZhan {

    //假设数组中没有重复元素
    public static int[][] getNearLargeNoRepeat(int[] arr){
        Stack<Integer> dandiaostack = new Stack<>();
        int[][] res = new int[arr.length][2];  //每一个元素对应两个值————左边离它最进且比它大的数的下标，右边一样
        for (int i=0;i< arr.length;i++){
            /*
             *  遍历数组中的元素————
             *     如果当前元素大于栈顶的元素，就让栈顶的元素出栈，直到栈顶的元素大于当前要入栈的元素；
             *     任何一个元素，一旦要出栈，在出栈的时候就会生成它所需要的信息。
             * */
            while(!dandiaostack.isEmpty()&&arr[i]>arr[dandiaostack.peek()]){
                Integer popIndex = dandiaostack.pop();
                res[popIndex][1] = i;       //右边满足要求的即为当前要入栈的数
                res[popIndex][0] = dandiaostack.isEmpty()?-1:dandiaostack.peek();       //左边满足要求的即为栈中在它下面的数
            }
            dandiaostack.push(i);       //当前数入栈
        }
        /*
         *  出了for循环表示所有的元素均已经过了一遍，如果此时单调栈不为空，则需对单调栈进行清算
         * */
        while(!dandiaostack.isEmpty()){
            /*
             * 虽然数组遍历完了，但是单调栈中还有元素，需要进行清算。
             *    清算阶段中，弹出的所有元素右边均没有满足要求的数；左边满足要求的数就是栈中在它下面的数。
             * */
            Integer index = dandiaostack.pop();
            res[index][1] = -1;      //右边没有满足要求的数
            res[index][0] = dandiaostack.isEmpty()?-1:dandiaostack.peek();  //左边满足要求是栈中被它压着的数，但是要判断它下面还有元素没。
        }
        return res;
    }


    //数组中如果有重复元素的时候。我们在栈中的每一个位置存放的是链表，把相等的元素放进去。
    public static int[][] getNearLarge(int[] arr){
        /**
         *      如果数组存在重复元素，则栈中存放的需要转变为链表，将相等的元素在栈中放在同一个位置的链表中。
         *  与之前的无重复元素相比，区别有下：
         *          ①此时一旦元素A需要出栈，则栈中压在它下面的元素认为是下面链表的最后一个值（链表的添加元素是有顺序的）；
         *          ②右边满足要求的数依然是谁让它出栈的，就是谁
         *          ③每次出栈时，生成栈顶的那个链表中所有元素的题目要求信息。
         * */
        Stack<LinkedList<Integer>> dandiaostack = new Stack<>();        //需要用到的栈结构
        int[][] res = new int[arr.length][2];               //res顺组存放每一个位置对应的左右两边最近且比它大的数
        for (int i=0;i< arr.length;i++){            //遍历数组的每一个元素
            while(!dandiaostack.isEmpty() && arr[i] > arr[dandiaostack.peek().get(0)]){
                /**
                 * 如果栈不为空并且栈顶链表对应的元素值比当前要入栈的元素小。。此时就是要加入比栈顶大的元素，不满足规则因此需要出栈。
                 * */
                LinkedList<Integer> topList = dandiaostack.pop();       //弹出栈顶的链表
                for (int index:topList){
                    /**
                     * 对链表遍历，由于这个链表出栈了，因此需要生成链表中所有元素的信息。
                     *      【注意】dandiaostack.isEmpty()?-1:dandiaostack.peek().get(dandiaostack.peek().size()-1);
                     *      这是被认为出栈链表元素的左边离它最近的元素，也就是栈在它下面的元素(这里是下面的链表的最后一个值)
                     * */
                    res[index][0] = dandiaostack.isEmpty()?-1:dandiaostack.peek().get(dandiaostack.peek().size()-1);
                    res[index][1] = i;
                }
            }
            //如果当前要入栈元素等于栈顶链表的元素值，则添加到栈顶的链表中
            if (!dandiaostack.isEmpty() && arr[i] == arr[dandiaostack.peek().get(0)])
                dandiaostack.peek().add(i);
            //否则，就表示该值还没有在栈中出现过，因此需要创建链表加入当前节点并将链表加入单调栈。
            else {
                LinkedList<Integer> list = new LinkedList<>();      //创建链表
                list.add(i);        //链表中加入当前元素
                dandiaostack.push(list);        //链表入栈
            }
        }
        while(!dandiaostack.isEmpty()){
            /**
             *      数组遍历完成但是单调栈非空，需要清算单调栈。
             * */
            LinkedList<Integer> list = dandiaostack.pop();
            for (int index:list){
                res[index][0] = dandiaostack.isEmpty()?-1:dandiaostack.peek().get(dandiaostack.peek().size()-1);
                res[index][1] = -1;
            }
        }
        return res;
    }



    public static void main(String[] args) {
        int[] arr={9,3,1,3,4,3,5,3,2,2};
        int[][] res = getNearLargeNoRepeat(arr);
        System.out.println(Arrays.deepToString(res));
    }
}
