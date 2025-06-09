package leecode_Debug;

import java.util.ArrayList;
import java.util.Arrays;
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


    List<List<String>> resSolveNQueens;
    public List<List<String>> solveNQueens(int n) {
        resSolveNQueens = new LinkedList<>();
        char[][] chessBoard = new char[n][n];
        for (int i=0;i<n;i++){
            Arrays.fill(chessBoard[i],'.');
        }
        solveNQueensBack(n,chessBoard,0);
        return resSolveNQueens;
    }

    private void solveNQueensBack(int n,char[][] chessBoard, int row) {
        if (row>=n){
            resSolveNQueens.add(Array2List(chessBoard));
        }
        for (int i=0;i<n;i++){
            if (isValid(chessBoard,row,i)){
                chessBoard[row][i] = 'Q';
                solveNQueensBack(n,chessBoard,row+1);
                chessBoard[row][i] = '.';
            }
        }
    }

    private boolean isValid(char[][] chessBoard, int row, int i) {
        for (int cur=0;cur<row;cur++){
            if (chessBoard[cur][i]=='Q') return false;
        }
        for (int cur=row-1,col=i-1;cur>=0&&col>=0;cur--,col--){
            if (chessBoard[cur][col]=='Q') return false;
        }
        for (int cur=row-1,col=i+1;cur>=0&&col<chessBoard.length;cur--,col++){
            if (chessBoard[cur][col]=='Q') return false;
        }
        return true;
    }

    private List<String> Array2List(char[][] chessBoard) {
        ArrayList<String> res = new ArrayList<>();
        for (char[] row:chessBoard){
            res.add(String.copyValueOf(row));
        }
        return res;
    }


    /**
     * 【难点】子串的区间问题很繁琐
     * */
    List<String> resRestoreIpAddresses1;
    public List<String> restoreIpAddresses1(String s) {
        resRestoreIpAddresses1 = new LinkedList<>();
        StringBuilder sb = new StringBuilder(s);
        resRestoreIpAddressesBack1(0,sb,0);
        return resRestoreIpAddresses1;
    }

    private void resRestoreIpAddressesBack1(int index, StringBuilder sb,int pointNum) {
        if (pointNum==3){
            if (isValidIp01(index,sb.length()-1,sb))
            resRestoreIpAddresses1.add(new String(sb.toString()));
            return;
        }
        for (int i=index;i<sb.length();i++){
            if (isValidIp01(index,i,sb)){
                sb.insert(i+1,'.');
                pointNum++;
                resRestoreIpAddressesBack1(i+2,sb,pointNum);
                pointNum--;
                sb.deleteCharAt(i+1);
            }
        }
    }

    private boolean isValidIp01(int left, int right,StringBuilder sb) {
        if (left>right) return false;
        if (right== left) return true;
        if (sb.charAt(left)!='0'&&right-left<=2&&Integer.parseInt(sb.substring(left,right+1))<=255) return true;
        return false;
    }


    public static void main(String[] args) {
        All_06 all_06 = new All_06();
        System.out.println(all_06.restoreIpAddresses1("25525511135"));
    }

}
