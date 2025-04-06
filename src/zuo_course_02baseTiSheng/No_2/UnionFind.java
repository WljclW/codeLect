package zuo_course_02baseTiSheng.No_2;


import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind {
    /**
     * @description: 并查集结构————实际上是一个不断往上指的图结构
     * @author: Zhou
     * @date: 2022/11/20 14:52
     */
    public static class Element<V>{
        /**
         * 这个结构就是对用户提供数据的初始封装————封装为一个对象。。。
         * */
        public V value;
        public Element(V value){
            this.value = value;
        }
    }


    public static class UnionFindSet<V>{
        public HashMap<V,Element<V>> elementMap;        //key:用户原始数据   value:封装后的数据
        public HashMap<Element<V>,Element<V>> fatherMap;       //key:某个元素      value:某个元素的父
        public HashMap<Element<V>,Integer> sizeMap;     //key:代表元素        value:该集合的大小

        public UnionFindSet(List<V> list){          //初始化时用户提供所有的数据
            /**
             * @description: 利用用户提供的列表来初始化并查集结构
             * @param list:
             * @return 并查集UnionFindSet的对象，该对象包含三个HashMap————
             *              ①elementMap：存放的是初始元素以及本结构需要它封装后的结构的映射
             *              ②fatherMap：存放当前节点到它的父节点的映射
             *              ③sizeMap：存放根父节点到这个根那一大堆元素数量的映射
             * @author: Zhou
             * @date: 2022/11/20 16:39
             */
            //创建需要的三个结构
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value:list){
                /**
                 *  将list中的每一个元素各自成堆。
                 * */
                Element<V> element = new Element<V>(value);     //首先将用户给的列表的每一个元素进行封装为element;
                elementMap.put(value,element);                 //将封装后的对象存储到elementMap
                fatherMap.put(element,element);                 //初始时每一个节点的父节点就是自己，因此key、value都是当前节点。
                sizeMap.put(element,1);                         //每一个跟父节点的大小都是一，因为初始一堆就一个节点，每个节点自成一堆。
            }
        }


        //查找指定元素所属集合的跟代表节点
        private Element<V> findHead(Element<V> element){
            /**
             * @description: 找到element节点的根父节点
             * @param element:
             * @return zuo_course_02baseTiSheng.No_2.UnionFind.Element<V>
             * @author: Zhou
             * @date: 2022/11/20 16:44
             */
            Stack<Element<V>> path = new Stack<>();     //stack的作用是记录整个找根代表节点的中间所有节点，记录的目的是为了"扁平化"。
            while (element != fatherMap.get(element)){
                /**
                 *  根代表节点的特点就是自己指向了自己的节点。。。。如果没有到根代表节点，则element一直向上追溯。。。
                 * */
                path.push(element);         //将路上碰到的节点一律入栈
                element = fatherMap.get(element);           //while循环结束后element存放的就是形参所在集合的根代表节点了
            }
            while(!path.isEmpty()){
                /**
                 *  这个while的作用是扁平化结构，即把刚刚过程找代表节点过程中遇到的所有节点  的代表节点改为它所属集合的根代表节点。
                 * */
                fatherMap.put(path.pop(),element);
            }
            return element;         //返回根代表节点
        }

        public boolean isSameSet(V a,V b){
            if (elementMap.containsKey(a) && elementMap.containsKey(b)){
                /**
                 * 判断elementMap是不是有形参的两个，如果没有说明我们就没有封装过，也就是list里面没有，返回false;
                 * 如果有，则通过findHead方法返回两个集合的根父节点，如果一样返回true,否则返回false;
                 * */
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));      //找两个元素的代表节点是不是同一个。
            }
            return false;
        }


        public void union(V a,V b){
            /**
             *      【注】判断两个点a和b是不是属于一个集合，只能使用findHead方法，不能通过fatherMap进行获取。
             *  原因：findHead方法会从这个element出发找到根，但是fatherMap只能获取目前记录的它归属的集合的代
             *  表节点。这个代表节点不一定时根。
             * */
            if (elementMap.containsKey(a) && elementMap.containsKey(b)){
                Element<V> aF = findHead(elementMap.get(a));
                Element<V> bF = findHead(elementMap.get(b));
                if (aF != bF){
                    /**
                     *  比较a,b所属集合哪个大哪个小，将小的那个集合的代表节点指向集合大的代表节点。
                     * */
                    Element<V> big = sizeMap.get(aF)>sizeMap.get(bF)?aF:bF;
                    Element<V> small = big == aF?bF:aF;
                    fatherMap.put(small,big);                           //小集合的代表节点 设置为 大集合的代表节点。
                    sizeMap.put(big,sizeMap.get(aF)+sizeMap.get(bF));       //更改sizeMap中大集合代表节点对应的value；
                    sizeMap.remove(small);      //删除sizeMap中小集合key，因为小鸡和已经挂到大集合下面，原来小集合的根不再是根了
                }
            }
        }

    }
}
