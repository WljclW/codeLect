package zuo_course_01base.No_6;

public class qianZhuiShu {
    public static class TrieNode{
        public int pass;
        public int end;
        public TrieNode[] nexts;
        public TrieNode(){
            /**
             *  关于pass和end的理解：
             *          pass：某点的pass表示当前包含在前缀树的串中，前缀是——————从根节点到该节点的所有节点表示出来的串 的数量。比如：
             *     'abc'，'abdf'，'acd'表示的树中，第二层节点b的pass值为2，因为'abc'，'abdf'有相同的前缀'ab'。
             *          end：某点的pass表示————从根节点走到该节点组成的字符串出现了几次。比如：'abc'，'abdf'，'abc','abcd'。它们
             *     构成的前缀树中第三层节点c的pass=3('abc','abc','abcd'都是以'abc'开头的),end=2("abc"出现了两次)。
             *          根节点的pass————表示当前树中加入了多少字符串。
             *          根节点的end—————表示加入了多少个空字符串。
             * */
            this.pass = 0;
            this.end = 0;
            /**
             *      关于nexts的解释：next[0]=null表示走a的话没有路，next[1]=null的话表示走字符b没有路.....nexts[25]！=null
             *  的话表示走字符'z'是有路径的。
             * */
            nexts = new TrieNode[26];
        }
    }


    public static class Trie{
        private TrieNode root;

        public Trie(){
            root = new TrieNode();
        }


        //向前缀树中插入新的字符串
        public void insert(String word){
            if (word == null)
                return;
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i=0;i<chs.length;i++){         //可以看出来字符串有几个字符，那么此时的树就有几层
                index = chs[i] - 'a';
                if (node.nexts[index]==null)
                    node.nexts[index] = new TrieNode();
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }


        //删除前缀树中指定的字符串
        public void delete(String word){
            if (search(word)!=0){
                char[] chs = word.toCharArray();
                int index = 0;
                TrieNode node = root;
                node.pass--;
                for (int i=0;i<chs.length;i++){
                    index = chs[i]-'a';
                    if (--node.nexts[index].pass==0){
                        node.nexts[index]=null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }



        //查询指定的字符串出现了几次，找最终节点的end值。
        public int search(String word){
            if (word==null)
                return 0;
            TrieNode node = root;
            char[] chs = word.toCharArray();
            int index = 0;
            for (int i=0;i<chs.length;i++){
                index = chs[i]-'a';
                if (node.nexts[index]==null)            //走着走着发现没路了就返回0.
                    return 0;
                node = node.nexts[index];           //将node结果继续向前缀树的下一层挪。
            }
            return node.end;
        }


        //查找以某个串word为前缀的字符串有多少个。
        public int prefixNumber(String word){
            if (word==null)
                return 0;
            TrieNode node = root;
            int index = 0;
            char[] chs = word.toCharArray();
            for (int i=0;i<chs.length;i++){
                index = chs[i]-'a';
                if (node.nexts[index]==null)
                    return 0;
                node = node.nexts[index];
            }
            return node.pass;
        }
    }
}
