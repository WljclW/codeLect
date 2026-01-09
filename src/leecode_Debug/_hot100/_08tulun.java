package leecode_Debug._hot100;

import java.util.LinkedList;
import java.util.List;

public class _08tulun {
    /*200. 岛屿数量
    给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    此外，你可以假设该网格的四条边均被水包围。
    * */
    /**
     【建议的写法】两种写法都可以，但是要学会 numIslands_ 的声明方向的写法，方向声明
        为 dirs，并且再声明的时候直接初始化
     【说明】这个题 每一个位置遍历之后，不能撤销之前的修改，撤销的话就错了
     */
    /*普通的写法*/
    public int numIslands(char[][] grid){
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
        /**【注意】：这里撤销修改的话就错了，会导致重复计数！！！因此研究过的陆地就要直接修改掉*/
//        grid[i][j] = '1';
    }

    /*声明方向的写法。*/
    int[][] dirs = {{1,0},{-1,0},{0,-1},{0,1}};
    public int numIslands_(char[][] grid) {
        int res = 0;
        int m = grid.length,n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j]=='1'){
                    dfs3(grid,i,j);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs3(char[][] grid, int i, int j) {
        if (i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]!='1') return;
        grid[i][j] = '0';
        for (int[] dir:dirs){
            int x = i+dir[0],y = j+dir[1];
            dfs3(grid,x,y);
        }
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



    /*207. 课程表
    你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
    在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
    例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
    请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
    * */
    /**
     【实质】是判断一个有向图是不是无环
     【方法1】BFS（拓扑排序）
     1. 建立邻接表，统计每个节点的 入度。
     2. 把入度为 0 的节点加入队列。
     3. 每次弹出队列的节点，把它指向的邻居入度减 1，如果入度为 0，再入队。
     4. 最后如果能弹出所有节点（拓扑序列长度 == 课程数），说明无环；否则有环。有节点（拓扑
     序列长度 == 课程数），说明无环；否则有环。
     【注意】
     我们的想法是每一次拿到某个入度维0的节点，然后将所有“前置课程”是该节点的课程即为m入
     度-1，如果-1后发现课程m的入度也是0了（即课程m的全部前置课程已经完成），则将课程m也添加
     到入度为0的课程队列
     */
    //BFS
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        /*step1：声明必须的信息，并初始化
            graph————存放一个个列表，索引i位置的列表存储课程i所有的后置课程————即如果课程i
        完成，接下来可学习的课程列表。可以描述为“前置课程i====>课程i所有后置课程的列表”
            indegree————入度表，索引i位置的值表示课程i的入度，即课程i的前置课程还有多少
            时间复杂度————O(V)，因为循环执行 numCourses 次；空间复杂度————O(V)
         */
        List<List<Integer>> graph = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new LinkedList<>());
        }
        int[] indegree = new int[numCourses];
        /*step2：使用prerequisites构建图和入度表。
            【详细说明】
                1. 构建图：List<List<Integer>>，根据p[1]拿到课程，p[0]则为它的后置课程，因此
             是"p[1]===>p[0]"（学完p[1]才能学习p[0]）这样的指向关系，所以拿到p[1]位置的List，
             将p[0]放进去，表示着图中有一条”p[1]节点指向p[0]节点的边“————graph.get(p[1]).add(p[0])。
                2. 入度表：因为”有一条p[1]指向p[0]的边“，因此对于节点p[0]来说，它的入度多了1，因
             此————indegree[p[0]]++。
            【注意】graph中位置index存储的是“课程index====>课程index所有的后继课程”，但是根据
         题目中描述的信息可知prerequisites数组存储的是“cur = [课程m，课程m的前置课程]”，因此这
         里添加的时候应该是倒序的关系，反过来添加即“拿到cur[1]对应的list然后把cur[0]加进去”
            时间复杂度————O(E)，因为每一条边就对应一次循环；
        */
        for (int[] p : prerequisites) { /**err：最绕的是这个for循环的内容*/
            int index = p[0], preCourse = p[1];
            graph.get(preCourse).add(index); /**这里容易写错，需要捋清楚添加的顺序~~~*/
            indegree[index]++;
        }

        /*step3：将入度为0的节点入队列。
            时间复杂度————O(V)，需要遍历 numCourses；
        * */
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        /*step4：依次拿出入度为0的节点进行处理，具体的步骤如下————
                ①从队列中弹出一个元素。（该课程对应的入度为0，即可直接学习）
                ②更新count。（已学习的课程数+1）
                ③从graph拿出“curVal是前置课程的课程列表”，将它对应的入度-1。如果-1后入度为0，则加进队列。
            这里的时间复杂度是难点。时间复杂度————
                ①外层的while循环每一个节点最多会入队列一次，出队列一次，因此外层的while循环最多执行V次；
                ②内层的for循环呢？这一步非常容易误判成 V * V，但这是错误的。每条边 pre → course；只会在
            pre被弹出的时候，访问一次；所有的for循环加起来，最多也就是边的数量即E。
                综上，上面的循环整体的时间复杂度 O(V+E)*/
        int count = 0;
        while (!queue.isEmpty()) {
            Integer curVal = queue.poll();
            count++;
            for (int index : graph.get(curVal)) {
                indegree[index]--; /**将对应后置课程的入度减一*/
                if (indegree[index] == 0) queue.offer(index);
            }
        }
        return count == numCourses;
    }



    /*208. 实现 Trie (前缀树)
    Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补全和拼写检查。

请你实现 Trie 类：
    Trie() 初始化前缀树对象。
    void insert(String word) 向前缀树中插入字符串 word 。
    boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
    boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
    **/
    /**
     【解题关键】关键是大脑中有一个26叉树，树中的每一个节点存储的是一个TrieNode结构————这个结
     构中包含了26个孩子的指针（即TrieNode[26]），以及是不是某一个单词结尾的标志（即变
     量isEnd）
     【代码的重点】①声明TrieNode，标志着节点的样子；②类内声明一个root作为根节点，每一次查找
     和 想插入的时候 都必须从root开始。
     【疑问】
     1. 前缀树的方法中第一行往往都是"TrieNode node = root;"，原因是什么？？
     答：每一次操作都是从根节点出发一步一步进行的，因此要保证整个树根是确定的，所以每一次
     方法执行之前要拷贝一份根节点，来使用拷贝后的指针node来执行操作。。。否则上一步操作完
     root节点就变了，因此就不对了
     【其他说明】
     ①每个节点存的是一个“分支数组 + 是否是单词结尾”；
     ②单词的路径就是从 root 一路走下去；
     ③search 就是看路径能不能走完，并且 isEnd=true；
     ④startsWith 就是看路径能不能走完，不管 isEnd。
     【注意】每一个位置并不用存储字符，而是对应的索引位置不是null的时候，就说明有对应的字符，
     因此是用下标index来暗示是不是有对应字符的。比如：index=0的位置如果不是null，就说明当前的
     位置是可以匹配到字符a的；index=2的位置如果不是null，就说明当前位置是可以匹配到字符b的....
     这才是这个题最抽象的地方~~
     */
    class Trie {

        class TrieNode{
            TrieNode[] children = new TrieNode[26]; /**err：注意在声明的时候进行初始化，或者 在空参的构造器中进行初始化也可以*/
            boolean isEnd;
        }

        private final TrieNode root; /**标识整个“前缀树”的根节点*/

        public Trie() {
            root = new TrieNode();
        }

        /**
         1. 插入的具体思路————
         for循环拿到word的每一个字符，如果字符对应位置的TrieNode是null，则创建；node来
         到它孩子数组的对应位置。
         2. 插入 和 查找前缀/查找单词 的区别————
         插入的时候如果到某一步发现children对应位置的TrieNode是null，则会创建TrieNode。
         但是”查找前缀/查找单词“的时候如果到某一步发现children对应位置的TrieNode是null，就直
         接返回false了。
         */
        public void insert(String word) {
            /**疑问？每一个方法之前都会这样记录一下这个变量，不记录的话行不行？？记录的意义又
             是什么？？
             答：不记录是不行的。意义就是保证全局记录的根节点root是正确的。如果这里不记录
             的话，会导致下面的问题————
             以insert为例，最终root指针将会来到插入节点的最后的位置，后面的操作就不知道根
             节点是啥了，所有的查找、插入都无从下手了*/
            TrieNode node = root;
            for (char c:word.toCharArray()){
                int index = c-'a';
                if (node.children[index]==null){
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        /**
         【思路】
         【search和startsWith的唯一区别在于：方法的最后返回值】
         startsWith要求有这样的前缀就可以，并不一定匹配一个确定的单词；但是search方法的要求是
         必须完整的匹配到一个单词…………因此最后的一个字符的isEnd必须是true才表示找到了
         */
        public boolean search(String word) {
            TrieNode node = root;
            for (char c:word.toCharArray()){
                int index = c-'a';
                if (node.children[index]==null) return false;
                node = node.children[index];
            }
            return node.isEnd; /**和startsWith方法的唯一区别*/
        }

        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char c:prefix.toCharArray()){
                int index = c-'a';
                if (node.children[index]==null) return false;
                node =node.children[index];
            }
            return true;
        }
    }




}
