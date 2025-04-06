package leecode_Debug.arr;

public class P695 {
    public static void main(String[] args) {
        P695 p695 = new P695();
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(p695.maxAreaOfIsland(grid));

    }

        public int maxAreaOfIsland(int[][] grid) {
            int m=grid.length,n=grid[0].length;
            int res=0;
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(grid[i][j]==1){
                        int cur = dfs(grid,i,j,0);
                        res = Math.max(cur,res);
                    }
                }
            }
            return res;
        }

        int dfs(int[][]grid,int i,int j,int sum){
            if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]!=1) return 0;
            grid[i][j]=0;
            sum++;
            sum =dfs(grid,i-1,j,sum)+
                    dfs(grid,i,j-1,sum)+
                    dfs(grid,i+1,j,sum)+
                    dfs(grid,i,j+1,sum);
            return sum;
        }
}
