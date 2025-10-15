package leecode_Debug._hot100;

public class _08tulun {

    /*200.
    给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    此外，你可以假设该网格的四条边均被水包围。
    * */
    public int numIsland(char[][] grid){
        int res = 0;
        int m = grid.length,n = grid[0].length;
        for (int i=0;i<m;i++)
            for (int j=0;j<n;j++){
                if (grid[i][j]=='1'){ //只需要研究为‘1’的位置
                    res++;
                    dfs(grid,i,j,m,n);
                }
            }
        return res;
    }

    void dfs(char[][] grid,int i,int j,int m,int n){
        /**err：需要注意grid为'0'的时候也需要跳过。否则会“stackOverFlow”*/
        if (i<0||i>=m||j<0||j>=n||grid[i][j]=='0')
            return;
        grid[i][j]='0';
        dfs(grid,i-1,j,m,n);
        dfs(grid,i,j-1,m,n);
        dfs(grid,i+1,j,m,n);
        dfs(grid,i,j+1,m,n);
        /**由于这个题修改了grid也没事，因此最后不还原也是可以的*/
//        grid[i][j] = '1';
    }


    /*994.
    在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：

    值 0 代表空单元格；
    值 1 代表新鲜橘子；
    值 2 代表腐烂的橘子。
    每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
    返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
    * */
//    public int orangesRotting(int[][] grid) {
//
//    }



    /*207.
    你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
    在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
    例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
    请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
    * */
//    public boolean canFinish(int numCourses, int[][] prerequisites) {
//
//    }



    /*208.
    Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补全和拼写检查。

请你实现 Trie 类：
    Trie() 初始化前缀树对象。
    void insert(String word) 向前缀树中插入字符串 word 。
    boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
    boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
    **/
//    class Trie {
//
//        public Trie() {
//
//        }
//
//        public void insert(String word) {
//
//        }
//
//        public boolean search(String word) {
//
//        }
//
//        public boolean startsWith(String prefix) {
//
//        }
//    }




}
