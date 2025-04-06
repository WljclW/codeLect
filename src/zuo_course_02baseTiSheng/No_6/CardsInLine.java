package zuo_course_02baseTiSheng.No_6;

public class CardsInLine {
    //暴力递归
    public static int win1(int[] arr){
        if(arr==null||arr.length==0) return 0;
        return Math.max(f(arr,0,arr.length-1),s(arr,0,arr.length-1));
    }
    //先手函数 当前该你拿 找最大
    public static int f(int[] arr,int l,int r){
        if(l==r) return arr[l];
        return Math.max(arr[l]+s(arr,l+1,r),arr[r]+s(arr,l,r-1));
    }
    //后手函数 当前不该你拿 对方希望你拿到最小
    public static int s(int[] arr,int l,int r){
        if(l==r) return 0;//只剩一个数，你是后手得不到分数
        return Math.min(f(arr,l+1,r),f(arr,l,r-1));
    }

    //记忆搜索dp
    public static int win2(int[] arr){
        //两数组左下半区表示l>r没用（不包括对角线)
        int[][] arrf=new int[arr.length][arr.length];
        int[][] arrs=new int[arr.length][arr.length];
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                arrf[i][j]=0;
                arrs[i][j]=0;
            }
        }
        if(arr==null||arr.length==0) return 0;
        return Math.max(f2(arr,0,arr.length-1,arrf,arrs),s2(arr,0,arr.length-1,arrf,arrs));
    }
    public static int f2(int[] arr,int l,int r,int[][] arrf,int[][] arrs){
        if(l==r) arrf[l][r]=arr[l];
        else arrf[l][r]=Math.max(arr[l]+s2(arr,l+1,r,arrf,arrs),arr[r]+s2(arr,l,r-1,arrf,arrs));
        return arrf[l][r];
    }
    public static int s2(int[] arr,int l,int r,int[][] arrf,int[][] arrs){
        if(l==r) arrs[l][r]=0;
        else arrs[l][r]=Math.min(f2(arr,l+1,r,arrf,arrs),f2(arr,l,r-1,arrf,arrs));
        return arrs[l][r];
    }

    //严格表结构dp
    public static int win3(int[] arr){
        if(arr==null||arr.length==0) return 0;
        int[][] f=new int[arr.length][arr.length];
        int[][] s=new int[arr.length][arr.length];
        //f,s的左下半边没用，f对角线初始化为arr[i],s对角线初始化为0————0就是默认的初始化值，因此不用初始化。
        for(int i=0;i<arr.length;i++){
            f[i][i]=arr[i];
        }
        for(int i=arr.length-2;i>=0;i--){
            for(int j=i+1;j<arr.length;j++){
                f[i][j]=Math.max(arr[i]+s[i+1][j],arr[j]+s[i][j-1]);
                s[i][j]=Math.min(f[i+1][j],f[i][j-1]);
            }
        }
        return Math.max(f[0][arr.length-1],s[0][arr.length-1]);
    }
}
