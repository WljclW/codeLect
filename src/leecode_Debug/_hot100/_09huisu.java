package leecode_Debug._hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _09huisu {
    /*46.
    给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
    * */
    List<List<Integer>> res;
    List<Integer> path;
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        res = new LinkedList<>();
        path = new LinkedList<>();
        used = new boolean[nums.length];
        permuteBack(nums);
        return res;
    }

    private void permuteBack(int[] nums) {
        if (path.size() == nums.length) {
            res.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            path.add(nums[i]);
            permuteBack(nums);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }


    /*78.
    给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
    * */
//    public List<List<Integer>> subsets(int[] nums) {
//
//    }


    /*17.
    给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
    * */
//    public List<String> letterCombinations(String digits) {
//
//    }


    /*39.
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
对于给定的输入，保证和为 target 的不同组合数少于 150 个。
    * */
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//
//    }


    /*22.
    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。*/
//    public List<String> generateParenthesis(int n) {
//
//    }


    /*79.
    给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
    * */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, int index, String word) {
        if (index == word.length()) return true; /*写成"index==word.length()-1"就错了*/
        if (i >= 0 && j >= 0 && i < board.length && j < board[0].length && board[i][j] == word.charAt(index)) {
            board[i][j] = '\0';
            boolean cur = dfs(board, i + 1, j, index + 1, word) ||
                    dfs(board, i - 1, j, index + 1, word) ||
                    dfs(board, i, j + 1, index + 1, word) ||
                    dfs(board, i, j - 1, index + 1, word);
            board[i][j] = word.charAt(index);
            return cur;
        } else {
            return false;
        }
    }


    /*131.
    给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
    * */
    List<List<String>> resPartition;
    List<String> pathPartition;
    public List<List<String>> partition(String s) {
        resPartition = new LinkedList<>();
        pathPartition = new LinkedList<>();
        partitionBack(s,0);
        return resPartition;
    }

    private void partitionBack(String s, int index) {
        /*如果当前研究到了最后的位置，则添加进结果集*/
        if (index>=s.length()){
            resPartition.add(new LinkedList<>(pathPartition)); /*注意创建一个新的copy*/
            return;
        }
        /*从该位置开始，依次判断生成的子串是不是回文。如果是回文则递归调用“partitionBack(s,i+1)”*/
        for (int i=index;i<s.length();i++){
            if (isPalindrome(s.substring(index,i+1))){
                pathPartition.add(s.substring(index,i+1));
                partitionBack(s,i+1);
                pathPartition.remove(pathPartition.size()-1);
            }
        }
    }

    /*判断一个串是不是回文串*/
    private boolean isPalindrome(String substring) {
        //两个指针相向而行，判断是不是指向的字符永远相等。。一旦出现不相等就返回false
        for (int i=0,j=substring.length()-1;i<j;i++,j--){
            if (substring.charAt(i)!=substring.charAt(j)){
                return false;
            }
        }
        return true;
    }


    /*51.
    按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。

n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
    * */
    /**
     * 【解题思路】从第0行开始，依次研究每一行。
     *      找到的条件：本轮研究的行来到了最后一行的下一行；
     *      处理逻辑：对于本轮研究行的每一个位置，判断放皇后是不是合理。如果合理的话就放一个皇后
     *  继续研究row+1行；否则的话研究该行的下一个位置。
     * */
    List<List<String>> resSolveNQueens = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] chessBoard = new char[n][n];
        for (int i=0;i<n;i++){
            Arrays.fill(chessBoard[i],'.');
        }
        solveNQueensBack(n,0,chessBoard);
        return resSolveNQueens;
    }

    private void solveNQueensBack(int n, int row, char[][] chessBoard) {
        /*if语句块添加结果集*/
        if (row>=n){ //只要当前需要研究的row来到最后一行的后一行，就说明找到了一个可行解。
            resSolveNQueens.add(Array2List(chessBoard));
            return;
        }
        /*尝试*/
        for (int i=0;i<n;i++){ //遍历当前行的每一个（列）位置
            if (isVaid(row,i,n,chessBoard)){ //如果该位置放置皇后不会冲突的话
                chessBoard[row][i] = 'Q';
                solveNQueensBack(n,row+1,chessBoard); //递归的决策后面的行
                chessBoard[row][i] = '.';
            }
        }
    }

    /*判断如果(row,col)放置一个皇后，是否合规*/
    private boolean isVaid(int row, int col, int n, char[][] chessBoard) {
        //判断col这一列是不是有皇后
        for (int rowIndex=0;rowIndex<row;rowIndex++){
            if (chessBoard[rowIndex][col]=='Q')
                return false;
        }
        //判断45方向，是不是有皇后
        for (int i=row-1,j=col-1;i>=0&&j>=0;i--,j--){
            if (chessBoard[i][j]=='Q')
                return false;
        }
        //判断135度方向，是不是有皇后
        for (int i=row-1,j=col+1;i>=0&&j<=n-1;i--,j++){
            if (chessBoard[i][j]=='Q')
                return false;
        }
        return true;
    }
    /*将一个可行性解转换为List类型*/
    public List Array2List(char[][] chessboard) {
        ArrayList<String> res = new ArrayList<>();
        for (char[] row:chessboard){
            res.add(String.copyValueOf(row));
        }
        return res;
    }


    /**=============================非100补充=======================================*/
    /**
     * 47
     * 一组可能有重复值的数组，写出所有的全排列。要求不能有重复的全排列
     */
    List<List<Integer>> resUnique;
    List<Integer> pathUnique;
    boolean[] usedUnique;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        resUnique = new LinkedList<>();
        pathUnique = new LinkedList<>();
        usedUnique = new boolean[nums.length];
        permuteUniqueBack(nums);
        return res;
    }

    private void permuteUniqueBack(int[] nums) {
        if (path.size() == nums.length) {
            resUnique.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            /*
            // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
            // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
            // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
            通俗理解：
                当前数和前面的数相等，但是前面的数没有选，当前数就不能选。
            详细的理解见：https://programmercarl.com/0047.%E5%85%A8%E6%8E%92%E5%88%97II.html#%E5%85%B6%E4%BB%96%E8%AF%AD%E8%A8%80%E7%89%88%E6%9C%AC
            * */
            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
                continue;
            }
            if (!used[i]) { /**err：全排列每一个数都必须选 并且 每一个数字只能选一次*/
                path.add(nums[i]);
                used[i] = true;
                permuteUniqueBack(nums);
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }
}
