package leecode_Debug;

import java.util.LinkedList;
import java.util.List;

/**
 * @author mini-zch
 * @date 2025/6/4 17:10
 */
public class All_06 {
    public int findMin(int[] nums) {
        int left = 0,right = nums.length-1;
        /*
            1. 这里必须是小于，因为一旦等于的时候就只有一个数了，其实这个数就是最小值。
            2. 如果这里写的是“小于等于”，则对于[5,8,9,10]这个数组，运行时会出现死循环。想象一种场景：l和r都指向某一个
         位置m，并且这个数小于数组的最后一个数，则按照代码逻辑“right=mid”，执行后l指向m，r也指向m.....由于l永远不大于
         r，因此成死循环。。所以求最小值的时候，这里必须是“left<right”
        * */
        while(left<right){
            int mid = left+(right-left)/2;
            if (nums[mid]>nums[nums.length-1]){
                left = mid+1;
            }else{
                right = mid; /*此时mid位置的值可能就是最小值*/
            }
        }
        return nums[right];
    }



    //复原ip地址
    List<String> resRestoreIpAddresses;
    public List<String> restoreIpAddresses(String s) {
        resRestoreIpAddresses = new LinkedList<>();
        StringBuilder sb = new StringBuilder(s);
        resRestoreIpAddressesBack(sb,0,0);
        return resRestoreIpAddresses;
    }

    private void resRestoreIpAddressesBack(StringBuilder sb, int index, int pointNum) {
        if (pointNum==3&&isValidIp1(sb,index, sb.length()-1)){
            resRestoreIpAddresses.add(new String(sb));
            return;
        }
        for (int i=index;i<sb.length()-1;i++){
            if (isValidIp1(sb,index,i+1)){
                sb.insert(i+1,'.');
                resRestoreIpAddressesBack(sb,i+1,++pointNum);
                sb.deleteCharAt(i+1);
            }
        }
    }

    private boolean isValidIp1(StringBuilder sb,int index, int i) {
        if (i-index==1){
            return true;
        }
        if (i-index>1&&sb.charAt(index)=='0'){
            return false;
        }
        if (Integer.valueOf(sb.substring(index,i))>255){
            return false;
        }
        return true;
    }
}
