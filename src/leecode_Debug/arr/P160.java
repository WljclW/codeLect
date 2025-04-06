package leecode_Debug.arr;

public class P160 {
    public static void main(String[] args) {
        int[] m = new int[]{10,9,9,9,10};
        System.out.println(majorityElement(m));
    }


    public static int majorityElement(int[] nums) {
        int flag = nums[0];
        int count=0;
        for(int num:nums){
            if(flag==num){
                count++;
            }else{
                count--;
                if(count<0)
                    flag = num;
            }
        }
        return flag;
    }
}
